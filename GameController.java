import java.util.*;


public class GameController {

	// DO NOT CHANGE OR ADD FIELDS BELOW.
	// YOU MUST USE THESE FIELDS TO IMPLEMENT YOUR GAME.

	private ArrayList<PlayingCard> deck;
	private ArrayList<PlayingCard>[] list;
	private ArrayList<PlayingCard> discardPile;
	private int playerScore;
	
	// WRITE YOUR CODE AND JAVADOC HERE:
	
	/**
	 * GameController constructor for game controller 
	 */
	@SuppressWarnings("unchecked")
	public GameController(){
		/*
		 * public GameController() constructor for setting the game. Mostly, I used the ArrayList to set the game setting.
		 * */
		deck = new ArrayList<PlayingCard>();
		discardPile = new ArrayList<PlayingCard>();
		list = (ArrayList<PlayingCard>[])(new ArrayList[4]);
		for (int i = 0; i < 4; i++){
			list[i] = new ArrayList<PlayingCard>();
		}
	}
	
	/**
	 * @see GameController.move()
	 * store the empty list index in list.
	 */
	private List<Integer> moveTo = new ArrayList<>();
	
	/**
	 * @see java.util.Random
	 * generating random when playing game. 
	 */
	private Random random = new Random();
	
	/**
	 * getCard method
	 * @param listNum shows the index of list
	 * @param index index
	 * @return PlayingCard
	 */
	public PlayingCard getCard(int listNum, int index){
		
		if (list[listNum].size() == 0){
			return null;
		} else if(list[listNum].size() <= index) {
			return null;
		} else {
			return list[listNum].get(index);
		}
	}
	
	/**
	 * getScore method
	 * @return platerScore returns the player score in int form
	 */
	public int getScore(){
		return playerScore;
	}
		
	/**
	 * deal method
	 * deals 4 cards from deck and print the card by using cardPrinter method
	 * @see gameController.cardPrinter()
	 */
	public void deal(){
		
		if(deck.size() != 0){
			for (int s = 0; s < 4; s++){
				list[s].add(deck.get(deck.size() - 1));
				deck.remove(deck.size() - 1);
			}
		}
		cardPrinter();
		
	}
	
	/**
	* discard method
	* discard a card from list when it's condition matches with discardAvaiable method and print the card by using cardPrinter method
	* @param listNum shows the list index
	* @see GameController.discardAvailable()
	* @see GameController.cardPrinter()
	*/
	public void discard(int listNum){
		
		if (DiscardAvailable(listNum)){
			discardPile.add(list[listNum].get(list[listNum].size() - 1));
			playerScore += list[listNum].get(list[listNum].size() - 1).getRank();
			list[listNum].remove(list[listNum].size() - 1);
		}
		cardPrinter();
	}
	
	/**
	 * moreMoves method
	 * judge the situation whether player can do additional move
	 * @return ableToMove boolean result
	 * @see gameController.ableToDiscard()
	 * @see gameController.ableToMove()
	 */
	public boolean moreMoves(){
		
		if (deck.size() != 0){
			return true;
		} else{
			boolean ableToMoveMore = false;
			boolean ableToDiscard;
			boolean ableToMove;
			
			for(int s = 0; s < 4; s++){
				ableToDiscard = DiscardAvailable(s);
				ableToMove = moveAvailable(s);
				
				if(ableToDiscard || ableToMove){
					ableToMoveMore = true;
					break;
				}
			}
			return ableToMoveMore;
		}
	}
	
	/**
	 * move method
	 * allows to move a card to the list which is empty
	 * @param listNum shows the list index
	 * @see gameController.moveAvailable()
	 * @see gameController.cardPrinter()
	 */
	public void move(int listNum){
	
		int moveDes = -1;
		if (moveAvailable(listNum)){
			boolean[] emptyList = empty(list);
			
			for (int s = 0; s < 4; s++){
				if (emptyList[s]){
					moveTo.add(new Integer(s));
				}
			}
			switch(moveTo.size()){
			
			case 1: moveDes = moveTo.get(0).intValue();
					list[moveDes].add(list[listNum].get(list[listNum].size() - 1));
					list[listNum].remove(list[listNum].size() - 1);
					break;
					
			case 2: moveDes = random.nextBoolean() ? moveTo.get(0).intValue() : moveTo.get(1).intValue();
					list[moveDes].add(list[listNum].get(list[listNum].size() - 1));
					list[listNum].remove(list[listNum].size() - 1);
					break;
			
			case 3: int r = random.nextInt(3);
					moveDes = moveTo.get(r).intValue();
					list[moveDes].add(list[listNum].get(list[listNum].size() - 1));
					list[listNum].remove(list[listNum].size() - 1);
					break;
			}
			moveTo.clear();
		}
		cardPrinter();
	}
	
	/**
	 * startNewGame method
	 * let a player to start a new game. This clears all of the cards from previous game
	 * @see GameController.CardPrinter
	 */
	public void startNewGame(){
		
		deck.clear();
		
		for (int r = 1; r < 14; r++){
			deck.add(new PlayingCard(r, 'S'));
			deck.add(new PlayingCard(r, 'H'));
			deck.add(new PlayingCard(r, 'C'));
			deck.add(new PlayingCard(r, 'D'));
		}
		
		for (int s = 0; s < 4; s++){
			list[s].clear();
		}
		discardPile.clear();
		Collections.shuffle(deck);
		playerScore = 0;
		cardPrinter();
	}
	
	/**
	 * win method
	 * check whether player had won the game or not
	 * @return isEmpty && winGame boolean result
	 */
	public boolean win(){
		
		boolean isEmpty = deck.size() == 0;
		boolean winGame = true;
		for (int s = 0; s < 4; s++){
			if (list[s].size() == 0){
				return false;
			}
			
			boolean onlyOne = list[s].size() == 1;
			boolean onlyAce = list[s].get(list[s].size() - 1).getRank() == 1;
			
			if (!onlyOne || !onlyAce){
				winGame = false;
			}
		}
		
		return isEmpty && winGame;
	}
	
	/**
	 * DiscardAvailable method
	 * @param listNum shows the list index
	 * @return ableToDiscard boolean result 
	 */
	public boolean DiscardAvailable(int listNum){
		boolean ableToDiscard = false;
		if (list[listNum].size() == 0){
			return false;
		} else {
			boolean[] emptyList = empty(list);
			PlayingCard pc = list[listNum].get(list[listNum].size() - 1);
			
			for (int s = 0; s < 4; s++){
				PlayingCard pcc;
				
				if(!emptyList[s] && s != listNum){
					pcc = list[s].get(list[s].size() - 1);
					
					if(pcc.getSuit() == pc.getSuit() && pcc.getRank() < pc.getRank()){
						ableToDiscard = true;
						break;
					}
				}
			}
			
			return ableToDiscard;
		}
	}
	
	/**
	 * cardPrinter method
	 * print the card on each list 
	 */
	public void cardPrinter(){
		System.out.print("deck: ");
		
		for(int i = 0; i < deck.size(); i++){
			System.out.print(deck.get(i).toString());
			System.out.print(",");
		}
		System.out.println();
		
		for (int s = 0; s < 4; s++){
			System.out.print("list[" + s + "]" + ":");
			
			for (int j = 0; j < list[s].size(); j++){
				System.out.print(list[s].get(j).toString());
				System.out.print(",");
			}
			System.out.println();
		}
		
		System.out.print("discard: ");
		
		for (int k = 0; k <discardPile.size(); k++){
			System.out.print(discardPile.get(k).toString());
			System.out.print(",");
		}
		System.out.println();
	}
	
	/**
	 * moveAvailable method
	 * check if player can move the card or not
	 * @param listNum shows the list index
	 * @return ableToMove boolean result
	 */
	public boolean moveAvailable(int listNum){
		
		boolean ableToMove = false;
		if (list[listNum].size() == 0){
			return false;
		} else {
			boolean[] emptyList = empty(list);
			for(int s = 0; s < 4; s++){
				if (emptyList[s] && s != listNum){
					ableToMove = true;
					break;
				}
			}
			return ableToMove;
		}
	}
	
	/**
	 * empty method
	 * return the emptyList boolean that shows empty in list
	 * @param list from ArrayList<PlayingCard>
	 * @return emptyList boolean array
	 */
	public boolean[] empty(ArrayList<PlayingCard>[] list){
		
		boolean[] emptyList = new boolean[4];
		for(int s = 0; s < 4; s++){
			if(list[s].size() == 0){
				emptyList[s] = true;
			}
		}
		return emptyList;
	}
	
}
