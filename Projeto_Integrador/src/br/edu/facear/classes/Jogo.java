package br.edu.facear.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

import br.edu.facear.util.Arquivo;

public class Jogo {
	private int id;
	private float tempo;
	private int vez;
	private Jogador jogador1, jogador2, jogadorVez;
	private Pergunta pergunta;
	private List<String> perguntasFeitas;
	private List<Integer> perguntaFeitas1;

	public Jogo() {
		
		Novo();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public List<Integer> getPerguntaFeitas1() {
		return perguntaFeitas1;
	}

	public void setPerguntaFeitas1(List<Integer> perguntaFeitas1) {
		this.perguntaFeitas1 = perguntaFeitas1;
	}


	public List<String> getPerguntasFeitas() {
		return perguntasFeitas;
	}

	public void setPerguntasFeitas(List<String> perguntasFeitas) {
		this.perguntasFeitas = perguntasFeitas;
	}

	public Jogador getJogadorVez() {
		return jogadorVez;
	}
	
	public void setJogadorVez(Jogador jogadorVez) {
		this.jogadorVez = jogadorVez;
	
	}

	public float getTempo() {
		return tempo;
	}

	public void setTempo(float tempo) {
		this.tempo = tempo;
	}

	public int getVez() {
		return vez;
	}

	public void setVez(int vez) {
		this.vez = vez;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public void Jogar() {

	}
	

	public void Novo() {
		
		EscolherAdversario();
		setJogador1(Jogador.getInstance());		
		EscolherPergunta();
	}
	public void EscolherPergunta(){
		Pergunta p = new Pergunta();
		List<Pergunta> object1 = p.Ler();
		int ID = new Random().nextInt(object1.size());
		this.setPergunta(p.buscarPerguntas(ID));
		this.questaoFeita();
		List<String> listaPerguntas = this.getPerguntasFeitas();
		List<Integer> listaI = new ArrayList<Integer>();
		
		for (String linha : listaPerguntas) {
			String[] vetdados = linha.split(",");		
			if(linha.contains(linha)){
				for (int i = 0; i < vetdados.length; i++) {
						listaI.add(Integer.parseInt(vetdados[i].substring(1).replaceAll("]", "")));
						
				}
			}

		}
		listaI.add(getPergunta().getId());
		this.setPerguntaFeitas1(listaI);
			
//		for (int j = 0; j < listaI.size(); j++) {
//			if(listaI.get(j) == this.getPergunta().getId()){
//				EscolherPergunta();
//				System.out.println(listaI.get(j));
//				System.out.println(this.getPerguntaFeitas1().get(j));
//			}
			
//		}
	}
		
	
	public void EscolherAdversario() {
		Jogador jogador = new Jogador();
		List<Jogador> object1 = jogador.Ler();
		int ID = new Random().nextInt(object1.size());
		this.setJogador2(jogador.Ler(ID));
		if (Jogador.getInstance().getId() == this.getJogador2().getId()) {
			EscolherAdversario();
		}
	}
	
	public List<Jogo> questaoFeita() {
		// lista para retornar do m�todo
		List<Jogo> listaRetorno = new ArrayList<Jogo>();
		List<String> lista1 =  new ArrayList<String>();

		// recuperar a lista de strings do txt
		Arquivo arq = new Arquivo();
		arq.setNome("PerguntasFeitas.txt");
		List<String> lista = arq.Ler();
		
		// percorrer a lista de strings
		for (String linha : lista) {
			// para cada linha
			String[] vetdados = linha.split(";");

//			 -> adicionar em um objeto
			int id = Integer.parseInt(vetdados[0]); // Id
			this.setId(id);
			lista1.add(vetdados[1]);
			this.setPerguntasFeitas(lista1);
			int j = Integer.parseInt(vetdados[2]);
			this.getJogador2().setId(j);;
			
			// -> adicionar na lista de retorno
			listaRetorno.add(this);

		}
		
		return listaRetorno;
	}

	public String Cadastrar() {
		String ret = null;					
		String linha =  + this.id + ";" + this.perguntaFeitas1 + ";" + this.jogador2.getId();
		
		Arquivo arq = new Arquivo();
		arq.setNome("PerguntasFeitas.txt");
		arq.setTexto(linha);
		arq.Gravar();
						

		ret = "OK";

		ret = "ERRO";

		return ret;

	}
	
//	public void perguntaFeitas(){
//		Jogo jogo = new Jogo();
//		jogo.questaoFeita();
//		System.out.println();
//		List<String> listat = jogo.getPerguntasFeitas();
//		List<String> lista = new ArrayList<String>();
//		
//		for (String linha : listat) {
//			String[] vetdados = linha.split(",");		
//			if(linha.contains(linha)){
//				for (int i = 0; i < vetdados.length; i++) {
//						lista.add(vetdados[i].substring(1).replaceAll("]", ""));
//				}
//			}
////			List<Integer> listI = new ArrayList<Integer>();
////			
//////			for (int i = 0; i < lista.size(); i++) {
//////				
//////				listI.add(Integer.parseInt(lista.get(i).substring(1).replaceAll("]", "")));
//////				System.out.println(lista.get(i).substring(1).replaceAll("]", ""));
//////				
//////			}
//////			jogo.setPerguntasFeitas(listat);
//		}
//		jogo.setPerguntasFeitas(lista);
//		System.out.println(jogo.getPerguntasFeitas().get(0));
////		jogo.setPerguntasFeitas(lista1);
////		lista1.add("5");
////		lista1.add("6");
////		lista1.add("7");
////		lista1.add("8");
////		jogo.Cadastrar();
//	}
	
	
	
	
}
