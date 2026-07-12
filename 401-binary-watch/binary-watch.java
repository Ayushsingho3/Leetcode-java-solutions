import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> times = new ArrayList<>();
        
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    times.add(String.format("%d:%02d", h, m));
                }
            }
        }
        
        return times;
    }
}