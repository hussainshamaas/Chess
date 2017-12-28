package game;

/**
 * @author Wilson Chan
 * @author Shamaas Hussain
 * Player class which helps handling turns
 */

import java.util.Scanner;

import chess.Board;
import pieces.Location;

public class Player {
	private String color;
	private Board board;
	public boolean requestsDraw;
	public boolean requestsResign;
	String choosePromotion;

	/**
	 *
	 * Initializes player objects
	 * @param color 
	 * This is the color of the player
	 * @param board
	 * The board 
	 */

	public Player(String color, Board board){
		this.color = color;
		this.board = board;
		this.requestsDraw=false;
	}

	
	/**
	 * This method check if the inputs are valid, 
	 * and adjust for moves, draw, and resign.
	 * It returns the move
	 * @return
	 * This method returns the move after parsing the inputs
	 */
	public Move parseInput() {
		Location starting;
		Location ending;
		Scanner sc = new Scanner(System.in);
		String[] splitInput = sc.nextLine().split(" ");
		String tok1 = null, tok2 = null, tok3;

		String start = null;
		String end= null;
		/*
		 * Check if the inputs are valid, and adjust for moves, draw, and resign.
		 */
		 if (splitInput.length == 1){
			 tok1 = splitInput[0].toLowerCase();

				if (tok1.toLowerCase().equals("resign")){
					requestsResign = true;

					return null;
				}
				return null;
		 }
		 else if(splitInput.length==2) {
			tok1=splitInput[0].toLowerCase();
			tok2=splitInput[1].toLowerCase();
			start=tok1;
			end=tok2;
		} else if (splitInput.length==3) {
			tok1 = splitInput[0].toLowerCase();
			tok2 = splitInput[1].toLowerCase();
			tok3 = splitInput[2].toLowerCase();
			start = tok1;
			end = tok2;
			if(tok3.equals("n") || tok3.equals("q") || tok3.equals("r") || tok3.equals("b")) {

				choosePromotion=tok3;
				 starting = board.index(tok1);
				 ending = board.index(tok2);
				 return new Move(starting,ending,starting.piece,tok3);
			} else if(tok3.toLowerCase().equals("draw?")){
					requestsDraw = true;
				}
			}
			else {
				return null;
			}

		 starting = board.index(start);
		 ending = board.index(end);
		 return new Move(starting,ending,starting.piece,"");
		 	// Find the location of a piece
		 // return the Move (this method is then inialized in Game.
		//return null;




	}
}
