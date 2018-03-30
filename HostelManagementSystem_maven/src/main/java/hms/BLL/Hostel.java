package hms.BLL;

import java.util.ArrayList;

import hms.DAL.InsertHandler;
import hms.DAL.SelectHandler;
import hms.DAL.UpdateHandler;

public class Hostel {

	private String hostelID ;
	private char [] hostelName ;
	private char [] hostelLoc ;
	private String hRegDate ;
	private char isWardenAssigned ;
	private Warden hostelWarden ;
	private ArrayList<Room> hostelRooms = null  ;
	private ArrayList<Hosteller> hostellers = null ;
	
	public Hostel(){
	}
	
	public Hostel(int totalRoom){	
		
		hostelRooms = new ArrayList<Room>(totalRoom);
		int rID = 100 ;                                     // Add hostel rooms Information..........
		for (int i = 0 ; i < totalRoom; i++){
		
			Room newRoom = new Room();
			rID += 1;
			newRoom.setrID("R"+rID);
			//System.out.println("RID: " +"R"+rID );
			newRoom.setrSpace(4);
			newRoom.setrCapacity(4);
			newRoom.setrStatus("empty");
			hostelRooms.add(newRoom);
		}
	}
	
	public boolean addNewHostel(){
		
		InsertHandler insertHostel = new InsertHandler();
		System.out.println("Sending rooms to inser query room=" + this.hostelRooms.size());
		return insertHostel.InsertIntoHostel(this);   // Call DAL Controller..........
	}
	
	public boolean updateHostelStatus(String hostelID , char status ){
		
		UpdateHandler updateDB = new UpdateHandler();
		return updateDB.UpdateHostelStatus(hostelID , status);
	}
	
	public Room getRoom(String Rid){
	
		SelectHandler selectRoom = new SelectHandler();
		return selectRoom.getHRoom(Rid);
	}

	public String getHostelID() {
		return this.hostelID;
	}

	public char[] getHostelName() {
		return this.hostelName;
	}

	public char[] getHostelLoc() {
		return this.hostelLoc;
	}

	public void setHostelID(String hostelID) {
		this.hostelID = hostelID;
	}

	public void setHostelName(char[] hostelName) {
		this.hostelName = hostelName;
	}

	public void setHostelLoc(char[] hostelLoc) {
		this.hostelLoc = hostelLoc;
	}

	public Warden getHostelWarden() {
		return this.hostelWarden;
	}

	public void setHostelWarden(Warden hostelWarden) {
		this.hostelWarden = hostelWarden;
	}

	public ArrayList<Room> getHostelRooms() {
		return this.hostelRooms;
	}

	public void setHostelRooms(ArrayList<Room> hostelRooms) {
		this.hostelRooms = hostelRooms;
	}

	public String gethRegDate() {
		return this.hRegDate;
	}

	public void sethRegDate(String hRegDate) {
		this.hRegDate = hRegDate;
	}

	public char getIsWardenAssigned() {
		return this.isWardenAssigned;
	}

	public void setIsWardenAssigned(char isWardenAssigned) {
		this.isWardenAssigned = isWardenAssigned;
	}

	public ArrayList<Hosteller> getHostellersList() {
		SelectHandler selectDB = new SelectHandler();
		this.setHostellersList(selectDB.getHostellers(this.hostelID));
		
		return this.hostellers;
	}

	public void setHostellersList(ArrayList<Hosteller> hostellers) {
		this.hostellers = hostellers;
	}
}
