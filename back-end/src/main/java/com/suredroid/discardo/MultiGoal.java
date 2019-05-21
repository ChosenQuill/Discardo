package com.suredroid.discardo;
import java.util.*;

public class MultiGoal implements Goal 
{
    private List<Goal> goals;
    
    public MultiGoal()
    {
    	this.goals = new ArrayList<>();
    }
    
    public void addGoal(Goal goal) {
    	this.goals.add(goal);
    }
    
    public boolean hasWon(int[] hand)
    {
    	for (Goal goal : this.goals)
    		if (goal.hasWon(hand))
    			return true;
    	
    	return false;
    }

    
    public static void main(String[] args) {
        MultiGoal mg = new MultiGoal();
        mg.addGoal(new RunGoal());
        mg.addGoal(new AllSameGoal(9));
        int[] hand1 = {4, 5, 6, 7};
        System.out.println(mg.hasWon(hand1));  //true
        int[] hand2 = {9, 9, 9};
        System.out.println(mg.hasWon(hand2));  //true
        int[] hand3 = {5, 5, 5};
        System.out.println(mg.hasWon(hand3));  //false
	}
    
    /** get the all the goals for this multi goal, used for getting the goal value of an AllSame goal */
    public List<Goal> getGoals() { return this.goals; } //used for better bot, not part of original lab
}