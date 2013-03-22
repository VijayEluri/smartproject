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
package br.nom.pedro.oliveira.smartproject.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.nom.pedro.oliveira.smartproject.application.ServiceException;
import br.nom.pedro.oliveira.smartproject.application.UserServices;
import br.nom.pedro.oliveira.smartproject.domain.User;
import br.nom.pedro.oliveira.smartproject.domain.UserId;
import br.nom.pedro.oliveira.smartproject.domain.common.Email;
import br.nom.pedro.oliveira.smartproject.web.dto.LoginData;
import br.nom.pedro.oliveira.smartproject.web.mapper.LoginDataMapper;
import br.nom.pedro.oliveira.smartproject.web.util.Messages;

/**
 * The UserLogin Form Component
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@ManagedBean
@RequestScoped
public class LoginController {

	@ManagedProperty(value = "#{loginData}")
	private LoginData loginData;
	@ManagedProperty(value = "#{loginDataMapper}")
	private LoginDataMapper loginDataMapper;
	private String outcome;
	@Autowired
	private UserServices userServices;

	/**
	 * Creates a new instance of LoginController
	 */
	public LoginController() {
	}

	public void doLogin() {
		try {
			final User user = userServices.authenticate(newUserId());
			if (user.isAuthenticated()) {
				outcome = "loginsuccess";
			} else {
				final FacesMessage message = Messages.create("loginfailed", FacesMessage.SEVERITY_WARN);
				FacesContext.getCurrentInstance().addMessage(null, message);
				outcome = "loginfail";
			}

		} catch (ServiceException ex) {
			final FacesMessage message = Messages.create("loginerror", FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, message);
			outcome = "loginfail";
		}
	}

	public String proccessRequest() {
		//System.out.println("Outcome: " + outcome);
		return outcome;
	}

	public LoginDataMapper getLoginDataMapper() {
		return loginDataMapper;
	}

	public void setLoginDataMapper(LoginDataMapper loginDataMapper) {
		this.loginDataMapper = loginDataMapper;
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}

	/**
	 * For Dependency Injection
	 */
	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}

	private UserId newUserId() {
		return UserId.newId(new Email(loginData.getUserIdentity()), loginData.getPassword());
	}
}