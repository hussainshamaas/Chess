package pieces;

import chess.Board.pieceColor;
import game.Move;
import pieces.Piece.PieceType;


public class Queen extends Piece{

	public Queen(pieceColor color, Location PlacedAt) {
		super(color, PlacedAt);
		this.type = PieceType.QUEEN;
		this.color = color;
		this.moved = 0;
		// TODO Auto-generated constructor stub
	}


	public String toString()
	{
		if(this.color==pieceColor.BLACK) {
			return "bQ";
		}
		else {
			return "wQ";
		}
	}






	public boolean isValidMove(Location[][] board, Move move, int confirm, String choosePromotion){
		//board[i][0].placePiece(new Rook(pieceColor.BLACK, board[i][0]));
		Location starting = move.getStart();
		Location ending = move.getEnd();

		int a = starting.getRow();
		int b = starting.getColumn();

		int c = ending.getRow();
		int d = ending.getColumn();

		//create dummy board, check if q can do either r or b moves.
		Location[][] snapShot = board;
		//create rook
		Piece r= new Rook(snapShot[a][b].piece.color, snapShot[a][b]);
		boolean attemptR = r.isValidMove(snapShot,move,0,"");

		Piece bish = new Bishop(snapShot[a][b].piece.color, snapShot[a][b]);
		boolean attemptB = bish.isValidMove(snapShot,move,0,"");

		if(attemptR || attemptB){
			if(confirm == 0){
				return true;
				}else{
					//confirm == 1;
					Piece p = board[a][b].piece;
					p.moved = 2;
					board[a][b].piece = null;
					board[c][d].piece = p;
					p.placedAt=board[c][d];
					//System.out.println("returned true");
					return true;
				}
		}
		return false;
	}


}
