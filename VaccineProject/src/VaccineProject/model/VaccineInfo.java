package VaccineProject.model;

public class VaccineInfo {
	

	private int id;
	
	private java.lang.String name;
	private int doses;
	private int dose_days;
	private int doses_recieved;
	private int doses_left;
	
	public VaccineInfo() {
		
	}
	
	public VaccineInfo(int id, java.lang.String name , int doses, int days, int recieved, int left) {		
		this.id=id;
		this.name=name ;
		this.doses=doses;
		this.dose_days=days;
		this.doses_recieved=recieved;
		this.doses_left=left;
		
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public int getDoses() {
		return doses;
	}
	public void setDoses(int doses) {
		this.doses = doses;
	}
	public int getDose_days() {
		return dose_days;
	}
	public void setDose_days(int dose_days) {
		this.dose_days = dose_days;
	}
	public int getDoses_recieved() {
		return doses_recieved;
	}
	public void setDoses_recieved(int doses_recieved) {
		this.doses_recieved = doses_recieved;
	}
	public int getDoses_left() {
		return doses_left;
	}
	public void setDoses_left(int doses_left) {
		this.doses_left = doses_left;
	}

}
