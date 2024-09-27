package com.automation;

import com.microsoft.playwright.*;

import java.util.List;

public class DragAndDrop {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.globalsqa.com/demo-site/draganddrop/");

        FrameLocator frameLocator = page.frameLocator("iframe.demo-frame.lazyloaded");
//
//        Locator draggable = frameLocator.locator("(//li[@class='ui-widget-content ui-corner-tr ui-draggable ui-draggable-handle'])[2]");
//        Locator dropTarget = frameLocator.locator("//div[@id='trash']");
//        draggable.dragTo(dropTarget);
        List<Locator> allItems = frameLocator.locator("ul#gallery li").all(); //ul[@id='gallery']/li
        System.out.println(allItems.size());
        for (int i = 1; i <= allItems.size(); i++) {
//            String loc = "(//li[@class='ui-widget-content ui-corner-tr ui-draggable ui-draggable-handle'])[1]";
//            String xpath = String.format(loc,i);
            Locator draggable = frameLocator.locator("ul#gallery li").first();
            Locator dropTarget = frameLocator.locator("//div[@id='trash']");
            draggable.dragTo(dropTarget);
            System.out.println("dragged");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


//        frameLocator.locator("(//li[@class='ui-widget-content ui-corner-tr ui-draggable ui-draggable-handle'])[1]").hover();
//        frameLocator.mouse().down();
//        frameLocator.locator("//div[@id='trash']").hover();
//        frameLocator.mouse().up();


//        FrameLocator frame = page.frame("iframe.demo-frame.lazyloaded");


//        System.out.println("open");
//
//
//        page.("(//li[@class='ui-widget-content ui-corner-tr ui-draggable ui-draggable-handle'])[1]").hover();
//
//        frame.locator("//div[@id='trash']").hover();
//        frame.

//        page.locator("(//li[@class='ui-widget-content ui-corner-tr ui-draggable ui-draggable-handle'])[1]").dragTo(page.locator("//div[@id='trash']"));


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        page.close();
    }
}
