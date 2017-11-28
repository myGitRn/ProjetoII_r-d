package br.edu.facear.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.edu.facear.classes.Jogador;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Tela_Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JTextField txtLogin;
	private JTextField txtSenha;
	private Jogador jogador;

	
	public void Run(){
		this.setSize(437, 302);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	public Tela_Login() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(painel);
		painel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(87, 84, 35, 17);
		painel.add(lblLogin);
		lblLogin.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtLogin = new JTextField();
		txtLogin.setBounds(138, 83, 176, 20);
		painel.add(txtLogin);
		txtLogin.setColumns(20);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(83, 112, 52, 17);
		painel.add(lblSenha);
		lblSenha.setFont(new Font("Arial", Font.PLAIN, 15));
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(138, 111, 176, 20);
		painel.add(txtSenha);
		txtSenha.setColumns(20);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.setBounds(233, 141, 81, 25);
		painel.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(new Color(255, 255, 255));
		btnEntrar.setBounds(138, 141, 85, 25);
		painel.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jogador = new Jogador();
				String login = txtLogin.getText();
				String senha = txtSenha.getText();
				jogador.setLogin(login);
				jogador.setSenha(senha);
				if(jogador.Logar().equals("OK")){
					Tela_inicial tela = new Tela_inicial();
					tela.Run();
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null,"Login ou senha Inv�lido");		
			}
		});
		btnEntrar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JButton btnCadastrar = new JButton("Cadastre - se aqui");
		btnCadastrar.setBackground(new Color(255, 255, 255));
		btnCadastrar.setBounds(209, 190, 166, 25);
		painel.add(btnCadastrar);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela_Cadastrar cadastrar = new Tela_Cadastrar();
				cadastrar.Run();
				dispose();
			}
		});
		btnCadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblCadastrar = new JLabel("N�o � cadastrado");
		lblCadastrar.setBounds(87, 194, 112, 17);
		painel.add(lblCadastrar);
		lblCadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
	
		getRootPane().setDefaultButton(btnEntrar);
		
		JLabel label = new JLabel("by R&D");
		label.setBounds(240, 56, 46, 14);
		painel.add(label);
		
		JLabel label_1 = new JLabel("Quiz");
		label_1.setFont(new Font("Arial", Font.PLAIN, 40));
		label_1.setBounds(161, 11, 80, 61);
		painel.add(label_1);
		
		JLabel lblFundo = new JLabel("fundo");
		lblFundo.setIcon(new ImageIcon("C:\\Users\\ricar\\Desktop\\ProjetoII_r-d\\Projeto_Integrador\\img\\Fundo2.png"));
		lblFundo.setBounds(0, -25, 532, 330);
		painel.add(lblFundo);
	}
}
