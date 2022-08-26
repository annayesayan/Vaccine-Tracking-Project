 package VaccineProject.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VaccineProject.model.VaccineInfo;



@WebServlet("/Edit_Vaccine")
public class Edit_Vaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Edit_Vaccine() {
        super();
        // TODO Auto-generated constructor stub
    }
    	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		
		
		// get data from database
    	Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu50";
            String username = "cs3220stu50";
            String password = "47Y2Ea3RM9Dd";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from vaccines where id="+id+"");

          
            while( rs.next() )
            {            	
            	String name = rs.getString("name");       
            	int doses = rs.getInt("doses");
            	int dose_days=rs.getInt("dose_days");
            	int doses_recieved=rs.getInt("doses_recieved");
            	int doses_left=rs.getInt("doses_left");
            	VaccineInfo vaccine = new VaccineInfo(Integer.parseInt(id),name,doses,dose_days,doses_recieved,doses_left); 
            	request.setAttribute("vaccine", vaccine);
            }       
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
		request.getRequestDispatcher("/WEB-INF/Edit_Vaccine.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		int doses=Integer.parseInt(request.getParameter("doses"));
		int dose_days=Integer.parseInt(request.getParameter("dose_days"));
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu50";
            String username = "cs3220stu50";
            String password = "47Y2Ea3RM9Dd";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            
            stmt.executeUpdate("update vaccines set name='"+name+"', doses="+doses+", dose_days="+dose_days+" where id="+id+"" );
    		  
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
		response.sendRedirect("Vaccine_Table");
	}

}
