package textgen;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class MarkovTextGeneratorLoLTester{
	
	MarkovTextGeneratorLoL hiLeoTextGenerator;

	@Before
	public void setUp() throws Exception {
		hiLeoTextGenerator = new MarkovTextGeneratorLoL(new Random(32));
		hiLeoTextGenerator.train("hi there hi Leo");
	}
	
	@Test
	public void testTrain() throws Exception {
//		assertEquals("Check all words are added", "hi: there->Leo->\n"	
//				+ "there: hi->\n"	
//				+ "Leo: hi->\n", hiLeoTextGenerator.wordList.get(0).toString() +
//				hiLeoTextGenerator.wordList.get(1).toString() +
//				hiLeoTextGenerator.wordList.get(2).toString());

		assertEquals("Check all words are added", "hi: there->Leo->\n"	
				+ "there: hi->\n"	
				+ "Leo: hi->\n", hiLeoTextGenerator.toString());
	}
	
	@Test
	public void testGenerateRandom() throws Exception{
		assertEquals("Check random generated text", "hi Leo hi there", hiLeoTextGenerator.generateText(4));
		hiLeoTextGenerator.retrain("Mohamed! Mahmoud AliAli loay Mohamed");
		System.out.println(hiLeoTextGenerator.toString());
	}
	
	
	
	
	
}