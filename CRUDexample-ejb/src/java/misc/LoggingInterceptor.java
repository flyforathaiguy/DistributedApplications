/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

import entities.ProductEntity;
import java.util.List;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggingInterceptor {
    
    @AroundInvoke
    public Object incercept(InvocationContext ic) throws Exception{
    
        //Check what class & method we are intercepting
        if(ic.getTarget().getClass().getSimpleName().equalsIgnoreCase("shoppingcart")){
            
            if(ic.getMethod().getName().equalsIgnoreCase("addtocart")){
                ProductEntity prod = (ProductEntity) (ic.getParameters())[0];       //Intercept the ProductEntity that is passed to this method
                System.out.println("Intercepted product to add to cart: " + prod.getName());
            }
            
            else if(ic.getMethod().getName().equalsIgnoreCase("getcart")){
                List<ProductEntity> interceptedList = (List<ProductEntity>) (ic.proceed());
                System.out.println("---------------Intercepter list from getCart() method:  -----------------");
                interceptedList.forEach((p) -> {
                    System.out.println(p.getName());
                });
                System.out.println("-------------------------------------------------------------------------");
            }
        }
        
        else if(ic.getTarget().getClass().getSimpleName().equalsIgnoreCase("singletonbean")){
            //Only 1 method to intercept, no need to check which
            String text = (String) ic.getParameters()[0];
            System.out.println("Intercepted message to be sent to queue:");
            System.out.println(text);
        
        }
        
//        System.out.println("Intercepted class name: " + ic.getClass().getName() + ", interepted method name: " + ic.getMethod().getName());
//        System.out.println("Canonical name: " + ic.getClass().getCanonicalName());
//        System.out.println("Simple name: " + ic.getClass().getSimpleName());
//        System.out.println("Target: " + ic.getTarget());
//        System.out.println("xxx: " + ic.getTarget().getClass().getName());
//        System.out.println("yyy: " + ic.getTarget().getClass().getCanonicalName());
//        System.out.println("zzz: " + ic.getTarget().getClass().getSimpleName());
        return ic.proceed();
    }
    
}
