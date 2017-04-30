/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.ProductEntity;
import entities.TagEntity;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import model.ProductEntityFacade;
import model.TagEntityFacade;

/**
 *
 * @author jaspe
 */
@Named(value = "tagController")
@SessionScoped
public class TagController implements Serializable {

    @EJB
    private ProductEntityFacade productEntityFacade;

    @EJB
    private TagEntityFacade tagEntityFacade;
    TagEntity tag = new TagEntity();

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
    
    private List<String> products;

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    public TagController() {
    }
    
    public String removeProduct(TagEntity tag, ProductEntity product){
        tag.removeRelatedProduct(product);
        product.removeRelatedTag(tag);
        this.tagEntityFacade.edit(tag);
        this.productEntityFacade.edit(product);
        return "index_tag";
    }
    
    public List<ProductEntity> getRelatedProducts(){
        System.out.println("getRelatedProducts() size: " + this.tag.getRelatedProducts().size());
        return this.tag.getRelatedProducts();
    }
    
    public List<TagEntity> findAll(){
        return tagEntityFacade.findAll();
    }
    
    public List<ProductEntity> findAllProducts(){
        return productEntityFacade.findAll();
    }
    
    public String add(){
        System.out.println("Start of add----------------------------");
        
        
        this.tagEntityFacade.create(this.tag);      //Hier werd veel werk BTS gedaan waardoor soms een tag 2x gecreëerd werd. Om 100% duiding te hebben op wat gebeurt,
        this.addProducts();                         //deze 3 lijnen in deze volgorde doen
        this.tagEntityFacade.edit(this.tag);
        
        products.clear();
        
        System.out.println("Tag name: " + this.tag.getName());
        System.out.println("relatedProducts: " + this.tag.getRelatedProducts());
        
        this.tag = new TagEntity();
        return "index_tag";
    }
    
    public String remove(TagEntity tag){
        
        //When a tag is deleted, make sure this tag is removed from all products that used this tag
        for(ProductEntity p : tag.getRelatedProducts()){
            p.removeRelatedTag(tag);
            this.productEntityFacade.edit(p);
        } 
        tag.clearRelatedProducts();
        this.tagEntityFacade.remove(tag);
        
        return "index_tag";
    }
    
    public String edit(TagEntity newTag){
        this.tag = newTag;
        return "edit_tag";
    }
    
    public String edit(){
        for(ProductEntity p : tag.getRelatedProducts()){
            p.removeRelatedTag(tag);
            this.productEntityFacade.edit(p);
        }
        this.tag.clearRelatedProducts();
        this.addProducts();
        this.tagEntityFacade.edit(this.tag);
        return "index_tag";
    }
    
    private void addProducts(){
        for(String s : products){
            System.out.println("String: " + s);
            try{
                ProductEntity p = this.productEntityFacade.find(Long.valueOf(s));
                System.out.println("Name: " + p.getName());
                this.tag.addRelatedProduct(p);
                p.addRelatedTag(this.tag);
                this.productEntityFacade.edit(p);
            } catch(Exception e){
                System.out.println("ProductEntity not in DB");
            }
        }
    }
    
}
