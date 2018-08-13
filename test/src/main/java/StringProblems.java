
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringProblems {

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            map.put(magazine.charAt(i), map.getOrDefault(magazine.charAt(i),0) + 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            int c = map.getOrDefault(ransomNote.charAt(i),0);
            if (c == 0)
                return false;
            c -= 1;
            map.put(ransomNote.charAt(i),c);
        }
        return true;
    }

    public boolean rotateString(String A, String B) {
        if (A.length() == 0 || B.length() == 0)
            return false;
        String rotated = A;
        do {
            String firstChar = String.valueOf(rotated.charAt(0));
            rotated = rotated.substring(1).concat(firstChar);
            if (rotated.equals(B))
                return true;
        }while (!rotated.equals(A));
        return false;
    }

    public String boldWords(String[] words, String S) {
        System.out.println(S);
        int[] isBold = new int[S.length()];
        Arrays.fill(isBold, 0);
        char[] chars = S.toCharArray();
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] == "wv")
                System.out.println(words[i]);
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == words[i].charAt(index)) {
                    index++;
                }
                else {
                    j = j - index;
                    index = 0;
                }
                if (index == words[i].length()) {
                    System.out.println(words[i] + " j: " + j + " index: " + index);
                    for (int k = j - index +1 ; k <= j; k++)
                        isBold[k] = 1;
                    index = 0;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean inLoop = false;
        for (int i = 0; i < chars.length; i++) {
            if (isBold[i] == 1 && !inLoop) {
                sb.append("<b>");
                inLoop = true;
            }
            if (inLoop && isBold[i] == 0) {
                sb.append("</b>");
                inLoop = false;
            }
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> frequencyMap = new HashMap<>();
        int max = 0;
        String current = "";
        String input = "";
        for (Character c : paragraph.toCharArray()) {
            if(Character.isLetterOrDigit(c) || c == ' ')
                input += c;
        }
        String[] words = input.split(" ");

        if (words.length == 0)
            return "";
        for (String word : words) {
            String lowercaseWord = word.toLowerCase();
            if (!bannedWords.contains(lowercaseWord)) {
                int f = frequencyMap.getOrDefault(lowercaseWord, 0);
                frequencyMap.put(lowercaseWord, ++f);
                if (max < f) {
                    max = f;
                    current = lowercaseWord;
                }
            }
        }
        return current;
    }

    public String longestPalindrome(String s) {
        boolean[][] memoTable = new boolean[s.length()][s.length()];
        String maxSequence = "";
        for (int i = 0; i < s.length(); i++){
            for (int j = i+1; j < s.length(); j++) {
                palindromeHelper(s,i,j,memoTable);
                if (memoTable[i][j]){
                    if ((j-i) > maxSequence.length())
                        maxSequence = s.substring(i,j);
                    }
            }
        }
        return maxSequence;
    }

    private boolean palindromeHelper(String s, int i, int j, boolean[][] memo) {
        memo[i][j] = false;
        if (i == j)
            memo[i][j] = true;
        if (memo[i+1][j-1] == true) {
            if (s.charAt(i) == s.charAt(j))
                memo[i][j] = true;
        }
        return memo[i][j];
    }

    public String getHint(String secret, String guess) {
        int a = 0;
        int b = 0;
        char[] g = guess.toCharArray();
        char[] s = secret.toCharArray();
        for (int i = 0; i < g.length; i++) {
            if (g[i] == s[i]) {
                a++;
                g[i] = 'X';
                s[i] = 'X';
            }
        }
        secret = new String(s);
        for (int i = 0; i < g.length; i++) {
            if (g[i] != 'X') {
                int index = secret.indexOf(g[i]);
                if (index > -1) {
                    s[index] = 'X';
                    b++;
                    secret = new String(s);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append("A");
        sb.append(b);
        sb.append("B");
        return sb.toString();
    }

    private List<Integer> getAllIndicesOf(char c, String s) {
        List<Integer> indices = new ArrayList<>();
        int index = s.indexOf(c);
        while(index != -1){
            indices.add(index);
            index = s.indexOf(c,index);
        }
        return indices;
    }

    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(0))
                return isSubsequence(s.substring(1), t.substring(i + 1));
        }
        return false;
    }

    public String replaceWords(List<String> dict, String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            List<String> roots = dict.stream().filter(root -> word.startsWith(root)).collect(Collectors.toList());
            if (roots.isEmpty())
                sb.append(" " + word);
            else {
                String shortestRoot = roots.get(0);
                for (String root : roots) {
                    if (root.length() < shortestRoot.length())
                        shortestRoot = root;
                }
                sb.append(" " + shortestRoot);
            }
        }
        return sb.toString().trim();
    }


    public int findMaxForm(String[] strs, int m, int n) {
        List<String> sorted = sortByLength(strs);
        int zeros = m;
        int ones = n;
        int formed = 0;
        for (int i = 0; i < sorted.size(); i++) {
            int[] onesAndZeros = getOnesAndZeros(sorted.get(i));
            if (zeros - onesAndZeros[0] >= 0 && ones - onesAndZeros[1] >= 0) {
                zeros -= onesAndZeros[0];
                ones -= onesAndZeros[1];
                formed++;
            }
        }
        return formed;
    }

    private List<String> sortByLength(String[] strs) {
        List<String> sorted = new ArrayList<>();
        TreeMap<Integer, List<String>> map = new TreeMap<>();
        for (int i = 0; i < strs.length; i++) {
            List<String> strings = new ArrayList<>();
            if (map.containsKey(strs[i].length())) {
                strings.addAll(map.get(strs[i].length()));
            }
            strings.add(strs[i]);
            map.put(strs[i].length(), strings);
        }
        map.entrySet().forEach(entry -> sorted.addAll(entry.getValue()));
        return sorted;
    }

    private int[] getOnesAndZeros(String s) {
        int zeros = 0;
        int ones = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') zeros++;
            else ones++;
        }
        return new int[]{zeros, ones};
    }

    public String shiftingLetters(String S, int[] shifts) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = shifts.length - 2; i >= 0; i--) {
            shifts[i] = (shifts[i + 1] + shifts[i]) % 26;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < shifts.length; i++) {
            int index = alphabet.indexOf(S.charAt(i));
            int shift = (index + shifts[i]) % 26;
            sb.append(alphabet.charAt(shift));
        }
        if (S.length() > shifts.length)
            sb.append(S.substring(shifts.length));
        return sb.toString();
    }

    public String reverseWords(String s) {
        List<String> words = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(words);
        return words.stream().collect(Collectors.joining(" "));
    }

    public List<Integer> shortestToChar(String S, char C) {
        List<Integer> distances = new ArrayList<>();
        char[] chars = S.toCharArray();
        List<Integer> locations = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == C)
                locations.add(i);
        }
        Collections.sort(locations);
        for (int i = 0; i < chars.length; i++) {
            int min = Math.abs(locations.get(0) - i);
            for (int j = 0; j < locations.size(); j++) {
                if (Math.abs(locations.get(j) - i) < min)
                    min = Math.abs(locations.get(j) - i);
            }
            distances.add(min);
        }
        int[] answer = new int[distances.size()];
        for (int i = 0; i < distances.size(); i++) {
            answer[i] = distances.get(i);
        }
        return distances;
    }

    public String toGoatLatin(String S) {
        List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        List<String> words = new ArrayList<>();
        String[] split = S.split(" ");
        for (int i = 0; i < split.length; i++) {
            StringBuilder sb = new StringBuilder();
            if (vowels.contains(split[i].charAt(0))) {
                sb.append(split[i]);
            } else {
                sb.append(split[i].substring(1, split[i].length()));
                sb.append(split[i].charAt(0));
            }
            sb.append("ma");
            for (int j = 0; j <= i; j++)
                sb.append("a");
            words.add(sb.toString());
        }
        return words.stream()
                .collect(Collectors.joining(" "));
    }

    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> answer = new ArrayList<>();
        int start = 0;
        int current = 0;
        while (current < S.length() - 1) {
            if (S.charAt(current) == S.charAt(current + 1)) {
                current++;
                continue;
            } else if (current - start >= 2) {
                answer.add(Arrays.asList(start, current));
            }
            start = current + 1;
            current++;
        }
        if (current - start >= 2) {
            answer.add(Arrays.asList(start, current));
        }
        return answer;
    }

    public boolean backspaceCompare(String S, String T) {
        return getString(S).equalsIgnoreCase(getString(T));
    }

    private String getString(String S) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                continue;
            }
            stack.push(S.charAt(i));
        }
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length())
            return false;
        // if A equals B, there should at least one dupe character to swap
        if (A == B) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i <= A.length(); i++) {
                if (set.contains(A.charAt(i)))
                    return true;
                set.add(A.charAt(i));
            }
            return false;
        }
        int diff = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i))
                diff++;
            if (diff > 2)
                return false;
        }
        return diff == 2;
    }

    public boolean isAnagram(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < sChars.length; i++) {
            map.put(sChars[i], map.getOrDefault(sChars[i], 0) + 1);
        }
        for (int i = 0; i < tChars.length; i++) {
            if (!map.containsKey(tChars[i]))
                return false;
            map.put(tChars[i], map.get(tChars[i]) - 1);
        }
        return !map.values().stream().anyMatch(v -> v > 0);
    }

    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>();
        for (int i = 0; i < bank.length; i++) {
            bankSet.add(bank[i]);
        }
        int mutations = 0;
        List<Integer> diff = new ArrayList<>();
        char[] startChar = start.toCharArray();
        char[] endChar = end.toCharArray();
        for (int i = 0; i < startChar.length; i++) {
            if (startChar[i] != endChar[i]) {
                char temp = startChar[i];
                startChar[i] = endChar[i];

            }
        }

        return mutations;
    }

    public List<Integer> partitionLabels(String S) {
        Stack<String> stack = new Stack<>();
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                StringBuilder popped = new StringBuilder();
                while (popped.toString().indexOf(chars[i]) == -1) {
                    popped.insert(0, stack.pop());
                }
                popped.append(chars[i]);
                stack.push(popped.toString());
            } else {
                stack.push(Character.toString(chars[i]));
            }
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        List<Integer> partitionLengths = new ArrayList<>();
        while (!stack.isEmpty()) {
            String s = stack.pop();
            partitionLengths.add(s.length());
        }
        Collections.reverse(partitionLengths);
        return partitionLengths;
    }

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 26) {
            int temp = n / 26;
            sb.append((char) ('A' + (temp - 1)));
            n = n % 26;
        }
        sb.insert(0, (char) ('A' + (n - 1)));

        return sb.toString();
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int start = 0;
        int end = s.length() - 1;
        while (end > start) {
            if (!Character.isLetter(s.charAt(start)))
                start++;
            while (!Character.isLetter(s.charAt(end)))
                end--;
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    public String multiply(String num1, String num2) {
        if (num1.equalsIgnoreCase("0") || num2.equalsIgnoreCase("0"))
            return "0";
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        StringBuilder total = new StringBuilder();
        int count = 0;
        for (int i = s2.length - 1; i >= 0; i--) {
            StringBuilder product = multiplyChars(s1, s2[i]);
            for (int z = 1; z <= count; z++)
                product.append('0');
            count++;
            total = addChars(total, product);
        }
        return total.toString();
    }

    private StringBuilder multiplyChars(char[] chars, char c) {
        StringBuilder product = new StringBuilder();
        if (c == '0')
            return product.append(0);
        int carry = 0;
        for (int j = chars.length - 1; j >= 0; j--) {
            int temp = (c - '0') * (chars[j] - '0') + carry;
            if (temp > 9) {
                carry = temp / 10;
                temp = temp % 10;
            } else
                carry = 0;
            product.insert(0, temp);
        }
        if (carry > 0)
            product.insert(0, carry);
        return product;
    }

    private StringBuilder addChars(StringBuilder s1, StringBuilder s2) {
        StringBuilder sum = new StringBuilder();
        int diff = Math.abs(s1.length() - s2.length());
        if (s1.length() < s2.length()) {
            for (int d = 0; d < diff; d++) {
                s1.insert(0, 0);
            }
        }
        if (s2.length() < s1.length()) {
            for (int d = 0; d < diff; d++) {
                s2.insert(0, 0);
            }
        }
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int temp = (s1.charAt(i) - '0') + (s2.charAt(j) - '0') + carry;
            if (temp > 9) {
                temp = temp % 10;
                carry = 1;
            } else
                carry = 0;
            sum.insert(0, temp);
            i--;
            j--;
        }
        if (carry > 0)
            sum.insert(0, carry);
        return sum;
    }

    public int romanToInt(String s) {
        if (s == null || s.length() == 0)
            return 0;
        s = s.toUpperCase();
        Map<String, Integer> values = new HashMap<>();
        values.put("IV", 4);
        values.put("IX", 9);
        values.put("XL", 40);
        values.put("XC", 90);
        values.put("CD", 400);
        values.put("CM", 900);
        values.put("I", 1);
        values.put("V", 5);
        values.put("X", 10);
        values.put("L", 50);
        values.put("C", 100);
        values.put("D", 500);
        values.put("M", 1000);

        List<String> digits = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ((i + 2) <= s.length() && values.containsKey(s.substring(i, i + 2))) {
                digits.add(s.substring(i, i + 2));
                i++;
                continue;
            }
            digits.add(s.substring(i, i + 1));
        }
        int answer = 0;
        for (String d : digits) {
            answer += values.get(d);
        }
        return answer;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        if (matrix.length == 0)
            return spiral;
        int n = matrix.length;
        int m = matrix[0].length;
        int rowStart = 0, rowEnd = n - 1;
        int colStart = 1, colEnd = m - 1;

        //loop till all elements printed
        while (rowStart <= rowEnd && colStart <= colEnd) {
            spiral.addAll(getRow(matrix, rowStart, rowStart, rowEnd));
            spiral.addAll(getCol(matrix, colEnd, colStart, colEnd));
            spiral.addAll(getRow(matrix, rowEnd, rowEnd - 1, rowStart));
            spiral.addAll(getCol(matrix, colStart - 1, colEnd - 1, colStart));

            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
        return spiral.subList(0, (n * m));
    }

    private List<Integer> getRow(int[][] matrix, int rowIndex, int start, int end) {
        List<Integer> row = new ArrayList<>();
        if (start < 0 || end > matrix[0].length)
            return row;
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                row.add(matrix[rowIndex][i]);
            }
        } else {
            for (int i = start; i >= end; i--) {
                row.add(matrix[rowIndex][i]);
            }
        }
        return row;
    }

    private List<Integer> getCol(int[][] matrix, int colIndex, int start, int end) {
        List<Integer> col = new ArrayList<>();
        if (start < 0 || end > matrix.length)
            return col;
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                col.add(matrix[i][colIndex]);
            }
        } else {
            for (int i = start; i >= end; i--) {
                col.add(matrix[i][colIndex]);
            }
        }
        return col;
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty())
            return null;
        String[] letters = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        char[][] permuteArray = new char[digits.length()][4];
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            permuteArray[i] = letters[digits.charAt(i) - '0'].toCharArray();
        }
        answer = getPermutations(permuteArray, answer, 0);
        return answer;
    }

    public List<String> getPermutations(char[][] array, List<String> partial, int index) {
        if (index == array.length - 1) {
            for (int i = 0; i < array[index].length; i++) {
                partial.add(String.valueOf(array[index][i]));
            }
            return partial;
        } else {
            partial = getPermutations(array, partial, index + 1);
            List<String> newPermutes = new ArrayList<>();
            for (int i = 0; i < array[index].length; i++) {
                for (String p : partial) {
                    newPermutes.add(array[index][i] + p);
                }
            }
            partial.clear();
            partial.addAll(newPermutes);
        }
        return partial;
    }

    public String reverseVowels(String s) {

        if (s.length() <= 1)
            return s;
        HashSet<Character> vowels = new HashSet<>();
        vowels.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        char[] chars = s.toCharArray();

        for (int start = 0, end = chars.length - 1; start < end; ) {
            if (!vowels.contains(chars[start]))
                start++;
            if (!vowels.contains(chars[end]))
                end--;
            if (vowels.contains(chars[start]) && vowels.contains(chars[end])) {
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
                start++;
                end--;
            }
        }
        return new String(chars);
    }

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.containsKey(s.charAt(i)) ? map.get(s.charAt(i)) + 1 : 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] answer = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            List<Integer> locations = new ArrayList<>();
            locations.add(i);
            if (map.containsKey(B[i])) {
                locations.addAll(map.get(B[i]));
            }
            map.put(B[i], locations);
        }

        for (int i = 0; i < A.length; i++) {
            List<Integer> locations = map.get(A[i]);
            answer[i] = locations.remove(0);
            if (locations.size() > 0)
                map.put(A[i], locations);
        }

        return answer;
    }

    public List<String> fizzBuzz(int n) {
        List<String> fizzbuzz = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if ((i % 3) == 0)
                sb.append("Fizz");
            if ((i % 5) == 0)
                sb.append("Buzz");
            if (sb.length() == 0)
                sb.append(i);
            fizzbuzz.add(sb.toString());
        }
        return fizzbuzz;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> maps = new HashMap<>();
        List<String> sortedStrs = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = new char[strs[i].length()];
            for (int j = 0; j < strs[i].length(); j++) {
                chars[j] = strs[i].charAt(j);
            }
            Arrays.sort(chars);
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < chars.length; k++) {
                sb.append(chars[k]);
            }
            sortedStrs.add(sb.toString());
        }

        for (int i = 0; i < strs.length; i++) {
            List<String> strings = new ArrayList<>();
            strings.add(strs[i]);
            if (maps.containsKey(sortedStrs.get(i))) {
                strings.addAll(maps.get(sortedStrs.get(i)));
            }
            maps.put(sortedStrs.get(i), strings);
        }
        List<List<String>> values = new ArrayList<>();
        maps.values().forEach(v -> values.add(v));
        return values;
    }

    public int compareVersion(String version1, String version2) {
        StringBuilder sb = new StringBuilder();
        List<Integer> parts1 = new ArrayList<>();
        for (int i = 0; i < version1.length(); i++) {
            if (version1.charAt(i) != '.') {
                sb.append(version1.charAt(i));
            } else {
                parts1.add(Integer.parseInt(sb.toString()));
                sb.delete(0, sb.length());
            }
        }
        if (sb.length() > 0)
            parts1.add(Integer.parseInt(sb.toString()));
        List<Integer> parts2 = new ArrayList<>();
        sb.delete(0, sb.length());
        for (int i = 0; i < version2.length(); i++) {
            if (version2.charAt(i) != '.') {
                sb.append(version2.charAt(i));
            } else {
                parts2.add(Integer.parseInt(sb.toString()));
                sb.delete(0, sb.length());
            }
        }
        if (sb.length() > 0)
            parts2.add(Integer.parseInt(sb.toString()));
        if (parts1.size() < parts2.size()) {
            for (int i = parts1.size(); i < parts2.size(); i++)
                parts1.add(0);
        }
        if (parts1.size() > parts2.size()) {
            for (int i = parts2.size(); i < parts1.size(); i++)
                parts2.add(0);
        }
        for (int i = 0; i < parts1.size(); i++) {
            if (parts1.get(i) > parts2.get(i))
                return 1;
            if (parts1.get(i) < parts2.get(i))
                return -1;
        }
        return 0;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int currLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); ) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
                currLength++;
                i++;
                continue;
            } else {
                maxLength = Integer.max(maxLength, currLength);
                map = new HashMap<>();
                currLength = 0;
                for (int j = i - 1; j >= 0; j--)
                    if (s.charAt(j) == s.charAt(i)) {
                        i = j + 1;
                        break;
                    }
            }
        }
        return Integer.max(maxLength, currLength);
    }


    public List<String> generateParenthesis(int n) {
        if (n <= 0)
            return null;
        if (n == 1) {
            return Arrays.asList("()");
        } else {
            HashSet<String> paranthesis = new HashSet<>();
            List<String> strings = generateParenthesis(n - 1);
            for (int i = 0; i < strings.size(); i++) {
                for (int j = 0; j < strings.get(i).length(); j++) {
                    paranthesis.add(strings.get(i).substring(0, j) + "()"
                            + strings.get(i).substring(j, strings.get(i).length()));
                }
            }
            return new ArrayList(paranthesis);
        }
    }

    public String customSortString(String S, String T) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, String> sortOrder = new HashMap<>();
        HashMap<Character, Integer> stringMap = new HashMap<>();

        for (int i = 0; i < T.length(); i++) {
            if (stringMap.containsKey(T.charAt(i)))
                stringMap.put(T.charAt(i), stringMap.get(T.charAt(i)) + 1);
            else
                stringMap.put(T.charAt(i), 1);
        }

        for (int j = 0; j < 26; j++) {
            sortOrder.put(j, "");
        }

        for (int j = 0; j < S.length(); j++) {
            sortOrder.put(S.charAt(j) - 'a', S.substring(0, j));
        }

        for (int a = 0; a < 26; a++) {
            for (Integer k : sortOrder.keySet()) {
                if (sortOrder.get(k).length() == a) {
                    for (Map.Entry<Character, Integer> entry : stringMap.entrySet()) {
                        if ((entry.getKey() - 'a') == k) {
                            for (Integer v = 0; v < entry.getValue(); v++) {
                                sb.append(entry.getKey());
                            }
                        }
                    }
                }
            }
        }

        return sb.toString();
    }

    public int lengthOfLastWord(String s) {
        String[] split = s.split(" ");
        if (split.length <= 1)
            return 0;
        String lastWord = split[split.length - 1];
        return lastWord.length();
    }

    public String simplifyPath(String path) {
        Stack<String> paths = new Stack<>();
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.isEmpty() || part.equalsIgnoreCase("."))
                continue;
            if (part.equalsIgnoreCase("..")) {
                if (!paths.isEmpty())
                    paths.pop();
                continue;
            }
            paths.push(part);
        }
        StringBuilder simplePath = new StringBuilder();
        while (!paths.isEmpty()) {
            simplePath.insert(0, "/" + paths.pop());
        }
        if (simplePath.toString().isEmpty())
            return "/";
        return simplePath.toString();

    }

    public String longestCommonPrefix(String[] strings) {
        if (strings == null)
            throw new IllegalArgumentException("strings");
        if (strings.length == 0)
            return "";
        if (strings.length == 1)
            return strings[0];
        String prefix = "";
        for (int i = 0; i < strings[0].length(); i++) {
            char c = strings[0].charAt(i);
            for (int j = 1; j < strings.length; j++) {
                if (strings[j].length() == i || strings[j].charAt(i) != c)
                    return strings[0].substring(0, i);
            }
            prefix += c;
        }
        return prefix;
    }

    public boolean isValidParanthesis(String s) {

        HashMap<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                case '}':
                case ']':
                    if (stack.isEmpty())
                        return false;
                    Character d = stack.pop();
                    if (!c.equals(pairs.get(d)))
                        return false;
                    break;
                default:
                    return false;
            }
        }
        return (stack.isEmpty());
    }

    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;

        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = 2;
        a[2] = 2;
        int head = 2, tail = 3, num = 1, result = 1;

        while (tail < n) {
            for (int i = 0; i < a[head]; i++) {
                a[tail] = num;
                if (num == 1 && tail < n) result++;
                tail++;
            }
            num = num ^ 3;
            head++;
        }

        return result;
    }
}