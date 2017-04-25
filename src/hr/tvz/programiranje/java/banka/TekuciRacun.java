package hr.tvz.programiranje.java.banka;

import java.math.BigDecimal;
import java.util.Scanner;

import tvz.programiranje.java.osoba.Osoba;

public class TekuciRacun extends Racun {
	
	private String brojRacuna;
	
	public TekuciRacun(Osoba vlasnik, BigDecimal stanje,String brojRacuna){
	super(vlasnik,stanje);
	this.brojRacuna=brojRacuna;
	
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}


	public String toString() { 
		  return brojRacuna; 
		} 
	
}
