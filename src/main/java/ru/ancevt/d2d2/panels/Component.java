package ru.ancevt.d2d2.panels;

import ru.ancevt.d2d2.common.IDisposable;
import ru.ancevt.d2d2.display.DisplayObjectContainer;

public class Component extends DisplayObjectContainer implements IDisposable {

	private boolean focused;
	
	public Component() {
		ComponentManager.getInstance().addComponent(this);
	}
	
	public void setFocused(boolean value) {
		this.focused = value;
	}
	
	public boolean isFocused() {
		return focused;
	}
	
	public void onFocus() {
		
	}
	
	public void onFocusLost() {
		
	}

	@Override
	public void dispose() {
		ComponentManager.getInstance().removeComponent(this);
	}
}
