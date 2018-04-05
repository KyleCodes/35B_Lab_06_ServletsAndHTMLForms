package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.AutoClient;

/**
 * Servlet implementation class ListModels
 */
@WebServlet("/ListModels")
public class ListModels extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListModels() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		AutoClient autoClient = new AutoClient("localhost", 8010);
		autoClient.openConnection();
		String[] modelArr = autoClient.getModelList();
		autoClient.sendOutput("carThatDoesntExist");
		autoClient.disconnect();
		autoClient.closeSession();
		
        String docType =
        	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
		out.println(docType + "<html>\n<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		out.println("<title>Select a model</title></head>");
		out.println("<body><h1>Model Selection</h1>");
		out.println("<form action=\"ListOptions\">");
		out.println("<select name=\"model\">");
		for (int i = 0; i < modelArr.length; i++) {
			out.println("<option value=\"" + modelArr[i] + "\"/>" + modelArr[i] + "</option>");
		}
		out.println("</select>");
        out.println("<input type=\"submit\" value=\"Submit\">");
        out.println("</form>\n</body>\n</html>");
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
