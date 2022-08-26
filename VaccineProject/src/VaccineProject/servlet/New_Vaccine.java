package VaccineProject.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VaccineProject.model.VaccineInfo;




@WebServlet("/New_Vaccine")
public class New_Vaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public New_Vaccine() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/New_Vaccine.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("name");
		int doses = Integer.parseInt(request.getParameter("doses"));
		int dose_days=Integer.parseInt(request.getParameter("dose_days"));
		
		if (doses==1) {
			dose_days=0;
		}
		Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu50";
            String username = "cs3220stu50";
            String password = "47Y2Ea3RM9Dd";

            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            
            stmt.executeUpdate("insert into vaccines (name, doses, dose_days, doses_recieved, doses_left) values ('"+name+"', '"+doses+"', '"+dose_days+"', '0', '0')" );
    		  
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
		response.sendRedirect("Vaccine_Table");
	}

}
