package conceptsProject;

import java.util.*;
import java.io.*;
/*
 * 
<program> → <stmt list> $$

<stmt list> → <stmt> <stmt list> | null

<stmt> → id assign <expr> | read id | write <expr>

<expr> → <term> <term tail>

<term tail> → <add op> <term> <term tail> | null

<term> → <factor> <fact tail>

<fact tail> → <mult op> <factor> <fact tail> | null

<factor> → lparen <expr> rparen | id | number

<add op> → plus | minus

<mult op> → times | div
 * 
 */
public class Parser {
	 

	
	
	public String Parser(List input,int counter) {
		System.out.println("<Program>");
		//code to call next is here
		//calls <stmt list> $$
		StatmentList(input, counter);
		System.out.println("</Program>");
		return "$$";
	}
	
	public static void StatmentList(List input,int counter) {
		//calls <stmt><stmt list> or null
		//code
		
		 if(input.get(counter)=="\u0000"){
		 //supposte to check to see if end of file and if is return and exit make sure it works and if not why
			 return;
		 }		 
		System.out.println("<stmt list>");
		Statment(input, counter);
		StatmentList(input, counter);
		System.out.println("</stmt list>");
		return;
	}
	
	public static void Statment(List input,int counter) {
		System.out.println("<stmt>");
		//code
		//call id assign <expr> or read id or write <expr>
		try {
		Integer.parseInt(String.valueOf(input.get(counter)));//so what this does is convert  input.get(counter to sting then checks if string is an int
			//call write exprestion
		}
		catch(NumberFormatException nfe){
			//if it is here then it is not a number so call it read and return
			counter++;
			System.out.println(input);
			System.out.println("</stmt>");
			return;
		}
		//if here then it is a number and procced without going to the next slot on the list because as a number that is done later
		expr(input, counter);		
		System.out.println("</stmt>");
	}
	public static void expr(List input,int counter) {
		System.out.println("<expr>");
		//code
		//call <term><term tail>
		term(input, counter);
		term_tail(input, counter);
		System.out.println("</expr>");
	}
	public static void term_tail(List input,int counter) {
		System.out.println("<term tail>");
		//code
		//if(null){
		//return
		//}
		//<add op> <term> <term tail> or null
		add_op(input, counter);
		term(input, counter);
		term_tail(input, counter);
		System.out.println("</term tail>");
	}
	public static void term(List input,int counter) {
		System.out.println("<term>");
		//code
		
		//go to <factor> <fact tail>
		factor(input, counter);
		fact_tail(input, counter);
		System.out.println("</term>");
	}
	public static void fact_tail(List input,int counter) {
		System.out.println("<fact tail>");
		//code
		//if(null){
		//return
		//}
		//<mult op> <factor> <fact tail> or null
			mult_op(input, counter);
			factor(input, counter);
			fact_tail(input, counter);
		System.out.println("</fact tail>");
	}
	public static void factor(List input,int counter) {
		System.out.println("<factor>");
		//code
		//lparen <expr> rparen or id or number
		System.out.println("</factor>");
	}
	public static void add_op(List input,int counter) {
		System.out.println("<add_op>");
		
		//plus or minus
		/*if() {
			System.out.println("+");
		}else {
			System.out.println("-");
		}*/
		System.out.println("</add_op>");
	}    
	public static void mult_op(List input,int counter) {
		System.out.println("<mult op>");
		//times or div
				if(input.get(counter)=="*") {
					System.out.println("*");
				}else if(input.get(counter)=="/") {
					System.out.println("/");
				}
				else {
					System.out.println("Error mult_op");
					System.exit(0);
				}
					counter++;
		System.out.println("</mult op>");
		return;
	} 
	
	public static void match() {
		//do i need?
	}
}
