package hr.tvz.programiranje.java.glavna;

import hr.tvz.programiranje.java.banka.DeviznaTransakcija;
import hr.tvz.programiranje.java.banka.DevizniRacun;
import hr.tvz.programiranje.java.banka.Racun;
import hr.tvz.programiranje.java.banka.TekuciRacun;
import hr.tvz.programiranje.java.banka.Transakcija;
import hr.tvz.programiranje.java.banka.Valuta;
import hr.tvz.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;
import hr.tvz.programiranje.java.iznimke.NepodrzanaValutaException;
import hr.tvz.programiranje.java.sortiranje.SortiranjeTransakcija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tvz.programiranje.java.osoba.Osoba;


public class Glavna {
	

	private static final Logger logger = LoggerFactory. getLogger (Glavna. class );
	public static final String FILE_NAME = "uneseniPodaci.txt";
	
	


	public static void zapisiUDatoteku(FileWriter fout,String poruka) throws IOException{
		fout.write(poruka);	
		fout.flush();
	}


	
	public static TekuciRacun unosTekucegRacuna(Scanner upisi,int redniBroj) throws IOException{
		redniBroj++;
		FileWriter fout = new FileWriter(FILE_NAME, true);
		System.out.printf("Unesite ime vlasnika "+ redniBroj+".raèuna: ");
		String ime=upisi.nextLine();
       zapisiUDatoteku(fout,"Ime vlasnika "+redniBroj+ " .racuna:" +ime+ "\n");
		logger.info( "Uneseno ime vlasnika prvog raèuna: " + ime);
		
		System.out.printf("Unesite prezime vlasnika " +redniBroj+ ".raèuna: ");
		String prezime=upisi.nextLine();
		logger.info( "Uneseno prezime vlasnika " +redniBroj+ ".raèuna: " + prezime);
		  zapisiUDatoteku(fout,"Prezime vlasnika "+redniBroj+ " .racuna:" +prezime+ "\n");
		System.out.printf("Unesite OIB vlasnika " +redniBroj+ ".raèuna: ");
		String OIB=upisi.nextLine();
		  zapisiUDatoteku(fout,"OIB vlasnika "+redniBroj+ " .racuna:" +OIB+ "\n");
		logger.info( "Unesen OIB vlasnika " +redniBroj+ ".raèuna: " + OIB);
		
		Osoba prvi=new Osoba(ime,prezime,OIB);
	
		boolean error=false;
		BigDecimal iznos=null;
		do{
			error=false;
			System.out.println("Unesite stanje " +redniBroj+ ".raèuna:");
			
			
			try{
				iznos=upisi.nextBigDecimal();
			
			}
			catch(InputMismatchException ex){
				error=true;
				logger.error ("Unesen neispravan iznos za stanje " +redniBroj+ ". raèuna: " + iznos,ex);
				upisi.nextLine();	
			}		
		}while(error==true);
		zapisiUDatoteku(fout,"Stanje "+redniBroj+ ".racuna:" +iznos+ " KN"+"\n");
		System.out.printf("Unesite broj " +redniBroj+ ". raèuna: ");
		String brRac=upisi.nextLine();
		  zapisiUDatoteku(fout,"Broj "+redniBroj+ " .racuna:" +brRac+ "\n");
		logger.info( "Unesen je broj prvog raèuna: " + brRac);
		
		upisi.nextLine();
		TekuciRacun prvog=new TekuciRacun(prvi,iznos,brRac);
	
		return prvog;
	}
	
	
	public static DevizniRacun unosDeviznogRacuna(Scanner upisi,int redniBroj) throws IOException{
		redniBroj++;
		FileWriter fout = new FileWriter(FILE_NAME, true);
		System.out.printf("Unesite ime vlasnika " +redniBroj+ ".raèuna: ");
		String imee=upisi.nextLine();
		logger.info( "Uneseno ime vlasnika " +redniBroj+ ". raèuna: " + imee);
		zapisiUDatoteku(fout,"Ime vlasnika"+redniBroj+ " .racuna:" +imee+ "\n");
		System.out.printf("Unesite prezime vlasnika " +redniBroj+ ". raèuna: ");
		String prezimee=upisi.nextLine();
		zapisiUDatoteku(fout,"Prezime vlasnika"+redniBroj+ " .racuna:" +prezimee+ "\n");
		logger.info( "Uneseno prezime vlasnika drugog raèuna: " + prezimee);

		System.out.printf("Unesite OIB vlasnika " +redniBroj+ ". raèuna: ");
		String OIBe=upisi.nextLine();
		logger.info( "Unesen OIB vlasnika drugog raèuna: " + OIBe);
		Osoba drugi=new Osoba(imee,prezimee,OIBe);
		zapisiUDatoteku(fout,"OIB vlasnika"+redniBroj+ " .racuna:" +OIBe+ "\n");
		System.out.printf("Unesite IBAN " +redniBroj+ ". raèuna: ");
		String IBAN=upisi.nextLine();
		logger.info( "Unesen IBAN vlasnika drugog raèuna: " + IBAN);
		 zapisiUDatoteku(fout,"IBAN vlasnika"+redniBroj+ " .racuna:" +IBAN+ "\n");

		
		
		boolean errore=false;
		String valuta=null;
		Valuta val2=null;
		do{
			errore=false;
			System.out.println("Unesite valutu " +redniBroj+ ". raèuna:");
			try{
				valuta=upisi.nextLine();
				
				val2=DeviznaTransakcija.provjeriValutu(valuta);
				
			}
			catch(NepodrzanaValutaException ex){
				errore=true;
				logger.error ("Unesena neispravna valuta: ",ex);
				System.out.println("Unesena neispravna valuta!");
					
			}		
		}while(errore==true);
		 zapisiUDatoteku(fout,"Valuta "+redniBroj+ " .racuna:" +IBAN+ "\n");

		boolean errore1=false;
		BigDecimal iznose=null;
		do{
			errore1=false;
			System.out.println("Unesite stanje " +redniBroj+ ".raèuna:");
			
			try{
				iznose=upisi.nextBigDecimal();
			}
			catch(InputMismatchException ex){
				errore1=true;
				logger.error ("Unesen neispravan iznos za stanje drugog raèuna: " + iznose,ex);
				upisi.nextLine();	
			}		
		}while(errore1==true);
		
		DevizniRacun drugog=new DevizniRacun(drugi,iznose,IBAN,val2);
		upisi.nextLine();
		return drugog;	
	}
	
	
	

	public static void main(String[] args) throws NepodrzanaValutaException {
		try(FileWriter fout = new FileWriter(FILE_NAME, true)){
		
		Scanner upisi=new Scanner(System.in);
		List<Racun> novaLista = new ArrayList<Racun>();
		Boolean zastavica=true;
		TekuciRacun prvog;
		Racun drugog;
		
		
		
		do{
			int i=0;
		
		while(i<2){
			System.out.println("Unesite vrstu raèuna (T- tekuæi; ostalo-devizni):");
		
		String vrsta=upisi.nextLine();
		if(vrsta.equals("T")) {
			
			novaLista.add(unosTekucegRacuna(upisi,i));
			 zapisiUDatoteku(fout,"Vrsta "+(i+1)+".racuna:"+vrsta+ "\n");

		i++;}
		else {
			novaLista.add(unosDeviznogRacuna(upisi,i));
			 zapisiUDatoteku(fout,"Vrsta "+(i+1)+".racuna:"+vrsta+ "\n");
			i++;}
		}
		if(novaLista.get(0) instanceof TekuciRacun && novaLista.get(1) instanceof TekuciRacun ||
				(novaLista.get(0) instanceof TekuciRacun && novaLista.get(1) instanceof DevizniRacun ))
			zastavica=false;
		else 
			System.out.println("Oba raèuna moraju biti tekuæi ili prvi tekuæi, a drugi devizni!"
					+ " Morate ponovno unijeti oba raèuna.");
	
	} while(zastavica==true);
		
		
		SortedSet setTransakcija=new TreeSet<>(new SortiranjeTransakcija());
		
		System.out.printf("Unesite iznos transakcije u KN s prvog raèuna: ");
		BigDecimal transakcija=upisi.nextBigDecimal();
		
	prvog=(TekuciRacun)novaLista.get(0);
	drugog=novaLista.get(1);
	 zapisiUDatoteku(fout,"Iznos transakcije: "+transakcija+ " KN"+"\n");

		try{
			
			if(novaLista.get(1) instanceof DevizniRacun){
				
				DeviznaTransakcija<TekuciRacun,DevizniRacun> transakcija1 = new DeviznaTransakcija<TekuciRacun, DevizniRacun>((TekuciRacun) prvog,(DevizniRacun) drugog,transakcija);
				transakcija1.provediTransakciju();
				setTransakcija.add(transakcija1);
			}
			
			else {
				
				DeviznaTransakcija<TekuciRacun,DevizniRacun> transakcija3 = new DeviznaTransakcija<TekuciRacun, DevizniRacun>((TekuciRacun) prvog,(DevizniRacun) drugog,transakcija);
				transakcija3.provediTransakciju();
				setTransakcija.add(transakcija3);
				
			}
		

		}
		catch(NedozvoljenoStanjeRacunaException ex){
			
			logger.error ("Racun " + prvog.getBrojRacuna()+ " nema ("+prvog.getStanje()+") dovoljno sredstava!",ex);
			System.out.println ("Racun " + prvog.getBrojRacuna()+ " nema dovoljno sredstava!");
			}		
	
		Boolean provjera=true;
		do{
			upisi.nextLine();
			System.out.println("Želite li još?");
			String zeli=upisi.nextLine();
			if(zeli.equals("D")){
				{System.out.printf("Unesite iznos transakcije u KN s prvog raèuna: ");
				BigDecimal transakcija2=upisi.nextBigDecimal();
				 zapisiUDatoteku(fout,"Iznos transakcije: "+transakcija2+ " KN"+"\n");

				try{
				
			
			if(novaLista.get(1) instanceof DevizniRacun){
				DeviznaTransakcija<TekuciRacun,DevizniRacun> tran = new DeviznaTransakcija<TekuciRacun, DevizniRacun>((TekuciRacun) prvog,(DevizniRacun) drugog,transakcija2);
				tran.provediTransakciju();
				setTransakcija.add(tran);
			}
				
			else {
				
				DeviznaTransakcija<TekuciRacun,DevizniRacun> transakcija3 = new DeviznaTransakcija<TekuciRacun, DevizniRacun>((TekuciRacun) prvog,(DevizniRacun) drugog,transakcija);
				transakcija3.provediTransakciju();
				setTransakcija.add(transakcija3);
				
			}
				}
		catch(NedozvoljenoStanjeRacunaException ex){
			
			logger.error ("Racun " + prvog.getBrojRacuna()+ " nema ("+prvog.getStanje()+") dovoljno sredstava!",ex);
			System.out.println ("Racun " + prvog.getBrojRacuna()+ " nema dovoljno sredstava!");
			}
				}}
			else provjera=false;
			
		}while (provjera);
		
		

		
	System.out.println("Stanje prvog: "+ prvog.getStanje()+" KN");
	if(drugog instanceof DevizniRacun){
	System.out.println("Stanje drugog: "+ drugog.getStanje() + " "+ ((DevizniRacun)drugog).getValuta());
	 zapisiUDatoteku(fout,"Stanje prvog: "+prvog.getStanje()+ " KN"+"\n");
	 zapisiUDatoteku(fout,"Stanje drugog: "+drugog.getStanje()+ " "+((DevizniRacun)drugog).getValuta()+"\n");

}
	else {System.out.println("Stanje drugog: "+ drugog.getStanje() + "KN");
	 zapisiUDatoteku(fout,"Stanje drugog: "+drugog.getStanje()+ "KN");
	
		
	}System.out.println("Transakcija s najviše sredstava: "+((Transakcija) setTransakcija.first()).getIznosTransakcije()+" KN");
		}
	catch (IOException ex) {
		System.err.println("Pogreška kod èitanja datoteke " + FILE_NAME);
		ex.printStackTrace();
	}
	}
}