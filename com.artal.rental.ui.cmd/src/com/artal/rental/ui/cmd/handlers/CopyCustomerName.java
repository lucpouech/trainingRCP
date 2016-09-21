package com.artal.rental.ui.cmd.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.artal.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class CopyCustomerName extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		StringBuffer sb = new StringBuffer();
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection)
		{
			IStructuredSelection isel = (IStructuredSelection) currentSelection;
			boolean first = true;
			for (Iterator<?> it = isel.iterator();it.hasNext();)
			{
				Object obj = it.next();
				if (obj instanceof Customer)
				{
					if (first)
					{
						first = false;
					}
					else
					{
						sb.append("\n");
					}
					sb.append(((Customer)obj).getDisplayName());
				}
			}
		}
		
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String textData = sb.toString();
		String rtfData = "{\\rtf1 " + sb.toString() + "}";
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer,ImageTransfer.getInstance(), URLTransfer.getInstance()};
		Object[] data = new Object[]{textData, rtfData, 
				RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER).getImageData(),
				"http://www.google.fr/search?q=\"" + sb.toString() + "\""};
		clipboard.setContents(data, transfers);
		clipboard.dispose();

		return null;
	}

}
