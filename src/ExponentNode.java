public class ExponentNode extends ExpressionNode {
	
	public ExponentNode(ExpressionNode lNode, ExpressionNode rNode) {
		leftNode = lNode;
		rightNode = rNode;
	}
	
	@Override
	public double getValue() throws InvalidIdentifierError{
		return Math.pow(leftNode.getValue(), rightNode.getValue());
	}
}