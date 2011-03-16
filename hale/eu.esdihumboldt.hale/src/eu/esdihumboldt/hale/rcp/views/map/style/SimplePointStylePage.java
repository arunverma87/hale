/*
 * HUMBOLDT: A Framework for Data Harmonisation and Service Integration.
 * EU Integrated Project #030962                 01.10.2006 - 30.09.2010
 * 
 * For more information on the project, please refer to the this web site:
 * http://www.esdi-humboldt.eu
 * 
 * LICENSE: For information on the license under which this program is 
 * available, please refer to http:/www.esdi-humboldt.eu/license.html#core
 * (c) the HUMBOLDT Consortium, 2007 to 2010.
 */
package eu.esdihumboldt.hale.rcp.views.map.style;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.geotools.styling.LineSymbolizer;
import org.geotools.styling.PointSymbolizer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.styling.StyleBuilder;
import org.geotools.styling.Symbolizer;

import eu.esdihumboldt.hale.Messages;
import eu.esdihumboldt.hale.rcp.views.map.style.editors.PointSymbolizerEditor;

/**
 * Simple point style editor
 * 
 * @author Simon Templer
 * @partner 01 / Fraunhofer Institute for Computer Graphics Research
 * @version $Id$ 
 */
public class SimplePointStylePage extends FeatureStylePage {
	
	private final StyleBuilder styleBuilder = new StyleBuilder();
	
	private PointSymbolizerEditor pointEditor;
	
	/**
	 * @param parent the parent dialog
	 */
	public SimplePointStylePage(FeatureStyleDialog parent) {
		super(parent, Messages.SimplePointStylePage_SuperTitle);
	}

	/**
	 * @see FeatureStylePage#getStyle(boolean)
	 */
	@Override
	public Style getStyle(boolean force) throws Exception {
		if (pointEditor != null) {
			if (force || pointEditor.isChanged()) {
				return styleBuilder.createStyle(pointEditor.getValue());
			}
			else {
				// nothing has changed
				return null;
			}
		}
		
		return null;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		// create new controls
		Composite page = new Composite(parent, SWT.NONE);

		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		page.setLayout(layout);
		
		Style style = getParent().getStyle();
		PointSymbolizer point = null;
		try {
			Symbolizer[] symbolizers = SLD.symbolizers(style);
			for (Symbolizer symbol : symbolizers) {
				if (symbol instanceof LineSymbolizer) {
					point = (PointSymbolizer) symbol;
					break;
				}
			}
		}
		catch (Exception e) {
			// ignore
		}
		
		if (point == null) {
			point = styleBuilder.createPointSymbolizer();
		}
		
		pointEditor = new PointSymbolizerEditor(page, point);
		
		setControl(page);
	}

}
