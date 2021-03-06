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

package eu.esdihumboldt.hale.ui.util.tree;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for a column of a tree with {@link TreeNode}s or
 * {@link AbstractMultiColumnTreeNode}s
 * 
 * @author Simon Templer
 * @partner 01 / Fraunhofer Institute for Computer Graphics Research
 * @version $Id$
 */
public class MultiColumnTreeNodeLabelProvider extends LabelProvider {

	/**
	 * The index of the column
	 */
	private final int columnIndex;

	/**
	 * Creates a new label provider for the column with the given index
	 * 
	 * @param columnIndex the column index (the index of the first column is
	 *            zero)
	 */
	public MultiColumnTreeNodeLabelProvider(final int columnIndex) {
		super();

		this.columnIndex = columnIndex;
	}

	/**
	 * @see LabelProvider#getText(Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof TreeNode) {
			Object value = ((TreeNode) element).getValue();
			if (value != null) {
				if (value.getClass().isArray()) {
					Object[] values = (Object[]) value;
					if (columnIndex < values.length) {
						return getValueText(values[columnIndex], (TreeNode) element);
					}
				}
				else if (columnIndex == 0) {
					return getValueText(value, (TreeNode) element);
				}
			}
		}

		return getDefaultText();
	}

	/**
	 * @see LabelProvider#getImage(Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof TreeNode) {
			Object value = ((TreeNode) element).getValue();
			if (value != null) {
				if (value.getClass().isArray()) {
					Object[] values = (Object[]) value;
					if (columnIndex < values.length) {
						return getValueImage(values[columnIndex], (TreeNode) element);
					}
				}
				else if (columnIndex == 0) {
					return getValueImage(value, (TreeNode) element);
				}
			}
		}

		return getDefaultImage();
	}

	/**
	 * Get the default text when no value is available
	 * 
	 * @return the default text
	 */
	protected String getDefaultText() {
		return ""; //$NON-NLS-1$
	}

	/**
	 * Get the default image when no value is available
	 * 
	 * @return the default image
	 */
	protected Image getDefaultImage() {
		return null;
	}

	/**
	 * Get the text for the given value
	 * 
	 * @param value the value
	 * @param node the tree node
	 * 
	 * @return the text representing the value
	 */
	protected String getValueText(Object value, TreeNode node) {
		return value.toString();
	}

	/**
	 * Get the image for the given value
	 * 
	 * @param value the value
	 * @param node the tree node
	 * 
	 * @return the text representing the value
	 */
	protected Image getValueImage(Object value, TreeNode node) {
		return null;
	}

}
