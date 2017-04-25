package tvz.programiranje.java.osoba;

public class Osoba {
	private String ime;
	private String prezime;
	private String OIB;

	
	public Osoba(String ime,String prezime,String OIB){
		this.ime=ime;
		this.prezime=prezime;
		this.OIB=OIB;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getOIB() {
		return OIB;
	}
	public String toString() { 
		  return prezime + " " + ime + " (" + OIB + ")"; 
		} 

	public void setOIB(String oIB) {
		OIB = oIB;
	}
}

