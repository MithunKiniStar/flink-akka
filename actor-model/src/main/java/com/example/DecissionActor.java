package com.example;


import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.customer.core.Customer;



public class DecissionActor extends AbstractActor {

    LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);



    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Customer.class, customer -> {
                    if(customer.isCustomerNotified()) {
                        log.info("Customer Notified>>>>" + customer.isCustomerNotified());
                        if (customer.isCustomerFormSubmitted()) {
                            log.info("Customer Submitted>>>>" + customer.isCustomerFormSubmitted());
                        }
                    }
                })
                .build();
    }
}
