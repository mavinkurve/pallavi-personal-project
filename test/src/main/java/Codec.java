import java.util.HashMap;
import java.util.Map;

public class Codec {

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int KEY_LENGTH = 10;
    private Map<String,String> urlToShortMap;
    private Map<String,String> shortToUrlMap;

    public Codec() {
        urlToShortMap = new HashMap<>();
        shortToUrlMap = new HashMap<>();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (urlToShortMap.containsKey(longUrl))
            return urlToShortMap.get(longUrl);
        String key = generateRandomString();
        while(!shortToUrlMap.containsKey(key))
            key = generateRandomString();
        shortToUrlMap.put(key,longUrl);
        urlToShortMap.put(longUrl,key);
        return key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortToUrlMap.containsKey(shortUrl))
            return shortToUrlMap.get(shortUrl);
        return null;
    }

    private String generateRandomString() {
        int count = KEY_LENGTH;
        StringBuilder builder = new StringBuilder();
        while (count != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
            count--;
        }
        return builder.toString();
    }
}