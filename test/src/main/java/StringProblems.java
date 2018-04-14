import java.util.*;

public class StringProblems {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int currLength = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i =0; i < s.length();) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i),1);
                currLength++;
                i++;
                continue;
            }
            else {
                maxLength = Integer.max(maxLength,currLength);
                map = new HashMap<>();
                currLength = 0;
                for (int j = i-1; j >= 0; j--)
                    if (s.charAt(j) == s.charAt(i))
                    {
                        i = j + 1;
                        break;
                    }
            }
        }
        return Integer.max(maxLength,currLength);
    }


    public List<String> generateParenthesis(int n) {
        if (n <= 0)
            return null;
        if (n == 1) {
            return Arrays.asList("()");
        }
        else {
            HashSet<String> paranthesis = new HashSet<>();
            List<String> strings = generateParenthesis(n - 1);
            for (int i = 0;i < strings.size(); i++ )
            {
                for (int j = 0; j < strings.get(i).length(); j++) {
                    paranthesis.add(strings.get(i).substring(0,j) + "()"
                            + strings.get(i).substring(j,strings.get(i).length()));
                }
            }
            return new ArrayList(paranthesis);
        }
    }

    public String customSortString(String S, String T) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer,String> sortOrder = new HashMap<>();
        HashMap<Character,Integer> stringMap = new HashMap<>();

        for (int i = 0; i < T.length(); i++) {
            if (stringMap.containsKey(T.charAt(i)))
                stringMap.put(T.charAt(i), stringMap.get(T.charAt(i)) + 1);
            else
                stringMap.put(T.charAt(i), 1);
        }

        for (int j = 0; j < 26; j++) {
            sortOrder.put(j,"");
        }

        for (int j = 0; j < S.length(); j++) {
            sortOrder.put(S.charAt(j) - 'a',S.substring(0,j));
        }

        for(int a =0; a < 26; a++) {
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
        String lastWord = split[split.length-1];
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
            simplePath.insert(0,"/" + paths.pop());
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
}
