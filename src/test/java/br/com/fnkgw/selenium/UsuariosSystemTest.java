package br.com.fnkgw.selenium;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UsuariosSystemTest {

	private WebDriver driver;
	private UsuariosPage usuarios;
	
	@BeforeClass
	public static void antesDoTest() {
		System.setProperty("webdriver.chrome.driver", "/home/developer/workspace/chromedriver");
	}
	
	@Before
	public void antesDeCadaTest() {
		driver = new ChromeDriver();
		usuarios = new UsuariosPage(driver);
		driver.get(new URLDaAplicacao().getUrlLimpaBaseDados());
	}

	@After
	public void depoisDeCadaTest() {
		driver.close();
	}
	
	@Test
	public void deveAdicionarUmUsuario() {
		usuarios.visita();
		usuarios.novo().cadastra("Adriano Xavier", "axavier@empresa.com.br");
		
		assertTrue(usuarios.existeNaListagem("Adriano Xavier", "axavier@empresa.com.br"));
	}
	
	@Test
	public void deveExibirMensagemDeErroAoInserirUsuarioSemNome() {
		usuarios.visita();
		NovoUsuarioPage form = usuarios.novo();
		form.cadastra("", "usuario@empresa.com.br");
		
		assertTrue(form.validacaoDeNomeObrigatorio());
	}
	
	@Test
	public void naoDeveAdicionarUmUsuarioSemNomeOuEmail() {
		usuarios.visita();
		NovoUsuarioPage form = usuarios.novo();
		form.cadastra("", "");
		
		assertTrue(form.validacaoDeNomeObrigatorio());
		assertTrue(form.validacaoDeEmailObrigatorio());
	}
	
	@Test
	public void deveIrParaPaginaNovoUsuario() {
		usuarios.visita();
		NovoUsuarioPage form = usuarios.novo();
		
		assertTrue(form.possuiCampoNome());
	}
	
	@Test
	public void deveExcluirUmUsuario() {
		usuarios.visita();
		usuarios.novo()
			.cadastra("Adriano", "adriano@empresa.com.br");
		
		usuarios.excluirUltimo();
		assertFalse(usuarios.existeNaListagem("Adriano", "adriano@empresa.com.br"));
	}
	
	@Test
	public void deveAlterarUmUsuario() {
		usuarios.visita();
		usuarios.novo()
			.cadastra("Adriano", "adriano@empresa.com.br");
		
		usuarios.altera(1).para("Fernando", "fernando@empresa.com.br");
		assertFalse(usuarios.existeNaListagem("Adriano", "adriano@empresa.com.br"));
		assertTrue(usuarios.existeNaListagem("Fernando", "fernando@empresa.com.br"));
	}
}
