/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import static oha.gui.ChessGUI.SQUARESIZE;
import oha.shakkiproggis.pieces.PieceT;

/**
 * Chess pieces for the gui. Has graphics and all.
 * @author pyry
 */
public class ChessPiece extends StackPane {
	
	
	//public chessPiece(int x, int y, Piece type, boolean isWhite) {
	/**
	 * Chess piece.
	 * @param x file
	 * @param y rank
	 * @param type what kind of a piece
	 * @param isWhite is it white
	 * @param img Image of a piece.
	 */
	public ChessPiece(int x, int y, PieceT type, boolean isWhite, Imageload img) {

		relocate(x * SQUARESIZE, y * SQUARESIZE);
		
		
		//Ellipse elli = new Ellipse(SQUARESIZE * 0.3, SQUARESIZE * 0.3);
		
		//elli.setFill(Color.valueOf("#ff0000"));
		
		ImageView iv; // = new ImageView(wPawn);
		
		if (isWhite) {	
			switch (type) {
				case KING:
					iv = new ImageView(img.wKing);
					break;
				case QUEEN:
					iv = new ImageView(img.wQueen);
					break;	
				case ROOK:
					iv = new ImageView(img.wRook);
					break;
				case BISHOP:
					iv = new ImageView(img.wBishop);
					break;
				case KNIGHT:
					iv = new ImageView(img.wKnight);
					break;
				default:
					iv = new ImageView(img.wPawn);
					break;
			}
		} else {
			switch (type) {
				case KING:
					iv = new ImageView(img.bKing);
					break;
				case QUEEN:
					iv = new ImageView(img.bQueen);
					break;	
				case ROOK:
					iv = new ImageView(img.bRook);
					break;
				case BISHOP:
					iv = new ImageView(img.bBishop);
					break;
				case KNIGHT:
					iv = new ImageView(img.bKnight);
					break;
				default:
					iv = new ImageView(img.bPawn);
					break;
			}
		
			
			
			
			
			
			
		}
		getChildren().addAll(iv);
	}
}
