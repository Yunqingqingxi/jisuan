package test;

import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(HuffmanNode otherNode) {
        return this.frequency - otherNode.frequency;
    }
}

public class HuffmanCoding {

    public static HuffmanNode buildHuffmanTree(char[] characters, int[] frequencies) {
        PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();

        for (int i = 0; i < characters.length; i++) {
            minHeap.add(new HuffmanNode(characters[i], frequencies[i]));
        }

        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();

            HuffmanNode parent = new HuffmanNode('$', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            minHeap.add(parent);
        }

        return minHeap.poll();
    }

    public static void printHuffmanCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }

        if (root.data != '$') {
            System.out.println(root.data + ": " + code);
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    public static void main(String[] args) {
        char[] characters = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] frequencies = {5, 9, 12, 13, 16, 45};

        HuffmanNode root = buildHuffmanTree(characters, frequencies);

        System.out.println("Huffman编码：");
        printHuffmanCodes(root, "");
    }
}
