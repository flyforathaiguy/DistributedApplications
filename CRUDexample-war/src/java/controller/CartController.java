/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.ShoppingCart;
import beans.SingletonBean;
import entities.ProductEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "cartController")
//@ManagedBean(name = "cartController", eager=true)
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private SingletonBean singletonBean;

    @EJB
    private ShoppingCart shoppingCart;

    public CartController() {
    }
    
    public String add(ProductEntity p){
        shoppingCart.addToCart(p);
        return "added_to_cart";
    }
    
    public String remove(ProductEntity p){
        shoppingCart.removeFromCart(p);
        return "remove_from_cart";
    }
    
    public String clearCart(){
        shoppingCart.clearCart();
        //singletonBean.messageCheckout();
        return "clear_cart";
    }
    
    //Voorlopige versie, nog uitbreiden met flows enz probably
    public String checkOut(){
        System.out.println("Starting checkout procedure");
        return "checkout_cart";
    }
    
    public boolean finalizeCheckout(){
        shoppingCart.clearCart();
        singletonBean.messageCheckout();
        return true;
    }
    
    public List<ProductEntity> findAll(){
        return shoppingCart.getCart();
    }
    
}
