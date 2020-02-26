package com.org;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * Maps a word to a tuple with a count 1
 * 
 * @author mans2singh
 *
 */
public class WordMapper implements MapFunction<String, Tuple2<String,Integer>> {

	/**
	 * Serial version of the class
	 */
	private static final long serialVersionUID = 2253987075061995793L;

	/**
	 * Map the word to a tuple
	 * 
	 * @param the word
	 * 
	 * @return a tuple with word and count 1
	 */
	@Override
	public Tuple2<String, Integer> map(String word) throws Exception {
		return new Tuple2<String,Integer>(word,1);
	}
	
}
