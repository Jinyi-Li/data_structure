/*
    1. Use a randomly generated fixed-length string as the tinyUrl.
    2. Why not hashing? 
        Collision. Urls tend to have heavy patterns, so collision is more likely to happen.
        Randomized strings, instead, can mitigate the collision issue easily.
    3. How to improve scalability?
        Horizontal sharding. Hash the original url to a 2-digit code -> map to a machine.
        Consistent hashing. Map a range of hash code to a machine.
    4. How to improve throughput?
        Based on memory hierarchy, use a key-value in-memory database (redis/memcached).
    
*/
public class Codec {
    
    // use [0-9a-zA-Z] as the alphabet
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final String BASE_URL = "http://tinyurl.com/";
    private final int LENGTH = 6;
    
    private Map<String, String> shortLongMap = new HashMap<>();    
    private Random rand = new Random();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl = getShortUrl();
        shortLongMap.put(shortUrl, longUrl);
        return BASE_URL + shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {        
        return shortLongMap.get(shortUrl.substring(BASE_URL.length()));
    }
    
    private String getShortUrl(){
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < LENGTH; i++){
            res.append(ALPHABET.charAt(rand.nextInt(62)));
        }
        return res.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));