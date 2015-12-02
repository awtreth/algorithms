package mamarantearaujo.hw4.expressions;

public class ENode extends NoParameterOperatorNode {

	public ENode(String op) {
		super(op);
	}
	
	/** No Parameter operator returns its value. */
	public double value() {
		return Math.E;
	}

}
