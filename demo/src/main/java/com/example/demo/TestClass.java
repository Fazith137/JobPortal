package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import javax.sound.midi.SysexMessage;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {
    @Test
   public void testNG(){
        System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");


        WebDriver webDriver=new ChromeDriver(options);

        webDriver.get("https://www.youtube.com/");
        System.out.println(webDriver.getTitle()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        webDriver.quit();
   }
}
