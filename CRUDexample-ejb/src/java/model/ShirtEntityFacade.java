/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.ShirtEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jaspe
 */
@Stateless
public class ShirtEntityFacade extends AbstractFacade<ShirtEntity> {

    @PersistenceContext(unitName = "CRUDexample-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShirtEntityFacade() {
        super(ShirtEntity.class);
    }
    
}
