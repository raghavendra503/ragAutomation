package testScripts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.interactions.Actions;

public class JavaPractice {

	static{
		System.out.println("Hi");
	}
	
	public static void sum(int a,long b) {
		System.out.println("a "+a+", b "+b);
	}
	public static void sum(long a,int b) {
		System.out.println("a "+a+1+", b "+b+1);
	}
	public static void main(String[] args,int a) {
		// TODO Auto-generated method stub\
		//Example of ambiguious for the java practice
		//sum(20, 30);
		System.out.println("6");
		

	}
//	public static void main(String args[],String a) {
//		System.out.println("4");
//	}

	
	
	List<Integer> l1=new ArrayList<Integer>();
	
	Set<String> set=new HashSet<String>();
	
	
	
	
}
