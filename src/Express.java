import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class Express {
	
	public enum choices {C, L, F}
	public static HashMap<String, Double> ids = new HashMap<String, Double>();	// Stored values for IDs

	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean esc = false;
		
		while(!esc) {
			
			String userIn;						// Console Input 
			String filename;					// File name for case F
			String currentLine;					// Storage for Lines pulled from file
			Lexer myLexer = new Lexer();		// Lexer for Token Extraction
			Parser myParser;					// Parser for Expression Evaluation
			double Value = Integer.MIN_VALUE;	// Calculated value from parser
			boolean valid = true;				// Condition for value release, set to false upon error encounter
			File file;							// File for input
			Scanner readFile;					// Scanner for files
			
			
			//Welcome Wagon
			System.out.printf("\nWhat kind of Input would you like?\nEnter C for parsed console input,"
					+ "\nEnter L for Token Listing,\nEnter F <filename> for file Parsing\nOr enter Q to quit.\n\n: ");
						
			userIn = in.nextLine();
			System.out.println();
			switch(userIn.toUpperCase().trim().charAt(0)) {
				//case for quitting out of console
				case 'Q':	
					esc = true;
					break;
					
				//case for file reading, each line is first evaluate, then the value is compared with the next line
				//all lines beginning with '#' are ignored as comments
				case 'F':
					//Get File
					filename = userIn.trim().substring(1).trim();
					file = new File(filename);

					try {
						readFile = new Scanner(file); //scanner for file
						
					} catch (FileNotFoundException e) {	//if file not found
						System.out.println("Invalid Filename, try again\n");
						break;
					}
					
					//loop until all lines of file have been read
					while(readFile.hasNext()) {
						currentLine = readFile.nextLine();
						if(!currentLine.substring(0, 1).equals("#")) { //check for comment
							
							
							try {
								myLexer.setString(currentLine); //assign String to lexer 
		
							} catch (InvalidLexerCharacterException e) {
								System.out.printf("ERROR: Invalid Character for Lexing at Character '%c'\n\n", e.getChar());
								valid = false;

							} catch (NumberFormatException e) {
								System.out.printf("ERROR: Multiple Decimal points found.\n\n");
								valid = false;
							}
								
							
							//Parse String
							myParser = new Parser(myLexer); //assign Lexer to Parser
							if(valid) {
								try {
									Value = myParser.Parse().getValue(); //pull value from parser
						
								} catch (InvalidParsingInputError e) {
									System.out.printf("ERROR: Invalid Expression for Parsing at Token '%s'\n\n", e.getToken().name);
									valid = false;
								} catch (MismatchParenthesisError e) {
									System.out.println("ERROR: Mismatched Parenthesis\n");
									valid = false;
								} catch (InvalidIdentifierError e) {
									System.out.println("ERROR: Unidentified Identifier\n");
									valid = false;
								}
							}
							
							if(valid) {
								//System.out.printf("\n%f\n", Value);
								currentLine = readFile.nextLine();
								if(Math.abs(Value - Double.parseDouble(currentLine)) <= 0.000001) System.out.printf("Correct!\tFile reads: %s, Parser returns: %f.\n", currentLine, Value);
								else System.out.println("Incorrect.");
							}
							
							
						}
					}
					break;
				
				//Case for console input lexing but not parsing
				case 'L': 
					
					//Get String
					while(true) {
						System.out.print("Enter String\n: ");
						userIn = in.nextLine();
						
						//Lex String
						try {
							myLexer.setString(userIn); //assign String to lexer 
							break;
			
						} catch (InvalidLexerCharacterException e) {
				
						} catch (NumberFormatException e) {
							
						}
					}
			
					
					System.out.println();
					System.out.print(myLexer.toString());
					
					//System.out.println("\n\nPress Enter to continue...\n\n");
					//in.nextLine();
					break;
					
				//Case console input lexing and parsing
				case 'C':	
					//Get String
					while(true) {
						System.out.print("Enter String: ");
						userIn = in.nextLine();

						//Lex String
						try {
							myLexer.setString(userIn); //assign String to lexer 
							break;
			
						} catch (InvalidLexerCharacterException e) {
				
						} catch (NumberFormatException e) {
							
						}
					}
			
					
					System.out.println();
					System.out.print(myLexer.toString());
					
					//Parse String
					
					myParser = new Parser(myLexer); //assign Lexer to Parser
					
					try {
						Value = myParser.Parse().getValue(); //pull value from parser
			
					} catch (InvalidParsingInputError e) {
						System.out.printf("\n\nERROR: Invalid Expression for Parsing at Token '%s'\n\n", e.getToken().name);
						valid = false;
					} catch (MismatchParenthesisError e) {
						System.out.println("\n\nERROR: Mismatched Parenthesis\n");
						valid = false;
					} catch (InvalidIdentifierError e) {
						System.out.printf("\n\nERROR: Unidentified Identifier %s\n\n", e.getId());
						valid = false;
					}
					if(valid) {
						System.out.println();
						System.out.print(Value + "\n\n");
					}
					
					//System.out.println("\n\nPress Enter to continue...\n\n");
					//in.nextLine();
					
					break;
					
				default:
					System.out.println("Invalid Input, try again\n\n");//Press Enter to continue...\n\n");
					//in.nextLine();
					
			} // end switch statement
		} //end while loop
		
		in.close();
		
	}
}
