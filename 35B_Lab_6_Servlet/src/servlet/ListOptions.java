package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.AutoClient;
import exception.AutoException;
import model.Automobile;

/**
 * Servlet implementation class ListOptions
 */
@WebServlet("/ListOptions")
public class ListOptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOptions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		
		
		
		String autoString = request.getParameter("model");
		
		System.out.println("AutoString: " + autoString);
		
		System.out.println("MMY: " + autoString);
		
	 	AutoClient autoClient = new AutoClient("localhost", 8010);
		autoClient.openConnection();
		Automobile auto = autoClient.getModel(autoString);
		autoClient.disconnect();
		autoClient.closeSession();
		
		if(auto == null)
			System.out.println("Auto is null");
		else 
			auto.print();
		
		PrintWriter out = response.getWriter();
		
        String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
        out.println(docType + "<html>\n<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
        out.println("<title>Car Configuration Menu</title></head>");
        out.println("<body>");
        out.println("<h1>Car Configuration Menu</h1>");
        out.println("<form action=\"show.jsp\"><table border=\"1\" style=\"width:40%\">");
        out.println("<tr><td class=\"midbold\">Make/Model:</td><td>" + auto.getMakeModelYear() + "</td></tr>");
		  
        String opsetTitle;
        String optionTitle;
        
        for (int i = 0; i < auto.getNumOfProperties(); i++) {
        		opsetTitle = auto.getOptionSetName(i);
        		 out.println("<tr><td class=\"midbold\"><b>" + opsetTitle + ": " +"</b></td>");
        		 out.println("<td><select name=\"" + opsetTitle + "\">");
        		 
        		 for (int j = 0; j < auto.getOptionNames(i).length; j++) {
        			 try {
        				 optionTitle = auto.getOptionName(i, j);
        				 out.println("<option value=\"" + optionTitle + "\">" + optionTitle + "</option>");
        			 } catch (Exception e) {e.printStackTrace();}
        		 }
        		 out.println("</select></td>\n</tr>");	 
        }
        
        out.println("</table>");
        out.println("</table>");
		out.println("<input type=\"submit\" align=\"center\" value=\"Submit\">");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
        
        HttpSession session = request.getSession(true);
        session.setAttribute("auto", auto);
        
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
