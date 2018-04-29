public class Parser {

	Lexer Tokens;
	Token currentToken;
	
	//CONSTRUCTOR
	public Parser(Lexer input) {
		Tokens = input;
	}
	
	//Single call "main" function. Calls Expression()
	public ExpressionNode Parse() throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError {
		Tokens.ResetToken();
		ExpressionNode Output = Expression();
		currentToken = Tokens.getNextToken();
		if(currentToken != null){
			if(currentToken.type == Token.types.T_RIGHT_PAREN) throw new MismatchParenthesisError();
			else throw new InvalidParsingInputError(currentToken);
		}
		return Output;
	}
	
	//Comprises of a Term followed by a RestExpr. the former is assigned as the left node of the latter in RestExpr()
	private ExpressionNode Expression() throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError {
		return RestExpr(Term());
	}
	
	// Searches for a low significant (order of Operations) operation, if found recursively calls itself with the operators node as the leftNode
	// if an equals is found the left node of an equals node is the ID and the left branch becomes another expression
	// otherwise there must no more tokens as the higher significant operations are handled in terms
	private ExpressionNode RestExpr(ExpressionNode term) throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError {
		currentToken = Tokens.getNextToken();
		if(currentToken == null) return term;
		switch (currentToken.type){
			case T_PLUS:	return RestExpr(new AddNode(term, Term()));
			
			case T_MINUS:	return RestExpr(new SubtractNode(term, Term()));

			case T_EQUALS:	return new EqualsNode(new IDNode(term.name), Expression());	
			
			default:		Tokens.getPrevToken(); //to compensate for invalid token
							return term;
		}
	}
	
	//Like Expression but Comprises of a Factor followed by a RestTerm. the former is assigned as the left node of the latter in RestTerm()
	private ExpressionNode Term() throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError {
		return RestTerm(Complex());
	}
	
	// Searches for a moderately significant (order of Operations) operation, if found recursively calls itself with the operators node as the leftNode
	// otherwise the initial factor taken in as a parameter is returned back up to Term()
	private ExpressionNode RestTerm(ExpressionNode complex) throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError {
		currentToken = Tokens.getNextToken();
		if(currentToken == null) return complex;
		switch (currentToken.type){
			case T_MULT:	return RestTerm(new MulNode(complex, Complex()));
			
			case T_DIV:		return RestTerm(new DivNode(complex, Complex()));		
			
			default:		Tokens.getPrevToken(); //to compensate for invalid token
							return complex;
		}
	}
	
	//Like Expression and Term but Comprises of a Factor followed by a RestComplex. the former is assigned as the left node of the latter in RestComplex()
	private ExpressionNode Complex() throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError {
		return RestComplex(Factor());
	}
	
	// Searches for the highest significant (order of Operations) operation, if found recursively calls itself with the operators node as the leftNode
	// otherwise the initial factor taken in as a parameter is returned back up to Term()
	private ExpressionNode RestComplex(ExpressionNode factor) throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError {
		currentToken = Tokens.getNextToken();
		if(currentToken == null) return factor;
		switch (currentToken.type){
		
			case T_EXPO:	return RestTerm(new ExponentNode(factor, Factor()));		
			
			case T_MOD:		return RestTerm(new ModulusNode(factor, Factor()));		
			
			default:		Tokens.getPrevToken(); //to compensate for invalid token
							return factor;
		}
	}
	
	// Bottom of recursion, everything here is wither an id, a double or a parentheses 
	// assigned then passes back up to Term() or RestTerm()
	private ExpressionNode Factor() throws InvalidParsingInputError, MismatchParenthesisError, InvalidIdentifierError{
		currentToken = Tokens.getNextToken();
		if(currentToken == null) throw new InvalidParsingInputError(currentToken);
		switch (currentToken.type){
			case T_MINUS:		return new SubtractNode(new DoubleNode(0), Factor()); //for negative doubles
			
			case T_ID:			return new IDNode(currentToken.name);
			
			case T_DOUBLE:		return new DoubleNode(currentToken.value);
						
			case T_LEFT_PAREN: 	ExpressionNode tempNode = Expression();
								currentToken = Tokens.getNextToken();
								if(currentToken == null) throw new MismatchParenthesisError();
								if(currentToken.type == Token.types.T_RIGHT_PAREN) return tempNode;
								else throw new InvalidParsingInputError(currentToken);
								
		
			default:			throw new InvalidParsingInputError(currentToken);
			
		}
		
	}
}



