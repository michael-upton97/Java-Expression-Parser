public class EqualsNode extends ExpressionNode {
	
	public EqualsNode(ExpressionNode lNode, ExpressionNode rNode) throws InvalidIdentifierError {
		leftNode = lNode;		//T_ID type
		rightNode = rNode;		//T_DOUBLE type
		Express.ids.put(lNode.name, new Double(rNode.getValue())); //stores the value of the ID into the HashMap in Express
	}
	
	@Override
	public double getValue() throws InvalidIdentifierError{
		return rightNode.getValue(); //still will return value of right side expression upon initialization. 
	}
}