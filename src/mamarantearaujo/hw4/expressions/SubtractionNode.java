package mamarantearaujo.hw4.expressions;

public class SubtractionNode extends BinaryOperatorNode {

	public SubtractionNode(String op) {
		super(op);
	}

	@Override
	public double value() {
		return left.value()-right.value();
	}

}
