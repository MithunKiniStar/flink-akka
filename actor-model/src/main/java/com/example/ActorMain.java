package com.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.customer.core.Customer;
import com.typesafe.config.ConfigFactory;

public class ActorMain {

    public static void main(String... args) {
        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());
        Customer customer = new Customer();
        ActorRef customerRef = system.actorOf(Props.create(DecissionActor.class),"DecissionActor");
        customerRef.tell(customer,ActorRef.noSender());
    }

}
