package hms.BLL;

public class MessItems {
	
	private String itemId ;
	private String morningItemName ;
	private String eveningItemName ;
	private String itemDay ;
	
	public MessItems(){
		
	}

	public String getItemId() {
		return this.itemId;
	}

	public String getMorningItemName() {
		return this.morningItemName;
	}

	public String getEveningItemName() {
		return this.eveningItemName;
	}

	public String getItemDay() {
		return this.itemDay;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setMorningItemName(String morningItemName) {
		this.morningItemName = morningItemName;
	}

	public void setEveningItemName(String eveningItemName) {
		this.eveningItemName = eveningItemName;
	}

	public void setItemDay(String itemDay) {
		this.itemDay = itemDay;
	}
	
	

}
