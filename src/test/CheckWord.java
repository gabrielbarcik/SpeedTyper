package test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.dineshkrish.*;

public class CheckWord implements Runnable {
	
	//AtomicInteger maxPoints;
	AtomicInteger points;
	String wordTried;
	MySpellChecker spellChecker;
	
	public CheckWord (AtomicInteger max_points, AtomicInteger point, String mot, MySpellChecker spellChecker) {
		//maxPoints = max_points;
		points = point;
		wordTried = mot;
		this.spellChecker = spellChecker;
	}
	
	@Override
	public void run() {
		List<String> wrongWords = spellChecker.detectMisspelledWords(wordTried);
		
		if(wrongWords.size() == 0) { // word were right
			int point = points.addAndGet(wordTried.length());
			
			//todo: printar a palavra em wrong words
			/*while(true) {
				int maxPoint = maxPoints.get();
				if(point <= maxPoint)
					break;			
				boolean newMax = maxPoints.compareAndSet(maxPoint, point);
				// Pode colocar um barullho se o maximo foi atingido
			}*/
		}
		
		else {
			points.addAndGet(-wordTried.length());
		}
		
		wrongWords.clear();
	}

}
