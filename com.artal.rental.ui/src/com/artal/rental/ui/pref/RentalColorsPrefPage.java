package com.artal.rental.ui.pref;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.artal.rental.ui.RentalUIActivator;

public class RentalColorsPrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String P_OBJ_COLOR = "P_OBJ_COLOR";
	public static final String P_RENTAL_COLOR = "P_RENTAL_COLOR";
	public static final String P_CUST_COLOR = "P_CUST_COLOR";
	public static final String P_NODE_COLOR = "P_NODE_COLOR";
	public static final String P_AGENCY_COLOR = "P_AGENCY_COLOR";

	public RentalColorsPrefPage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Colors");
	}

	@Override
	public void init(IWorkbench workbench) {
		

	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(P_AGENCY_COLOR, "Agency:", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_NODE_COLOR, "Nodes:", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_CUST_COLOR, "Customer:", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_RENTAL_COLOR, "Rental:", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_OBJ_COLOR, "Objects:", getFieldEditorParent()));
	}
}
