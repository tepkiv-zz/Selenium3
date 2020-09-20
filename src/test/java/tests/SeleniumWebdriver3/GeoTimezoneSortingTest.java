package tests.SeleniumWebdriver3;

import com.BaseTest;
import com.google.common.collect.Ordering;
import com.litecart.EditCountryPage;
import com.litecart.EditGeoZonePage;
import com.litecart.GeoZonesPage;
import com.litecart.SidebarMenuEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.litecart.AdminPage.CountriesForm.*;
import static com.litecart.EditCountryPage.ZONE_TABLE_CODE_COLUMN;
import static com.litecart.EditGeoZonePage.FORM_GEO_ZONE_TABLE_ROW;
import static com.litecart.GeoZonesPage.GEO_ZONES_TABLE_ROWS;
import static com.litecart.GeoZonesPage.NAME_COLUMN;
import static org.assertj.core.api.Assertions.assertThat;

public class GeoTimezoneSortingTest extends BaseTest {
    Logger logger = LogManager.getLogger(GeoTimezoneSortingTest.class);
    AdminPageHelper adminPageHelper = new AdminPageHelper();

    @Test
    public void countriesOrder() {
        openAdmin();
        adminPageHelper.clickMenuItem(SidebarMenuEnum.Countries);

        List<String> countries = new ArrayList<>();
        // Collect countries
        for (WebElement element : findElements(CONTENT_TABLE_ROWS)) {
            countries.add(element.findElement(By.xpath(COUNTRY_COLUMN)).getText());
        }
        assertThat(Ordering.natural().isOrdered(countries)).as("Countries were not sorted alphabetically").isEqualTo(true);
    }

    @Test
    public void countriesZonesOrder() {
        openAdmin();
        adminPageHelper.clickMenuItem(SidebarMenuEnum.Countries);

        List<String> countries = new ArrayList<>();
        // Collect countries, Zone amount > 0
        for (WebElement element : findElements(CONTENT_TABLE_ROWS)) {
            if (Integer.parseInt(element.findElement(By.xpath(ZONES_COLUMN)).getText()) != 0) {
                countries.add(element.findElement(By.xpath(COUNTRY_COLUMN)).getText());
            }
        }
        logger.info("Countries with Zone > 0, {}", countries);

        SoftAssertions.assertSoftly(softly -> {
            // Go through countries
            for (String country : countries) {
                List<String> codes = new ArrayList<>();
                findElement(By.xpath(CONTENT_TABLE_ROWS + COUNTRY_COLUMN.replace(".", "") + String.format("[contains(text(),'%s')]", country))).click();
                //Collect zones
                for (WebElement webElement : findElements(ZONE_TABLE_CODE_COLUMN)) {
                    codes.add(webElement.getText());
                }
                logger.info("{} found for {}", country, codes);

                softly.assertThat(Ordering.natural().isOrdered(codes)).as("%s Zones were not sorted alphabetically", country).isEqualTo(true);
                findElement(By.xpath(EditCountryPage.CANCEL_BUTTON)).click();
            }
        });
    }

    @Test
    public void geoZonesOrder() {
        openAdmin();
        adminPageHelper.clickMenuItem(SidebarMenuEnum.Geo_Zones);

        SoftAssertions.assertSoftly(softly -> {
            // Go through countries available at the Geo Zones page
            for (int j = 0; j < findElements(GeoZonesPage.GEO_ZONES_TABLE_ROWS).size(); j++) {
                findElements(GEO_ZONES_TABLE_ROWS + NAME_COLUMN).get(j).click();
                List<String> countries = new ArrayList<>();
                // Gather Countries available at the geo zone page specific to country
                for (int i = 0; i < findElements(FORM_GEO_ZONE_TABLE_ROW).size(); i++) {
                    countries.add(findElements(FORM_GEO_ZONE_TABLE_ROW + EditGeoZonePage.COUNTRY_COLUMN).get(i).getText());
                    findElement(By.xpath(EditGeoZonePage.CANCEL_BUTTON)).click();
                }
                // compare
                logger.info("Countries found {}", countries);
                softly.assertThat(Ordering.natural().isOrdered(countries)).as("%s  Geo Zones were not sorted alphabetically", countries).isEqualTo(true);
            }
        });
    }

}
