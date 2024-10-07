package tests;

import dto.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = User.builder()
                .name("Al"+i)
                .lastName("Lomov"+i)
                .email("locker"+i+"@gmail.com")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"You are logged in success");
    }
    @Test
    public void registrationWrongName(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = User.builder()
                .name("")
                .lastName("Lomov"+i)
                .email("locker"+i+"@gmail.com")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorMessage(),"Name is required");
    }
    @Test
    public void registrationWrongLastName(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = User.builder()
                .name("Al"+i)
                .lastName("")
                .email("locker"+i+"@gmail.com")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorMessage(),"Last name is required");
    }
    @Test
    public void registrationWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = User.builder()
                .name("Al"+i)
                .lastName("Lomov"+i)
                .email("locker@gmail")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorMessage(),"Wrong email format");
    }
    @Test
    public void registrationWrongEmailSecond(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = User.builder()
                .name("Al"+i)
                .lastName("Lomov"+i)
                .email("lockergmail.com")
                .password("Qwerty1234!")
                .build();
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().getErrorMessage().contains("Wrong email format"));
    }
    @Test
    public void registrationWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        User user = User.builder()
                .name("Al"+i)
                .lastName("sev")
                .email("locker"+i+"@gmail.com")
                .password("Ssnow123")
                .build();//s
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicyXY();
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorMessage(),"Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().clickOkButton();
    }
}
