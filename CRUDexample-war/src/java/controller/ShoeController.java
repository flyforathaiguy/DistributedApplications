/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import model.ShoeEntityFacade;
import model.TagEntityFacade;

/**
 *
 * @author jaspe
 */
@Named(value = "shoeController")
@SessionScoped
public class ShoeController implements Serializable {

    @EJB
    private TagEntityFacade tagEntityFacade;

    @EJB
    private ShoeEntityFacade shoeEntityFacade;
    private ShoeEntity shoe = new ShoeEntity();

    public ShoeEntity getShoe() {
        return shoe;
    }

    public void setShoe(ShoeEntity shoe) {
        this.shoe = shoe;
    }

    
    public ShoeController() {
    }
    
    public List<ShoeEntity> findAll(){
        return this.shoeEntityFacade.findAll();
    }
    
    public String add(){
        this.shoeEntityFacade.create(this.shoe);
        return "product_added";
    }
    
    public void remove(ShoeEntity shoe){
        
        //When a product is deleted, make sure to remove all tags that this product used
        for(TagEntity p : shoe.getRelatedTags()){
            p.removeRelatedProduct(shoe);
            this.tagEntityFacade.edit(p);
        }
        shoe.clearRelatedTags();
        this.shoeEntityFacade.remove(shoe);
    }
    
    public String edit(ShoeEntity shoe){
        this.shoe = shoe;
        return "edit_a_shoe";
    }
    
    public String edit(){
        this.shoeEntityFacade.edit(this.shoe);
        return "product_edited";
    }
}
