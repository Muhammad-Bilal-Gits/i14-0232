package hms.BLL;

import hms.DAL.SelectHandler;

public class PersonAccount {

	private String pAId ;
	private char [] password ;
	
	public PersonAccount(){
		
		pAId = "" ;
		password = new char[20];
	}

	public String getpAId() {
		return this.pAId;
	}

	public char[] getPassword() {
		return this.password;
	}

	public void setpAId(String pAId) {
		this.pAId = pAId;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
	
	// Account class methods...........
	
	public String LoginAccount(PersonAccount NewUserAccount ,String Role)
    {
        SelectHandler selecthandler = new SelectHandler();        // Call DAL method..........
        return selecthandler.SelectUserAccount(NewUserAccount , Role);
    }
	
}
