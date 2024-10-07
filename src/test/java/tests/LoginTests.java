package tests;


import dto.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test
    public void loginPositiveTest(){
        User user = User.builder()
                .email("locker@gmail.com")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }
    @Test
    public void loginWrongEmail(){
        User user = User.builder()
                .email("lockergmail.com")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorMessage(),"It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());

    }
    @Test
    public void loginWrongPassword(){
        User user = User.builder()
                .email("locker@gmail.com")
                .password("qwe")
                .build();
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

    }
    @Test
    public void loginUnregisteredUser(){
        User user = User.builder()
                .email("l1234ocker@gmail.com")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
