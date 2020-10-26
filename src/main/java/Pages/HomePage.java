package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By signInButton =By.cssSelector("button[data-modal-id=new-log-reg]");
    By mailField = By.cssSelector("[method=post].new-log-reg__form input[type=text]");
    By passwordField = By.cssSelector("[method=post].new-log-reg__form input[type=password]");
    By loginButton = By.cssSelector("form[data-type=modal-form].new-log-reg__form .new-button");
    By avatarMenu = By.cssSelector(".header2__right .header2-menu__item-wrapper");
    By myCabinetButton = By.cssSelector("[title=\"Личный кабинет\"]");

    public HomePage signInButtonClick(){
        driver.findElement(signInButton).click();
        return this;
    }

    public HomePage enterMailField(String mailString){
        driver.findElement(mailField).sendKeys(mailString);
        return this;
    }

    public HomePage enterPasswordField(String passwordString){
        driver.findElement(passwordField).sendKeys(passwordString);
        return this;
    }

    public HomePage loginButtonClick(){
        driver.findElement(loginButton).click();
        return this;
    }

    public HomePage hooverAvatarMenu(){
        Actions actions=new Actions(driver);
        actions.moveToElement(driver.findElement(avatarMenu)).build().perform();
        return this;
    }

    public LearningPage myCabinetButtonClick(){
        driver.findElement(myCabinetButton).click();
        return new LearningPage(driver);
    }
}
