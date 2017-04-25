package hr.tvz.programiranje.java.gui;

import hr.tvz.programiranje.java.banka.DevizniRacun;
import hr.tvz.programiranje.java.banka.Racun;
import hr.tvz.programiranje.java.banka.TekuciRacun;
import hr.tvz.programiranje.java.banka.Valuta;
import hr.tvz.programiranje.java.banka.VrstaRacuna;



import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import tvz.programiranje.java.osoba.Osoba;

public class UnosenjeNovogRacunaFrame extends JFrame {private JPanel contentPane;
	private JTextField textFieldStanjeRacuna;
	private JTextField textFieldBrojRacunaIban;

	/**
	 * Launch the application.
	 */

	
	public void prikaziEkran() 
	{ 
		EventQueue.invokeLater(new Runnable() 
		{ 
			public void run() 
			{ 
				try 
				{ 
					setVisible(true); 
					setResizable(false);
				} 
				catch (Exception e) 
				{ 
					e.printStackTrace(); 
				} 
			} 
		}); 
	}
	
	public UnosenjeNovogRacunaFrame(final List<Racun> listaRacuna,final List<Osoba> listaOsoba) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 381, 198);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		final JLabel labelBrojRacunaIban = new JLabel("Broj raèuna:");
		labelBrojRacunaIban.setBounds(10, 95, 119, 20);
		contentPane.add(labelBrojRacunaIban);
		
		final JLabel labelStanjeRacuna = new JLabel("Stanje raèuna:");
		labelStanjeRacuna.setBounds(10, 70, 119, 20);
		contentPane.add(labelStanjeRacuna);
		
		JLabel labelVrstaRacuna = new JLabel("Vrsta raèuna:");
		labelVrstaRacuna.setBounds(10, 11, 119, 20);
		contentPane.add(labelVrstaRacuna);
		
		final JComboBox<Valuta> comboBoxValuta = new JComboBox<>();
		for(Valuta valuta : Valuta.values())
		{
			comboBoxValuta.addItem(valuta);
		}
		comboBoxValuta.setBounds(280, 70, 80, 20);
		contentPane.add(comboBoxValuta);
		
		final JComboBox<VrstaRacuna> comboBoxVrstaRacuna = new JComboBox<>();
		comboBoxVrstaRacuna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VrstaRacuna odabranaVrstaRacuna = (VrstaRacuna)
						comboBoxVrstaRacuna.getSelectedItem();
				
				if(odabranaVrstaRacuna.equals(VrstaRacuna.DEVIZNI))
				{
					comboBoxValuta.setVisible(true);
					labelBrojRacunaIban.setText("IBAN:");
					labelStanjeRacuna.setText("Stanje raèuna:");
				}
				else
				{
					comboBoxValuta.setVisible(false); 
					labelBrojRacunaIban.setText("Broj ra\u010Duna:"); 
					labelStanjeRacuna.setText("Stanje ra\u010Duna (KN):");
				}
			}
		});
		
		for(VrstaRacuna vrstaRacuna : VrstaRacuna.values())
		{
			comboBoxVrstaRacuna.addItem(vrstaRacuna);
		}
		comboBoxVrstaRacuna.setBounds(139, 11, 131, 20);
		contentPane.add(comboBoxVrstaRacuna);
		
		JLabel labelVlasnikRacuna = new JLabel("Vlasnik ra\u010Duna:");
		labelVlasnikRacuna.setBounds(10, 39, 119, 20);
		contentPane.add(labelVlasnikRacuna);
		
		final JComboBox<Osoba> comboBoxVlasnikRacuna = new JComboBox<>();
		for(Osoba osoba : listaOsoba)
		{
			comboBoxVlasnikRacuna.addItem(osoba);
		}
		comboBoxVlasnikRacuna.setBounds(139, 39, 131, 20);
		contentPane.add(comboBoxVlasnikRacuna);
		
		
		
		textFieldStanjeRacuna = new JTextField();
		textFieldStanjeRacuna.setBounds(139, 70, 131, 20);
		contentPane.add(textFieldStanjeRacuna);
		textFieldStanjeRacuna.setColumns(10);
		
		
		
		
		
		textFieldBrojRacunaIban = new JTextField();
		textFieldBrojRacunaIban.setBounds(139, 95, 131, 20);
		contentPane.add(textFieldBrojRacunaIban);
		textFieldBrojRacunaIban.setColumns(10);
		
		JButton buttonSpremiRacuna = new JButton("Spremi ra\u010Dun");
		buttonSpremiRacuna.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VrstaRacuna odabranaVrstaRacuna = (VrstaRacuna)
						comboBoxVrstaRacuna.getSelectedItem();
				if(odabranaVrstaRacuna.equals(VrstaRacuna.DEVIZNI))
				{
					String iban = textFieldBrojRacunaIban.getText();
					Valuta valuta = (Valuta) comboBoxValuta.getSelectedItem();
					BigDecimal stanjeRacuna = new BigDecimal( textFieldStanjeRacuna .getText()); 
					Osoba vlasnikRacuna = (Osoba) comboBoxVlasnikRacuna.getSelectedItem(); 
					DevizniRacun devizni = new DevizniRacun(vlasnikRacuna, stanjeRacuna,iban, valuta);
					listaRacuna.add(devizni); 
					dispose();
					JOptionPane.showMessageDialog (null , "Uspješno ste dodali novi devizni raèun: " + iban);		
					GlavniEkran.osvjeziPopisRacuna();
					
				}
				else
				{
					String brojRacuna = textFieldBrojRacunaIban.getText(); 
					BigDecimal stanjeRacuna = new BigDecimal( textFieldStanjeRacuna .getText());
					Osoba vlasnikRacuna = (Osoba) comboBoxVlasnikRacuna.getSelectedItem(); 
					TekuciRacun tekuci = new TekuciRacun( vlasnikRacuna,stanjeRacuna, brojRacuna); 
					listaRacuna.add(tekuci); 
					dispose();
					JOptionPane.showMessageDialog (null , "Uspješno ste dodali novi tekuæi raèun: " + brojRacuna); 
					GlavniEkran.osvjeziPopisRacuna();
					
					
				}
				
			}
		});
		buttonSpremiRacuna.setBounds(149, 126, 111, 23);
		contentPane.add(buttonSpremiRacuna);
	}
}
