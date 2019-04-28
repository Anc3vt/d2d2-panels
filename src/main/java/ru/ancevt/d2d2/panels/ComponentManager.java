package ru.ancevt.d2d2.panels;

import java.util.ArrayList;
import java.util.List;

public class ComponentManager {
	
	private static ComponentManager instance;
	
	public static final ComponentManager getInstance() {
		return instance == null ? instance = new ComponentManager() : instance;
	}
	
	private List<Component> components;
	
	private ComponentManager() {
		components = new ArrayList<Component>();
	}
	
	public final void addComponent(Component component) {
		components.add(component);
	}
	
	public final void removeComponent(Component component) {
		components.remove(component);
	}
	
	public final int getComponentCount() {
		return components.size();
	}
	
	public final Component getComponent(int index) {
		return components.get(index);
	}
	
	public final void provideMouseMove(int x, int y) {
		// TODO: implement
	}
}
