package main.java.GeneticAlgorithm.Interfaces;

import java.util.ArrayList;

public interface ISolution<T> {
	
	T[] FindSolutions(ArrayList<T> population);

}
