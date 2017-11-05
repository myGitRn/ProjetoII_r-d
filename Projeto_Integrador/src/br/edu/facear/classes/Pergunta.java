package br.edu.facear.classes;

import java.util.ArrayList;
import java.util.List;

import br.edu.facear.util.Arquivo;

public class Pergunta {

	private int id;
	private int avaliacao;
	private String pergunta;
	private String correta;
	private String alternativas1;
	private String alternativas2;
	private String alternativas3;
	private String autor;
	private Categoria categoria;
	private int perguntasFeitas;

	public Pergunta() {

	}
	

	public int getPerguntasFeitas() {
		return perguntasFeitas;
	}

	public void setPerguntasFeitas(int perguntasFeitas) {
		this.perguntasFeitas = perguntasFeitas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getAlternativas1() {
		return alternativas1;
	}

	public void setAlternativas1(String alternativas1) {
		this.alternativas1 = alternativas1;
	}

	public String getAlternativas2() {
		return alternativas2;
	}

	public void setAlternativas2(String alternativas2) {
		this.alternativas2 = alternativas2;
	}

	public String getAlternativas3() {
		return alternativas3;
	}

	public void setAlternativas3(String alternativas3) {
		this.alternativas3 = alternativas3;
	}

	public String getCorreta() {
		return correta;
	}

	public void setCorreta(String correta) {
		this.correta = correta;
	}

	public String getAutor() {
		return autor;

	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void AvaliarPerguntas() {

		System.out.println("Pergunta avaliada com sucesso!");

	}

	public void SortearPerguntas() {

	}

	public String ValidarPergunta(String resposta) {

		String ret = null;
		List<Pergunta> listaObjectPer = this.Ler();
		for (Pergunta per : listaObjectPer) {
			if (per.correta.equals(resposta)) {
				ret = "OK";
				break;
			} else
				ret = "ERRO";
		}
		return ret;

	}

	public Pergunta buscarPerguntas(int ID) {
		Pergunta perguntaRetorno = null;
		List<Pergunta> listaObjectPerg = this.Ler();
		for (Pergunta per : listaObjectPerg) {
			if (per.getId() == ID)
				perguntaRetorno = per;
		}
		return perguntaRetorno;

	}

	public List<Pergunta> Ler() {
		// lista para retirnar do m�todo
		List<Pergunta> listaRetorno = new ArrayList<Pergunta>();

		// recuperar a lista de strings do txt
		Arquivo arq = new Arquivo();
		arq.setNome("Pergunta.txt");
		List<String> lista = arq.Ler();

		// percorrer a lista de strings
		for (String linha : lista) {
			// para cada linha
			String[] vetdados = linha.split(";");

			// -> adicionar em um objeto
			Pergunta pergunta = new Pergunta();
			int id = Integer.parseInt(vetdados[0]); // Id
			pergunta.setId(id);
			Categoria categoria = Categoria.valueOf(vetdados[1]);
			pergunta.setCategoria(categoria);
			pergunta.setPergunta(vetdados[2]);
			pergunta.setCorreta(vetdados[3]); // Nome
			pergunta.setAlternativas1(vetdados[4]);
			pergunta.setAlternativas2(vetdados[5]);
			pergunta.setAlternativas3(vetdados[6]);
			pergunta.setAutor(vetdados[7]);

			// -> adicionar na lista de retorno
			listaRetorno.add(pergunta);

		}
		return listaRetorno;

	}
	public String CadastrarPergunta() {
		String ret = null;
		List<Pergunta> listaObjectPer = this.Ler();
		for (Pergunta pergunta : listaObjectPer) {
			if (pergunta.id == pergunta.id)
				id++;
		}

		try {
			String linha = this.id + ";" + this.categoria + ";" + this.pergunta + ";" + this.correta + ";"
					+ this.alternativas1 + ";" + this.alternativas2 + ";" + this.alternativas3 + ";" + this.autor;
			Arquivo arq = new Arquivo();
			arq.setNome("Pergunta.txt");
			
			arq.setTexto(linha);
			arq.Gravar();
			ret = "OK";
		} catch (Exception e1) {
			ret = "ERRO";
		}

		return ret;

	}
	
	public String CadastrarPerguntaFeita() {
		String ret = null;
		try {
			String linha = this.perguntasFeitas + ";";
			Arquivo arq = new Arquivo();
			arq.setNome("PerguntasFeitas.txt");
			
			arq.setTexto(linha);
			arq.Gravar();
			ret = "OK";
		} catch (Exception e1) {
			ret = "ERRO";
		}

		return ret;

	}
	
}
