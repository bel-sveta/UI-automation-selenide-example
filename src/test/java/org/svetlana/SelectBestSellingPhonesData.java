package org.svetlana;

import org.svetlana.models.PhoneModel;
import org.svetlana.pages.BasePage;
import org.svetlana.pages.MobilePhones;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;


public class SelectBestSellingPhonesData extends BaseTest {

    private File file = new File("src/test/resources/PhonesData.txt");

    @Test
    public void selectPhonesDataAndWriteToFile() {
        MobilePhones mobilePhones = new BasePage()
                .openBasePage()
                .navigateToMobilePhonesCatalog()
                .sortByPopularity();
        List<PhoneModel> listOfBestSellingPhones = mobilePhones.getListOfBestSellingPhones();
        mobilePhones.writePhonesDataToFile(listOfBestSellingPhones, file);
    }
}
