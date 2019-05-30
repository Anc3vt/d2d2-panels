package ru.ancevt.d2d2.panels;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import ru.ancevt.d2d2.debug.FPSMeter;
import ru.ancevt.d2d2.display.Color;
import ru.ancevt.d2d2.display.Root;
import ru.ancevt.d2d2.display.Sprite;
import ru.ancevt.d2d2.display.Stage;
import ru.ancevt.d2d2.display.text.BitmapFont;
import ru.ancevt.d2d2.pc.D2D2Window;

public class Test_Panels {
	public static void main(String[] args) {
		final D2D2Window window = new D2D2Window(800, 640) {

			private static final long serialVersionUID = 3296315089809377966L;

			@Override
			public void init() {
				try {
					BitmapFont.loadDefaultBitmapFont("Terminus.bmf");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				d2d2Init(this);
			}
			
		};
		window.setLocationByPlatform(true);
		window.setTitle("D2D2 project (floating)");
		window.setVisible(true);
	}

	public static final void d2d2Init(final D2D2Window window) {
		final Stage stage = window.getStage();
		
		stage.setFrameRate(60);
		stage.setScaleMode(Stage.SCALE_MODE_REAL);
		stage.setAlign(Stage.ALIGN_TOP_LEFT);
		stage.setStageSize(800, 640);
		stage.setBackgroundColor(Color.DARK_GRAY);

		final Root root = new Root();
			
		// Entry point
		
		final TitledPanel panel = new TitledPanel("My title");
		root.add(panel);
		panel.setHeight(500);
		panel.setXY(50, 50);

		final Button button = new Button() {
			@Override
			public void onButtonPressed() {
				System.out.println("size: " + stage.getWidth() + "x" + stage.getHeight());
				System.out.println("stage size: " + stage.getStageWidth() + "x" + stage.getStageHeight());
				super.onButtonPressed();
			}
		};
		button.setText("Hello");
		button.setEnabled(false);
		button.setXY(20, 10);
		panel.add(button);
		
		final Button iconedButton = new Button() {
			@Override
			public void onButtonPressed() {
				setEnabled(false);
				super.onButtonPressed();
			}
		};
		iconedButton.setX(20);
		final Sprite icon = new Sprite("satellite");
		icon.setScale(0.5f, 0.5f);
		iconedButton.setIcon(icon);
		iconedButton.setY(button.getY() + button.getHeight() + 20);
		panel.add(iconedButton);
		
		final Checkbox checkbox = new Checkbox("This is checkbox") {
			@Override
			public void onCheckedStateChange(boolean checked) {
				button.setEnabled(checked);
				super.onCheckedStateChange(checked);
			}
		};
		checkbox.setXY(20, 100);
		panel.add(checkbox);
		
		// TextInput:
		
		final TextInput textInput = new TextInput();
		textInput.setText("My text");
		panel.add(textInput, 20, 200);
		
		final TextInput textInput1 = new TextInput() {
			@Override
			public void onTextEnter() {
				if(getText().equals("disable")) {
					textInput.setEnabled(false);
				} else 
				if(getText().equals("enable")) {
					textInput.setEnabled(true);
				}
				super.onTextEnter();
			}
		};
		textInput1.setText("My text");
		panel.add(textInput1, 20, 230);
		
		final DropList dropList = new DropList();
		dropList.setWidth(100);
		dropList.addItem(new DropListItem("Item #1", "Key #1"));
		dropList.addItem(new DropListItem("Item #2", "Key #2"));
		dropList.addItem(new DropListItem("Item #3", "Key #3"));
		dropList.addItem(new DropListItem("Item #4", "Key #4"));
		panel.add(dropList, 10, 300);
		
		final FPSMeter fpsMeter = new FPSMeter();
		fpsMeter.setXY(0, 0);
		root.add(fpsMeter);
		stage.setRoot(root);
		
		window.canvas().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.isAltDown()) {
					switch(e.getKeyCode()) {
					case KeyEvent.VK_E:
						stage.setScaleMode(Stage.SCALE_MODE_EXTENDED);
						break;
					case KeyEvent.VK_R:
						stage.setScaleMode(Stage.SCALE_MODE_REAL);
						break;
					}
				}
				
				System.out.println("Key: " + e.getKeyCode() + " " + e.getKeyChar());
				
				super.keyPressed(e);
			}
		});
		
	 final TItledTextPanel ttp = new TItledTextPanel();
	 root.add(ttp, 200, 200);
	}
}
