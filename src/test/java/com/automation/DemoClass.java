package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.awt.print.Pageable;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DemoClass {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.saucedemo.com");
//        BrowserContext context=browser.newContext(); //for login incoginito tab
//        Page page = context.newPage();
//        page.navigate("https://www.saucedemo.com");
//        page.waitForLoadState(LoadState.LOAD);

        Locator userName = page.locator("#user-name"); //page.getByPlaceholder("")
        Locator password = page.locator("#password");
        Locator loginBtn = page.locator("#login-button");

        userName.fill("standard_user");
        password.fill("secret_sauce");
        page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true)
                .setMask(Arrays.asList(userName))
                .setPath(Paths.get("src/test/resources/image2.png")));
        //take the screenshot of only one locator
        userName.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("src/test/resources/image3.png")));
        loginBtn.click();

        assertThat(page.locator("span.title")).hasText("Products");

        //print product names
        List<Locator> listOfProducts = page.locator("//div[@class='inventory_item_name ']").all();
        for (Locator locator : listOfProducts) {
            System.out.println(locator.allInnerTexts());
        }


        Locator addToCartButton = page.locator("#add-to-cart-sauce-labs-backpack");
        addToCartButton.click();
        Locator shopCartItemSymbol = page.locator("a.shopping_cart_link");
        shopCartItemSymbol.click();
        assertThat(page.locator("div.cart_quantity")).hasText("1");
        Locator checkOutButton = page.locator("#checkout");
        checkOutButton.click();

        assertThat(page.locator("#continue")).hasText("Continue");

        Locator firstNameInput = page.locator("#first-name");
        Locator lastNameInput = page.locator("#last-name");
        Locator zipPostalCode = page.locator("#postal-code");

        firstNameInput.fill("na peru");
        lastNameInput.fill("uu nagendra ..!!!");
        zipPostalCode.fill("naku teliyadhu ");
        page.locator("#continue").click();

        assertThat(page.locator("#finish")).hasText("Finish");
        Locator finishButton = page.locator("#finish");
        finishButton.click();

        assertThat(page.locator("h2.complete-header")).hasText("Thank you for your order!");



        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        //for taking screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true)
                .setPath(Paths.get("src/test/resources/image1.png")));
        playwright.close();
        System.out.println("execution finished and attached screenshot");
    }
    }

