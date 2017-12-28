package pieces;
import chess.Board.pieceColor;
import game.Move;
import pieces.Piece.PieceType;

public class Rook extends Piece{

	public Rook(pieceColor color, Location PlacedAt) {
		super(color, PlacedAt);
		this.type = PieceType.ROOK;
		this.color = color;
		this.moved = 0;
		// TODO Auto-generated constructor stub
	}




	public boolean isValidMove(Location[][] board, Move move, int confirm, String choosePromotion){
		//this section grabs and names variables for use
		Location starting = move.getStart();
		Location ending = move.getEnd();

		int a = starting.getRow();
		int b = starting.getColumn();

		int c = ending.getRow();
		int d = ending.getColumn();

		//this grabs piece color of the starting and ending areas
		if(board[c][d].piece != null){
			if(board[a][b].piece.color == board[c][d].piece.color){
				return false;
			}
		}

		if(a == c){

			int max = java.lang.Math.max(b, d);
			int min = java.lang.Math.min(b,d);
			for(int i = min+1; i < max-1 ; i++){
				Location z = board[a][i];
					if(z.piece != null){
						return false;
					}
				//collision logic

			}
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

		}
		//column traverse
		if(b == d){
			int max = java.lang.Math.max(a, c);
			int min = java.lang.Math.min(a,c);
			for(int i = min+1; i < max-1 ; i++){
				Location z = board[b][i];
					if(z.piece != null){
						return false;
					}
				//collision logic

			}
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

		}

		return false;
	}


	public String toString()
	{
		if(this.color==pieceColor.BLACK) return "bR";
		else return "wR";
	}
}
