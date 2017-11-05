package br.edu.facear.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.facear.classes.Jogador;
import br.edu.facear.classes.Ranking;

public class Tela_Ranking extends JFrame {


	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable tblSemana;

	public void Run() {
		this.setSize(1144, 762);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_BOTH);
		
	}
	
	public void preencherTabela() {
		Jogador j = new Jogador();
		if (tblSemana != null) {
			DefaultTableModel modelo = (DefaultTableModel) tblSemana.getModel();
			if (modelo.getRowCount() > 0) {
				modelo.setRowCount(0);
			}
	
			try {
				Ranking r = new Ranking();
				List<Jogador> lista =  r.GerenciarRanking();
				Collections.sort(lista, new Ranking());
				for (int i = 0; i < lista.size(); i++) {
					Object[] linha = new Object[2];
					linha[0] = lista.get(i).getNome();
					linha[1] = lista.get(i).getPontos();
					modelo.addRow(linha);
					
			}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	
	public Tela_Ranking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMelhoresDaSemana = new JLabel("Melhores da semana");
		lblMelhoresDaSemana.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMelhoresDaSemana.setBounds(432, 127, 174, 14);
		contentPane.add(lblMelhoresDaSemana);
		
		JLabel lblRanking = new JLabel("RANKING");
		lblRanking.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRanking.setBounds(680, 62, 127, 14);
		contentPane.add(lblRanking);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(432, 186, 274, 320);
		contentPane.add(scrollPane);
		
		tblSemana = new JTable();
		scrollPane.setViewportView(tblSemana);
		tblSemana.setBorder(new LineBorder(new Color(0, 0, 0)));
		DefaultTableModel modelo = new DefaultTableModel(new Object[][] {}, new String[]{"Nome","Pontos"});
		scrollPane.setViewportView(tblSemana);
		tblSemana.setModel(modelo);
		tblSemana.setEnabled(false);
		tblSemana.getColumnModel().getColumn(0).setPreferredWidth(120);
		tblSemana.getColumnModel().getColumn(1).setPreferredWidth(120);
		
		preencherTabela();
				
		JLabel lblSuaPosio = new JLabel("Sua posi\u00E7\u00E3o \u00E9:");
		lblSuaPosio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSuaPosio.setBounds(432, 152, 174, 23);
		contentPane.add(lblSuaPosio);
		
		JLabel lblMelhoresDoMs = new JLabel("Melhores do m\u00EAs");
		lblMelhoresDoMs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMelhoresDoMs.setBounds(783, 127, 174, 14);
		contentPane.add(lblMelhoresDoMs);
		
		JLabel label = new JLabel("Sua posi\u00E7\u00E3o \u00E9:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(783, 152, 174, 23);
		contentPane.add(label);
		
		JButton btnSair = new JButton("Voltar");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_inicial t = new Tela_inicial();
				t.Run();
				dispose();
				
			}
		});
		btnSair.setBounds(690, 555, 104, 31);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(783, 186, 274, 320);
		contentPane.add(scrollPane_1);
	
		getRootPane().setDefaultButton(btnSair);
		
	
					
	}
}
