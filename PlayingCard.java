
/**
 * PlayingCard class for making card to be played during the game
 */
public class PlayingCard implements Comparable<PlayingCard> {

	
	private int cardRank; // declared cardRank with private
	private char cardSuit; // declared cardSuit with private
	
	/**
	 * PlayingCard constructor
	 * @param cardRank shows the card rank in forms of int
	 * @param cardSuit shows the card suit in forms of char
	 */
	public PlayingCard(int cardRank, char cardSuit){
		
		boolean validRank = (cardRank >=1 && cardRank <= 13);
		boolean validSuit = (cardSuit == 'S' || cardSuit == 'H' || cardSuit == 'C' || cardSuit == 'D');
		
		if (validRank && validSuit){
			this.cardRank = cardRank;
			this.cardSuit = cardSuit;
		} else {
			System.out.println("Problem occurred with Suit or Rank");
			System.exit(0);
		}
	}
	
	/**
	 * getRank method
	 * @return cardRank returns the card rank in int form
	 */
	public int getRank(){
		return cardRank;
	}
	
	/**
	 * getSuit method
	 * @return cardSuit returns the card suit in char form 
	 */
	public char getSuit(){
		return cardSuit;
	}
	
	/**
	 * @return String returns the image file name in forms of string
	 */
	public String getImageFileName(){
		return Integer.toString(cardRank) + Character.toString(cardSuit) + ".png";
 	}
	
	/**
	 * @return String returns the card rank to string from int, card suit to string from char 
	 */
	public String toString(){
		return Integer.toString(cardRank) + Character.toString(cardSuit);
	}
	
	
	/**
	 * @return int returns 1 if card rank is greater than the other card rank, returns -1 when the card rank is less than the other card, 
	 * returns 0 when the card rank is same with the other card 
	 */
	public int compareTo(PlayingCard otherCard){
		if (this.cardRank > otherCard.getRank()){
			return 1;
		} else if (this.cardRank < otherCard.getRank()){
			return -1;
		} else{
			return 0;
		}
	}

}
