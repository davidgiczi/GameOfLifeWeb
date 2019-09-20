package com.david.giczi.gameoflife.model;

import java.util.ArrayList;
import java.util.List;

public class TableLogic implements Pattern, PatternName{

	
	private List<Boolean> beforeTable;
	private List<Boolean> afterTable;
	private int row;
	
	public TableLogic(int rowNumber) {
		
		row=rowNumber;
		beforeTable=new ArrayList<>();
		afterTable=new ArrayList<>();
		
		for(int i=0; i<row*row; i++) {
			beforeTable.add(false);
			afterTable.add(false);
		}
			
	}


	public List<Boolean> getBeforeTable() {
		return beforeTable;
	}


	public List<Boolean> getAfterTable() {
		return afterTable;
	}
	
	
	public void inputPattern(String name) {
		
		
		for(int i=0; i<NAMES.length; i++) {
			
			
			if(NAMES[i].equals(name)) {
				
				
				for (Integer value : PATTERNS[i]) {
					beforeTable.set(value, true);
				}
					
			}
				
		}
			
	}
	
	private int getNeighborsNumber(int index) {
		
		int x=index/row;
		int y=index%row;
		int neighbor=0;
		
		if(x-1>=0 && y-1>=0 && beforeTable.get((x-1)*row+(y-1)))
		neighbor++;
		if(x-1>=0 && beforeTable.get((x-1)*row+y))
		neighbor++;
		if(x-1>=0 && y+1<row && beforeTable.get((x-1)*row+(y+1)))
		neighbor++;
		if(y+1<row && beforeTable.get(x*row+(y+1)))
		neighbor++;
		if(x+1<row && y+1<row && beforeTable.get((x+1)*row+(y+1)))
		neighbor++;
		if(x+1<row && beforeTable.get((x+1)*row+y))
		neighbor++;
		if(x+1<row && y-1>=0 && beforeTable.get((x+1)*row+(y-1)))
		neighbor++;
		if(y-1>=0 && beforeTable.get(x*row+(y-1)))
		neighbor++;
			
		return neighbor;
	}
	
	
	public void createTable() {
		
	
		for(int i=0; i<row*row; i++) {
			
			int neighbor=getNeighborsNumber(i);
			
			
			if(beforeTable.get(i) && (neighbor==2 || neighbor==3)) {
				
				afterTable.set(i, true);
			}
			else if(beforeTable.get(i) && (neighbor>3 || neighbor<2)) {
				
				afterTable.set(i, false);
			}
			else if(!beforeTable.get(i) && neighbor==3) {
				
				afterTable.set(i, true);
			}
			
		}
		
	}
	
	
	public void changeTable() {
		
		for(int i=0; i<row*row; i++) {
			
			beforeTable.set(i, afterTable.get(i));
		}
	}
	
	public boolean isTheEnd() {
		
		int counter=0;
		
		for(int i=0; i<row*row; i++) {
			
			if((beforeTable.get(i) && afterTable.get(i)) || (!beforeTable.get(i) && !afterTable.get(i)))
				counter++;
		}
		
		if(counter==row*row)
			return true;
		
		return false;
	}
	
	public void editPatternInTable(String value) throws InvalidInputValueException {
		
		
		try {
			
			int  index=Integer.parseInt(value);

			if(index<51 || index>=row*row) {
				
				throw new InvalidInputValueException();
			}
			
			for(int i=0; i<row*row; i+=50) {
				
				if(index==i) {
					throw new InvalidInputValueException();
				}
				
			}
			
			if(beforeTable.get(index)) {
				
				beforeTable.set(index, false);
				
			}
			else {
				
				beforeTable.set(index, true);
				
			}
			
		} catch (NumberFormatException e) {
			
			throw new InvalidInputValueException();
		}
		
		
		
	}
	
	
}
