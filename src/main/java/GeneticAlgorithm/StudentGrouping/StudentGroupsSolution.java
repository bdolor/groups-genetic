package main.java.GeneticAlgorithm.StudentGrouping;

import java.util.ArrayList;

import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ISolution;

public class StudentGroupsSolution <T extends IChromosome<int[]>> implements ISolution<T> {

	@Override
	public ArrayList<T> getSolutions(ArrayList<T> population) {
		return new ArrayList<T>();
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
