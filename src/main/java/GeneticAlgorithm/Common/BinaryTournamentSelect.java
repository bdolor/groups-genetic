package main.java.GeneticAlgorithm.Common;

import java.util.ArrayList;
import java.util.Collections;

import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ISelect;

public class BinaryTournamentSelect<T extends IChromosome> implements ISelect<T> {

	@Override
	public T[] GetParents(ArrayList<T> population, double[] fitness, int parentCount) {
		IChromosome[] parents = new IChromosome[parentCount];
		int pool = fitness.length;
		int contenders = 20;
		double champ1fitness = 0;
		double champ2fitness = 0;
		int redCorner = 0;
		int blueCorner = 0;

		// create a list of numbers
		ArrayList<Integer> randomList = new ArrayList<>();
		for (int i = 0; i < pool; i++) {
			randomList.add(i);
		}

		// give that list a shake
		Collections.shuffle(randomList);
		// choose a number of individuals randomly from a population
		// repeat until the mating pool is filled
		for (int i = 0; i < contenders; i++) {

			// select the best individuals from the group
			if (fitness[randomList.get(i)] > champ1fitness) {
				champ1fitness = fitness[randomList.get(i)];
				redCorner = randomList.get(i);
			}

			if (fitness[randomList.get(i + contenders)] > champ2fitness) {
				champ2fitness = fitness[randomList.get(i + contenders)];
				blueCorner = randomList.get(i + contenders);

			}
		}

		parents[0] = population.get(redCorner);
		parents[1] = population.get(blueCorner);

		return (T[]) parents;
	}

}
