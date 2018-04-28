import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BitOps {
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        List<String> bits = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            bits.add(getBits(nums[i]));
        }
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                sum += hammingDistance(bits.get(i).toCharArray(),bits.get(j).toCharArray());
            }
        }
        return sum;
    }

    public int hammingDistance(char[] i, char[] j){
        int d = 0;
        for (int k = 0; k< i.length; k++) {
            if (i[k] != j[k])
                d++;
        }
        return d;
    }

    public String getBits(int n) {
        String binString = Integer.toBinaryString(n);
        int length = 64 - binString.length();
        char[] padArray = new char[length];
        Arrays.fill(padArray, '0');
        String padString = new String(padArray);
        binString = padString + binString;
        return binString;
    }

    public boolean hasAlternatingBits(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n & 1);
            n = n >> 1;
        }
        for (int i = 0; i < sb.toString().length()-1; i++)
        {
            if (sb.toString().charAt(i) == sb.toString().charAt(i +1))
                return false;
        }
        return true;
    }
}
