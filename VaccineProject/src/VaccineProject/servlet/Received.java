package VaccineProject.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VaccineProject.model.PatientInfo;
import VaccineProject.model.VaccineInfo;


@WebServlet("/Received")
public class Received extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Received() {
        super();
        
    }
    

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		
		//get today's date for 2nd dose
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String d=dateFormat.format(date);
		
		
		String id=request.getParameter("id");
			
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu50";
            String username = "cs3220stu50";
            String password = "47Y2Ea3RM9Dd";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            
            stmt.executeUpdate("update patients set scd_dose_date='"+d+"' where id="+id+"" );
            ResultSet rs = stmt.executeQuery( "select * from patients where id="+id+"");

            rs.next();          	
            String vacc_name = rs.getString("vaccine_name");
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
		
		
		//returning a view
		response.sendRedirect("Patient_Table");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
