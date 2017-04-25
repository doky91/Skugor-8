package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.glavna.Glavna;
import hr.tvz.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;
import hr.tvz.programiranje.java.iznimke.NepodrzanaValutaException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviznaTransakcija<T extends TekuciRacun, S extends DevizniRacun> 
extends Transakcija<T, S> implements Devizna {
	
	public static final BigDecimal TECAJ_EUR_KN=new BigDecimal(7.5);
	public static final String valuta="EUR";
	public BigDecimal mjenjacnica(BigDecimal polazniIznosKN, Valuta valuta) throws IOException, NepodrzanaValutaException{ 
			for(Tecaj tecaj : Tecajnica.dohvatiTecajeve()) { 
				if(tecaj.getValuta().compareTo(valuta) == 0) {
					BigDecimal iznos = polazniIznosKN.divide(tecaj.getTecajPremaKuni(), 2, RoundingMode.HALF_UP); return iznos; } } return polazniIznosKN;
	
}
	
	public DeviznaTransakcija(T polazni,S dolazni,BigDecimal iznos){
		super(polazni,dolazni,iznos);	
	}
	
	
	public void provediTransakciju() throws IOException, NepodrzanaValutaException{
		
			
		if (iznosTransakcije.compareTo(polazniRacun.getStanje())==1) throw new NedozvoljenoStanjeRacunaException ("Nemas kuna!");
		else{
				polazniRacun.isplatiSRacuna(super.iznosTransakcije);
				
				BigDecimal konvertiraniIznos=mjenjacnica(super.iznosTransakcije,
						((DevizniRacun)odlazniRacun).getValuta());
				odlazniRacun.uplatiNaRacun(konvertiraniIznos);
			}
		
		
		
	}
	public static Valuta provjeriValutu(String valuta) throws NepodrzanaValutaException { 
		try { 
			return Valuta.valueOf(valuta); } 
		catch (IllegalArgumentException ex) { 
			throw new NepodrzanaValutaException( " Valuta " + valuta + " nije podržana!" , ex); } }
		
	}


