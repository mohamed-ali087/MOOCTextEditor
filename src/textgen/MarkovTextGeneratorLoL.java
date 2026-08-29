package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private /*protected*/ List<ListNode> wordList;  /* use protected for the tester to work */
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
//		wordList = new MyLinkedList<ListNode>(); // used MyLinkedList instead of LinkedList.
		wordList = new LinkedList<ListNode>(); 
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		String[] words = sourceText.split("[ ]+");
		
		outerLoop:
		for(int i = 0; i<words.length; i++) {

			// check if the word is not already in the wordList
			// #TODO: replace the for loop with getWordNode helper method.
			for(ListNode wordNode : wordList) {
				if(wordNode.getWord().equals(words[i])) {
					wordNode.addNextWord(words[(i+1) < words.length ? i+1 : 0 ]); // if the word is the final word, connect it to the first word.
					continue outerLoop;
				}
			}
			// if the word is new.
			ListNode wordNode = new ListNode(words[i]);
			wordList.add(wordNode);
			wordNode.addNextWord(words[(i+1) < words.length ? i+1 : 0 ]);

		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // DONE: Implement this method
		
		if(wordList.size() <= 0 || numWords <= 0) {
			return "";
		}

		ListNode currentWordNode = wordList.get(0);
		String toReturn = currentWordNode.getWord();

		for(int i = 1; i < numWords; i++) {
			toReturn = toReturn.concat(" ");
			String currentWord = currentWordNode.getRandomNextWord(rnGenerator);
			currentWordNode = getWordNode(currentWord);
			toReturn = toReturn.concat(currentWord);
		}
		
		return toReturn;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
//		wordList = new MyLinkedList<ListNode>();
		wordList = new LinkedList<ListNode>();
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	private ListNode getWordNode(String word) {
		for(ListNode wordNode : wordList) {
			if(wordNode.getWord().equals(word)) {
				return wordNode;
			}
		}
		return null;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
//		nextWords = new MyLinkedList<String>(); // used MyLinkedList instead of LinkedList
		nextWords = new LinkedList<String>(); 
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class

	    return nextWords.get(Math.abs(generator.nextInt()) % nextWords.size());
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


