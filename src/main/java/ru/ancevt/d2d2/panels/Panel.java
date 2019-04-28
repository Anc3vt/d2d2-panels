package ru.ancevt.d2d2.panels;

import ru.ancevt.d2d2.common.PlainRect;
import ru.ancevt.d2d2.display.Color;

public class Panel extends Component {
   
	protected static final float DEFAULT_WIDTH = 200;
	protected static final float DEFAULT_HEIGHT = 100;
	
	private static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	protected static final Color BORDER_COLOR_1 = Color.WHITE;
	protected static final Color BORDER_COLOR_2 = Color.DARK_GRAY;
	
	protected final PlainRect borderLeft, borderRight, borderTop, borderBottom;
	protected final PlainRect background;
	
	public Panel() {
		borderLeft = new PlainRect();
		borderRight = new PlainRect();
		borderTop = new PlainRect();
		borderBottom = new PlainRect();
		background = new PlainRect();

		borderLeft.setSize(1, 1);
		borderRight.setSize(1, 1);
		borderTop.setSize(1, 1);
		borderBottom.setSize(1, 1);
		background.setSize(1, 1);
		
		borderLeft.setColor(BORDER_COLOR_1);
		borderRight.setColor(BORDER_COLOR_2);
		borderTop.setColor(BORDER_COLOR_1);
		borderBottom.setColor(BORDER_COLOR_2);
		background.setColor(BACKGROUND_COLOR);
		
		add(background);
		add(borderLeft);
		add(borderRight);
		add(borderTop);
		add(borderBottom);
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void setWidth(float width) {
		background.setWidth(width);
		borderTop.setWidth(width);
		borderBottom.setWidth(width);
		borderRight.setX(width);
	}
	
	public void setHeight(float height) {
		background.setHeight(height);
		borderLeft.setHeight(height);
		borderRight.setHeight(height);
		borderBottom.setY(height);
	}
	
	public void setSize(float width, float height) {
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public float getWidth() {
		return background.getWidth();
	}
	
	@Override
	public float getHeight() {
		return background.getHeight();
	}
	
	
	
}











