/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oha.shakkiproggis;

/**
 *
 * @author pyry
 */
public class Shakki {
	
	public static void main(String [] args) {
	
		Pelilauta lauta = new Pelilauta();
		
		lauta.asetaNappulatAloituspaikoille();
		lauta.tulostaLauta();
	
	
	}
	
}
