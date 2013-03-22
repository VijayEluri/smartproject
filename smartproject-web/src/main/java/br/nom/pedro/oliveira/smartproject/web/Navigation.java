/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.pedro.oliveira.smartproject.web;

/**
 *
 * @author Pedro T. Oliveira
 */
public enum Navigation {
    
    NEW_PROJECT("./project/new.xhtml"),
    DASHBOARD("./project/dashboard.xhtml");
    
    private final String viewId;

    private Navigation(String viewId) {
        this.viewId = viewId;
    }
    
    public String view() {
        return viewId;
    }
}
