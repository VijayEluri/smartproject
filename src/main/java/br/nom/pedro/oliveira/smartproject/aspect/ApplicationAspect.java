package br.nom.pedro.oliveira.smartproject.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
@Aspect
public final class ApplicationAspect {
    
    private static final String APP_POINTCUT = "br.nom.pedro.oliveira.smartproject.aspect.SystemLayers.application()";
    
    @Before(APP_POINTCUT)
    public void beforeApplication() {
    }
    
        /**
     * On after service layer.
     */
    @AfterReturning(APP_POINTCUT)
    public void onAfterServiceLayer() {
    }

    /**
     * On exception service layer.
     */
    @AfterThrowing(pointcut = APP_POINTCUT, throwing = "ex")
    public void onExceptionServiceLayer(final Exception ex) {
    }
}
