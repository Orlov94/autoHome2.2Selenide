package ru.netology.cardDelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.apache.hc.client5.http.config.Configurable;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class CardDeliveryOrderTest {

    public static String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;

    @Test
    void deliveryCard() {


        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Владикавказ");
        $("[placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        //$("[placeholder='Дата встречи']").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue("01.08.2022");
        $("[data-test-id='name'] input").setValue("Орлов Владислав");
        $("[data-test-id='phone'] input ").setValue("+79240918327");
        $("[data-test-id='agreement'] ").click();
        $x("//span[@class='button__content'] ").click();
        $x("//div[@class='notification__content']")
                .should(Condition.text("Встреча успешно забронирована на " + "01.08.2022"), Duration.ofSeconds(15));



    }
}
