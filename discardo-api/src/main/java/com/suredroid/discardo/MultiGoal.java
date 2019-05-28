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
    
    /** get the all the goals for this multi goal, used for getting the goal value of an AllSame goal */
    public List<Goal> getGoals() { return this.goals; } //used for better bot, not part of original lab
}