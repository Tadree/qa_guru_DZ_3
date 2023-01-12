package ru.jiehk;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void formTest() {
        String firstName = "Elena";
        String lastName = "Kosiakova";
        String email = "test@test.ru";
        String gender = "Female";
        String mobile = "1234567890";
        String subjects = "Maths";
        String hobbies = "Sports";
        String currentAddress = "Test address";
        String state = "NCR";
        String city = "Noida";

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__day--019").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/test.jpeg"));
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table > tbody > tr:nth-child(1) > td:nth-child(2)").shouldHave(text(firstName + ' ' + lastName));
        $(".table > tbody > tr:nth-child(2) > td:nth-child(2)").shouldHave(text(email));
        $(".table > tbody > tr:nth-child(3) > td:nth-child(2)").shouldHave(text(gender));
        $(".table > tbody > tr:nth-child(4) > td:nth-child(2)").shouldHave(text(mobile));
        $(".table > tbody > tr:nth-child(5) > td:nth-child(2)").shouldHave(text("19 January,1997"));
        $(".table > tbody > tr:nth-child(6) > td:nth-child(2)").shouldHave(text(subjects));
        $(".table > tbody > tr:nth-child(7) > td:nth-child(2)").shouldHave(text(hobbies));
        $(".table > tbody > tr:nth-child(8) > td:nth-child(2)").shouldHave(text("test.jpeg"));
        $(".table > tbody > tr:nth-child(9) > td:nth-child(2)").shouldHave(text(currentAddress));
        $(".table > tbody > tr:nth-child(10) > td:nth-child(2)").shouldHave(text(state + ' ' + city));
    }
}
