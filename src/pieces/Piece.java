package pieces;

import chess.Board.pieceColor;
import game.Move;

public abstract class Piece {
	public static enum PieceType
	{
		PAWN, ROOK, BISHOP, KNIGHT, QUEEN, KING
	}
	//Acts as a boolean to check the color;
	public PieceType type;
	public pieceColor color;
	public Location placedAt;
	public int moved ;
//	public boolean underCheck;



	public Piece(pieceColor color, Location PlacedAt) {
        this.color = color;
        this.placedAt = PlacedAt;
    }
	//confirm, pass 0 to return true or false, pass 1 to return true or false and also do the move
	public abstract boolean isValidMove(Location[][] board, Move move, int confirm, String choosePromotion);


}
