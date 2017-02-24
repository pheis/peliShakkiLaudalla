/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.gui;

import oha.gui.ChessGUI;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Rectangles for chess gui. Has colors and rectangles.
 * @author pyry
 */
public class ChessSquare extends Rectangle {
	/**
	 * Makes chess square.
	 * @param color black or white.
	 * @param x file.
	 * @param y rank.
	 */
	public ChessSquare(boolean color, int x, int y) {
	
		setWidth(ChessGUI.SQUARESIZE);
		setHeight(ChessGUI.SQUARESIZE);
		
		relocate(x * ChessGUI.SQUARESIZE, y * ChessGUI.SQUARESIZE);
		
		setFill(color ? Color.valueOf("#5577aa") : Color.valueOf("aaa"));
		
	
	}
	
	
}
