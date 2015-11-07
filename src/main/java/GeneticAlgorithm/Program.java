package main.java.GeneticAlgorithm;

import main.java.GeneticAlgorithm.Binomial.BinomialChromosome;
import main.java.GeneticAlgorithm.Binomial.BinomialFactory;
import main.java.GeneticAlgorithm.Binomial.BinomialSolution;
import main.java.GeneticAlgorithm.Common.BinaryTournamentSelect;
import main.java.GeneticAlgorithm.Common.BitInversionMutation;
import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;
import main.java.GeneticAlgorithm.Common.PermutationCrossover;
import main.java.GeneticAlgorithm.Common.PermutationMutation;
import main.java.GeneticAlgorithm.Common.RouletteSelect;
import main.java.GeneticAlgorithm.Common.SinglePointCrossover;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroups;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroupsFactory;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroupsSolution;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("Athabasca University");
		System.out.println("Computer Science 658: Computational Intelligence");
		System.out.println();
		System.out.println("Assignment #2");
		System.out.println();
		System.out.println();
		
		Program.RunStudentGroup();
		//Program.RunBinomialSample();
		
	}
	
	private static void RunStudentGroup() {
		GeneticAlgorithmConfig config = new GeneticAlgorithmConfig();
		
		GeneticAlgorithm<StudentGroups> ga = new GeneticAlgorithm<StudentGroups>();
		
		ga.Config = config;
		ga.CrossOver = new PermutationCrossover<StudentGroups>();
		ga.Mutation = new PermutationMutation<StudentGroups>();
		//ga.Select = new RouletteSelect<StudentGroups>();
		ga.Select = new BinaryTournamentSelect<StudentGroups>();
		ga.Solution = new StudentGroupsSolution<StudentGroups>();
		ga.Factory = new StudentGroupsFactory<StudentGroups>();
				
		try {
			ga.Evolve();
		} catch (GeneticAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
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

