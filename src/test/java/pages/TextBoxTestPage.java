package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTestPage {

    private SelenideElement userFullNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            userCurrentAddress = $("#currentAddress"),
            userPermanentAddress = $("#permanentAddress"),
            submitForm = $("#submit"),
            formOutput = $("#output");

    public TextBoxTestPage openPage() {
        open("/text-box");
        $(".main-header").shouldHave(text("Text Box"));

        return this;
    }

    public TextBoxTestPage setFullName(String value) {
        userFullNameInput.setValue(value);

        return this;
    }

    public TextBoxTestPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxTestPage setCurrentAddress(String value) {
        userCurrentAddress.setValue(value);

        return this;
    }

    public TextBoxTestPage setPermanentAddress(String value) {
        userPermanentAddress.setValue(value);

        return this;
    }

    public TextBoxTestPage submit() {
        submitForm.click();

        return this;
    }

    public TextBoxTestPage checkResults(String nameResult, String value) {
        formOutput.$("#" + nameResult).shouldHave(text(value));

        return this;
    }

}
