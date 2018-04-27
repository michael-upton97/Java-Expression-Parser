public class InvalidIdentifierError extends Exception{
	private static final long serialVersionUID = 1L; //to prevent compiler warning
	private String id;
   
   public InvalidIdentifierError(String id){
      this.id = id;
   }
   
   public String getId(){
      return id;
   }

}

/* 
 * This Error is for the parsing process, when an identifier type Node is sent into the parser
 * without being initialized into the HashMap first, making it impossible to evaluate. 
 */

