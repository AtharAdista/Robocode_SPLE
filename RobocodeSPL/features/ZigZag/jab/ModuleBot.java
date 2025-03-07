package jab;

public class ModuleBot extends Module {
	protected Movement getSelectedMovement() {		
		return new ZigZag(this);
	}
}
