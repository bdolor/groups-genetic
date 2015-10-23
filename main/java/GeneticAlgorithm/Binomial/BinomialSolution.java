package main.java.GeneticAlgorithm.Binomial;

import java.util.ArrayList;

import main.java.GeneticAlgorithm.Common.BinaryChromosome;
import main.java.GeneticAlgorithm.Interfaces.ISolution;

public class BinomialSolution <T extends BinaryChromosome> implements ISolution<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T[] FindSolutions(ArrayList<T> population) {
		
		ArrayList<BinaryChromosome> solutions = new ArrayList<BinaryChromosome>();
		
		for (int i=0; i<population.size(); i++) {
			BinaryChromosome candidate = population.get(i);		
			if (candidate.GetFitness() > 30) {
				solutions.add(candidate);
			}
		}
		
		return (T[]) solutions.toArray();
	}

}
