package VideoPoker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

import VideoPoker.CardMoves.CardMove;

public class PokerGame 
{
	private static boolean inInventory = false;
	
	private static JFrame frame = new JFrame("Video Poker");
	private static JTextArea cardHolder1 = new JTextArea();
	private static JTextArea cardHolder2 = new JTextArea();
	private static JTextArea cardHolder3 = new JTextArea();
	private static JTextArea cardHolder4 = new JTextArea();
	private static JTextArea cardHolder5 = new JTextArea();

	private static JTextArea[] cardHolders = new JTextArea[]
	{
		cardHolder1,
		cardHolder2,
		cardHolder3,
		cardHolder4,
		cardHolder5,
	};

	private static JCheckBox[] boxCheckerHolders = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
	private static JLabel[] categoriesLabel = 
	{
		new JLabel(CardMoves.returnToString(0) + ": " + CardMoves.cardValues[0]),
		new JLabel(CardMoves.returnToString(1) + ": " + CardMoves.cardValues[1]),
		new JLabel(CardMoves.returnToString(2) + ": " + CardMoves.cardValues[2]),
		new JLabel(CardMoves.returnToString(3) + ": " + CardMoves.cardValues[3]),
		new JLabel(CardMoves.returnToString(4) + ": " + CardMoves.cardValues[4]),
		new JLabel(CardMoves.returnToString(5) + ": " + CardMoves.cardValues[5]),
		new JLabel(CardMoves.returnToString(6) + ": " + CardMoves.cardValues[6]),
		new JLabel(CardMoves.returnToString(7) + ": " + CardMoves.cardValues[7]),
		new JLabel(CardMoves.returnToString(8) + ": " + CardMoves.cardValues[8]),
		new JLabel(CardMoves.returnToString(9) + ": " + CardMoves.cardValues[9]),
	};

	//first goes to get inventory, submit, and refresh
	private static JButton[] menuButtonsHolder = {new JButton("Inventory"), new JButton("Submit"), new JButton("Refresh")};
	private static JPanel Panel = new JPanel(null);
	private static Border border = BorderFactory.createLineBorder(Color.BLACK);
	
	public static ArrayList<ArrayList<Card>> deckOfCards;
	public static boolean canPlay = true;
	public PokerGame()
	{	
		
		int currentAreaAmount = 0;
		Panel.setSize(500, 500);
		for (JButton button : menuButtonsHolder)
		{
			currentAreaAmount++;
			button.setBounds(120 * currentAreaAmount + 35 / 6, 470, 100, 30);
			setMenuButtonsFunction(button, currentAreaAmount);
			Panel.add(button);
		}
		currentAreaAmount = 0;
		for (JTextArea textArea : cardHolders)
		{
			textArea.setText("a\na\na\na\na\na");
			currentAreaAmount++;
			textArea.setBounds(120 * currentAreaAmount - 115, 320, 95, 120);
			textArea.setBackground(new Color(128,128,128));
			textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
			Panel.add(textArea);
		}
		currentAreaAmount = 0;
		for (JCheckBox checkbox : boxCheckerHolders)
		{
			checkbox.setText("" + currentAreaAmount);
			
			currentAreaAmount++;
			checkbox.setBounds(120 * currentAreaAmount - 80, 445, 20, 20);
			checkbox.setVisible(true);
			checkbox.setBackground(new Color(128,128,128));
			Panel.add(checkbox);
		}
		currentAreaAmount = 0;
		for (JLabel label : categoriesLabel)
		{
			label = new JLabel(CardMoves.returnToString(currentAreaAmount) + ": " + CardMoves.cardValues[currentAreaAmount]);
			currentAreaAmount++;
			label.setBounds(20, 30 * currentAreaAmount - 30, 80, 40);
			Panel.add(label);
		}
		
		deckOfCards = new ArrayList<ArrayList<Card>>();
		for (int rows = 0; rows < houseType.values().length; rows++)
		{
			ArrayList<Card> placeHolder = new ArrayList<Card>();
			for (int columns = 0; columns < cardType.values().length; columns++)
			{
				placeHolder.add(new Card(cardType.getValueFromIndex(columns), houseType.getValueFromIndex(rows)));
			}
			deckOfCards.add(placeHolder);
		}
		
//		frame.setLocationRelativeTo(null);
		frame.setSize(600, 540);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(Panel);
		
		resetDeck();
	}
	
	private void setMenuButtonsFunction(JButton button, int currentAreaAmount) 
	{
		switch (currentAreaAmount)
		{
			case 1:
				//gets into the inventory
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						disableChecks();
						inInventory = true;
						System.out.println("Inventory");
					}
					
				});
				
				break;
			case 2: //SUBMIT
				button.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						if (inInventory)
						{	
							SwingUtilities.invokeLater(new Runnable()
							{

								@Override
								public void run() {
									// TODO Auto-generated method stub
									for (JCheckBox box : boxCheckerHolders)
									{
										box.setSelected(canPlay);
										System.out.println("Apparently running");
									}
								}
						
							});
							System.out.println("Ran");
							inInventory = false;
						};
						// TODO Auto-generated method stub
						for (int i = 0; i < Program.randomlyGeneratedPile.size(); i++)
						{
							if (boxCheckerHolders[i].isSelected())
							{
								Player.addToInventory(Program.randomlyGeneratedPile.get(i));
							}
						}
							
						System.out.println(CardMoves.checkForWin(Player.stockOfCards));
						//this will also go through the loop and decheck the checkmark
						for (JCheckBox box : boxCheckerHolders)
						{
							box.setEnabled(false);
						}
					}
					
					//refresh it here
			
				});
				break;
			case 3:
				System.out.println("Refresh");
				break;
				
		}
	}
	
	public static void resetDeck()
	{
		int amountOfCards = 0;
		Random randomNumberGenerator = new Random();
		while (amountOfCards < 5)
		{
			PokerGame.deckOfCards = Card.shuffleCards(PokerGame.deckOfCards);
			try 
			{
				int randomROW = randomNumberGenerator.nextInt(houseType.values().length),
				randomCOLUMN = randomNumberGenerator.nextInt(cardType.values().length);

				Program.randomlyGeneratedPile.add(PokerGame.deckOfCards.get(randomROW).get(randomCOLUMN));

				Card currentSelectedCard = PokerGame.deckOfCards.get(randomROW).get(randomCOLUMN);
				PokerGame.deckOfCards.get(randomROW).remove(currentSelectedCard);
			
				amountOfCards++;
			} 
			catch (IndexOutOfBoundsException e) 
			{
				//leave it because who likes to make catch errors :/ - not me!
			}
		}
		PokerGame.updateCardLabelText(Program.randomlyGeneratedPile);
		Program.randomlyGeneratedPile.clear();
		
	}
	public static ArrayList<ArrayList<Card>> returnDeck()
	{
		return deckOfCards;
	}
	
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	public static void updateCardLabelText(ArrayList<Card> array)
	{
		int currentAreaAmount = 0;
		for (JTextArea textArea : cardHolders)
		{
			textArea.setText
			(Card.returnCardAscii(array.get(currentAreaAmount).returnCardType(), array.get(currentAreaAmount).returnHouseType()));
			currentAreaAmount++;
			
			textArea.repaint();
		}
	}

	public static void disableChecks() 
	{
		for (JCheckBox box : boxCheckerHolders)
		{
			box.setVisible(false);
		}
		
	}
	
	public static void enableChecks() 
	{
		
		
	}

	public static void updateCategoryLabels() 
	{
		// TODO Auto-generated method stub
		int currentAreaAmount = 0;
		for (int i = 0; i < categoriesLabel.length; i++)
		{
			categoriesLabel[i].setText(CardMoves.returnToString(currentAreaAmount) + ": " + CardMoves.cardValues[currentAreaAmount]);
			categoriesLabel[i].paintImmediately(categoriesLabel[i].getVisibleRect());
			categoriesLabel[i].repaint();
			categoriesLabel[i].revalidate();
			currentAreaAmount++;
		}

	}
}
