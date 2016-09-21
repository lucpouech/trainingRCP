package com.artal.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.AggregateWorkingSet;

import com.artal.rental.ui.RentalUIActivator;
import com.artal.rental.ui.pref.RentalColorsPrefPage;
import com.artal.rental.ui.pref.RentalPrefPage;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			return ((Collection) inputElement).toArray();
		}

		return null;

	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof RentalAgency) {
			// return ((RentalAgency) parentElement).getCustomers().toArray();
			List<Node> nodes = new ArrayList<Node>();
			RentalAgency ra = (RentalAgency) parentElement;
			nodes.add(new Node(Node.CUST, ra));
			nodes.add(new Node(Node.LOC, ra));
			nodes.add(new Node(Node.RENTAL_OBJS, ra));
			return nodes.toArray();
		} else if (parentElement instanceof Node) {
			Node node = (Node) parentElement;
			return node.getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {

		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof RentalAgency || element instanceof Node);
	}

	@Override
	public String getText(Object element) {
		
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		} else if (element instanceof Node) {
			boolean displayGroupObjCount = RentalUIActivator.getDefault().getPreferenceStore().getBoolean(RentalPrefPage.P_OBJ_GRP_COUNT);
			if (displayGroupObjCount)
			{
				return ((Node) element).toString() + " (" + getChildren(element).length + ")";
			}
			else
			{
				return ((Node) element).toString();
			}
		} else if (element instanceof Rental) {
			return ((Rental) element).toString();
		} else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		return null;
	}

	private class Node {

		

		public static final String CUST = "Clients";
		public static final String LOC = "Locations";
		public static final String RENTAL_OBJS = "Objets à louer";

		private String label;
		RentalAgency rentalAgency;

		public Node(String pLabel, RentalAgency pRentalAgency) {
			label = pLabel;
			rentalAgency = pRentalAgency;
		}

		@Override
		public String toString() {
			return label;
		}

		public String getLabel() {
			return label;
		}

		public RentalAgency getRentalAgency() {
			return rentalAgency;
		}

		public Object[] getChildren() {
			if (label.equalsIgnoreCase(CUST)) {
				return rentalAgency.getCustomers().toArray();
			}
			if (label.equalsIgnoreCase(LOC)) {
				return rentalAgency.getRentals().toArray();	
			}
			if (label.equalsIgnoreCase(RENTAL_OBJS)) {
				return rentalAgency.getObjectsToRent().toArray();
			}
			return null;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			result = prime * result + ((rentalAgency == null) ? 0 : rentalAgency.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			if (rentalAgency == null) {
				if (other.rentalAgency != null)
					return false;
			} else if (!rentalAgency.equals(other.rentalAgency))
				return false;
			return true;
		}
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

	@Override
	public Image getImage(Object element) {
		if (element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_AGENCY);
		}
		else if (element instanceof Node) {
			if (((Node) element).getLabel().equalsIgnoreCase(Node.CUST)) {
				return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);
			}
			else if (((Node) element).getLabel().equalsIgnoreCase(Node.LOC)) {
				return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_RENTAL);
			}
			else if (((Node) element).getLabel().equalsIgnoreCase(Node.RENTAL_OBJS)) {
				return RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_OBJECT);
			}
		}
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
