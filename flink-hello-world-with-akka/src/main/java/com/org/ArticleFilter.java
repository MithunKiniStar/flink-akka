package com.org;

import org.apache.flink.api.common.functions.FilterFunction;

/**
 * Class to filter out articles
 * 
 * @author mans2singh
 *
 */
public class ArticleFilter implements FilterFunction<String> {

	/**
	 * Serial version for the class
	 */
	private static final long serialVersionUID = 1289375655225327623L;

	/**
	 * Filters articles
	 * 
	 * @param - word
	 * @return <code>true</code> if article and <code>false</code> otherwise
	 */
	@Override
	public boolean filter(String word) throws Exception {
		if ( word != null && ! (word.equals("a")) && ! (word.equals("an")) && !(word.equals("the")))
			return true;
		else 
			return false;
	}

}
