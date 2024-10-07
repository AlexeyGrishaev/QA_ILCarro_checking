package main;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {
    WebDriver wd;
    By dialogContainer = By.xpath("//div[@class='dialog-container']/h2");
    By btnSubmitLogin = By.xpath("//button[@type='submit']");
    By errorMessage = By.cssSelector("div.error");
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public WebElement findElementBase(By locator) {
        return wd.findElement(locator);
    }
    public String getErrorMessage() {
        return wd.findElement(errorMessage).getText();
    }
    public void clickBase(By locator){
        findElementBase(locator).click();
    }
    public void clickBaseWait(By locator, int time) {
        new WebDriverWait(wd, time).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void typeBase(By locator,String text){
        WebElement element = findElementBase(locator);
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL+"a"));
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);

    }
    public String getMessage() {
        pause(1);
        return findElementBase(dialogContainer).getText();
    }
    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;
    }
    public void submit() {
        clickBase(btnSubmitLogin);
    }
    public void pause(int time){
        try {
            Thread.sleep(1000L * time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
