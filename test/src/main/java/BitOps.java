public class BitOps {

    public String getBits(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
         sb.append(n & 1);
         n = n >> 1;
        }
        return sb.toString();
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
