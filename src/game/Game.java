package game;
/**
 * @author Wilson Chan
 * @author Shamaas Hussain
 * Game class handles all the player turns.
 */
import java.util.Scanner;

import chess.Board;
import chess.Board.pieceColor;
import pieces.*;
import pieces.Piece.PieceType;



public class Game {
	private Player white;
	private Player black;
	private Board board;
	private boolean isGameOver;
	private Player currentPlayer;
	/**
	 * moveIterator is a helper method that make sure Pawns can only take en Passant
	 * if they are taking a piece that double pushed past the previous turn.
	 * @param board
	 * the game board that is being looked through
	 */
	public void moveIterator(Location[][] board){
		int team = 0;
		if(currentPlayer == white){
			team = 0;
		}
		if(currentPlayer == black){
			team = 1;
		}

		for(int r = 0; r< 8; r++){
			for(int c= 0; c< 8; c++){
				if(board[r][c].piece != null){
					if(team == 0){
						if(board[r][c].piece.color == pieceColor.WHITE){
							if(board[r][c].piece.moved == 1){
								board[r][c].piece.moved = 2;
							}
						}
					}
					if(team == 1){
						if(board[r][c].piece.color == pieceColor.BLACK){
							if(board[r][c].piece.moved == 1){
								board[r][c].piece.moved = 2;
							}
						}
					}
				}
			}
		}
	}


/*	public boolean isCheckMate(Location[][] board){
		Piece BK = board[0][0].piece;
		Piece WK = board[7][7].piece;
		for(int r= 0; r<8; r++){
			for(int c = 0; c< 8; c++){

				Location l = board[r][c];
				if(l.piece!=null) {
				Piece p = l.piece;
				if(p.type == PieceType.KING){
					if(p.color == pieceColor.BLACK){
						BK = board[r][c].piece;
					}
					if(p.color == pieceColor.WHITE){
						WK = board [r][c].piece;
					}

				}
			}
			}
		}
		pieceColor curColor = king.color;
		Location tmp;
		for(int r = 0; r<8; r++){
			for(int c=0; c<8; c++){
				tmp = board[r][c];
				//if there is a piece on the location
				if(tmp.piece != null){
					if(tmp.piece.color != curColor){
						//b is the dummy board we're gonna use to check possibilities
						for(int m = 0; m<8; m++){
							for(int n=0; n<8; n++){
								//try to move b[r][c] to b[m][n]
								//then check isCheck
								// Move(Location start, Location end, Piece p , String choosePromotion)

								Board tempBoard = new Board();
								Location[][] b = tempBoard.getLocation();
								//
								for(int z= 0; z< 8; z++){
									for(int y = 0; y<8; y++){

									}
								}
								//
								if(b[r][c].piece != null){
									System.out.println(b[r][c].piece.type);
								}


								Move check = new Move(b[r][c], b[m][n] , b[r][c].piece, "");
								Piece p = b[r][c].piece;
								if(p.isValidMove(b, check, 1, "") == true){
									System.out.println("valid move found");
									if(isCheck(b)== false){
										return false;

									}
								}
								if(p.isValidMove(b, check,  1, "")== false){
									System.out.println("valid move NOT found");
								}

							}
						}
						//dummy board ends here


					}
				}
			}
		}
		System.out.println("Checkmate");
		return true;
	}*/

	public Game(){
		this.board = new Board();
		this.white = new Player("white",this.board);
		this.black = new Player("black",this.board);
	}
	
	/**
	 * isCheck gets called during the end of each move to see if the player's
	 * king is in check.
	 * @param board
	 *This parameter is the board that will be updated.
	 *@return 
	 *return true if a player is in check, false if not. 
	 */
	
	public boolean isCheck(Location[][] board){
		Piece BK = board[0][0].piece;
		Piece WK = board[7][7].piece;
		for(int r= 0; r<8; r++){
			for(int c = 0; c< 8; c++){

				Location l = board[r][c];
				if(l.piece!=null) {
				Piece p = l.piece;
				if(p.type == PieceType.KING){
					if(p.color == pieceColor.BLACK){
						BK = board[r][c].piece;
					}
					if(p.color == pieceColor.WHITE){
						WK = board [r][c].piece;
					}

				}
			}
			}
		}
		//now check for check
		for(int r = 0; r<8; r++){
			for(int c=0; c<8; c++){
				Location l = board[r][c];
				Piece p = l.piece;
				if(l.piece == null){
					continue;
				}
				if(l.piece.color == pieceColor.BLACK){
					/*System.out.println(l.piece.placedAt);
					System.out.println(WK.placedAt);
					System.out.println( l.piece);*/
					Move check = new Move(l.piece.placedAt, WK.placedAt , l.piece, "");
					if(p.isValidMove(board, check, 0, "") == true){
						//WK.underCheck = true;
				//		isCheckMate(board, WK);
						return true;
					}



				}
				if(l.piece.color == pieceColor.WHITE){
					Move check = new Move(l.piece.placedAt, BK.placedAt , l.piece, "");
					if(p.isValidMove(board, check, 0, "") == true){
					//	isCheckMate(board, BK);
						//BK.underCheck = true;
						return true;
					}

				}
			}

		}
		return false;
	}

	/**
	 * Takes and parses input from a player.
	 * Current player make a move 
	 * Then calls method to check if move is valid.
	 * That method take care of the movements.
	 * If its invalid, then user is prompted again.
	 *
	 */
public void playGame(){


	 boolean whiteplayer = true;
	 Move move;

	 while(!isGameOver) {
	    	System.out.println(board.toString());


		 if(whiteplayer) {
			 currentPlayer=white;
			 moveIterator(this.board.getLocation());
			 System.out.print("White's Move:");

			 move = white.parseInput();
			 handleResign();
			 if(move.getStart().piece == null){

				 System.out.println("Illegal move, try again");
				 move = null;
					continue;
			 	}

				if(requestDraw()) {

					continue;
				}
				if(move != null && move.getPiece().color==pieceColor.BLACK)
				{
					System.out.println("Illegal move, try again");
					move = null;
					continue;
				}

			 Location l = move.start;
			 Piece p = l.piece;

			 this.board.getLocation();
			 Location[][] curr = this.board.getLocation();

			 boolean attempt = p.isValidMove(curr,move,0,move.choosePromotion);
			 if(attempt == true){

				 attempt = p.isValidMove(curr,move,1,move.choosePromotion);

				 if(isCheck(this.board.getLocation())==true) {
					 System.out.println("Check");
				 }
				 
				 isKingKilled();
				/* if(isCheckMate(this.board.getLocation())== true){
					 System.out.println("CHECKMATE");
				 }*/


			 }else {
				 System.out.println("Illegal move, try again");
				 move = null;
				continue;

				 //System.out.println("Why is this invalid?");
			 }



				whiteplayer=false;



			//System.out.println();

		 }else {
			 currentPlayer=black;
		//	 moveIterate();
			 moveIterator(this.board.getLocation());
			 System.out.print("Black's move:");
			move = black.parseInput();
			handleResign();

			 if(move.getStart().piece == null){
				 System.out.println("Illegal move, try again");
				 move = null;
					continue;
			 	}
			if (requestDraw()){
				continue;
			}

			if(move != null && move.getPiece().color==pieceColor.WHITE)
			{
				System.out.println("Illegal move, try again");
				move = null;
				continue;
			}
			Location l = move.start;
			 Piece p = l.piece;
			 this.board.getLocation();
			 Location[][] curr = this.board.getLocation();

			 boolean attempt = p.isValidMove(curr,move,0,move.choosePromotion);
			 if(attempt == true){

				 attempt = p.isValidMove(curr,move,1,move.choosePromotion);
				 if(isCheck(this.board.getLocation())==true) {
					 System.out.println("check");
				 }
				 
				 isKingKilled();
				/* if(isCheckMate(this.board.getLocation())== true){
					 System.out.println("CHECKMATE");
				 }*/

			 }else {
				 System.out.println("Illegal move, try again");
				 move = null;
					continue;
			}

			if (requestDraw()){
				continue;
			}
			else{
				whiteplayer = true;
			}


		 }

	 }

 }
/**
 * Handles resigns, if white or black declare "resign"
 * Declare the other player the winner.
 */


public void handleResign(){
		if(white.requestsResign){
			System.out.println();
			System.out.println("Black wins");
			System.exit(0);
		}
		else if (black.requestsResign){
			System.out.println();
			System.out.println("White wins");
			System.exit(0);
		}

	}

/**
 * Handles draw. If the third parameter is draw? 
 * The other user can either respond with "no" (n works too.)
 * or accept the draw by "draw" 
 * @return 
 */

private void isKingKilled(){
	 boolean isBlackKing=false;
	 boolean isWhiteKing=false;

	 for(int m = 0; m<8; m++){
			for(int n=0; n<8; n++){
				Location[][] b = this.board.getLocation();
			if(b[m][n].piece!=null) {
				if (b[m][n].piece.type==PieceType.KING && b[m][n].piece.color==pieceColor.BLACK) {
					isBlackKing=true;
				}
				if (b[m][n].piece.type==PieceType.KING && b[m][n].piece.color==pieceColor.WHITE) {
					isWhiteKing=true;
				}

			}
			
			}
	 }
	 
	 if(isBlackKing==false) {
		 System.out.println(this.board.toString());
		 System.out.println("White wins");
		 isGameOver=true;
	 }
	 if(isWhiteKing==false) {
		 System.out.println(this.board.toString());
		 System.out.println("Black wins");
		 isGameOver=true;
	 }
}


 private boolean requestDraw(){
		Scanner input = new Scanner(System.in);
		String answer;
		boolean keepAsking = true;
		if (black.requestsDraw){
			while (keepAsking){
				System.out.println();
				System.out.print("Please type draw to accept the draw, otherwise type 'no' ");
				answer = input.nextLine();
				System.out.println();
				if (answer.toLowerCase().equals("draw")){
					System.out.print("Draw");
					isGameOver = true;
					return true;
				}else if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")){
					black.requestsDraw = false;
					keepAsking = false;
					return false;
					//continue with game
				}else{
					System.out.println("Incorrect input");
				}
			}
		}
		else if (white.requestsDraw){
			while (keepAsking){
				System.out.println();
				System.out.print("Draw?");
				answer = input.nextLine();
				System.out.println();
				if (answer.toLowerCase().equals("draw")){
					System.out.print("Draw");
					isGameOver = true;
					return true;
				}else if (answer.toLowerCase().equals("no") || answer.toLowerCase().equals("n")){
					white.requestsDraw = false;
					keepAsking = false;
					return false;
				}else{
					System.out.println("Incorrect input");
				}
			}
		}
		return false;
	}
}

