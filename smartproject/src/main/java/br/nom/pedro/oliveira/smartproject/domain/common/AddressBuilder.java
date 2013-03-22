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



package br.nom.pedro.oliveira.smartproject.domain.common;

/**
 * A Builder to create the Address ValueObject
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version
 * @since 1.0
 * @see br.nom.pedro.oliveira.smartproject.domain.Address
 */
public final class AddressBuilder {

    // Optional Parameters
    private String complement = "";
    private String street     = "";
    private String city;
    private String state;

    // Required Parameters
    private int zipCode;

    public AddressBuilder(int zipCode, String city, String state) {
        this.zipCode = zipCode;
        this.city    = city;
        this.state   = state;
    }

    public AddressBuilder complement(String complement) {
        this.complement = complement;

        return this;
    }

    public AddressBuilder street(String street) {
        this.street = street;

        return this;
    }

    public Address build() {
        return new Address(this);
    }

    /**
     * @return the zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @return the complement
     */
    public String getComplement() {
        return complement;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
