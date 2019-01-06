package com.cy.test;

public abstract class BaseDictActivityResultRecoder {
	
	BaseDictActivityResultRecoder(){};
	
	public String getResAt(int pos) {return "";}
	
	public void renderContentAt(int pos) {}
	
	public int size() {return 0;}
	
	public void invalidate() {}
	
	public void invalidate(int adapter_idx) {}

	public void shutUp() {}
	
	public int dictIdx=0;

}
