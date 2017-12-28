package chess;

import pieces.Location;
import pieces.*;

public class Board {
	Location[][] board;
	  public enum pieceColor {BLACK, WHITE};

	 public Board() {
		this.board=new Location[8][8];
		intializeBlack(board);
		initializePawns(board);
		intializeNonPawns(board);
	 }


	  public Location[][] getLocation(){
			return this.board;
		}
	  /**
		 * This is a helper method that maps each Location
		 * on the board with the corresponding letter and row
		 * @param a 
		 * This string is what is taken in as input from each player
		 * @return Location mapped
		 */
	 public Location index(String a) throws IllegalArgumentException{
			String s = a.toLowerCase();
			switch(s){
			case "a8":return board[0][0];
			case "b8":return board[0][1];
			case "c8":return board[0][2];
			case "d8":return board[0][3];
			case "e8":return board[0][4];
			case "f8":return board[0][5];
			case "g8":return board[0][6];
			case "h8":return board[0][7];

			case "a7":return board[1][0];
			case "b7":return board[1][1];
			case "c7":return board[1][2];
			case "d7":return board[1][3];
			case "e7":return board[1][4];
			case "f7":return board[1][5];
			case "g7":return board[1][6];
			case "h7":return board[1][7];

			case "a6":return board[2][0];
			case "b6":return board[2][1];
			case "c6":return board[2][2];
			case "d6":return board[2][3];
			case "e6":return board[2][4];
			case "f6":return board[2][5];
			case "g6":return board[2][6];
			case "h6":return board[2][7];

			case "a5":return board[3][0];
			case "b5":return board[3][1];
			case "c5":return board[3][2];
			case "d5":return board[3][3];
			case "e5":return board[3][4];
			case "f5":return board[3][5];
			case "g5":return board[3][6];
			case "h5":return board[3][7];

			case "a4":return board[4][0];
			case "b4":return board[4][1];
			case "c4":return board[4][2];
			case "d4":return board[4][3];
			case "e4":return board[4][4];
			case "f4":return board[4][5];
			case "g4":return board[4][6];
			case "h4":return board[4][7];

			case "a3":return board[5][0];
			case "b3":return board[5][1];
			case "c3":return board[5][2];
			case "d3":return board[5][3];
			case "e3":return board[5][4];
			case "f3":return board[5][5];
			case "g3":return board[5][6];
			case "h3":return board[5][7];

			case "a2":return board[6][0];
			case "b2":return board[6][1];
			case "c2":return board[6][2];
			case "d2":return board[6][3];
			case "e2":return board[6][4];
			case "f2":return board[6][5];
			case "g2":return board[6][6];
			case "h2":return board[6][7];

			case "a1":return board[7][0];
			case "b1":return board[7][1];
			case "c1":return board[7][2];
			case "d1":return board[7][3];
			case "e1":return board[7][4];
			case "f1":return board[7][5];
			case "g1":return board[7][6];
			case "h1":return board[7][7];


			default: throw new IllegalArgumentException();
			}
		}

	 /**
		 * This method will initialize # on the board 
		 * @param board
		 * This parameter is the board that will be updated.
		 */
	 public void intializeBlack(Location[][] board) {
			pieceColor color= pieceColor.BLACK;
			for (int i = 0; i < 8; i++){
				if (i % 2 == 0) {
					for (int j = 0; j < 8; j++) {
						if (j % 2 == 0) {
							color = pieceColor.WHITE;
						}
						else {
							color = pieceColor.BLACK;
						}
						board[i][j] = new Location(i, j, color);
					}
				}
				else
				{
					for (int j = 0; j < 8; j++){
						if (j % 2 == 1){
							color = pieceColor.WHITE;
						}
						else{
							color = pieceColor.BLACK;
						}
						board[i][j] = new Location(i, j, color);
						}
				}
			}
	 }

	 /**
		 * This method will initialize all the pawns on the board
		 * @param board
		 * This parameter is the board that will be updated.
		 */
	 public void initializePawns(Location[][] board) {
			for(int i = 0; i < 8; i++) {
				if(i==1) {
					for(int j = 0; j < 8; j++) {
						board[i][j].placePiece(new Pawn(pieceColor.BLACK, board[i][j]));

					}
				}
				else if(i==6) {
					for(int j = 0; j < 8; j++) {
						board[i][j].placePiece(new Pawn(pieceColor.WHITE, board[i][j]));

				}

			}

			}
	 }
	 
	 /**
		 * This method will initialize all the every other piece 
		 * besides pawn on the board
		 * @param board
		 * This parameter is the board that will be updated.
		 */

	 public void intializeNonPawns(Location[][] board) {
		 for(int i = 0; i < 8; i++)
			{
				if( i == 0)
				{
					board[i][0].placePiece(new Rook(pieceColor.BLACK, board[i][0]));
					board[i][1].placePiece(new Knight(pieceColor.BLACK, board[i][1]));
					board[i][2].placePiece(new Bishop(pieceColor.BLACK, board[i][2]));
					board[i][3].placePiece(new Queen(pieceColor.BLACK, board[i][3]));
					board[i][4].placePiece(new King(pieceColor.BLACK, board[i][4]));
					board[i][5].placePiece(new Bishop(pieceColor.BLACK, board[i][5]));
					board[i][6].placePiece(new Knight(pieceColor.BLACK, board[i][6]));
					board[i][7].placePiece(new Rook(pieceColor.BLACK, board[i][7]));
				}
				else if( i == 7)
				{
					board[i][0].placePiece(new Rook(pieceColor.WHITE, board[i][0]));
					board[i][1].placePiece(new Knight(pieceColor.WHITE, board[i][1]));
					board[i][2].placePiece(new Bishop(pieceColor.WHITE, board[i][2]));
					board[i][3].placePiece(new Queen(pieceColor.WHITE, board[i][3]));
					board[i][4].placePiece(new King(pieceColor.WHITE, board[i][4]));
					board[i][5].placePiece(new Bishop(pieceColor.WHITE, board[i][5]));
					board[i][6].placePiece(new Knight(pieceColor.WHITE, board[i][6]));
					board[i][7].placePiece(new Rook(pieceColor.WHITE, board[i][7]));
				}
			}

		}
	 
	 
	 public String toString(){
			String output = "";

			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					output = output + " " + board[i][j];
				}
				output = output + " " + (8 - i) + "\n";
			}
			output = output + "  a  b  c  d  e  f  g  h\n";
			return output;
		}
	 }




