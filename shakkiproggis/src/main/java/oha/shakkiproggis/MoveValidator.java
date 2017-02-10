package oha.shakkiproggis;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.*;

// tää tyyppi ei erottele onko vuoro valkoisen vai musta
// se sille se, jonka vuoro on, on "omat" ja toinen on vihu.
//

public class MoveValidator {

	public ArrayList<Board> gameStates;
	//private Board firstBoard;
	
	public MoveValidator() {
		//this.firstBoard = b;
		this.gameStates = new ArrayList<>();
		Board b = new Board();
		b.piecesToStartingFormation();
		this.gameStates.add(b);
		
	}
	
	private Board lastBoard() {
		return this.gameStates.get(gameStates.size() - 1);
	}
	
	
	public ArrayList<Board> listMoves() {
		Board b = this.gameStates.get(gameStates.size() - 1);
		ArrayList<Board> movesList = b.listPossibleMoves();
		return movesList;
	}
	
	public HashMap<Integer, String> getMyPieceStrings() {
		HashMap<Integer, String> pieces = new HashMap<>();	
		lastBoard().myPieces.keySet().stream()
			.forEach(x -> pieces.put(x.ordinal(), lastBoard().myPieces.get(x).toString()));
		return pieces;
	}
	
	public HashMap<Integer, String> getEnemyPieceStrings() {
		HashMap<Integer, String> pieces = new HashMap<>();
		lastBoard().enemyPieces.keySet().stream()
			.forEach(x -> pieces.put(x.ordinal(), lastBoard().enemyPieces.get(x).toString()));
		return pieces;
	}
	
	public HashMap<Integer, Piece> getMyPieces() {
		HashMap<Integer, Piece> pieces = new HashMap<>();	
		lastBoard().myPieces.keySet().stream()
			.forEach(x -> pieces.put(x.ordinal(), lastBoard().myPieces.get(x)));
		return pieces;
	}
	
	public HashMap<Integer, Piece> getEnemyPieces() {
		HashMap<Integer, Piece> pieces = new HashMap<>();	
		lastBoard().enemyPieces.keySet().stream()
			.forEach(x -> pieces.put(x.ordinal(), lastBoard().enemyPieces.get(x)));
		return pieces;
	}
	
	public boolean iAmWhite() {
		return lastBoard().iAmWhite;
	}

	
	public boolean movePiece(String bName) {
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
	 
	 
	
	



	/*
	private int omanKinginSijainti(){
			this.omat.stream()
					.filter (x -> 
	}
	*/

	/*
	private boolean onksKingi(int koordinaatti) {
			boolean t;
			t = (Math.abs(this.nytLauta.getValueOf
	}*/


}
