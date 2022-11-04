/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.baraye;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plasma {

	private String separator;
	private final Quark root;
	private final Map<List<String>, Quark> quarks = new HashMap<>();

	public Plasma(String separator) {
		final List<String> empty = Collections.emptyList();
		this.root = ensurePresent(empty);
		this.separator = separator;
	}

	public Quark root() {
		return root;
	}

	public final String getSeparator() {
		return separator;
	}

	public final void setSeparator(String separator) {
		this.separator = separator;
	}

	public Quark parse(Quark root, String full) {

		final List<String> result = root.getSignature();
		while (true) {
			int idx = full.indexOf(separator);
			if (idx == -1) {
				result.add(full);
				return ensurePresent(result);
			}
			if (idx > 0) {
				result.add(full.substring(0, idx));
				ensurePresent(result);
			}

			full = full.substring(idx + separator.length());
		}
	}

	Quark ensurePresent(List<String> result) {
		Quark quark = quarks.get(result);
		if (quark == null) {
			quark = new Quark(this, result);
			quarks.put(result, quark);
		}
		return quark;

	}

	public Collection<Quark> quarks() {
		return Collections.unmodifiableCollection(quarks.values());
	}

}