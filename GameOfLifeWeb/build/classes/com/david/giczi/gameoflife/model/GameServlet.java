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
		String value=request.getParameter("input");
		
		if(patternName!=null) {
			
			startFunction(patternName, request, response);
			
		}
		else if(patternName==null && value==null){
			
			
			runFunction(request, response);
		}
		
		else if(value!=null) {
			
			
			inputFunction(value, request, response);
			
		}
		
	}

	
	
	private void startFunction(String patternName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if("Empty table".equals(patternName)) {
			this.patternName="Your pattern";
		}
		else {
			
			this.patternName=patternName;
		}
		
		counter=0;
		table=new TableLogic(Row.getRowValue());
		table.inputPattern(patternName);
		
		request.setAttribute("run", false);
		request.setAttribute("badvalue", false);
		
		initJSP(request, response);
		
	}
	

	private void runFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		counter++;
		
		table.createTable();
		
		if(table.isTheEnd()) {
			
			request.setAttribute("run", false);
		}
		
		else {
			
			request.setAttribute("run", true);
		}
		
		table.changeTable();
		
		request.setAttribute("badvalue", false);
		initJSP(request, response);
		
	}
	
	private void inputFunction(String input, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				if(table==null) {
					
					startFunction("Empty table", request, response);
				}
		
			
			try {
				table.editPatternInTable(input);
				
				request.setAttribute("run", false);
				request.setAttribute("badvalue", false);
				initJSP(request, response);
				
			} catch (InvalidInputValueException e) {
				
				request.setAttribute("run", false);
				request.setAttribute("badvalue", true);
				initJSP(request, response);
				
			}
				
			
	}
	
	
	
	private void initJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setAttribute("counter", counter);
		request.setAttribute("pattern", table.getBeforeTable());
		request.setAttribute("row", Row.getRowValue());
		request.setAttribute("names", PatternName.NAMES);
		request.setAttribute("patternName", patternName);
		request.getRequestDispatcher("table.jsp").forward(request, response);
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	

}
