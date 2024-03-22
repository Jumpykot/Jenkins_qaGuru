package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testdata.testData;

public class RegistrationPageWithTestData extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    testData testData = new testData();

    @Test
    void minimalSuccessfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.userEmail)
                .setUserMobileNumber(testData.userPhoneNumber)
                .setGender(testData.userGender)
                .setUserDateOfBirth(testData.usersDayOfBirth, testData.usersMonthOfBirth, testData.usersYearOfBirth)

                .submit()

                .checkUserSubmitResults("Student Name", testData.firstName + " " + testData.lastName)
                .checkUserSubmitResults("Student Email", testData.userEmail)
                .checkUserSubmitResults("Gender", testData.userGender)
                .checkUserSubmitResults("Mobile", testData.userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", testData.usersDayOfBirth + " " + testData.usersMonthOfBirth + "," + testData.usersYearOfBirth);

    }

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.userEmail)
                .setUserMobileNumber(testData.userPhoneNumber)
                .setGender(testData.userGender)
                .setUserDateOfBirth(testData.usersDayOfBirth, testData.usersMonthOfBirth, testData.usersYearOfBirth)
                .setUserSubjects(testData.usersSubject)
                .setUserHobbies(testData.usersHobbie)
                .uploadPicture(testData.usersPicture)
                .setUserAddress(testData.userAddress)
                .setState(testData.usersState)
                .setCity(testData.usersCity)

                .submit()

                .checkUserSubmitResults("Student Name", testData.firstName + " " + testData.lastName)
                .checkUserSubmitResults("Student Email", testData.userEmail)
                .checkUserSubmitResults("Gender", testData.userGender)
                .checkUserSubmitResults("Mobile", testData.userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", testData.usersDayOfBirth + " " + testData.usersMonthOfBirth + "," + testData.usersYearOfBirth)
                .checkUserSubmitResults("Subjects", testData.usersSubject)
                .checkUserSubmitResults("Hobbies", testData.usersHobbie)
                .checkUserSubmitResults("Picture", testData.usersPicture)
                .checkUserSubmitResults("Address", testData.userAddress)
                .checkUserSubmitResults("State and City", testData.usersState + " " + testData.usersCity);

    }

    @Test
    void unsuccessfulRegistrationTest() {
        registrationPage.openPage()
                .submit()
                .checkUserUnsuccessfulSubmit();

    }
}
