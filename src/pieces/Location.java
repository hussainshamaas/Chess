package pieces;
import chess.Board.pieceColor;

public class Location {
	private int row;
    private int column;
    private pieceColor color;
	public Piece piece;



    public Location(int x, int y, pieceColor color) {
    	//x represents columns a through h
        this.row = x;
        //y represents columns 8 through 1
        this.column = y;
        this.color= color;
        this.piece=null;
    }

    public void placePiece(Piece piece){
		this.piece = piece;
	}


    public String toString(){
		if(piece != null)
		{
			return piece.toString();
		}
		else if (color== pieceColor.BLACK){
			return "##";
		}
		else {
			return "  ";
		}
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
