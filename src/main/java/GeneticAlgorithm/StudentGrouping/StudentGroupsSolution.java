package main.java.GeneticAlgorithm.StudentGrouping;

import java.util.ArrayList;

import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ISolution;

public class StudentGroupsSolution <T extends IChromosome<int[]>> implements ISolution<T> {

	@Override
	public ArrayList<T> getSolutions(ArrayList<T> population) {
		return new ArrayList<T>();
	}
	/**
	 * Looks for the highest overall heterogeneity of all groups within a population 
	 * and selects a winner! 
	 * 
	 * @param population ArrayList
	 * @return fittest ArrayList
	 */
	@Override
	public T getFittestSolution(ArrayList<T> population) {
		T fittest = population.get(0);
				
		for (T population1 : population) {
			fittest = population1.getFitness() > fittest.getFitness() ? population1 : fittest;
		} 
			
		return fittest;	
	}
	
	/**
	 * Looks for the best solution and returns value of the index. 
	 * 
	 * @param population
	 * @return integer bestIndex
	 */
	@Override
	public int getFittestSolutionIndex(ArrayList<T> population) {
		T fittest = population.get(0);
		int bestIndex = 0;

		for (int i = 0; i < population.size(); i++) {
			if (population.get(i).getFitness() > fittest.getFitness()) {
				fittest = population.get(i);
				bestIndex = i;
			}
		}

		return bestIndex;
	}
	
	
}
