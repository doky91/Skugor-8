package hr.tvz.programiranje.java.sortiranje;

import hr.tvz.programiranje.java.banka.Transakcija;

import java.math.BigDecimal;
import java.util.Comparator;


public class SortiranjeTransakcija implements Comparator<Transakcija> {

	public int compare(Transakcija arg0, Transakcija arg1) {
		BigDecimal prva = arg0.getIznosTransakcije();
		BigDecimal druga = arg1.getIznosTransakcije();
		
		int rez= prva.compareTo(druga);
		if (rez==-1) {
			return 1;
		}
		else if (rez==1) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	
}
