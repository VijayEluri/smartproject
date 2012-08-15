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

/**
 * A value Object that represents de User Global Identification
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version
 * @since
 */
public class UserId extends ValueObject {

	private final String userName;
	private final String email;
	private final String password;
	private String passPhrase;

	public UserId(String userName, String email, String password) {
		this.userName = userName;
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
	public static UserId newId(String userName, String email, String password) {
		return new UserId(userName, email, password);
	}

	public static UserId newId(String aIdentity, String password) {
		final UserId userId;
		if (isEmail(aIdentity)) {
			userId = new UserId(null, aIdentity, password);
		} else {
			userId = new UserId(aIdentity, null, password);
		}
		return userId;
	}

	public String getUserName() {
		return userName;
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

	public String getEmail() {
		return email;
	}

	private static boolean isEmail(String aIdentity) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public String toString() {
		return "UserId{" + "userName=" + userName + ", password=" + password + '}';
	}
}
