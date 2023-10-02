package VideoPoker;

public enum cardType 
{
	Ace,
	Two,
	Three,
	Four,
	Five,
	Six,
	Seven,
	Eight,
	Nine,
	Ten,
	Jack,
	Queen,
	King;

	public static cardType getValueFromIndex(int index)
	{
		if (index >= 0 && index < cardType.values().length)
			return cardType.values()[index];
		return null;
	}
	
	public static int getIndexFromCard(cardType type)
	{
		for (int i = 0; i < cardType.values().length; i++)
		{
			if (type == cardType.values()[i])
				return i;
		}
		return 0;
	}
	
	public static char returnToString(int index) 
	{
		switch (index)
		{
			case 0:
				return 'A';
			case 1:
				return '2';
			case 2:
				return '3';
			case 3:
				return '4';
			case 4:
				return '5';
			case 5:
				return '6';
			case 6:
				return '7';
			case 7:
				return '8';
			case 8:
				return '9';
			case 9:
				return 'X';
			case 10:
				return 'J';
			case 11:
				return 'Q';
			case 12:
				return 'K';
			default:
				return '?';
		}
			
	}
}
