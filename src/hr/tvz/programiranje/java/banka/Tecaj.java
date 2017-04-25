package hr.tvz.programiranje.java.banka;

import java.math.BigDecimal;


public class Tecaj {

	private Valuta valuta;
	private BigDecimal TecajPremaKuni;
	
	Tecaj(Valuta valuta,BigDecimal TecajPremaKuni){
		this.valuta=valuta;
		this.TecajPremaKuni=TecajPremaKuni;
	}

	

	public Valuta getValuta() {
		return valuta;
	}

	public BigDecimal getTecajPremaKuni() {
		return TecajPremaKuni;
	}


	
	
}
