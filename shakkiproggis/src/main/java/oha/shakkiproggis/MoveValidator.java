package oha.shakkiproggis;

import oha.shakkiproggis.Piece.PieceT;
import java.util.ArrayList;
import java.util.*;

public class MoveValidator {

	public ArrayList<Board> gameStates;
	public int fiftyMoveCounter;
	public int piecesOnBoard;
	
	public MoveValidator(PawnPromoChooser pc1, PawnPromoChooser pc2) {
		this.gameStates = new ArrayList<>();
		Board b = new Board(pc1, pc2);
		this.gameStates.add(b);
		this.piecesOnBoard = b.piecesOnBoard();
		this.fiftyMoveCounter = 0;
		
	}
	public MoveValidator(Board board) {
		this.gameStates = new ArrayList<>();
		Board b = board;
		this.gameStates.add(b);
		this.piecesOnBoard = b.piecesOnBoard();
		this.fiftyMoveCounter = 0;
	}
	/**
	 * 
	 * @return current game state.
	 */
	public Board lastBoard() {
		return this.gameStates.get(gameStates.size() - 1);
	}
	
	/**
	 * 
	 * @return Possible moves from current game state. 
	 */
	public ArrayList<Board> listMoves() {
		Board b = this.gameStates.get(gameStates.size() - 1);
		ArrayList<Board> movesList = b.listPossibleMoves();
		return movesList;
	}
	/**
	 * 
	 * @return pairs of locations and names of current players pieces. Used by text ui.
	 */
	public HashMap<Integer, String> getMyPieceStrings() {
		HashMap<Integer, String> pieces = new HashMap<>();	
		lastBoard().my.squares()
			.forEach(x -> pieces.put(x.ordinal(), lastBoard().my.sqPtMap.get(x).toString()));
		return pieces;
	}
	
	/**
	 * 
	 * @return locations and names of other player pieces. Used by text ui.
	 */
	public HashMap<Integer, String> getEnemyPieceStrings() {
		HashMap<Integer, String> pieces = new HashMap<>();
		lastBoard().enemy.squares()
			.forEach(x -> pieces.put(x.ordinal(), lastBoard().enemy.sqPtMap.get(x).toString()));
		return pieces;
	}
	/**
	 * 
	 * @return locations and Piece types of current players pieces. 
	 */
	public HashMap<Integer, PieceT> getMyPieces() {
		HashMap<Integer, PieceT> pieces = new HashMap<>();	
		lastBoard().my.squares()
			.forEach(sq -> pieces.put(sq.ordinal(), lastBoard().my.sqPtMap.get(sq)));
		return pieces;
	}
		/**
	 * 
	 * @return locations and Piece types of other players pieces. 
	 */
	public HashMap<Integer, PieceT> getEnemyPieces() {
		HashMap<Integer, PieceT> pieces = new HashMap<>();	
		lastBoard().enemy.squares()
			.forEach(sq -> pieces.put(sq.ordinal(), lastBoard().enemy.sqPtMap.get(sq)));
		return pieces;
	}
	
	/**
	 * 
	 * @return true if current player is white. Otherwiser false.
	 */
	
	public boolean iAmWhite() {
		return lastBoard().color;
	}

	private boolean gameOver() {
		return (listMoves().isEmpty()) ? true : false;
	}
	/**
	 * 
	 * @return True if game has ended in mate. Else false.
	 */
	public boolean mate() {
		return (gameOver()) ? (lastBoard().kingInCheck()) : false;
	}
	/**
	 * 
	 * @return True if game has ended in stalemate. Else false.
	 */
	public boolean stalemate() {
		boolean t = (this.fiftyMoveCounter >= 99) ? true : false; 
		t = (gameOver()) ? !(lastBoard().kingInCheck()) : t;
		return t;
	}

	/**
	 * 
	 * @param bName for example G2F4. Former letter number pair is starting coordinate. Second one is where we want tom move a piece.
	 * @return true if move was valid. else False. If move was not valid new game state is not created.
	 */
	public boolean move(String bName) {
		Optional<Board> b = listMoves().stream()
						.filter(x -> x.toString().equals(bName))
						.findFirst();
		boolean t = b.isPresent();
		if (!t) {
			return false;
		} else {
			this.gameStates.add(b.get());
			return true;
		}
	}
	public boolean move(Move m) {
		Optional<Board> b = listMoves().stream()
						.filter(x -> x.toString().equals(m.toString()))
						.findFirst();
		boolean t = b.isPresent();
		if (!t) {
			return false;
		} else {
			this.gameStates.add(b.get());
			updateFiftyMoveCounter(b.get(), m);
			return true;
		}
	}
	
	
	private void updateFiftyMoveCounter(Board b, Move m) {
		PieceT lastMover = b.enemy.sqPtMap.get(m.second);
		if(lastMover == PieceT.BPAWN || lastMover == PieceT.WPAWN) {
			this.fiftyMoveCounter = 0;
		} else if (this.piecesOnBoard != b.piecesOnBoard()) {
			this.fiftyMoveCounter = 0;
			this.piecesOnBoard = b.piecesOnBoard();
		} else {
			this.fiftyMoveCounter++;
		}
	}
}

