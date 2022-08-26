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


@WebServlet("/New_Doses")
public class New_Doses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public New_Doses() {
        super();
        // TODO Auto-generated constructor stub
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
		
		
		
		
		request.getRequestDispatcher("/WEB-INF/New_Doses.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("vacc_names");
		int doses = Integer.parseInt(request.getParameter("doses_received"));
		
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu50";
            String username = "cs3220stu50";
            String password = "47Y2Ea3RM9Dd";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            
            stmt.executeUpdate("update vaccines set doses_recieved=doses_recieved+"+doses+", doses_left=doses_left+"+doses+" where name='"+name+"'" );
    		  
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
