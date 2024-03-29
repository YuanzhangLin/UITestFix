package testcases.Claroline_Test_Suite.study.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
	Claroline_AddUserTest.class,
	Claroline_SearchUserTest.class,
	Claroline_LoginUserTest.class,
	Claroline_AddCourseTest.class,
	Claroline_SearchCourseTest.class,
	Claroline_EnrolUserTest.class,
	Claroline_AddCourseEventTest.class,
	Claroline_AddCourseExerciseTest.class,
	Claroline_MakeCourseExerciseVisibleTest.class,
	Claroline_AddCourseExerciseQuestionsTest.class,
	Claroline_DoCourseExerciseQuestionsTest.class,
	Claroline_ViewProfileStatisticsUserTest.class,
	Claroline_AddMultipleUsersTest.class,
	Claroline_SearchMultipleUsersTest.class,
	Claroline_SearchStudentTest.class,
	Claroline_SearchTeacherTest.class,
	Claroline_SearchAdminTest.class,
	Claroline_EnrolMultipleUsersTest.class,
	Claroline_DoCourseExerciseQuestionsMultipleUsersTest.class,
	Claroline_RemoveEnrolMultipleUsersTest.class,
	Claroline_RemoveMultipleUsersTest.class,
	Claroline_AddEmptyUserTest.class,
	Claroline_AddWrongEmailUserTest.class,
	Claroline_AddTwiceUserTest.class,
	Claroline_AddWrongPasswordUserTest.class,
	Claroline_AddEmptyCourseTest.class,
	Claroline_AddDeniedCourseTest.class,
	Claroline_EnrolDeniedCourseTest.class,
	Claroline_AddPasswordCourseTest.class,
	Claroline_EnrolPasswordCourseWrongPasswordUserTest.class,
	Claroline_EnrolPasswordCourseGoodPasswordUserTest.class,
	Claroline_SearchAllowedCourseTest.class,
	Claroline_SearchAndRemovePasswordCourseTest.class,
	Claroline_SearchAndRemoveDeniedCourseTest.class,
	Claroline_RemoveCourseExerciseQuestionsTest.class,
	Claroline_RemoveCourseEventTest.class,
	Claroline_RemoveCourseExerciseTest.class,
	Claroline_RemoveEnrolUserTest.class,
	Claroline_RemoveUserTest.class,
	Claroline_RemoveCourseTest.class
})

public class Claroline_TestSuite {}
