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
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author jaspe
 */
@Named(value = "cartController")
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
        return "index";
    }
    
    public String remove(ProductEntity p){
        shoppingCart.removeFromCart(p);
        return "cart";
    }
    
    public String clearCart(){
        shoppingCart.clearCart();
        singletonBean.messageCheckout();
        return "cart";
    }
    
    //Voorlopige versie, nog uitbreiden met flows enz probably
    public String checkOut(){
        singletonBean.addPurchase(shoppingCart.getCart().size());
        shoppingCart.clearCart();
        singletonBean.messageCheckout();
        return "index";
    }
    
    public List<ProductEntity> findAll(){
        return shoppingCart.getCart();
    }
    
}
