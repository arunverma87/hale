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

package eu.esdihumboldt.cst.corefunctions.inspire;

import java.util.Map;

import org.geotools.feature.FeatureCollection;
import org.opengis.feature.Feature;
import org.opengis.feature.type.FeatureType;

import eu.esdihumboldt.cst.align.ICell;
import eu.esdihumboldt.cst.transformer.AbstractCstFunction;

/**
 * This function creates INSPIRE-compliant identifiers like this one
 * <code>urn:de:fraunhofer:exampleDataset:exampleFeatureTypeName:localID</code> 
 * based on the localId of the given source attribute.
 * 
 * @author Thorsten Reitz 
 * @partner 01 / Fraunhofer Institute for Computer Graphics Research
 * @version $Id$ 
 */
public class IdentifierFunction 
	extends AbstractCstFunction {
	
	public static final String COUNTRY_PARAMETER_NAME = "countryName";
	public static final String DATA_PROVIDER_PARAMETER_NAME = "providerName";
	public static final String PRODUCT_PARAMETER_NAME = "productName";

	/* (non-Javadoc)
	 * @see eu.esdihumboldt.cst.transformer.CstFunction#configure(eu.esdihumboldt.cst.align.ICell)
	 */
	public boolean configure(ICell cell) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see eu.esdihumboldt.cst.transformer.CstFunction#transform(org.geotools.feature.FeatureCollection)
	 */
	public FeatureCollection<? extends FeatureType, ? extends Feature> transform(
			FeatureCollection<? extends FeatureType, ? extends Feature> fc) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see eu.esdihumboldt.cst.transformer.CstFunction#transform(org.opengis.feature.Feature, org.opengis.feature.Feature)
	 */
	public Feature transform(Feature source, Feature target) {
		// TODO Auto-generated method stub
		return target;
	}


	@Override
	protected void setParametersTypes(Map<String, Class<?>> parametersTypes) {
		// TODO Auto-generated method stub
		
	}

}
