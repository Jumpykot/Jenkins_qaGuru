package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import testdata.testData;

import java.util.Map;

import static io.qameta.allure.Allure.step;

public class RegistrationPageWithTestData extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    testData testData = new testData();

    @BeforeAll
    static void beforeAll() {

        System.out.println("     Запуск конфигураций");
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @Tag("HomeWorkTests")
    void minimalSuccessfulRegistrationTest() {
        step("Open form", () -> {
        registrationPage.openPage();
        });
        step("Fill form", () -> {
        registrationPage.setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setUserEmail(testData.userEmail)
                .setUserMobileNumber(testData.userPhoneNumber)
                .setGender(testData.userGender)
                .setUserDateOfBirth(testData.usersDayOfBirth, testData.usersMonthOfBirth, testData.usersYearOfBirth)

                .submit();
        });
        step("Verify results", () -> {
        registrationPage.checkUserSubmitResults("Student Name", testData.firstName + " " + testData.lastName)
                .checkUserSubmitResults("Student Email", testData.userEmail)
                .checkUserSubmitResults("Gender", testData.userGender)
                .checkUserSubmitResults("Mobile", testData.userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", testData.usersDayOfBirth + " " + testData.usersMonthOfBirth + "," + testData.usersYearOfBirth);
        });
    }

    @Test
    @Tag("HomeWorkTests")
    void successfulRegistrationTest() {
        step("Open form", () -> {
            registrationPage.openPage();
        });
        step("Fill form", () -> {
        registrationPage.setFirstName(testData.firstName)
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

                .submit();
        });
        step("Verify results", () -> {
        registrationPage.checkUserSubmitResults("Student Name", testData.firstName + " " + testData.lastName)
                .checkUserSubmitResults("Student Email", testData.userEmail)
                .checkUserSubmitResults("Gender", testData.userGender)
                .checkUserSubmitResults("Mobile", testData.userPhoneNumber)
                .checkUserSubmitResults("Date of Birth", testData.usersDayOfBirth + " " + testData.usersMonthOfBirth + "," + testData.usersYearOfBirth)
                .checkUserSubmitResults("Subjects", testData.usersSubject)
                .checkUserSubmitResults("Hobbies", testData.usersHobbie)
                .checkUserSubmitResults("Picture", testData.usersPicture)
                .checkUserSubmitResults("Address", testData.userAddress)
                .checkUserSubmitResults("State and City", testData.usersState + " " + testData.usersCity);
        });
    }

    @Test
    @Tag("HomeWorkTests")
    void unsuccessfulRegistrationTest() {
        step("Open form and submit", () -> {
        registrationPage.openPage()
                .submit()
                .checkUserUnsuccessfulSubmit();
        });
    }
}
