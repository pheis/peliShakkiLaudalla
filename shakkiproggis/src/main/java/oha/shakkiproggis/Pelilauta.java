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
public class Pelilauta implements Cloneable{
	private int[] lauta;

	public final int M_SOTURI = -1;
	public final int M_HEPO   = -2;
	public final int M_LAHE   = -3;
	public final int M_TORNI  = -4;
	public final int M_MUIJA  = -5;
	public final int M_KINGI  = -6;
	
	public final int V_SOTURI = 1;
	public final int V_HEPO   = 2;
	public final int V_LAHE   = 3;
	public final int V_TORNI  = 4;
	public final int V_MUIJA  = 5;
	public final int V_KINGI  = 6;
	public final int A1 = 0;
	public final int A2 = 8;
	public final int A3 = 16;
	public final int A4 = 24;
	public final int A5 = 32;
	public final int A6 = 40;
	public final int A7 = 48;
	public final int A8 = 56;
	public final int B1 = 1;
	public final int B2 = 9;
	public final int B3 = 17;
	public final int B4 = 25;
	public final int B5 = 33;
	public final int B6 = 41;
	public final int B7 = 49;
	public final int B8 = 57;
	public final int C1 = 2;
	public final int C2 = 10;
	public final int C3 = 18;
	public final int C4 = 26;
	public final int C5 = 34;
	public final int C6 = 42;
	public final int C7 = 50;
	public final int C8 = 58;
	public final int D1 = 3;
	public final int D2 = 11;
	public final int D3 = 19;
	public final int D4 = 27;
	public final int D5 = 35;
	public final int D6 = 43;
	public final int D7 = 51;
	public final int D8 = 59;
	public final int E1 = 4;
	public final int E2 = 12;
	public final int E3 = 20;
	public final int E4 = 28;
	public final int E5 = 36;
	public final int E6 = 44;
	public final int E7 = 52;
	public final int E8 = 60;
	public final int F1 = 5;
	public final int F2 = 13;
	public final int F3 = 21;
	public final int F4 = 29;
	public final int F5 = 37;
	public final int F6 = 45;
	public final int F7 = 53;
	public final int F8 = 61;
	public final int G1 = 6;
	public final int G2 = 14;
	public final int G3 = 22;
	public final int G4 = 30;
	public final int G5 = 38;
	public final int G6 = 46;
	public final int G7 = 54;
	public final int G8 = 62;
	public final int H1 = 7;
	public final int H2 = 15;
	public final int H3 = 23;
	public final int H4 = 31;
	public final int H5 = 39;
	public final int H6 = 47;
	public final int H7 = 55;
	public final int H8 = 63;


	public Pelilauta() {
		this.lauta = new int[64];
	}
	

	private void alustaPeliLautaNolliksi() {
		for (int i=0;i<64;i++) {
			this.lauta[i] = 0;
		}
	}


	private void asetaAlkuPaikoilleValkoiset() {
		for (int i = A2; i < A3; i++) {
				this.lauta[i] = V_SOTURI;
		}
		
		this.lauta[A1] = this.lauta[H1] = V_TORNI;
		this.lauta[B1] = this.lauta[G1] = V_HEPO;
		this.lauta[C1] = this.lauta[F1] = V_LAHE;
		this.lauta[D1] = V_MUIJA;
		this.lauta[E1] = V_KINGI;
	}

	private void asetaAlkuPaikoilleMustat() {
		for (int i = A7 ; i<A8 ;i++) {
			
			this.lauta[i] = M_SOTURI;
	
		}
		this.lauta[A8] = this.lauta[H8] = M_TORNI;
		this.lauta[B8] = this.lauta[G8] = M_HEPO;
		this.lauta[C8] = this.lauta[F8] = M_LAHE;
		this.lauta[D8] = M_MUIJA;
		this.lauta[E8] = M_KINGI;
	}

	public void asetaNappulatAloituspaikoille() {
		alustaPeliLautaNolliksi();
		asetaAlkuPaikoilleMustat(); 
		asetaAlkuPaikoilleValkoiset();
	}

	public void tulostaLauta() {
		for (int j=0; j<8; j++) {
			for (int i = A8 - (8*j) ; i <= H8 - (8*j); i++) {
				System.out.printf(" %2d ", this.lauta[i]);
			}
			System.out.printf("\n\n");
		}	
	}

	
	
}
