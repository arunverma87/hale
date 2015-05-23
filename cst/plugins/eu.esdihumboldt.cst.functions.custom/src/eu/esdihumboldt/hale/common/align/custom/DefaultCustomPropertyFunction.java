/*
 * Copyright (c) 2015 Data Harmonisation Panel
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Data Harmonisation Panel <http://www.dhpanel.eu>
 */

package eu.esdihumboldt.hale.common.align.custom;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.esdihumboldt.hale.common.align.custom.groovy.CustomGroovyTransformation;
import eu.esdihumboldt.hale.common.align.extension.function.FunctionParameterDefinition;
import eu.esdihumboldt.hale.common.align.extension.function.PropertyFunctionDefinition;
import eu.esdihumboldt.hale.common.align.extension.function.PropertyParameterDefinition;
import eu.esdihumboldt.hale.common.align.extension.function.custom.CustomPropertyFunction;
import eu.esdihumboldt.hale.common.align.model.CellExplanation;
import eu.esdihumboldt.hale.common.align.transformation.function.PropertyTransformation;
import eu.esdihumboldt.hale.common.core.io.Value;

/**
 * Custom property function.
 * 
 * @author Simon Templer
 */
public class DefaultCustomPropertyFunction implements CustomPropertyFunction {

	private DefaultCustomPropertyFunctionEntity target;
	private List<DefaultCustomPropertyFunctionEntity> sources;

	private String identifier;
	private String name;
	private String functionType;
	private Value functionDefinition;
	private final PropertyFunctionDefinition descriptor = new PropertyFunctionDefinition() {

		@Override
		public String getId() {
			return identifier;
		}

		@Override
		public boolean isAugmentation() {
			return sources == null || sources.isEmpty();
		}

		@Override
		public Set<? extends PropertyParameterDefinition> getTarget() {
			if (target == null) {
				return Collections.emptySet();
			}
			return Collections.singleton(createParamDefinition(target));
		}

		@Override
		public Set<? extends PropertyParameterDefinition> getSource() {
			Set<PropertyParameterDefinition> sourceDefs = new HashSet<>();

			if (sources != null && !sources.isEmpty()) {
				for (DefaultCustomPropertyFunctionEntity source : sources) {
					sourceDefs.add(createParamDefinition(source));
				}
			}

			return sourceDefs;
		}

		@Override
		public FunctionParameterDefinition getParameter(String paramName) {
			// TODO Auto-generated method stub
			// XXX no parameters yet
			return null;
		}

		@Override
		public URL getIconURL() {
			return null;
		}

		@Override
		public URL getHelpURL() {
			return null;
		}

		@Override
		public CellExplanation getExplanation() {
			return null;
		}

		@Override
		public String getDisplayName() {
			return name;
		}

		@Override
		public String getDescription() {
			return null;
		}

		@Override
		public String getDefiningBundle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Collection<FunctionParameterDefinition> getDefinedParameters() {
			// TODO Auto-generated method stub
			return Collections.emptyList();
		}

		@Override
		public String getCategoryId() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	/**
	 * Default constructor.
	 */
	public DefaultCustomPropertyFunction() {
		super();
	}

	/**
	 * Copy constructor.
	 * 
	 * @param other the function to copy
	 */
	public DefaultCustomPropertyFunction(DefaultCustomPropertyFunction other) {
		setIdentifier(other.getIdentifier());
		setName(other.getName());
		setFunctionType(other.getFunctionType());

		setFunctionDefinition(other.getFunctionDefinition());

		DefaultCustomPropertyFunctionEntity otherTarget = other.getTarget();
		if (otherTarget != null) {
			setTarget(new DefaultCustomPropertyFunctionEntity(otherTarget));
		}
		else {
			setTarget(null);
		}

		List<DefaultCustomPropertyFunctionEntity> sources = new ArrayList<>();
		List<DefaultCustomPropertyFunctionEntity> targetSources = other.getSources();
		if (targetSources != null) {
			for (DefaultCustomPropertyFunctionEntity source : targetSources) {
				sources.add(new DefaultCustomPropertyFunctionEntity(source));
			}
		}
		setSources(sources);
	}

	/**
	 * @return the functionType
	 */
	public String getFunctionType() {
		return functionType;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param target2
	 * @return
	 */
	protected PropertyParameterDefinition createParamDefinition(
			final DefaultCustomPropertyFunctionEntity entity) {
		return entity;
	}

	/**
	 * @param functionType the functionType to set
	 */
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	/**
	 * @return the functionDefinition
	 */
	public Value getFunctionDefinition() {
		return functionDefinition;
	}

	/**
	 * @param functionDefinition the functionDefinition to set
	 */
	public void setFunctionDefinition(Value functionDefinition) {
		this.functionDefinition = functionDefinition;
	}

	/**
	 * @return the target
	 */
	public DefaultCustomPropertyFunctionEntity getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(DefaultCustomPropertyFunctionEntity target) {
		this.target = target;
	}

	/**
	 * @return the sources
	 */
	public List<DefaultCustomPropertyFunctionEntity> getSources() {
		return sources;
	}

	/**
	 * @param sources the sources to set
	 */
	public void setSources(List<DefaultCustomPropertyFunctionEntity> sources) {
		this.sources = sources;
	}

	@Override
	public PropertyFunctionDefinition getDescriptor() {
		return descriptor;
	}

	@Override
	public PropertyTransformation<?> createTransformationFunction() {
		return new CustomGroovyTransformation(this);
	}

}
