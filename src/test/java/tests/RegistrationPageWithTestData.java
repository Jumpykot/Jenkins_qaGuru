package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.RegistrationPage;
import testdata.testData;

import java.util.ArrayList;
import java.util.HashMap;

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

        ChromeOptions options = new ChromeOptions();

        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
        }});

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
    @Tag("HomeWork tests")
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
    @Tag("HomeWork tests")
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
    @Tag("HomeWork tests")
    void unsuccessfulRegistrationTest() {
        step("Open form and submit", () -> {
        registrationPage.openPage()
                .submit()
                .checkUserUnsuccessfulSubmit();
        });
    }
}
