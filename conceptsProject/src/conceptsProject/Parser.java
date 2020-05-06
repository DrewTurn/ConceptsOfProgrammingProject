package conceptsProject;

import java.util.*;

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


	
	
	public static void Parser(String input) {
		System.out.println("<Program>");
		//code to call next is here
		//calls <stmt list> $$
		StatmentList();
		System.out.println("</Program>");
		//return "$$";
	}
	
	public static void StatmentList() {
		//calls <stmt><stmt list> or null
		//code
		/*
		 if(nil){
		 return
		 }
		 		 
		 */
		System.out.println("<stmt list>");
		Statment();
		StatmentList();
		System.out.println("</stmt list>");
	}
	
	public static void Statment() {
		System.out.println("<stmt>");
		//code
		//call id assign <expr> or read id or write <expr>
		System.out.println("</stmt>");
	}
	public static void expr() {
		System.out.println("<expr>");
		//code
		//call <term><term tail>
		term();
		term_tail();
		System.out.println("</expr>");
	}
	public static void term_tail() {
		System.out.println("<term tail>");
		//code
		//if(null){
		//return
		//}
		//<add op> <term> <term tail> or null
		add_op();
		term();
		term_tail();
		System.out.println("</term tail>");
	}
	public static void term() {
		System.out.println("<term>");
		//code
		
		//go to <factor> <fact tail>
		factor();
		fact_tail();
		System.out.println("</term>");
	}
	public static void fact_tail() {
		System.out.println("<fact tail>");
		//code
		//if(null){
		//return
		//}
		//<mult op> <factor> <fact tail> or null
					mult_op();
			factor();
			fact_tail();
		System.out.println("</fact tail>");
	}
	public static void factor() {
		System.out.println("<factor>");
		//code
		//lparen <expr> rparen or id or number
		System.out.println("</factor>");
	}
	public static void add_op() {
		System.out.println("<add_op>");
		
		//plus or minus
		/*if() {
			System.out.println("+");
		}else {
			System.out.println("-");
		}*/
		System.out.println("</add_op>");
	}    
	public static void mult_op() {
		System.out.println("<mult op>");
		//times or div
				/*if() {
					System.out.println("*");
				}else {
					System.out.println("/");
				}*/
		System.out.println("</mult op>");
	} 
	
	public static void match() {
		
	}
}
