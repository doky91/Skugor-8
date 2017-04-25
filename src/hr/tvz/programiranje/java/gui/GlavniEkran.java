package hr.tvz.programiranje.java.gui;

import hr.tvz.programiranje.java.banka.DeviznaTransakcija;
import hr.tvz.programiranje.java.banka.DevizniRacun;
import hr.tvz.programiranje.java.banka.Racun;
import hr.tvz.programiranje.java.banka.TekuciRacun;
import hr.tvz.programiranje.java.banka.Transakcija;
import hr.tvz.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;



import hr.tvz.programiranje.java.iznimke.NepodrzanaValutaException;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;

import org.slf4j.LoggerFactory;

import tvz.programiranje.java.osoba.Osoba;
import ch.qos.logback.classic.Logger;

import java.awt.ScrollPane;

public class GlavniEkran {

	private JFrame frmTvzBankarstvo; 
	private List<Osoba> listaOsoba; 
	private static List<Racun> listaRacuna;
	private JTextField iznosTransakcijeTextField;
	private JTable transakcijaTable;
	
	private static JComboBox prviRacunComboBox; 
	private static JComboBox drugiRacunComboBox ; 
	private static JPanel stanjePrvogRacunaPanel; 
	private static JPanel stanjeDrugogRacunaPanel; 
	private static final Logger logger = (Logger) LoggerFactory.getLogger(GlavniEkran.class); 
	private static JTable transakcijeTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniEkran window = new GlavniEkran();
					window.frmTvzBankarstvo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public static void osvjeziPopisRacuna() 
	{
		prviRacunComboBox.removeAllItems();
		drugiRacunComboBox.removeAllItems();
		for(Racun racun : listaRacuna) 
		{
			prviRacunComboBox.addItem(racun);
			drugiRacunComboBox.addItem(racun);
		}
	}
	
	public static void dodajTransakcijuUTablicu(Racun polazni, Racun dolazni, BigDecimal iznos) 
	{
			Object[] podaciUTablici = new Object[5];
			podaciUTablici[0] = transakcijeTable.getRowCount() + 1 + ".";
			podaciUTablici[1] = ((TekuciRacun) polazni).getBrojRacuna();
			
			if(dolazni instanceof TekuciRacun) 
			{
				podaciUTablici[2] = ((TekuciRacun) dolazni).getBrojRacuna();
			}
			else if (dolazni instanceof DevizniRacun) 
			{
				podaciUTablici[2] = ((DevizniRacun) dolazni).getIban();
			}
			
			podaciUTablici[3] = iznos.toString();
			podaciUTablici[4] = "KN";
			
			((DefaultTableModel)transakcijeTable.getModel()).addRow(podaciUTablici);
			
	}
	
	public GlavniEkran() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		listaOsoba = new ArrayList<Osoba>(); 
		listaRacuna = new ArrayList<Racun>();
		
		frmTvzBankarstvo = new JFrame();
		frmTvzBankarstvo.setResizable(false);
		frmTvzBankarstvo.setBounds(100, 100, 477, 319);
		frmTvzBankarstvo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTvzBankarstvo.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 461, 21);
		frmTvzBankarstvo.getContentPane().add(menuBar);
		JMenu menuOsobe = new JMenu("Osobe");
		menuBar.add(menuOsobe);
		
		JMenuItem dodajNovuOsobu = new JMenuItem("Dodaj novu osobu");
		dodajNovuOsobu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosenjeNoveOsobeFrame frame = new UnosenjeNoveOsobeFrame(listaOsoba); 
				frame.prikaziEkran(); 
			}
		});
		menuOsobe.add(dodajNovuOsobu);
		
		JMenu menuRacuni = new JMenu("Ra\u010Duni");
		menuBar.add(menuRacuni);
		
		JMenuItem dodajNoviRacun = new JMenuItem("Dodaj novi ra\u010Dun");
		dodajNoviRacun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UnosenjeNovogRacunaFrame frame = new UnosenjeNovogRacunaFrame(listaRacuna,listaOsoba); 
				frame.prikaziEkran(); 
			}
		});
		menuRacuni.add(dodajNoviRacun);
		
		JPanel racuniPanel = new JPanel();
		racuniPanel.setBounds(0, 21, 471, 259);
		frmTvzBankarstvo.getContentPane().add(racuniPanel);
		racuniPanel.setLayout(null);
		
		JPanel panelPrviRacun = new JPanel();
		panelPrviRacun.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelPrviRacun.setBounds(10, 5, 218, 92);
		racuniPanel.add(panelPrviRacun);
		panelPrviRacun.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Polazni ra\u010Dun");
		lblNewLabel.setBounds(68, 1, 79, 20);
		panelPrviRacun.add(lblNewLabel);
		
		final JPanel stanjePrvogRacunaPanel = new JPanel();
		stanjePrvogRacunaPanel.setBounds(47, 26, 100, 24);
		panelPrviRacun.add(stanjePrvogRacunaPanel);
		
		final JLabel stanjePrvogRacunaLabel = new JLabel("");
		stanjePrvogRacunaPanel.add(stanjePrvogRacunaLabel);
		stanjePrvogRacunaPanel.setVisible(false);
		
		final JLabel valutaPrvogRacunaLabel = new JLabel("");
		stanjePrvogRacunaPanel.add(valutaPrvogRacunaLabel);
		
		JLabel stanjePrvogRacunaTextLabel = new JLabel("Stanje ra\u010Duna:");
		stanjePrvogRacunaPanel.add(stanjePrvogRacunaTextLabel);
		
		prviRacunComboBox = new JComboBox();
		prviRacunComboBox.setBounds(10, 50, 198, 20);
		panelPrviRacun.add(prviRacunComboBox);
		
		JPanel panelDrugiRacun = new JPanel();
		panelDrugiRacun.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelDrugiRacun.setBounds(238, 5, 223, 92);
		racuniPanel.add(panelDrugiRacun);
		panelDrugiRacun.setLayout(null);
		
		final JPanel stanjeDrugogRacunaPanel = new JPanel();
		stanjeDrugogRacunaPanel.setBounds(47, 26, 91, 24);
		panelDrugiRacun.add(stanjeDrugogRacunaPanel);
		stanjeDrugogRacunaPanel.setVisible(false);
		
		JLabel stanjeDrugogRacunaTextLabel = new JLabel("Stanje ra\u010Duna:");
		stanjeDrugogRacunaPanel.add(stanjeDrugogRacunaTextLabel);
		
		final JLabel stanjeDrugogRacunaLabel = new JLabel("");
		stanjeDrugogRacunaPanel.add(stanjeDrugogRacunaLabel);
		
		final JLabel valutaDrugogRacunaLabel = new JLabel("");
		stanjeDrugogRacunaPanel.add(valutaDrugogRacunaLabel);
		
		drugiRacunComboBox = new JComboBox();
		drugiRacunComboBox.setBounds(10, 50, 203, 20);
		panelDrugiRacun.add(drugiRacunComboBox);
		
		JLabel lblDolazniRaun = new JLabel("Dolazni ra\u010Dun");
		lblDolazniRaun.setBounds(68, 1, 83, 20);
		panelDrugiRacun.add(lblDolazniRaun);
		
		JPanel iznosTransakcijePanel = new JPanel();
		iznosTransakcijePanel.setBounds(10, 98, 451, 33);
		racuniPanel.add(iznosTransakcijePanel);
		
		JLabel unosIznosaTextLabel = new JLabel("Iznos transakcije:");
		iznosTransakcijePanel.add(unosIznosaTextLabel);
		
		iznosTransakcijeTextField = new JTextField();
		iznosTransakcijePanel.add(iznosTransakcijeTextField);
		iznosTransakcijeTextField.setColumns(10);
		
		JLabel iznosTransakcijeValutaLabel = new JLabel("KN");
		iznosTransakcijePanel.add(iznosTransakcijeValutaLabel);
		
		JButton izvrsiTransakcijuButton = new JButton("Izvr\u0161i transakciju");
		iznosTransakcijePanel.add(izvrsiTransakcijuButton);
		
		JPanel popisTransakcijaPanel = new JPanel();
		popisTransakcijaPanel.setBounds(10, 142, 451, 117);
		racuniPanel.add(popisTransakcijaPanel);
	
		prviRacunComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Racun prviRacun = (Racun) prviRacunComboBox.getSelectedItem();
				if(prviRacun == null) 
				{
					prviRacun = listaRacuna.get(0);
				}
				if(prviRacun instanceof TekuciRacun) 
				{
					stanjePrvogRacunaLabel.setText(prviRacun.getStanje().toString());
					valutaPrvogRacunaLabel.setText("KN");
				}
				else 
				{
					stanjePrvogRacunaLabel.setText(prviRacun.getStanje().toString());
					valutaPrvogRacunaLabel.setText(((DevizniRacun)prviRacun).getValuta().toString());
				}
				
				stanjePrvogRacunaPanel.setVisible(true);
			}
		});
		
		drugiRacunComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Racun drugiRacun = (Racun) drugiRacunComboBox.getSelectedItem();
				if(drugiRacun == null) 
				{
					drugiRacun = listaRacuna.get(0);
				}
				if(drugiRacun instanceof TekuciRacun) 
				{
					stanjeDrugogRacunaLabel.setText(drugiRacun.getStanje().toString());
					valutaDrugogRacunaLabel.setText("KN");
				}
				else 
				{
					stanjeDrugogRacunaLabel.setText(drugiRacun.getStanje().toString());
					valutaDrugogRacunaLabel.setText(((DevizniRacun)drugiRacun).getValuta().toString());
				}
				
				stanjeDrugogRacunaPanel.setVisible(true);
			}
		});
		
		transakcijeTable = new JTable();
		transakcijeTable.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"Rbr.", "Polazni ra\u010Dun", "Dolazni ra\u010Dun", "Iznos", "Valuta"
						}
		));
		
		
		izvrsiTransakcijuButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
			Racun prviRacun = (Racun) prviRacunComboBox.getSelectedItem();
			Racun drugiRacun = (Racun) drugiRacunComboBox.getSelectedItem();
			
			if (prviRacun instanceof TekuciRacun && drugiRacun instanceof TekuciRacun)
			{
				Transakcija<Racun,Racun> transakcija = new Transakcija<>(prviRacun, drugiRacun, new BigDecimal(iznosTransakcijeTextField.getText()));
				try 
				{
					transakcija.provediTransakciju();
					dodajTransakcijuUTablicu(prviRacun, drugiRacun,transakcija.getIznosTransakcije());
					stanjePrvogRacunaLabel.setText(prviRacun.getStanje().toString());
					stanjeDrugogRacunaLabel.setText(drugiRacun.getStanje().toString());
				}
				catch(NedozvoljenoStanjeRacunaException ex) 
				{
					String message ="Transakcija se nije provela! Nedozvoljeno stanje raèuna!";
					System.out.println(message);
					JOptionPane.showMessageDialog(null, message);
					logger.error(message, ex);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NepodrzanaValutaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			else if (prviRacun instanceof TekuciRacun && drugiRacun instanceof DevizniRacun) 
			{
				DeviznaTransakcija<TekuciRacun,DevizniRacun> transakcija =new DeviznaTransakcija<>((TekuciRacun)prviRacun,(DevizniRacun)drugiRacun, new BigDecimal(iznosTransakcijeTextField.getText()));
				try 
				{
					transakcija.provediTransakciju();
					dodajTransakcijuUTablicu(prviRacun, drugiRacun,transakcija.getIznosTransakcije());
					stanjePrvogRacunaLabel.setText(prviRacun.getStanje().toString());
					stanjeDrugogRacunaLabel.setText(drugiRacun.getStanje().toString());
				}
				catch(NedozvoljenoStanjeRacunaException ex) 
				{
					String message ="Transakcija se nije provela! Nedozvoljeno stanje raèuna!";
					System.out.println(message);
					JOptionPane.showMessageDialog(null, message);
					logger.error(message, ex);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NepodrzanaValutaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else 
			{
			JOptionPane.showMessageDialog(null,
			"Odabrali ste nepodržanu transakciju!");
			}
		}}); 
		
		transakcijeTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		transakcijeTable.getColumnModel().getColumn(0).setMinWidth(35);
		transakcijeTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		transakcijeTable.setPreferredScrollableViewportSize(new Dimension (450, 85));
		JScrollPane transakcijeTableScrollPane = new JScrollPane(transakcijeTable);
		popisTransakcijaPanel.add(transakcijeTableScrollPane);
	}
}
