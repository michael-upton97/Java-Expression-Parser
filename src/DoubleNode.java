public class DoubleNode extends ExpressionNode {
	
	public DoubleNode(double data) {
		value = data;
	}
	
	@Override
	public double getValue() {
		return value;
	}
}