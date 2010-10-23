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

/**
 * Represents a System User
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public class User {

    private UserId userId;
    private UserCredentials credentials;

    public User(UserId userId, UserCredentials credentials) {
        this.userId = userId;
        this.credentials = credentials;
    }

    public UserId getUserId() {
        return userId;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    @Override
    public String toString() {
        return "User { " + " userId=" + userId + ", credentials=" + credentials
                + " }";
    }
}
