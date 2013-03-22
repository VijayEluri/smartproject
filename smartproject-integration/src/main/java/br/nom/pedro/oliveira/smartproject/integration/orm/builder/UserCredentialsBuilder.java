package br.nom.pedro.oliveira.smartproject.integration.orm.builder;

import br.nom.pedro.oliveira.smartproject.integration.orm.*;
import com.ppm.model.Builder;

public final class UserCredentialsBuilder extends Builder<UserCredentials> {

	public UserCredentialsBuilder() {
		super(new UserCredentials());
	}

	public UserCredentialsBuilder(final UserCredentials instance) {
		super(instance);
	}

	public UserCredentialsBuilder comAccesslevel(final Short accesslevel) {
		this.instance.setAccesslevel(accesslevel);
		return this;
	}

	public UserCredentialsBuilder comToken(final String token) {
		this.instance.setToken(token);
		return this;
	}

	public UserCredentialsBuilder comUser(final User user) {
		this.instance.setUser(user);
		return this;
	}

	@Override
	public UserCredentials build() {
		return this.instance;
	}

	@Override
	public void fillDefaultValues() {
		//TODO: Implementar 
	}
}
