package com.artal.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;

public class PaletteDescriptor {

	private String id, name;
	
	private IColorProvider colorProvider;

	public PaletteDescriptor() {
		
	}

	public PaletteDescriptor(String id, String name, IColorProvider colorProvider) {
		super();
		this.id = id;
		this.name = name;
		this.colorProvider = colorProvider;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IColorProvider getColorProvider() {
		return colorProvider;
	}

	public void setColorProvider(IColorProvider colorProvider) {
		this.colorProvider = colorProvider;
	}
}
