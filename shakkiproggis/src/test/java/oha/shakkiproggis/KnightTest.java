package oha.shakkiproggis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import oha.shakkiproggis.Board;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.Collectors;
import oha.shakkiproggis.Square;

/**
 *
 * @author pyry
 */
public class KnightTest {
	
	public KnightTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test
	public void knightsAttacks() {
		Board b;		
		b = new Board();
		EnumSet<Square> attacks = EnumSet.noneOf(Square.class);
		attacks.add(Square.C2);
		attacks.add(Square.B3);
		assertEquals(attacks, b.knightAttacks(Square.A1).collect(Collectors.toCollection(() -> EnumSet.noneOf(Square.class))));

	}
}
