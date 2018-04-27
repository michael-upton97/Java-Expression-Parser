public class ParenNode extends ExpressionNode {
	
	public ParenNode() {
		value = 0;
	}
	
	@Override
	public double getValue() {
		return value;
	}
}