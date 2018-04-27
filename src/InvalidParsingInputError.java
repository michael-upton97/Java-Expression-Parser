public class InvalidParsingInputError extends Exception{
	private static final long serialVersionUID = 1L; //to prevent compiler warning
	private Token tkn;
   
   public InvalidParsingInputError(Token invalid){
      this.tkn = invalid;
   }
   
   public Token getToken(){
      return tkn;
   }

}

/* 
 * This error is thrown when the expression sent to the parser is not an expression that can be evaluated
 * for example: 567.4 + 90 (3 - 4)
 */


