/*
 * Copyright (c) 2012 Data Harmonisation Panel
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
 *     HUMBOLDT EU Integrated Project #030962
 *     Data Harmonisation Panel <http://www.dhpanel.eu>
 */

package eu.esdihumboldt.hale.io.gml.ui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import eu.esdihumboldt.hale.common.core.io.Value;
import eu.esdihumboldt.hale.io.gml.reader.internal.StreamGmlReader;
import eu.esdihumboldt.hale.ui.io.IOWizard;
import eu.esdihumboldt.hale.ui.io.config.AbstractConfigurationPage;

/**
 * Configuration page for basic {@link StreamGmlReader} settings.
 * 
 * @author Simon Templer
 */
@SuppressWarnings("restriction")
public class GmlReaderSettingsPage extends
		AbstractConfigurationPage<StreamGmlReader, IOWizard<StreamGmlReader>> {

	private Button ignoreNamespaces;
	private Button strict;
	private Button rootAsInstance;

	/**
	 * Default constructor
	 */
	public GmlReaderSettingsPage() {
		super("gml.basicSettings");

		setTitle("XML/GML settings");
		setDescription("Basic XML and GML reader settings");
	}

	@Override
	public boolean updateConfiguration(StreamGmlReader provider) {
		provider.setParameter(StreamGmlReader.PARAM_IGNORE_NAMESPACES,
				Value.of(ignoreNamespaces.getSelection()));
		provider.setParameter(StreamGmlReader.PARAM_STRICT, Value.of(strict.getSelection()));
		provider.setParameter(StreamGmlReader.PARAM_IGNORE_ROOT,
				Value.of(!rootAsInstance.getSelection()));
		return true;
	}

	@Override
	protected void createContent(Composite page) {
		page.setLayout(new GridLayout(1, false));
		GridDataFactory groupData = GridDataFactory.fillDefaults().grab(true, false);

		Group parser = new Group(page, SWT.NONE);
		parser.setLayout(new GridLayout(1, false));
		parser.setText("Schema conformance");
		groupData.applyTo(parser);

		ignoreNamespaces = new Button(parser, SWT.CHECK);
		ignoreNamespaces
				.setText("Read types and properties where the namespace does not match the schema");
		// default
		ignoreNamespaces.setSelection(false);

		strict = new Button(parser, SWT.CHECK);
		strict.setText("XML element order must strictly match the XML schema");
		// default
		strict.setSelection(false);

		Group root = new Group(page, SWT.NONE);
		root.setLayout(new GridLayout(1, false));
		root.setText("Root element");
		groupData.applyTo(root);

		rootAsInstance = new Button(root, SWT.CHECK);
		rootAsInstance.setText("Create an instance for the root element");
		// default
		rootAsInstance.setSelection(false);
		Label descRoot = new Label(root, SWT.NONE);
		descRoot.setText("Will only take effect if the root element type is classified as mapping relevant type.\nOnly select if you are sure you need it.");

		setPageComplete(true);
	}

	/**
	 * @see AbstractConfigurationPage#disable()
	 */
	@Override
	public void disable() {
		// do nothing
	}

	/**
	 * @see AbstractConfigurationPage#enable()
	 */
	@Override
	public void enable() {
		// do nothing
	}

}
