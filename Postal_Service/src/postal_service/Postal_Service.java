/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postal_service;

import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author adriedger
 */
public class Postal_Service {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            Scanner inFile = new Scanner(Paths.get("PostalCodeRecord.txt"));
/*            while (inFile.hasNextLine()) {
                String entryLine = inFile.nextLine();
                System.out.println(entryLine);
            }*/
            processEntries(inFile);
        } 
        catch(IOException ex){
            ex.printStackTrace();
            System.out.println("Could not find path");
            return;
        }
        
    }
    
    private static void processEntries(Scanner inFile){
        Months current = new Months("January");
        List<Months> monthList = new ArrayList<>();
        while(inFile.hasNextLine()){
            String line = inFile.nextLine();            
            if(!line.equals("")) {
                Scanner token = new Scanner(line).useDelimiter(" ");
                String entry = token.next();
                if(Months.isMonth(entry)){
                    current = new Months(entry);
                    monthList.add(current);
                } 
                else{
                    current.addCode(entry);                    
                }
            }
        }
        for(Months s : monthList){
            System.out.println(s.shortName() +" "+ s.codeCount());
        }
        System.out.println("done");
    }
    
}