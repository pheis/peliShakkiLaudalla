package oha.shakkiproggis;


import java.util.*;

public class Board {
	public final PieceGroup my;
	public final PieceGroup enemy;
	/**
	 * Tells if curret player is white.
	 * if true -> current player is white.
	 * else -> current player is black.
	 */

	public final boolean color;
	
	public final Move lastMove;
	
	public Board(PawnPromoChooser pc1, PawnPromoChooser pc2) {
		this.my = new PieceGroup(true, pc1);
		this.enemy = new PieceGroup(false, pc2);
		this.color = true;
		this.lastMove = new Move(Square.A1, Square.A2);
	}
	public Board(PieceGroup my, PieceGroup enemy, boolean color, Move lMove) {
		this.my = my;
		this.enemy = enemy;
		this.color = color;
		this.lastMove = lMove;
	}
	// If enemy king is in check when it is my turn something illegal has happened. Else -> Everything OK.
	private boolean possibleFuture() {
		return !(enemy.kingInCheck(my));
	}
	
	public Optional<Board> move(Square a, Square b) {
		PieceGroup futureEnemy = my.moveMyPiece(a, b);
		PieceGroup futureMe = enemy.eatOneOfMyPieces(a, b, this.my);
		Move futLstMove = new Move(a, b);
		Board futureBoard = new Board(futureMe, futureEnemy, !(this.color), futLstMove);
		return (futureBoard.possibleFuture()) ? Optional.of(futureBoard) : Optional.empty();
	}
	/**
	 * 
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
	
	public int piecesOnBoard() {
		return my.sqPtMap.size() + enemy.sqPtMap.size();
	}
	
	
	/**
	 * 
	 * @return Tells if current players King is in check.
	 */
	public boolean kingInCheck() {
		return my.kingInCheck(enemy);
	}
	/**
	 * 
	 * @return Last move that was done. 
	 */
	
	@Override
	public String toString() {
		return this.lastMove.toString();
	}
}
