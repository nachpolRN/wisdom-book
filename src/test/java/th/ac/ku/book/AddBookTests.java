//Nachpol Ruangnam 6410406568
package th.ac.ku.book;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddBookTests {
    @LocalServerPort
    private Integer port;
    private static WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(id = "nameInput")
    private WebElement nameField;
    @FindBy(id = "authorInput")
    private WebElement authorField;
    @FindBy(id = "priceInput")
    private WebElement priceField;
    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }
    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/book/add");
        PageFactory.initElements(driver, this);
    }
    @Test
    void testAddBook() {
//        WebElement nameField = wait.until(webDriver ->
//                webDriver.findElement(By.id("nameInput")));
//        WebElement authorField = driver.findElement(By.id("authorInput"));
//        WebElement priceField = driver.findElement(By.id("priceInput"));
//        WebElement submitButton = driver.findElement(By.id("submitButton"));
        nameField.sendKeys("A-Z");
        authorField.sendKeys("Boss Baby");
        priceField.sendKeys("999");
        submitButton.click();
        WebElement name = wait.until(webDriver -> webDriver
                .findElement(By.xpath("//table/tbody/tr[1]/td[1]")));
        WebElement author = driver
                .findElement(By.xpath("//table/tbody/tr[1]/td[2]"));
        WebElement price = driver
                .findElement(By.xpath("//table/tbody/tr[1]/td[3]"));
        assertEquals("A-Z", name.getText());
        assertEquals("Boss Baby", author.getText());
        assertEquals("999.00", price.getText());
    }

    @AfterEach
    public void afterEach() throws InterruptedException {
        Thread.sleep(3000);
    }
    @AfterAll
    public static void afterAll() {
        if (driver != null)
            driver.quit();
    }
}
