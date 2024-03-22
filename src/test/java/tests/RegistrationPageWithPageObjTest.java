package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.sleep;

public class RegistrationPageWithPageObjTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void minimalSuccessfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName("Biba")
                .setLastName("Bubov")
                .setUserEmail("Buba@biba.com")
                .setUserMobileNumber("7123456789")
                .setGender("Male")
                .setUserDateOfBirth(16, "February", 1995)

                .submit()

                .checkUserSubmitResults("Student Name", "Biba Bubov")
                .checkUserSubmitResults("Student Email", "Buba@biba.com")
                .checkUserSubmitResults("Gender", "Male")
                .checkUserSubmitResults("Mobile", "7123456789")
                .checkUserSubmitResults("Date of Birth", "16 February,1995");

    }

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()

                .setFirstName("Biba")
                .setLastName("Bubov")
                .setUserEmail("Buba@biba.com")
                .setUserMobileNumber("7123456789")
                .setGender("Male")
                .setUserDateOfBirth(16, "February", 1995)
                .setUserSubjects("Arts")
                .setUserHobbies("Sports")
                .uploadPicture("apple-touch-icon.png")
                .setUserAddress("Baker street 221b")
                .setState("NCR")
                .setCity("Delhi")

                .submit()

                .checkUserSubmitResults("Student Name", "Biba Bubov")
                .checkUserSubmitResults("Student Email", "Buba@biba.com")
                .checkUserSubmitResults("Gender", "Male")
                .checkUserSubmitResults("Mobile", "7123456789")
                .checkUserSubmitResults("Date of Birth", "16 February,1995")
                .checkUserSubmitResults("Subjects", "Arts")
                .checkUserSubmitResults("Hobbies", "Sports")
                .checkUserSubmitResults("Picture", "apple-touch-icon.png")
                .checkUserSubmitResults("Address", "Baker street 221b")
                .checkUserSubmitResults("State and City", "NCR Delhi");

    }

    @Test
    void unsuccessfulRegistrationTest() {
        registrationPage.openPage()
                .submit()
                .checkUserUnsuccessfulSubmit();

    }
}
