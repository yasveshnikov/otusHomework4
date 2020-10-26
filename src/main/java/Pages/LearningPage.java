package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LearningPage {
    private WebDriver driver;

    public LearningPage(WebDriver driver) {
        this.driver = driver;
    }

    By personalDataButton = By.xpath("(//div[@class=\"nav__items\"]//a[@title=\"О себе\"])[1]");

    public PersonalDataPage personalDataClick(){
        driver.findElement(personalDataButton).click();
        return new PersonalDataPage(driver);
    }
}
