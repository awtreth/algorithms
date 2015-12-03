package mamarantearaujo.hw4.expressions;

public class ExponentNode extends BinaryOperatorNode {

	public ExponentNode(String op) {
		super(op);
	}

	@Override
	public double value() {
		return Math.pow(left.value(), right.value());
	}

}
