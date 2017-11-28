package br.edu.facear.telas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.facear.classes.Jogador;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Tela_inicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel lblBemVindo;
	private JButton btnJogar, btnRanking, btnCadastrarPergunta, btnAvaliarPergunta;
	private JLabel lblFundo;
	private JLabel label;
	private JLabel label_1;

	public void Run() {
		this.setSize(1144, 762);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);

	}

	public Tela_inicial() {
		setTitle("Tela inicial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1218, 640);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		painel.add(panel, BorderLayout.CENTER);
		lblBemVindo = new JLabel("Bem vindo " + Jogador.getInstance().getLogin().toUpperCase());
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBemVindo.setBounds(590, 133, 217, 34);
		panel.add(lblBemVindo);

		btnJogar = new JButton("Jogar");
		btnJogar.setBackground(new Color(255, 255, 255));
		btnJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_Jogo jogo = new Tela_Jogo();
				jogo.Run();
				dispose();

			}
		});
		btnJogar.setBounds(590, 194, 200, 46);
		panel.add(btnJogar);
		btnJogar.setFont(new Font("Arial", Font.PLAIN, 16));

		btnRanking = new JButton("Ranking");
		btnRanking.setBackground(new Color(255, 255, 255));
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_Ranking r = new Tela_Ranking();
				r.Run();
				dispose();
			}
		});
		btnRanking.setBounds(590, 264, 200, 44);
		panel.add(btnRanking);
		btnRanking.setFont(new Font("Arial", Font.PLAIN, 16));

		btnCadastrarPergunta = new JButton("Cadastrar perguntas");
		btnCadastrarPergunta.setBackground(new Color(255, 255, 255));
		btnCadastrarPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_Cadastrar_Pergunta c = new Tela_Cadastrar_Pergunta();
				c.Run();
				dispose();
			}
		});
		btnCadastrarPergunta.setBounds(590, 335, 200, 44);
		panel.add(btnCadastrarPergunta);
		btnCadastrarPergunta.setFont(new Font("Arial", Font.PLAIN, 16));

		btnAvaliarPergunta = new JButton("Avaliar Perguntas");
		btnAvaliarPergunta.setBackground(new Color(255, 255, 255));
		btnAvaliarPergunta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Tela_Avaliar a = new Tela_Avaliar();
				a.Run();
				dispose();

			}
		});
		btnAvaliarPergunta.setBounds(590, 400, 200, 44);
		panel.add(btnAvaliarPergunta);
		btnAvaliarPergunta.setFont(new Font("Arial", Font.PLAIN, 16));

		JButton btnSair = new JButton("Sair");
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_Login l = new Tela_Login();
				l.Run();
				dispose();
			}
		});
		btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSair.setBounds(590, 467, 200, 46);
		panel.add(btnSair);
		
		label = new JLabel("by R&D");
		label.setBounds(711, 93, 46, 14);
		panel.add(label);
		
		label_1 = new JLabel("Quiz");
		label_1.setFont(new Font("Arial", Font.PLAIN, 40));
		label_1.setBounds(632, 48, 80, 61);
		panel.add(label_1);
		
		lblFundo = new JLabel("Fundo");
		lblFundo.setIcon(new ImageIcon("C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\Fundo.png"));
		lblFundo.setBounds(0, 0, 2362, 768);
		panel.add(lblFundo);

	}
}
