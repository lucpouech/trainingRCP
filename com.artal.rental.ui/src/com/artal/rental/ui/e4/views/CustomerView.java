package com.artal.rental.ui.e4.views;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.adapter.Adapter;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;

public class CustomerView {

	public static final String ID = "com.artal.rental.ui.views.CustomerView"; //$NON-NLS-1$
	private Label lblCust;

	// E34
	// @Override
	// public void init(IViewSite site) throws PartInitException {
	// super.init(site);
	// site.getPage().addSelectionListener(this);
	// }
	//
	// @Override
	// public void dispose() {
	// getSite().getPage().removeSelectionListener(this);
	// super.dispose();
	// }

	@Inject
	public CustomerView(Composite parent) {

		// parent.setLayout(new GridLayout(1));

		// Composite

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		// composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
		// 1, 1));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("Customer:");

		lblCust = new Label(composite, SWT.NONE);
		lblCust.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	@Focus
	public void setFocus() {
		// Set the focus
	}

	public void setCustomer(Customer cust) {
		if (cust == null) {
			lblCust.setText("");
		} else {
			lblCust.setText(cust.getDisplayName());
		}

	}

	// E34
	// @Override
	// public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	// if (selection instanceof IStructuredSelection) {
	// Object selected = ((IStructuredSelection) selection).getFirstElement();
	// if (selected != null) {
	// Customer c = Platform.getAdapterManager().getAdapter(selected,
	// Customer.class);
	// setCustomer(c);
	// }
	// }
	// }

	@Inject
	@Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Customer c) {
		setCustomer(c);
	}

	@Inject
	@Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object o, Adapter adapter) {
		if (o != null) {
			setCustomer(adapter.adapt(o, Customer.class));
		}
	}

	@Inject
	@Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selection, Adapter adapter) {
		if (selection != null) {
			Object selected = selection[0];
			setCustomer(adapter.adapt(selected, Customer.class));
		}
	}
}
