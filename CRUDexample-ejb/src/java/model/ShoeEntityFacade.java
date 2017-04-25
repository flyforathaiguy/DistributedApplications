/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entities.ShoeEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jaspe
 */
@Stateless
public class ShoeEntityFacade extends AbstractFacade<ShoeEntity> {

    @PersistenceContext(unitName = "CRUDexample-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShoeEntityFacade() {
        super(ShoeEntity.class);
    }
    
}
