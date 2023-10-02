package VideoPoker;

import java.util.ArrayList;

/*
 * No pair – The lowest hand, containing 5 separate cards that do not match up to create any of the hands below.

One pair – Two cards of the same value, for example 2 queens. Payout: 1

Two pairs – Two pairs, for example two queens and two fives. Payout: 2

Three of a kind – Three cards of the same value, for example three queens. Payout: 3

Straight – Five cards with consecutive values, not necessarily of the same suit, such as 4, 5, 6, 7, 8. The Ace can either precede a 2 or follow a King. Payout: 4

Flush – Five cards, not necessarily in order, of the same suit. Payout: 5

Full House – Three of a kind and a pair, for example three queens and two fives. Payout: 6

Four of a Kind – Four card of the same value, such as four queens. Payout: 25

Straight Flush – A straight and a flush: five cards with consecutive values of the same suit. Payout: 50

Royal Flush – The best possible hand in poker. A 10, Jack, Queen, King, and Ace, all of the same suit. Payout: 250
 */
public class CardMoves 
{
	enum CardMove
	{
		//total will be 12 that is out
		None,
		OnePair,
		TwoPair,
		ThreePair,
		FourPair,
		Straight,
		Flush,
		FullHouse,
		SFlush,
		RFlush
	}
	
	public static int[] cardValues = new int[CardMove.values().length];
	
	public static String returnToString(int index) 
	{
		return CardMove.values()[index].toString();
	}
	
	public static int returnStringLength(int index)
	{
		return CardMove.values()[index].toString().length();
	}
	
	//two cards
	//one pair
	//pairValue = 2;
	//
	public static CardMove checkForWin(ArrayList<Card> inventory)
	{
		//Check for pairs
		int index = 0;
		int pairValue = 0;
		boolean hasPair;
		do
		{
			for (int arrayPos = 0; arrayPos < inventory.size(); arrayPos++)
			{
				if (index != arrayPos)
				{
					if (inventory.get(index).returnCardType() == inventory.get(arrayPos).returnCardType())
					{
						pairValue++;
					}
				}
			}
			index++;
		}
		while (index < inventory.size());
		System.out.println(pairValue);
		Player.resetCards();
		if ( (pairValue > 0 || inventory.size() > 1) && pairValue % 2 == 0)
		{
			CardMove move = CardMove.values()[(int) pairValue / 2];
			cardValues[(int) pairValue/2]++;
			pairValue = 0;
			PokerGame.updateCategoryLabels();
			return move;
		}
		
		//checks for straight
		// 4 5 6 7 8 
		// prev = 4
		//cur == 5
		//if cur - prev == 1;
		boolean toReturnStraight = false;
		for (int i = 0; i < inventory.size() - 1; i++)
		{
			//get the integer of the current and last element
			int prevCardNum = (int) inventory.get(i).indexOfCard;
			int curCardNum = (int) inventory.get(i + 1).indexOfCard;
			
			if (! ( curCardNum - prevCardNum == 1))
			{
				toReturnStraight = true;
			}
		}
		
		//checks for flush
		boolean toReturnFlush = false;
		for (int i = 1; i < inventory.size(); i++)
		{
			//will be a placeholder to judge every single element next
			houseType curCardHouse = inventory.get(i - 1).returnHouseType();
			if (! ( inventory.get(i).returnHouseType() == curCardHouse ))
			{
				toReturnFlush = true;
			}
		}
		
		if (toReturnStraight && toReturnFlush)
		{
			return CardMove.values()[9];
		}
		else if (toReturnStraight)
		{
			return CardMove.values()[5];
		}
		else if (toReturnFlush)
		{
			return CardMove.values()[6];
		}
		
		PokerGame.disableChecks();
		return CardMove.None;
	}
}
