package Object;

public abstract class ElementCatalogue {
	private boolean recommand�;
	
	public ElementCatalogue(boolean recommand�) {
		super();
		this.recommand� = recommand�;
	}

	public void modifierElement() {
		
	}

	public boolean isRecommand�() {
		return recommand�;
	}

	public void setRecommand�(boolean recommand�) {
		this.recommand� = recommand�;
	}
}
