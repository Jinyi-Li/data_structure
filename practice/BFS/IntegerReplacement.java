/*
    Given a positibe integer n, return minimum number of replacements needed for n to become 1.
    Rule 1: if n is even, replace n with (n / 2)
    Rule 2: if n is odd, replace n with either (n + 1) or (n - 1)

    For example,
    input = 8
    output = 3
    8 -> 4 -> 2 -> 1

    input = 7
    output = 4
    7 -> 8 -> 4 -> 2 -> 1
    or 7 -> 6 -> 3 -> 2 -> 1

    Solution: to find the shortest distance from n to 1, use BFS. 
    REMEMBER TO HANDLE INTEGER OVERFLOW.
*/
class Solution {
    public int integerReplacement(int n) {
        if(n == 1){
            return 0;
        }
        
        int numSteps = 0;        
        Queue<Long> toVisit = new LinkedList<>();
        toVisit.offer(1L * n);
        Set<Long> visited = new HashSet<>();
        
        while(!toVisit.isEmpty()){
            int sizeOfLevel = toVisit.size();
            for(int i = 0; i < sizeOfLevel; i++){
                Long curr = toVisit.poll();
                if(curr == 1){
                    return numSteps;
                }
                
                // check parity and offer new possibilities
                if(curr % 2 == 0){
                    if(!visited.contains(curr / 2)){
                        visited.add(curr / 2);
                        toVisit.offer(curr / 2);                        
                    }
                }else{
                    if(!visited.contains(curr + 1)){
                        visited.add(curr + 1);
                        toVisit.offer(curr + 1);
                    }
                    if(!visited.contains(curr - 1)){
                        visited.add(curr - 1);
                        toVisit.offer(curr - 1);
                    }
                }
            }
            numSteps = numSteps + 1;
        }
        return -1;
    }
}
