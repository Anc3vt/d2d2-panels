package ru.ancevt.d2d2.panels;

public class DropListItem {
	private String labelText;
	private Object key;
	
	public DropListItem(String labelText, Object key) {
		this.labelText = labelText;
		this.key = key;
	}
	
	public String getLabelText() {
		return labelText;
	}
	
	public Object getKey() {
		return key;
	}
}
