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

import java.util.Collections;
import java.util.Set;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 * @version 1.0
 * @since 1.0
 */
public final class ProductBacklog {

    private Project project;
    private Set<UserStory> projectStories;
    private Set<UserStory> completedStories;
    private Set<UserStory> unfinishedStories;
    private Set<Sprint> allProjectSprints;
    private Set<Sprint> oldSprints;
    private Set<Sprint> currentSprints;

    //Cant be instantiate
    private ProductBacklog() {
    }

    /**
     * 
     * @param project
     */
    public ProductBacklog(Project project) {
        this.project = project;
    }

    /**
     *
     * @return Project the current Project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @return the projectStories
     */
    public Set<UserStory> getProjectStories() {
        return Collections.unmodifiableSet(projectStories);
    }

    /**
     * @return the completedStories
     */
    public Set<UserStory> getCompletedStories() {
        return Collections.unmodifiableSet(completedStories);
    }

    /**
     * @return the unfinishedStories
     */
    public Set<UserStory> getUnfinishedStories() {
        return Collections.unmodifiableSet(unfinishedStories);
    }

    /**
     * @return the allProjectSprints
     */
    public Set<Sprint> getAllProjectSprints() {
        return Collections.unmodifiableSet(allProjectSprints);
    }

    /**
     * @return the oldSprints
     */
    public Set<Sprint> getOldSprints() {
        return Collections.unmodifiableSet(oldSprints);
    }

    /**
     * @return the currentSprints
     */
    public Set<Sprint> getCurrentSprints() {
        return Collections.unmodifiableSet(currentSprints);
    }

    /**
     * @param projectStories the projectStories to set
     */
    public void setProjectStories(Set<UserStory> projectStories) {
        this.projectStories = projectStories;
    }

    /**
     * @param completedStories the completedStories to set
     */
    public void setCompletedStories(Set<UserStory> completedStories) {
        if (completedStories != null) {
            this.completedStories.addAll(completedStories);
        }
    }

    /**
     * @param unfinishedStories the unfinishedStories to set
     */
    public void setUnfinishedStories(Set<UserStory> unfinishedStories) {
        if (unfinishedStories != null) {
            this.unfinishedStories.addAll(unfinishedStories);
        }
    }

    /**
     * @param allProjectSprints the allProjectSprints to set
     */
    public void setAllProjectSprints(Set<Sprint> allProjectSprints) {
        if (allProjectSprints != null) {
            this.allProjectSprints.addAll(allProjectSprints);
        }
    }

    /**
     * @param oldSprints the oldSprints to set
     */
    public void setOldSprints(Set<Sprint> oldSprints) {
        if (oldSprints != null) {
            this.oldSprints.addAll(oldSprints);
        }
    }

    /**
     * @param currentSprints the currentSprints to set
     */
    public void setCurrentSprints(Set<Sprint> currentSprints) {
        if (currentSprints != null) {
            this.currentSprints.addAll(currentSprints);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ProductBacklog other = (ProductBacklog) obj;
        if (this.project != other.getProject() && (this.project == null || !this.project.equals(other.project))) {
            return false;
        }
        if (this.projectStories != other.projectStories && (this.projectStories == null || !this.projectStories.equals(other.projectStories))) {
            return false;
        }
        if (this.completedStories != other.completedStories && (this.completedStories == null || !this.completedStories.equals(other.completedStories))) {
            return false;
        }
        if (this.unfinishedStories != other.unfinishedStories && (this.unfinishedStories == null || !this.unfinishedStories.equals(other.unfinishedStories))) {
            return false;
        }
        if (this.allProjectSprints != other.allProjectSprints && (this.allProjectSprints == null || !this.allProjectSprints.equals(other.allProjectSprints))) {
            return false;
        }
        if (this.oldSprints != other.oldSprints && (this.oldSprints == null || !this.oldSprints.equals(other.oldSprints))) {
            return false;
        }
        if (this.currentSprints != other.currentSprints && (this.currentSprints == null || !this.currentSprints.equals(other.currentSprints))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.project != null ? this.project.hashCode() : 0);
        hash = 89 * hash + (this.projectStories != null ? this.projectStories.hashCode() : 0);
        hash = 89 * hash + (this.completedStories != null ? this.completedStories.hashCode() : 0);
        hash = 89 * hash + (this.unfinishedStories != null ? this.unfinishedStories.hashCode() : 0);
        hash = 89 * hash + (this.allProjectSprints != null ? this.allProjectSprints.hashCode() : 0);
        hash = 89 * hash + (this.oldSprints != null ? this.oldSprints.hashCode() : 0);
        hash = 89 * hash + (this.currentSprints != null ? this.currentSprints.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ProductBacklog{"
                + "project=" + project
                + "projectStories=" + projectStories
                + "completedStories=" + completedStories
                + "unfinishedStories=" + unfinishedStories
                + "allProjectSprints=" + allProjectSprints
                + "oldSprints=" + oldSprints
                + "currentSprints=" + currentSprints + '}';
    }
}
