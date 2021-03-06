package br.com.fnkgw.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoUsuarioPage {

	private WebDriver driver;

	public NovoUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void cadastra(String nome, String email) {
		WebElement txtNome = driver.findElement(By.name("usuario.nome"));
		WebElement txtEmail = driver.findElement(By.name("usuario.email"));
		
		txtNome.sendKeys(nome);
		txtEmail.sendKeys(email);
		
		txtNome.submit();
	}
	
	public boolean validacaoDeNomeObrigatorio() {
		return driver.getPageSource().contains("Nome obrigatorio!");
	}
	
	public boolean validacaoDeEmailObrigatorio() {
		return driver.getPageSource().contains("E-mail obrigatorio!");
	}
	
	private boolean possuiCampo(String campo) {
		return driver.getPageSource().contains(campo);
	}
	
	public boolean possuiCampoNome() {
		return possuiCampo("usuario.nome");
	}
}
