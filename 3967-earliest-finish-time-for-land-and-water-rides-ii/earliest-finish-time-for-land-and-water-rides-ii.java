class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minLandEnd = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            minLandEnd = Math.min(minLandEnd, landStartTime[i] + landDuration[i]);
        }
        
        int minWaterEnd = Integer.MAX_VALUE;
        for (int j = 0; j < waterStartTime.length; j++) {
            minWaterEnd = Math.min(minWaterEnd, waterStartTime[j] + waterDuration[j]);
        }
        
        int minTime = Integer.MAX_VALUE;
        
        for (int j = 0; j < waterStartTime.length; j++) {
            int waterEnd = waterStartTime[j] + waterDuration[j];
            minTime = Math.min(minTime, Math.max(minLandEnd + waterDuration[j], waterEnd));
        }
        
        for (int i = 0; i < landStartTime.length; i++) {
            int landEnd = landStartTime[i] + landDuration[i];
            minTime = Math.min(minTime, Math.max(minWaterEnd + landDuration[i], landEnd));
        }
        
        return minTime;
    }
}