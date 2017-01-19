package br.com.fnkgw.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesSystemTest {
	
	private WebDriver driver;
	private LeiloesPage leiloes;
	
	@BeforeClass
	public static void antesDoTest() {
		System.setProperty("webdriver.chrome.driver", "/home/developer/workspace/chromedriver");
	}
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		leiloes = new LeiloesPage(driver);
		driver.get(new URLDaAplicacao().getUrlLimpaBaseDados());
		new CriadorDeCenarios(driver).umUsuario("Paulo Henrique", "paulohenrique@gmail.com");
	}
	
	@After
	public void aposTest() {
		driver.close();
	}
	
	@Test
	public void deveCadastrarUmLeilao() {
		
		leiloes.visita();
		leiloes.novo().preenche("Geladeira", 123.0, "Paulo Henrique", true);
		
		assertTrue(leiloes.existe("Geladeira", 123.0, "Paulo Henrique", true));
	}
	
	@Test
	public void naoDeveAdicionarUmLeilaoSemNomeOuValorInicial() {
		leiloes.visita();
		NovoLeilaoPage form = leiloes.novo();
		form.preenche("", 0, "Paulo Henrique", false);
		
		assertTrue(form.validacaoDeNomeObrigatorio());
		assertTrue(form.validacaoDeValorInicialObrigatorio());
	}
}
