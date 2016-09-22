package com.artal.rental.ui.pref;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.artal.rental.ui.RentalUIActivator;

public class RentalPrefInit extends AbstractPreferenceInitializer {

	public RentalPrefInit() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(RentalPalettePrefsPage.P_PALETTE, "com.artal.rental.ui.palette.DefaultPalette");
		store.setDefault(RentalColorsPrefPage.P_OBJ_COLOR, StringConverter.asString(new RGB(100, 200, 250)));
		store.setDefault(RentalColorsPrefPage.P_CUST_COLOR, StringConverter.asString(new RGB(250, 100, 40)));
		store.setDefault(RentalColorsPrefPage.P_RENTAL_COLOR, StringConverter.asString(new RGB(50, 100, 200)));
		store.setDefault(RentalColorsPrefPage.P_NODE_COLOR, StringConverter.asString(new RGB(150, 50, 100)));
		store.setDefault(RentalColorsPrefPage.P_AGENCY_COLOR, StringConverter.asString(new RGB(200, 150, 150)));
	}

}
