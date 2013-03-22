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



package br.nom.pedro.oliveira.smartproject.web.mapper;

//~--- non-JDK imports --------------------------------------------------------

import br.nom.pedro.oliveira.smartproject.domain.UserId;
import br.nom.pedro.oliveira.smartproject.web.dto.LoginData;

//~--- JDK imports ------------------------------------------------------------

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@ManagedBean
@ApplicationScoped
public class LoginDataMapper {

    /** Creates a new instance of LoginDataMapper */
    public LoginDataMapper() {}

    public UserId map(LoginData loginData) {
        return null;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
