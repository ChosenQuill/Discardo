package com.suredroid.discardo;

/**
 * An average bot should take around 50 turns, given the original bot tester parameters
 */
public class BotTester 
{
	public static final int TESTS_PER_BOT = 1_000_000;
	public static final int MAX_TURNS     = 4_000; //max turns allowed per game, for bad bots
	
//	public static void main(String[] args) {
//		System.out.println("running tests");
//		BotTester.test();
//	}
	
    public static double test(Player bot)
    {
        int numCards = 5;
        
        MultiGoal multi = new MultiGoal();
        
        int goal1 = (int)(Math.random() * 9);
        int goal2 = (int)(Math.random() * 9);
        
        while (goal1 == goal2) //ensure unique values for each AllSameGoal
        	goal2 = (int)(Math.random() * 9);
        
        multi.addGoal(new AllSameGoal(goal1));
        multi.addGoal(new AllSameGoal(goal2));
        multi.addGoal(new RunGoal());
        
        int totalTurns = 0;
        
        for (int i = 0; i < TESTS_PER_BOT; i++) {
        	totalTurns += play(bot, numCards, multi);
        }

        //average
        return ((double)totalTurns / TESTS_PER_BOT);
    }

    public static int play(Player p, int numCards, Goal goal)
    {
        int[] hand = new int[numCards];
        
        for (int i = 0; i < hand.length; i++)
            hand[i] = randomCard();

        p.init(hand, goal);

        int numTurns = 0;

        while (!goal.hasWon(hand)) 
        {
        	if (numTurns == MAX_TURNS)
        		return MAX_TURNS;
        	
            p.maybeReplaceCard(randomCard());
            numTurns++;
        }

        return numTurns;
    }

    public static int randomCard()
    {
        return (int)(Math.random() * 9) + 1;
    }
}
