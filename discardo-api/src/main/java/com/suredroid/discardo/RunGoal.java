/*
 * Copyright (c) 2019 SureDroid. Published under the GNU General Public Use License. See LICENSE.MD for more information.
 */

package com.suredroid.discardo;

public class RunGoal implements Goal
{
	public boolean hasWon(int[] hand)
	{
		int min = getMin(hand); //linear scan for min/max
		int max = getMax(hand); //value in the array

		if (max - min + 1 != hand.length)  
			return false;

		boolean[] visited = new boolean[hand.length]; //false initially

		for (int i = 0; i < hand.length; i++)  
			if (visited[hand[i] - min] != false) 
				return false; 
			else
				visited[hand[i] - min] = true;  

		return true;

		//        int[] freqs = new int[10];
		//        
		//        for (int i = 0; i < hand.length; i++)
		//            freqs[hand[i]]++;
		//        
		//        //int count = 0;
		//        int low = 0;
		//        
		//        while (freqs[low] == 0)
		//            low++;
		//        
		//        for (int i = 1; i < hand.length; i++)
		//            if (low + i >= 10 || freqs[low + i] != 1)
		//                return false;
		//
		//        return true;
	}

	private int getMin(int[] hand) {
		int min = Integer.MAX_VALUE;
		for (int value : hand)
			if (value < min)
				min = value;
		return min;
	}

	private int getMax(int[] hand) {
		int max = Integer.MIN_VALUE;
		for (int value : hand)
			if (value > max)
				max = value;
		return max;
	}
}