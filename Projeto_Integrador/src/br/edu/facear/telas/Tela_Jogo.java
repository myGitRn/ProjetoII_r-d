package br.edu.facear.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.facear.classes.Jogador;
import br.edu.facear.classes.Jogo;
import br.edu.facear.classes.Rodada;

public class Tela_Jogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private String resposta;
	private JPanel contentPane;
	private JLabel lblPontos1, lblHorcrux1, lblTempo, lblTempo1, lblJogador, lblJogador2, lblJogador_1,
			lblJogador_2, lblImg, lblPergunta, lblCategoria, lblPontos2, lblHorcrux2, lblPontoHorc2, lblPont1, lblPont2, lblPontoHorc1;
	private JRadioButton rdbtnAlternativa_1, rdbtnAlternativa_2, rdbtnAlternativa_3, rdbtnAlternativa_4;
	private JButton btnConfirmar, btnPedirAjuda, btnSair;
	private Thread contar;
	private Jogo jogo;
	private boolean fimDeJogo;
	private ButtonGroup bg;
	private JLabel lblRodada;
	private JLabel lblLogo;
	private JLabel lblLogo1;
	private JLabel lblAcertos1;
	private JLabel lblAcerto1;
	private JLabel lblAcertos2;
	private JLabel lblAcerto2;

	public void Run() {
		this.setSize(1144, 762);
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);
	}

	public void atualizarRodada() {
		List<Jogo> lista = jogo.Ler();
		for (Jogo jogo : lista) {
			if (this.jogo.getId() == jogo.getId()) {
				for (Rodada rodada : jogo.getRodada()) {
					if (jogo.getId() == rodada.getJogo().getId()) {
						lblRodada.setText(String.valueOf(rodada.getIdRodada() + 1 + "/15"));
						if (this.jogo.getId() == rodada.getJogo().getId()
								&& Jogador.getInstance().getId() == rodada.getJogo().getJogador1().getId()
								&& rodada.getIdRodada() == 15) {
							contar.interrupt();
							jogo.setId(this.jogo.getId());
							this.jogo.FinalizarJogo();
							fimDeJogo = true;
							Tela_Final_Jogo t = new Tela_Final_Jogo(this.jogo.getId());
							t.Run(this.jogo.getId());
							dispose();
						}
					}	
				}
			}
		}
	}

	public void ComecarJogo() {
		jogo = new Jogo();

		BuscarJogos();

		if (!jogo.isJogoAnterior()) {
			
			Contar();
			
			jogo.EscolherAdversario();

			lblJogador_1.setText(jogo.getJogador1().getLogin().toUpperCase());

			lblJogador_2.setText(jogo.getJogador2().getLogin().toUpperCase());
			jogo.setJogadorVez(jogo.getJogador1());

			lblJogador_1.setForeground(Color.red);
			lblJogador_2.setForeground(Color.black);
			
			lblPont1.setVisible(true);;
			lblPontos1.setVisible(true);
			lblHorcrux1.setVisible(true);
			lblPontoHorc1.setVisible(true);
			lblAcerto1.setVisible(true);
			lblAcertos1.setVisible(true);
			
			lblPont1.setText(String.valueOf(jogo.getJogador1().getPontos()));
			lblPontoHorc1.setText(String.valueOf(jogo.getJogadorVez().getHorcrux()));
			lblAcerto1.setText(String.valueOf(jogo.getPontoJogador1()));
			
			lblPont2.setVisible(false);
			lblHorcrux2.setVisible(false);
			lblPontos2.setVisible(false);
			lblPontoHorc2.setVisible(false);
			lblAcertos2.setVisible(false);
			lblAcerto2.setVisible(false);
			
			
			CarregarPergunta();

		} else {
			
			Contar();
			PerguntaJogoAnterior();
		}

		jogo.SalvarJogo();

		atualizarRodada();

	}

	public void Contar() {
		contar = new Thread() {
			public void run() {
				int segundos = 20;
				int i;
				int t = 0;
				try {
					while (t != -1) {	
						for (i = segundos; i >= 0; i--) {
							lblTempo1.setText(String.format("%02d:%02d", t, i));
							Thread.sleep(1000); // 1 segundo
							if(i<=5){
								lblTempo1.setForeground(Color.RED);
							}
						}
						t--;
						if(t == -1 && !fimDeJogo){
							JOptionPane.showMessageDialog(null, "Acabou seu tempo!!!");
							if (jogo.getJogadorVez().getId() == jogo.getJogador1().getId()) {
								jogo.setJogadorVez(jogo.getJogador2());
								lblJogador_1.setForeground(Color.black);
								lblJogador_2.setForeground(Color.red);
								
								lblPont2.setVisible(true);
								lblHorcrux2.setVisible(true);
								lblPontos2.setVisible(true);
								lblPontoHorc2.setVisible(true);
								lblAcerto2.setVisible(true);
								lblAcertos2.setVisible(true);
								
								lblPont2.setText(String.valueOf(jogo.getJogador2().getPontos()));
								lblPontoHorc2.setText(String.valueOf(jogo.getJogadorVez().getHorcrux()));
								lblAcerto2.setText(String.valueOf(jogo.getPontoJogador2()));
								
								lblPont1.setVisible(false);;
								lblPontos1.setVisible(false);
								lblHorcrux1.setVisible(false);
								lblPontoHorc1.setVisible(false);
								lblAcertos1.setVisible(false);
								lblAcerto1.setVisible(false);
								
								jogo.PerguntasFeitas();
								CarregarPergunta();
								jogo.SalvarRodada();
								atualizarRodada();
							} else {
								jogo.setJogadorVez(jogo.getJogador1());
								lblJogador_2.setForeground(Color.black);
								lblJogador_1.setForeground(Color.red);
								
								lblPont1.setVisible(true);;
								lblPontos1.setVisible(true);
								lblHorcrux1.setVisible(true);
								lblPontoHorc1.setVisible(true);
								lblAcertos1.setVisible(true);
								lblAcerto1.setVisible(true);
								
								lblPont1.setText(String.valueOf(jogo.getJogador1().getPontos()));
								lblPontoHorc1.setText(String.valueOf(jogo.getJogadorVez().getHorcrux()));
								lblAcerto1.setText(String.valueOf(jogo.getPontoJogador1()));
								
								lblPont2.setVisible(false);
								lblHorcrux2.setVisible(false);
								lblPontos2.setVisible(false);
								lblPontoHorc2.setVisible(false);
								lblAcertos2.setVisible(false);
								lblAcerto2.setVisible(false);
								
								jogo.PerguntasFeitas();
								CarregarPergunta();
								jogo.SalvarRodada();
								atualizarRodada();
								
							}
							contar.interrupt();
							if(!fimDeJogo)
								Contar();
						}	
					}
				} catch (InterruptedException e) {
					System.out.println("Erro: " + e.getMessage());
				}
			}
		};
		contar.start();
	}
	
	public void VerificarResposta() {
		if (rdbtnAlternativa_1.isSelected())
			resposta = rdbtnAlternativa_1.getText();
		else if (rdbtnAlternativa_2.isSelected())
			resposta = rdbtnAlternativa_2.getText();
		else if (rdbtnAlternativa_3.isSelected())
			resposta = rdbtnAlternativa_3.getText();
		else if (rdbtnAlternativa_4.isSelected())
			resposta = rdbtnAlternativa_4.getText();

		
			String retirarTag = "<[^>]+>";
			String res = resposta.replaceAll(retirarTag, "");

		if (jogo.getPergunta().ValidarPergunta(res).equals("OK")) {
			JOptionPane.showMessageDialog(null, "Parab�ns voc� acertou!!!");
			jogo.AtualizarPontos();
			if(jogo.getJogadorVez().getId() == jogo.getJogador1().getId()){
				jogo.setJogadorVez(jogo.getJogadorVez());
				lblPont1.setText(String.valueOf(jogo.getJogador1().getPontos()));
				lblPontoHorc1.setText(String.valueOf(jogo.getJogador1().getHorcrux()));
				jogo.setPontoJogador1(jogo.getPontoJogador1()+1);
				lblAcerto1.setText(String.valueOf(jogo.getPontoJogador1()));
			}else{
				jogo.setJogadorVez(jogo.getJogadorVez());
				lblPont2.setText(String.valueOf(jogo.getJogador2().getPontos()));
				lblPontoHorc2.setText(String.valueOf(jogo.getJogador2().getHorcrux()));
				jogo.setPontoJogador2(jogo.getPontoJogador2()+1);
				lblAcerto2.setText(String.valueOf(jogo.getPontoJogador2()));
			}
			rdbtnAlternativa_1.setVisible(true);
			rdbtnAlternativa_2.setVisible(true);
			rdbtnAlternativa_3.setVisible(true);
			rdbtnAlternativa_4.setVisible(true);
			btnPedirAjuda.setEnabled(true);
			jogo.PerguntasFeitas();
			atualizarRodada();
		} else {
			JOptionPane.showMessageDialog(null, "Resposta Errada =(");
			if (jogo.getJogadorVez().getId() == jogo.getJogador1().getId()) {
				jogo.setJogadorVez(jogo.getJogador2());
				lblJogador_1.setForeground(Color.black);
				lblJogador_2.setForeground(Color.red);
				
				lblPont2.setVisible(true);
				lblHorcrux2.setVisible(true);
				lblPontos2.setVisible(true);
				lblPontoHorc2.setVisible(true);
				lblAcerto2.setVisible(true);
				lblAcertos2.setVisible(true);
				
				lblPont2.setText(String.valueOf(jogo.getJogador2().getPontos()));
				lblPontoHorc2.setText(String.valueOf(jogo.getJogadorVez().getHorcrux()));
				lblAcerto2.setText(String.valueOf(jogo.getPontoJogador2()));
				
				lblPont1.setVisible(false);;
				lblPontos1.setVisible(false);
				lblHorcrux1.setVisible(false);
				lblPontoHorc1.setVisible(false);
				lblAcerto1.setVisible(false);
				lblAcertos1.setVisible(false);
				
				jogo.PerguntasFeitas();
				CarregarPergunta();
				atualizarRodada();
			} else {
				jogo.setJogadorVez(jogo.getJogador1());
				lblJogador_2.setForeground(Color.black);
				lblJogador_1.setForeground(Color.red);
				
				lblPont1.setVisible(true);;
				lblPontos1.setVisible(true);
				lblHorcrux1.setVisible(true);
				lblPontoHorc1.setVisible(true);
				lblAcerto1.setVisible(true);
				lblAcertos1.setVisible(true);
				
				lblPont1.setText(String.valueOf(jogo.getJogador1().getPontos()));
				lblPontoHorc1.setText(String.valueOf(jogo.getJogadorVez().getHorcrux()));
				lblAcerto1.setText(String.valueOf(jogo.getPontoJogador1()));
				
				lblPont2.setVisible(false);
				lblHorcrux2.setVisible(false);
				lblPontos2.setVisible(false);
				lblPontoHorc2.setVisible(false);
				lblAcerto2.setVisible(false);
				lblAcertos2.setVisible(false);
				
				jogo.PerguntasFeitas();
				CarregarPergunta();
				atualizarRodada();

			}
			
			rdbtnAlternativa_1.setVisible(true);
			rdbtnAlternativa_2.setVisible(true);
			rdbtnAlternativa_3.setVisible(true);
			rdbtnAlternativa_4.setVisible(true);
			btnPedirAjuda.setEnabled(true);
			
		}
	}

	public void BuscarJogos() {
		jogo = new Jogo();
		List<Jogo> lista = jogo.Ler();
		for (Jogo jogo2 : lista) {
			if (Jogador.getInstance().getId() == jogo2.getJogador1().getId()) {
				if (!jogo2.isFinalizado()) {
					int result = JOptionPane.showConfirmDialog(null, "Deseja Continuar o jogo anterior ? ", "Sim",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						jogo.setId(jogo2.getId());
						jogo.CarregarJogo();
						jogo.setJogoAnterior(true);
					} else {
						jogo.setId(jogo2.getId());
						jogo.FinalizarJogo();
					}
				}
			}
		}
	}

	public void PerguntaJogoAnterior() {

		jogo.CarregarJogo();

		if (jogo.getJogadorVez().getId() == Jogador.getInstance().getId()) {
			jogo.setJogadorVez(jogo.getJogador1());
			
			lblJogador_1.setText(jogo.getJogador1().getLogin().toUpperCase());
			lblJogador_2.setForeground(Color.black);
			lblJogador_1.setForeground(Color.red);
			
			lblPont1.setVisible(true);;
			lblPontos1.setVisible(true);
			lblHorcrux1.setVisible(true);
			lblPontoHorc1.setVisible(true);
			lblAcertos1.setVisible(true);
			lblAcerto1.setVisible(true);
			
			lblPont1.setText(String.valueOf(jogo.getJogador1().getPontos()));
			lblPontoHorc1.setText(String.valueOf(jogo.getJogador1().getHorcrux()));
			lblAcerto1.setText(String.valueOf(jogo.getPontoJogador1()));
			
			lblPont2.setVisible(false);
			lblHorcrux2.setVisible(false);
			lblPontos2.setVisible(false);
			lblPontoHorc2.setVisible(false);
			lblAcertos2.setVisible(false);
			lblAcerto2.setVisible(false);
			
			lblJogador_2.setText(jogo.getJogador2().getLogin().toUpperCase());
			
		} else {
			jogo.setJogadorVez(jogo.getJogador2());
			lblJogador_2.setText(jogo.getJogador2().getLogin().toUpperCase());
			lblJogador_1.setForeground(Color.black);
			lblJogador_2.setForeground(Color.red);	
			
			lblPont2.setVisible(true);
			lblHorcrux2.setVisible(true);
			lblPontos2.setVisible(true);
			lblPontoHorc2.setVisible(true);
			lblAcertos2.setVisible(true);
			lblAcerto2.setVisible(true);
			
			lblPont2.setText(String.valueOf(jogo.getJogador2().getPontos()));
			lblPontoHorc2.setText(String.valueOf(jogo.getJogador2().getHorcrux()));
			lblAcerto2.setText(String.valueOf(jogo.getPontoJogador2()));
			
			lblPont1.setVisible(false);;
			lblPontos1.setVisible(false);
			lblHorcrux1.setVisible(false);
			lblPontoHorc1.setVisible(false);
			lblAcertos1.setVisible(false);
			lblAcerto1.setVisible(false);
			
			lblJogador_1.setText(jogo.getJogador1().getLogin().toUpperCase());

		}
		
		lblCategoria.setText(jogo.getPergunta().getCategoria().getCategoria());
		
		lblPergunta.setText("<html><b>" + jogo.getPergunta().getPergunta() + "<br></html>");

		List<String> escolhas = Arrays.asList(jogo.getPergunta().getAlternativas1(),
				jogo.getPergunta().getAlternativas2(), jogo.getPergunta().getAlternativas3(),
				jogo.getPergunta().getCorreta());

		Collections.shuffle(escolhas);

		rdbtnAlternativa_1.setText("<html><b>" + escolhas.get(0) + "<br></html>");
		rdbtnAlternativa_2.setText("<html><b>" + escolhas.get(1) + "<br></html>");
		rdbtnAlternativa_3.setText("<html><b>" + escolhas.get(2) + "<br></html>");
		rdbtnAlternativa_4.setText("<html><b>" + escolhas.get(3) + "<br></html>");

	}

	public void CarregarPergunta() {

		jogo.EscolherPergunta();

		lblCategoria.setText(jogo.getPergunta().getCategoria().getCategoria());

		lblPergunta.setText("<html><b>" + jogo.getPergunta().getPergunta() + "<br></html>");

		List<String> escolhas = Arrays.asList(jogo.getPergunta().getAlternativas1(),
				jogo.getPergunta().getAlternativas2(), jogo.getPergunta().getAlternativas3(),
				jogo.getPergunta().getCorreta());

		Collections.shuffle(escolhas);

		rdbtnAlternativa_1.setText("<html><b>" + escolhas.get(0) + "<br></html>");
		rdbtnAlternativa_2.setText("<html><b>" + escolhas.get(1) + "<br></html>");
		rdbtnAlternativa_3.setText("<html><b>" + escolhas.get(2) + "<br></html>");
		rdbtnAlternativa_4.setText("<html><b>" + escolhas.get(3) + "<br></html>");

	}

	public Tela_Jogo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1263, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 1363, 718);
		contentPane.add(panel);
		panel.setLayout(null);

		lblPontos1 = new JLabel("Pontos");
		lblPontos1.setBounds(252, 387, 70, 17);
		panel.add(lblPontos1);
		lblPontos1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblPont1 = new JLabel("0");
		lblPont1.setBounds(319, 389, 46, 14);
		panel.add(lblPont1);

		lblHorcrux1 = new JLabel("Horcrux");
		lblHorcrux1.setBounds(252, 415, 48, 17);
		panel.add(lblHorcrux1);
		lblHorcrux1.setFont(new Font("Arial", Font.PLAIN, 14));
		
		lblPontoHorc1 = new JLabel("0");
		lblPontoHorc1.setBounds(319, 415, 46, 14);
		panel.add(lblPontoHorc1);
		
		lblPontos2 = new JLabel("Pontos");
		lblPontos2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPontos2.setBounds(975, 387, 44, 17);
		panel.add(lblPontos2);
		
		lblPont2 = new JLabel("0");
		lblPont2.setBounds(1039, 389, 46, 14);
		panel.add(lblPont2);
		
		lblHorcrux2 = new JLabel("Horcrux");
		lblHorcrux2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblHorcrux2.setBounds(975, 418, 48, 17);
		panel.add(lblHorcrux2);
		
		lblPontoHorc2 = new JLabel("0");
		lblPontoHorc2.setBounds(1039, 420, 46, 14);
		panel.add(lblPontoHorc2);

		lblTempo = new JLabel("Tempo:");
		lblTempo.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTempo.setBounds(603, 102, 68, 34);
		panel.add(lblTempo);

		lblTempo1 = new JLabel("00:20");
		lblTempo1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTempo1.setBounds(681, 107, 171, 24);
		panel.add(lblTempo1);

		lblJogador = new JLabel();
		lblJogador.setIcon(new ImageIcon(
				"C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_user_male_172625.png"));
		lblJogador.setBounds(230, 239, 135, 112);
		panel.add(lblJogador);

		lblJogador2 = new JLabel("Jogador 2");
		lblJogador2.setIcon(new ImageIcon("C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_user_male_172625.png"));
		lblJogador2.setBounds(955, 239, 120, 112);
		panel.add(lblJogador2);

		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contar.interrupt();
				Tela_inicial tela = new Tela_inicial();
				tela.Run();
				dispose();
			}
		});
		btnSair.setBounds(726, 524, 103, 38);
		panel.add(btnSair);

		lblPergunta = new JLabel();
		lblPergunta.setBounds(420, 206, 497, 121);
		panel.add(lblPergunta);

		rdbtnAlternativa_1 = new JRadioButton();
		rdbtnAlternativa_1.setBackground(new Color(51, 204, 255));
		rdbtnAlternativa_1.setBounds(420, 334, 497, 39);
		panel.add(rdbtnAlternativa_1);

		rdbtnAlternativa_2 = new JRadioButton();
		rdbtnAlternativa_2.setBackground(new Color(51, 204, 255));
		rdbtnAlternativa_2.setBounds(420, 376, 497, 34);
		panel.add(rdbtnAlternativa_2);

		rdbtnAlternativa_3 = new JRadioButton();
		rdbtnAlternativa_3.setBackground(new Color(51, 204, 255));
		rdbtnAlternativa_3.setBounds(420, 413, 496, 34);
		panel.add(rdbtnAlternativa_3);

		rdbtnAlternativa_4 = new JRadioButton();
		rdbtnAlternativa_4.setBackground(new Color(51, 204, 255));
		rdbtnAlternativa_4.setBounds(420, 450, 497, 34);
		panel.add(rdbtnAlternativa_4);

		bg = new ButtonGroup();
		bg.add(rdbtnAlternativa_1);
		bg.add(rdbtnAlternativa_2);
		bg.add(rdbtnAlternativa_3);
		bg.add(rdbtnAlternativa_4);

		lblJogador_1 = new JLabel();
		lblJogador_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador_1.setBounds(195, 352, 182, 24);
		panel.add(lblJogador_1);

		lblJogador_2 = new JLabel();
		lblJogador_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblJogador_2.setBounds(923, 352, 182, 24);
		panel.add(lblJogador_2);

		lblImg = new JLabel();
		lblImg.setIcon(new ImageIcon("C:\\Users\\ricar\\Downloads\\if_access-time_326483.png"));
		lblImg.setBounds(521, 91, 70, 64);
		panel.add(lblImg);

		btnPedirAjuda = new JButton("Pedir Ajuda");
		btnPedirAjuda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPedirAjuda.setBackground(new Color(255, 255, 255));
		btnPedirAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jogo.getJogadorVez().getHorcrux() != 0) {
					String r1 = rdbtnAlternativa_1.getText();
					String r2 = rdbtnAlternativa_2.getText();
					String r3 = rdbtnAlternativa_3.getText();
					String r4 = rdbtnAlternativa_4.getText();

					String retirarTag1 = "<[^>]+>";
					String res1 = r1.replaceAll(retirarTag1, "");

					String retirarTag2 = "<[^>]+>";
					String res2 = r2.replaceAll(retirarTag2, "");

					String retirarTag3 = "<[^>]+>";
					String res3 = r3.replaceAll(retirarTag3, "");

					String retirarTag4 = "<[^>]+>";
					String res4 = r4.replaceAll(retirarTag4, "");

					int cont = 0;
					while (cont < 2) {
						if (cont < 2 && jogo.getPergunta().ValidarPergunta(res1).equals("ERRO")) {
							rdbtnAlternativa_1.setVisible(false);
						} else {
							rdbtnAlternativa_1.setVisible(true);
							cont++;
						}
						if (cont == 1 && cont < 2 && jogo.getPergunta().ValidarPergunta(res2).equals("ERRO")) {
							rdbtnAlternativa_2.setVisible(false);

						} else {
							rdbtnAlternativa_2.setVisible(true);
						}
						if (cont < 2 && jogo.getPergunta().ValidarPergunta(res3).equals("ERRO")) {
							rdbtnAlternativa_3.setVisible(false);
							cont++;
						} else {
							rdbtnAlternativa_3.setVisible(true);
							cont++;
						}
						if (cont < 2 && jogo.getPergunta().ValidarPergunta(res4).equals("ERRO")) {
							rdbtnAlternativa_4.setVisible(false);

						} else {
							rdbtnAlternativa_4.setVisible(true);
							cont++;
						}
					}

					btnPedirAjuda.setEnabled(false);
					
					
					jogo.PedirAjuda();
					
					if(jogo.getJogadorVez().getId() == jogo.getJogador1().getId()){
						jogo.setJogadorVez(jogo.getJogadorVez());
						lblPont1.setText(String.valueOf(jogo.getJogador1().getPontos()));
						lblPontoHorc1.setText(String.valueOf(jogo.getJogadorVez().getHorcrux()));
					}else{
						jogo.setJogadorVez(jogo.getJogadorVez());
						lblPont2.setText(String.valueOf(jogo.getJogador2().getPontos()));
						lblPontoHorc2.setText(String.valueOf(jogo.getJogadorVez().getHorcrux()));
					}
					
				}else
					JOptionPane.showMessageDialog(null, "Voc� n�o tem Horcrux Sufici�ntes");
			}
		});
		btnPedirAjuda.setBounds(493, 524, 105, 38);
		panel.add(btnPedirAjuda);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnConfirmar.setBackground(new Color(255, 255, 255));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contar.interrupt();
				VerificarResposta();
				CarregarPergunta();
				jogo.SalvarRodada();
				bg.clearSelection();
				atualizarRodada();
				Contar();
			}
		});

		lblRodada = new JLabel("Rodada: 0/15");
		lblRodada.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRodada.setHorizontalAlignment(SwingConstants.CENTER);
		lblRodada.setBounds(614, 136, 117, 24);
		panel.add(lblRodada);

		btnConfirmar.setBounds(608, 524, 107, 38);
		panel.add(btnConfirmar);

		lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoria.setBounds(564, 171, 200, 24);
		panel.add(lblCategoria);
		
		lblLogo = new JLabel("Quiz");
		lblLogo.setFont(new Font("Arial", Font.PLAIN, 40));
		lblLogo.setBounds(606, 30, 80, 61);
		panel.add(lblLogo);
		
		lblLogo1 = new JLabel("by R&D");
		lblLogo1.setBounds(685, 75, 46, 14);
		panel.add(lblLogo1);
		
		lblAcertos1 = new JLabel("Acertos");
		lblAcertos1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAcertos1.setBounds(252, 446, 46, 14);
		panel.add(lblAcertos1);
		
		lblAcerto1 = new JLabel("0");
		lblAcerto1.setBounds(319, 448, 46, 14);
		panel.add(lblAcerto1);
		
		lblAcertos2 = new JLabel("Acertos");
		lblAcertos2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAcertos2.setBounds(975, 448, 46, 14);
		panel.add(lblAcertos2);
		
		lblAcerto2 = new JLabel("0");
		lblAcerto2.setBounds(1039, 448, 46, 14);
		panel.add(lblAcerto2);
		
		JLabel lblFundo = new JLabel();
		lblFundo.setIcon(new ImageIcon("C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\Fundo.png"));
		lblFundo.setBounds(-75, -16, 2500, 768);
		panel.add(lblFundo);
		
		ComecarJogo();

	}
}
