package org.svetlana.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.svetlana.models.PhoneModel;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartModal {
    private SelenideElement cartModal = $(".modal-content.cart-modal");
    private ElementsCollection cartItems = $$("li.cart-modal__item");
    private String l_cartItemTitle = "a.cart-modal__title";
    private String l_cartItemCost = ".cart-modal__coast>.cart-modal__coast-digits";

    public CartModal() {
        $(".modal-content.cart-modal").shouldBe(visible);
    }

    @Step("Select all items in the cart")
    public Map<String, Integer> SelectCartItemsData() {
        Map<String, Integer> itemData = new HashMap<>();
        if (cartItems != null)
            for (SelenideElement element : cartItems) {
                String itemTitle = element.$(l_cartItemTitle).getAttribute("title");
                Integer itemPrice = Integer.parseInt(element.$(l_cartItemCost).getText().replaceAll("\\s*", ""));
                itemData.put(itemTitle, itemPrice);


            }
        return itemData;
    }


    @Step("Verify if the particular phone is in the cart")
    public boolean isPhonePresentInCart(PhoneModel phone) {
        Map<String, Integer> items = SelectCartItemsData();
        String title = phone.getTitle();
        Integer price = phone.getPrice();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (entry.getKey().equals(title) && (entry.getValue().equals(price))) {
                return true;
            }

        }
        return false;
    }


    public void closeCartModal() {
        cartModal.$("button.modal__close").click();
        cartModal.$("button.modal__close").shouldBe(not(visible));
    }


}
