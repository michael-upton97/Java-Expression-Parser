public abstract class ExpressionNode {
	public ExpressionNode leftNode;		// Left hand node used for first term in operation
	public ExpressionNode rightNode;	// Right hand node used for second term in operation
										// Either node above could have large branches of node below
	protected double value;				// This stores the value in ID and double, otherwise is unused
	protected String name;				// This will store the string representation of the token or the name of the identifier
	
	// This will return the value of a double or ID, or will find the value after the operation 
	// type and return that, allowing for the value of the whole tree to be evaluated with one call. 
	public abstract double getValue() throws InvalidIdentifierError;	
}