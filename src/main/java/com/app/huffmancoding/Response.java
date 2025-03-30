
package com.app.huffmancoding;

import java.util.Map;

public class Response {
	private Node tree;
	private String bitString;
	private Map<Character, String> dictionary;

	public Response(Node tree, String bitString, Map<Character, String> dictionary) {
		super();
		this.tree = tree;
		this.bitString = bitString;
		this.dictionary = dictionary;
	}
	public Node getTree() {
		return tree;
	}
	public void setTree(Node tree) {
		this.tree = tree;
	}
	public String getBitString() {
		return bitString;
	}
	public void setBitString(String bitString) {
		this.bitString = bitString;
	}
	public Map<Character, String> getDictionary() {
		return dictionary;
	}
	public void setDictionary(Map<Character, String> dictionary) {
		this.dictionary = dictionary;
	}
}
