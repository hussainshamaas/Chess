package pieces;

import chess.Board.pieceColor;
import game.Move;
import pieces.Piece.PieceType;

public class King extends Piece{

	public King(pieceColor color, Location location) {
		super(color, location);
		this.type = PieceType.KING;
		this.color = color;
		this.moved = 0;
		boolean underCheck = false;

	}


	public String toString()
	{
		if(this.color==pieceColor.BLACK) {
			return "bK";
		}
		else {
			return "wK";
		}
	}



	public boolean isValidMove(Location[][] board, Move move, int confirm, String choosePromotion){
		//this section grabs and names variables for use

				Location starting = move.getStart();
				Location ending = move.getEnd();

				int a = starting.getRow();
				int b = starting.getColumn();

				int c = ending.getRow();
				int d = ending.getColumn();
				//System.out.println(board[a][b].piece.underCheck);

				//checks if user is moving a piece onto a friendly piece ------

				if(board[c][d].piece != null){
					if(board[a][b].piece.color == board[c][d].piece.color){
						return false;
					}
				}
				Location tmp;
				int maxRow = java.lang.Math.max(a, c);
				int minRow = java.lang.Math.min(a,c);

				int maxCol = java.lang.Math.max(b, d);
				int minCol = java.lang.Math.min(b, d);


				if(b==4 && d==2 || b==4 && d==6 ){   //castling
					Piece kC = board[a][b].piece;
					/*if(kC.underCheck == true){
						System.out.println("under check no castle");
						return false;
					}*/
					tmp = board[a][b];
					Piece p = tmp.piece;
					if(p.moved == 2){
						return false;    //king cannot have moved
					}
					if(d ==2){ // queenside castling
						tmp = board[a][0];
						if(tmp.piece.moved == 2){
							return false;
						}
						for(int i = 1; i<3; i++){
							tmp = board [a][i];
							if(tmp.piece != null){
								return false;
							}
						}
					}
					if(d==6){  // kingside castling
						tmp = board[a][7];
						if(tmp.piece.moved == 2){
							return false;
						}
						for(int i = 5; i!= 7; i++){
							tmp = board [a][i];
							if(tmp.piece != null){
								return false;
							}
						}
					}

					if(confirm == 0){
						return true;
						}else{
							//confirm == 1;
							p = board[a][b].piece;
							p.moved = 2;
							board[c][d].piece = p;
							p.placedAt=board[c][d];
							board[a][b].piece = null;
						//	p.placedAt=board[c][d];


							//now move the rook
							if(d==2){ //queen side castling
								p = board[a][0].piece;
								p.moved = 2;
								board[a][3].piece = p;
								p.placedAt=board[a][3];
								board[a][0].piece = null;


							}
							if(d==6){
								p = board[a][7].piece;
								p.moved = 2;
								p.placedAt=board[a][5];
								board[a][5].piece = p;
								board[a][7].piece = null;

							}
							/*
							 * moved
							 * 0 = not moved
							 * 1= moved for the first time previous turn, only pawns that double push are going to have this value
							 * 2= moved
							 *  we need this for en passant
							 */
							if(p.moved == 0){
								p.moved = 2;
							}

							return true;
						}

				}

				//movement for non castling
				int rD = maxRow - minRow;
				int cD = maxCol - minCol;
				if(cD > 1){
					return false;
				}
				if(rD >1){
					return false;
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
}

