package com.suredroid;

import org.joor.Reflect;
import org.joor.ReflectException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
//		Pattern className = Pattern.compile("class (\\w+)");
//		Matcher m = className.matcher("class DiscardoBot implements java.til.function.Supplier<String> {\n" +
//				"public String get() {\n" +
//				"return \"Hello World!\";\n" +
//				"}\n" +
//				"\n" +
//				"}");
//		if(m.find()) {
//			System.out.println(m.groupCount());
//			System.out.println(m.group(1));
//		}
	}

}
