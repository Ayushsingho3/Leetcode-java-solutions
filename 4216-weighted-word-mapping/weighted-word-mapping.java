class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            int sum = 0;
            for (char ch : word.toCharArray()) {
                sum += weights[ch - 'a'];
            }
            int rem = sum % 26;
            sb.append((char) ('z' - rem));
        }

        return sb.toString();
    }
}