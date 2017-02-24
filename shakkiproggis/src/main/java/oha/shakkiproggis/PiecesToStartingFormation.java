/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import java.util.EnumMap;
import oha.shakkiproggis.pieces.Bishop;
import oha.shakkiproggis.pieces.BlackPawn;
import oha.shakkiproggis.pieces.King;
import oha.shakkiproggis.pieces.Knight;
import oha.shakkiproggis.pieces.PieceT;
import oha.shakkiproggis.pieces.Queen;
import oha.shakkiproggis.pieces.Rook;
import oha.shakkiproggis.pieces.WhitePawn;
import oha.shakkiproggis.pieces.PieceObj;


/**
 * Provides methods to initialize a new start state for a chess game.
 * Only needed by PieceGroup. This interface exists to make piecegroup's code more readable.
 * @author pyry
 */
public interface PiecesToStartingFormation {
	/**
	 * Generates the squares to pieces map.
	 * @param sqPtMap squares to piece enum map. Squares are piece locations.
	 * @param white if pieces are white true. if they are black then false.
	 */
	
	default void sqPtMapGen(EnumMap<Square, PieceT> sqPtMap, boolean white) {
		Square[] sqA = (white) ? Square.firstRank() : Square.eightRank();
		sqPtMap.put(sqA[0], PieceT.ROOK);
		sqPtMap.put(sqA[1], PieceT.KNIGHT);
		sqPtMap.put(sqA[2], PieceT.BISHOP);
		sqPtMap.put(sqA[3], PieceT.QUEEN);
		sqPtMap.put(sqA[4], PieceT.KING);
		sqPtMap.put(sqA[5], PieceT.BISHOP);
		sqPtMap.put(sqA[6], PieceT.KNIGHT);
		sqPtMap.put(sqA[7], PieceT.ROOK);
		if (white) {
			wPawnStartForm(sqPtMap);
		} else {
			bPawnStartForm(sqPtMap);
		}
	}
	/**
	 * Puts white pawns to their correct place on the begining of the game.
	 * @param sqPtMap Squares to Pieces enum map.  Squares are piece locations.
	 */
	default void wPawnStartForm(EnumMap<Square, PieceT> sqPtMap) {
		Square[] sqA = Square.secondRank();
		for (Square s : sqA) {
			sqPtMap.put(s, PieceT.WPAWN);
		}
	}
	/**
	 * Puts black pawn to starting formation on the befginning of the game.
	 * @param sqPtMap Squares to pieces enum map. Squares are piece locations. 
	 */
	default void bPawnStartForm(EnumMap<Square, PieceT> sqPtMap) {
		Square[] sqA = Square.seventhRank();
		for (Square s : sqA) {
			sqPtMap.put(s, PieceT.BPAWN);
		}
	}
	/**
	 * Generates the piece type to piece object map.
	 * @param pTpMap piece enum to piece object map.
	 * @param white true if pieces are white. else false.
	 */
	default void pTpMapGen(EnumMap<PieceT, PieceObj> pTpMap, boolean white) {
		pTpMap.put(PieceT.KING, new King());
		pTpMap.put(PieceT.QUEEN, new Queen());
		pTpMap.put(PieceT.ROOK, new Rook());
		pTpMap.put(PieceT.BISHOP, new Bishop());
		pTpMap.put(PieceT.KNIGHT, new Knight());
		if (white) {
			pTpMap.put(PieceT.WPAWN, new WhitePawn());
		} else {
			pTpMap.put(PieceT.BPAWN, new BlackPawn());
		}
	}
}
