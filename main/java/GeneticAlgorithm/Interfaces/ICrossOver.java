package main.java.GeneticAlgorithm.Interfaces;

import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;

public interface ICrossOver<T> {
	T[] CrossOver(T[] parents) throws GeneticAlgorithmException;
}
