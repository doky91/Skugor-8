package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.iznimke.NepodrzanaValutaException;

import java.io.IOException;
import java.math.BigDecimal;

public class Transakcija<T extends Racun, S extends Racun> {
	 protected Racun polazniRacun;
	 protected Racun odlazniRacun;
	 protected BigDecimal iznosTransakcije;
	 
	 public Transakcija(T polazniRacun, S odlazniRacun,
			BigDecimal iznosTransakcije) {
		this.polazniRacun=polazniRacun;
		this.odlazniRacun=odlazniRacun;
		this.iznosTransakcije=iznosTransakcije;
		 
	}

	public void provediTransakciju() throws IOException,NepodrzanaValutaException{};
	
	public BigDecimal getIznosTransakcije() {
		return iznosTransakcije;
	}
	 
	 
}
