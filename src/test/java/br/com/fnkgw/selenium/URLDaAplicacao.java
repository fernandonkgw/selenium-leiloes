package br.com.fnkgw.selenium;

public class URLDaAplicacao {

	public String getUrlBase() {
		return "http://localhost:8080";
	}
	
	public String getUrlLimpaBaseDados() {
		return getUrlBase() + "/apenas-teste/limpa";
	}
}
