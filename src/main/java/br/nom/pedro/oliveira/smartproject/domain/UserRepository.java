package br.nom.pedro.oliveira.smartproject.domain;

/**
 * The User Repository
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
public interface UserRepository extends Repository<User> {
		
	/**
	 *
	 * @param userName
	 * @return
	 */
	User findByUserName(final String userName);
}
