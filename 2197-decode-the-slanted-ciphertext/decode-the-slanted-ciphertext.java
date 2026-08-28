class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder result = new StringBuilder();
        
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows; r++) {
                if (r + c < cols) {
                    result.append(encodedText.charAt(r * cols + r + c));
                }
            }
        }
        
        int i = result.length() - 1;
        while (i >= 0 && result.charAt(i) == ' ') {
            i--;
        }
        
        return result.substring(0, i + 1);
    }
}