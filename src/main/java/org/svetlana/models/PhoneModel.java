package org.svetlana.models;

import com.codeborne.selenide.SelenideElement;

public class PhoneModel {
    private String l_phoneTitle = ".goods-tile  .goods-tile__title";
    private String l_phonePrice = ".goods-tile__price-value";

    private String title;
    private Integer price;

    public PhoneModel(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public PhoneModel(SelenideElement mobilePhoneElement) {
        String phoneTitle = mobilePhoneElement.$(l_phoneTitle).getText();
        Integer phonePrice = Integer.parseInt(mobilePhoneElement.$(l_phonePrice).getText().replaceAll("\\s*", ""));
        title = phoneTitle;
        price = phonePrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PhoneModel{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
