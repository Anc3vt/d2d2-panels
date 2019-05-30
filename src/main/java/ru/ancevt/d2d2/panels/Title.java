package ru.ancevt.d2d2.panels;

import ru.ancevt.d2d2.common.PlainRect;
import ru.ancevt.d2d2.display.Color;
import ru.ancevt.d2d2.display.DisplayObject;
import ru.ancevt.d2d2.display.text.BitmapText;
import ru.ancevt.d2d2.touch.TouchButton;

public class Title extends Component {

	protected static final String DEFAULT_TITLE_TEXT = "Title";
	
	private static final int PADDING = 8;
	private static final Color BACKGROUND_COLOR = Color.DARK_BLUE;
	private static final Color FOREGROUND_COLOR = Color.WHITE;
	private static final int HEIGHT = 17;
	protected static final Color BORDER_COLOR = Color.LIGHT_GRAY;
	
	private final PlainRect borderLeft, borderRight, borderTop, borderBottom;
	
	private final PlainRect background;
	private final BitmapText label;
	
	private final DisplayObject owner;
	private final TouchButton titleTouchButton;
	
	public Title(DisplayObject owner) {
		this(owner, DEFAULT_TITLE_TEXT);
	}
	
	public Title(DisplayObject owner, String titleText) {
		this.owner = owner;
		
		background = new PlainRect();
		background.setHeight(HEIGHT);
		background.setColor(BACKGROUND_COLOR);
		label = new BitmapText();
		label.setColor(FOREGROUND_COLOR);
		label.setText(titleText);
		
		label.setX(PADDING);
		label.setY((HEIGHT - label.getBitmapFont().getCharHeight()) / 2);
		
		add(background);
		add(label);
		
		borderLeft = new PlainRect();
		borderRight = new PlainRect();
		borderTop = new PlainRect();
		borderBottom = new PlainRect();

		borderLeft.setSize(1, 1);
		borderRight.setSize(1, 1);
		borderTop.setSize(1, 1);
		borderBottom.setSize(1, 1);
		
		borderLeft.setColor(BORDER_COLOR);
		borderRight.setColor(BORDER_COLOR);
		borderTop.setColor(BORDER_COLOR);
		borderBottom.setColor(BORDER_COLOR);
		
		add(borderLeft);
		add(borderRight);
		add(borderTop);
		add(borderBottom);
		
		titleTouchButton = new TouchButton() {
			private float oldX, oldY;
			
			@Override
			public boolean onTouchDown(int x, int y) {
				super.onTouchDown(x, y);
				this.oldX = x;
				this.oldY = y;
				return false;
			}
			
			@Override
			public boolean onTouchDrag(int x, int y) {
				super.onTouchDrag(x, y);
				final float diffX = x - oldX;
				final float diffY = y - oldY;
				Title.this.owner.moveX(diffX);
				Title.this.owner.moveY(diffY);
				return false;
			}
		};
		titleTouchButton.setEnabled(true);
		add(titleTouchButton);
		
		setHeight(HEIGHT);
	}
	
	public void setWidth(float width) {
		if(background == null) return;
		background.setWidth(width);
		borderTop.setWidth(width);
		borderBottom.setWidth(width);
		borderRight.setX(width);
		
		background.setWidth(width);
		label.setBounds(getWidth() - PADDING * 2, label.getBitmapFont().getCharHeight());
		titleTouchButton.setWidth((int)width);
	}
	
	public void setHeight(float height) {
		if(background == null) return;
		background.setHeight(height);
		borderLeft.setHeight(height);
		borderRight.setHeight(height);
		borderBottom.setY(height);
		titleTouchButton.setHeight((int)height);
	}
	
	public void setSize(float width, float height) {
		if(background == null) return;
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public float getWidth() {
		if(background == null) return 0f;
		return background.getWidth();
	}
	
	@Override
	public float getHeight() {
		if(background == null) return 0f;
		return background.getHeight();
	}
	
	public void setText(String titleText) {
		label.setText(titleText);
	}
	
	public String getText() {
		return label.getText();
	}
	
}
