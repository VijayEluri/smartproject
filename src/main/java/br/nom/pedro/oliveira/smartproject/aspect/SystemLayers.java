/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.nom.pedro.oliveira.smartproject.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * System Layers Aspects Definitions
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Aspect
public class SystemLayers {
    
    @Pointcut("execution(* br.nom.pedro.oliveira.smartproject.application.*.*(..))")
    public void application() {}
    
    @Pointcut("execution(* br.nom.pedro.oliveira.smartproject.domain.*.*(..))")
    public void domain(){}
    
    @Pointcut("execution(* br.nom.pedro.oliveira.smartproject.integration.*.*(..))")
    public void integration(){}
}
