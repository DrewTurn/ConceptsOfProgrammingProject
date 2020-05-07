package conceptsProject;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Scanner_ {
    private int line;
    private int pos;
    private int position;
    private char character;
    private String s;
    private static StreamTokenizer tokens;
    private static int token;
    Map<String, TokenType> keywords = new HashMap<>();
    
    
    static class Token {
        public TokenType tokentype;
        public String value;
        public int line;
        public int pos;
        Token(TokenType token, String value){
            this.tokentype = token; 
            this.value = value; 
        }
        @Override
        public String toString() {
            String result = String.format("%-15s", this.tokentype);
            switch (this.tokentype) {
                case Number:
                    result += String.format("  %4s", value);
                    break;
                case ID:
                    result += String.format(" %s", value);
                    break;
                case String:
                    result += String.format(" \"%s\"", value);
                    break;
            }
            return result;
        }
    }
 
    static enum TokenType {
        Multiplication,  
        Division, 
        Addition, 
        Subtraction,
        Equal, 
        Assignment, 
        Or, 
        Read, 
        Write,
        LeftParen, 
        RightParen,
        Semicolon, 
        Comma, 
        ID, 
        Number, 
        End_of_input, 
        String
    }
 
    static void error(int line, int pos, String msg) {
        if (line > 0 && pos > 0) {
            System.out.printf("%s in line %d, pos %d\n", msg, line, pos);
        } else {
            System.out.println(msg);
        }
        System.exit(1);
    }
 
    Scanner_(String source) {
        this.line = 1;
        this.pos = 0;
        this.position = 0;
        this.s = source;
        this.character = this.s.charAt(0);
        this.keywords.put("Read", TokenType.Read);
        this.keywords.put("Write", TokenType.Write);
 
    }
    
    Token follow(char expectedChar, TokenType pass, TokenType fail, int line, int pos) {
        if (getNextChar() == expectedChar) {
            getNextChar();
            return new Token(pass, "");
        }
        if (fail == TokenType.End_of_input) {
            error(line, pos, String.format("follow: unrecognized character: (%d) '%c'", (int)this.character, this.character));
        }
        return new Token(fail, "");
    }
    
    
    
    Token commentCheck(int line, int pos) {
        if (getNextChar() != '*') {
            return new Token(TokenType.Division, "");
        }
        getNextChar();
        while (true) { 
            if (this.character == '\u0000') {
                error(line, pos, "EOF in comment");
            } else if (this.character == '*') {
                if (getNextChar() == '/') {
                    getNextChar();
                    return getToken();
                }
            } else {
                getNextChar();
            }
        }
    }
    
    
    
    Token idCheck(int line, int pos) {
        boolean numberCheck = true;
        String text = "";
 
        while (Character.isAlphabetic(this.character) || Character.isDigit(this.character) || this.character == '_') {
            text += this.character;
            if (!Character.isDigit(this.character)) {
                numberCheck = false;
            }
            getNextChar();
        }
 
        if (text.equals("")) {
            error(line, pos, String.format("unrecognized character: (%d) %c", (int)this.character, this.character));
        }
 
        if (Character.isDigit(text.charAt(0))) {
            if (!numberCheck) {
                error(line, pos, String.format("invalid number: %s", text));
            }
            return new Token(TokenType.Number, text);
        }
 
        if (this.keywords.containsKey(text)) {
            return new Token(this.keywords.get(text), "");
        }
        return new Token(TokenType.ID, text);
    }
    
    
    
    Token getToken() {
        int line, pos;
        while (Character.isWhitespace(this.character)) {
            getNextChar();
        }
        line = this.line;
        pos = this.pos;
 
        switch (this.character) {
            case '\u0000': 
            	return new Token(TokenType.End_of_input, "");
            case '/': 
            	return commentCheck(line, pos);
            case '=': 
            	return follow('=', TokenType.Equal, TokenType.Assignment, line, pos);
            case '|': 
            	return follow('|', TokenType.Or, TokenType.End_of_input, line, pos);
            case '(': 
            	getNextChar(); 
            	return new Token(TokenType.LeftParen,"" );
            case ')': 
            	getNextChar(); 
            	return new Token(TokenType.RightParen, "");
            case '+': 
            	getNextChar();
            	return new Token(TokenType.Addition, "");
            case '-': 
            	getNextChar(); 
            	return new Token(TokenType.Subtraction, "");
            case '*': 
            	getNextChar();
            	return new Token(TokenType.Multiplication, "");
            case ';':
            	getNextChar(); 
            	return new Token(TokenType.Semicolon, "");
            case ',': 
            	getNextChar(); 
            	return new Token(TokenType.Comma, "");
 
            default: return idCheck(line, pos);
        }
    }
 
    char getNextChar() {
        this.pos++;
        this.position++;
        if (this.position >= this.s.length()) {
            this.character = '\u0000';
            return this.character;
        }
        this.character = this.s.charAt(this.position);
        if (this.character == '\n') {
            this.line++;
            this.pos = 0;
        }
        return this.character;
    }
 
    void printTokens() {
        Token t;
        while ((t = getToken()).tokentype != TokenType.End_of_input) {
            System.out.println(t);
        }
        System.out.println(t);
    }
    
  
    
    private static String stringmade(String filePath) 
    {
        String content = "";
 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        return content;
    }

    public static void main(String[] args) {
    	ArrayList<String> list = new ArrayList<String>();
         if (args.length == 0) {
            try {
            	String fileName = "foo.txt";
                File f = new File(fileName);
                Scanner s = new Scanner(f);
                String source = " ";
                while (s.hasNext()) {
                    source += s.nextLine() + "\n";
                }
                Scanner_ finalCheck = new Scanner_(source);
                finalCheck.printTokens();
                s.close();
            } catch(FileNotFoundException e) {
                error(-1, -1, "Exception: " + e.getMessage());
            }
          
        } else {
            error(-1, -1, "No args");
        }
       //this will print tokens to screen  
        
       //this will   
        /*
        	String fileName = "foo.txt";
        	String str1 = stringmade(fileName);
        	StringTokenizer TokensOfInput = new StringTokenizer(str1);
        	while (TokensOfInput.hasMoreTokens()) {
                list.add(TokensOfInput.nextToken());
            }  
          Parser Parser = new Parser();
        //this is where call to parser will be list1 is list of all tokens
        	Parser.Parser(list,0);
        
        
        String t="$$";//temp 
		if(t=="$$") {
        	System.out.println("Parsing Successful"); 
        }
        else {
        	System.out.println("Error");
        	return;
        }
        */
    }
}