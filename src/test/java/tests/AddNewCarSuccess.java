package tests;

import dto.CarDTO;
import dto.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewCarSuccess extends TestBase{
    @BeforeClass
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(User.builder().email("locker@gmail.com").password("Qwerty1234!").build());
        }
    }

    @Test
    public void addNewCarSuccess(){
        int i = (int)(System.currentTimeMillis()/1000%3600);
        CarDTO car = CarDTO.builder()
                .location("Tel Aviv, Israel")
                .manufacture("Mazda")
                .model("M3")
                .year("2023")
                .fuel("Petrol")
                .seats(4)
                .carClass("C")
                .carRegNumber("678-900-"+i)
                .price(50)
                .about("Very Nice Car")
                .build();
    }
}
