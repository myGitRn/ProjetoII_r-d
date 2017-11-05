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

import br.edu.facear.classes.Pergunta;

public class Tela_Avaliar extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private int index = 0;
	private JPanel contentPane;
	private JLabel lblPergunta, lblAlternativa_1, lblAlternativa_2, lblAlternativa_3, lblAlternativa_4, lblAnterior,
			lblLike, lblDesLike, lblAutor, lblCategoria;

	public void Run() {
		this.setSize(1144, 762);
		this.setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
//		this.setResizable(false);

	}

	public void EscolherPerguntas(int id) {

		Pergunta pergunta = new Pergunta();
		List<Pergunta> listaObjectPerg = pergunta.Ler();

		for (Pergunta pergunta2 : listaObjectPerg) {

			if (pergunta2.getId() == id) {
				lblPergunta.setText("<html><b>" + pergunta2.getPergunta() + "<br></html>");

				lblAlternativa_1.setText("<html><b>" + "1 - " + pergunta2.getAlternativas1() + "<br></html>");
				lblAlternativa_2.setText("<html><b>" + "2 - " + pergunta2.getAlternativas2() + "<br></html>");
				lblAlternativa_3.setText("<html><b>" + "3 - " +  pergunta2.getAlternativas3() + "<br></html>");
				lblAlternativa_4.setText("<html><b>" + "4 - " +  pergunta2.getCorreta() + "<br></html>");
				lblAutor.setText(pergunta2.getAutor());
			}

		}

	}

	public Tela_Avaliar() {
		
		index = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Quiz by R&D");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setBounds(558, 76, 117, 55);
		contentPane.add(label);

		lblPergunta = new JLabel("Pergunta");
		lblPergunta.setBounds(360, 157, 563, 120);
		contentPane.add(lblPergunta);

		lblAlternativa_1 = new JLabel("Alternativa 1");
		lblAlternativa_1.setBounds(360, 274, 601, 28);
		contentPane.add(lblAlternativa_1);

		lblAlternativa_2 = new JLabel("Alternativa 2");
		lblAlternativa_2.setBounds(360, 313, 601, 28);
		contentPane.add(lblAlternativa_2);

		lblAlternativa_3 = new JLabel("Alternativa 3");
		lblAlternativa_3.setBounds(360, 357, 601, 28);
		contentPane.add(lblAlternativa_3);

		lblAlternativa_4 = new JLabel("Alternativa 4");
		lblAlternativa_4.setBounds(360, 393, 601, 28);
		contentPane.add(lblAlternativa_4);

		lblAnterior = new JLabel("");
		lblAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				index--;
				EscolherPerguntas(index);
			}
		});
		lblAnterior.setIcon(new ImageIcon(
				"C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_circle-back-arrow-glyph_763477.png"));
		lblAnterior.setBounds(512, 488, 75, 64);
		contentPane.add(lblAnterior);

		JLabel lblProximo = new JLabel("");
		lblProximo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index++;
				EscolherPerguntas(index);
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

			}
		});
		lblDesLike.setIcon(new ImageIcon(
				"C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\if_icons_exit_1564505.png"));
		lblDesLike.setBounds(699, 476, 69, 64);
		contentPane.add(lblDesLike);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_inicial t = new Tela_inicial();
				t.Run();
				dispose();

			}
		});
		btnVoltar.setBounds(640, 567, 89, 23);
		contentPane.add(btnVoltar);
		
		lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(558, 128, 120, 28);
		contentPane.add(lblCategoria);
		
		lblAutor = new JLabel();
		lblAutor.setBounds(709, 135, 75, 14);
		contentPane.add(lblAutor);
	}
}
