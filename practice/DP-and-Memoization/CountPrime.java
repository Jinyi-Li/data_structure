class Solution {
    public int countPrimes(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 0;
        }
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        
        // find all non-prime numbers
        for(int i = 2; i * i < n; i++){
            if(!isPrime[i]){
                continue;
            }
            for(int j = 2; i * j < n; j++){
                isPrime[i * j] = false;
            }
        }
        
        // the rest are primes
        int count = 0;
        for(int i = 2; i < n; i++){
            if(isPrime[i]){
                count++;
            }
        }
        return count;
    }
}
