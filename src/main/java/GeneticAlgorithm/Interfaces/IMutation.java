package main.java.GeneticAlgorithm.Interfaces;

import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;

public interface IMutation <T> {
	T[] Mutate(T[] offspring) throws GeneticAlgorithmException;
}
