package VideoPoker;

public enum houseType 
{
	Spades,
	Hearts,
	Diamonds,
	Clubs;

	public static houseType getValueFromIndex(int index)
	{
		if (index >= 0 && index < cardType.values().length)
			return houseType.values()[index];
		return null;
	}
	
	public static int getIndexFromHouse(houseType type)
	{
		for (int i = 0; i < cardType.values().length; i++)
		{
			if (type == houseType.values()[i])
				return i;
		}
		return 0;
	}
	
	public static String returnToString(int index) 
	{
		return houseType.values()[index].toString();
	}
}
