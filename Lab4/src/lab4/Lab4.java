/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package lab4;

import java.util.Scanner;

/**
 *
 * @author adriedger
 */
public class Lab4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the edit distance calculator\n" +
                                "Initializing dictionary...");
        ReadDict reader = new ReadDict();
        Scanner console = new Scanner(System.in);
        String str1 = "";
        int dist = 0;
        while(!str1.equals("0")){ 
            System.out.printf("Please enter two words (or 0 to quit): ");
            String line = console.nextLine();
            Scanner tokenizer = new Scanner(line);
            str1 = tokenizer.next();
            if(tokenizer.hasNext()){
                String str2 = tokenizer.next();
                dist = reader.calculateDistance(str1, str2);
            }
            if(dist < 1000000)
                System.out.println("Edit Distance: " + dist);
            else
                System.out.println("Edit Distance: No Path Found");
        }
    }    
}
