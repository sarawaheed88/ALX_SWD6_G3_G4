import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "F:\\driver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            driver.get("https://demoblaze.com/index.html");
            driver.manage().window().maximize();


            WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Samsung galaxy s6")));
            productLink.click();


            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));
            addToCartBtn.click();


            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept(); // الضغط OK على الـ Alert


            WebElement cartLink = driver.findElement(By.id("cartur"));
            cartLink.click();


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Samsung galaxy s6']")));

            System.out.println("✅Add to cart ");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

          driver.quit();
        }
    }
    }
