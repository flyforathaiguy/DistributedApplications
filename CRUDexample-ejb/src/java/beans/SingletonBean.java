/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import misc.LoggingInterceptor;

/**
 *
 * @author jaspe
 */
@Startup
@Singleton
@LocalBean
public class SingletonBean {

    @Resource(mappedName = "jms/myqueue")
    private Queue queue;

//    @Inject
//    @JMSConnectionFactory("java:comp/myQueueFactory")
//    private JMSContext context;
    
    @Resource(mappedName = "jms/myQueueFactory")
    private QueueConnectionFactory connFactory;
   
    private int purchasesToday;
    private int purchasesAllTime;
    
    public void addPurchase(int amount){
        purchasesToday+=amount;
        purchasesAllTime+=amount;
    }
    
    //Clear purchases of the day every day at midnight
    @Schedule(minute="0", hour="0", persistent = true)
    private void resetDayCounter(){
        purchasesToday = 0;
    }
    
    //Put message in a queue concerning opening hours of shop
    //Non persistent, state should not be kept in a DB or missed triggers compensated when server comes back online
    @Schedules ({
        @Schedule(hour="8", dayOfWeek="Mon-Fri", persistent = false),
        @Schedule(hour="9", minute="30", dayOfWeek="Sat", persistent = false)
    })
    public void messageOpeningTime(){
        
        //Simplified API
//        sendJMSMessageToMyqueue("The shop in Leuven opens now");

        sendMessage("The shop in Leuven opens now"); 
    }
    
    //Non persistent, state should not be kept in a DB or missed triggers compensated when server comes back online
    @Schedules ({
        @Schedule(hour="18", dayOfWeek="Mon-Fri", persistent = false),
        @Schedule(hour="16", minute="30", dayOfWeek="Sat", persistent = false)
    })
    public void messageClosingTime(){
        
        //Simplified API
//        sendJMSMessageToMyqueue("The shop in Leuven closes now");

        sendMessage("The shop in Leuven closes now");
    }
    
    public void messageCheckout(){
        sendMessage("A checkout has happened");
    }
    
    @Interceptors(LoggingInterceptor.class)
    private void sendMessage(String text){
        
        //Classic API
        try {

            Connection connection = connFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = (MessageProducer)session.createProducer(queue);
            messageProducer.setTimeToLive(60000);
            TextMessage textMessage = session.createTextMessage(text);
            messageProducer.send(textMessage);
        } catch (JMSException ex) {
            Logger.getLogger(SingletonBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPurchasesToday() {
        return purchasesToday;
    }

    public void setPurchasesToday(int purchasesToday) {
        this.purchasesToday = purchasesToday;
    }

    public int getPurchasesAllTime() {
        return purchasesAllTime;
    }

    public void setPurchasesAllTime(int purchasesAllTime) {
        this.purchasesAllTime = purchasesAllTime;
    }

//    private void sendJMSMessageToMyqueue(String messageData) {
//        context.createProducer().send(queue, messageData);
//    }
    
}
