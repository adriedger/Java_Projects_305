/*
 * Macewan University CMPT 305 Professor: Brian Brookwell
 */
package freecell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * *
 * @author Andre Driedger
 * Cell class is parent class of the different Cells in FreeCell
 */
public class Cell {
    
    private List<Card> stack;
    private String name;
    
    /**
     * @param start list of cards dealt into cell when game starts
     * @param name 2 char string name of the Cell 
     */
    public Cell(List<Card> start, String name){
        stack = new ArrayList<>();
        for (Card c : start)
            stack.add (c);        
        this.name = name;
    }
    
    public int getSize() {return stack.size();}
    
    /**
     * @param num index of where you want to get Card in 
     * @return Card at index
     */
    public Card getCard(int num) {return stack.get(num);}
    public String getName() {return name;}
    
    /**
     * @return true if Cell is empty
     */
    public boolean isEmpty() {return stack.isEmpty();}
    
    /**
     * @return Card at the top of stack (bottom-most facing the player)
     */
    public Card getTop() {return stack.get(stack.size()-1);}
    public Card getSubstackTop(){return null;}
    
    @Override
    public String toString() {
        String out = this.getName() + ": ";         
        for(Card c : stack)
            out = out + c.toString() + " ";
        return out;
    }
    
    public boolean canMoveFrom(){return false;}
    public boolean canMoveTo(Card moving){return false;}
    
    /**
     * @return true if Cell is a Tableau
     */
    public boolean isTableau(){return this.getName().charAt(0) == 'T';}
    
    /**
     * @param dest The destination Cell of attempted move
     * This method checks if attempted move is a legal move.
     * If its not a legal move, does nothing and prints to stdout Illegal Move.
     * If it is legal move, it moves card(s) and prints to stdout Successful Move.
     * If the origin and destination Cells are both Tableaus and legal, 
     * this method moves the substack instead of the single card.
     */
    public void move(Cell dest){
        if(this.isTableau() && dest.isTableau()){
            if(this.canMoveFrom() && dest.canMoveTo(this.getSubstackTop())){
                System.out.println("Succesfull Move!");
                this.stackAddRemove(dest);
            }
            else
                System.out.println("Illegal Move");
        }
        else{
            if(this.canMoveFrom() && dest.canMoveTo(this.getTop())){            
                System.out.println("Succesfull Move!");
                Card c = this.getTop();
                dest.stack.add(c);
                this.stack.remove(c);
            }
            else
                System.out.println("Illegal Move");
        }
    }
    
    /**
     * @param dest The destination Cell of the stack move
     * This method removes the substack (stack of cards which match the Solitaire 
     * stack pattern of next less rank and alternating colors) and appends it to 
     * destination Cell
     */
    private void stackAddRemove(Cell dest){
        List<Card> movingStack = new ArrayList<>();
        int count = this.getSize() - 1;
        Card current = this.getCard(count);
        Card next = current;
        if(count != 0)
                next = this.getCard(count - 1);
        this.stack.remove(count);
        movingStack.add(current);
        while(current.getRank().isNextLess(next) && current.getSuit().isDiffColor(next) && count!=0){
            count --;
            current = this.getCard(count);
            if(count != 0)
                next = this.getCard(count - 1);
            this.stack.remove(count);
            movingStack.add(current);           
        }
        Collections.reverse(movingStack);
        dest.stack.addAll(movingStack);
    }
}
