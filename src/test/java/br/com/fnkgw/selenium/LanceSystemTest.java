package br.com.fnkgw.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LanceSystemTest {

	@BeforeClass
	public static void antesDoTest() {
		System.setProperty("webdriver.chrome.driver", "/home/developer/workspace/chromedriver");
	}

	private WebDriver driver;
	private LeiloesPage leiloes;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.get(new URLDaAplicacao().getUrlLimpaBaseDados());
		
		new CriadorDeCenarios(driver)
			.umUsuario("Paulo Henrique", "paulo@henrique.com.br")
			.umUsuario("Adriano Almeida", "adriano@almeida.com.br")
			.umLeilao("Paulo Henrique", "Geladeira", 1000, true);
		leiloes = new LeiloesPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	
	@Test
	public void deveFazerUmLance() {
		DetalhesDoLeilaoPage lances = leiloes.detalhes(1);
		
		lances.lance("Adriano Almeida", 1001.0);
		
		assertTrue(lances.existeLance("Adriano Almeida", 1001.0));
	}
}
