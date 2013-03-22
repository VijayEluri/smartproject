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



package br.nom.pedro.oliveira.smartproject.domain;

//~--- non-JDK imports --------------------------------------------------------

import br.nom.pedro.oliveira.smartproject.domain.common.Address;

/**
 * SteakHolder Entity
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public final class SteakHolder {
    private Address address;
    private Contact contact;
    private String  name;
    private Team    team;

    /** Cannot create a default instance */
    private SteakHolder() {}

    /**
     * Create a instance of a SteakHolder
     * @param name the steak holder name
     * @param contact a Contact
     * @param team a Team
     */
    public SteakHolder(String name, Contact contact, Team team) {
        this.name    = name;
        this.contact = contact;
        this.team    = team;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
