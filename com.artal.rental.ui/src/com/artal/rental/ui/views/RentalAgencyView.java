package com.artal.rental.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.artal.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView extends ViewPart implements IPropertyChangeListener {

	private TreeViewer treeViewer;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}
	
	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent);
		RentalProvider rp = new RentalProvider();
		treeViewer.setContentProvider(rp);
		treeViewer.setLabelProvider(rp);

		List<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		treeViewer.refresh();
	}
}
