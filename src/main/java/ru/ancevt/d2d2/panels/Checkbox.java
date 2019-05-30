package ru.ancevt.d2d2.panels;

import ru.ancevt.d2d2.common.BorderedRect;
import ru.ancevt.d2d2.common.PlainRect;
import ru.ancevt.d2d2.display.Color;
import ru.ancevt.d2d2.display.text.BitmapText;
import ru.ancevt.d2d2.touch.TouchButton;

public class Checkbox extends Component {
	
	private static final float PADDING = 8;
	private static final float BOX_OUTER_SIZE = 16;
	private static final float BOX_INNER_SIZE = 6;
	private static final Color BOX_OUTER_DISABLED_FILL_COLOR = Color.GRAY;
	private static final Color BOX_OUTER_FILL_COLOR = Color.WHITE;
	private static final Color BOX_OUTER_BOREDER_COLOR = Color.BLACK;
	private static final Color BOX_INNER_COLOR = Color.BLACK;
	private static final Color LABEL_COLOR = Color.BLACK;
	private static final String DEFAULT_LABEL_TEXT = "Checkbox";
	private static final float DEFAULT_WIDTH = 200;
	
	private final BorderedRect boxOuter;
	private final PlainRect boxInner;
	private final BitmapText label;
	private final TouchButton touchButton;
	
	private boolean checked;
	
	public Checkbox() {
		this(DEFAULT_LABEL_TEXT);
	}
	
	public Checkbox(String labelText) {
		boxOuter = new BorderedRect(BOX_OUTER_SIZE, BOX_OUTER_SIZE, BOX_OUTER_FILL_COLOR, BOX_OUTER_BOREDER_COLOR);
		add(boxOuter);
		
		boxInner = new PlainRect(BOX_INNER_SIZE, BOX_INNER_SIZE, BOX_INNER_COLOR);
		boxInner.setXY((BOX_OUTER_SIZE - BOX_INNER_SIZE)/2, (BOX_OUTER_SIZE - BOX_INNER_SIZE)/2);
		
		label = new BitmapText();
		label.setBoundWidth(DEFAULT_WIDTH - BOX_OUTER_SIZE - PADDING);
		label.setText(labelText);
		label.setColor(LABEL_COLOR);
		label.setX(boxOuter.getWidth() + PADDING);
		label.setY((boxOuter.getHeight() - label.getBitmapFont().getCharHeight() * label.getAbsoluteScaleY()) / 2);
		add(label);
		
		touchButton = new TouchButton() {
			@Override
			public boolean onTouchDown(int x, int y) {
				setChecked(!isChecked());
				
				Focus.setFocusedComponent(Checkbox.this);
				
				if(checked) {
					Checkbox.this.add(boxInner);
				} else {
					Checkbox.this.remove(boxInner);
				}
				return super.onTouchDown(x, y);
			}
		};
		touchButton.setEnabled(true);
		touchButton.setSize((int) (boxOuter.getWidth() + PADDING + label.getWidth()), (int) boxOuter.getHeight());
		add(touchButton);
	}
	
	public void setWidth(float width) {
		label.setBoundWidth(width - BOX_OUTER_SIZE - PADDING);
		touchButton.setSize((int) (boxOuter.getWidth() + PADDING + label.getWidth()), (int) boxOuter.getHeight());
	}
	
	@Override
	public float getWidth() {
		return boxOuter.getWidth() + PADDING + label.getWidth();
	}
	
	@Override
	public float getHeight() {
		return boxOuter.getHeight();
	}
	
	public void onCheckedStateChange(boolean checked) {
		
	}
	
	public final void setLabelText(String value) {
		label.setText(value);
	}
	
	public String getLabelText() {
		return label.getText();
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		if(this.checked != checked)
			onCheckedStateChange(checked);
		
		this.checked = checked;
	}
	
	public void setEnabled(boolean value) {
		touchButton.setEnabled(value);
		
		boxOuter.setFillColor(value ? BOX_OUTER_FILL_COLOR : BOX_OUTER_DISABLED_FILL_COLOR);
	}

	public boolean isEnabled() {
		return touchButton.isEnabled();
	}
}













