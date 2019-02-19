class Solution {
    public int numUniqueEmails(String[] emails) {
        // ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
        // output 2
        int numUniqueEmail = 0;
        Set<String> localAddrs = new HashSet<>();
        for (String email : emails) {            
            String[] fields = email.split("@");
            
            // If only "@", only local, or only domain part
            if(fields.length == 0) {
                localAddrs.add(email);
                numUniqueEmail++;
                continue;
            }   
            if(fields.length == 1) {
                continue;
            }
            
            String local = fields[0];
            String domain = fields[1];
            // Only valid before the first '+';
            String validLocal = local.split("\\+")[0];
            String[] realLocals = validLocal.split("\\.");
            StringBuilder parsedLocal = new StringBuilder();
            for(String part : realLocals) {
                parsedLocal.append(part);
            }
            String addr = parsedLocal.append(domain).toString();
            
            // Check uniqueness
            if(!localAddrs.contains(addr)){
                numUniqueEmail++;
                localAddrs.add(addr);                
            }
        }
        return numUniqueEmail;        
    }
}