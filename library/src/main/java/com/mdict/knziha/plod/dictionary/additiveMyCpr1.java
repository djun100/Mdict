package com.mdict.knziha.plod.dictionary;

public class additiveMyCpr1 implements Comparable<additiveMyCpr1>{
	public String key;
	public Object value;
	public additiveMyCpr1(String k,Object v){
		key=k;value=v;
	}
	
	public int compareTo(additiveMyCpr1 other) {
		//UtilMy.show(this.key.replaceAll(UtilMy.replaceReg,UtilMy.emptyStr));
		return mdict.replaceReg.matcher(key).replaceAll(mdict.emptyStr).toLowerCase().compareTo(mdict.replaceReg.matcher(other.key).replaceAll(mdict.emptyStr).toLowerCase());
	}
	public String toString(){
		String str = ""; //for(Integer i:value) str+="@"+i;
		return key+"____"+str;
	}
}