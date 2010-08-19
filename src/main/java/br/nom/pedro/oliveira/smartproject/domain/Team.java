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

//~--- JDK imports ------------------------------------------------------------

import java.util.Set;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public abstract class Team {
    private Set<TeamMember> members;
    private Set<Project>    projects;
}


//~ Formatted by Jindent --- http://www.jindent.com
