package com.suredroid;


public class User implements Comparable<User> {
    private final String name;
    double bestAverage;
    int tries = 0;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBAvj(double avj){
        bestAverage = avj;
        tries++;
    }

    @Override
    public int compareTo(User o) {
        return (int) Math.round(bestAverage-o.bestAverage);
    }
}
