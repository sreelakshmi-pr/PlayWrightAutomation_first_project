package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.junit.jupiter.api.Test;


import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ebay {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();
        page.navigate("https://www.ebay.com");


        assertThat(page.locator("#gh-btn")).hasText("Search");

        page.locator("#gh-ac").fill("TV");
        page.locator("#gh-btn").click();

        Page newPage = page.waitForPopup(() -> {
            page.locator("//a[@class='s-item__link'][not(@tabindex)]").first().click();
        });

        System.out.println(newPage.locator("div.x-price-primary span").textContent());
        newPage.close();

        page.bringToFront();

        Locator advanced=page.locator("#gh-as-a");
        advanced.click();

        Locator keyWord=page.locator("#_nkw");
        keyWord.fill("java");

        Locator category=page.locator("//span[@class=\'select\']//select[@name=\'_sacat\']");
        category.selectOption("Books & Magazines");

        Locator minPrice=page.locator("//*[@class='ui-range__entry']//*[@name='_udlo']");
        minPrice.fill("0");

        Locator maxPrice=page.locator("//*[@class='ui-range__entry']//*[@name='_udhi']");
        maxPrice.fill("100");

        Locator availableTo=page.locator("//*[@aria-label='Available to ']");
        availableTo.selectOption("India");

        page.getByLabel("New").check();

        Locator search=page.locator("//*[@class='adv-form__actions']//*[text()='Search']");
        search.click();

        // Locate the element
        Locator element = page.locator(".srp-controls__count-heading");
        // Assert that the element contains the expected text
        assertThat(element).containsText("java");

        List<Locator> listOfProducts = page.locator(".s-item__title").all();
        for (Locator locator : listOfProducts) {
            System.out.println(locator.allInnerTexts());
        }

        playwright.close();
        System.out.println("execution finished and attached screenshot");

    }
}
