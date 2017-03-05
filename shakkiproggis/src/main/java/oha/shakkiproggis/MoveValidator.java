package oha.shakkiproggis;

import oha.shakkiproggis.pieces.PieceT;
import java.util.ArrayList;
import java.util.*;
/**
 * This class validates moves. Tells if they are ok or not. It also tells where the pieces are on the board.
 * @author pyry
 */

public class MoveValidator {
	public ArrayList<Board> gameStates;
	public int fiftyMoveCounter;
	public int piecesOnBoard;
	/**
	 * Makes new MoveValidator. This is the standard constructor
	 * @param pc1 pawnpromotarget chooser for player 1
	 * @param pc2 pawnpromotarget chooser for player 2
	 */
	public MoveValidator(PawnPromoChooser pc1, PawnPromoChooser pc2) {
		this.gameStates = new ArrayList<>();
		Board b = new Board(pc1, pc2);
		this.gameStates.add(b);
		this.piecesOnBoard = b.piecesOnBoard();
		this.fiftyMoveCounter = 0;
	}
	/**
	 * Makes new MoveValidator with a spesific board. used for testing.
	 * @param board Board first board.
	 */
	public MoveValidator(Board board) {
		this.gameStates = new ArrayList<>();
		Board b = board;
		this.gameStates.add(b);
		this.piecesOnBoard = b.piecesOnBoard();
		this.fiftyMoveCounter = 0;
	}
	/**
	 * Current game state. 
	 * @return current game state.
	 */
	public Board lastBoard() {
		return this.gameStates.get(gameStates.size() - 1);
	}
	
	/**
	 * Possible move from the current situation.
	 * @return Possible moves from current game state. 
	 */
	public ArrayList<Board> listMoves() {
		Board b = this.gameStates.get(gameStates.size() - 1);
		ArrayList<Board> movesList = b.listPossibleMoves();
		return movesList;
	}
	/**
	 * locations and piece type of current players pieces.
	 * @return locations and Piece types of current players pieces. 
	 */
	public HashMap<Integer, PieceT> getMyPieces() {
		HashMap<Integer, PieceT> pieces = new HashMap<>();	
		lastBoard().my.squares()
			.forEach(sq -> pieces.put(sq.ordinal(), lastBoard().my.sqPtMap.get(sq)));
		return pieces;
	}
	/**
	 * Enemy pieces. Enemy is the one that's turn it is not.
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
	 * Am I white or black.
	 * @return true if current player is white. Otherwiser false.
	 */
	
	public boolean iAmWhite() {
		return lastBoard().color;
	}

	private boolean gameOver() {
		return (listMoves().isEmpty()) ? true : false;
	}
	/**
	 * Is it a Mate.
	 * @return True if game has ended in mate. Else false.
	 */
	public boolean mate() {
		return (gameOver()) ? (lastBoard().kingInCheck()) : false;
	}
	/**
	 * Is it a draw.
	 * @return True if game has ended in stalemate. Else false.
	 */
	public boolean draw() {
		boolean t = (this.fiftyMoveCounter >= 99); 
		t = (gameOver()) ? !(lastBoard().kingInCheck()) : t;
		t = !t ? repetition() : t;
		return t;
	}

	/**
	 * Move the piece.
	 * @param bName for example G2F4. Former letter number pair is the starting coordinate. The second one is where we want to move a piece.
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
	/**
	 * Move the piece.
	 * @param m the move that you want to make.
	 * @return True if and only if the move was possible to do.
	 */
	public boolean move(Move m) {
		Optional<Board> b = listMoves().stream()
						.filter(x -> x.toString().equals(m.toString()))
						.findFirst();
		boolean t = b.isPresent() && !draw();
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
		if (lastMover == PieceT.BPAWN || lastMover == PieceT.WPAWN) {
			this.fiftyMoveCounter = 0;
		} else if (this.piecesOnBoard != b.piecesOnBoard()) {
			this.fiftyMoveCounter = 0;
			this.piecesOnBoard = b.piecesOnBoard();
		} else {
			this.fiftyMoveCounter++;
		}
	}
	
	private boolean repetition() {
		int lim = this.gameStates.size();
		for (int i = lim - this.fiftyMoveCounter - 1; i < lim; i++) {
			for (int j = i + 1; j < lim; j++) {
				for (int k = j + 1; k < lim; k++) {
					Board a = this.gameStates.get(i);
					Board b = this.gameStates.get(j);
					Board c = this.gameStates.get(k);
					if (a.equals(b) && b.equals(c)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
}

