package com.app.huffmancoding;

import java.util.List;
import java.util.Map;

public class Response {
	private Node tree;
	private String bitString;
	private List<CharacterCode> dictionary;

	public Response(Node tree, String bitString, List<CharacterCode> dictionary) {
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

	public List<CharacterCode> getDictionary() {
		return dictionary;
	}
}
