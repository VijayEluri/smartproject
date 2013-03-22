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

import br.nom.pedro.oliveira.smartproject.domain.common.Email;
import com.ppm.model.ValueObject;
import java.util.Objects;

/**
 * A value Object that represents de User Global Identification
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version
 * @since
 */
public class UserId extends ValueObject {

	private final Email email;
	private final String password;
	private String passPhrase;

	public UserId(Email email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Creates a new UserId
	 *
	 * @param userName
	 * @param password
	 * @return
	 */
	public static UserId newId(Email email, String password) {
		return new UserId(email, password);
	}

	public String getPassword() {
		return password;
	}

	public String getPassPhrase() {
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	public Email getEmail() {
		return email;
	}
	
	public String getEmailAsString() {
		return email.getAddress();
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + Objects.hashCode(this.email);
		hash = 59 * hash + Objects.hashCode(this.password);
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
		final UserId other = (UserId) obj;
		if (!Objects.equals(this.email, other.email)) {
			return false;
		}
		if (!Objects.equals(this.password, other.password)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserId{" + "email=" + email + ", password=" + password + ", passPhrase=" + passPhrase + '}';
	}
}
