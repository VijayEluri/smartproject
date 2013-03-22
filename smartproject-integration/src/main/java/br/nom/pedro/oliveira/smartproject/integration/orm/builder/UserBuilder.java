package br.nom.pedro.oliveira.smartproject.integration.orm.builder;

import br.nom.pedro.oliveira.smartproject.integration.orm.*;
import com.ppm.model.Builder;

public final class UserBuilder extends Builder<User> {

	public UserBuilder() {
		super(new User());
	}

	public UserBuilder(final User instance) {
		super(instance);
	}

	public UserBuilder comId(final Integer id) {
		this.instance.setId(id);
		return this;
	}

	public UserBuilder comUsername(final String username) {
		this.instance.setUsername(username);
		return this;
	}

	public UserBuilder comEmail(final String email) {
		this.instance.setEmail(email);
		return this;
	}

	public UserBuilder comUserCredentials(final UserCredentials userCredentials) {
		this.instance.setUserCredentials(userCredentials);
		return this;
	}

	public UserBuilder comPassword(final Password passwords) {
		this.instance.setPassword(passwords);
		return this;
	}

	@Override
	public User build() {
		return this.instance;
	}

	@Override
	public void fillDefaultValues() {
		//todo: implementar
	}
}
