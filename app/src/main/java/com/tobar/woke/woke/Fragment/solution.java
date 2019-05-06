//package com.tobar.woke.woke.Fragment;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class solution {
//    int a;
//
//
//    public int solution(int[] a) {
//        // write your code in Java SE 8
//
//        System.out.println(this.a);
//
//
//        int root = A[0];
//        int currentLevel = 1;
//        int currentNode = A[1];
//        int level2 = 0;
//        int nLevels = 1;
//
//        ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
//        levels.add(new ArrayList<Integer>(root));
//
//        for(int i = 1; i < A.length; i++) {
//
//
//            if(isAtLevel(currentLevel, A, currentNode)) {
//
//                levels.get(i - 1).add(A[i]);
//                currentNode = A[i + 1];
//
//
//            } else {
//
//                levels.add(new ArrayList<Integer>(currentNode));
//                currentNode = A[i];
//
//            }
//
//
//
//        }
//
//        int greatestSum = 0;
//
//        int winningLevel = 0;
//
//        int currValue = 0;
//
//
//        for(int n = 0; n < levels.size(); n++) {
//            for(int t = 0; t < levels.get(n).size(); t++) {
//
//                ArrayList<Integer> curr = levels.get(n);
//
//
//                currValue = currValue + curr.get(t);
//
//                if(currValue > greatestSum) {
//
//                    winningLevel = n + 1;
//
//                } else {
//
//                    currValue = 0;
//
//                }
//
//
//
//
//            }
//
//
//
//
//
//
//        }
//
//        return winningLevel;
//
//
//
//
//
//
//
//
//    }
//
//
//    public boolean isAtLevel(int level, int[] ar, int a) {
//
//        int root = ar[0];
//
//
//        List<int[]> ar1 = Arrays.asList(ar);
//
//        int index = ar1.indexOf(a);
//
//        return ((Math.abs(index - root)) == (level - 1));
//
//
//
//    }
//
//    public boolean isChildren(int node) {
//       return ((2 * node) && (2 * k) + 1);
//
//    }
//
//    public static void main(String[] args) {
//
//        solution s = new solution();
//
//        System.out.println(a);
//
//    }
//
//
//}
