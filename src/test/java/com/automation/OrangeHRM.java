package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class OrangeHRM {
    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );

        BrowserContext context = browser.newContext();

        //logging-in in the first context and first page
        Page page1 = context.newPage();
        page1.navigate("https://opensource-demo.orangehrmlive.com");

        page1.locator("//input[@name = 'username']").fill("Admin");
        page1.locator("//input[@name = 'password']").fill("admin123");
        page1.locator("button").click();

        PlaywrightAssertions.assertThat(page1.locator("h6")).isVisible();

        // creating a page page2(new tab) in the same context and verifying user is logged in
        Page page2 = context.newPage();

        page2.navigate("https://opensource-demo.orangehrmlive.com");
        PlaywrightAssertions.assertThat(page1.locator("h6")).isVisible();


        // creating a new context(open new browser) and navigating and then verifying login page is dispalyed
        BrowserContext context2 = browser.newContext();
        Page page3 = context2.newPage();
        page3.navigate("https://opensource-demo.orangehrmlive.com");
        PlaywrightAssertions.assertThat(page3.locator("//input[@name = 'username']")).isVisible();

        page1.close();
        page2.close();
        page3.close();


    }
}









