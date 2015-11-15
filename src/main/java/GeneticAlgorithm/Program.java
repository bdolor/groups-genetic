package main.java.GeneticAlgorithm;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import main.java.GeneticAlgorithm.Binomial.BinomialChromosome;
import main.java.GeneticAlgorithm.Binomial.BinomialFactory;
import main.java.GeneticAlgorithm.Binomial.BinomialSolution;
import main.java.GeneticAlgorithm.Common.BitInversionMutation;
import main.java.GeneticAlgorithm.Common.GeneticAlgorithmException;
import main.java.GeneticAlgorithm.Common.PermutationMutation;
import main.java.GeneticAlgorithm.Common.RouletteSelect;
import main.java.GeneticAlgorithm.Common.BinaryTournamentSelect;
import main.java.GeneticAlgorithm.Common.SinglePointCrossover;
import main.java.GeneticAlgorithm.GroupEncoding.GroupEncodingChromosome;
import main.java.GeneticAlgorithm.GroupEncoding.GroupEncodingCrossover;
import main.java.GeneticAlgorithm.GroupEncoding.GroupEncodingMutation;
import main.java.GeneticAlgorithm.GroupEncoding.MultiPointCrossover;
import main.java.GeneticAlgorithm.Interfaces.IMutation;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroupCrossover;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroups;
import main.java.GeneticAlgorithm.StudentGrouping.StudentGroupsFactory;
import main.java.GeneticAlgorithm.UserInterface.ReportFrame;

public class Program {

	public static void main(String[] args) {

		System.out.println("COMP-658 Computational Intelligence - Assignment 2");
		System.out.println("Athabasca University");
		System.out.println();
		System.out.println();
		
		System.out.println("Tool for configuring, executing and monitoring Genetic Algorithm to optimize student group heterogenity.");

		
		CommandLineAlgorithmParser parser = new CommandLineAlgorithmParser();
		
		parser.printHelp();
		
		//String[] a = new String[] {"-s", "500", "-na", "-g"};
		
		GeneticAlgorithm algorithm = null;
		try {
			algorithm = parser.getAlgorithm(args);
		} catch (Exception e) {
		System.out.println();
			System.out.println();
			System.out.println("Error parsing arguments:");
			System.out.println();
			System.out.println("   " + e.getMessage());
			System.out.println();
			System.out.println("Please try again.");
			System.out.println();
			System.exit(1);;		
		}

		//Program.RunPermutationEncoding();
		//Program.RunGroupEncoding();
		//Program.RunAdaptiveGroupEncoding();
		//Program.RunAdaptivePermutationEncoding();
		//Program.RunBinomialSample();

		
	   ReportFrame report = new ReportFrame();
	   report.initializeReport(algorithm);
	   report.setVisible(true);
	    
		algorithm.setReport(report);;
		
	    try {
			algorithm.Evolve();
		} catch (GeneticAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}



	private static void RunPermutationEncoding() {

		ReportFrame report = new ReportFrame();

		GeneticAlgorithmConfig config = new GeneticAlgorithmConfig();

		GeneticAlgorithm<StudentGroups> ga = new GeneticAlgorithm<>();

		ga.Config = config;
		ga.CrossOver = new StudentGroupCrossover<>();
		ga.Mutation = new PermutationMutation<>();
		//ga.Select = new RouletteSelect<>();
		ga.Select = new BinaryTournamentSelect<>();
		ga.Factory = new StudentGroupsFactory<>();
		ga.setReport(report);

		report.initializeReport(ga);
		report.setVisible(true);

		try {
			ga.Evolve();
		} catch (GeneticAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void RunGroupEncoding() {

		ReportFrame report = new ReportFrame();

		GeneticAlgorithmConfig config = new GeneticAlgorithmConfig();

		GeneticAlgorithm<GroupEncodingChromosome> ga = new GeneticAlgorithm<>();

		ga.Config = config;
		ga.CrossOver = new GroupEncodingCrossover<>();
		ga.Mutation = new GroupEncodingMutation<>();
		ga.Select = new RouletteSelect<>();
		//ga.Select = new BinaryTournamentSelect<>();
		ga.Factory = new GroupEncodingChromosome();
		ga.setReport(report);

		report.initializeReport(ga);
		report.setVisible(true);

		try {
			ga.Evolve();
		} catch (GeneticAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void RunAdaptivePermutationEncoding() {

		ReportFrame report = new ReportFrame();

		GeneticAlgorithmConfig config = new GeneticAlgorithmConfig();

		GeneticAlgorithm<StudentGroups> ga;
		ga = new AdaptiveGeneticAlgorithm<>();

		ga.Config = config;
		ga.CrossOver = new StudentGroupCrossover<>();
		ga.Mutation = new PermutationMutation<>();
		//ga.Select = new RouletteSelect<>();
		ga.Select = new BinaryTournamentSelect<>();
		ga.Factory = new StudentGroupsFactory<>();
		ga.setReport(report);

		report.initializeReport(ga);
		report.setVisible(true);

		try {
			ga.Evolve();
		} catch (GeneticAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void RunAdaptiveGroupEncoding() {

		ReportFrame report = new ReportFrame();

		GeneticAlgorithmConfig config = new GeneticAlgorithmConfig();

		GeneticAlgorithm<GroupEncodingChromosome> ga;
		ga = new AdaptiveGeneticAlgorithm<>();

		ga.Config = config;
		ga.CrossOver = new GroupEncodingCrossover<>();
		ga.Mutation = new GroupEncodingMutation<>();
		//ga.Select = new RouletteSelect<>();
		ga.Select = new BinaryTournamentSelect<>();
		ga.Factory = new GroupEncodingChromosome();
		ga.setReport(report);

		report.initializeReport(ga);
		report.setVisible(true);

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
