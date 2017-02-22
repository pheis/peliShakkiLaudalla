/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.gui;

import javafx.scene.image.Image;
import static oha.gui.ChessGUI.SQUARESIZE;

/**
 *
 * @author pyry
 */
public class Imageload {
	public Image wRook;
	public Image wKing;
	public Image wPawn;
	public Image wBishop;
	public Image wKnight;
	public Image wQueen;
	
	public Image bRook;
	public Image bKing;
	public Image bPawn;
	public Image bBishop;
	public Image bKnight;
	public Image bQueen;
	public Imageload() {
		this.wRook = new Image("file:icons/wRook.png", SQUARESIZE, SQUARESIZE, false, false);
		this.wKing = new Image("file:icons/wKing.png", SQUARESIZE, SQUARESIZE, false, false);
		this.wPawn = new Image("file:icons/wPawn.png", SQUARESIZE, SQUARESIZE, false, false);
		this.wBishop = new Image("file:icons/wBishop.png", SQUARESIZE, SQUARESIZE, false, false);
		this.wKnight = new Image("file:icons/wKnight.png", SQUARESIZE, SQUARESIZE, false, false);
		this.wQueen = new Image("file:icons/wQueen.png", SQUARESIZE, SQUARESIZE, false, false);
	
		this.bRook = new Image("file:icons/bRook.png", SQUARESIZE, SQUARESIZE, false, false);
		this.bKing = new Image("file:icons/bKing.png", SQUARESIZE, SQUARESIZE, false, false);
		this.bPawn = new Image("file:icons/bPawn.png", SQUARESIZE, SQUARESIZE, false, false);
		this.bBishop = new Image("file:icons/bBishop.png", SQUARESIZE, SQUARESIZE, false, false);
		this.bKnight = new Image("file:icons/bKnight.png", SQUARESIZE, SQUARESIZE, false, false);
		this.bQueen = new Image("file:icons/bQueen.png", SQUARESIZE, SQUARESIZE, false, false);
	}
	

	
}
