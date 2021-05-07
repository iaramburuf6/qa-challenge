package com.challenge.qa.WebFEAutomation.driver;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;

import com.challenge.qa.WebFEAutomation.properties.PropertiesByProfile;


public class SeleniumDriver {

	/**
     * Test destination url
     */
    private static final String targetUrl;
    
    /**
     * Flag to determinate if the test execution is headless or not
     */
    private static final Boolean headless;
    /**
     * Browser used in tests
     */
    private static final String browser;

    /**
     * Driver for current browser
     */
    private static WebDriver currentDriver;
    
    static {
        try {
            Properties properties = new PropertiesByProfile("application");
            targetUrl = properties.getProperty("target-url");
            headless = BooleanUtils.toBoolean(properties.getProperty("selenium.headless"));
            browser = properties.getProperty("selenium.browser");


        } catch (IOException exception) {
            throw new RuntimeException("Selenium driver configuration file not found");
        }
    }
    
    public static WebDriver getCurrentDriver() {
        if (currentDriver == null) {
            throw new NullPointerException("Variable [currentDriver] is null: Call createDriver method before this");
        }
        return currentDriver;
    }

    public static void createDefaultDriver() {
        if (currentDriver == null) {
            createDriver(browser);
        }
        getCurrentDriver();
    }

    public static void createDriver(String browser) {
        if (currentDriver != null) {
            close();
        }

        if (browser == null || BrowserType.CHROME.equalsIgnoreCase(browser)) {
            currentDriver = createChromeDriver();
        } else {
            throw new IllegalArgumentException(String.format("Given browser name [%s] is not supported", browser));
        }

    }

    public static void reset() {
        if (currentDriver == null) {
            throw new NullPointerException("Variable [currentDriver] is null: Call createDriver method before this");
        }

        Class<? extends WebDriver> driverClass = currentDriver.getClass();

        close();

        if (driverClass.equals(ChromeDriver.class)) {
            currentDriver = createChromeDriver();
        } else {
            throw new IllegalArgumentException(
                    String.format("Given browser class [%s] is not supported", driverClass.getName()));
        }
    }

    public static void close() {
        if (currentDriver != null) {
            currentDriver.quit();
            currentDriver = null;
        }
    }

    public static void gotoTargetURL() {
        currentDriver.get(targetUrl);

    }
    
    public static String getBrowser() {
        return browser;
    }
    
    public static WebDriver createChromeDriver() {
    	LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(headless);
        
        WebDriver driver = new ChromeDriver(options);
        
        return driver;
    }
	
}
