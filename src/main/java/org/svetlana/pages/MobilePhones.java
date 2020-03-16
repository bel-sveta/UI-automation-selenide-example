package org.svetlana.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.svetlana.models.PhoneModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.svetlana.utils.Utils.isSortedAscending;
import static org.svetlana.utils.Utils.isSortedDescending;

public class MobilePhones {

    private ElementsCollection bestSellingPhones = $$x("//*[contains(@class,'promo-label_type_popularity')]/ancestor::div[@class='goods-tile']");
    private ElementsCollection phonesPrices = $$(".goods-tile .goods-tile__price-value");
    private SelenideElement sortDropDown = $(".catalog-settings__sorting>select");
    private SelenideElement loadMoreItemsLink = $("a.catalog-more__link");


    @Step("Get the list of best selling phones on the current page")
    public List<PhoneModel> getListOfBestSellingPhones() {
        return new ArrayList<PhoneModel>() {
            {
                for (SelenideElement element : bestSellingPhones) {
                    add(new PhoneModel(element));
                }
            }
        };
    }


    public PhoneModel getBestSellingPhoneByIndex(int index) {
        return getListOfBestSellingPhones().get(index);
    }


    @Step("Get Data of the Best Selling Phone which is displayed first on the page")
    public PhoneModel getFirstBestSellingPhone() {
        return getBestSellingPhoneByIndex(0);
    }


    @Step("Click Buy Phone button")
    public CartModal clickBuyPhone(String phoneTitle) {
        $$("a.goods-tile__heading").find(exactText(phoneTitle)).parent().find("button.buy-button").click();
        sleep(2000);
        return new CartModal();

    }


    @Step("Write Phones Data to the file")
    public void writePhonesDataToFile(List<PhoneModel> listOfBestSellingPhones, File file) {
        try {
            FileOutputStream fileOS = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fileOS);

            for (PhoneModel phone : listOfBestSellingPhones) {
                pw.println(phone.getTitle() + " - " + phone.getPrice());
            }
            pw.flush();
            pw.close();
            fileOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Step("Sort items on the page from lowest price to highest price")
    public MobilePhones sortFromLowestPrice() {
        sortDropDown.selectOptionByValue("1: cheap");
        sleep(2000);
        return this;
    }


    @Step("Sort items on the page from highest price to lowest price")
    public MobilePhones sortFromHighestPrice() {
        sortDropDown.selectOptionByValue("2: expensive");
        sleep(2000);
        return this;
    }

    @Step("Sort items on the page by Popularity")
    public MobilePhones sortByPopularity() {
        sortDropDown.selectOptionByValue("3: popularity");
        sleep(2000);
        return this;
    }

    @Step("Click and load more items")
    public MobilePhones loadMoreItems() {
        loadMoreItemsLink.scrollTo().click();
        sleep(2000);
        return this;
    }

    @Step("Verify if items are sorted correctly from lowest price to highest price")
    public boolean isSortingFromLowestPriceCorrect() {
        List<Integer> prices = new ArrayList<>();
        for (SelenideElement element : phonesPrices) {
            Integer price = Integer.parseInt(element.getText().replaceAll("\\s*", ""));
            prices.add(price);
        }
        return isSortedAscending(prices);
    }

    @Step("Verify if items are sorted correctly from highest price to lowest price")
    public boolean isSortingFromHighestPriceCorrect() {
        List<Integer> prices = new ArrayList<>();
        for (SelenideElement element : phonesPrices) {
            Integer price = Integer.parseInt(element.getText().replaceAll("\\s*", ""));
            prices.add(price);
        }
        return isSortedDescending(prices);

    }

}

