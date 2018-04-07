public class TrieNode {

    boolean isEnd;
    TrieNode[] links;
    final int alphabetSize = 26;

    TrieNode(boolean end) {
        isEnd = end;
        links = new TrieNode[alphabetSize];
    }
}
