package com.org.stream;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.customer.core.Customer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlinkActor extends AbstractActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(),this);

    private ActorSelection remoteActor = getContext().actorSelection("akka.tcp://AkkaRemoteServer@127.0.0.1:2555/user/DecissionActor");

    Map<String, Integer> customerMap = new ConcurrentHashMap<>();


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Customer.class, customer -> {
                    log.info("Customer Email>>>>" + customer.getEmail());
                    if(customerMap.get(customer.getEmail()) == null){
                        customerMap.putIfAbsent(customer.getEmail(),1);
                    }else {
                        Integer counter = customerMap.get(customer.getEmail());
                        customerMap.put(customer.getEmail(), ++counter);
                        if(counter==3 || customer.isCustomerFormSubmitted()) {
                            log.info("Customer Targeted>>>>" + customer.getEmail());
                            customer.setCustomerNotified(true);
                            remoteActor.tell(customer, getSelf());
                        }
                    }
                })
        .build();
    }
}
