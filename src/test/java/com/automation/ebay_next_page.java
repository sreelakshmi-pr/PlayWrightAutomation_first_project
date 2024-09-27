package com.automation;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ebay_next_page {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        Page page = browser.newPage();
        page.navigate("https://www.ebay.com");


        page.locator("#gh-ac").fill("TV");
        page.locator("#gh-btn").click();

        Page newPage = page.waitForPopup(() -> {
            page.locator("//a[@class='s-item__link'][not(@tabindex)]").first().click();
        });

        System.out.println(newPage.locator("div.x-price-primary span").textContent());
        newPage.close();

        page.bringToFront();
//        page.locator("#gh-ac");
        page.locator("#gh-ac").fill("Laptop");
        page.locator("#gh-btn").click();
        Thread.sleep(3000);

        playwright.close();
    }
}
