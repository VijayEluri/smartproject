/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.pedro.oliveira.smartproject.integration.orm;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Entity
@Table(name = "Password")
@NamedQueries({
	@NamedQuery(name = "Password.findAll", query = "SELECT p FROM Password p"),
	@NamedQuery(name = "Password.findById", query = "SELECT p FROM Password p WHERE p.pass = :pass"),
	@NamedQuery(name = "Password.findByPassphrase", query = "SELECT p FROM Password p WHERE p.passphrase = :passphrase")})
public class Password implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "Password_Seq_Gen", sequenceName = "Password_Seq", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "Password_Seq_Gen")
	private Long id;
	@Basic(optional = false)
	@Column(name = "pass")
	private String pass;
	@Column(name = "passphrase")
	private String passphrase;
	@OneToOne(optional = false, cascade = CascadeType.REMOVE, mappedBy = "Password", fetch = FetchType.LAZY)
	private User user;

	public Password() {
	}

	public Password(String pass) {
		this.pass = pass;
	}

	public Password(Long id, String pass) {
		this.id = id;
		this.pass = pass;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 67 * hash + Objects.hashCode(this.id);
		hash = 67 * hash + Objects.hashCode(this.pass);
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
		final Password other = (Password) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.pass, other.pass)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Password{" + "id=" + id + ", pass=" + pass + ", passphrase=" + passphrase + ", user=" + user + '}';
	}
}
