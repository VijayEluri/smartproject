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

import java.sql.Time;

import java.util.Date;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version
 * @since
 */
public class Task {
    private boolean assigned;
    private boolean completed;
    private Date    finishedDate;
    private boolean locked;
    private Time    progress;
    private Date    startDate;
    private String  taskName;
}


//~ Formatted by Jindent --- http://www.jindent.com
