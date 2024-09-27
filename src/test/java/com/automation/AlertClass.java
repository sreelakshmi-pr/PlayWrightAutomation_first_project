package com.automation;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Dialog;

public class AlertClass {

    public static void main(String[] args) {
        // Initialize Playwright
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();


            page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

            // Set up a single dialog handler
            page.onDialog(dialog -> {
                System.out.println("Dialog message: " + dialog.message());

                if (dialog.type().equals("alert")) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialog.accept();
                    // Accept an alert dialog
                }
                else if (dialog.type().equals("confirm")) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialog.accept();
                }
                else if (dialog.type().equals("prompt")) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    dialog.accept("Hello theree"); // Provide input text for the prompt
                }
            });


            page.locator("//button[text()='Click for JS Confirm']").click();
            page.waitForTimeout(2000);


            page.locator("//button[text()='Click for JS Alert']").click();
            page.waitForTimeout(2000);


            page.locator("//button[text()='Click for JS Prompt']").click();
            page.waitForTimeout(2000);


            browser.close();
        }
    }
}
