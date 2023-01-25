package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {
    private String generateDate(int addDay, String pattern) {
        return LocalDate.now().plusDays(addDay).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldCheckDelivery() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x800";
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Тверь");
        $("span.menu-item__control").click();
        String currentDate = generateDate(7, "dd.mm.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(currentDate);
        $("[data-test-id='name'] input").setValue("Александра Чернова");
        $("[data-test-id='phone'] input").setValue("+79201793885");
        $x("//label[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));
    }
}
