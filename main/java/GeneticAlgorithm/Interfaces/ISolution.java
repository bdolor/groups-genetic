package main.java.GeneticAlgorithm.Interfaces;

import java.util.ArrayList;

public interface ISolution<T> {
	
	ArrayList<T> getSolutions(ArrayList<T> population);
	T getFittestSolution(ArrayList<T> population);

}
