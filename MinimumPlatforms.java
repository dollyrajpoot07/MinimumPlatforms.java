// Minimum Number of Platforms Required for a Railway/Bus Station

// Given the arrival and departure times of all trains that reach a railway station, the task is to find 
// the minimum number of platforms required for the railway station so that no train waits. We are given 
// two arrays that represent the arrival and departure times of trains that stop.
// Input: arr[] = {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}, dep[] = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00} 
// Output: 3 
// Explanation: There are at-most three trains at a time (time between 9:40 to 12:00)

// Input: arr[] = {9:00, 9:40}, dep[] = {9:10, 12:00} 
// Output: 1 
// Explanation: Only one platform is needed.

import java.util.*;

public class MinimumPlatforms {
    private static class TrainSchedule {
        int arrivalTime, deptTime;

        TrainSchedule(int arrivalTime, int deptTime) {
            this.arrivalTime = arrivalTime;
            this.deptTime = deptTime;
        }

        public String toString() {
            return "(" + this.arrivalTime + "," + this.deptTime + ")";
        }
    }

    private static class SortByArrival
            implements Comparator<TrainSchedule> {
        @Override
        public int compare(TrainSchedule o1, TrainSchedule o2) {
            return o1.arrivalTime - o2.arrivalTime;
        }
    }

    public static int countPlatforms(int[] arr, int[] dep) {
        TrainSchedule[] trains = new TrainSchedule[arr.length];
        for (int i = 0; i < arr.length; i++) {
            trains[i] = new TrainSchedule(arr[i], dep[i]);
        }
        Arrays.sort(trains, new SortByArrival());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(trains[0].deptTime);
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            TrainSchedule curr = trains[i];
            if (curr.arrivalTime <= pq.peek()) {
                count++;
            } else {
                pq.poll();
            }
            pq.add(curr.deptTime);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
        int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };
        int res = countPlatforms(arr, dep);
        System.out.println(res);
    }
}
