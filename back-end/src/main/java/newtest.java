package com.suredroid;

import com.suredroid.Test;

public class newtest implements Test{
        @Override
        public String doSomething() {
            int a = 8;
            int b = 3;
            return a + " * " + b  + " is "  + (a*b);
    }
}
