	public class IDNode extends ExpressionNode {
		
	public IDNode(String name) {
		this.name = name;
	}
	
	@Override
	public double getValue() throws InvalidIdentifierError{
		if(!Express.ids.containsKey(name)) throw new InvalidIdentifierError(name);
		return Express.ids.get(name);
	}
}