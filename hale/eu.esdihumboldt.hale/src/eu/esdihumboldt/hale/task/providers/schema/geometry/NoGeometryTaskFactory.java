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

package eu.esdihumboldt.hale.task.providers.schema.geometry;

import eu.esdihumboldt.hale.Messages;
import eu.esdihumboldt.hale.schemaprovider.model.Definition;
import eu.esdihumboldt.hale.schemaprovider.model.SchemaElement;
import eu.esdihumboldt.hale.schemaprovider.model.TypeDefinition;
import eu.esdihumboldt.hale.task.ServiceProvider;
import eu.esdihumboldt.hale.task.Task;
import eu.esdihumboldt.hale.task.TaskFactory;
import eu.esdihumboldt.hale.task.TaskType;
import eu.esdihumboldt.hale.task.impl.AbstractTaskFactory;
import eu.esdihumboldt.hale.task.impl.AbstractTaskType;
import eu.esdihumboldt.hale.task.impl.SchemaTask;

/**
 * Map type task factory
 *
 * @author Simon Templer
 * @partner 01 / Fraunhofer Institute for Computer Graphics Research
 * @version $Id$ 
 */
public class NoGeometryTaskFactory extends AbstractTaskFactory {

	/**
	 * The task type
	 */
	private static class NoGeometryDescriptorTaskType extends AbstractTaskType {
		
		/**
		 * Constructor
		 * 
		 * @param taskFactory the task factory
		 */
		public NoGeometryDescriptorTaskType(TaskFactory taskFactory) {
			super(taskFactory);
		}

		/**
		 * @see TaskType#getReason(Task)
		 */
		@Override
		public String getReason(Task task) {
			return Messages.NoGeometryTaskFactory_0; //$NON-NLS-1$
		}

		/**
		 * @see TaskType#getSeverityLevel(Task)
		 */
		@Override
		public SeverityLevel getSeverityLevel(Task task) {
			return SeverityLevel.warning;
		}

		/**
		 * @see TaskType#getTitle(Task)
		 */
		@Override
		public String getTitle(Task task) {
			return Messages.NoGeometryTaskFactory_1 + ((SchemaElement) task.getMainContext()).getElementName().getLocalPart(); //$NON-NLS-1$
		}

		/**
		 * @see TaskType#getValue(Task)
		 */
		@Override
		public double getValue(Task task) {
			return 0.6;
		}

	}
	
	/**
	 * The type name
	 */
	public static final String BASE_TYPE_NAME = Messages.NoGeometryTaskFactory_2; //$NON-NLS-1$
	
	/**
	 * The task type
	 */
	private final TaskType type;

	/**
	 * Default constructor
	 */
	public NoGeometryTaskFactory() {
		super(BASE_TYPE_NAME);
		
		type = new NoGeometryDescriptorTaskType(this);
	}

	/**
	 * @see TaskFactory#createTask(ServiceProvider, Definition[])
	 */
	@Override
	public Task createTask(ServiceProvider serviceProvider,
			Definition... definitions) {
		if (definitions == null || definitions.length < 1 || !(definitions[0] instanceof SchemaElement)) {
			return null;
		}
		
		SchemaElement element = (SchemaElement) definitions[0];
		if (validateTask(element)) {
			return new SchemaTask(serviceProvider, getTaskTypeName(), element);
		}
		
		return null;
	}

	/**
	 * Determines if the given element is valid for a task
	 * 
	 * @param element the element
	 * @param alignmentService the alignment service
	 * 
	 * @return if the type is valid
	 */
	private static boolean validateTask(SchemaElement element) {
		TypeDefinition type = element.getType();
		if (type.isFeatureType() && !type.isAbstract()) {
			// check if a geometry descriptor is available
			if (type.getFeatureType().getGeometryDescriptor() == null) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * @see TaskFactory#getTaskType()
	 */
	@Override
	public TaskType getTaskType() {
		return type;
	}

}
