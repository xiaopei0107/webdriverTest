package com.zxp.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String aa = "a";
		List list = new ArrayList();
		list.add("bb");
		list.add("a");
		list.add("c");
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(aa)){
				continue;
			}
			System.out.println(list.get(i));
		}
	}

}
