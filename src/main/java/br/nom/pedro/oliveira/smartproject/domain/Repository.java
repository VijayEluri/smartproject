package br.nom.pedro.oliveira.smartproject.domain;

import com.ppm.infrastructure.dao.BasicDaoOperations;
import com.ppm.model.Entity;

/**
 * Generic Repository Interface.
 * 
 * @author Pedro T. Oliveira
 */
public interface Repository<T extends Entity> extends BasicDaoOperations<T> {
}
