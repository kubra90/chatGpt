package com.example.model;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileReader {


    //this class read the csv file and return messages.
    private String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Message> readingFile() {
//        String fileName = "prompt.csv";
        File promptsFile = new File(fileName);

        //save all messages into the list of messages
        List<Message> messages = new ArrayList<>();
        String input = "";
        try (Scanner reader = new Scanner(promptsFile)) {
            while (reader.hasNextLine()) {
                input = reader.nextLine();
                Message message = new Message("user", input);
                messages.add(message);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error  -- unable to read the file!");
            System.exit(1);
        }
        return messages;
    }
}
