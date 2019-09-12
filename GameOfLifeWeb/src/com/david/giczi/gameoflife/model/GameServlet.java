package com.david.giczi.gameoflife.model;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private TableLogic table;
	private int counter=0;
	private String patternName;
	
    public GameServlet() {
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String patternName=request.getParameter("pattern");
		
		
		if(patternName!=null) {
			
			this.patternName=patternName;
			
			startFunction(patternName, request, response);
			
		}
		else {
			
			runFunction(request, response);
		}
		
		
	
		
	}

	
	
	private void startFunction(String patternName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		counter=0;
		new Row(50);
		table=new TableLogic(Row.getLogicValue());
		table.inputPattern(patternName);
		
		request.setAttribute("run", false);
		request.setAttribute("counter", counter);
		request.setAttribute("row", Row.getJSPValue());
		request.setAttribute("names", PatternName.NAMES);
		request.setAttribute("patternName", patternName);
		request.setAttribute("pattern", table.getBeforeTable());
		request.getRequestDispatcher("table.jsp").forward(request, response);
		
	}
	

	private void runFunction( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		counter++;
		
		table.createTable();
		
		if(table.isTheEnd()) {
			
			request.setAttribute("run", false);
		}
		
		else {
			
			request.setAttribute("run", true);
		}
		
		table.changeTable();
			
		request.setAttribute("pattern", table.getBeforeTable());
		request.setAttribute("counter", counter);
		request.setAttribute("row", Row.getJSPValue());
		request.setAttribute("names", PatternName.NAMES);
		request.setAttribute("patternName", patternName);
		request.getRequestDispatcher("table.jsp").forward(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}


	

}
