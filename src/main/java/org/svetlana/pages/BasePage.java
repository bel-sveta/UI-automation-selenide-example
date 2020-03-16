package org.svetlana.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class BasePage {

    private SelenideElement catalogHeading = $("h1.catalog-heading");
    private SelenideElement headerLogo = $("a.header__logo>img");
    private SelenideElement catalogButton = $("button.menu-toggler");
    private SelenideElement baseMainMenuItems = $("ul.menu-categories");


    @Step("Open Base Page")
    public BasePage openBasePage() {
        open("/");
        headerLogo.shouldBe(Condition.visible);
        return this;
    }

    @Step("Navigate to Mobile Phones Catalog")
    public MobilePhones navigateToMobilePhonesCatalog() {
        catalogButton.click();
        baseMainMenuItems.shouldBe(Condition.visible)
                .$$("li>a.menu-categories__link.js-menu-categories__link")
                .find(Condition.exactText("Смартфоны, ТВ и электроника"))
                .hover();
        sleep(500);
        baseMainMenuItems.parent()
                .$$("a.menu__link")
                .find(Condition.exactText("Мобильные телефоны"))
                .click();
        catalogHeading.shouldHave(Condition.text("Мобильные телефоны"));
        return new MobilePhones();

    }


}

