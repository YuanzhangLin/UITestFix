package autorepair.instrument;

import autorepair.instrument.newprocess.NewWebDriverProcess;
import autorepair.instrument.newprocess.NewWebElementProcess;
import autorepair.instrument.oldprocess.OldWebDriverProcess;
import autorepair.instrument.oldprocess.OldWebElementProcess;
import autorepair.state.statematchine.StateMachineImpl;
import config.Settings;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.openqa.selenium.WebDriver;
import utils.UtilsProperties;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * define all aspect
 */
@Aspect
public class Trace {
    boolean version = false; // false -> old, true -> new
    String className;
    String savePath;
    WebDriver webDriver;
    StateMachineImpl oldStateMachine;
    StateMachineImpl newStateMachine;
    private boolean aspectLock = true;

    public Trace() {
        try {
            Properties configProperties = UtilsProperties.getConfigProperties();
            version = !configProperties.getProperty("version").trim().equals("old");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    @Pointcut("execution (* testcases.**..*.setUp(..)) || execution (* testcases.**..*.before(..) )")
    public void setUpPointcut(JoinPoint joinPoint) {
    }

    @Before("setUpPointcut(JoinPoint)")
    public void beforeSetUp(JoinPoint joinPoint) {
        // get className
        String signature = joinPoint.getSignature().toString();
        signature = signature.substring(signature.indexOf(" ") + 1, signature.lastIndexOf("."));
        className = signature.substring(signature.lastIndexOf(".") + 1);
        aspectLock = true;
        if (!version) {
            // version is old
            savePath = Settings.OUTPUT_PATH + "old" + File.separator + signature.replaceAll("[.]", "/") + File.separator;
            System.out.println(savePath);
            oldStateMachine = new StateMachineImpl(className, savePath);
        } else {
            savePath = Settings.OUTPUT_PATH + "new" + File.separator + signature.replaceAll("[.]", "/") + File.separator;
            oldStateMachine = new StateMachineImpl(className,
                    Settings.OUTPUT_PATH + "old" + File.separator + signature.replaceAll("[.]", "/") + File.separator
            );
            try {
                oldStateMachine.load(oldStateMachine.getSavePath());
            } catch (IOException | ClassNotFoundException ioException) {
                System.out.println("could not find oldStateMachine.");
            }
            newStateMachine = new StateMachineImpl(className, savePath);
        }

    }

    @Pointcut("call (* org.openqa.selenium.WebDriver.get(..))")
    public void WebDriverGetPointcut(JoinPoint joinPoint) {
    }

    @Before("WebDriverGetPointcut(JoinPoint)")
    public void beforeWebDriverGet(JoinPoint joinPoint) {
        if (oldStateMachine != null) {
            oldStateMachine.setDriver((WebDriver) joinPoint.getTarget());
        }
        if (newStateMachine != null) {
            newStateMachine.setDriver((WebDriver) joinPoint.getTarget());
        }
        webDriver = (WebDriver) joinPoint.getTarget();
    }


    @Pointcut("call (* org.openqa.selenium.WebElement.*(..))")
    public void webElementPointcut(ProceedingJoinPoint proceedingJoinPoint) {
    }

    @Around("webElementPointcut(ProceedingJoinPoint)")
    public Object aroundWebElement(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = null;
        if (aspectLock) {
            aspectLock = false;
            try {
                if (!version) {
                    // version is old
                    object = OldWebElementProcess.webelementprocess(oldStateMachine, webDriver, proceedingJoinPoint);
                } else {
                    object = NewWebElementProcess.webelementprocess(oldStateMachine, newStateMachine, webDriver, proceedingJoinPoint);
                }
            } catch (Exception exception) {
                aspectLock = true;
                throw exception;
            }
            aspectLock = true;
        } else {
            object = proceedingJoinPoint.proceed();
        }
        return object;
    }

    @Pointcut("call (* org.openqa.selenium.WebDriver.findElement(..))")
    public void webDriverPointcut(ProceedingJoinPoint proceedingJoinPoint) {
    }

    @Around("webDriverPointcut(ProceedingJoinPoint)")
    public Object aroundWebDriver(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        webDriver = (WebDriver) proceedingJoinPoint.getTarget();
        if (proceedingJoinPoint.getSignature().getName().equals("quit")) {
            return proceedingJoinPoint.proceed();
        }
        if (version) {
            newStateMachine.setDriver(webDriver);
        } else {
            oldStateMachine.setDriver(webDriver);
        }
        Object result = null;
        if (proceedingJoinPoint.getSignature().getName().equals("findElement") && aspectLock) {
            // findElement
            aspectLock = false;
            System.out.println("driver.findElement" + Arrays.toString(proceedingJoinPoint.getArgs()));
            try {
                if (!version) {
                    // version is old
                    result = OldWebDriverProcess.findElementProcess(oldStateMachine, webDriver, proceedingJoinPoint);
                } else {
                    result = NewWebDriverProcess.findElementProcess(oldStateMachine, newStateMachine, webDriver, proceedingJoinPoint);
                }
            } catch (Exception exception) {
                aspectLock = true;
                throw exception;
            }
            aspectLock = true;
        } else {
            // others
            result = proceedingJoinPoint.proceed();
        }
        return result;
    }

    @Pointcut("execution (* testcases.**..*.close(..)) || execution (* testcases.**..*.end(..)) || execution (* testcases.**..*.tearDown(..))")
    public void closePointcut(JoinPoint joinPoint) {
    }

    @Before("closePointcut(JoinPoint)")
    public void beforeClose(JoinPoint joinPoint) {
        // get className
        if (!version) {
            // version is old
            oldStateMachine.save(savePath);
        } else {
            newStateMachine.save(savePath);
        }
        aspectLock = true;
    }
}
