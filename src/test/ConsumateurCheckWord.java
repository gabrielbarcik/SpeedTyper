package test;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.dineshkrish.*;

public class ConsumateurCheckWord extends Thread {

	private final AtomicInteger points;
	private final BlockingQueue<String> wordsToBeChecked;
	private final MySpellChecker spellChecker;
	private final BlockingQueue<Boolean> queueOfActiveThreads;

	public ConsumateurCheckWord(AtomicInteger max_points, AtomicInteger point, BlockingQueue<String> wordsToBeChecked2,
			MySpellChecker spellChecker, BlockingQueue<Boolean> queueOfActiveThreads) {
		// maxPoints = max_points;
		points = point;
		this.wordsToBeChecked = (BlockingQueue<String>) wordsToBeChecked2;
		this.spellChecker = spellChecker;
		this.queueOfActiveThreads = queueOfActiveThreads;
	}

	@Override
	public void run() {

		String wordToBeChecked;

		while (true) {

			try {
				wordToBeChecked = wordsToBeChecked.take();
			} catch (InterruptedException e) {
				return;
			}
			queueOfActiveThreads.offer(true);

			List<String> wrongWords = spellChecker.detectMisspelledWords(wordToBeChecked);

			if (wrongWords.size() == 0) { // word is right
				int point = points.addAndGet(wordToBeChecked.length());

				// todo: printar palvra de verde na interface
				/*
				 * while(true) { int maxPoint = maxPoints.get(); if(point <= maxPoint) break;
				 * boolean newMax = maxPoints.compareAndSet(maxPoint, point); } // Pode colocar
				 * um barullho se o maximo foi atingido
				 */
			}

			else {
				// todo: printar a palavra em wrong words
				points.addAndGet(-wordToBeChecked.length());
			}
			
			queueOfActiveThreads.remove(true);

		}

	}

}
