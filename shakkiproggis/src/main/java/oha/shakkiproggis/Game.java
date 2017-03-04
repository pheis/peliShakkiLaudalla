/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import oha.ai.AI;
import oha.shakkiproggis.PawnPromoChooser;
/**
 * abstraction layer To create different kind of games with MoveValidator.
 * @author pyry
 */
public class Game {
	public final MoveValidator mv;
	public final AI ai;
	public PawnPromoChooser ppc1 = new PawnPromoChooser();
	public PawnPromoChooser ppc2 = new PawnPromoChooser();
	boolean whiteIsHuman;
	boolean aiPlays;
	boolean wTurn;
	/**
	 * Constructs a game.
	 * @param whiteIsHuman is white player human player.
	 * @param aiPlays does Ai play. If false then human vs human match.
	 */
	public Game(boolean whiteIsHuman, boolean aiPlays) {
		//	this.ppc1 = new PawnPromoChooser();
		//	this.ppc2 = new PawnPromoChooser();
	//		this.ui = ui;
		this.mv = new MoveValidator(ppc1, ppc2);
		this.whiteIsHuman = whiteIsHuman;
		this.aiPlays = aiPlays;
		this.wTurn = true;
		if (whiteIsHuman) {
			this.ai = new AI(this.mv, this.ppc2);
		} else {
			this.ai = new AI(this.mv, this.ppc1);
			if (aiPlays) {
				wTurn = !this.wTurn;
				ai.move();
			}
		}
			
	}
	/**
	 * set pawn promo target to queen.
	 */
	public void pptToQueen() {
		if (wTurn) {
			this.ppc1.set(PromoTarget.QUEEN);
		}
		if (!wTurn)  {
			this.ppc2.set(PromoTarget.QUEEN);
		}
	}
	/**
	 * set pawn promo target to rook.
	 */
	public void pptToRook() {
		if (wTurn) {
			this.ppc1.set(PromoTarget.ROOK);
		}
		if (!wTurn) {
			this.ppc2.set(PromoTarget.ROOK);
		}
	}
	/**
	 * set pawn promo target to bishop.
	 */
	public void pptToBishop() {
		if (wTurn) {
			this.ppc1.set(PromoTarget.BISHOP);
		}
		if (!wTurn) {
			this.ppc2.set(PromoTarget.BISHOP);
		}
	}
	/**
	 * set pawn promo target to knight.
	 */
	public void pptToKnight() {
		if (wTurn) {
			this.ppc1.set(PromoTarget.KNIGHT);
		}
		if (!wTurn) {
			this.ppc2.set(PromoTarget.KNIGHT);
		}
	}
	/**
	 *  Tries to make a move.
	 * @param m move that we want to make.
	 * @return if it was possible to make the move that we wanted to make.
	 */
	public boolean move(Move m) {
		boolean t;
		t = this.mv.move(m);
		if (t) {
			this.wTurn = !this.wTurn;
			//System.out.println(this.wTurn);
			return t;
		} else {
			return false;
		}
	}
	/**
	 * Check if mate.
	 * @return true if mate.
	 */
	public boolean mate() {
		return this.mv.mate();
	}
	/**
	 * Check if draw.
	 * @return true if draw
	 */
	public boolean draw() {
		return this.mv.draw();
	}
	/**
	 * Ai moves some piece.
	 * if Ai does not play, does nothing. bit hacky. not good.
	 */
	public void aiMove() {
		if (this.aiPlays) {
			this.wTurn = !this.wTurn;
			this.ai.move();
		} 
	}
}
