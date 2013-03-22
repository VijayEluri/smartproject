/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.pedro.oliveira.smartproject.web.controller;

import static br.nom.pedro.oliveira.smartproject.web.Navigation.DASHBOARD;
import static br.nom.pedro.oliveira.smartproject.web.Navigation.NEW_PROJECT;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pedrot
 */
@ManagedBean
@SessionScoped
public class MainPageController {

    private final String viewIdPanel;
    private String currentView;

    /**
     * Creates a new instance of MainPageController
     */
    public MainPageController() {
        currentView = "";
        viewIdPanel = "main_content_panel";
    }

    public String getViewIdPanel() {
        return viewIdPanel;
    }

    public String getCurrentView() {
        return currentView;
    }

    public void setCurrentView(String currentView) {
        this.currentView = currentView;
    }

    public void startNewProject() {
        setCurrentView(NEW_PROJECT.view());
    }

    public void goToProjectDashboard() {
        setCurrentView(DASHBOARD.view());
    }
}
