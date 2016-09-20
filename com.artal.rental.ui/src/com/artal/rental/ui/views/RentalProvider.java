package com.artal.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

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
			return ((Node) element).toString();
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
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof RentalAgency) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		} else if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
		} else if (element instanceof Node) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		} else if (element instanceof Rental) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
		} else if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		}

		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
}
