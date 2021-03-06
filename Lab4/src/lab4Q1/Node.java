/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package lab4Q1;

import java.util.HashSet;
import java.util.Set;

/**
 * Node class has the dictionary word, a list of words 1 edit distance away, and
 * the current edit distance of the algorithm.
 *
 * @author adriedger
 */
public class Node {
    
    private String word;
    private Set<String> list;
    private int distance;
    
    public Node(String word){
        this.word = word;
        this.list = new HashSet<>();
        this.distance = 0;
    }
    
    public void add(String word){this.list.add(word);}
    
    public void setDistance(int dist){this.distance = dist;}
    
    public int getDistance(){return this.distance;}

    public String getWord(){return this.word;}
    
    public Set<String> getList(){return this.list;}
}
