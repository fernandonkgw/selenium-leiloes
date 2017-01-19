package br.com.fnkgw.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAutomatizado {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "/home/developer/workspace/chromedriver");
		
		// abre firefox
		WebDriver driver = new ChromeDriver();
		
		// acessa o site do goole
		driver.get("https://www.google.com.br");
		
		// digita no campo com nome q do google
		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("caelum");
		
		// submete o form
		campoDeTexto.submit();
	}
}
