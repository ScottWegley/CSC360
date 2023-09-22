package Cryptanalyst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Cryptanalyst {

    public static void main(String[] args) {
        dictionaryTest();
    }

    private static void dictionaryTest() {
        try {
            File dictionary = new File("C:\\Code\\CSC360\\Cryptanalyst\\dictionary.txt");
            File mask = new File("C:\\Code\\CSC360\\Cryptanalyst\\out.txt");
            ArrayList<String> permitted = new ArrayList<>();
            ArrayList<String> output = new ArrayList<>();

            BufferedReader mReader = new BufferedReader(new FileReader(mask));
            String line;
            while ((line = mReader.readLine()) != null) {
                permitted.add(line);
            }


            for (String string : permitted) {
                System.out.println("Allowing: " + string);
            }
            BufferedReader dReader = new BufferedReader(new FileReader(dictionary));

            while ((line = dReader.readLine()) != null) {
                System.out.println("Checking: " + line);
            }
            dReader.close();
            mReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}