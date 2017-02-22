/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import oha.shakkiproggis.Piece.PieceT;

/**
 *
 * @author pyry
 */
public class PawnPromoChooser {
	private PromoTarget pT;
	public PawnPromoChooser() {
		this.pT = PromoTarget.QUEEN;
	}
	public void set(PromoTarget nPT) {
		this.pT = nPT;
	}	
	
	public PieceT get() {
		switch (this.pT) {
			case QUEEN:
				return PieceT.QUEEN;
			case ROOK:
				return PieceT.ROOK;
			case BISHOP:
				return PieceT.BISHOP;
			case KNIGHT:
				return PieceT.KNIGHT;
			default:
				return PieceT.QUEEN;
		}
	}
}
