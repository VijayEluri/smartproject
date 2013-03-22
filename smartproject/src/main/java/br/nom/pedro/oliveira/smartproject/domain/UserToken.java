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
package br.nom.pedro.oliveira.smartproject.domain;

import com.ppm.model.ValueObject;
import java.util.Objects;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version
 * @since
 */
public class UserToken extends ValueObject {

	private final String value;

	public UserToken(final String token) {
		this.value = token;
	}

	public static UserToken newToken(final String token) {
		Objects.requireNonNull(token, "The token value cant be [null]");
		return new UserToken(token);
	}

	public static UserToken blockedToken() {
		return new UserToken("blockedtoken");
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.value);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserToken other = (UserToken) obj;
		if (!Objects.equals(this.value, other.value)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "UserToken{" + "value=" + value + '}';
	}
}
