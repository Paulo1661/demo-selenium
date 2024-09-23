package example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
