package br.nom.pedro.oliveira.smartproject.integration.orm;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Entity
@Table(name = "UserCredentials")
@NamedQueries({
	@NamedQuery(name = "UserCredentials.findAll", query = "SELECT u FROM UserCredentials u"),
	@NamedQuery(name = "UserCredentials.findByAccesslevel", query = "SELECT u FROM UserCredentials u WHERE u.accesslevel = :accesslevel"),
	@NamedQuery(name = "UserCredentials.findByToken", query = "SELECT u FROM UserCredentials u WHERE u.token = :token"),
	@NamedQuery(name = "UserCredentials.findById", query = "SELECT u FROM UserCredentials u WHERE u.id = :id")})
public class UserCredentials implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="UserCredentials_Seq_Gen", sequenceName="UserCredentials_Seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UserCredentials_Seq_Gen")
	private Long id;
	@Basic(optional = false)
	@Column(name = "token", unique = false, updatable = true)
	private String token;
	@Column(name = "accesslevel", unique = false, updatable = true)
	private Short accesslevel;
	@OneToOne(cascade = CascadeType.REMOVE, mappedBy = "UserCredentials", fetch = FetchType.LAZY)
	private User user;

	public UserCredentials() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserCredentials(Integer id, String token) {
		this.token = token;
	}

	public Short getAccesslevel() {
		return accesslevel;
	}

	public void setAccesslevel(Short accesslevel) {
		this.accesslevel = accesslevel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + Objects.hashCode(this.id);
		hash = 79 * hash + Objects.hashCode(this.token);
		hash = 79 * hash + Objects.hashCode(this.accesslevel);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserCredentials other = (UserCredentials) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.token, other.token)) {
			return false;
		}
		if (!Objects.equals(this.accesslevel, other.accesslevel)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserCredentials{" + "id=" + id + ", token=" + token + ", accesslevel=" + accesslevel + '}';
	}
}
