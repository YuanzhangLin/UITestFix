package testcases.collabtive.study.test;

import testcases.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@SuiteClasses({
        Collabtive_AddProjectTest.class,
        Collabtive_EditProjectTest.class,
        Collabtive_SearchProjectTest.class,
        Collabtive_CloseProjectTest.class,
        Collabtive_SearchClosedProjectTest.class,
        Collabtive_OpenProjectTest.class,
        Collabtive_AddRoleTest.class,
        Collabtive_EditRoleTest.class,
        Collabtive_AddUserTest.class,
        Collabtive_EditUserRoleTest.class,
        Collabtive_LoginUserTest.class,
        Collabtive_AssignUserToProjectTest.class,
        Collabtive_AddMilestoneTest.class,
        Collabtive_RemoveMilestoneTest.class,
        Collabtive_AddMilestoneTest.class,
        Collabtive_EditMilestoneTest.class,
        Collabtive_CloseMilestoneTest.class,
        Collabtive_OpenMilestoneTest.class,
        Collabtive_AddTasklistTest.class,
        Collabtive_CloseTasklistTest.class,
        Collabtive_OpenTasklistTest.class,
        Collabtive_AddAndRemoveMultipleTasksTest.class,
        Collabtive_AddTaskDesktopPresentTest.class,
        Collabtive_RemoveTaskDesktopNotPresentTest.class,
        Collabtive_AddTasksTest.class,
        Collabtive_CloseTasksTest.class,
        Collabtive_OpenTasksTest.class,
        Collabtive_CloseTasksProjectPercentageTest.class,
        Collabtive_OpenTasksProjectPercentageTest.class,
        Collabtive_AddUserAlreadyPresentTest.class,
        Collabtive_AddUserEmptyValuesTest.class,
        Collabtive_AddAndRemoveLateMilestoneTest.class,
        Collabtive_AddMultipleProjectsTest.class,
        Collabtive_SearchMultipleProjectsTest.class,
        Collabtive_RemoveMultipleProjectsTest.class,
        Collabtive_DeassignUserToProjectTest.class,
        Collabtive_RemoveTasksTest.class,
        Collabtive_RemoveTasklistTest.class,
        Collabtive_RemoveRoleTest.class,
        Collabtive_RemoveUserTest.class,
        Collabtive_RemoveProjectTest.class,
})

public class Collabtive_TestSuite {
}
