package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ModalWindowComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            userDateOfBirth = $("#dateOfBirthInput"),
            userSubjects = $("#subjectsInput"),
            userHobbies = $("#hobbiesWrapper"),
            fileDownload = $("#uploadPicture"),
            userAddress = $("#currentAddress"),
            userState = $("#state"),
            userCity = $("#city");

    CalendarComponent calendarComponent = new CalendarComponent();
    ModalWindowComponent modalWindowComponent = new ModalWindowComponent();


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }
    public RegistrationPage setLastName (String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserEmail (String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender (String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserMobileNumber (String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setUserDateOfBirth(Integer day, String month, Integer year) {
        userDateOfBirth.click();

        calendarComponent.setDate(day.toString(), month, year.toString());

        return this;

    }

    public RegistrationPage setUserSubjects (String value) {
        userSubjects.setValue(value).pressTab();

        return this;
    }

    public RegistrationPage setUserHobbies (String value) {
        userHobbies.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPicture (String value) {
        fileDownload.uploadFromClasspath(value);

        return this;
    }
    public RegistrationPage setUserAddress (String value) {
        userAddress.setValue(value);

        return this;
    }

    public RegistrationPage setState (String value) {
        userState.click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity (String value) {
        userCity.click();
        $(byText(value)).click();

        return this;
    }

    public RegistrationPage submit() {
        $("#submit").click();

        return this;
    }

    public RegistrationPage checkUserSubmitResults (String nameResult, String value) {
        modalWindowComponent.checkSubmittingTheForm(nameResult, value);
        return this;
    }

    public RegistrationPage checkUserUnsuccessfulSubmit () {
        $(".modal-content").shouldNotBe(visible);
        return this;
    }

}
