package br.nom.pedro.oliveira.smartproject.integration.persistence;

import java.util.Arrays;

/**
 *
 * @author Pedro T. Oliveira <pedro.oliveira.nom.br>
 */
final class EntityHolder {
	   
     /** The hashcode. */
     private volatile int hashcode;
     
     /** The entity. */
     private final Object entity;
     
     /** The parents. */
     private final Object[] parents;
     
     /** The stacks. */
     private final StackTraceElement[] stacks;

     /**
      * Instantiates a new entity holder.
      * 
      * @param entity the entity
      * @param stacks the stacks
      */
     public EntityHolder(final Object entity, final StackTraceElement[] stacks) {
         this(entity, null, stacks);
     }
     
     /**
      * Instantiates a new entity holder.
      * 
      * @param entity the entity
      * @param parents the parents
      * @param stacks the stacks
      */
     public EntityHolder(final Object entity, final Object[] parents, final StackTraceElement[] stacks) {
         this.entity = entity;
         this.parents = parents;
         this.stacks = stacks;
     }

     /**
      * Gets the entity.
      * 
      * @return the entity
      */
     protected Object getEntity() {
         return this.entity;
     }
     
     /**
      * Gets the parents.
      * 
      * @return the parents
      */
     protected Object[] getParents() {
         return this.parents;
     }

     /**
      * Gets the stacks.
      * 
      * @return the stacks
      */
     protected StackTraceElement[] getStacks() {
         return this.stacks;
     }
     
     /**
      * Same identity as.
      * 
      * @param entity the entity
      * 
      * @return true, if successful
      */
     protected boolean sameIdentityAs(final Object entity) {
         return this.entity.equals(entity);         
     }
     
     /* (non-Javadoc)
      * @see java.lang.Object#equals(java.lang.Object)
      */
     @Override
     public boolean equals(Object obj) {
         if (this == obj) {
             return true;
         }

         if (obj instanceof EntityHolder) {
             final EntityHolder other = (EntityHolder) obj;
             if (this.entity != other.entity && (this.entity == null || !this.entity.equals(other.entity))) {
                 return false;
             }
             if (this.parents != other.parents && (this.parents == null || !Arrays.equals(this.parents, other.parents))) {
                 return false;
             }
             return true;
         }
         return false;
     }

     /* (non-Javadoc)
      * @see java.lang.Object#hashCode()
      */
     @Override
     public int hashCode() {
         if (hashcode == 0) {
             int hash = 5;
             hash = 89 * hash + (this.entity != null ? this.entity.hashCode() : 0);
             hash = 89 * hash + (this.parents != null ? this.parents.hashCode() : 0);
             hashcode = hash;
         }
         return hashcode;
     }
}
