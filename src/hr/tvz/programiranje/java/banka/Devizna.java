package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.iznimke.NepodrzanaValutaException;

import java.io.IOException;
import java.math.BigDecimal;

public interface Devizna {

	public BigDecimal mjenjacnica(BigDecimal polazniIznosKN,Valuta valuta) throws IOException, NepodrzanaValutaException;
}
