class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int arriveAliceDays = convertToDays(arriveAlice, days);
        int leaveAliceDays = convertToDays(leaveAlice, days);
        int arriveBobDays = convertToDays(arriveBob, days);
        int leaveBobDays = convertToDays(leaveBob, days);

        int start = Math.max(arriveAliceDays, arriveBobDays);
        int end = Math.min(leaveAliceDays, leaveBobDays);

        return Math.max(0, end - start + 1);
    }

    private int convertToDays(String date, int[] days) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += days[i];
        }
        return totalDays;
    }
}