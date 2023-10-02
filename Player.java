package VideoPoker;
import java.util.ArrayList;

public class Player 
{
    public static double payout = 5.0;
    public static ArrayList<Card> stockOfCards = new ArrayList<Card>();
    
    public static void addToInventory(Card card)
    {
    	stockOfCards.add(card);
    }
    
    public static void getRidOfItemInInventory(int index)
    {
    	stockOfCards.remove(index);
    }
    
    public static void resetCards()
    {
    	stockOfCards.clear();
    }
}
