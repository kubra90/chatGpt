package com.example.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OutputFile {
    public OutputFile() {
    }

    public void printFile(List<Message> responses) {
        try {
            // Create a new PrintWriter instance, specifying the file name
            PrintWriter writer = new PrintWriter("output.csv");

            for(Message a: responses) {
                if (a.getRole().equals("assistant")) {
                    writer.println(a.getContent());
                }
            }

            writer.close();

            System.out.println("Data written to the file successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        }
    }
