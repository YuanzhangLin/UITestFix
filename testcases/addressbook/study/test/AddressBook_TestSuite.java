package testcases.addressbook.study.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
	AddressBook_AddAddressBookTest.class,
	AddressBook_SearchAddressBookNameTest.class,
	AddressBook_SearchAddressBookEmailTest.class,
	AddressBook_SearchAddressBookTelephoneNegativeTest.class,
	AddressBook_AddGroupTest.class,
	AddressBook_AssignToGroupTest.class,
	AddressBook_SearchByGroupTest.class,
	AddressBook_CheckBirthdayInfoTest.class,
	AddressBook_CheckAddressBookTest.class,
	AddressBook_PrintAddressBookTest.class,
	AddressBook_EditAddressBookTest.class,
	AddressBook_EditGroupTest.class,
	AddressBook_UnassignFromGroupTest.class,
	AddressBook_RemoveGroupTest.class,
	AddressBook_RemoveAddressBookTest.class,
	AddressBook_AddMultipleAddressBookTest.class,
	AddressBook_SearchMultipleAddressBookNameTest.class,
	AddressBook_AddMultipleGroupsTest.class,
	AddressBook_AssignToMultipleGroupsTest.class,
	AddressBook_SearchByMultipleGroupsTest.class,
	AddressBook_CheckMultipleBirthdaysInfoTest.class,
	AddressBook_CheckMultipleAddressBookTest.class,
	AddressBook_PrintMultipleAddressBookTest.class,
	AddressBook_EditMultipleAddressBookTest.class,
	AddressBook_EditMultipleGroupsTest.class,
	AddressBook_UnassignFromMultipleGroupsTest.class,
	AddressBook_RemoveMultipleGroupsTest.class,
	AddressBook_RemoveMultipleAddressBookTest.class
})

public class AddressBook_TestSuite {}