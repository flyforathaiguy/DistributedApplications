/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.TagEntityFacade;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "PRODUCT_TYPE")
@XmlRootElement
public abstract class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    @NotNull(message="Please choose a username")
    protected String name;
    @NotNull
    @Min(value=0, message="Price can't be lower than 0")
    protected int price;
    @NotNull
    @Min(value=0, message="Quantity can't be lower than 0")
    protected int quantity;
    @NotNull
    @Min(value=0, message="Size can't be lower than 0")
    int size;
    
    @ManyToMany(mappedBy = "relatedProducts")
    private List<TagEntity> relatedTags;
    
    @PostConstruct
    private void afterConstruction(){
        relatedTags = new ArrayList<>();
    }
    
    public void addRelatedTag(TagEntity tag){
        if(!relatedTags.contains(tag)){
            relatedTags.add(tag);
        }
    }
    
    public void removeRelatedTag(TagEntity tag){
        relatedTags.remove(tag);
    }
    
    public void clearRelatedTags(){
        relatedTags.clear();
    }

    @XmlTransient
    public List<TagEntity> getRelatedTags() {
        return relatedTags;
    }

    public void setRelatedTags(List<TagEntity> relatedTags) {
        this.relatedTags = relatedTags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductEntity)) {
            return false;
        }
        ProductEntity other = (ProductEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
        //return "entities.ProductEntity[ id=" + id + " ]";
    }
    
    public String toInfoString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(name);
        builder.append('\t');
        builder.append(", price: ");
        builder.append(price);
        return builder.toString();
    } 
    
    public void setSize(int size){
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
}
