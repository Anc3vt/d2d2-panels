package ru.ancevt.d2d2.panels;

import ru.ancevt.d2d2.display.Color;
import ru.ancevt.d2d2.display.Sprite;
import ru.ancevt.d2d2.display.text.BitmapText;
import ru.ancevt.d2d2.touch.TouchButton;

public class Button extends Panel {
	
	private static final int PADDING = 4;
	private static final String DEFAULT_BUTTON_LABEL_TEXT = "Button";
	private static final float DEFAULT_WIDTH = 120;
	private static final float DEFAULT_HEIGHT = 30;
	private static final Color COLOR = Color.BLACK;
	private static final Color DISABLED_COLOR = Color.GRAY;
	
	private BitmapText label;
	private TouchButton touchButton;
	private Sprite icon;
	private boolean pressed;
	
	public Button() {
		this(DEFAULT_BUTTON_LABEL_TEXT);
	}
	
	public Button(String labelText) {
		label = new BitmapText();
		label.setColor(COLOR);
		label.setText(labelText);
		
		touchButton = new TouchButton() {
			@Override
			public boolean onTouchDown(int x, int y) {
				Focus.setFocusedComponent(Button.this);
				
				borderLeft.setColor(BORDER_COLOR_2);
				borderRight.setColor(BORDER_COLOR_1);
				borderTop.setColor(BORDER_COLOR_2);
				borderBottom.setColor(BORDER_COLOR_1);
				label.move(1, 1);
				pressed = true;
				super.onTouchDown(x, y);
				return false;
			}
			
			@Override
			public boolean onTouchUp(int x, int y, boolean onArea) {
				borderLeft.setColor(BORDER_COLOR_1);
				borderRight.setColor(BORDER_COLOR_2);
				borderTop.setColor(BORDER_COLOR_1);
				borderBottom.setColor(BORDER_COLOR_2);
				label.move(-1, -1);
				
				if(!pressed) return false ;
				
				onButtonPressed();
				
				pressed = false;
				super.onTouchUp(x, y, onArea);
				return false;
			}
		};
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		add(touchButton);
		touchButton.setEnabled(true);
		add(label);
		setEnabled(true);
	}
	
	public void onButtonPressed() {
		
	}
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public String getText() {
		return label.getText();
	}
	
	public void setBackgroundColor(Color color) {
		background.setColor(color);
	}
	
	public Color getBackgroundColor() {
		return background.getColor();
	}
	
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		
		if(label != null && touchButton != null) {
			final int charHeight = label.getBitmapFont().getCharHeight();
			label.setBounds(width - PADDING*2, charHeight);
			label.setX(PADDING);
			touchButton.setWidth((int)width);
		}
		
		if(icon != null) {
			icon.setX((width - icon.getWidth() * icon.getAbsoluteScaleX()) / 2);
		}
	}
	
	@Override
	public void setHeight(float height) {
		super.setHeight(height);

		if(label != null && touchButton != null) {
			final int charHeight = label.getBitmapFont().getCharHeight();
			touchButton.setHeight((int)height);
			label.setY(getAbsoluteScaleY() * (height - charHeight) / 2);
		}
		
		if(icon != null) {
			icon.setY((height - icon.getHeight()*  icon.getAbsoluteScaleY()) / 2);
		}
	}
	
	@Override
	public void setSize(float width, float height) {
		setWidth(width);
		setHeight(height);
	}

	public boolean isEnabled() {
		return touchButton.isEnabled();
	}

	public void setEnabled(boolean value) {
		touchButton.setEnabled(value);
		
		label.setColor(value ? COLOR : DISABLED_COLOR);
		
		if(icon != null) {
			icon.setColor(value ? Color.WHITE : Color.GRAY);
		}
	}

	public Sprite getIcon() {
		return icon;
	}

	public void setIcon(Sprite icon) {
		if (this.icon != null && this.icon.getParent() == this) {
			remove(icon);
		}
		
		this.icon = icon;

		if(icon != null) {
			if(label.getParent() == this) {
				remove(label);
			}
			add(icon);
		} else {
			add(label);
		}
		
		setSize(getWidth(), getHeight());
	}
	
	
}
