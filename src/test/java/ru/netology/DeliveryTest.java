package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @Test
    public void shouldCheckDelivery() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "800x300";
        open("http://localhost:9999/");
        // $x("//span[@data-test-id='city']").click();



    }
}
