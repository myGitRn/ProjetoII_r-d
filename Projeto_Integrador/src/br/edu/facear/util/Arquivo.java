package br.edu.facear.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	//Atributos
	private String diretorio;
	private String nome;
	private String texto;
	private boolean acrescentar;
	
	public boolean isAcrescentar() {
		return acrescentar;
	}
	public void setAcrescentar(boolean acrescentar) {
		this.acrescentar = acrescentar;
	}
	//Get e Set
	public String getDiretorio(){
		return this.diretorio;
	}
	public void setDiretorio(String diretorio){
		this.diretorio = diretorio;
	}
	public String getNome(){
		return this.nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}	
	public String getTexto(){
		return this.texto;
	}
	public void setTexto(String texto){
		this.texto = texto;
	}	
	//Construtor
	public Arquivo(){
		this.diretorio = "C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador";
		this.acrescentar = true;
	}
	public Arquivo(String dir, String nome, String texto, boolean acrescentar){
		this.diretorio = dir;
		this.nome = nome;
		this.texto = texto;
		this.acrescentar = acrescentar;
	}	
	
	public boolean Gravar(){
		
		File dir = new File(this.diretorio);		
		File arq = new File(dir, this.nome);
		
		try{
			
			arq.createNewFile();			
			
			FileWriter fw = new FileWriter(arq, acrescentar);
			
			PrintWriter pw = new PrintWriter(fw);
			
			pw.println(this.texto);
			
			pw.flush();
			pw.close();
			
		}
		catch (Exception e){
			System.out.println("Erro ao Gravar o arquivo: "+e.getMessage());
		}
		
		return true;
	}
	public List<String> Ler(){
		List<String> lista = new ArrayList<String>();

		File dir = new File(this.diretorio);		
		File arq = new File(dir, this.nome);
		try{
			String linha;
			
			FileReader fr = new FileReader(arq);
			
			BufferedReader br = new BufferedReader(fr);
			while ( (linha = br.readLine()) != null ){
				lista.add(linha);
			}
			
		br.close();
			
		}
		catch (Exception e){
			System.out.println("Erro ao Ler o arquivo: "+e.getMessage());
		}		
		
		return lista;
	
	}	

}
