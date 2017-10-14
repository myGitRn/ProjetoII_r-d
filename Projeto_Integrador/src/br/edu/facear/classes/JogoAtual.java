package br.edu.facear.classes;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class JogoAtual{
	
	private JLabel lblPontos, lblHorcrux, lblTempo, lblTempo1, lblLogo, lblJogador, lblJogador2, lblJogador_1, lblJogador_2, lblImg, lblPergunta;
	private JRadioButton rdbtnAlternativa_1, rdbtnAlternativa_2, rdbtnAlternativa_3, rdbtnAlternativa_4;
	
	
	public JogoAtual(){
		
	}

	public JogoAtual(JLabel lblPontos, JLabel lblHorcrux, JLabel lblTempo, JLabel lblTempo1, JLabel lblLogo,
			JLabel lblJogador, JLabel lblJogador2, JLabel lblJogador_1, JLabel lblJogador_2, JLabel lblImg,
			JLabel lblPergunta, JRadioButton rdbtnAlternativa_1, JRadioButton rdbtnAlternativa_2,
			JRadioButton rdbtnAlternativa_3, JRadioButton rdbtnAlternativa_4) {
		super();
		this.lblPontos = lblPontos;
		this.lblHorcrux = lblHorcrux;
		this.lblTempo = lblTempo;
		this.lblTempo1 = lblTempo1;
		this.lblLogo = lblLogo;
		this.lblJogador = lblJogador;
		this.lblJogador2 = lblJogador2;
		this.lblJogador_1 = lblJogador_1;
		this.lblJogador_2 = lblJogador_2;
		this.lblImg = lblImg;
		this.lblPergunta = lblPergunta;
		this.rdbtnAlternativa_1 = rdbtnAlternativa_1;
		this.rdbtnAlternativa_2 = rdbtnAlternativa_2;
		this.rdbtnAlternativa_3 = rdbtnAlternativa_3;
		this.rdbtnAlternativa_4 = rdbtnAlternativa_4;
		
		Pergunta p = new Pergunta();
		List<Pergunta> listaObjectJog = p.Ler();
		for (Pergunta pergunta : listaObjectJog) {
				
		lblPergunta = new JLabel(pergunta.getPergunta());
		
		rdbtnAlternativa_1 = new JRadioButton(pergunta.getAlternativas1());
		
		rdbtnAlternativa_2 = new JRadioButton(pergunta.getAlternativas2());
		
		rdbtnAlternativa_3 = new JRadioButton(pergunta.getAlternativas3());

		rdbtnAlternativa_4 = new JRadioButton(pergunta.getCorreta());
		
	}


	}
}