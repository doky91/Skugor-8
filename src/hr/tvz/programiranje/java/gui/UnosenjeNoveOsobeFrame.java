package hr.tvz.programiranje.java.gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import tvz.programiranje.java.osoba.Osoba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UnosenjeNoveOsobeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldOib;

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
					setVisible( true ); 
					setResizable(false);
				} 
				catch (Exception e) 
				{ 
					e.printStackTrace(); 
				} 
			} 
		}); 
	}
				
	/**
	 * Create the frame.
	 */
	public UnosenjeNoveOsobeFrame(final List<Osoba> listaOsoba) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 249, 168);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelPrezime = new JLabel("Prezime:");
		labelPrezime.setBounds(5, 11, 50, 20);
		contentPane.add(labelPrezime);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setBounds(62, 11, 150, 20);
		contentPane.add(textFieldPrezime);
		textFieldPrezime.setColumns(10);
		
		JLabel labelIme = new JLabel("Ime:");
		labelIme.setBounds(5, 42, 50, 20);
		contentPane.add(labelIme);
		
		textFieldIme = new JTextField();
		textFieldIme.setBounds(62, 42, 150, 20);
		contentPane.add(textFieldIme);
		textFieldIme.setColumns(10);
		
		JLabel labelOib = new JLabel("OIB:");
		labelOib.setBounds(5, 75, 50, 20);
		contentPane.add(labelOib);
		
		textFieldOib = new JTextField();
		textFieldOib.setBounds(62, 73, 150, 20);
		contentPane.add(textFieldOib);
		textFieldOib.setColumns(10);
		
		JButton buttonSpremi = new JButton("Spremi");
		buttonSpremi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String prezime = textFieldPrezime.getText(); 
				String ime = textFieldIme.getText(); 
				String oib = textFieldOib.getText(); 
				Osoba novaOsoba = new Osoba(prezime, ime, oib); 
				listaOsoba.add(novaOsoba); 
				dispose(); 
				JOptionPane.showMessageDialog(null, "Uspješno ste unijeli osobu: " + prezime + " " + ime); 
			}
		});
		buttonSpremi.setBounds(72, 104, 89, 23);
		contentPane.add(buttonSpremi);
	}
	
}
