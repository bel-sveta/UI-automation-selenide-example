package org.svetlana;

import org.svetlana.pages.BasePage;
import org.svetlana.pages.MobilePhones;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SortingVerification extends BaseTest {


    @Test
    public void verifySortingFromCheapestPrice() {
        MobilePhones mobilePhones = new BasePage()
                .openBasePage()
                .navigateToMobilePhonesCatalog()
                .sortFromLowestPrice()
                .loadMoreItems();
        Assert.assertTrue(mobilePhones.isSortingFromLowestPriceCorrect());
    }

    @Test
    public void verifySortingFromExpensivePrice() {
        MobilePhones mobilePhones = new BasePage()
                .openBasePage()
                .navigateToMobilePhonesCatalog()
                .sortFromHighestPrice()
                .loadMoreItems();
        Assert.assertTrue(mobilePhones.isSortingFromHighestPriceCorrect());
    }
}
