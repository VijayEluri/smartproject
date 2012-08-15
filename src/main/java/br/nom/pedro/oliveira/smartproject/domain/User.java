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

/**
 * A System User
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public class User extends Entity {

    /**
     * The User Code
     */
    private Integer userCode;
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
        return ( getCredentials() != null
                && getCredentials().getAccessLevel() != null
                && getCredentials().getAccessLevel().ordinal() > AcessLevel.NONE.ordinal() );
    }
}
