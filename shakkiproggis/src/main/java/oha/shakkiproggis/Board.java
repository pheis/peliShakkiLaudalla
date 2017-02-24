package oha.shakkiproggis;


import java.util.*;
/**
 * This represents a chess board. Has two piecesets on it. Enemys and mine. This is immutable object
 * A new board is always generated when a move is done.
 * During generations my pieces turn to enemy pieces and enemy pieces turn to my pieces.
 * The 'me' of a Board if always the player whose turn it is.
 * @author pyry
 */
public class Board {
	public final PieceGroup my;
	public final PieceGroup enemy;
	/**
	 * Tells if curret player is white.
	 * if true current player is white.
	 * else current player is black.
	 */

	public final boolean color;
	
	public final Move lastMove;
	/**
	 * This is the default constructor. 
	 * @param pc1 pawn promo target chooser for white player.
	 * @param pc2 pawn promo target chooser for black player.
	 */
	public Board(PawnPromoChooser pc1, PawnPromoChooser pc2) {
		this.my = new PieceGroup(true, pc1);
		this.enemy = new PieceGroup(false, pc2);
		this.color = true;
		this.lastMove = new Move(Square.A1, Square.A2);
	}
	/**
	 * This is the constructor that is used by the object itself to create more Board objects.
	 * @param my my pieces.
	 * @param enemy enemy pieces.
	 * @param color color of my.
	 * @param lMove last move that has been done on the game.
	 */
	public Board(PieceGroup my, PieceGroup enemy, boolean color, Move lMove) {
		this.my = my;
		this.enemy = enemy;
		this.color = color;
		this.lastMove = lMove;
	}
	// If enemy king is in check when it is my turn something illegal has happened. Else Everything OK.
	private boolean possibleFuture() {
		return !(enemy.kingInCheck(my));
	}
	/**
	 * Makes a new board.
	 * @param a starting square.
	 * @param b ending square.
	 * @return Maybe a new board. Maybe not. If the move was possible then yes, a new board.
	 */
	public Optional<Board> move(Square a, Square b) {
		PieceGroup futureEnemy = my.moveMyPiece(a, b);
		PieceGroup futureMe = enemy.eatOneOfMyPieces(a, b, this.my);
		Move futLstMove = new Move(a, b);
		Board futureBoard = new Board(futureMe, futureEnemy, !(this.color), futLstMove);
		return (futureBoard.possibleFuture()) ? Optional.of(futureBoard) : Optional.empty();
	}
	/**
	 * lists all possible moves.
	 * @return Arraylist of all possible gamestates after a legal move is done
	 */
	public ArrayList<Board> listPossibleMoves () {
		ArrayList<Board> boards = new ArrayList<>();
		my.squares()
			.flatMap(a -> my.moves(a, enemy).map(b -> move(a, b)))
			.filter(b -> b.isPresent())
			.map(b -> b.get())
			.forEach(b -> boards.add(b));
		return boards;
	}
	/**
	 * Tells how many pieces are still on the board.
	 * @return how many pieces are left.
	 */
	public int piecesOnBoard() {
		return my.sqPtMap.size() + enemy.sqPtMap.size();
	}
	
	
	/**
	 * Tells if current players King is in check.
	 * @return True or false.
	 */
	public boolean kingInCheck() {
		return my.kingInCheck(enemy);
	}
	/**
	 * Tells about the last move.
	 * @return Last move that was done. 
	 */
	
	@Override
	public String toString() {
		return this.lastMove.toString();
	}
}
