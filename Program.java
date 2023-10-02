package VideoPoker;
import java.util.Scanner;

/**
 * So this program uses a double arraylist of a Card class
 * And what it does when the program first runs, it assigns cards to every single
 * placement in the double array , and what it does is it randomizes the whole list 
 * by converting it to a 1D array, and then it goes back and reassigns it to the double array
 * Then what it does next is assigns 5 random cards to be presented to the user
 * and the user can choose which cards they want to choose, whether they want to choose all
 * quit or get all of the cards, it is up to them
 */
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
public class Program 
{
	public static ArrayList<Card> randomlyGeneratedPile = new ArrayList<Card>();
	public static void main(String[] args) 
	{
		//turn this into a gui , my future self will totally not be mad at me amr
		PokerGame game = new PokerGame();
	}

}
