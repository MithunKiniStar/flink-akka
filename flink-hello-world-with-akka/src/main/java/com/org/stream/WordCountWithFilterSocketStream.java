package com.org.stream;

import java.util.Arrays;

import com.org.ArticleFilter;
import com.org.WordMapper;
import com.org.WordSplitter;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Socket stream application with filtering
 * 
 * @author mans2singh
 *
 */
public class WordCountWithFilterSocketStream {

	/**
	 * Logger for the class
	 */
	protected final static Logger LOGGER = LoggerFactory.getLogger(WordCountWithFilterSocketStream.class);
	
	/**
	 * Driver for the streaming filter app
	 * @param args - host and port for streaming
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		LOGGER.info("Arguments were " + Arrays.toString(args));
		
		if ( args.length != 2 ) {
			LOGGER.error("Please enter the host and port to listen");
			return;
		}
		
		// set up the execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// get input data
		DataStream<String> text = env.socketTextStream(args[0], Integer.parseInt(args[1]));

		// Split the words and apply filter
		DataStream<String> textWords = text.flatMap(new WordSplitter()).
				filter(new ArticleFilter());

		/**
		 * Count the distinct words
		 */
		DataStream<Tuple2<String, Integer>> result = textWords.map(new WordMapper())
//				.
//				groupBy(0).
//				sum(1);
		;
		
		result.print();

		// execute program
		env.execute("Streaming word count with article filter");
				 		
	}

}
