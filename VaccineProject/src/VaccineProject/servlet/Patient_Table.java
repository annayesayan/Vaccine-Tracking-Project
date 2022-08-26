package VaccineProject.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VaccineProject.model.PatientInfo;
import VaccineProject.model.VaccineInfo;


@WebServlet("/Patient_Table")
public class Patient_Table extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Patient_Table() {
        super();
        
    }
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// get data from database
    	Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu50";
            String username = "cs3220stu50";
            String password = "47Y2Ea3RM9Dd";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from vaccines" );

            //create a list<VaccineInfo>
            List<VaccineInfo> Vtable = new ArrayList<VaccineInfo>();
            
            while( rs.next() )
            {            	
            	String name = rs.getString("name");
            	int id = rs.getInt("id");
            	int doses = rs.getInt("doses");
            	int dose_days=rs.getInt("dose_days");
            	int doses_recieved=rs.getInt("doses_recieved");
            	int doses_left=rs.getInt("doses_left");
            	Vtable.add( new VaccineInfo(id,name,doses,dose_days,doses_recieved,doses_left));           	
            }       
            request.setAttribute("Vtable", Vtable); //Pass the list to view
            
            rs = stmt.executeQuery( "select * from patients" );

            //create a list<PatientInfo>
            List<PatientInfo> Ptable = new ArrayList<PatientInfo>();
            
            while( rs.next() )
            {            	
            	int id = rs.getInt("id");
            	String name = rs.getString("name");
            	String vaccine_name = rs.getString("vaccine_name");
            	String fst_dose_date = rs.getString("fst_dose_date");
            	String scd_dose_date = rs.getString("scd_dose_date");
            	Ptable.add( new PatientInfo(id,name,vaccine_name,fst_dose_date,scd_dose_date));           	
            }
            
            for (PatientInfo patient: Ptable) {
    			for(VaccineInfo vaccine: Vtable) {
    				if(patient.getVaccine_name().equals(vaccine.getName())) {
    					if(vaccine.getDoses()==1) {
    						stmt.executeUpdate("update patients set scd_dose_date='-' where id="+patient.getId()+"" );
    						patient.setScd_dose_date("-");
    					}
    					else if(patient.getScd_dose_date()==null && vaccine.getDoses_left()==0) {
    						stmt.executeUpdate("update patients set scd_dose_date='out of stock' where id="+patient.getId()+"" );
    						patient.setScd_dose_date("out of stock");
    					}
    					else if(patient.getScd_dose_date()==null && vaccine.getDoses_left()> 0) {
    						stmt.executeUpdate("update patients set scd_dose_date='Received' where id="+patient.getId()+"" );
    						patient.setScd_dose_date("Received");
    					}
    					break;
    				}
//    				stmt.executeUpdate("update patients set scd_dose_date='out of stock' where id="+patient.getId()+"" );
//    				patient.setScd_dose_date("out of stock");
    			}		
    		}
            request.setAttribute("Ptable", Ptable);
        }
        catch( SQLException e )
        {
            throw new ServletException( e );
        }
        finally
        {
            try
            {
                if( c != null ) c.close();
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
        }
		
		
		request.getRequestDispatcher("/WEB-INF/Patient_Table.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
