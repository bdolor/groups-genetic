package main.java.GeneticAlgorithm.Common;

import java.util.ArrayList;

import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ISelect;

@SuppressWarnings("rawtypes")
public class RouletteSelect<T extends IChromosome> implements ISelect<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T[] GetParents(ArrayList<T> population, double[] fitness, int parentCount) {

		IChromosome[] parents = new IChromosome[parentCount];

		double sumFitness = 0;
		for (int i = 0; i < fitness.length; i++) {
			sumFitness += fitness[i];
		}

		// Random parents when sumfitness = 0, otherwise roulette will always select first chromosome.
		if (sumFitness == 0) {
			for (int i = 0; i < parentCount; i++) {
				parents[i] = population.get((int) (Math.random() * sumFitness));
			}
		} else {
			for (int i = 0; i < parentCount; i++) {
				int r = (int) (Math.random() * sumFitness);
				double sumSelection = 0;
				for (int j = 0; j < fitness.length; j++) {
					sumSelection += fitness[j];
					if (sumSelection >= r) {
						parents[i] = population.get(j);
						break;
					}
				}
			}
		}

		return (T[]) parents;
	}

}
