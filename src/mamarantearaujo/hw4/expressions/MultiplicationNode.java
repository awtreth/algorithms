package mamarantearaujo.hw4.expressions;

public class MultiplicationNode extends BinaryOperatorNode {

	public MultiplicationNode(String op) {
		super(op);
	}

	@Override
	public double value() {
		return left.value()*right.value();
	}

}
