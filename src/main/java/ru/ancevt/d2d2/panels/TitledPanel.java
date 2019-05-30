package ru.ancevt.d2d2.panels;

public class TitledPanel extends Panel {

	protected static final String DEFAULT_TITLE_TEXT = "Titled panel";
	
	protected final Title title;
	
	public TitledPanel(String titleText) {
		super();
		title = new Title(this, titleText);
		setWidth(DEFAULT_WIDTH);
		add(title);
		title.setY(-title.getHeight());
	}
	
	public void setTitleText(String titleText) {
		title.setText(titleText);
	}
	
	public String getTitleText() {
		return title.getText();
	}
	
	public TitledPanel() {
		this(DEFAULT_TITLE_TEXT);
	}
	
	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		
		if (title == null) return;
		
		title.setWidth(width);
	}
	
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		
		if (title == null) return;
		
		title.setWidth(width);
	}
}













