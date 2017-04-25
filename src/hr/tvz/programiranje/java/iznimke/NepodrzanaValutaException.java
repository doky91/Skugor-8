package hr.tvz.programiranje.java.iznimke;


public class NepodrzanaValutaException extends Exception {
	
	public NepodrzanaValutaException(String poruka){
		super(poruka);
	}
	
	public NepodrzanaValutaException(String poruka,Throwable objekt)
	{
		super(poruka,objekt);
	}
	

}
