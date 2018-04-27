public class Token {
	public enum types {T_NULL, T_ID, T_DOUBLE, T_EQUALS, T_PLUS, T_MINUS, T_MULT, T_DIV, T_LEFT_PAREN, T_RIGHT_PAREN, T_EXPO, T_MOD} //possible types of Tokens
	
	/*	NOTE
				In terms of order of operations, the exponent and the modulus are considered to be on the same level as 
				the divide. meaning they will be done first, unless consecutive to a divide function, when they will be 
				evaluated in the order in which they are written
	*/
	
	types type;		// field for type (above)
	String name;	// field for name (usually character, id name in T_ID and string representation of double in T_DOUBLE)
	double value;	// field for value of token (Usually 0 unless predefined ID or double)

	// CONSTRUCTOR
	public Token(types type, String name, double value){ 
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	//returns formatted string for Lexer output
	@Override
	public String toString() {
		return String.format("%-15s%12s%13s\n", this.type, this.name, this.value);
	}
}
