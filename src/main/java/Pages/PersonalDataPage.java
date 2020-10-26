package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalDataPage {
    private WebDriver driver;

    public PersonalDataPage(WebDriver driver) {
        this.driver = driver;
    }

    By addButton = By.xpath("//button[text()=\"Добавить\"]");
    By communicationTypeButton = By.xpath("//span[contains(text(),\"Способ связи\")]");
    String socialNetworkButton ="(//button[@title=\"%s\"])[last()]";
    String socialNetworkField = "//div[contains(text(),\"%s\")]/ancestor::div[@class=\"container__col container__col_12 container__col_middle\"]//input[@type=\"text\"]";
    By saveButton = By.name("continue");

    public PersonalDataPage addButtonClick(){
        driver.findElement(addButton).click();
        return this;
    }

    public PersonalDataPage communicationTypeButtonClick(){
        driver.findElement(communicationTypeButton).click();
        return this;
    }

    public PersonalDataPage socialNetworkChoose(String networkName){
        driver.findElement(By.xpath(String.format(socialNetworkButton,networkName))).click();
        return this;
    }

    public PersonalDataPage enterSocialNetworkField(String fieldName, String networkField){
        driver.findElement(By.xpath(String.format(socialNetworkField,fieldName))).sendKeys(networkField);
        return this;
    }

    public PersonalDataPage submitForm(){
        driver.findElement(saveButton).click();
        return this;
    }

    public String fieldValue(String fieldName){
        return driver.findElement(By.xpath(String.format(socialNetworkField,fieldName))).getAttribute("value");
    }

    public PersonalDataPage fieldClearing(String fieldName1,String fieldName2){
        driver.findElement(By.xpath(String.format(socialNetworkField,fieldName1))).clear();
        driver.findElement(By.xpath(String.format(socialNetworkField,fieldName2))).clear();
        return this;
    }
}
