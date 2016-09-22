package com.artal.rental.ui.e4.views;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import com.artal.rental.core.RentalCoreActivator;
import com.artal.rental.ui.views.RentalProvider;
import com.opcoach.training.rental.RentalAgency;

public class RentalAgencyView {

	private TreeViewer treeViewer;

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	// E34
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		super.init(site);
//		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
//	}

	// E34
//	@Override
//	public void dispose() {
//		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
//		super.dispose();
//	}
	
	@PostConstruct
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent);
		RentalProvider rp = new RentalProvider();
		treeViewer.setContentProvider(rp);
		treeViewer.setLabelProvider(rp);

		List<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		treeViewer.setInput(agencies);
		treeViewer.expandAll();
		
		// E34
//		getSite().setSelectionProvider(treeViewer);
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		// E34
//		getSite().registerContextMenu(menuManager, treeViewer);
		
		
	}

	@Focus
	public void setFocus() {

	}

	// E34
//	@Override
//	public void propertyChange(PropertyChangeEvent event) {
//		treeViewer.refresh();
//	}
}
