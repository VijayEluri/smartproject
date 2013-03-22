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

//~--- non-JDK imports --------------------------------------------------------

import br.nom.pedro.oliveira.smartproject.domain.ValueFormatException;

/**
 * Represents a Address
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public final class Address {
    private final String comma = ", ";
    private final String slash = " - ";
    private String       city;
    private String       complement;
    private String       state;
    private String       street;
    private int          zipCode;

    protected Address(AddressBuilder builder) {
        this.zipCode    = builder.getZipCode();
        this.city       = builder.getCity();
        this.state      = builder.getState();
        this.complement = builder.getComplement();
        this.street     = builder.getStreet();
    }

    public Address(String street, int zipCode, String complement, String city, String state) {
        this.street     = street;
        this.zipCode    = zipCode;
        this.complement = complement;
        this.city       = city;
        this.state      = state;
    }

    /**
     * Get the value of street
     *
     * @return the value of street
     */
    public String getStreet() {
        return (street == null)
               ? ""
               : street;
    }

    /**
     * Set the value of street
     *
     * @param street new value of street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the complement
     */
    public String getComplement() {
        return (complement == null)
               ? ""
               : complement;
    }

    /**
     * @param complement the complement to set
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setValue(String theAddress) throws ValueFormatException {

        /**
         * Pattern Example:
         * Rua das Palmeiras, 230 Apto 26, CEP 0126-010, Sao Paulo - SP
         */
        String streetPattern     = "";
        String complementPattern = "";
        String adrressPattern    = "";
    }

    public String toFormatedString() {
        return street + comma + complement + comma + zipCode + comma + city + slash + state;
    }

    @Override
    public String toString() {
        return "Address{" + "street=" + street + "zipCode=" + zipCode + "complement=" + complement + "city=" + city
               + "state=" + state + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Address o = (Address) obj;

        if ((this.street == null)
            ? (o.getStreet() != null)
            : !this.street.equals(o.getStreet())) {
            return false;
        }

        if (this.zipCode != o.getZipCode()) {
            return false;
        }

        if ((this.complement == null)
            ? (o.getComplement() != null)
            : !this.complement.equals(o.getComplement())) {
            return false;
        }

        if ((this.city == null)
            ? (o.getCity() != null)
            : !this.city.equals(o.getCity())) {
            return false;
        }

        if ((this.state == null)
            ? (o.getState() != null)
            : !this.state.equals(o.getState())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;

        hash = 97 * hash + ((this.street != null)
                            ? this.street.hashCode()
                            : 0);
        hash = 97 * hash + this.zipCode;
        hash = 97 * hash + ((this.complement != null)
                            ? this.complement.hashCode()
                            : 0);
        hash = 97 * hash + ((this.city != null)
                            ? this.city.hashCode()
                            : 0);
        hash = 97 * hash + ((this.state != null)
                            ? this.state.hashCode()
                            : 0);

        return hash;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
