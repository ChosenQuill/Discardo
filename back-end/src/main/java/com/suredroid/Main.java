package com.suredroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.suredroid.botexample.TerribleBot;
import com.suredroid.discardo.BotTester;
import com.suredroid.discardo.Goal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Optional;

@SpringBootApplication
public class Main {
	public static boolean debug = false;
	public static void main(String[] args) {
		if(args.length > 0 && args[0].equals("debug"))
			debug = true;
		System.out.println(Goal.class.getPackage().toString());
		SpringApplication.run(Main.class, args);
	}

	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public static <T> Optional<T> getJson(String filename, T object){
		File file = new File("./" +  filename);
		if(file.exists()){
			try {
				FileReader fr = new FileReader(file);
				//noinspection unchecked
				return Optional.of((T) gson.fromJson(fr, object.getClass()));
			} catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}
		}
		return Optional.empty();
	}


	public static <T> void writeJson(String fileName, T object){
		try {
			FileWriter writer = new FileWriter( "./" + fileName);
			gson.toJson(object,writer);
			writer.close();
		} catch (IOException e1) {
			System.out.println("Cannot write to file " + fileName);
			e1.printStackTrace();
		}
	}
}
