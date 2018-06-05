package hw8;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

public class SpellCheckerTest {

	private SpellChecker spellChecker;

	@Before
	public void setUp() throws Exception {
		spellChecker = new SpellChecker();
		
		String data = "about\nabove\nabzve\nabsolutely\nacceptable\nbauve\nabuv\nabuven"
				+ "\nadd\nadjacent\nafter\nalgorithm\nall\nalong\nalso\nan";
		
		spellChecker.ReadDictionary(new StringReader(data));
	}

	@Test
	public void CheckCorrectWord() {
		String[] variants = spellChecker.CheckWord("acceptable");
		assertNull(variants);
	}
	@Test
	public void CheckMisspelledWord() {
		String[] expected={"above","abzve","abuv","abuven","bauve"};
		String[] variants = spellChecker.CheckWord("Abuve");
		assertArrayEquals(variants, expected);
	}
	@Test
	public void CheckMisspelledWordWithNoVariant() {
		String[] expected={};
		String[] variants = spellChecker.CheckWord("baba");
		assertArrayEquals(variants, expected);
	}

}
