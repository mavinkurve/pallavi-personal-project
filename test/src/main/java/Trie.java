class Trie {

    TrieNode root;
    /**
     * Initialize your data structure here.
     */
    public Trie() {
        TrieNode root = new TrieNode(false);
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.links[word.charAt(i) - 'a'] == null) {
                current.links[word.charAt(i) - 'a'] = new TrieNode(false);
            }
            current = current.links[word.charAt(i) - 'a'];
        }
        current.isEnd = true;

    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return false;
    }
}
