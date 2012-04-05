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
package br.nom.pedro.oliveira.smartproject.application;

import br.nom.pedro.oliveira.smartproject.domain.User;
import br.nom.pedro.oliveira.smartproject.domain.UserId;

/**
 * Smart Project User General Services
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
public interface UserServices {

    /**
     * Authenticate a User in the system, and return this user whith the
     * credential. If the User is not Authenticated the UserCredential will be
     * invalid.
     *
     * @param userId
     * @return a Authenticated or not User.
     * @throws ServiceException
     * @see {@link User#isAuthenticated()}
     */
    User authenticate(final UserId userId) throws ServiceException;
    
    /**
     * Register a new user in the system, if this user can be registred, also
     * throw a service exception.
     * 
     * @param userId
     * @return the Registred User.
     * @throws ServiceException
     */
    User register(final UserId userId) throws ServiceException;
}
