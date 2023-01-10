package com.test.spring.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest {

    private static WebDriver driver;

    private static final By REGISTER = By.xpath("//a[contains(.,'Register')]");

    private static final By EMAIL = By.xpath("//input[@name='email']");
    private static final By PASSWORD = By.xpath("//input[@name='password']");
    private static final By FIRST_NAME = By.xpath("//input[@name='firstName']");
    private static final By LAST_NAME = By.xpath("//input[@name='lastName']");
    private static final By SUBMIT = By.xpath("//button[@type='submit']");
    private static final By TEXT = By.xpath("//div[@class='container text-center']/h3");

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testSimpleVerifyTitle() {
        driver.get("http://localhost:8080/");

        String title = driver.getTitle();
        assertThat(title).contains("Testing page.");
    }

    @Test
    public void testSimpleVerifyList() {
        driver.get("http://localhost:8080/");
        String title = driver.getTitle();
        assertThat(title).contains("Testing page.");
        List<String> webElementList = driver
                .findElements(By.xpath("//div[@class='container text-center']/h3"))
                .stream().map(WebElement::getText).toList();

        assertThat(webElementList).isEqualTo(List.of("The users list.", "Register", "Login"));
    }

    @Test
    public void testSimpleRegisterNewUser() {
        driver.get("http://localhost:8080/");
        driver.findElement(REGISTER).click();

        final String randomData = createDataForTesting();

        driver.findElement(EMAIL).sendKeys(randomData);
        driver.findElement(PASSWORD).sendKeys(randomData);
        driver.findElement(FIRST_NAME).sendKeys(randomData);
        driver.findElement(LAST_NAME).sendKeys(randomData);
        driver.findElement(SUBMIT).click();
        String SUCCESS = driver.findElement(TEXT).getText();

        assertThat(SUCCESS).isEqualTo("You have signed up successfully!");
    }

    @AfterAll
    public static void offAll() {
        driver.quit();
    }

    private String createDataForTesting() {
        return "test_" + new Date().getTime() + "@qa.team";
    }
}
