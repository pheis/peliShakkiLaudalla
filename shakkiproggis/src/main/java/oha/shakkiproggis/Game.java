/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

import java.util.ArrayList;
import oha.ai.AI;

/**
 *
 * @author pyry
 */
public class Game {
	private Player white;
	private Player black;
	private final PawnPromoChooser pc1 = new PawnPromoChooser();
	private final PawnPromoChooser pc2 = new PawnPromoChooser();
	private final MoveValidator mv = new MoveValidator(pc1, pc2);
	
	public Game(Player w, Player b, UI) {
		this.white = w;
		this.black = b;
		
	}
	
	public ArrayList<Move> listMoves() {
		return this.mv.listMoves();
	}
	
	public void playAIvsHuman() {
	
		this.white = new AI(this.mv, this.pc1);
		this.black =
		
	}
}
