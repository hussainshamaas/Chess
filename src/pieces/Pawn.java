package pieces;
import chess.Board.pieceColor;
import game.Move;
import pieces.Piece.PieceType;

public class Pawn extends Piece {

	public Pawn(pieceColor color, Location PlacedAt) {
		super(color, PlacedAt);
		this.type = PieceType.PAWN;
		this.color = color;
		this.moved = 0;
		// TODO Auto-generated constructor stub
	}


	public String toString() {
		if (this.color==pieceColor.BLACK)
			return "bp";
		else
			return "wp";
	}

	//rowEnd and colEnd are just the where the pawn ends the turn getting promoted
	public void promotion(int rowEnd, int colEnd, Location[][] board, String p){
		//black starts on 0, white starts on 7

		board[rowEnd][colEnd].piece = null;
		pieceColor b = pieceColor.WHITE;
		if(rowEnd == 7){
			b = pieceColor.BLACK;
		}
		System.out.println(p);
		switch(p){
		case "n": board[rowEnd][colEnd].placePiece(new Knight(b, board[rowEnd][colEnd]));
		break;
		case "b": board[rowEnd][colEnd].placePiece(new Bishop(b, board[rowEnd][colEnd]));
		break;
		case "q": board[rowEnd][colEnd].placePiece(new Queen(b, board[rowEnd][colEnd]));
		break;
		case "r": board[rowEnd][colEnd].placePiece(new Rook(b, board[rowEnd][colEnd]));
		break;
		default: throw new IllegalArgumentException();
		}

	}



	public boolean isValidMove(Location[][] board, Move move, int confirm, String choosePromotion){


				//System.out.println(choosePromotion);
			//System.out.println("isValid called");
		//this section grabs and names variables for use
				Location starting = move.getStart();
				Location ending = move.getEnd();


				int a = starting.getRow();
				int b = starting.getColumn();

				int c = ending.getRow();
				int d = ending.getColumn();
				pieceColor startColor = board[a][b].piece.color;
				//makes sure pawn isn't trying to take a friendly piece
				if(board[c][d].piece != null){
					if(board[a][b].piece.color == board[c][d].piece.color){
						return false;
					}
				}
				Piece p = board[a][b].piece;
				int maxRow = java.lang.Math.max(a, c);
				int minRow = java.lang.Math.min(a,c);

				int maxCol = java.lang.Math.max(b, d);
				int minCol = java.lang.Math.min(b, d);
				int columnDistance = maxCol-minCol;
				int rowDistance = maxRow - minRow;
				//System.out.println("minCol"+ ' ' + minCol);

				//ensure pawns move unidirectionally
				if(p.color == pieceColor.BLACK){

					if(c<a){
						return false;
					}
				}
				if(p.color == pieceColor.WHITE){

					if(a<c){
						return false;
					}
				}


				//check if pawn is moving too far
				if(rowDistance < 3 ){
				//	System.out.println("Row Distance under 3");

					if(rowDistance == 2){
						if(p.moved == 1 || p.moved == 2){ //can only go two if you haven't moved yet
							return false;
						}
						if(b !=d){   //can change rows, not columns if you're moving two squares
							return false;
						}

						if(board[minRow+1][d].piece != null){

							return false;  //can't jump through pieces
						}

						if(board[c][d].piece != null){   //can't take going forwards.
							return false;
						}
						if(confirm == 0){
							return true;
							}else{
								//confirm == 1;
								p = board[a][b].piece;
								p.moved = 1; //THIS ALLOWS IT TO BE TAKEN EN PASSANT
								board[a][b].piece = null;
								board[c][d].piece = p;
								p.placedAt=board[c][d];
								return true;
							}

					}

					if(rowDistance == 1){
				//		System.out.println("Row distance of 1");
						if(columnDistance > 1){
					//		System.out.println("colDist > 1");
							return false;
						}
						//take diagonal below
						if(columnDistance == 1){
						//	System.out.println("column distance of 1");



							//check for taking en passant
							if(board[c][d].piece == null){
							//	System.out.println("en passant logic called");
								//split black/white
								if(startColor == pieceColor.BLACK){
									if(c==5){           //only row you can en passant to as black
										if(board[4][d].piece == null){    //need a piece to take en passant
											return false;
										}
										if(board[4][d].piece.moved != 1){//ensures you can only take pawns that double pushed last turn
											return false;
										}
										if(confirm == 0){
											return true;
										}else{//return == 1
										//en passant is legal at this point
										p = board[a][b].piece;
										board[a][b].piece = null;
										board[c][d].piece =p;
										board[4][d].piece = null;//opposing square was captured en passant
										p.placedAt=board[c][d];
										return true;
										}
									}

								}
								if(startColor == pieceColor.WHITE){
									if(c==2){           //only row you can en passant to as white
										if(board[3][d].piece == null){    //need a piece to take en passant
											return false;
										}
										if(board[3][d].piece.moved != 1){//ensures you can only take pawns that double pushed last turn
											return false;
										}
										//en passant is legal at this point
										if(confirm == 0){
											return true;
										}else{//return == 1
										//en passant is legal at this point
										p = board[a][b].piece;
										board[a][b].piece = null;
										board[c][d].piece =p;
										board[3][d].piece = null;//opposing square was captured en passant
										p.placedAt=board[c][d];
										return true;
										}
									}

								}


							}
							//end en passant logic here
								if(board[c][d].piece != null){
							//		System.out.println("1x1 move");
									if(confirm == 0){
									//	System.out.println("passed 0");
										return true;
										}else{
											//confirm == 1;
											p = board[a][b].piece;
											board[a][b].piece = null;
											board[c][d].piece = p;
											if(p.moved != 2){
											p.moved = 2;
										//	System.out.println("take diagonal");
											}
											p.placedAt=board[c][d];
												if(c == 0 || c == 7){
										//			System.out.println("attempting promotion");
													if(choosePromotion == ""){
														choosePromotion = "q";
													}
													promotion(c, d, board, choosePromotion);
													p = board[c][d].piece;
													p.placedAt = board[c][d];
											}
										return true;
									}


									//handle promotion

								}else{
									return false;}
							}
						//try to single push forward
						if(columnDistance == 0){
							if(board[c][d].piece != null){
								return false;
							}
							if(confirm == 0){
								return true;
								}else{
									//confirm == 1;
									p = board[a][b].piece;
									p.moved = 2;
									board[a][b].piece = null;
									board[c][d].piece = p;
									p.placedAt=board[c][d];
									/*
									 * moved
									 * 0 = not moved
									 * 1= moved for the first time previous turn, only pawns that double push are going to have this value
									 * 2= moved
									 *  we need this for en passant
									 */
									//handle promotion
									if(c == 0 || c == 7){
										promotion(c, d, board, choosePromotion);
										p = board[c][d].piece;
										p.placedAt = board[c][d];
									}
									return true;
								}
						}
					}

				}else{
					//System.out.println("uh r>3");
					return false;
				}
		return true;  //shouldn't get here at all anyways
	}



}
