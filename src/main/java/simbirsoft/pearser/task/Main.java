package simbirsoft.pearser.task;

import simbirsoft.pearser.task.services.RunService;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        RunService service = new RunService();
        service.run(args[0]);
    }
}
