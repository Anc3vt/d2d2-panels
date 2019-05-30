package ru.ancevt.d2d2.panels;

public class Focus {
	private static Component focusedComponent;

	public static Component getFocusedComponent() {
		return focusedComponent;
	}

	public static void setFocusedComponent(Component value) {
		if(focusedComponent == value) return;
		
		if(focusedComponent != null) {
			focusedComponent.setFocused(false);
			focusedComponent.onFocusLost();
		}
		
		focusedComponent = value;
		if(value != null) {
			value.setFocused(true);
			value.onFocus();
		}
	}
	
}
