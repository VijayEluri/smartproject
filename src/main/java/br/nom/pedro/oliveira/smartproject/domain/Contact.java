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

/**
 * Contact entity
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public final class Contact {

    private int primaryPhoneNumber;
    private int secondaryPhoneNumber;
    private String email;
    /** not required, in Portuguese called "ramal" */
    private int branch;

    public Contact(int primaryPhoneNumber, String email) {
        this.primaryPhoneNumber = primaryPhoneNumber;
        this.email = email;
    }

    /**
     * @return the primaryPhoneNumber
     */
    public int getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    /**
     * @param primaryPhoneNumber the primaryPhoneNumber to set
     */
    public void setPrimaryPhoneNumber(int primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    /**
     * @return the secondaryPhoneNumber
     */
    public int getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    /**
     * @param secondaryPhoneNumber the secondaryPhoneNumber to set
     */
    public void setSecondaryPhoneNumber(int secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the branch
     */
    public int getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(int branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Contact{" + "primaryPhoneNumber=" + primaryPhoneNumber
                + "secondaryPhoneNumber=" + secondaryPhoneNumber
                + "email=" + email + "branch=" + branch + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Contact other = (Contact) obj;
        if (this.primaryPhoneNumber != other.getPrimaryPhoneNumber()) {
            return false;
        }

        if (this.secondaryPhoneNumber != other.getSecondaryPhoneNumber()) {
            return false;
        }

        if ((this.email == null)
                ? (other.getEmail() != null)
                : !this.email.equals(other.getEmail())) {

            return false;
        }

        if (this.branch != other.getBranch()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.primaryPhoneNumber;
        hash = 89 * hash + this.secondaryPhoneNumber;
        hash = 89 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 89 * hash + this.branch;
        return hash;
    }
}
