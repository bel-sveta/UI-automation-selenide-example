package org.svetlana;

import org.svetlana.models.PhoneModel;
import org.svetlana.pages.BasePage;
import org.svetlana.pages.CartModal;
import org.svetlana.pages.MobilePhones;
import org.testng.Assert;
import org.testng.annotations.Test;


public class VerifyPhoneAddedToCart extends BaseTest {


    @Test
    public void verifyIfPhoneAddedToCart() {
        MobilePhones mobilePhones = new BasePage()
                .openBasePage()
                .navigateToMobilePhonesCatalog()
                .sortByPopularity();
        PhoneModel firstPhone = mobilePhones.getFirstBestSellingPhone();
        CartModal cartModal = mobilePhones.clickBuyPhone(firstPhone.getTitle());
        Assert.assertTrue(cartModal.isPhonePresentInCart(firstPhone));

    }
}
