package br.nom.pedro.oliveira.smartproject.domain.annotation;

import java.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Defines an application's boundary with a layer of services that establishes a
 * set of available operations and coordinates the application's response in
 * each operation.
 * 
 * @author Pedro Oliveira
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ServiceLayer {
	/**
	 * The value may indicate a suggestion for a logical component name, to be
	 * turned into a Spring bean in case of an autodetected component.
	 * 
	 * @return the suggested component name, if any
	 */
	String value() default "";
}
