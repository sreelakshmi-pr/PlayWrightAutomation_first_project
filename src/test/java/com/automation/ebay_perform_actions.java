package com.automation;

import com.microsoft.playwright.*;

import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ebay_perform_actions {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();

        page.navigate(" https://www.ebay.com/");

        page.getByLabel("Search for anything").fill("Laptop");

        page.getByLabel("Select a category for search").selectOption(new SelectOption().setValue("58058"));

        page.locator("#gh-btn").click();

        Locator advancedSearch = page.getByLabel("Advanced Search");

        advancedSearch.click();

        Locator keywordField = page.getByTestId("_nkw");

        keywordField.fill("ipod nano 4th generation");



        Thread.sleep(2000);

        Locator excludeWords = page.getByTestId("_ex_kw");

        excludeWords.fill("generation");

        Thread.sleep(2000);



        excludeWords = page.getByTestId("_ex_kw");

        excludeWords.dblclick();

        Thread.sleep(2000);

        excludeWords.fill("4th generation");


        //titleAndDescriptionCheckBox

        page.getByTestId("s0-1-17-5[1]-[0]-LH_TitleDesc").check();

        assertThat(page.getByTestId("s0-1-17-5[1]-[0]-LH_TitleDesc")).isChecked();

        Locator minPrice = page.getByLabel("Enter minimum price range value, $");

        minPrice.fill("199");
//
//        Thread.sleep(2000);
//
//        Locator maxPrice= page.getByLabel("Enter maximum price range value, $");

//        maxPrice.fill("699");

//        String min = "199";

//        minPrice.type(min, new Locator.TypeOptions().setDelay(300));
//
        page.locator("xpath = //input[@aria-label='Enter maximum price range value, $']").pressSequentially("699", new Locator.PressSequentiallyOptions().setDelay(400));

//        playwright.close();

    }

}

