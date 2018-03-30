package hms.BLL;

public class Room {
	
	private String rID ;
	private int rSpace ;
	private int rCapacity ;
	private String rStatus ;
	
	public Room(){
	}
	
	public Room(String rID, int rSpace, int capacity, String rStatus){
		this.rID = rID;
		this.rSpace = rSpace;
		this.rCapacity = capacity;
		this.rStatus = rStatus;
	}

	public String getrID() {
		return this.rID;
	}

	public int getrSpace() {
		return this.rSpace;
	}

	public int getrCapacity() {
		return this.rCapacity;
	}

	public String getrStatus() {
		return this.rStatus;
	}

	public void setrID(String rID) {
		this.rID = rID;
	}

	public void setrSpace(int rSpace) {
		this.rSpace = rSpace;
	}

	public void setrCapacity(int rCapacity) {
		this.rCapacity = rCapacity;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}	
}
