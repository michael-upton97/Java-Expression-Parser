public class DivNode extends ExpressionNode {
	
	public DivNode(ExpressionNode lNode, ExpressionNode rNode) {
		leftNode = lNode;
		rightNode = rNode;
	}
	
	@Override
	public double getValue() throws InvalidIdentifierError {
		return leftNode.getValue() / rightNode.getValue();
	}
}