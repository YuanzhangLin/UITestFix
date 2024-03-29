package testcases.mrbs.study.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
	MRBS_AddBuildingTest.class,
	MRBS_AddRoomTest.class,
	MRBS_CheckBuildingRoomTest.class,
	MRBS_AddEntryTest.class,
	MRBS_CheckEntryTest.class,
	MRBS_SearchEntryTest.class,
	MRBS_AddLongNameBuildingNegativeTest.class,
	MRBS_AddLongNameRoomNegativeTest.class,
	MRBS_SearchEntryNegativeTest.class,
	MRBS_AddConflictualEntryNegativeTest.class,
	MRBS_RemoveEntryTest.class,
	MRBS_AddMultipleEntriesSameRoomSameDayTest.class,
	MRBS_AddMultipleEntriesSameRoomDifferentDaysTest.class,
	MRBS_SearchMultipleEntriesTest.class,
	MRBS_RemoveRoomTest.class,
	MRBS_AddMultipleRoomsTest.class,
	MRBS_CheckMultipleBuildingRoomsTest.class,
	MRBS_AddMultipleEntriesDifferentRoomsSameDaysTest.class,
	MRBS_AddMultipleEntriesDifferentRoomsDifferentDaysTest.class,
	MRBS_CheckMultipleEntriesTest.class,
	MRBS_RemoveMultipleEntriesTest.class,
	MRBS_RemoveMultipleRoomsTest.class,
	MRBS_AddAndRemoveSerialEntryTest.class,
	MRBS_RemoveBuildingTest.class
})

public class MRBS_TestSuite {}