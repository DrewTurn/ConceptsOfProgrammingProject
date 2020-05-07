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
	 public static int counter = 0;

	
	
	public String Parser(List input) {
		System.out.println("<Program>");
		//code to call next is here
		//calls <stmt list> $$
		StatmentList(input);
		System.out.println("</Program>");
		return "$$";
	}
	
	public static void StatmentList(List input) {
		//calls <stmt><stmt list> or null
		//code
		
		 if(input.size()==counter){
		 //supposte to check to see if end of file and if is return and exit make sure it works and if not why
			 return;
		 }		 
		System.out.println("	<stmt list>");
		Statment(input);
		StatmentList(input);
		System.out.println("	</stmt list>");
		return;
	}
	
	public static void Statment(List input) {
		System.out.println("		<stmt>");
		//code
		//call id assign <expr> or read id or write <expr>
		try {
		Integer.parseInt(String.valueOf(input.get(counter)));//so what this does is convert  input.get  to sting then checks if string is an int
			//call write exprestion
		}
		catch(NumberFormatException nfe){
			//if it is here then it is not a number so call it read and return
			System.out.println("			Read "+input.get(counter));
			counter++;
			System.out.println("		</stmt>");
			return;
		}
		//if here then it is a number and procced without going to the next slot on the list because as a number that is done later
		expr(input);		
		System.out.println("		</stmt>");
	}
	public static void expr(List input) {
		System.out.println("			<expr>");
		//code
		//call <term><term tail>
		term(input);
		term_tail(input);
		System.out.println("			</expr>");
	}
	public static void term_tail(List input) {
	
		//code
		try {
			Integer.parseInt(String.valueOf(input.get(counter)));//so what this does is convert  input.get  to sting then checks if string or an int
			
			}
			catch(NumberFormatException nfe){
				//if it is here then it is not a number so call it read and return
				System.out.println("					<term tail>");
				System.out.println("						Null");	
				System.out.println("					</term tail>");
				return;
			}
			//
		if(String.valueOf(input.get(counter))=="+"||String.valueOf(input.get(counter))=="-") {
		System.out.println("					<term tail>");
		add_op(input);
		term(input);
		term_tail(input);
		System.out.println("					</term tail>");
		return;
		}
		
	}
	public static void term(List input) {
		System.out.println("				<term>");
		//code
		
		//go to <factor> <fact tail>
		factor(input);
		fact_tail(input);
		System.out.println("				</term>");
	}
	public static void fact_tail(List input) {
		//<mult op> <factor> <fact tail> or null
		try {
			Integer.parseInt(String.valueOf(input.get(counter)));//so what this does is convert  input.get  to sting then checks if string or an int
			
			}
			catch(NumberFormatException nfe){
				//if it is here then it is not a number so return and end branch
				return;
			}
		
		System.out.println("					<fact tail>");
		//--------------------
		
		if(String.valueOf(input.get(counter))=="*"||String.valueOf(input.get(counter))=="/") {
			mult_op(input);
			factor(input);
			fact_tail(input);
			System.out.println("					</fact tail>");
			return;
		}
		System.out.println("						Null");	
		System.out.println("					</fact tail>");
	}
	public static void factor(List input) {
		//code
		try {
			Integer.parseInt(String.valueOf(input.get(counter)));//so what this does is convert  input.get  to sting then checks if string or an int
			}
			catch(NumberFormatException nfe){
				//if it is here then it is not a number so call it read and return
				System.out.println("Error Factor Catch");
				System.exit(0);
				
			}
		//lparen <expr> rparen or id or number
		
		if(input.get(counter+1)=="+"||input.get(counter+1)=="-"||input.get(counter+1)=="*"||input.get(counter+1)=="/") {
			System.out.println("					<factor>");
			System.out.println("				"+input.get(counter));
			counter++;
			expr(input);
			System.out.println(input.get(counter));
			System.out.println("					</factor>");
			return;
		}
		else {
			System.out.println("					<factor>");
			System.out.println("						"+input.get(counter));
			System.out.println("					</factor>");
			counter++;
			return;
		}
		
		
	}
	public static void add_op(List input) {
		System.out.println("						<add_op>");
		
		//plus or minus
		if(String.valueOf(input.get(counter))=="+") {
			System.out.println("						+");
		}else if(String.valueOf(input.get(counter))=="-") {
			System.out.println("						-");
		}
		else {
			System.out.println("Error mult_op");
			System.exit(0);
		}
			counter++;
		System.out.println("						</add_op>");
	}    
	public static void mult_op(List input) {
		System.out.println("						<mult op>");
		//times or div
				if(String.valueOf(input.get(counter))=="*") {
					System.out.println("						*");
				}else if(String.valueOf(input.get(counter))=="/") {
					System.out.println("						/");
				}
				else {
					System.out.println("Error mult_op");
					System.exit(0);
				}
					counter++;
		System.out.println("						</mult op>");
		return;
	} 
	
	public static void match() {
		//do i need?
	}
}
