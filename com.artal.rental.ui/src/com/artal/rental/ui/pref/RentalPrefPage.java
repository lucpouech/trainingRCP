package com.artal.rental.ui.pref;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.artal.rental.ui.RentalUIActivator;

public class RentalPrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String P_OBJ_GRP_COUNT = "P_OBJ_GRP_COUNT";

	public RentalPrefPage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Rental");
	}


	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(P_OBJ_GRP_COUNT, "Affiche les totaux par groupe d'objets:", getFieldEditorParent()));
	}
}
