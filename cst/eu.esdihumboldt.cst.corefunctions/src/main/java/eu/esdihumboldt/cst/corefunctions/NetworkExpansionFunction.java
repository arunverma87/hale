/*
 * HUMBOLDT: A Framework for Data Harmonisation and Service Integration.
 * EU Integrated Project #030962                  01.10.2006 - 30.09.2010
 * 
 * For more information on the project, please refer to this website:
 * http://www.esdi-humboldt.eu
 * 
 * LICENSE: For information on the license under which this program is 
 * available, please refer to : http:/www.esdi-humboldt.eu/license.html#core
 * (c) the HUMBOLDT Consortium, 2007 to 2010.
 *
 * Componet     : cst
 * 	 
 * Classname    : eu.esdihumboldt.cst.transformer/BufferGeometryTransformer.java 
 * 
 * Author       : schneidersb
 * 
 * Created on   : Aug 13, 2009 -- 9:34:39 AM
 *
 */
package eu.esdihumboldt.cst.corefunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opengis.feature.Feature;
import org.opengis.feature.type.PropertyDescriptor;

import com.iabcinc.jmep.Environment;
import com.iabcinc.jmep.Expression;
import com.iabcinc.jmep.hooks.Constant;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.TopologyException;
import com.vividsolutions.jts.operation.buffer.BufferBuilder;
import com.vividsolutions.jts.operation.buffer.BufferParameters;

import eu.esdihumboldt.cst.align.ICell;
import eu.esdihumboldt.cst.align.ext.IParameter;
import eu.esdihumboldt.cst.AbstractCstFunction;
import eu.esdihumboldt.goml.align.Cell;
import eu.esdihumboldt.goml.oml.ext.Parameter;
import eu.esdihumboldt.goml.oml.ext.Transformation;
import eu.esdihumboldt.goml.omwg.Property;
import eu.esdihumboldt.goml.rdf.About;

/**
 * CstFunction to apply a geometric buffer to a Feature.
 */
public class NetworkExpansionFunction extends AbstractCstFunction {

	private String bufferExpression = "10.0";
	private int capStyle = BufferParameters.CAP_ROUND;
	
	/**
	 * Name of the Parameter for buffer width.
	 */
	public static final String BUFFERWIDTH = "BUFFERWIDTH";
	
	/**
	 * Name of the parameter for the Cap Style (see {@link BufferParameters}).
	 */
	public static final String CAPSTYLE = "CAPSSTYLE";
	
	protected Map<String, Class<?>>  parameters = new HashMap<String, Class<?>>();
	private Property targetProperty = null;
	
	
	
	private void setBufferExpression(String bufferExpression) {
		this.bufferExpression = bufferExpression;
	}

	public Feature transform(Feature source, Feature target) {
		// find out which input parameters have been used
		Environment env = new Environment();
		for (PropertyDescriptor pd : source.getType().getDescriptors()) {
			if (this.bufferExpression.contains(pd.getName().getLocalPart())) {
				Object value = source.getProperty(
						pd.getName().getLocalPart()).getValue();
				Number number = Double.parseDouble(value.toString());
				env.addVariable(pd.getName().getLocalPart(), 
						new Constant(number));
			}
		}
		
		Geometry old_geometry = (Geometry)source.getDefaultGeometryProperty().getValue();
		if (old_geometry != null) {
			Geometry new_geometry = null;
			try {
				Expression expr = new Expression(this.bufferExpression, env);
				Object result = expr.evaluate();
				
				BufferParameters bufferParameters = new BufferParameters();
				bufferParameters.setEndCapStyle(this.capStyle);
				BufferBuilder bb = new BufferBuilder(new BufferParameters());
				new_geometry = bb.buffer(old_geometry, Double.parseDouble(
						result.toString()));
				target.getProperty(
						this.targetProperty.getLocalname()).setValue(new_geometry);
			} catch (Exception ex) {
				if (!ex.getClass().equals(TopologyException.class)) {
					throw new RuntimeException(ex);
				}
			}
		}
		return target;
	}

	public boolean configure(ICell cell) {
		for (IParameter ip : cell.getEntity1().getTransformation().getParameters()) {
				if (ip.getName().equals(NetworkExpansionFunction.BUFFERWIDTH)) {
					this.setBufferExpression(ip.getValue());
				}
				else if(ip.getName().equals(NetworkExpansionFunction.CAPSTYLE)) {
					this.capStyle = Integer.parseInt(ip.getValue());
				}
		}
		// if no bufferWidth or capStyle then default values will be used

		this.targetProperty = (Property) cell.getEntity2();
		return true;
	}

	public Cell getParameters() {
		Cell parameterCell = new Cell();
	
		Property entity1 = new Property(new About(""));
		
		// Setting of type condition for entity1
		List <String> entityTypes = new ArrayList <String>();
		entityTypes.add(com.vividsolutions.jts.geom.Geometry.class.getName());
		entityTypes.add(org.opengis.geometry.Geometry.class.getName());
		entity1.setTypeCondition(entityTypes);
		
		Property entity2 = new Property(new About(""));
		 
		// Setting of type condition for entity2
			// 	entity2 has same conditions as entity1
		entity2.setTypeCondition(entityTypes);
		
		List<IParameter> params = new ArrayList<IParameter>();
		IParameter buffer   = new Parameter(NetworkExpansionFunction.BUFFERWIDTH, "0");
		IParameter capstyle = new Parameter(NetworkExpansionFunction.CAPSTYLE   , "0");
		params.add(buffer);
		params.add(capstyle);
		Transformation t = new Transformation();
		t.setParameters(params);
		
		entity1.setTransformation(t);		
		
		parameterCell.setEntity1(entity1);
		parameterCell.setEntity2(entity2);
		return parameterCell;
	}

	@Override
	public String getDescription() {
		return Messages.getString("NetworkExpansionFunction.7"); //$NON-NLS-1$
	}

}
