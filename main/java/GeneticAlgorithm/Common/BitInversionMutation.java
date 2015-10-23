package main.java.GeneticAlgorithm.Common;

import main.java.GeneticAlgorithm.Interfaces.IMutation;

public class BitInversionMutation<T extends BinaryChromosome> implements IMutation<T> {

	@SuppressWarnings("unchecked")
	@Override
	public BinaryChromosome[] Mutate(BinaryChromosome[] offspring) throws GeneticAlgorithmException {

		BinaryChromosome[] mutatedOffspring = new BinaryChromosome[offspring.length];

		for (int i = 0; i < offspring.length; i++) {

			int r = (int) (1 + Math.random() * offspring[i].getLength());

			String encoding = offspring[i].getEncoding();
			String mutatedEncoding = this.replaceCharAt(encoding, r, encoding.charAt(r) == '0' ? '1' : '0');

			
			mutatedOffspring[i] = BinaryChromosome.NewInstance(offspring[i]);
			mutatedOffspring[i].setEncoding(mutatedEncoding);
		}

		return mutatedOffspring;
	}

	protected String replaceCharAt(String s, int pos, char c) {
		return s.substring(0, pos) + c + s.substring(pos + 1);
	}
}
