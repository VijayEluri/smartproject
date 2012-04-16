/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.pedro.oliveira.smartproject.aspect;

import com.db4o.ObjectContainer;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Aspect
public class ApplicationAspect {

    @Before(value = "br.nom.pedro.oliveira.smartproject.aspect.SystemLayers.application()",
            argNames = "rootObjectContainer")
    public void beforeApplication(ObjectContainer rootObjectContainer) {
        
    }
}
