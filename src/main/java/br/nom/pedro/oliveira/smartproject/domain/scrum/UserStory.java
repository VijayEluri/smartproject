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

import br.nom.pedro.oliveira.smartproject.domain.Project;

/**
 * UserStory Entity
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public final class UserStory {
    private final ProductBacklog backlog;
    private Sprint               currentSprint;
    private String               detailedDescription;
    private int                  estimatePoints;
    private String               name;
    private Project              project;
    private String               smallDescription;
    private final String         title;

    /** Cannot Create default instance */
    private UserStory() {
        this.title   = "";
        this.backlog = null;
    }

    /**
     * Construct a UserStory
     * @param title represents a Story Title.
     * @param backlog A Object ProductBacklog that contains this Story.
     */
    public UserStory(String title, ProductBacklog backlog) {
        this.title   = title;
        this.backlog = backlog;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the smallDescription
     */
    public String getSmallDescription() {
        return smallDescription;
    }

    /**
     * @param smallDescription the smallDescription to set
     */
    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    /**
     * @return the estimatePoints
     */
    public int getEstimatePoints() {
        return estimatePoints;
    }

    /**
     * @param estimatePoints the estimatePoints to set
     */
    public void setEstimatePoints(int estimatePoints) {
        this.estimatePoints = estimatePoints;
    }

    /**
     * @return the detailedDescription
     */
    public String getDetailedDescription() {
        return detailedDescription;
    }

    /**
     * @param detailedDescription the detailedDescription to set
     */
    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    /**
     * @return the backlog
     */
    public ProductBacklog getBacklog() {
        return backlog;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the currentSprint
     */
    public Sprint getCurrentSprint() {
        return currentSprint;
    }

    /**
     * @param currentSprint the currentSprint to set
     */
    public void setCurrentSprint(Sprint currentSprint) {
        this.currentSprint = currentSprint;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
