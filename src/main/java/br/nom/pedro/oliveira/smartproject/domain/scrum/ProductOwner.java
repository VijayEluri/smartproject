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



package br.nom.pedro.oliveira.smartproject.domain.scrum;

//~--- non-JDK imports --------------------------------------------------------

import br.nom.pedro.oliveira.smartproject.domain.Contact;
import br.nom.pedro.oliveira.smartproject.domain.Project;
import br.nom.pedro.oliveira.smartproject.domain.common.Address;

//~--- JDK imports ------------------------------------------------------------

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * ProductOwner Entity
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public class ProductOwner {
    private Address address;
    private Contact contact;

    // Required Parameters
    private String             name;
    private SortedSet<Project> projects;

    // Optional Parameters
    private String title;

    /** cannot create default instance */
    private ProductOwner() {}

    public ProductOwner(String name) {
        this.name     = name;
        this.projects = Collections.synchronizedSortedSet(new TreeSet<Project>());
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    /**
     * Retreive a Sorted Set of Projects
     *
     * @return immutable set of Projects for this ProductOwner
     */
    public SortedSet<Project> retreiveProjects() {
        return Collections.unmodifiableSortedSet(projects);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
