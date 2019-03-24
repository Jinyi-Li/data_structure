/*
    Given a graph with nodes labelled from 0 to (n-1), find the celebrity. 

    A node is the celebrity if it knows no one and everyone else knows it!

    Solution: the definition of celebrity is the key to this probelm. I can assume 0 is the celebrity and test it with 1 to (n-1). If node i doesn't know 0 or 0 knows node i, 0 is not the celebrity! Then I can assume i is the cele and test with the rest of graph.    
*/
public class Solution extends Relation {
    public int findCelebrity(int n) {                
        if(n <= 0){
            return 0;
        }
        
        int cele = 0;
        for(int i = 1; i < n; i++){
            if(!knows(1, cele) || knows(cele, i)){
                cele = i;
            }
        }
        
        // need to validate this cele - because now cele could be a fake one, 
        // simply not being replaced yet as I've reached the end of array
        for(int i = 0; i < n; i++){
            if(i != cele){
                if(knows(cele, i) || !knows(i, cele)){
                    return -1;
                }
            }
        }
        
        return cele;
    }
}

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
