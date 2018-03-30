package testCases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import hms.BLL.Admin;
import hms.BLL.Attendance;
import hms.BLL.Hosteller;
import hms.BLL.Mess;
import hms.BLL.PersonProfile;
import hms.BLL.Room;
import hms.BLL.Warden;
import hms.Controller.HmsFacadeController;

public class testCase1 {

	@Test
	public void test1() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
//		contrl.initiateConditions();
		
		////////////////////////// Login TestCase ////////////////////////////
		PersonProfile result_Profile = contrl.LoginPerson("A10001", "admin", "Admin");
		System.out.println(".."+result_Profile.getpID()+"..");
		assertEquals("Admin's login failed","A10001",result_Profile.getpID());
		//////////////////////////////////////////////////////////////////////
		
	}
	
	@Test
	public void test2() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		///////////////////////// Register Hostel ///////////////////////////////////
		boolean result_isHostelRegistered = contrl.hostelRegistration("h5","Senior Boys Hostel","House No 12 G11-2 Islamabad","2018-01-23","20","W001");
		System.out.println(".."+ result_isHostelRegistered +"..");
		assertEquals("Not created",true,result_isHostelRegistered);
		/////////////////////////////////////////////////////////////////////
		
	}
	
	@Test
	public void test3() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		///////////////////////// Register Warden ///////////////////////////////////
		boolean result_isCreated = contrl.registerNewPerson("W001", "Muhammad", "Bilal", "House 18 Block H SoanGarden Islamabad", "Bilalshafqat0336@gmail.com", "23", "Male", "03365430635", "2018-02-23","100000" , "h5");
		System.out.println(".."+ result_isCreated +"..");
		assertEquals("Not created",true,result_isCreated);
		/////////////////////////////////////////////////////////////////////////////
		
	}
	
	@Test
	public void test4() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////////////// Check Person Info ////////////////////////////
		PersonProfile result_profileGender = contrl.getPersonInfo("W001");
		System.out.println(result_profileGender.getGender());
		assertEquals( result_profileGender.getGender() ,"Male");
		///////////////////////////////////////////////////////////////////////////////////////
		
	}
	
	@Test
	public void test5() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		///////////////////////// Register Hosteler ///////////////////////////////////
		boolean result_isCreated = contrl.registerNewPerson("H001", "Ali", "Hasan", "House 7 Street 4 G-8 Islamabad", "ZararHasan@gmail.com", "22", "Male", "03345412694", "2018-02-23","100000" , "h5");
		System.out.println(".."+ result_isCreated +"..");
		assertEquals("Not created",true,result_isCreated);
		/////////////////////////////////////////////////////////////////////////////
	
	}
	
	@Test
	public void test6() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		boolean result_UpdateWardenInfo = contrl.updatePersonInfo("W001", "Ahsan", "Ali", "Bailla Road G9-2 Islamabad", "Ahsan@gmail.com", "20", "Male", "03345632739");
		assertEquals( result_UpdateWardenInfo , true);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void test7() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Check Room Space Info ///////////////////////////////
		boolean result_IsHostelFree = contrl.CheckRoomSapce("W001");
		assertEquals( result_IsHostelFree , true);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void test8() {
	
		// room must be added first..............
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		Room room = new Room("232",3,5,"free");
		//////////////////////////////// Allocating Room ///////////////////////////////
		boolean result_IsRoomAllocated = contrl.AllocateRoomToHosteller("h5","R102","H001");
		assertEquals( result_IsRoomAllocated , true);
		///////////////////////////////////////////////////////////////////////////////////
	}

	@Test
	public void test9() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// checking Hostel Info ///////////////////////////////
		String result_HostelName = contrl.getHostelInfo("h5");
		assertEquals( result_HostelName , "Senior Boys Hostel");
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test1() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Updating hostel Info ///////////////////////////////
		boolean result_IsHostelUpdated = contrl.updateHostelInfo("h5", "Junior Boys Hostel");
		assertEquals( result_IsHostelUpdated , true);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test2() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// check Make New Mess ///////////////////////////////
		
		List<String>morningItm =  new ArrayList<String>() ;
		morningItm.add("Korma");
		morningItm.add("Burger");
		morningItm.add( "beef");
		morningItm.add("Mutton");
		morningItm.add("Roti");
		morningItm.add("daal");
		morningItm.add("Broast" );
		
		List<String>eveningItm = new ArrayList<String>() ;
		eveningItm.add("Korma");
		eveningItm.add("Burger");
		eveningItm.add( "beef");
		eveningItm.add("Mutton");
		eveningItm.add("Roti");
		eveningItm.add("daal");
		eveningItm.add("Broast" );
		
		
		
		boolean result_IsHostelUpdated = contrl.MakeNewMess("M001" , "h5" , morningItm ,eveningItm );
		assertEquals( result_IsHostelUpdated , true);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test3() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		List<String>morningItm =  new ArrayList<String>() ;
		morningItm.add("Korma");
		morningItm.add("Burger");
		morningItm.add( "beef");
		morningItm.add("Mutton");
		morningItm.add("Roti");
		morningItm.add("daal");
		morningItm.add("Broast" );
		
		List<String>eveningItm = new ArrayList<String>() ;
		eveningItm.add("Shawarma");
		eveningItm.add("Burger");
		eveningItm.add( "beef");
		eveningItm.add("Mutton");
		eveningItm.add("Roti");
		eveningItm.add("daal");
		eveningItm.add("Broast" );
		
		boolean result_UpdateMess = contrl.updateMessItems("M001", "h5", morningItm ,  eveningItm  );
		System.out.println(result_UpdateMess);
		assertEquals( result_UpdateMess ,true);
	}
	
	
	
	@Test
	public void Z_test4() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		String[] attendList = {"P","P","P"};
		String[] attendersList = {"ATT1","ATT2","ATT3"};
		boolean is_removed = contrl.saveAttendance( "23-45-2018",attendList, attendersList);
		System.out.println(is_removed);
		assertEquals( is_removed ,true);
	}
	
	@Test
	public void Z_test5() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		boolean is_presentAttendence = contrl.checkAttendanceDate( "23-45-2018");
		System.out.println(is_presentAttendence);
		assertEquals( is_presentAttendence ,true);
	}
	
	@Test
	public void Z_test6() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		boolean is_Changed = contrl.changePass("W001", "W001P","W001" );
		System.out.println(is_Changed);
		assertEquals( is_Changed ,true);
	}
	
	@Test
	public void Z_test7() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
	
		String result = contrl.checkMessMenuExist("W001");
		System.out.println(result);
		assertEquals( result ,"h5");
	}
	
	@Test
	public void Z_test8() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		///////////////////////// Register Warden ///////////////////////////////////
		boolean result_isCreated = contrl.registerNewPerson("K001", "Muhammad", "Asif", "House 19 Block B SoanGarden Islamabad", "Asif0336@gmail.com", "23", "Male", "03365430635", "2018-02-23","100000" , "h5");
		System.out.println(".."+ result_isCreated +"..");
		assertEquals("Not created",false,result_isCreated);
		/////////////////////////////////////////////////////////////////////////////
		
	}
	
	@Test
	public void Z_test9() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		boolean result_UpdateWardenInfo = contrl.updatePersonInfo("H001", "Ahsan", "Ali", "Bailla Road G9-2 Islamabad", "Ahsan@gmail.com", "20", "Male", "03345632739");
		assertEquals( result_UpdateWardenInfo , false);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test9_1() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		Mess result_MessInfo = contrl.getMessMenu("W001");
		System.out.println(result_MessInfo.getmHostelId());
		assertEquals( result_MessInfo.getmID() , "M001");
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test9_2() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		Mess result_MessInfo = contrl.getMessMenu("H001");
		assertEquals( result_MessInfo.getmID() , "M001");
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test9_3() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		Mess result_MessInfo = contrl.getMessMenu("K001");
		assertEquals( result_MessInfo , null);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test9_4() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		ArrayList<Hosteller> RegisteredHostellers = contrl.getRegisteredHostellers("W001");
		assertEquals(RegisteredHostellers.size() , 1);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test9_5() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		boolean result_isID = contrl.isValidID("W001");
		assertEquals(result_isID , true);
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test9_6() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		String result_ID = contrl.getPersonID("selectWardenID");
		System.out.println(result_ID);
		assertEquals(result_ID , "W2");
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_test9_7() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		String result_ID = contrl.getPersonID("selectHostellerID");
		System.out.println(result_ID);
		assertEquals(result_ID , "H2");
		///////////////////////////////////////////////////////////////////////////////////
	}
	@Test
	public void Z_test9_8() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		String result_ID = contrl.getPersonID("selectHostelID");
		System.out.println(result_ID);
		assertEquals(result_ID , "h6");
		///////////////////////////////////////////////////////////////////////////////////
	}
	@Test
	public void Z_test9_9() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		//////////////////////////////// Update Perosn Info ///////////////////////////////
		String result_ID = contrl.getPersonID("Mess");
		System.out.println(result_ID);
		assertEquals(result_ID , "M2");
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	@Test
	public void Z_ztest9_91() {
		HmsFacadeController contrl = HmsFacadeController.getInstance();
		
		boolean is_removed = contrl.removeMessItems("h5");
		System.out.println(is_removed);
		assertEquals( is_removed ,true);
	}
	
	//////////////////////////////////////BLL/////////////////////////////////////////
	@Test
	public void Z_ztest9_92() {
		Admin admin = new Admin();
		
		PersonProfile resultProfile = admin.getPersonProfile("H009");
		//System.out.println(is_removed);
		assertEquals( resultProfile ,null);
	}
	
	@Test
	public void Z_ztest9_93() {
		Admin admin = new Admin();
		
		boolean result = admin.changePass("H004", "GOO1", "HOO2");
		//System.out.println(is_removed);
		assertEquals( result ,false);
	}
	
}
