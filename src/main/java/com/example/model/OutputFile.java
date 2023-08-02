package com.example.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OutputFile {

    private File filename;

    public OutputFile(File filename) {
        this.filename = filename;
    }

    public File getFilename() {
        return filename;
    }

    public void setFilename(File filename) {
        this.filename = filename;
    }

    public static void printFile(List<Message> responses) {
        responses =new ArrayList<>();
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
