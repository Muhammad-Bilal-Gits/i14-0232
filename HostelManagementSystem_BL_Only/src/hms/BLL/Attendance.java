package hms.BLL;

import hms.DAL.InsertHandler;
import hms.DAL.UpdateHandler;

public class Attendance {
	
	private String atteDate ;
	
	public Attendance(){
		
	}
	
	public boolean addAttendance(String [] attenList , String [] attenders){
		
		InsertHandler setAttendance = new InsertHandler();
		return setAttendance.InsertIntoAttendance(attenList, attenders, this.atteDate);
	}
	
	public boolean updateAttendance(String [] attenList , String [] attenders){
		
		UpdateHandler updateDB = new UpdateHandler();
		return updateDB.updateAttendanceDB(attenList, attenders, this.atteDate);
	}

	public String getAtteDate() {
		return this.atteDate;
	}

	public void setAtteDate(String atteDate) {
		this.atteDate = atteDate;
	}

}
