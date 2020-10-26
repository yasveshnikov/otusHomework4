import Pages.HomePage;
import Pages.LearningPage;
import Pages.PersonalDataPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;



public class PageObjectSimpleTest {
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(PageObjectSimpleTest.class);
    private static HomePage homePage;
    private static LearningPage learningPage;
    private static PersonalDataPage personalDataPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage=new HomePage(driver);
        logger.info("Драйвер поднят");
    }

    @Test
    public void personalDataFieldTest()  {
        String firstSocialNetworkName="VK";
        String secondSocialNetworkName="Facebook";
        String firstSocialNetworkField="https://vk.com/otusru";
        String secondSocialNetworkField="https://www.facebook.com/otusru/";
        driver.get("https://otus.ru");
        logger.info("Сайт OTUS открыт");
        logIn();
        logger.info("Авторизация");
        personalDataPageOpen();
        logger.info("Личный кабинет открыт");
        socialNetworkAdd(firstSocialNetworkName,firstSocialNetworkField);
        socialNetworkAdd(secondSocialNetworkName,secondSocialNetworkField);
        logger.info("Раздел о себе заполнен");
        personalDataPage.submitForm();
        logger.info("Кнопка Сохранить и заполнить позже нажата");
        driver.manage().deleteAllCookies();
        logger.info("Удаляем куки");
        driver.get("https://otus.ru");
        logger.info("Открываем сайт OTUS в чистом браузере");
        logIn();
        logger.info("Повторная авторизация");
        personalDataPageOpen();
        logger.info("Личный кабинет повторно открыт");
        fieldEquals(firstSocialNetworkField,firstSocialNetworkName);
        fieldEquals(secondSocialNetworkField,secondSocialNetworkName);
        logger.info("Проверен раздел О себе");
        fieldClearing(firstSocialNetworkName, secondSocialNetworkName);
        logger.info("Чистим заполненные поля");
    }

    public static void logIn(){
        homePage.signInButtonClick();
        homePage.enterMailField("jejado6391@nalafx.com");
        homePage.enterPasswordField("qwer1234");
        homePage.loginButtonClick();
    }

    public static void personalDataPageOpen() {
        homePage.hooverAvatarMenu();
        learningPage=homePage.myCabinetButtonClick();
        personalDataPage=learningPage.personalDataClick();
    }

    public static void socialNetworkAdd(String socialNetworkName, String socialNetworkField) {
        personalDataPage.communicationTypeButtonClick();
        personalDataPage.socialNetworkChoose(socialNetworkName);
        personalDataPage.enterSocialNetworkField(socialNetworkName,socialNetworkField);
        personalDataPage.addButtonClick();
    }

    public static void fieldEquals(String socalNetworkField, String socialNetworkName){
        Assert.assertEquals(socalNetworkField,personalDataPage.fieldValue(socialNetworkName));
    }

    public static void fieldClearing(String firstSocialNetworkName, String secondSocialNetworkName){
        personalDataPage.fieldClearing(firstSocialNetworkName,secondSocialNetworkName);
        personalDataPage.submitForm();
    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}