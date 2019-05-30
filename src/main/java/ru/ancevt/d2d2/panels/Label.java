package ru.ancevt.d2d2.panels;

import ru.ancevt.d2d2.display.Color;
import ru.ancevt.d2d2.display.text.BitmapText;

public class Label extends BitmapText {
	
	public Label(String label) {
		this();
		setText(label);
	}
	
	public Label() {
		setColor(Color.BLACK);
	}
}
