package main.java.GeneticAlgorithm.Common;

import java.util.ArrayList;

import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.ICrossOver;

public class PermutationCrossover <T extends IChromosome<int[]>> implements ICrossOver<T> {

	@SuppressWarnings("unchecked")
	@Override
	public T[] CrossOver(T[] parents) throws GeneticAlgorithmException {
		if (parents.length != 2) {
			throw new GeneticAlgorithmException(
					String.format("Crossover expecting 2 parents but found {0}", parents.length));
		}

		int[] parent1 = parents[0].getEncoding();
		int[] parent2 = parents[1].getEncoding();
		ArrayList<Integer> offspringEncoding = new ArrayList<Integer>();

		int r = (int) (Math.random() * parent1.length);

		for (int i = 0; i <= r; i++) {
			offspringEncoding.add(parent1[i]);
		}

		for (int i = 0; i < parent1.length; i++) {
			if (!offspringEncoding.contains(parent2[i])) {
				offspringEncoding.add(parent2[i]);
			}
		}
		
		int[] offspringEncodingArray = new int[offspringEncoding.size()];
		for (int i=0;i<offspringEncoding.size();i++) {
			offspringEncodingArray[i] = offspringEncoding.get(i);
		}
			
		PermutationChromosome offspring = PermutationChromosome.NewInstance(parents[0]);
		offspring.setPermutationEncoding(offspringEncodingArray);

		return (T[]) new PermutationChromosome[]{offspring};
	}

}
