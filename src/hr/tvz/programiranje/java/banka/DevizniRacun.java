package hr.tvz.programiranje.java.banka;

import java.math.BigDecimal;

import tvz.programiranje.java.osoba.Osoba;

public class DevizniRacun extends Racun{
	private String iban;
	private Valuta valuta;
	
	
	public DevizniRacun(Osoba vlasnik,BigDecimal stanje,String iban,Valuta valuta){
		super(vlasnik,stanje);
		this.iban=iban;
		this.valuta=valuta;
	}




	public String getIban() {
		return iban;
	}


	public Valuta getValuta() {
		return valuta;
	}
	
	 
		public String toString() { 
		  return iban; 
		} 
}
