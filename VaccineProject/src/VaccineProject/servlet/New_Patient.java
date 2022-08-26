package VaccineProject.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VaccineProject.model.PatientInfo;
import VaccineProject.model.VaccineInfo;


@WebServlet("/New_Patient")
public class New_Patient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public New_Patient() {
        super();
    }
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
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
            List<VaccineInfo> table = new ArrayList<VaccineInfo>();
            
            while( rs.next() )
            {            	
            	String name = rs.getString("name");
            	int id = rs.getInt("id");
            	int doses = rs.getInt("doses");
            	int dose_days=rs.getInt("dose_days");
            	int doses_recieved=rs.getInt("doses_recieved");
            	int doses_left=rs.getInt("doses_left");
            	table.add( new VaccineInfo(id,name,doses,dose_days,doses_recieved,doses_left));           	
            }       
            request.setAttribute("table", table);
            //Pass the list to view
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
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/New_Patient.jsp").forward(request,response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String name=request.getParameter("name");
		String vacc_name=request.getParameter("vacc_names");
		
		
		//get today's date for 1st dose
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String d=dateFormat.format(date);
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu50";
            String username = "cs3220stu50";
            String password = "47Y2Ea3RM9Dd";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            
            stmt.executeUpdate("insert into patients (name, vaccine_name, fst_dose_date, scd_dose_date) values ('"+name+"', '"+vacc_name+"', '"+d+"', null)" );
            stmt.executeUpdate("update vaccines set doses_left=doses_left-1 where name='"+vacc_name+"'");
    		  
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		//add to the table
//		PatientInfo patient = new PatientInfo(name,vacc_name, d, n);
//		List<PatientInfo> Ptable = (List<PatientInfo>) getServletContext().getAttribute("Ptable");
//		Ptable.add(patient);
		
		//decrement vaccine by one
		//decrement(vacc_name);
					
		//returning a view
		response.sendRedirect("Patient_Table");
	}

}
