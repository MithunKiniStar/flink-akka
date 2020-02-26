package com.example;


import akka.actor.AbstractActor;
import com.customer.core.Customer;



public class DecissionActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Customer.class, customer -> {
                    if(customer.isCustomerNotified()) {
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
                        System.out.println("Customer Notified>>>>" + customer.isCustomerNotified());
                        if (customer.isCustomerFormSubmitted()) {
                            System.out.println("Customer Submitted>>>>" + customer.isCustomerFormSubmitted());
                        }
                    }
                })
                .build();
    }
}
