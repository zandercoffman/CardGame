package VideoPoker;

import java.util.ArrayList;
import java.util.Collections;

public class Card 
{
	private cardType typeOfCard;
	private houseType typeOfHouse;
	public int indexOfCard, indexOfHouse;
	
	public Card(cardType typeOfCard, houseType typeOfHouse)
	{
		this.typeOfCard = typeOfCard;
		this.typeOfHouse = typeOfHouse;
		
		indexOfCard = cardType.getIndexFromCard(typeOfCard);
		indexOfHouse = houseType.getIndexFromHouse(typeOfHouse);
	}

	public String returnCard()
	{
		return typeOfCard + " of " + typeOfHouse;
	}

	/**
	 * What this method does to take this board and shuffle it
	 * is converting it to not a double array, but back to a 
	 * 1D array, and then it shuffles it through, and then
	 * it goes back and adds it all back to the 2D ArrayList.
	 * 
	 * @param board
	 * @return Shuffled Array from the parameter input.
	 */
	public static ArrayList<ArrayList<Card>> shuffleCards(ArrayList<ArrayList<Card>> board)
	{
		ArrayList<Card> flatDeck = new ArrayList<>();

		for (ArrayList<Card> row : board)
		{
			flatDeck.addAll(row);
		}

		// Shuffle the flattened deck
		Collections.shuffle(flatDeck);

		// Reconstruct the shuffled deck
		int index = 0;
		for (ArrayList<Card> row : board)
		{
			for (int i = 0; i < row.size(); i++)
			{
				row.set(i, flatDeck.get(index));
				index++;
			}
		}

		return board;
	}

	public static void printDeck()
	{
		ArrayList<ArrayList<Card>> board = PokerGame.returnDeck();
		for (int rows = 0; rows < board.size(); rows++) 
		{
            for (int columns = 0; columns < board.get(rows).size(); columns++) 
			{
    
				System.out.printf("%4s", board.get(rows).get(columns).returnCard() + " , ");
            }
            System.out.println();
			System.out.println();
        }
	}
	
	public cardType returnCardType()
	{
		return typeOfCard;
	}
	
	public houseType returnHouseType()
	{
		return typeOfHouse;
	}
	
	public static String returnCardAscii(cardType typeToConvert, houseType houseToConvert)
	{
		/*
		 * Inspo: X = typeToConvert
		 * ------------
		 * | X        |
		 * |          |
		 * |          |
		 * |     T    |
		 * |          |
		 * |          |
		 * |         X|
		 * ------------
		 */
		//SAVE FOR GUI
		String toReturn = "";
		final int width = toReturn.length();
		toReturn += cardType.returnToString(typeToConvert.ordinal());
		toReturn += "         \n               \n";
		//Calculates the amount of required spaces for the word
		String houseToAdd = houseType.returnToString(houseToConvert.ordinal());
		//We now have 11 characters to add - technically 10 - now is 10
		//Hearts - /2 = 3 ----- full length now is 4 - 10 - 6 = 4
		// divide that by 2, and that will get you the spaces
		int amountOfSpaces = (int) (10 - houseToAdd.length())/2;
		if (amountOfSpaces % 2 != 0)
		{
			amountOfSpaces+=3;
		}
		for (int i = 0; i < amountOfSpaces; i++)
		{
			toReturn += " ";
		}
		toReturn += houseToAdd;
		for (int i = 0; i < amountOfSpaces; i++)
		{
			toReturn += " ";
		}
		toReturn += "\n            \n             ";
		toReturn += cardType.returnToString(typeToConvert.ordinal());
		toReturn += "  \n\t";
		System.out.println();
		return toReturn;
	}

}
