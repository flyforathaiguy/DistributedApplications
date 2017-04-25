/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.TagEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jaspe
 */
@Stateless
public class TagEntityFacade extends AbstractFacade<TagEntity> {

    @PersistenceContext(unitName = "CRUDexample-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagEntityFacade() {
        super(TagEntity.class);
    }
    
}
