package com.artal.rental.ui.pref;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.artal.rental.ui.PaletteDescriptor;
import com.artal.rental.ui.RentalUIActivator;

public class RentalPalettePrefsPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String P_PALETTE = "P_PALETTE";

	public RentalPalettePrefsPage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected void createFieldEditors() {
		Map<String, PaletteDescriptor> map = RentalUIActivator.getDefault().getPaletteMap();

		String[][] comboValues = new String[map.size()][2];

		int i = 0;
		for (PaletteDescriptor p : map.values()) {
			comboValues[i][0] = p.getName();
			comboValues[i][1] = p.getId();
			i++;
		}
		addField(new ComboFieldEditor(P_PALETTE, "Palette: ", comboValues, getFieldEditorParent()));
	}
}
