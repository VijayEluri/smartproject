package br.nom.pedro.oliveira.smartproject.integration.dao;

import br.nom.pedro.oliveira.smartproject.domain.User;
import br.nom.pedro.oliveira.smartproject.domain.UserRepository;
import br.nom.pedro.oliveira.smartproject.domain.annotation.RepositoryLayer;
import br.nom.pedro.oliveira.smartproject.integration.mapper.UserDataMapper;
import com.ppm.model.Identity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@RepositoryLayer("userRepository")
public final class UserJpaDao
	extends JpaDao<br.nom.pedro.oliveira.smartproject.integration.orm.User>
	implements UserRepository {

	@Autowired
	private UserDataMapper userDataMapper;

	public UserJpaDao() {
	}

	@Override
	public Class<br.nom.pedro.oliveira.smartproject.integration.orm.User> getOrmClass() {
		return br.nom.pedro.oliveira.smartproject.integration.orm.User.class;
	}

	@Override
	public void create(final User entity) {
		userDataMapper.insert(entity);
	}

	@Override
	public void update(final User entity) {
		userDataMapper.update(entity);
	}

	@Override
	public List<User> findAll() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User findById(Identity<?> id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public User findByUserName(final String userName) {
		Assert.notNull(userName, "userName can not be [null]");

		br.nom.pedro.oliveira.smartproject.integration.orm.User orm = entityManager
			.createNamedQuery("User.findByUsername", getOrmClass())
			.setParameter("username", userName)
			.getSingleResult();

		return userDataMapper.ormToEntity(orm);
	}

	@Override
	public void delete(final User entity) {
		userDataMapper.delete(entity);
	}

	public void setDataMapper(UserDataMapper userDataMapper) {
		this.userDataMapper = userDataMapper;
	}
}
