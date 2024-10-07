package main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;
    HelperUser helperUser;
    HelperCar helperCar;
    public void init(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
        wd.navigate().to("https://ilcarro.web.app/");
        helperUser = new HelperUser(wd);
        helperCar = new HelperCar(wd);
    }

    public HelperCar getHelperCar() {
        return helperCar;
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop(){
//        if(wd!=null){
//            wd.quit();
//        }

    }
}
