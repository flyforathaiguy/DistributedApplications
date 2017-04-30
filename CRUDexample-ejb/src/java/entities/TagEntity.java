/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author jaspe
 */
@Entity
public class TagEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull(message="Please enter a name")
    private String name;
    
    @ManyToMany
    @JoinTable(name = "joined_tag_product",
            joinColumns = @JoinColumn(name = "tag_fk"),
            inverseJoinColumns = @JoinColumn(name = "product_fk"))
    private List<ProductEntity> relatedProducts = new ArrayList<ProductEntity>();
//    
//    @PostConstruct
//    private void afterConstruction(){
//        relatedProducts = new ArrayList<>();
//    }
    
    public void addRelatedProduct(ProductEntity product){
         relatedProducts.add(product);
         //System.out.println("relatedProducts: " + relatedProducts);
    }
    
    public void removeRelatedProduct(ProductEntity product){
        relatedProducts.remove(product);
    }
    
    public void clearRelatedProducts(){
        relatedProducts.clear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getRelatedProducts() {
        return relatedProducts;
    }

    public void setRelatedProducts(List<ProductEntity> relatedProducts) {
        this.relatedProducts = relatedProducts;
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
        if (!(object instanceof TagEntity)) {
            return false;
        }
        TagEntity other = (TagEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TagEntity[ id=" + id + " ]";
    }
    
}
