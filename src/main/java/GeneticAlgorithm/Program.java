package main.java.GeneticAlgorithm;

import main.java.GeneticAlgorithm.Binomial.BinomialChromosome;
import main.java.GeneticAlgorithm.Binomial.BinomialFactory;
import main.java.GeneticAlgorithm.Binomial.BinomialSolution;
import main.java.GeneticAlgorithm.Common.BitInversionMutation;
import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;
import main.java.GeneticAlgorithm.Common.PermutationCrossover;
import main.java.GeneticAlgorithm.Common.PermutationMutation;
import main.java.GeneticAlgorithm.Common.RouletteSelect;
import main.java.GeneticAlgorithm.Common.SinglePointCrossover;
import main.java.GeneticAlgorithm.Interfaces.IReport;
import main.java.GeneticAlgorithm.StudentGrouping.GroupEncodingChromosome;
import main.java.GeneticAlgorithm.StudentGrouping.GroupEncodingCrossover;
import main.java.GeneticAlgorithm.StudentGrouping.GroupEncodingMutation;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroupCrossover;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroups;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroupsFactory;
import main.java.GeneticAlgorithm.StudentGrouping.StudentScores;
import main.java.GeneticAlgorithm.UserInterface.ReportFrame;


public class Program {

	public static void main(String[] args) {
		
		
	
		ReportFrame report = new ReportFrame();
		report.setVisible(true);
		

		
		System.out.println("Athabasca University");
		System.out.println("Computer Science 658: Computational Intelligence");
		System.out.println();
		System.out.println("Assignment #2");
		System.out.println();
		System.out.println();
		
		Program.RunStudentGroup(report);
		//Program.RunBinomialSample();
		
	}
	
	private static void RunStudentGroup(IReport report) {
		GeneticAlgorithmConfig config = new GeneticAlgorithmConfig();
		
		//GeneticAlgorithm<StudentGroups> ga = new GeneticAlgorithm<StudentGroups>();
		GeneticAlgorithm<GroupEncodingChromosome> ga = new GeneticAlgorithm<GroupEncodingChromosome>();
		
		ga.Config = config;
		//ga.CrossOver = new PermutationCrossover<StudentGroups>();
		//ga.CrossOver = new StudentGroupCrossover<StudentGroups>();
		ga.CrossOver = new GroupEncodingCrossover<>();
		
		
		
		
		//ga.Mutation = new PermutationMutation<StudentGroups>();
		
		ga.Mutation = new GroupEncodingMutation<>();
		
		//ga.Select = new RouletteSelect<StudentGroups>();
		ga.Select = new RouletteSelect<GroupEncodingChromosome>();
		
		
		//ga.Solution = new StudentGroupsSolution<StudentGroups>();
		//ga.Factory = new StudentGroupsFactory<StudentGroups>();
		ga.Factory = new GroupEncodingChromosome();
		ga.setReport(report);

				
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

