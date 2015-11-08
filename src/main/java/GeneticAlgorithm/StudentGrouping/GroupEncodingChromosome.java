package main.java.GeneticAlgorithm.StudentGrouping;

import java.util.ArrayList;
import java.util.Stack;

import org.jfree.util.StringUtils;

import main.java.GeneticAlgorithm.Interfaces.IChromosome;
import main.java.GeneticAlgorithm.Interfaces.IFactory;

public class GroupEncodingChromosome implements IChromosome<int[]>, IFactory<GroupEncodingChromosome> {

	public final static int MAXIMUM_STUDENTS = 512;

	private int[] Encoding;
	private Double fitness = null;
	private StudentScores scores = new StudentScores();

	public GroupEncodingChromosome() {
		this.setEncoding(this.getRandomEncoding());
		this.applyCorrection();
	}
	/**
	 * Get the sum of all GH values in a class of students. Invalid groups
	 * are given a value of zero.
	 * 
	 * @return 
	 */
	@Override
	public double getFitness() {		
		double sumGh = 0;
		
		if (fitness != null) {
			sumGh = this.fitness;
		} else {
			if (this.isValid()) {
				ArrayList<Stack<Integer>> groups = this.getGroups();
				for (Stack<Integer> group : groups) {
					sumGh += this.scores.getGhValue(group.get(0), group.get(1), group.get(2), group.get(3));
				}			
			}						
		}		
		return sumGh;
	}

	@Override
	public int[] getEncoding() {
		return this.Encoding;
	}

	public void setEncoding(int[] encoding) {
		this.fitness = null;
		this.Encoding = encoding;
	}

	/**
	 * Evaluate if a group of 4 is valid, meaning has a Euclidean Distance greater
	 * than 2 and a GH value greater than 0.5.
	 * 
	 * @return Boolean
	 */
	public boolean isValid() {
		boolean ret = true;
		ArrayList<Stack<Integer>> groups = this.getGroups();		
		for (Stack<Integer> group : groups) {
			if ((this.scores.getMaxDistance(group.get(0), group.get(1), group.get(2), group.get(3)) <= 2)
					&& this.scores.getGhValue(group.get(0), group.get(1), group.get(2), group.get(3)) <= 0.5) {
				ret = false;
			}
		}

		return ret;
	}

	public void applyCorrection() {
		this.setEncoding(this.getCorrectedEncoding());
	}

	protected int[] getRandomEncoding() {
		int[] encoding = new int[GroupEncodingChromosome.MAXIMUM_STUDENTS];
		for (int i = 0; i < GroupEncodingChromosome.MAXIMUM_STUDENTS; i++) {
			encoding[i] = (int) (1 + Math.random() * (GroupEncodingChromosome.MAXIMUM_STUDENTS / 4));
		}
		return encoding;
	}

	protected int[] getCorrectedEncoding() {

		ArrayList<Stack<Integer>> groups = this.getGroups();

		boolean isValid = false;
		while (!isValid) {
			isValid = true;
			for (Stack<Integer> group : groups) {
				while (group.size() > 4) {
					isValid = false;
					for (int i = 0; i < groups.size(); i++) {
						if (groups.get(i).size() < 4) {
							int studentToMove = group.pop();
							groups.get(i).push(studentToMove);
							break;
						}
					}
				}
			}
		}

		int[] correctedEncoding = new int[GroupEncodingChromosome.MAXIMUM_STUDENTS];
		for (int i = 0; i < groups.size(); i++) {
			Stack<Integer> group = groups.get(i);
			correctedEncoding[group.get(0) - 1] = i + 1;
			correctedEncoding[group.get(1) - 1] = i + 1;
			correctedEncoding[group.get(2) - 1] = i + 1;
			correctedEncoding[group.get(3) - 1] = i + 1;
		}

		return correctedEncoding;
	}

	/**
	 * Take a class of 512 student IDs and represent them as a stack array of 128 groups.
	 * 
	 * @return groups ArrayList stack
	 */
	protected ArrayList<Stack<Integer>> getGroups() {
		ArrayList<Stack<Integer>> groups = new ArrayList<Stack<Integer>>();

		for (int i = 0; i < GroupEncodingChromosome.MAXIMUM_STUDENTS / 4; i++) {
			groups.add(new Stack<Integer>());
		}

		for (int i = 0; i < GroupEncodingChromosome.MAXIMUM_STUDENTS; i++) {
			int studentGroup = this.getEncoding()[i];
			groups.get(studentGroup - 1).push(i + 1);
		}

		return groups;
	}

	@Override
	public GroupEncodingChromosome CreateChromosome() {
		return new GroupEncodingChromosome();
	}

}
