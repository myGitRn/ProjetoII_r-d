package br.edu.facear.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.facear.util.Arquivo;

public class Jogo {
	private int id;
	private float tempo;
	private Jogador jogador1, jogador2, jogadorVez;
	private Pergunta pergunta;
	private List<Rodada> rodada;
	private boolean finalizado;
	private boolean salvarJogo;
	private boolean jogoAnterior;
	private int PontoJogador1, PontoJogador2;

	public Jogo() {

	}

	public int getPontoJogador1() {
		return PontoJogador1;
	}

	public void setPontoJogador1(int pontoJogador1) {
		PontoJogador1 = pontoJogador1;
	}

	public int getPontoJogador2() {
		return PontoJogador2;
	}

	public void setPontoJogador2(int pontoJogador2) {
		PontoJogador2 = pontoJogador2;
	}

	public boolean isJogoAnterior() {
		return jogoAnterior;
	}

	public void setJogoAnterior(boolean jogoAnterior) {
		this.jogoAnterior = jogoAnterior;
	}

	public boolean isSalvarJogo() {
		return salvarJogo;
	}

	public void setSalvarJogo(boolean salvarJogo) {
		this.salvarJogo = salvarJogo;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public List<Rodada> getRodada() {
		return rodada;
	}

	public void setRodada(List<Rodada> rodada) {
		this.rodada = rodada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void SalvarJogo() {
		int cont = 0;
		Jogo jogo = new Jogo();
		List<Jogo> lista = jogo.Ler();
		for (Jogo jogo2 : lista) {
			if (Jogador.getInstance().getId() == jogo2.getJogador1().getId()) {
				cont++;
			}
		}

		if (!this.isSalvarJogo()) {
			this.setId(cont);
			Cadastrar(true);
		}

		if (!this.isSalvarJogo()) {
			Rodada rodada = new Rodada();
			rodada.setJogo(this);
			rodada.setPergunta(this.getPergunta());
			rodada.getJogo().setJogador1(this.getJogador1());
			rodada.Cadastrar(true);
		}
	}

	public void SalvarRodada() {
		int cont = 0;
		Rodada rodada = new Rodada();
		List<Rodada> lista = rodada.Ler();
		for (Rodada ro : lista) {
			if (ro.getJogo().getId() == this.getId()
					&& Jogador.getInstance().getId() == ro.getJogo().getJogador1().getId()) {
				cont++;
			}

		}

		rodada.setIdRodada(cont);
		rodada.setJogo(this);
		rodada.setPergunta(this.getPergunta());
		rodada.Cadastrar(true);
	}

	public void CarregarJogo() {

		Jogo jogo = new Jogo();
		Pergunta pergunta = new Pergunta();
		Jogador jogador = new Jogador();
		List<Jogo> lista = jogo.Ler();
		for (Jogo jogo2 : lista) {
			if (jogo2.getId() == this.getId()) {
				this.setId(this.getId());
				this.setPontoJogador1(jogo2.getPontoJogador1());
				this.setPontoJogador2(jogo2.getPontoJogador2());
				for (Rodada rodada : jogo2.getRodada()) {
					if (!rodada.isPerguntaRes()
							&& rodada.getJogo().getJogador1().getId() == Jogador.getInstance().getId()) {
						this.setPergunta(pergunta.buscarPerguntas(rodada.getPergunta().getId()));
						this.setJogador2(jogador.Ler(rodada.getPergunta().getId()));
						this.setJogadorVez(jogador.Ler(rodada.getJogo().getJogadorVez().getId()));
						this.setJogador1(jogador.Ler(jogo2.getJogador1().getId()));
						this.setJogador2(jogador.Ler(jogo2.getJogador2().getId()));

					}
				}
				this.setSalvarJogo(true);
			}
		}
	}

	public void EscolherPergunta() {
		Pergunta pergunta = new Pergunta();
		List<Pergunta> object1 = pergunta.Ler();
		int ID = new Random().nextInt(6)+1;
		List<Integer> lista1 = new ArrayList<Integer>();
		for (Pergunta pergunta2 : object1) {
			if(pergunta2.getCategoria().getId() == ID && pergunta2.getAvaliacao() >= 5){
				lista1.add(pergunta2.getId());
			}
		}
		int sorteado = lista1.get(new Random().nextInt(lista1.size()));
		
		this.setPergunta(pergunta.buscarPerguntas(sorteado));
		
		List<Jogo> object2 = this.Ler();
		for (Jogo jogo : object2) {
			for (Rodada rodada : jogo.getRodada()) {
				if (rodada.getJogo().getId() == this.getId()
						&& rodada.getJogo().getJogador1().getId() == Jogador.getInstance().getId()) {
					if (getPergunta().getId() == rodada.getPergunta().getId())
						EscolherPergunta();
				}
			}
		}
	}

	public void PerguntasFeitas() {
		List<Rodada> lista = new Rodada().Ler();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getJogo().getJogador1().getId() == Jogador.getInstance().getId()
					&& lista.get(i).getJogo().getId() == this.getId()) {
				lista.get(i).setPerguntaRes(true);
			}
		}
		new Rodada().Salvar(lista);
	}

	public void FinalizarJogo() {
		List<Jogo> lista = new Jogo().Ler();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getId() == this.getId()
					&& lista.get(i).getJogador1().getId() == Jogador.getInstance().getId()) {
				lista.get(i).setFinalizado(true);
			}
		}
		new Jogo().SalvarTudo(lista);
	}

	public void EscolherAdversario() {
		Jogador jogador = new Jogador();
		List<Jogador> object1 = jogador.Ler();
		int ID = new Random().nextInt(object1.size());
		this.setJogador2(jogador.Ler(ID));
		this.setJogador1(Jogador.getInstance());
		if (getJogador1().getId() == getJogador2().getId()) {
			EscolherAdversario();
		}
	}

	public void PedirAjuda() {
		if (this.getJogadorVez().getId() == this.getJogadorVez().getId()) {
			List<Jogador> lista = new Jogador().Ler();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getId() == this.getJogadorVez().getId() && lista.get(i).getHorcrux() > 0) {
					lista.get(i).setHorcrux(lista.get(i).getHorcrux() - 1);
					this.jogadorVez.setHorcrux(lista.get(i).getHorcrux());
				}
			}
			new Jogador().Salvar(lista);

		}
	}

	public void AtualizarPontos() {

		if (this.getJogadorVez().getId() == this.getJogadorVez().getId()) {
			List<Jogador> lista = new Jogador().Ler();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getId() == this.getJogadorVez().getId()) {
					if (lista.get(i).getPontos() == 9) {
						int diferenca = lista.get(i).getPontos() - 8;
						lista.get(i).setHorcrux(lista.get(i).getHorcrux() + 1);
						lista.get(i).setPontos(diferenca);
						this.jogadorVez.setPontos(diferenca);
						this.jogadorVez.setHorcrux(lista.get(i).getHorcrux());
					} else if (lista.get(i).getPontos() == 10) {
						lista.get(i).setHorcrux(lista.get(i).getHorcrux() + 1);
						lista.get(i).setPontos(0);
						this.jogadorVez.setPontos(lista.get(i).getPontos());
						this.jogadorVez.setHorcrux(lista.get(i).getHorcrux());
					} else {
						lista.get(i).setPontos(lista.get(i).getPontos() + 3);
						this.jogadorVez.setPontos(lista.get(i).getPontos());
						this.jogadorVez.setHorcrux(lista.get(i).getHorcrux());
					}
					if (lista.get(i).getHorcrux() % 10 == 0) {
						lista.get(i).setNivel(lista.get(i).getNivel() + 1);
					}
				}
			}
			new Jogador().Salvar(lista);
		}
		
		List<Jogo> lista1 = Ler();
		for (int i = 0; i < lista1.size(); i++) {
			if (lista1.get(i).getId() == this.getId()) {
				if (this.getJogadorVez().getId() == Jogador.getInstance().getId()) {
					lista1.get(i).setPontoJogador1(lista1.get(i).getPontoJogador1() +1);
					lista1.get(i).setPontoJogador2(lista1.get(i).getPontoJogador2());
				} else {
					lista1.get(i).setPontoJogador2(lista1.get(i).getPontoJogador2() +1);
					lista1.get(i).setPontoJogador1(lista1.get(i).getPontoJogador1());
				}
			}
		}
		new Jogo().SalvarTudo(lista1);
		
	}

	public List<Jogo> Ler() {
		// lista para retornar do m�todo
		List<Jogo> listaRetorno = new ArrayList<Jogo>();

		// recuperar a lista de strings do txt
		Arquivo arq = new Arquivo();
		arq.setNome("Jogo.txt");
		List<String> lista = arq.Ler();

		// percorrer a lista de strings
		for (String linha : lista) {
			// para cada linha
			String[] vetdados = linha.split(";");

			// -> adicionar em um objeto
			Jogo jogo = new Jogo();
			int id = Integer.parseInt(vetdados[0]); // Id
			jogo.setId(id);

			// int idRodada = Integer.parseInt(vetdados[1]);
			// Rodada rodada = new Rodada();
			// rodada.setIdRodada(idRodada);
			List<Rodada> listaRodada = new Rodada().listaRodada(id);
			jogo.setRodada(listaRodada);

			int idJogador1 = Integer.parseInt(vetdados[1]);
			Jogador jogador1 = new Jogador();
			jogador1.setId(idJogador1);
			jogo.setJogador1(jogador1);

			int idJogador2 = Integer.parseInt(vetdados[2]);
			Jogador jogador2 = new Jogador();
			jogador2.setId(idJogador2);
			jogo.setJogador2(jogador2);
			
			int ponto1 = Integer.parseInt(vetdados[3]);
			jogo.setPontoJogador1(ponto1);
			
			int ponto2 = Integer.parseInt(vetdados[4]);
			jogo.setPontoJogador2(ponto2);
			
			boolean finalizado = Boolean.parseBoolean(vetdados[5]);
			jogo.setFinalizado(finalizado);

			// -> adicionar na lista de retorno
			listaRetorno.add(jogo);

		}

		return listaRetorno;
	}

	public String Cadastrar(boolean acrescentar) {
		String ret = null;

		try {

			String linha = this.getId() + ";" + this.getJogador1().getId() + ";" + this.getJogador2().getId() + ";"
					+ this.PontoJogador1 + ";" + this.PontoJogador2 + ";" + this.finalizado;

			Arquivo arq = new Arquivo();
			arq.setNome("Jogo.txt");
			arq.setAcrescentar(acrescentar);
			arq.setTexto(linha);
			arq.Gravar();

			ret = "OK";
		} catch (Exception e1) {
			ret = "ERRO";

		}

		return ret;
	}

	public Jogo Ler(int ID) {
		Jogo jogoRetorno = null;
		Jogo jo = new Jogo();
		List<Jogo> listaObjectJo = jo.Ler();
		for (Jogo jogo : listaObjectJo) {
			if (jogo.getId() == ID)
				jogoRetorno = jogo;
		}
		return jogoRetorno;
	}

	public void SalvarTudo(List<Jogo> lista) {
		int cont = 0;
		for (Jogo jogo : lista) {
			if (cont == 0)
				jogo.Cadastrar(false);
			else
				jogo.Cadastrar(true);
			cont++;
		}
	}
}
