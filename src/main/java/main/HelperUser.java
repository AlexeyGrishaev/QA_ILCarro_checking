package main;

import dto.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    By btnLogin = By.xpath("//a[@ng-reflect-router-link='login']");
    By inputEmail = By.xpath("//input[@id='email']");
    By inputPassword = By.xpath("//input[@id='password']");


    By btnOk = By.xpath("//button[text()='Ok']");
    //" Logout "
    By btnLogout = By.xpath("//a[text()=' Logout ']");


    By btnSignIn = By.xpath("//a[@ng-reflect-router-link='registration']");
    //id="name"
    By inputNameReg = By.xpath("//input[@id='name']");
    By inputLastNameReg = By.xpath("//input[@id='lastName']");
    By inputEmailReg = By.xpath("//input[@id='email']");
    By inputPasswordReg = By.xpath("//input[@id='password']");
    By labelTermsOfUse = By.xpath("//label[@for='terms-of-use']");

    public void openLoginForm() {
        clickBaseWait(btnLogin, 5);
    }

    public void fillLoginForm(User user) {
        typeBase(inputEmail, user.getEmail());
        typeBase(inputPassword, user.getPassword());
    }


    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.xpath("//button[@disabled]"));
        WebElement element = wd.findElement(By.xpath("//button[@type='submit']"));

        return res && !element.isEnabled();
    }

    public void clickOkButton() {
        pause(1);
        if (isElementPresent(btnOk)) {
            clickBaseWait(btnOk, 5);
        }
    }





    public boolean isLogged() {
        return isElementPresent(btnLogout);
    }

    public void logout() {
        clickBaseWait(btnLogout, 5);
    }

    public void openRegistrationForm() {
        clickBaseWait(btnSignIn, 5);
    }

    public void fillRegistrationForm(User user) {
        typeBase(inputNameReg, user.getName());
        typeBase(inputLastNameReg, user.getLastName());
        typeBase(inputEmailReg, user.getEmail());
        typeBase(inputPasswordReg, user.getPassword());
    }

    public void checkPolicy() {
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click()");
    }

    //labelTermsOfUse

    public void checkPolicyXY() {
        WebElement policy = wd.findElement(By.id("terms-of-use"));
        if (!policy.isSelected()) {
            WebElement element = wd.findElement(By.xpath("//label[@for='terms-of-use']"));
            Rectangle rectangle = element.getRect();

            int width = rectangle.getWidth();
            int xOffSet = -width / 2;
            Actions actions = new Actions(wd);
            actions.moveToElement(element, xOffSet, 0).click().release().perform();
        }
    }

    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submit();
        clickOkButton();
    }
}
