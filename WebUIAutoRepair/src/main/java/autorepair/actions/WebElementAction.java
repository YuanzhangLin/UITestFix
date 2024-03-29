package autorepair.actions;

import autorepair.state.edge.Event;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Execute WebElement events through reflection
 */
public class WebElementAction {
    public static Object doAction(WebDriver driver,Event event) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        return doWebElementActionHelper(driver.findElement(event.getIdentification().getBy()),
                event.getMethod(),event.getArguments());
    }

    public static Object doWebElementActionHelper(WebElement webElement, String methodName,
                                                  List<Object> arguments) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class<?> clz = Class.forName("org.openqa.selenium.WebElement");
        Method[] ms = clz.getDeclaredMethods();
        List<Method> methods = new ArrayList<>(Arrays.asList(ms));
        for (Method method : methods) {
            int len = method.getParameterTypes().length;
            if (methodName.equals(method.getName()) && len == arguments.size()) {
                switch (len) {
                    case 0:
                        return method.invoke(webElement);
                    case 1:
                        return method.invoke(webElement, arguments.get(0));
                    case 2:
                        return method.invoke(webElement, arguments.get(0), arguments.get(1));
                }
            }
        }
        return null;
    }

}
