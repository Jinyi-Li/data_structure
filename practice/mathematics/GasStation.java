class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if(gas.length == 0){
            return 0;
        }
        if(gas.length == 1){
            return gas[0] >= cost[0] ? 0 : -1;
        }
        
        int size = gas.length;
        
        for(int i = 0; i < size; i++){
            int sum = 0;            
            if(gas[i] < cost[i]){
                continue;
            }
            for(int j = 0; j < size; j++){
                sum = sum + gas[(i + j) % size] - cost[(i + j) % size];
                if(sum < 0){
                    break;
                }
            }
            if(sum >= 0){
                return i;
            }
        }
        return -1;
    }
}