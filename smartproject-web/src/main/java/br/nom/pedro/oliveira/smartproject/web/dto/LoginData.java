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
package br.nom.pedro.oliveira.smartproject.web.dto;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Holds User Login Data
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@ManagedBean
@RequestScoped
public class LoginData {

    private String password;
    private String userIdentity;

    /**
     * Creates a new instance of LoginData
     */
    public LoginData() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final LoginData other = (LoginData) obj;

        if ((this.userIdentity == null)
                ? (other.userIdentity != null)
                : !this.userIdentity.equals(other.userIdentity)) {
            return false;
        }

        if ((this.password == null)
                ? (other.password != null)
                : !this.password.equals(other.password)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;

        hash = 59 * hash + ((this.userIdentity != null)
                ? this.userIdentity.hashCode()
                : 0);
        hash = 59 * hash + ((this.password != null)
                ? this.password.hashCode()
                : 0);

        return hash;
    }
}