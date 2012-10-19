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

import br.nom.pedro.oliveira.smartproject.domain.Repository;
import br.nom.pedro.oliveira.smartproject.domain.User;
import br.nom.pedro.oliveira.smartproject.domain.UserCredentials;
import br.nom.pedro.oliveira.smartproject.domain.UserId;
import br.nom.pedro.oliveira.smartproject.domain.common.Password;
import com.ppm.infrastructure.utils.log.Severity;
import com.ppm.model.Identity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Implements the UserServices
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
public final class UserServicesProvider extends AbstractDomainService implements UserServices {

	@Autowired(required = true)
	private final Repository<User> userRepository;

	public UserServicesProvider(final Repository<User> userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User authenticate(UserId userId) throws ServiceException {
		try {

			Assert.notNull(userId, "User Id [null]");
			final User user = userRepository.findById(new Identity<>(userId));
			return (user == null) ? new User(userId, UserCredentials.userNotExist()) : user;

		} catch (Exception ex) {
			log(ex.getMessage(), Severity.ERROR);
			throw new ServiceException(ex);
		}
	}

	@Override
	public User authenticate(User user) throws ServiceException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User authenticate(String userName, Password password) throws ServiceException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User register(User user) throws ServiceException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
