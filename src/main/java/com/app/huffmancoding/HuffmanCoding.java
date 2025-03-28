package com.app.huffmancoding;

import com.almasb.fxgl.core.collection.Array;
import com.sun.source.tree.Tree;

import java.util.*;


public class HuffmanCoding {

	public Response encode(char[] input) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		List<CharacterCode>dictionary = new ArrayList<CharacterCode>();
		for (int i = 0; i < input.length; i++)
			if (map.containsKey(input[i]))
				map.put(input[i], map.get(input[i]) + 1);
			else
				map.put(input[i], 1);
		Node tree = buildTree(map);
		buildDictionary(dictionary, tree, "");
		return new Response(tree, buildBitString(dictionary, input), dictionary);
	}

	public String decode(String bitString, Map<String, Character> dictionary) {
		StringBuilder decodedText = new StringBuilder();
		StringBuilder prefix = new StringBuilder();
		for (int i = 0; i < bitString.length(); i++) {
            prefix.append(bitString.charAt(i)); // Agregamos el bit actual al prefijo
            if (dictionary.containsKey(prefix.toString())) {
                decodedText.append(dictionary.get(prefix.toString())); // Decodificamos el carácter
                prefix.setLength(0); // Reiniciamos el prefijo
            }
        }
		return decodedText.toString();
	}

	public String decode(String bitString,Node root) {
		StringBuilder decodedMessage = new StringBuilder();
		Node current = root;
		for (int i = 0; i < bitString.length(); i++) {
			char c = bitString.charAt(i);
			if (c == '0') {
				current = current.getLeft();
			} else if (c == '1') {
				current = current.getRight();
			}
			if (current.getLeft() == null && current.getRight() == null) {
				decodedMessage.append(current.getCharacter());
				current = root;
			}
		}
		return decodedMessage.toString();
	}

	private String buildBitString(List<CharacterCode> dictionary, char[] input) {
		StringBuilder bitString = new StringBuilder();
		for (char c : input) {
			// Buscar el código correspondiente al carácter en la lista
			for (CharacterCode entry : dictionary) {
				if (entry.getCharacter() == c) {
					String code = entry.getCode();
					// Convertir cada '0' y '1' a bits (0 y 1 como enteros)
					for (int j = 0; j < code.length(); j++) {
						bitString.append(code.charAt(j) == '0' ? 0 : 1);
					}
					break; // Salir del bucle una vez encontrado el carácter
				}
			}
		}
		return bitString.toString();
	}
	
	private Node buildTree(Map<Character, Integer> map) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Iterator<Character> it = map.keySet().iterator();
		Node node = null;
		while (it.hasNext()) {
			char key = (char) it.next();
			int ocurrences = (int) map.get(key); // se puede evitar casteo? revisar
			node = new Node(key, ocurrences);
			queue.offer(node);
		}
		Node root = queue.peek();
		while (queue.size() > 1) {
			Node subTree1 = queue.poll();
			Node subTree2 = queue.poll();
			root = new Node('\0', subTree1.getFrequency() + subTree2.getFrequency(), subTree1, subTree2);
			queue.offer(root);
		}
		return root;
	}

	private void buildDictionary(List<CharacterCode> dictionary, Node tree, String path) {
		if (!tree.isLeafNode()) {
			buildDictionary(dictionary, tree.getLeft(), path + "0");
			buildDictionary(dictionary, tree.getRight(), path + "1");
		} else if (path.isEmpty())
			dictionary.add(new CharacterCode(tree.getCharacter(), "1"));
		else
			dictionary.add(new CharacterCode(tree.getCharacter(), path));
	}
}
