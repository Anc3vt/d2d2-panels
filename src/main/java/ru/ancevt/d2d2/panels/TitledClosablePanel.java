package ru.ancevt.d2d2.panels;

public class TitledClosablePanel extends TitledPanel {
	
	private Button buttonClose;
	
	public TitledClosablePanel() {
		this(DEFAULT_TITLE_TEXT);
	}
	
	public TitledClosablePanel(String titleText) {
		super(titleText);
		buttonClose = new Button("x") {
			@Override
			public void onButtonPressed() {
				onCloseButtonPressed();
				super.onButtonPressed();
			}
		};
		buttonClose.setSize(26, title.getHeight() - 1);
		buttonClose.setY(-title.getHeight());
		buttonClose.setX(getWidth() - buttonClose.getWidth());
		add(buttonClose);
	}
	
	public void onCloseButtonPressed() {
		
	}

	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		
		if(buttonClose != null) {
			buttonClose.setX(getWidth() - buttonClose.getWidth());
		}
	}
}
