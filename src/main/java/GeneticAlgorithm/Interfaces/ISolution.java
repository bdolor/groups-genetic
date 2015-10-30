package main.java.GeneticAlgorithm.Interfaces;

import java.util.ArrayList;

public interface ISolution<T> {
	
	ArrayList<T> getSolutions(ArrayList<T> population);
	T getFittestSolution(ArrayList<T> population);
	int getFittestSolutionIndex(ArrayList<T> population);
	String[] getMembersOfGroup(ArrayList<T> population, int index);
}
