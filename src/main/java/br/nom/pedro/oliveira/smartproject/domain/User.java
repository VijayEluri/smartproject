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

import com.ppm.model.Identity;

/**
 * A System User
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public class User extends DomainEntity {

    private final Identity<UserId> id;
    private UserCredentials credentials;
    
    public User(final UserId userId) {
        this.id = new Identity<>(userId);
    }

    public User(final UserId userId, final UserCredentials credentials) {
        this.id = new Identity<>(userId);
        this.credentials = credentials;
    }

    public static User createUser(final UserId userId) {
        return new User(userId);
    }
    
    public static User createUser(final UserId userId, UserCredentials credentials) {
        return new User(userId, credentials);
    }
    
    public void withCredentials(final UserCredentials credentials) {
        this.credentials = credentials;
    }

    public UserId getUserId() {
        return getId().value();
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public boolean isAuthenticated() {
        return (getCredentials() != null
                && getCredentials().getAccessLevel() != null
                && getCredentials().getAccessLevel().ordinal() > AcessLevel.NONE.ordinal());
    }

    @Override
    public String toString() {
        return "User { " + " identity=" + id + ", credentials=" + credentials + " }";
    }

    @Override
    public Identity<UserId> getId() {     
        return id;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
