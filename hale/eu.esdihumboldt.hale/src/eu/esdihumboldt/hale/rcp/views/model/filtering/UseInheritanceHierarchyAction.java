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
package eu.esdihumboldt.hale.rcp.views.model.filtering;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import eu.esdihumboldt.hale.rcp.HALEActivator;
import eu.esdihumboldt.hale.rcp.views.model.InheritanceContentProvider;

/**
 * Enabling this action will switch the affected SchemaExplorer to display it's 
 * elements in a inheritance hierarchy. This represents the default style of 
 * ordering.
 * 
 * When both it and the aggregation hierarchy are inactive, a simple list will 
 * be shown.
 * 
 * @author Thorsten Reitz, Simon Templer
 * @partner 01 / Fraunhofer Institute for Computer Graphics Research
 * @version $Id$ 
 */
public class UseInheritanceHierarchyAction 
	extends AbstractContentProviderAction {

	/**
	 * @see Action#getToolTipText()
	 */
	@Override
	public String getToolTipText() {
		return "Organize FeatureTypes by inheritance";
	}

	/**
	 * @see Action#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				HALEActivator.PLUGIN_ID, "/icons/inheritance_hierarchy.png");
	}

	/**
	 * @see AbstractContentProviderAction#getContentProvider()
	 */
	@Override
	protected IContentProvider getContentProvider() {
		return new InheritanceContentProvider();
	}
	
	

}
