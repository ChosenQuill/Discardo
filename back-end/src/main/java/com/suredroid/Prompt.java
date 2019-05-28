package com.suredroid;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class Prompt {
    private static Gson gson = new Gson();
    private final String title, text;
    public static Prompt of(String title, String text){
        return new Prompt(title, text);
    }
    @Override
    public String toString(){
        return gson.toJson(this);
    }
}
