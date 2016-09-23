package com.artal.rental.ui.e4.views;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalView {

	@Inject
	private RentalAgency agency;

	private Label rentedObjectLabel;

	private Label customerLabel;
	private Group grpDatesDeLocations;

	private Label endDate;

	private Label startDate;
	private Label fromLabel;
	private Label toLabel;
	private Label rentedToLabel;


	// E34: init
	// @Override
	// public void init(IViewSite site) throws PartInitException {
	// super.init(site);
	// site.getPage().addSelectionListener(this);
	// }
	//
	// E34: dispose
	// @Override
	// public void dispose() {
	// getSite().getPage().removeSelectionListener(this);
	// super.dispose();
	// }

	/**
	 * @wbp.parser.entryPoint
	 */
	@Inject
	public RentalView(Composite parent, RentalAgency agency) {

		parent.setLayout(new GridLayout(1, false));

		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));

		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalAlignment = SWT.FILL;
		rentedObjectLabel.setLayoutData(gd);

		rentedToLabel = new Label(infoGroup, SWT.NONE);
		rentedToLabel.setText("Lou\u00E9 \u00E0:");

		customerLabel = new Label(infoGroup, SWT.NONE);
		GridData gd2 = new GridData();
		gd2.grabExcessHorizontalSpace = true;
		gd2.horizontalAlignment = SWT.FILL;
		customerLabel.setLayoutData(gd2);

		grpDatesDeLocations = new Group(parent, SWT.NONE);
		grpDatesDeLocations.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpDatesDeLocations.setText("Dates de locations");
		grpDatesDeLocations.setLayout(new GridLayout(2, false));

		fromLabel = new Label(grpDatesDeLocations, SWT.NONE);
		fromLabel.setText("du:");

		startDate = new Label(grpDatesDeLocations, SWT.NONE);
		startDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		toLabel = new Label(grpDatesDeLocations, SWT.NONE);
		toLabel.setText("au: ");

		endDate = new Label(grpDatesDeLocations, SWT.NONE);
		endDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		setRental(agency.getRentals().get(0));

		setLabelAsDragSource(rentedObjectLabel);
		setLabelAsDragSource(customerLabel);
		setLabelAsDragSource(startDate);
		setLabelAsDragSource(endDate);
	}

	@Focus
	public void setFocus() {
	}

	public void setRental(Rental rental) {
		if (rental != null)
		{
			rentedObjectLabel.setText(rental.getRentedObject().getName());
			customerLabel.setText(rental.getCustomer().getDisplayName());
			startDate.setText(rental.getStartDate().toString());
			endDate.setText(rental.getEndDate().toString());
		}
	}

	// E34: selectionChanged
	// @Override
	// public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	// if (selection instanceof IStructuredSelection) {
	// Object selected = ((IStructuredSelection) selection).getFirstElement();
	// if (selected instanceof Rental) {
	// setRental((Rental) selected);
	// }
	// }
	// }

	@Inject
	@Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Rental r) {
		setRental((Rental) r);
	}

	@Inject
	@Optional
	public void receiveSelection(@Named(IServiceConstants.ACTIVE_SELECTION) Object[] selection) {
		if (selection != null) {
			Object selected = selection[0];
			if (selected instanceof Rental) {
				setRental((Rental) selected);
			}
		}
	}

	public void setLabelAsDragSource(final Label label) {
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });

		source.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = label.getText();
				}
			}
		});
	}
}
