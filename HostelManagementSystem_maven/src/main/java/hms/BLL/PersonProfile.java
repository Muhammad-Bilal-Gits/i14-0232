package hms.BLL;

import java.util.Arrays;

import hms.DAL.SelectHandler;
import hms.DAL.UpdateHandler;

public class PersonProfile {
	
	private String pID ;
	private char [] firstName ;
	private char [] lastName ;
	private char [] address ;
	private char [] e_mail ;
	private char [] contact ;
	
	private int age ;
	private String gender;
	private String role ;
	private PersonAccount Account ;
	
	public PersonProfile(){
		
		pID = "" ;
		gender = "Male";
		role = "" ;
		firstName = new char[50];
		lastName = new char[50];
		address = new char[255];
		e_mail = new char[100];
		contact = new char[12];
		Account = new PersonAccount();
	}
	
	public boolean updatePersonProfile(PersonProfile person){
		
		UpdateHandler updateDB = new UpdateHandler();
		return updateDB.updateProfile(person);
		
	}
	
	public PersonProfile viewPersonProfile(String personID){
		
		SelectHandler selectDB = new SelectHandler();
		return selectDB.SelectPerson(personID);
	}

	public String getpID() {
		return this.pID;
	}

	public char[] getFirstName() {
		return this.firstName;
	}

	public char[] getLastName() {
		return this.lastName;
	}

	public char[] getAddress() {
		return this.address;
	}

	public char[] getE_mail() {
		return this.e_mail;
	}

	public char[] getContact() {
		return this.contact;
	}

	public int getAge() {
		return this.age;
	}

	public String getGender() {
		return this.gender;
	}

	public void setpID(String pID) {
		this.pID = pID;
	}

	public void setFirstName(char[] firstName) {
		this.firstName = firstName;
	}

	public void setLastName(char[] lastName) {
		this.lastName = lastName;
	}

	public void setAddress(char[] address) {
		this.address = address;
	}

	public void setE_mail(char[] e_mail) {
		this.e_mail = e_mail;
	}

	public void setContact(char[] contact) {
		this.contact = contact;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setAccountID(String aID){
		this.Account.setpAId(aID);
	}
	
	public void setAccountPassword(char [] password){
		this.Account.setPassword(password);
	}
	
	public String getAccountPassword(){
		
		return String.valueOf(Account.getPassword());
		
	}
	
	public String getAcountID(){
		
		return Account.getpAId();
	}

	public PersonAccount getAccount() {
		return this.Account;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public PersonProfile LoginPersonAccount(PersonProfile person , String role){
		
		String personID = person.Account.LoginAccount(Account , role);  // Call PersonAccount Login method........
        person.setpID(personID);    // setting personId......
        
        if(personID != null)
        {
            SelectHandler selectHandler = new SelectHandler();
            person= selectHandler.SelectPerson(personID);
            person.setpID(personID);
            return person ;
        }
        //System.out.println(person.getFirstName());
        return null;
	}
}
