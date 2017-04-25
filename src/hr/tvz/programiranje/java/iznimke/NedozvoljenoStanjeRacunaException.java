package hr.tvz.programiranje.java.iznimke;

public class NedozvoljenoStanjeRacunaException extends RuntimeException {

	
	public NedozvoljenoStanjeRacunaException (String poruka){
		super(poruka);
	}
	
	NedozvoljenoStanjeRacunaException (String poruka,Throwable objekt)
	{
		super(poruka,objekt);
	}
	
	
	
	
}
