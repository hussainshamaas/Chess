package pieces;

import chess.Board.pieceColor;
import game.Move;
import pieces.Piece.PieceType;

public class Knight extends Piece {

	public Knight(pieceColor color, Location PlacedAt) {
		super(color, PlacedAt);
		this.type = PieceType.KNIGHT;
		this.color = color;
		this.moved = 0;
		// TODO Auto-generated constructor stub
	}


	public String toString()
	{
		if(this.color==pieceColor.BLACK) {
			return "bN";
		}
		else {
			return "wN";
		}
	}



	@Override
	public boolean isValidMove(Location[][] board, Move move, int confirm, String choosePromotion){

		//this section grabs and names variables for use
		Location starting = move.getStart();
		Location ending = move.getEnd();

		int a = starting.getRow();
		int b = starting.getColumn();

		int c = ending.getRow();
		int d = ending.getColumn();


		//checks if user is moving a piece onto a friendly piece ------
		if(board[c][d].piece != null){
			if(board[a][b].piece.color == board[c][d].piece.color){
				return false;
			}
		}



		//checks if the move is legal
		int maxRow = java.lang.Math.max(a, c);
		int minRow = java.lang.Math.min(a,c);

		int maxCol = java.lang.Math.max(b, d);
		int minCol = java.lang.Math.min(b, d);

		if(maxRow-minRow==1 && maxCol-minCol == 2 || maxRow-minRow== 2 && maxCol-minCol == 1){
			if(confirm == 0){
				return true;
				}else{
					//confirm == 1;
					Piece p = board[a][b].piece;
					p.moved = 2;
					board[a][b].piece = null;
					board[c][d].piece = p;
					p.placedAt=board[c][d];
					return true;
				}
		}else return false;
	}

}
