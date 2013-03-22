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
package br.nom.pedro.oliveira.smartproject.web.validator;

import br.nom.pedro.oliveira.smartproject.web.dto.LoginData;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * The Login Data Validator
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@FacesValidator(value = "loginDataValidator")
public class LoginDataValidator implements Validator {

    /**
     * Creates a new instance of LoginDataValidator
     */
    public LoginDataValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        LoginData loginData = new LoginData();

        if (value instanceof LoginData) {
            loginData = (LoginData) value;
        }

        final String userIdentity = loginData.getUserIdentity();
        FacesMessage erro = new FacesMessage();

        if ((userIdentity == null) || userIdentity.trim().isEmpty()) {
            erro.setSeverity(FacesMessage.SEVERITY_WARN);
            erro.setSummary("UserIdentity Error");
            erro.setDetail(userIdentity);

            throw new ValidatorException(erro);
        }
    }
}
