package com.example;

import org.kie.api.runtime.process.ProcessContext;

public class HelloProcScript {
    public static void sayHello(ProcessContext kcontext) {
        System.out.println("Hello "+kcontext.getVariable("name"));
    }
}
