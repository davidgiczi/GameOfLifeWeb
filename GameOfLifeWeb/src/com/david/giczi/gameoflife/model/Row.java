package com.david.giczi.gameoflife.model;

public class Row {

	
	private static int logicValue;
	private static int JSPValue;
	
	
	public Row(int row) {
		
		Row.logicValue=row;
		Row.JSPValue=row-1;
	}


	public static int getLogicValue() {
		return logicValue;
	}


	public static int getJSPValue() {
		return JSPValue;
	}

	
	
	
	
	
}
