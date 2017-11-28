package br.edu.facear.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import br.edu.facear.classes.Categoria;
import br.edu.facear.classes.Jogador;
import br.edu.facear.classes.Pergunta;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Tela_Cadastrar_Pergunta extends JFrame {


	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel lblUsuario;
	private Pergunta pergunta;

	public void Run(){
		this.setSize(1144, 762);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);
	
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Tela_Cadastrar_Pergunta() {
		setTitle("Cadastrar Pergunta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1144, 762);
		contentPane = new JPanel();
		contentPane.setToolTipText("Selecione a categoria");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo1 = new JLabel("Quiz");
		lblTitulo1.setFont(new Font("Arial", Font.PLAIN, 40));
		lblTitulo1.setBounds(621, 13, 80, 61);
		contentPane.add(lblTitulo1);
		
		JLabel lblTitulo2 = new JLabel("by R&D");
		lblTitulo2.setBounds(700, 58, 46, 14);
		contentPane.add(lblTitulo2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new Categoria().listaCategoria()));
		comboBox.setBounds(385, 127, 598, 20);
		contentPane.add(comboBox);
		
		JLabel lblQuestao = new JLabel("Digite a Pergunta:");
		lblQuestao.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuestao.setBounds(387, 159, 131, 20);
		contentPane.add(lblQuestao);
		
		JLabel lblAlternativaCorreta = new JLabel("Digite a alternativa correta:");
		lblAlternativaCorreta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlternativaCorreta.setBounds(386, 290, 187, 20);
		contentPane.add(lblAlternativaCorreta);
		
		JLabel lblIncorreta1 = new JLabel("Digite a alternativa incorreta:");
		lblIncorreta1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIncorreta1.setBounds(385, 376, 238, 20);
		contentPane.add(lblIncorreta1);
		
		JLabel lblIncorreta2 = new JLabel("Digite a alternativa incorreta:");
		lblIncorreta2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIncorreta2.setBounds(383, 463, 234, 20);
		contentPane.add(lblIncorreta2);
		
		JLabel lblIncorreta3 = new JLabel("Digite a alternativa incorreta:");
		lblIncorreta3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIncorreta3.setBounds(384, 545, 207, 20);
		contentPane.add(lblIncorreta3);
		
		JTextArea txtAPergunta = new JTextArea();
		txtAPergunta.setLineWrap(true);
		txtAPergunta.setBounds(387, 180, 598, 105);
		contentPane.add(txtAPergunta);
		
		JTextArea txtACorreta = new JTextArea();
		txtACorreta.setLineWrap(true);
		txtACorreta.setBounds(385, 308, 598, 65);
		contentPane.add(txtACorreta);
		
		JTextArea txtAIncorreta1 = new JTextArea();
		txtAIncorreta1.setLineWrap(true);
		txtAIncorreta1.setBounds(385, 396, 598, 65);
		contentPane.add(txtAIncorreta1);
		
		JTextArea txtAIncorreta2 = new JTextArea();
		txtAIncorreta2.setLineWrap(true);
		txtAIncorreta2.setBounds(384, 482, 598, 61);
		contentPane.add(txtAIncorreta2);
		
		JTextArea txtAIncorreta3 = new JTextArea();
		txtAIncorreta3.setLineWrap(true);
		txtAIncorreta3.setBounds(384, 565, 598, 62);
		contentPane.add(txtAIncorreta3);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pergunta = new Pergunta();
				
				String questao = txtAPergunta.getText();
				String correta = txtACorreta.getText(); 
				String incorreta1 = txtAIncorreta1.getText();
				String incorreta2 = txtAIncorreta2.getText();
				String incorreta3 = txtAIncorreta3.getText();
				
				pergunta.setCategoria((Categoria) comboBox.getSelectedItem());
				pergunta.setPergunta(questao);
				pergunta.setCorreta(correta);
				pergunta.setAlternativas1(incorreta1);
				pergunta.setAlternativas2(incorreta2);
				pergunta.setAlternativas3(incorreta3);
				pergunta.setJogador(Jogador.getInstance());
				
				if(pergunta.Cadastrar(true).equals("OK")) {
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
					
					txtAPergunta.setText(" ");
					txtACorreta.setText(" ");
					txtAIncorreta1.setText("");
					txtAIncorreta2.setText("");
					txtAIncorreta3.setText("");
					
				}else {
					JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
				}
				
			}
		});
		btnSalvar.setBounds(576, 638, 131, 36);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Voltar");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_inicial inicio = new Tela_inicial();
				inicio.Run();
				dispose();
			}
		});
		btnCancelar.setBounds(852, 639, 131, 36);
		contentPane.add(btnCancelar);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(605, 89, 46, 27);
		contentPane.add(lblAutor);
		
		lblUsuario = new JLabel(Jogador.getInstance().getLogin().toUpperCase());
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(661, 89, 238, 27);
		contentPane.add(lblUsuario);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBackground(Color.WHITE);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtAPergunta.setText(" ");
				txtAIncorreta1.setText(" ");
				txtAIncorreta2.setText(" ");
				txtAIncorreta3.setText(" ");
								
			}
		});
		btnLimpar.setBounds(715, 638, 127, 36);
		contentPane.add(btnLimpar);
		
		JLabel lblFundo = new JLabel("Fundo");
		lblFundo.setIcon(new ImageIcon("C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\Fundo.png"));
		lblFundo.setBounds(10, 0, 2534, 768);
		contentPane.add(lblFundo);
		
	}
}
