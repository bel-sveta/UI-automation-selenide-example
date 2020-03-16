package org.svetlana;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.svetlana.listeners.TestListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class BaseTest {


    @BeforeSuite
    public void setUp() {
        WebDriverManager.chromedriver().version("79").setup();
        Configuration.baseUrl = "https://rozetka.com.ua";
        Configuration.timeout = 20000;
        Configuration.startMaximized = true;

    }

}
