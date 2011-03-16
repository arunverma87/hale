/*
 * HUMBOLDT: A Framework for Data Harmonisation and Service Integration.
 * EU Integrated Project #030962                  01.10.2006 - 30.09.2010
 * 
 * For more information on the project, please refer to the this web site:
 * http://www.esdi-humboldt.eu
 * 
 * LICENSE: For information on the license under which this program is 
 * available, please refer to http:/www.esdi-humboldt.eu/license.html#core
 * (c) the HUMBOLDT Consortium, 2007 to 2010.
 */
package eu.esdihumboldt.hale.rcp.wizards.io;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import eu.esdihumboldt.hale.Messages;

/**
 * This is the main page of the {@link MappingImportWizard}.
 * 
 * @author Thorsten Reitz, Simon Templer
 * @partner 01 / Fraunhofer Institute for Computer Graphics Research
 * @version $Id$ 
 */
public class MappingImportWizardMainPage 
	extends WizardPage {
	
	private String result = null;
	
	private FileFieldEditor ffe;

	/**
	 * Constructor
	 * 
	 * @param pageName the page name 
	 * @param pageTitle the page title
	 */
	public MappingImportWizardMainPage(String pageName, String pageTitle) {
		super(pageName, pageTitle, (ImageDescriptor) null);
		setTitle(pageName); //NON-NLS-1
		setDescription(Messages.MappingImportWizardMainPage_ImportDescription); //NON-NLS-1
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		super.initializeDialogUnits(parent);
        this.setPageComplete(this.isPageComplete());
        
		// define source group composite
		Group selectionArea = new Group(parent, SWT.NONE);
		selectionArea.setText(Messages.MappingImportWizardMainPage_FileSelect);
		selectionArea.setLayout(new GridLayout());
		GridData selectionAreaGD = new GridData(GridData.VERTICAL_ALIGN_FILL
                | GridData.HORIZONTAL_ALIGN_FILL);
		selectionAreaGD.grabExcessHorizontalSpace = true;
		selectionArea.setLayoutData(selectionAreaGD);
		selectionArea.setSize(selectionArea.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		selectionArea.setFont(parent.getFont());
		
		// write to OML file (Model Repository service to be added later on)
		final Composite fileSelectionArea = new Composite(selectionArea, SWT.NONE);
		GridData fileSelectionData = new GridData(
				GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL);
		fileSelectionData.grabExcessHorizontalSpace = true;
		fileSelectionArea.setLayoutData(fileSelectionData);

		GridLayout fileSelectionLayout = new GridLayout();
		fileSelectionLayout.numColumns = 1;
		fileSelectionLayout.makeColumnsEqualWidth = false;
		fileSelectionLayout.marginWidth = 0;
		fileSelectionLayout.marginHeight = 0;
		fileSelectionArea.setLayout(fileSelectionLayout);
		Composite ffe_container = new Composite(fileSelectionArea, SWT.NULL);
		ffe_container.setLayoutData(
				new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		this.ffe = new FileFieldEditor(Messages.MappingImportWizardMainPage_FileSelectTitle, 
				Messages.MappingImportWizardMainPage_FileSelectDescription, ffe_container); //NON-NLS-1 //NON-NLS-2
		this.ffe.getTextControl(ffe_container).addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				getWizard().getContainer().updateButtons();
			}
		});
		ffe.setEmptyStringAllowed(false);
		String[] extensions = new String[] { "*.oml", "*.goml", "*.xml" }; //NON-NLS-1 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		this.ffe.setFileExtensions(extensions);
		
		setErrorMessage(null);	// should not initially have error message
		super.setControl(selectionArea);
	}
	
	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	@Override
	public boolean isPageComplete() {
		if (this.ffe == null) {
			return false;
		}
		if (this.ffe.getStringValue() != null && !this.ffe.getStringValue().isEmpty()) {
			this.result = this.ffe.getStringValue();
			if (result != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the URI representing the selected output path.
	 */
	public String getResult() {
		return this.result;
	}

}
