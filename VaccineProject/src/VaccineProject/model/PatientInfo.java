package VaccineProject.model;

public class PatientInfo {
	
	
	private int id;
	
	private String name;
	private String vaccine_name;
	private String fst_dose_date;
	private String scd_dose_date;

	
	public PatientInfo(int id, String name, String vaccine_name, String fst_dose_date, String scd_dose_date) {		
		this.id=id;
		this.name=name;
		this.vaccine_name=vaccine_name;
		this.fst_dose_date=fst_dose_date;
		this.scd_dose_date=scd_dose_date;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVaccine_name() {
		return vaccine_name;
	}
	public void setVaccine_name(String vaccine_name) {
		this.vaccine_name = vaccine_name;
	}
	public String getFst_dose_date() {
		return fst_dose_date;
	}
	public void setFst_dose_date(String fst_dose_date) {
		this.fst_dose_date = fst_dose_date;
	}
	public String getScd_dose_date() {
		return scd_dose_date;
	}
	public void setScd_dose_date(String scd_dose_date) {
		this.scd_dose_date = scd_dose_date;
	}
	
	

}
