package br.com.fnkgw.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsuariosPage {

	private WebDriver driver;

	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get(new URLDaAplicacao().getUrlBase()+"/usuarios");
	}
	
	public NovoUsuarioPage novo() {
		driver.findElement(By.linkText("Novo Usuário")).click();
		return new NovoUsuarioPage(driver);
	}
	
	public boolean existeNaListagem(String nome, String email) {
		
		return driver.getPageSource().contains(nome) && 
				driver.getPageSource().contains(email);
	}

	public AlteraUsuarioPage altera(int posicao) {
		driver.findElements(By.linkText("editar")).get(posicao-1).click();
        return new AlteraUsuarioPage(driver);
	}

	public void excluirUltimo() {
		int posicao = driver.findElements(By.tagName("button")).size();
		excluir(posicao);
	}
	
	public void excluir(int posicao) {
		driver.findElements(By.tagName("button")).get(posicao-1).click();
		// pega o alert que está aberto
		Alert alert = driver.switchTo().alert();
		// confirma
		alert.accept();
	}
}
