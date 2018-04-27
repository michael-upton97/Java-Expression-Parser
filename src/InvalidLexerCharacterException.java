public class InvalidLexerCharacterException extends Exception{
	private static final long serialVersionUID = 1L; //to prevent compiler warning
	private char letter;
   
   public InvalidLexerCharacterException(char invalid){
      this.letter = invalid;
   }
   
   public char getChar(){
      return letter;
   }
}

/* 
 * This Error is used in the lexing function when a character is not valid for the lexer to handle
 */

