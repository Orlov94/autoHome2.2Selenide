package ru.netology.carddelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void deliveryCard() {

        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Владикавказ");
        $("[placeholder='Дата встречи']").sendKeys(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(generateDate(4));
        $("[data-test-id='name'] input").setValue("Орлов Владислав");
        $("[data-test-id='phone'] input ").setValue("+79240918327");
        $("[data-test-id='agreement'] ").click();
        $x("//span[@class='button__content'] ").click();
        $x("//div[@class='notification__content']")
                .should(Condition.text("Встреча успешно забронирована на " + generateDate(4)), Duration.ofSeconds(15));


    }
}
