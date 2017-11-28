package br.edu.facear.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.facear.classes.Jogador;
import br.edu.facear.classes.Pergunta;
import java.awt.Color;

public class Tela_Avaliar extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private int index = 0;
	private JPanel contentPane;
	private JLabel lblPergunta, lblAlternativa_1, lblAlternativa_2, lblAlternativa_3, lblAlternativa_4, lblAnterior,
			lblLike, lblDesLike, lblAutor, lblCategoria;
	private Pergunta pergunta;
	private JLabel lblFundo;
	private JLabel label_1;
	private JLabel label;
	private JLabel label_2;

	public void Run() {
		this.setSize(1144, 762);
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);

	}

	public void EscolherPerguntas(int id) {
		
		pergunta = new Pergunta();
		List<Pergunta> listaObjectPerg = pergunta.Ler();
		for (Pergunta per : listaObjectPerg) {
				if (per.getId() == id && per.getJogador().getId() != Jogador.getLogado().getId()) {
					lblCategoria.setText(per.getCategoria().getCategoria());
					lblPergunta.setText("<html><b>" + per.getPergunta() + "<br></html>");
					lblAlternativa_1.setText("<html><b>" + "1 - " + per.getAlternativas1() + "<br></html>");
					lblAlternativa_2.setText("<html><b>" + "2 - " + per.getAlternativas2() + "<br></html>");
					lblAlternativa_3.setText("<html><b>" + "3 - " +  per.getAlternativas3() + "<br></html>");
					lblAlternativa_4.setText("<html><b>" + "4 - " +  per.getCorreta() + "<br></html>");
					lblAutor.setText(per.getJogador().getLogin().toUpperCase());
				}		
			}
		}

	public Tela_Avaliar() {
		setTitle("Avaliar Pergunta");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblPergunta = new JLabel("Pergunta");
		lblPergunta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPergunta.setBounds(360, 157, 623, 120);
		contentPane.add(lblPergunta);

		lblAlternativa_1 = new JLabel("Alternativa 1");
		lblAlternativa_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlternativa_1.setBounds(360, 274, 601, 28);
		contentPane.add(lblAlternativa_1);

		lblAlternativa_2 = new JLabel("Alternativa 2");
		lblAlternativa_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlternativa_2.setBounds(360, 313, 601, 28);
		contentPane.add(lblAlternativa_2);

		lblAlternativa_3 = new JLabel("Alternativa 3");
		lblAlternativa_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlternativa_3.setBounds(360, 357, 601, 28);
		contentPane.add(lblAlternativa_3);

		lblAlternativa_4 = new JLabel("Alternativa 4");
		lblAlternativa_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlternativa_4.setBounds(360, 393, 601, 28);
		contentPane.add(lblAlternativa_4);

		lblAnterior = new JLabel("");
		lblAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				List<Pergunta> listaObjectPerg = pergunta.Ler();
				if(index < listaObjectPerg.size()){
					index = 0;
					EscolherPerguntas(index);
				}else{
					index--;
					EscolherPerguntas(index);
				}
			}
		});
		
		lblAutor = new JLabel();
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutor.setBounds(672, 91, 189, 32);
		contentPane.add(lblAutor);
		lblAnterior.setIcon(new ImageIcon(
				"C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_circle-back-arrow-glyph_763477.png"));
		lblAnterior.setBounds(512, 488, 75, 64);
		contentPane.add(lblAnterior);

		JLabel lblProximo = new JLabel("");
		lblProximo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Pergunta> listaObjectPerg = pergunta.Ler();
				if(index > listaObjectPerg.size()){
					index = 0;
					EscolherPerguntas(index);
				}else{
					index++;
					EscolherPerguntas(index);
				}
			}
		});
		lblProximo.setIcon(new ImageIcon(
				"C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_circle-next-arrow-disclosure-glyph_763448.png"));
		lblProximo.setBounds(794, 488, 89, 64);
		contentPane.add(lblProximo);

		lblLike = new JLabel("");
		lblLike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pergunta = new Pergunta();
				pergunta.setId(index);
				pergunta.AvaliarPergunta(true);
				List<Pergunta> listaObjectPerg = pergunta.Ler();
				if(index > listaObjectPerg.size()){
					index = 0;
					EscolherPerguntas(index);
				}else{
					index++;
					EscolherPerguntas(index);
				}
			}
		});
		lblLike.setIcon(new ImageIcon(
				"C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_checkmark-24_103184.png"));
		lblLike.setBounds(608, 476, 81, 64);
		contentPane.add(lblLike);

		lblDesLike = new JLabel("");
		lblDesLike.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pergunta = new Pergunta();
				pergunta.setId(index);
				pergunta.AvaliarPergunta(false);
				
				List<Pergunta> listaObjectPerg = pergunta.Ler();
				if(index > listaObjectPerg.size()){
					index = 0;
					EscolherPerguntas(index);
				}else{
					index++;
					EscolherPerguntas(index);
				}
			}
		});
		lblDesLike.setIcon(new ImageIcon(
				"C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_icons_exit_1564505.png"));
		lblDesLike.setBounds(699, 476, 69, 64);
		contentPane.add(lblDesLike);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBackground(new Color(255, 255, 255));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_inicial t = new Tela_inicial();
				t.Run();
				dispose();

			}
		});
		btnVoltar.setBounds(618, 551, 121, 39);
		contentPane.add(btnVoltar);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCategoria.setBounds(657, 125, 144, 28);
		contentPane.add(lblCategoria);
		
		label_1 = new JLabel("Autor:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(616, 95, 46, 27);
		contentPane.add(label_1);
		
		label = new JLabel("by R&D");
		label.setBounds(697, 68, 46, 14);
		contentPane.add(label);
		
		label_2 = new JLabel("Quiz");
		label_2.setFont(new Font("Arial", Font.PLAIN, 40));
		label_2.setBounds(618, 23, 80, 61);
		contentPane.add(label_2);
		
		lblFundo = new JLabel("Fundo");
		lblFundo.setIcon(new ImageIcon("C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\Fundo.png"));
		lblFundo.setBounds(0, 0, 2534, 768);
		contentPane.add(lblFundo);

		index = 0;
		EscolherPerguntas(index);
	}
}
