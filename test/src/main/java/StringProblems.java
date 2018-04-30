import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringProblems {

    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        int[] answer = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            List<Integer> locations = new ArrayList<>();
            locations.add(i);
            if (map.containsKey(B[i])) {
                locations.addAll(map.get(B[i]));
            }
            map.put(B[i],locations);
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
            }
            else {
                parts1.add(Integer.parseInt(sb.toString()));
                sb.delete(0,sb.length());
            }
        }
        if (sb.length() > 0)
            parts1.add(Integer.parseInt(sb.toString()));
        List<Integer> parts2 = new ArrayList<>();
        sb.delete(0,sb.length());
        for (int i = 0; i < version2.length(); i++) {
            if (version2.charAt(i) != '.') {
                sb.append(version2.charAt(i));
            }
            else {
                parts2.add(Integer.parseInt(sb.toString()));
                sb.delete(0,sb.length());
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

    public static String longestCommonPrefix(String[] strings) {
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
