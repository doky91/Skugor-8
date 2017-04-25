package hr.tvz.programiranje.java.banka;

import java.math.BigDecimal;

import tvz.programiranje.java.osoba.Osoba;

public abstract class Racun {

private Osoba osoba;
private BigDecimal stanje;
	
	public Racun(Osoba osoba,BigDecimal racun){
		this.osoba=osoba;
		this.stanje=racun;
		
	}
	
	public void uplatiNaRacun(BigDecimal iznos){
		stanje=stanje.add(iznos);	
	}
	public void isplatiSRacuna(BigDecimal iznos){
	stanje=stanje.subtract(iznos);	
	}
	
	
	public BigDecimal getStanje(){return stanje;}

}


