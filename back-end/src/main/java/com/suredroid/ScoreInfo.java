package com.suredroid;


import lombok.Data;

@Data
class ScoreInfo {
    private final User user;
    private final double average;
    private final boolean better;
}