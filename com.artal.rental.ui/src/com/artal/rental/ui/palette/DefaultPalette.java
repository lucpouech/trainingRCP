package com.artal.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.artal.rental.ui.RentalUIActivator;
import com.artal.rental.ui.pref.RentalColorsPrefPage;
import com.artal.rental.ui.views.RentalProvider.Node;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof RentalAgency) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorsPrefPage.P_AGENCY_COLOR));
		} else if (element instanceof Customer) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorsPrefPage.P_CUST_COLOR));
		} else if (element instanceof Node) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorsPrefPage.P_NODE_COLOR));
		} else if (element instanceof Rental) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorsPrefPage.P_RENTAL_COLOR));
		} else if (element instanceof RentalObject) {
			return getAColor(RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorsPrefPage.P_OBJ_COLOR));
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	private Color getAColor(String rgbKey)
	{
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(rgbKey);
		if (col == null)
		{
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}
}
