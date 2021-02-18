package Object;

public abstract class ElementCatalogue {
	private boolean recommandé;
	
	public ElementCatalogue(boolean recommandé) {
		super();
		this.recommandé = recommandé;
	}

	public void modifierElement() {
		
	}

	public boolean isRecommandé() {
		return recommandé;
	}

	public void setRecommandé(boolean recommandé) {
		this.recommandé = recommandé;
	}
}
