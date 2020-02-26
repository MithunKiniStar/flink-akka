package com.org.stream;

import java.io.IOException;
import java.util.Arrays;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.customer.core.Customer;
import com.typesafe.config.ConfigFactory;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This streaming application reads data from a socket and counts the words in it
 * 
 * @author Mithun Kini
 *
 */
public class WordCountSocketStream {

	private static ActorRef client = null;

	/** Logger */
	protected final static Logger LOGGER = LoggerFactory.getLogger(WordCountSocketStream.class);

	static ActorSystem system = ActorSystem.create("AkkaRemoteClient", ConfigFactory.load());

	/**
	 * The driver class
	 * @param args requires socket and port
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		LOGGER.info("Arguments were " + Arrays.toString(args));

		// set up the execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		DataStream<String> text = env.socketTextStream("localhost", 9999);
		DataStream<WordWithCount> windowCounts = text
				.flatMap(new FlatMapFunction<String, WordWithCount>() {
					@Override
					public void flatMap(String value, Collector<WordWithCount> out) throws IOException {
						ObjectMapper objectMapper = new ObjectMapper();
						Customer customer = objectMapper.readValue(value, Customer.class);
						tellActor(customer);
					}
				});
		windowCounts.print().setParallelism(1);
		// execute program
		env.execute("Streaming WordCount");
				 		
	}

	public static void tellActor(Customer customer){
		if(client == null) {
			client = system.actorOf(Props.create(FlinkActor.class), "flinkActor");
		}
		client.tell(customer, ActorRef.noSender());
		System.out.println(client.isTerminated());
	}

}
