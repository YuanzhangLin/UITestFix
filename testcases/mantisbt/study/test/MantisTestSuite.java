package testcases.mantisbt.study.test;
import testcases.Constants;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import config.DriverConfig;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AddUserTest.class,
	AddUserWrongTest.class,
	AddUserEmptyTest.class,
	AddProjectTest.class,
	AddProjectWrongTest.class,
	AddProjectEmptyTest.class,
	AddCategoryTest.class,
	AddCategoryWrongTest.class,
	AddCategoryEmptyTest.class,
	AddIssueTest.class,
	AddIssueEmptyTest.class,
	AssignIssueTest.class,
	SummaryIssueTest.class,
	UpdateIssuePriorityTest.class,
	UpdateIssueSeverityTest.class,
	UpdateIssueStatusNewTest.class,
	UpdateIssueStatusFeedbackTest.class,
	UpdateIssueStatusAcknowledgedTest.class,
	UpdateIssueStatusConfirmedTest.class,
	UpdateIssueStatusAssignedTest.class,
	UpdateIssueStatusResolvedTest.class,
	UpdateIssueSummaryTest.class,
	UpdateProjectDescriptionTest.class,
	UpdateProjectStatusTest.class,
	UpdateProjectViewStatusTest.class,
	UpdateCategoryTest.class,
	UpdateCategoryEmptyTest.class,
	UpdateUserTest.class,
	UpdateUserEmptyTest.class,
	DeleteCategoryTest.class,
	DeleteIssueTest.class,
	DeleteProjectTest.class,
	DeleteUserTest.class,
	LoginNegativeTest.class,
	LogoutTest.class
})


public class MantisTestSuite {}
