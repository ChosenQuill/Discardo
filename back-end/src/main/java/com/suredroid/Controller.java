package com.suredroid;

import com.google.gson.Gson;
import com.suredroid.compile.DynamicCompiler;
import com.suredroid.compile.DynamicCompilerException;
import com.suredroid.discardo.Test;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@CrossOrigin
@RestController
public class Controller {

    HashMap<String, CompletableFuture<ScoreInfo>> status = new HashMap<>();
    ArrayList<User> leaderBoard = new ArrayList<>();
    ExecutorService executorService = Executors.newCachedThreadPool();


    @RequestMapping("/submit")
    public ResponseEntity sumbit(@RequestParam(value = "name", defaultValue = "") String name, @RequestParam(value = "code", defaultValue = "") String code) {
        Pattern className = Pattern.compile("class (\\w+)");

        if (name.isEmpty() || code.isEmpty())
            return ResponseEntity.status(470).body("Requested params for query were not given. If this error is shown, something has gone terribly wrong.\nIf the error persists after Rereload, report this to support@suredroid.com and your teacher.");
        try {
            Matcher m = className.matcher(code);
            DynamicCompiler<Test> compiler = new DynamicCompiler<>();
            if (m.find()) {
                Test supplier = compiler.compile(null, m.group(1), code).getDeclaredConstructor().newInstance();
                status.put(name, runUser(name, supplier));
                return ResponseEntity.ok(supplier.doSomething());
            } else
                return ResponseEntity.badRequest().body("Missing Class");
        } catch (Exception e) {
            if (e instanceof InstantiationException || e instanceof IllegalAccessException || e instanceof DynamicCompilerException) {
                if (Main.debug) System.out.println(e.getLocalizedMessage());
                return ResponseEntity.badRequest().body(e.getLocalizedMessage());
            } else {
                if (Main.debug) System.out.println(e.getClass().getName());
                if (Main.debug) e.printStackTrace();
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Report the error below to support@suredroid.com and your teacher.\n" + errors.toString());
            }
        }
    }

    @RequestMapping("/")
    public ResponseEntity confirm() {
        return new <String>ResponseEntity("Yep, server is live. Status Ok", HttpStatus.OK);
    }

    @RequestMapping("/status")
    @ResponseBody
    public Prompt status(@RequestParam(value = "name", defaultValue = "") String name) {
        CompletableFuture<ScoreInfo> current = status.get(name);
        if (current != null) {
            ScoreInfo info = current.join();
            return Prompt.of("You are on the leaderboard!", "Congratulations, your bot did it!\nIt took an average of " + info.getAverage() + " turns for your bot to reach the goal!" + (!info.isBetter() ? "\nSadly, your score was not better than last time. Good luck next time!" : ""));
        } else {
            return Prompt.of("Status Not Found", "Your code was never submitted to the server. Report this to support@suredroid.com and your teacher.");
        }
    }

    private static Gson gson = new Gson();
    @RequestMapping("/leaderboard")
    public String getLeaderBoard() {
        return gson.toJson(leaderBoard);
    }

    public CompletableFuture<ScoreInfo> runUser(String name, Test code) {
        CompletableFuture<ScoreInfo> future = new CompletableFuture<>();
        executorService.submit(() -> {
//            Thread.sleep(2000);
            double score = (double) Math.round(Math.random() * 100 * 100) / 100;

            leaderBoard.stream().filter(u -> u.getName().equalsIgnoreCase(name)).findAny().ifPresentOrElse(user ->{
                boolean better = false;
                if(user.bestAverage > score){
                    user.bestAverage = score;
                    better = true;
                }
                future.complete(new ScoreInfo(user, score, better));
            },()->{
                User user = new User(name);
                user.setBAvj(score);
                leaderBoard.add(user);
                future.complete(new ScoreInfo(user, score, true));
            });
            Collections.sort(leaderBoard);
            return null;
        });
        return future;
    }
}

@Data
class ScoreInfo {
    private final User user;
    private final double average;
    private final boolean better;
}