package pieces;

import chess.Board.pieceColor;
import game.Move;
public class Bishop extends Piece{

	public Bishop(pieceColor color, Location PlacedAt) {
		super(color, PlacedAt);
		this.type = PieceType.BISHOP;
		this.color = color;
		this.moved = 0;
		// TODO Auto-generated constructor stub
	}
	public boolean checkMove(Location[][] board, Location start, Location end, int direction){
		//dummy check for oob and common sense moves

		// 1, 3, 6, 8
		switch(direction){
		case 1:
			if(board[start.getRow() - 1][start.getColumn() - 1].piece != null){
				if(start.getRow() - 1 == end.getRow() && start.getColumn() - 1 == end.getColumn()){
					return true;
				}
				else{
					return false;
				}

			}
			else{
				if(start.getRow() - 1 == end.getRow() && start.getColumn() - 1 == end.getColumn()){
					return true;
				}
				else{
					return checkMove(board, board[start.getRow() - 1][start.getColumn() - 1], end, direction);
				}
			}

		case 3:

			if(board[start.getRow() - 1][start.getColumn() + 1].piece != null){
				if(start.getRow() - 1 == end.getRow() && start.getColumn() + 1 == end.getColumn()){
					return true;
				}
				else{
					return false;
				}

			}
			else{
				if(start.getRow() - 1 == end.getRow() && start.getColumn() + 1 == end.getColumn()){
					return true;
				}
				else{
					return checkMove(board, board[start.getRow() - 1][start.getColumn() + 1], end, direction);
				}
			}
		case 6:
			if(board[start.getRow() + 1][start.getColumn() - 1].piece != null){
				if(start.getRow() + 1 == end.getRow() && start.getColumn() - 1 == end.getColumn()){
					return true;
				}
				else{
					return false;
				}

			}
			else{
				if(start.getRow() + 1 == end.getRow() && start.getColumn() - 1 == end.getColumn()){
					return true;
				}
				else{
					return checkMove(board, board[start.getRow() + 1][start.getColumn() - 1], end, direction);
				}
			}
		case 8:
			if(board[start.getRow() + 1][start.getColumn() + 1].piece != null){
				if(start.getRow() + 1 == end.getRow() && start.getColumn() + 1 == end.getColumn()){
					return true;
				}
				else{
					return false;
				}

			}
			else{
				if(start.getRow() + 1 == end.getRow() && start.getColumn() + 1 == end.getColumn()){
					return true;
				}
				else{
					return checkMove(board, board[start.getRow() + 1][start.getColumn() + 1], end, direction);
				}
			}
			default: return false;
		}

	}
	public boolean isValidMove(Location[][] board, Move move, int confirm, String choosePromotion){
		//Location[][] board, Location start, Location end, int direction
		//linearity or
		//Math.abs(dest.letter - piece.letter) == Math.abs(dest.y - piece.y
		Location starting = move.getStart();
		Location ending = move.getEnd();

		int a = starting.getRow();
		int b = starting.getColumn();

		int c = ending.getRow();
		int d = ending.getColumn();
		if(Math.abs(d-b)==Math.abs(c-a)){
			int direction = 0;
			//case 1
			if(c<a && d<b){
				direction = 1;
			}
			// 3
			if(c<a && d>b){
				direction = 3;
			}
			//6
			if(c>a && d<b){
				direction = 6;
			}
			//8
			if(c>a && d>b){
				direction = 8;
			}
			boolean valid =  checkMove(board, starting, ending, direction);
			if(valid== true){
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
		//System.out.println("invalid move");
		return false;







	}

	//this prints
	public String toString()
	{
		if(this.color==pieceColor.BLACK) {
			return "bB";
		}
		else {
			return "wB";
		}
	}
}

