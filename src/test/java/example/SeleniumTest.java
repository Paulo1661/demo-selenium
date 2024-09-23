package example;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Allow all origins
        options.addArguments("--start-maximized"); // Start maximized
        options.addArguments("--disable-infobars"); // Disable info bars
        //options.addArguments("--headless"); // Run in headless mode (optional)

        driver = new ChromeDriver(options);
    }

    @Test
    public void testOpenWebsite() {
        //given
        driver.get("https://todomvc.com/examples/react/dist/");

        //then
        assertEquals("TodoMVC: React", driver.getTitle());
    }

    @Test
    public void shouldAddATaskInATheTodoList() {
        //given
        driver.get("https://todomvc.com/examples/react/dist/");
        assertEquals("TodoMVC: React", driver.getTitle());

        //When
        WebElement input = driver.findElement(By.cssSelector(".new-todo"));
        input.sendKeys("Go to the supermarket");
        input.sendKeys(Keys.ENTER);
        takeScreenshot("1");
        input.sendKeys("Read a book");
        input.sendKeys(Keys.ENTER);
        takeScreenshot("2");

        //Then
        List<String> tasks = Arrays.asList("Go to the supermarket","Read a book");

        List<WebElement> labels = driver.findElements(By.xpath("//ul//li//label"));

        assertEquals(tasks.get(0), labels.get(0).getText());
        assertEquals(tasks.get(1), labels.get(1).getText());
        takeScreenshot("3");
    }

    public void takeScreenshot(String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destFile = new File("target/screenshots/" + fileName + ".png");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
