package com.org;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * Function to split a line into words
 * 
 * @author mans2singh
 */
public class WordSplitter implements FlatMapFunction<String,String> {

	/**
	 * Serial version of the class
	 */
	private static final long serialVersionUID = -3934658156048663316L;
	
	/**
	 * Split token for words
	 */
	private static final String WORD_SPLIT = " ";

	/**
	 * Split line into words separated by space
	 */
	@Override
	public void flatMap(String line, Collector<String> collector) throws Exception {
		String [] words = line.split(WORD_SPLIT);
		for (String word: words) {
			collector.collect(word);
		}
	}

}
