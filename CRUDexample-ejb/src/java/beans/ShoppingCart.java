/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import misc.LoggingInterceptor;


@Stateful
@LocalBean
@Interceptors(LoggingInterceptor.class)
public class ShoppingCart {

    private List<ProductEntity> products = new ArrayList<ProductEntity>();
    
    @PostConstruct
    public void methodPostConstruct(){
        System.out.println("Cart Created");
    }
    
    @PreDestroy
    public void methodPreDestroy(){
        System.out.println("Cart Destroyed");
    }
    
    public List<ProductEntity> getCart(){
        return products;
    }
    
    public void addToCart(ProductEntity p){
        products.add(p);
    }
    
    public void removeFromCart(ProductEntity p){
        products.remove(p);
    }
    
    public void clearCart(){
        products.clear();
    }
    
}
