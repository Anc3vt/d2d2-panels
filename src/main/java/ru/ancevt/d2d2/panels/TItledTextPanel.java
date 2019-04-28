package ru.ancevt.d2d2.panels;

public class TItledTextPanel extends TitledPanel {
	public final TextInput textInput;
	
	public TItledTextPanel() {
		textInput = new TextInput() {
			@Override
			public void onTextEnter() {
				TItledTextPanel.this.onTextEnter();
				super.onTextEnter();
			}
			
			@Override
			public void onAddToStage() {
				Focus.setFocusedComponent(textInput);
			}
		};
		add(textInput);
		
		setSize(140, 30);
		textInput.setY(7);;
		textInput.setWidth(140);
	}
	
	public void onTextEnter() {
		
	}
}
