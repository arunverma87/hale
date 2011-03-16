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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.opengis.feature.type.FeatureType;

import eu.esdihumboldt.hale.rcp.HALEActivator;
import eu.esdihumboldt.hale.rcp.utils.FeatureTypeHelper;
import eu.esdihumboldt.hale.Messages;

/**
 * Action that opens a style editor for a certain feature type
 * 
 * @author Simon Templer
 * @partner 01 / Fraunhofer Institute for Computer Graphics Research
 * @version $Id$ 
 *
 */
public class FeatureTypeStyleAction extends Action {
	
	private static final Log log = LogFactory.getLog(FeatureTypeStyleAction.class);
	
	private static ImageDescriptor featureImage;
	private static ImageDescriptor abstractImage;
	
	private final FeatureType type;
	
	/**
	 * Creates an action for editing a feature type style
	 * 
	 * @param type the feature type
	 */
	public FeatureTypeStyleAction(final FeatureType type) {
		super(type.getName().getLocalPart());
		
		this.type = type;
		
		init();
		
		setImageDescriptor(
				(FeatureTypeHelper.isAbstract(type))
				?(abstractImage):(featureImage));
	}

	/**
	 * Initialize the resources
	 */
	private static void init() {
		if (featureImage == null) {
			featureImage = AbstractUIPlugin.imageDescriptorFromPlugin(
					HALEActivator.PLUGIN_ID, "/icons/concrete_ft.png"); //$NON-NLS-1$
		}
		
		if (abstractImage == null) {
			abstractImage = AbstractUIPlugin.imageDescriptorFromPlugin(
					HALEActivator.PLUGIN_ID, "/icons/abstract_ft.png"); //$NON-NLS-1$
		}
	}

	/**
	 * @see Action#run()
	 */
	@Override
	public void run() {
		try {
			FeatureStyleDialog dialog = new FeatureStyleDialog(type);
			dialog.setBlockOnOpen(false);
			dialog.open();
		} catch (Exception e) {
			log.error("Error opening style editor dialog", e); //$NON-NLS-1$
		}
	}

}
