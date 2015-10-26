package main.java.GeneticAlgorithm.Binomial;

import java.util.ArrayList;
import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ISolution;

public class BinomialSolution <T extends IChromosome<String>> implements ISolution<T> {
	
	private static double ROUND_TOLERANCE = 0.001; 

	@Override
	public ArrayList<T> getSolutions(ArrayList<T> population) {

		ArrayList<T> solutions = new ArrayList<T>();
		
		for (int i=0; i<population.size(); i++) {
			T candidate = population.get(i);		
			if (Math.abs(candidate.getFitness() - 1050) <  BinomialSolution.ROUND_TOLERANCE) {
				solutions.add(candidate);
			}
		}
		
		return solutions;
	}

	@Override
	public T getFittestSolution(ArrayList<T> population) {
		 T fittest = population.get(0);
		
		for (int i=0; i<population.size();i++) {
			fittest = population.get(i).getFitness() > fittest.getFitness() ?  population.get(i) : fittest;
		}
		
		return fittest;		
	}
	


}
