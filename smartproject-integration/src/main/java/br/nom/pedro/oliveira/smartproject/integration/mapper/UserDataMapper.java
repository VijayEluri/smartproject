package br.nom.pedro.oliveira.smartproject.integration.mapper;

import br.nom.pedro.oliveira.smartproject.domain.AcessLevel;
import br.nom.pedro.oliveira.smartproject.domain.User;
import br.nom.pedro.oliveira.smartproject.domain.UserCredentials;
import br.nom.pedro.oliveira.smartproject.domain.UserId;
import br.nom.pedro.oliveira.smartproject.domain.common.Email;
import br.nom.pedro.oliveira.smartproject.integration.orm.Password;
import br.nom.pedro.oliveira.smartproject.integration.orm.builder.UserBuilder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Component
public class UserDataMapper
	extends AbstractDataMapper<User, br.nom.pedro.oliveira.smartproject.integration.orm.User> {

	@Override
	public br.nom.pedro.oliveira.smartproject.integration.orm.User entityToORM(final User entity) {
		br.nom.pedro.oliveira.smartproject.integration.orm.User orm =
			new UserBuilder()
			.comId(entity.getUserCode())
			.comUsername(entity.getUserName())
			.comEmail(entity.getUserId().getEmailAsString())
			.build();

		final br.nom.pedro.oliveira.smartproject.integration.orm.UserCredentials credentials = new br.nom.pedro.oliveira.smartproject.integration.orm.UserCredentials();
		credentials.setAccesslevel((short) entity.getCredentials().getAccessLevel().ordinal());
		credentials.setToken(entity.getCredentials().getTokenValue());
		credentials.setUser(orm);

		final Password password = new Password();
		password.setPass(entity.getUserId().getPassword());
		password.setPassphrase(entity.getUserId().getPassPhrase());
		password.setUser(orm);

		orm.setPassword(password);
		orm.setUserCredentials(credentials);

		return orm;
	}

	@Override
	public User ormToEntity(final br.nom.pedro.oliveira.smartproject.integration.orm.User orm) {
		final User user = User.createUser(
			UserId.newId(new Email(orm.getEmail()), orm.getPassword().getPass()),
			UserCredentials.newCredentials(orm.getUserCredentials().getToken(), AcessLevel.values()[orm.getUserCredentials().getAccesslevel()]));
		
		user.setUserCode(orm.getId());
		user.setUserName(orm.getUsername());
		
		return user;
	}

	@Override
	public void insert(final User entity) {
		final br.nom.pedro.oliveira.smartproject.integration.orm.User orm = entityToORM(entity);
		entityStateManager.markToInsert(orm);
	}

	@Override
	public void update(User entity) {
		entityStateManager.markToUpdate(entityToORM(entity));
	}

	@Override
	public void delete(User entity) {
		entityStateManager.markToDelete(entityToORM(entity));
	}
}
