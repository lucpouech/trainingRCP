package com.artal.rental.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView extends ViewPart {

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		TreeViewer tv = new TreeViewer(parent);
		RentalProvider rp = new RentalProvider();
		tv.setContentProvider(rp);
		tv.setLabelProvider(rp);

		// TODO
		List<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		tv.expandAll();
		
		getSite().setSelectionProvider(tv);
	}

	@Override
	public void setFocus() {

	}

}
