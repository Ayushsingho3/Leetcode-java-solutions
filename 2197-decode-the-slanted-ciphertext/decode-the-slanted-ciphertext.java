class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int len = encodedText.length();
        if (len == 0) {
            return "";
        }
        
        int cols = len / rows;
        StringBuilder sb = new StringBuilder();
        
        for (int startCol = 0; startCol < cols; startCol++) {
            for (int r = 0; r < rows; r++) {
                int c = startCol + r;
                if (c >= cols) {
                    break;
                }
                int idx = r * cols + c;
                sb.append(encodedText.charAt(idx));
            }
        }
        
        int end = sb.length() - 1;
        while (end >= 0 && sb.charAt(end) == ' ') {
            end--;
        }
        
        return sb.substring(0, end + 1);
    }
}