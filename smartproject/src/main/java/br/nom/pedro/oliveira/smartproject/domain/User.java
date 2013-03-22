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

import com.ppm.model.Entity;
import com.ppm.model.Identity;
import java.util.Objects;

/**
 * A System User
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public class User extends Entity {
	
	private final Identity<UserId> id;
	/**
	 * The User Code
	 */
	private Integer userCode;
	/**
	 * The Username
	 */
	private String userName;
	/**
	 * The UserCredentials
	 */
	private UserCredentials credentials;

	public User(final UserId userId) {
		this.id = new Identity<>(userId);
		this.credentials = UserCredentials.blockedAccess();
	}

	public User(final UserId userId, final UserCredentials credentials) {
		this.id = new Identity<>(userId);
		this.credentials = credentials;
	}

	public static User createUser(final UserId userId) {
		return new User(userId, UserCredentials.blockedAccess());
	}

	public static User createUser(final UserId userId, UserCredentials credentials) {
		return new User(userId, credentials);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void changeCredentials(final UserCredentials credentials) {
		this.credentials = credentials;
	}

	public UserId getUserId() {
		return getId().value();
	}

	@Override
	public Identity<UserId> getId() {
		return (Identity<UserId>) id;
	}

	public UserCredentials getCredentials() {
		return credentials;
	}

	public Integer getUserCode() {
		return userCode;
	}

	public void setUserCode(Integer userCode) {
		this.userCode = userCode;
	}

	public boolean isAuthenticated() {
		return (getCredentials() != null
			&& getCredentials().getAccessLevel() != null
			&& getCredentials().getAccessLevel().ordinal() > AcessLevel.NONE.ordinal());
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 41 * hash + Objects.hashCode(this.id);
		hash = 41 * hash + Objects.hashCode(this.userName);
		hash = 41 * hash + Objects.hashCode(this.credentials);
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
		final User other = (User) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.userName, other.userName)) {
			return false;
		}
		if (!Objects.equals(this.credentials, other.credentials)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", userCode=" + userCode + ", userName=" + userName + ", credentials=" + credentials + '}';
	}
}
