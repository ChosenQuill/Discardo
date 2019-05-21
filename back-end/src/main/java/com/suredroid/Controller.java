package com.suredroid;

import org.joor.Reflect;
import org.joor.ReflectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class Controller {
    @RequestMapping("/submit")
    public ResponseEntity sumbit(@RequestParam(value="code", defaultValue = "") String code){
        Pattern className = Pattern.compile("class (\\w+)");
        try {
            System.out.println(code);
            Matcher m = className.matcher(code);
            if(m.find()) {
                Supplier<String> supplier = Reflect.compile(m.group(1),
                        code
                ).create().get();
                System.out.println(supplier.get());
                return new ResponseEntity(HttpStatus.ACCEPTED);
            }
            else
                return new <String>ResponseEntity("Missing Class",HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            if(e instanceof ReflectException){
                return new <String>ResponseEntity(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
            } else {
                StringWriter errors = new StringWriter();
                e.printStackTrace(new PrintWriter(errors));
                return new <String>ResponseEntity(errors.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @RequestMapping("/")
    public ResponseEntity confirm() {
        return new <String>ResponseEntity("Status Ok.",HttpStatus.OK);
    }
}
