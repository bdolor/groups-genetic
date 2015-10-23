package main.java.GeneticAlgorithm;

import main.java.GeneticAlgorithm.Binomial.BinomialChromosome;
import main.java.GeneticAlgorithm.Binomial.BinomialFactory;
import main.java.GeneticAlgorithm.Binomial.BinomialSolution;
import main.java.GeneticAlgorithm.Common.BitInversionMutation;
import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;
import main.java.GeneticAlgorithm.Common.RouletteSelect;
import main.java.GeneticAlgorithm.Common.SinglePointCrossover;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("Athabasca University");
		System.out.println("Computer Science 658: Computational Intelligence");
		System.out.println();
		System.out.println("Assignment #2");
		System.out.println();
		System.out.println();
		
		Program.RunBinomialSample();
		
	}
	
	private static void RunBinomialSample() {
		GeneticAlgorithmConfig config = new GeneticAlgorithmConfig();
				
		GeneticAlgorithm<BinomialChromosome> ga = new GeneticAlgorithm<BinomialChromosome>();
		
		ga.Config = config;
		ga.CrossOver = new SinglePointCrossover<BinomialChromosome>();
		ga.Mutation = new BitInversionMutation<BinomialChromosome>();
		ga.Select = new RouletteSelect<BinomialChromosome>();
		ga.Solution = new BinomialSolution<BinomialChromosome>();
		ga.Factory = new BinomialFactory<>();
				
		try {
			ga.Evolve();
		} catch (GeneticAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

