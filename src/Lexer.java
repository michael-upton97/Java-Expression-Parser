import java.util.LinkedList;

public class Lexer{

	String inputString;			// Storage for lexer string input
	Token CurrentToken;			// Storage for token being at tokenIndex 
	LinkedList<Token> Tokens;	// List of tokens completed in LexIt()
	int tokenIndex = 0;			// position in list indicating CurrentToken(for psuedo iterator)

	//Sets inputString and calls LexIt()
	public void setString(String input) throws InvalidLexerCharacterException{
		inputString = input;
		CurrentToken = null;
		Tokens = new LinkedList<Token>();
		try {
			LexIt();
		} catch (InvalidLexerCharacterException e) {	//Error for if character is invalid
			System.out.printf("Invalid character: %c\n\n", e.getChar());
			throw e;
		} catch (java.lang.NumberFormatException e) {	//Error for if multiple decimal points are entered in a single double
			System.out.printf("Multiple Decimal Points Error\n\n");
			throw e;
		}
	}

	// Takes full inputString and breaks it into Tokens (types defined in Token.types)
	private void LexIt() throws InvalidLexerCharacterException, NumberFormatException{
		String buffer;	//buffer for holding characters part of an ID or Double. 

		for(int index = 0; index < inputString.length(); index++){ //index will determine how many characters into the String we are (edited in ID and Double)
			switch (inputString.charAt(index)){	//checks for basic operators and symbols and assigns them tokens
				case '(':	Tokens.add(new Token(Token.types.T_LEFT_PAREN, "(", 0));
							break;

				case ')':	Tokens.add(new Token(Token.types.T_RIGHT_PAREN, ")", 0));
							break;

				case '=':	Tokens.add(new Token(Token.types.T_EQUALS, "=", 0));
							break;

				case '+':	Tokens.add(new Token(Token.types.T_PLUS, "+", 0));
							break;

				case '-':	Tokens.add(new Token(Token.types.T_MINUS, "-", 0));
							break;

				case '*':	Tokens.add(new Token(Token.types.T_MULT, "*", 0));
							break;

				case '/':	Tokens.add(new Token(Token.types.T_DIV, "/", 0));
							break;
							
				case '^':	Tokens.add(new Token(Token.types.T_EXPO, "^", 0));
							break;
				
				case '%':	Tokens.add(new Token(Token.types.T_MOD, "%", 0));
							break;

				case ' ':	break;

				default:	// checks for double or ID and assigns them tokens
				
				if(Character.isDigit(inputString.charAt(index)) || inputString.charAt(index) == '.'){	//first character in a double could be a number or a '.'

					buffer = "" + inputString.charAt(index); 	//takes first character and stores it as a String in buffer
					index++;									//move to next character so we can test if its part of this token

					//while character at index is valid for this token add it to the buffer and increase the index
					while(index < inputString.length() && (Character.isDigit(inputString.charAt(index)) || inputString.charAt(index) == '.') ) { 
						buffer += inputString.charAt(index);
						index++;
					}
					
					//we have found an invalid character
					try {
						Tokens.add(new Token(Token.types.T_DOUBLE, buffer, Double.parseDouble(buffer))); //convert the buffer into a token
						index--;	//move backwards in index to compensate for the incrementing due to the 'for' loop
					} catch (Exception e) {	
						throw new NumberFormatException();	//this only occurs if there are multiple decimal points thus the buffer cannot become a double. 
					}
				}

				else if(Character.isLetter(inputString.charAt(index))){

					buffer = "" + inputString.charAt(index); 	//takes first character and stores it as a String in buffer
					index++;									//move to next character so we can test if its part of this token

					//while character at index is valid for this token add it to the buffer and increase the index
					while(index < inputString.length() && (Character.isLetter(inputString.charAt(index)) || Character.isDigit(inputString.charAt(index))|| inputString.charAt(index) == '.') ) {
						buffer += inputString.charAt(index);
						index++;
					}
					
					//we have found an invalid character					
					if(!Express.ids.containsKey(buffer)) Tokens.add(new Token(Token.types.T_ID, buffer, 0));	//convert the buffer into a token, but with value 0 as id isn't identified
					else Tokens.add(new Token(Token.types.T_ID, buffer, Express.ids.get(buffer)));				//convert the buffer into a token, but assign value to the id if already initialized
					index--;	//move backwards in index to compensate for the incrementing due to the 'for' loop
				}

				else {	// case if not a valid symbol or double/ID
					throw new InvalidLexerCharacterException(inputString.charAt(index));
				}
				

			}
		}
	}

	// Acts as a iterator.next() for the tokenList
	public Token getNextToken(){
		if(Tokens.size() > ++tokenIndex){ 			// increase tokenIndex first then check if in range
			CurrentToken = Tokens.get(tokenIndex);	// set CurrentToken before returning it
			return CurrentToken;
		}

		else return null;
	}
	
	// function moves backwards in list
	public Token getPrevToken(){
		if(Tokens.size() > --tokenIndex && tokenIndex >= 0){	// decrease tokenIndex first then check if in range
			CurrentToken = Tokens.get(tokenIndex);				// set CurrentToken before returning it
			return CurrentToken;
		}

		else return null;
	}
		
	//function returns index to -1 so when getNextToken() is called and index is incremented it starts at 0
	public void ResetToken(){
		tokenIndex = -1;
	}
	
	//Prints header for Lexer console output, then prints String version
	@Override
	public String toString() {
		String output = String.format("%-15s%12s%13s\n========================================\n", "TOKEN TYPE", "Lexed Value", "Value");
		
		for(Token t: Tokens) {
			output += t.toString();
		}
		
		return output;
	}
}








