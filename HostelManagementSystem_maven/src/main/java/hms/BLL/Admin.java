package hms.BLL;

import java.util.ArrayList;

import hms.DAL.InsertHandler;
import hms.DAL.SelectHandler;
import hms.DAL.UpdateHandler;

public class Admin extends PersonProfile {
	
	private Warden newWarden ;
	//private ArrayList<Hostel> hostels ;
	
	public Admin(){
	}
	
	public boolean WardenRegistration(String ID , String fName , String lName , String addr , 
			                       String e_mail , String role , String age , String gender , 
			                       String contact , String date , String salary , String hostelID){
		boolean isUpdate = false ;
		
		int pAge = Integer.parseInt(age) ;
		//System.err.println("pAge: " + pAge);
		newWarden = new Warden();
		newWarden.setpID(ID);
		newWarden.setFirstName(fName.toCharArray());
		newWarden.setLastName(lName.toCharArray());
		newWarden.setAddress(addr.toCharArray());
		newWarden.setE_mail(e_mail.toCharArray());
		newWarden.setRole(role);
		newWarden.setAge(pAge);
		newWarden.setGender(gender);
		newWarden.setContact(contact.toCharArray());
		
		// Open an account of new Warden...........
		
		newWarden.setAccountID(ID);
		newWarden.setAccountPassword(ID.toCharArray());   // Default Password........
		
		newWarden.setDate(date);
		newWarden.setHostelID(hostelID);
		newWarden.setSalary(salary);
		newWarden.setwID(ID);
		
		Hostel hostel = new Hostel();
        //hostel.setHostelID(hostelID);
        //hostel.setIsWardenAssigned('1');
		isUpdate = hostel.updateHostelStatus(hostelID , '1');
		
		if (isUpdate){
			
			//System.out.println("ID: " + ID);
			InsertHandler insertHandler = new InsertHandler();
			isUpdate = insertHandler.InsertIntoWarden(newWarden);    // Register new Warden into system via admin.........
		}
		
		return isUpdate ;	
	}
	
	public PersonProfile getPersonProfile(String personID){
		
		PersonProfile tempPerson = new PersonProfile();
		tempPerson = tempPerson.viewPersonProfile(personID);
		
		if (tempPerson.getpID().length() > 0){
			
			return tempPerson ;
		}
		return null ;
	}
	
	public boolean updatePersonProfile(String ID , String fName , String lName , String addr ,
			String role , String eMail , String age , String gender , String contct){
		PersonProfile person = new PersonProfile();
		person.setpID(ID);
		person.setFirstName(fName.toCharArray());
		person.setLastName(lName.toCharArray());
		person.setAddress(addr.toCharArray());
		person.setRole(role);
		person.setE_mail(eMail.toCharArray());
		person.setAge(Integer.parseInt(age));
		person.setContact(contct.toCharArray());
		person.setGender(gender);
		
		return person.updatePersonProfile(person);
	}
	
	public boolean changePass(String oldPass,String newPass,String ID){
		SelectHandler selectPass=new SelectHandler();
		if(selectPass.comparePassword(ID, oldPass)){
			UpdateHandler u=new UpdateHandler();
			if(u.updatePass(ID, newPass)){
				return true;
			}
		}
		return false;
	}
	
	public boolean registerNewHostel(String hostelID , String hostelName , String hostelLoc , 
			                         String hostelRegDate , int totalRoom , String WID){
		
		Hostel newHostel = new Hostel(totalRoom);
		newHostel.setHostelID(hostelID);
		newHostel.setHostelName(hostelName.toCharArray());    // Add Hostel information........
		newHostel.setHostelLoc(hostelLoc.toCharArray());
		newHostel.sethRegDate(hostelRegDate);
		
		if (WID != null){
			
			newHostel.setIsWardenAssigned('1');
			UpdateHandler updateDB = new UpdateHandler();
			updateDB.updateWardenHostelID(hostelID, WID);
		}
		
		return newHostel.addNewHostel();
		//newHostel.setHostelRooms(hostelRooms);
	}
	
	public String getHostelName(String hostelID){
		
		SelectHandler selectDB = new SelectHandler();
		return selectDB.SelectHostelName(hostelID);
	}
	
	public boolean updateHostelName(String hostelID , String hostelName){
		
		UpdateHandler updateHostel = new UpdateHandler();
		return updateHostel.updateHostelInfo(hostelID , hostelName);
		
	}
}