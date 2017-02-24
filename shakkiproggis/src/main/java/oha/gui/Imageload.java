/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.gui;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import static oha.gui.ChessGUI.SQUARESIZE;
import oha.shakkiproggis.Main;

/**
 * loads nice pics for pieces. 
 * @author pyry
 */
public class Imageload {
	
	
	//InputStream is = getClass().getClassLoader().getResourceAsStream("images/munkuva.jpg");

	//Ja lukea streamista BufferedImageksi:

	//BufferedImage bf = ImageIO.read(is);
	
	

	
	//Image fxImgDirect = new Image(wrook.openStream());
	
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
	/**
	 * load images.
	 */
	public Imageload() {
		URL wrook = Main.class.getResource(
                                     "/icons/wRook.png");
		URL wking = Main.class.getResource(
                                     "/icons/wKing.png");
		URL wpawn = Main.class.getResource(
                                     "/icons/wPawn.png");
		URL wbishop = Main.class.getResource(
                                     "/icons/wBishop.png");
		URL wknight = Main.class.getResource(
                                     "/icons/wKnight.png");
		URL wqueen = Main.class.getResource(
                                     "/icons/wQueen.png");
		URL brook = Main.class.getResource(
                                     "/icons/bRook.png");
		URL bking = Main.class.getResource(
                                     "/icons/bKing.png");
		URL bpawn = Main.class.getResource(
                                     "/icons/bPawn.png");
		URL bbishop = Main.class.getResource(
                                     "/icons/bBishop.png");
		URL bknight = Main.class.getResource(
                                     "/icons/bKnight.png");
		URL bqueen = Main.class.getResource(
                                     "/icons/bQueen.png");
		
		this.wRook = new Image(wrook.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.wKing = new Image(wking.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.wPawn = new Image(wpawn.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.wBishop = new Image(wbishop.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.wKnight = new Image(wknight.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.wQueen = new Image(wqueen.toString(), SQUARESIZE, SQUARESIZE, false, false);
	
		this.bRook = new Image(brook.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.bKing = new Image(bking.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.bPawn = new Image(bpawn.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.bBishop = new Image(bbishop.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.bKnight = new Image(bknight.toString(), SQUARESIZE, SQUARESIZE, false, false);
		this.bQueen = new Image(bqueen.toString(), SQUARESIZE, SQUARESIZE, false, false);
	}
	
	/*
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
	

	*/
}
