package com.artal.rental.ui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.artal.rental.core.RentalCoreActivator;
import com.opcoach.training.rental.Rental;

public class RentalView extends ViewPart {

	private Label rentedObjectLabel;
	
	private Label customerLabel;
	private Group grpDatesDeLocations;

	private Label endDate;

	private Label startDate;
	private Label fromLabel;
	private Label toLabel;
	private Label rentedToLabel;

	public RentalView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

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
		startDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		toLabel = new Label(grpDatesDeLocations, SWT.NONE);
		toLabel.setText("au: ");
		
		endDate = new Label(grpDatesDeLocations, SWT.NONE);
		endDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public void setRental(Rental rental)
	{
		rentedObjectLabel.setText(rental.getRentedObject().getName());
		customerLabel.setText(rental.getCustomer().getDisplayName());
		startDate.setText(rental.getStartDate().toString());
		endDate.setText(rental.getEndDate().toString());
	}
}
