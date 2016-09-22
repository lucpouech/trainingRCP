package com.artal.rental.ui.e4.views;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Customer;

public class CustomerView {

	public static final String ID = "com.artal.rental.ui.views.CustomerView"; //$NON-NLS-1$
	private Label lblCust;

	public CustomerView() {
	}
// E34
//	@Override
//	public void init(IViewSite site) throws PartInitException {
//		super.init(site);
//		site.getPage().addSelectionListener(this);
//	}
//
//	@Override
//	public void dispose() {
//		getSite().getPage().removeSelectionListener(this);
//		super.dispose();
//	}

	@PostConstruct
	public void createPartControl(Composite parent) {

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
//	@Override
//	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//		if (selection instanceof IStructuredSelection) {
//			Object selected = ((IStructuredSelection) selection).getFirstElement();
//			if (selected != null) {
//				Customer c = Platform.getAdapterManager().getAdapter(selected, Customer.class);
//				setCustomer(c);
//			}
//		}
//	}
}
