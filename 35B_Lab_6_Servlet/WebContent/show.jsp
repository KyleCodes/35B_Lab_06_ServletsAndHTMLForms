<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Automobile"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Choices</title>
</head>
<body>
<h1>Selected Options</h1>
<% Automobile auto = (Automobile) request.getSession().getAttribute("auto"); %>
	<table border="1" style="width:40%">
		<tr>
			<td><%= auto.getMakeModelYear() %></td>
			<td>Base Price</td>
			<td><%= auto.getBasePrice() %></td>
		</tr>
		<%
			String optionSetTitle = null;
			String optionChoice = null;
			int price = 0;
			
			int n = auto.getNumOfProperties();
			for (int i = 0; i < n; i++) {
				optionSetTitle = auto.getOptionSetName(i);
				optionChoice = request.getParameter(optionSetTitle);
				out.println("<tr><td>" + optionSetTitle + "</td>");
				out.println("<td>" + optionChoice + "</td>");
				
				for(int j = 0; j < auto.getOptionNames(i).length; j++) {
					if (auto.getOptionNames(i)[j].equals(optionChoice))
						price = auto.getOptionPrice(i, j);
				}
				
				out.println("<td class=\"price\">" + price + "</td></tr>");
				auto.setOptionChoice(optionSetTitle, optionChoice);
			}
		%>
		<tr>
			<td><b>Price Total</b></td>
			<td></td><td class="price"><b><%= auto.getTotalPriceString() %></b></td>
	    </tr>
	</table>
</body>
</html>