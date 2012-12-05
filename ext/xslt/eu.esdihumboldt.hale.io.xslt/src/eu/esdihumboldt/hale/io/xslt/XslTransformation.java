/*
 * Copyright (c) 2012 Fraunhofer IGD
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
 *     Fraunhofer IGD
 */

package eu.esdihumboldt.hale.io.xslt;

/**
 * Translates a transformation function to a XSLT template.
 * 
 * @author Simon Templer
 */
public interface XslTransformation {

	/**
	 * Set the XSLT generation context.
	 * 
	 * @param context the context of the current generation process
	 */
	public void setContext(XsltGenerationContext context);

}
