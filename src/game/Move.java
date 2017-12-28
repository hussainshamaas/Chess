package game;
/**
 * @author Wilson Chan
 * @author Shamaas Hussain
 * Move class makes it easier to visualize start and end. 
 * 
 */
import pieces.Location;
import pieces.Piece;

public class Move {
	Location start;
	Location end;
	Piece piece;
	String choosePromotion;

	public Move(Location start, Location end, Piece p , String choosePromotion){
		this.start = start;
		this.end=end;
		this.piece = p;
		this.choosePromotion = choosePromotion;
	}

	public Location getStart() {
		return this.start;
	}

	public Location getEnd() {
		return this.end;
	}

	public void setStart(Location start) {
		this.start=start;
	}


	public void setEnd(Location end) {
		this.start=end;
	}

	public Piece getPiece() {
		return this.piece;
	}

}


