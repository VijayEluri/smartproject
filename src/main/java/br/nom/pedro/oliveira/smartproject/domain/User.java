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

    private UserCredentials credentials;
    private String email;
    
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
        return ( getCredentials() != null
                && getCredentials().getAccessLevel() != null
                && getCredentials().getAccessLevel().ordinal() > AcessLevel.NONE.ordinal() );
    }

    @Override
    public String toString() {
        return "User { " + " identity=" + id + ", credentials=" + credentials + " }";
    }

    @Override
    public Identity<UserId> getId() {
        return (Identity<UserId>) id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
        if (!Objects.equals(this.credentials, other.credentials)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.credentials);
        hash = 71 * hash + Objects.hashCode(this.email);
        return hash;
    }
}
