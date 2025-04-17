package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "F:\\driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 🛒 المنتجات اللي هنضيفها
        List<String> products = Arrays.asList("Samsung galaxy s6", "Nokia lumia 1520", "Sony xperia z5");

        try {
            driver.get("https://demoblaze.com/index.html");
            driver.manage().window().maximize();

            // ➕ إضافة كل منتج
            for (String product : products) {
                // الرجوع للصفحة الرئيسية
                driver.get("https://demoblaze.com/index.html");
                // الانتظار وفتح صفحة المنتج
                WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(product)));
                productLink.click();

                // الضغط على "Add to cart"
                WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));
                addToCartBtn.click();

                // تأكيد التنبيه
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();

                // تأخير بسيط لتفادي الحماية
                Thread.sleep(1000);
            }


            WebElement cartLink = driver.findElement(By.id("cartur"));
            cartLink.click();


            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Samsung galaxy s6']")));

            String productToDelete = "Nokia lumia 1520";
            WebElement deleteBtn = driver.findElement(By.xpath("//td[text()='" + productToDelete + "']/following-sibling::td/a[text()='Delete']"));
            deleteBtn.click();


            Thread.sleep(3000);

            // ✅ طباعة المنتجات المتبقية
            List<WebElement> remainingProducts = driver.findElements(By.xpath("//tr/td[2]"));
            System.out.println("📦 المنتجات المتبقية في السلة:");
            for (WebElement p : remainingProducts) {
                System.out.println("- " + p.getText());
            }
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();

        }

        finally {
             driver.quit();
        }
    }

    }
