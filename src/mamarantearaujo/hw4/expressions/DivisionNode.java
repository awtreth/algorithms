package mamarantearaujo.hw4.expressions;

public class DivisionNode extends BinaryOperatorNode {

	public DivisionNode(String op) {
		super(op);
	}

	@Override
	public double value() {
		return left.value()/right.value();
	}

}
