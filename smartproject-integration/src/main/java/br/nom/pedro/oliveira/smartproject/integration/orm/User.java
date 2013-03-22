/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.pedro.oliveira.smartproject.integration.orm;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Entity
@Table(name = "SmUser")
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
	@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
	@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "id", unique= true)
	@SequenceGenerator(name = "SmUser_Seq_Gen", sequenceName = "SmUser_Seq", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SmUser_Seq_Gen")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "username", unique = true)
	private String username;
	@Basic(optional = false)
	@Column(name = "email", unique = true)
	private String email;
	@JoinColumn(nullable = false, name = "token", referencedColumnName = "id", insertable = true, updatable = true)
	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	private UserCredentials userCredentials;
	@JoinColumn(nullable = false, name = "password", referencedColumnName = "id", insertable = true, updatable = true)
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
	private Password password;

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof User)) {
			return false;
		}
		User other = (User) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.nom.pedro.oliveira.smartproject.integration.orm.User[ id=" + id + " ]";
	}
}
