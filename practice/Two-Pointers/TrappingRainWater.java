class HtrappingRainWater {
    public int trap(int[] height) {    
        if(height == null || height.length == 0){
            return 0;
        }
        
        int area = 0;
        int leftHighest = 0;
        int rightHighest = 0;
        int left = 0;
        int right = height.length - 1;
        
        while(left < right){
            // if left lower than right, area must be bounded by left height
            if(height[left] < height[right]){                
                if(height[left] >= leftHighest){    // cannot hold water cuz its a slope
                    leftHighest = height[left];
                }else{                              // can hold water cuz its a puddle!
                    area += leftHighest - height[left];
                }
                left++;
            }else{
                if(height[right] >= rightHighest){
                    rightHighest = height[right];
                }else{
                    area += rightHighest - height[right];
                }
                right--;
            }
        }
        return area;
    }
}