/*
    Given a positive integer n, return minimum number of replacements needed for n to become 1.
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
        
        Queue<Long> toVisit = new LinkedList<>(); // current number n+-1 or n/2
        toVisit.offer(1L * n); // ensure every number in queue is Long.
        
        int numSteps = 0;
        Set<Long> visited = new HashSet<>(); // we don't want to revisit any number
        while(!toVisit.isEmpty()){
            int sizeOfLevel = toVisit.size();
            for(int i = 0; i < sizeOfLevel; i++){
                Long curr = toVisit.poll();
                if(curr == 1){
                    return numSteps; // found it
                }
                
                // check parity and note down new numbers to check
                if(curr % 2 == 0){z
                    long halfValue = curr / 2;
                    if(!visited.contains(halfValue)){
                        update(visited, toVisit, halfValue);
                    }
                }else{
                    long plusOne = curr + 1;
                    if(!visited.contains(plusOne)){
                        update(visited, toVisit, plusOne);
                    }
                    long minusOne = curr - 1;
                    if(!visited.contains(minusOne)){
                        update(visited, toVisit, minusOne);
                    }
                }
            }
            numSteps = numSteps + 1;
        }
        return -1;
    }

    private void update(Set<Long> visited, Queue<Long> toVisit, Long value) {
        visited.add(value);
        toVisit.offer(value);           
    }
}
