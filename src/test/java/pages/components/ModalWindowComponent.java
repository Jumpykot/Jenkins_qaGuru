package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalWindowComponent {

    public void checkSubmittingTheForm(String nameResult, String value) {
        $(".modal-open").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive table").$(byText(nameResult)).parent().shouldHave(text(value));
    }

}
