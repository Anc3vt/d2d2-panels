package ru.ancevt.d2d2.panels;

import ru.ancevt.d2d2.common.BorderedRect;
import ru.ancevt.d2d2.common.PlainRect;
import ru.ancevt.d2d2.display.Color;
import ru.ancevt.d2d2.display.RootKeyListener;
import ru.ancevt.d2d2.display.text.BitmapText;
import ru.ancevt.d2d2.touch.TouchButton;

public class TextInput extends Component implements RootKeyListener {
	
	private static final float PADDING = 5;
	
	private static final Color BACKGROUND_COLOR = Color.WHITE;
	private static final Color BORDER_COLOR = Color.BLACK;
	private static final Color DISABLED_BACKGROUND_COLOR = Color.LIGHT_GRAY;
	private static final Color FOCUSED_BORDER_COLOR = Color.BLUE;
	private static final Color FOREGROUND_COLOR = Color.BLACK;
	
	private static final float CURSOR_WIDTH = 1;
	private static final float CURSOR_HEIGHT = 14;
	
	private static final float DEFAULT_WIDTH = 120;
	private static final float DEFAULT_HEIGHT = 20;
	
	private static final int[] NON_CHARS_KEY_CODES = new int[] {
		10, 16, 17, 18, 19, 20, 157
	};
	
	private BorderedRect rect;
	private BitmapText bitmapText;
	private PlainRect cursor;
	private TouchButton touchButton;
	
	public TextInput() {
		rect = new BorderedRect(BACKGROUND_COLOR, BORDER_COLOR);
		bitmapText = new BitmapText();
		cursor = new PlainRect(CURSOR_WIDTH, CURSOR_HEIGHT, Color.BLACK);
		
		bitmapText.setColor(FOREGROUND_COLOR);
		bitmapText.setX(PADDING);
		
		touchButton = new TouchButton() {
			@Override
			public boolean onTouchDown(int x, int y) {
				onTouch();
				return super.onTouchDown(x, y);
			}
		};
		
		setEnabled(true);
		add(rect);
		add(bitmapText);
		add(touchButton);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	private final void relocateCursor() {
		final float newX = bitmapText.getX() + bitmapText.getTextWidth() + 1;
		
		cursor.setVisible(newX < getWidth());
		
		if(newX < getWidth())
			cursor.setX(newX);
	}
	
	@Override
	public void onFocus() {
		rect.setBorderColor(FOCUSED_BORDER_COLOR);

		getRoot().addRootKeyListener(this);
		relocateCursor();
		add(cursor);
		super.onFocus();
	}
	
	@Override
	public void onFocusLost() {
		rect.setBorderColor(BORDER_COLOR);
		remove(cursor);
		getRoot().removeRootKeyListener(this);
		super.onFocusLost();
	}
	
	public final void onTouch() {
		Focus.setFocusedComponent(this);
	}
	
	public void setText(String text) {
		bitmapText.setText(text);
	}
	
	public String getText() {
		return bitmapText.getText();
	}
	
	public void setWidth(float width) {
		rect.setWidth(width);
		bitmapText.setBoundWidth(width);
		touchButton.setWidth((int)width);
	}
	
	public void setHeight(float height) {
		rect.setHeight(height);

		bitmapText.setBoundHeight(height - bitmapText.getBitmapFont().getCharHeight());
		bitmapText.setY((getHeight() - bitmapText.getBitmapFont().getCharHeight())/2);
		cursor.setY((getHeight() - cursor.getHeight())/2);
		touchButton.setHeight((int)height);
	}
	
	@Override
	public float getWidth() {
		return rect.getWidth();
	}
	
	@Override
	public float getHeight() {
		return rect.getHeight();
	}
	
	public void setSize(float width, float height) {
		setWidth(width);
		setHeight(height);
	}
	
	public void setEnabled(boolean enabled) {
		touchButton.setEnabled(enabled);
		rect.setFillColor(enabled ? BACKGROUND_COLOR : DISABLED_BACKGROUND_COLOR);
	}
	
	public boolean isEnabled() {
		return touchButton.isEnabled();
	}
	
	@Override
	public void onRemoveFromStage() {
		getRoot().removeRootKeyListener(this);
	}
	
	public void onTextEnter() {
		
	}
	
	public void onTextChange() {
		
	}

	@Override
	public void keyDown(int keyCode, char keyChar, boolean shift, boolean control, boolean alt) {
		if(keyCode == 10) {
			onTextEnter();
			Focus.setFocusedComponent(null);
		}
		
		if(keyChar == '\0' || isNonChar(keyCode)) return;
		
		if(keyChar == '\b') {
			if(bitmapText.getText().length() == 0) return;
			bitmapText.setText(bitmapText.getText().substring(0, bitmapText.getText().length() - 1));
		} else {
			bitmapText.setText(bitmapText.getText() + keyChar);
		}
		relocateCursor();
		onTextChange();
	}

	@Override
	public void keyUp(int keyCode, char keyChar, boolean shift, boolean control, boolean alt) {
		
	}
	
	private static final boolean isNonChar(int keyCode) {
		for(final int k : NON_CHARS_KEY_CODES) {
			if(k == keyCode) return true;
		}
		return false;
	}
	
}



















