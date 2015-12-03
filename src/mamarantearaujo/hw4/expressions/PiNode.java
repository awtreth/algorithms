package mamarantearaujo.hw4.expressions;

public class PiNode extends NoParameterOperatorNode {

	public PiNode(String op) {
		super(op);
	}

	@Override
	public double value() {
		return Math.PI;
	}

}
