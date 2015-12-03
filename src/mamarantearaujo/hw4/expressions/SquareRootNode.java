package mamarantearaujo.hw4.expressions;

public class SquareRootNode extends UnaryOperatorNode {

	public SquareRootNode(String op) {
		super(op);
	}

	@Override
	public double value() {
		return Math.sqrt(left.value());
	}

}
