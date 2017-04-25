package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.iznimke.NepodrzanaValutaException;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



public class Tecajnica {
	

		public static List<Tecaj> dohvatiTecajeve() throws IOException, NepodrzanaValutaException{

			List<Tecaj> listaTecaja = new ArrayList<>();
			InputStream in=null;
			URL u = null;
		
		try{
			u = new URL("http://www.hnb.hr/tecajn/f161113.dat");
		}catch (MalformedURLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		try {
			in = u.openStream();
		} catch (IOException e2) {
			System.err.println(e2);
			e2.printStackTrace();
		} 
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));	
			String line = "";
			try {
				line = reader.readLine ();
			} catch (IOException e) {
				System.err.println(e);
				e.printStackTrace();
			}
			
			try{
				while ((line = reader.readLine()) != null) {
			
			//System.out.println(line);
			String valuta=line.substring(3,6);
			
			 Valuta nova=Valuta.valueOf(valuta);
			 StringTokenizer tokenizer = new StringTokenizer(line, " ");
			 tokenizer.nextToken();
			 tokenizer.nextToken();
			 String tecaj = tokenizer.nextToken();

			 String novi= tecaj.replace(",", ".");
			 BigDecimal izmjenjen =new BigDecimal(novi);
			  
				
				Tecaj tecajNovi=new Tecaj(nova,izmjenjen);
			 listaTecaja.add(tecajNovi);
			 
			 
			}
			
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		}
			return listaTecaja;
			
			
			}
		
		}
		


