/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.ShirtEntity;
import entities.TagEntity;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import model.ShirtEntityFacade;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import model.TagEntityFacade;

/**
 *
 * @author jaspe
 */
@Named(value = "shirtController")
@SessionScoped
public class ShirtController implements Serializable {

    @EJB
    private TagEntityFacade tagEntityFacade;

    @EJB
    private ShirtEntityFacade shirtEntityFacade;
    private ShirtEntity shirt = new ShirtEntity();
    
    public ShirtController() {
    }

    public ShirtEntity getShirt() {
        return shirt;
    }

    public void setShirt(ShirtEntity shirt) {
        this.shirt = shirt;
    }
    
    public List<ShirtEntity> findAll(){
        return this.shirtEntityFacade.findAll();
    }
    
    public String add(){
        this.shirtEntityFacade.create(this.shirt);
        return "product_added";
    }
    
    public void remove(ShirtEntity shirt){
        
        //When a product is deleted, make sure to remove all tags that this product used
        for(TagEntity p : shirt.getRelatedTags()){
            p.removeRelatedProduct(shirt);
            this.tagEntityFacade.edit(p);
        }
        shirt.clearRelatedTags();
        this.shirtEntityFacade.remove(shirt);
    }
    
    public String edit(ShirtEntity shirt){
        this.shirt = shirt;
        return "edit_a_shirt";
    }
    
    public String edit(){
        this.shirtEntityFacade.edit(this.shirt);
        return "product_edited";
    }
    
    public String removeTag(ShirtEntity shirt, TagEntity tag){
        shirt.removeRelatedTag(tag);
        tag.removeRelatedProduct(shirt);
        this.tagEntityFacade.edit(tag);
        this.shirtEntityFacade.edit(shirt);
        return "product_edited";
    }
    
    
}
