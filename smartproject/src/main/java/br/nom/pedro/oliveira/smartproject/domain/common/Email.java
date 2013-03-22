/*
 *  Copyright (C) 2010 Pedro T. Oliveira <pedro.oliveira.nom.br>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.nom.pedro.oliveira.smartproject.domain.common;

import java.io.Serializable;
import java.util.regex.Pattern;
import org.springframework.util.Assert;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public class Email implements Serializable, Comparable<Email> {

	private static final long serialVersionUID = 6527032358723605505L;
	private final String address;
	private static final String PARAMETRO_INVALIDO = "The address Cannot be null or a empty String";
	private static final String regex = "";
	private static final Pattern pattern = Pattern.compile(regex);

	public Email(final String address) {
		Assert.notNull(address, PARAMETRO_INVALIDO);
		Assert.isTrue(!address.isEmpty(), PARAMETRO_INVALIDO);
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public boolean isValid() {
		return pattern.matcher(address).matches();
	}
	
	public static boolean isEmail(final String address) {
		Assert.notNull(address, PARAMETRO_INVALIDO);
		return pattern.matcher(address).matches();
	}

	@Override
	public int compareTo(Email o) {
		return this.address.compareTo(o.getAddress());
	}

	@Override
	public String toString() {
		return "Email{" + "address=" + address + '}';
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Email other = (Email) obj;
		if ((this.address == null)
			? (other.address != null)
			: !this.address.equals(other.address)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 79 * hash + (this.address != null ? this.address.hashCode() : 0);
		return hash;
	}
}
