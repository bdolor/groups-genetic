package main.java.GeneticAlgorithm.StudentGrouping;

import java.util.ArrayList;
import java.util.Collections;

public class StudentScores {

	public static int getSumScore(int studentId) {
		int scores[] = StudentScores.Scores[studentId - 1];
		int sum = 0;
		for (int s : scores) {
			sum += s;
		}
		return sum;
	}
	
	public static int[] getScores(int studentId) {
		return StudentScores.Scores[studentId - 1];
	}

	public static double getGhValue(int student1, int student2, int student3, int student4) {

		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(StudentScores.getSumScore(student1));
		scores.add(StudentScores.getSumScore(student2));
		scores.add(StudentScores.getSumScore(student3));
		scores.add(StudentScores.getSumScore(student4));
		Collections.sort(scores);

		double adValue = (scores.get(0) + scores.get(3)) / 2;
		double ghValue = (scores.get(3) - scores.get(0)) / (1 + Math.abs(adValue - scores.get(1)) + Math.abs(adValue - scores.get(2)));

		return ghValue;
	}
	
	public static double getMaxDistance(int student1, int student2, int student3, int student4) {
		ArrayList<Double> distances = new ArrayList<Double>();
		
		distances.add(StudentScores.getDistance(student1, student2));
		distances.add(StudentScores.getDistance(student1, student3));
		distances.add(StudentScores.getDistance(student1, student4));
		distances.add(StudentScores.getDistance(student2, student3));
		distances.add(StudentScores.getDistance(student2, student4));
		distances.add(StudentScores.getDistance(student3, student4));

		Collections.sort(distances);
		
		return distances.get(5);
	}
	
	private static double getDistance(int student1, int student2) {
		int[] score1 = StudentScores.getScores(student1);
		int[] score2 = StudentScores.getScores(student2);
		
		double s = 0;
		for (int i=0;i<score1.length;i++) {
			s += Math.pow(score1[i] - score2[i], 2);			
		}
		return Math.sqrt(s);
	}

	private static int[][] Scores = new int[][] { { 2, 2, 2, 2, 2, 3, 3 }, { 2, 2, 1, 2, 2, 2, 2 },
			{ 2, 2, 2, 1, 1, 1, 2 }, { 1, 2, 2, 1, 2, 2, 3 }, { 2, 2, 2, 2, 2, 1, 1 }, { 3, 2, 2, 3, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 1, 2, 2, 1, 1, 2, 1 }, { 2, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 1, 2 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 3, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 1, 2, 1, 1 }, { 1, 2, 2, 2, 2, 1, 3 },
			{ 1, 3, 2, 2, 2, 1, 2 }, { 2, 1, 2, 2, 1, 1, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 1, 1, 2, 2, 2, 2, 1 }, { 3, 2, 2, 2, 1, 1, 1 }, { 2, 3, 2, 2, 3, 1, 2 }, { 1, 2, 1, 1, 1, 3, 3 },
			{ 2, 3, 2, 2, 3, 3, 3 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 1, 3, 2, 2, 1, 1 }, { 1, 1, 1, 2, 2, 2, 1 },
			{ 2, 2, 2, 2, 1, 1, 1 }, { 3, 3, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 3, 3, 2, 1 }, { 2, 2, 2, 1, 2, 2, 1 }, { 2, 3, 2, 3, 2, 1, 1 }, { 3, 3, 2, 3, 2, 1, 2 },
			{ 1, 2, 1, 2, 2, 2, 3 }, { 2, 3, 2, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 1 }, { 2, 3, 2, 3, 2, 3, 3 },
			{ 2, 2, 2, 2, 2, 3, 1 }, { 2, 2, 2, 1, 1, 1, 1 }, { 2, 2, 3, 3, 2, 1, 2 }, { 3, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 1, 3, 2, 2, 1, 1 }, { 2, 1, 2, 2, 3, 2, 1 }, { 1, 2, 2, 1, 2, 2, 1 },
			{ 2, 3, 3, 2, 3, 2, 1 }, { 3, 2, 2, 2, 3, 3, 3 }, { 1, 2, 2, 1, 2, 2, 2 }, { 3, 3, 2, 2, 3, 3, 2 },
			{ 2, 2, 2, 1, 2, 2, 1 }, { 2, 3, 2, 2, 2, 3, 2 }, { 2, 3, 3, 2, 2, 1, 1 }, { 3, 1, 2, 3, 3, 2, 1 },
			{ 2, 3, 2, 2, 3, 1, 1 }, { 1, 1, 2, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 1, 2, 2, 2, 1, 3, 2 },
			{ 3, 1, 2, 2, 2, 3, 1 }, { 1, 1, 1, 1, 2, 1, 1 }, { 2, 1, 1, 2, 2, 2, 2 }, { 2, 2, 2, 3, 2, 1, 1 },
			{ 2, 3, 3, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 2, 3 }, { 2, 1, 2, 2, 2, 1, 1 }, { 3, 1, 3, 3, 3, 2, 1 },
			{ 2, 2, 1, 1, 2, 3, 1 }, { 2, 2, 2, 1, 2, 1, 2 }, { 2, 3, 1, 1, 2, 1, 1 }, { 1, 1, 2, 2, 2, 1, 1 },
			{ 2, 3, 2, 2, 2, 1, 1 }, { 2, 2, 3, 1, 3, 1, 1 }, { 2, 3, 2, 1, 2, 3, 3 }, { 3, 1, 2, 2, 2, 1, 2 },
			{ 2, 3, 2, 2, 3, 2, 3 }, { 1, 2, 2, 2, 2, 1, 1 }, { 2, 3, 3, 2, 3, 3, 3 }, { 1, 2, 2, 2, 2, 3, 2 },
			{ 2, 2, 1, 2, 1, 1, 1 }, { 1, 3, 2, 2, 1, 1, 3 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 3, 3, 3, 2, 3, 3 },
			{ 1, 2, 2, 2, 3, 3, 2 }, { 2, 2, 1, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 1 }, { 1, 3, 3, 2, 1, 2, 2 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 3, 1, 3, 3, 3, 3, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 2, 2 },
			{ 3, 2, 3, 3, 2, 3, 2 }, { 1, 2, 3, 2, 1, 2, 2 }, { 3, 3, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 3, 1, 1 },
			{ 2, 2, 1, 2, 2, 2, 1 }, { 2, 2, 2, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 2, 2, 1, 1 },
			{ 2, 1, 1, 3, 3, 2, 2 }, { 2, 1, 1, 2, 2, 3, 1 }, { 2, 2, 3, 3, 2, 1, 1 }, { 2, 2, 3, 2, 1, 1, 1 },
			{ 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 2, 3 }, { 2, 3, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 2, 2, 1, 3 }, { 3, 2, 3, 2, 3, 3, 2 }, { 2, 3, 2, 2, 3, 2, 2 },
			{ 2, 3, 2, 2, 2, 2, 3 }, { 3, 3, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 1, 1, 1 }, { 1, 1, 1, 2, 2, 2, 1 },
			{ 2, 2, 2, 3, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2 }, { 1, 2, 2, 1, 1, 2, 1 },
			{ 2, 3, 3, 3, 2, 2, 3 }, { 2, 3, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 1, 1, 2, 2 },
			{ 2, 3, 2, 2, 2, 2, 2 }, { 3, 1, 2, 2, 2, 3, 2 }, { 2, 2, 2, 2, 2, 3, 3 }, { 2, 1, 2, 2, 2, 2, 2 },
			{ 2, 3, 3, 2, 2, 3, 3 }, { 2, 3, 2, 2, 2, 1, 3 }, { 2, 3, 2, 2, 1, 2, 3 }, { 2, 2, 1, 1, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 3, 2, 3, 3 }, { 3, 2, 2, 2, 1, 1, 1 }, { 2, 1, 2, 2, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 1, 3, 2, 2, 1, 1 }, { 2, 2, 3, 3, 3, 2, 2 }, { 2, 2, 3, 3, 2, 1, 1 },
			{ 2, 2, 2, 2, 1, 1, 2 }, { 2, 2, 2, 2, 3, 3, 2 }, { 2, 2, 2, 3, 2, 1, 1 }, { 2, 2, 1, 2, 2, 2, 2 },
			{ 2, 3, 2, 3, 3, 1, 3 }, { 2, 1, 2, 2, 2, 1, 2 }, { 2, 1, 2, 2, 2, 2, 2 }, { 2, 2, 2, 3, 3, 2, 1 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 2, 3, 2, 1 }, { 2, 3, 2, 3, 3, 3, 2 }, { 1, 3, 3, 2, 2, 3, 2 },
			{ 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 1, 2, 1 }, { 2, 1, 2, 3, 3, 1, 3 },
			{ 1, 2, 3, 3, 2, 2, 2 }, { 2, 3, 2, 2, 3, 2, 2 }, { 2, 1, 2, 3, 3, 3, 1 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 3, 3, 3, 3, 3, 2, 3 }, { 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 1, 2, 3, 3 },
			{ 3, 2, 3, 2, 2, 2, 1 }, { 2, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 1, 1, 3, 2 }, { 2, 2, 3, 2, 2, 1, 1 },
			{ 3, 1, 2, 2, 3, 1, 1 }, { 1, 2, 1, 1, 2, 1, 1 }, { 1, 2, 1, 1, 1, 1, 2 }, { 2, 2, 2, 1, 2, 1, 2 },
			{ 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 1, 1 }, { 2, 3, 2, 3, 2, 1, 1 },
			{ 2, 1, 2, 2, 2, 1, 1 }, { 1, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 3, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 1, 2, 1, 1 }, { 2, 1, 1, 2, 3, 2, 2 }, { 2, 2, 2, 1, 1, 1, 2 }, { 2, 1, 2, 1, 2, 1, 1 },
			{ 1, 2, 2, 2, 2, 3, 3 }, { 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 2, 1 }, { 2, 2, 1, 2, 2, 1, 2 },
			{ 2, 2, 2, 3, 2, 1, 1 }, { 1, 2, 1, 3, 2, 3, 3 }, { 3, 2, 2, 2, 2, 1, 1 }, { 1, 3, 3, 2, 2, 3, 3 },
			{ 2, 2, 2, 2, 2, 1, 2 }, { 2, 3, 3, 2, 2, 3, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 3, 2, 1, 1 },
			{ 1, 1, 1, 2, 2, 1, 2 }, { 3, 3, 3, 2, 2, 1, 2 }, { 1, 3, 3, 2, 1, 1, 2 }, { 2, 2, 1, 2, 1, 3, 3 },
			{ 2, 2, 2, 3, 2, 1, 1 }, { 2, 3, 2, 2, 3, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 3, 1, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 1, 2, 2, 2, 1 }, { 3, 2, 3, 3, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 1, 1, 2, 2, 3, 3, 1 }, { 2, 2, 3, 3, 3, 2, 1 }, { 2, 3, 1, 2, 2, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 3, 3, 2, 3, 3, 1, 1 }, { 2, 2, 2, 2, 3, 2, 2 }, { 3, 3, 3, 1, 2, 1, 2 }, { 2, 1, 3, 1, 2, 1, 1 },
			{ 2, 2, 2, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2, 3, 2 }, { 2, 2, 3, 2, 2, 2, 1 }, { 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 3, 2, 3, 1 }, { 2, 1, 2, 2, 2, 2, 2 }, { 3, 2, 3, 2, 2, 1, 1 }, { 1, 1, 2, 2, 2, 2, 1 },
			{ 2, 2, 1, 1, 2, 1, 1 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 3, 3, 3, 3, 2 }, { 2, 1, 1, 1, 2, 1, 3 },
			{ 1, 2, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 1, 1 }, { 1, 2, 2, 1, 1, 1, 1 }, { 1, 2, 1, 2, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 3, 1, 1 }, { 3, 2, 2, 2, 2, 2, 3 }, { 3, 3, 2, 2, 2, 2, 1 },
			{ 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 2, 2 }, { 2, 1, 3, 2, 2, 2, 1 }, { 2, 2, 1, 2, 2, 3, 3 },
			{ 1, 3, 2, 2, 2, 2, 2 }, { 2, 2, 2, 1, 2, 3, 3 }, { 2, 3, 2, 2, 2, 3, 3 }, { 2, 2, 2, 3, 3, 1, 2 },
			{ 1, 3, 3, 2, 2, 3, 3 }, { 3, 3, 3, 1, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 2, 3, 2, 3, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 1, 2, 1, 2, 2, 2, 1 }, { 2, 3, 2, 3, 3, 1, 3 },
			{ 2, 2, 1, 2, 2, 1, 2 }, { 2, 2, 2, 2, 1, 2, 2 }, { 2, 2, 3, 2, 2, 2, 3 }, { 2, 2, 2, 3, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 1, 2 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 2, 1, 2, 2, 2, 2 }, { 3, 2, 3, 3, 2, 1, 1 },
			{ 2, 1, 1, 1, 1, 1, 1 }, { 3, 1, 1, 1, 1, 1, 1 }, { 3, 2, 2, 2, 2, 2, 2 }, { 1, 1, 1, 2, 2, 1, 2 },
			{ 2, 2, 3, 2, 2, 3, 3 }, { 2, 2, 2, 1, 2, 1, 1 }, { 3, 3, 2, 2, 2, 1, 2 }, { 2, 2, 1, 1, 2, 1, 1 },
			{ 1, 2, 2, 3, 2, 3, 2 }, { 3, 2, 2, 2, 2, 2, 3 }, { 1, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 3, 2, 2, 1 },
			{ 2, 1, 2, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 2, 2, 1, 2 },
			{ 2, 3, 2, 2, 2, 1, 1 }, { 3, 2, 2, 2, 2, 1, 3 }, { 2, 1, 2, 2, 2, 1, 1 }, { 3, 3, 3, 2, 1, 3, 1 },
			{ 1, 1, 1, 2, 3, 3, 2 }, { 2, 1, 1, 2, 2, 3, 1 }, { 1, 3, 2, 2, 1, 3, 3 }, { 2, 2, 2, 2, 2, 3, 2 },
			{ 2, 2, 1, 2, 1, 3, 2 }, { 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 1, 1, 1, 1 }, { 2, 2, 2, 1, 2, 1, 1 },
			{ 2, 1, 3, 1, 2, 1, 1 }, { 2, 2, 2, 1, 1, 3, 1 }, { 2, 1, 1, 2, 2, 2, 2 }, { 3, 1, 3, 2, 2, 1, 1 },
			{ 3, 2, 2, 2, 2, 2, 1 }, { 2, 2, 3, 3, 2, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 3, 2 },
			{ 3, 2, 3, 2, 1, 2, 1 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 3, 2, 1, 2 }, { 2, 2, 1, 1, 2, 3, 2 },
			{ 1, 3, 1, 1, 2, 1, 2 }, { 2, 2, 2, 3, 2, 3, 1 }, { 2, 3, 3, 2, 2, 1, 1 }, { 2, 3, 3, 3, 2, 3, 2 },
			{ 3, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 2, 3, 3, 2, 2, 2, 2 }, { 2, 2, 3, 2, 2, 2, 1 },
			{ 2, 2, 2, 1, 1, 2, 2 }, { 2, 2, 2, 2, 2, 2, 2 }, { 1, 2, 2, 3, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 },
			{ 1, 2, 2, 3, 2, 1, 1 }, { 2, 3, 3, 3, 2, 2, 1 }, { 2, 3, 2, 2, 1, 3, 3 }, { 1, 1, 3, 1, 1, 2, 2 },
			{ 3, 2, 2, 3, 2, 3, 1 }, { 2, 2, 3, 2, 2, 2, 1 }, { 1, 1, 1, 2, 2, 1, 2 }, { 3, 1, 2, 2, 2, 2, 1 },
			{ 2, 2, 3, 3, 3, 2, 3 }, { 2, 2, 2, 2, 1, 1, 1 }, { 1, 2, 2, 2, 2, 1, 1 }, { 3, 2, 2, 1, 2, 2, 1 },
			{ 3, 2, 3, 2, 1, 1, 1 }, { 1, 1, 1, 1, 3, 2, 1 }, { 2, 2, 3, 2, 1, 2, 2 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 2, 3, 2, 1, 2, 3, 3 }, { 2, 2, 2, 3, 2, 1, 1 }, { 2, 2, 2, 2, 3, 3, 2 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 2, 3, 2, 1, 2, 1, 2 }, { 1, 3, 3, 1, 2, 1, 3 }, { 2, 2, 2, 3, 3, 2, 1 }, { 3, 2, 2, 2, 1, 2, 1 },
			{ 2, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 1, 3 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 3, 3 }, { 2, 3, 2, 2, 2, 3, 3 },
			{ 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 1, 2, 1, 1 }, { 2, 2, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 1, 3, 2, 1, 1 }, { 2, 2, 2, 3, 3, 3, 2 }, { 2, 2, 2, 3, 2, 1, 1 },
			{ 1, 1, 1, 2, 2, 2, 1 }, { 1, 2, 3, 3, 2, 2, 2 }, { 1, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 3, 2, 1, 1 },
			{ 2, 1, 2, 2, 2, 2, 1 }, { 2, 2, 1, 2, 3, 3, 3 }, { 2, 2, 2, 1, 2, 1, 2 }, { 3, 2, 3, 2, 2, 1, 1 },
			{ 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 3, 3 }, { 2, 1, 2, 2, 2, 3, 2 }, { 2, 1, 2, 2, 3, 2, 1 },
			{ 2, 2, 1, 2, 1, 1, 1 }, { 3, 3, 3, 2, 3, 2, 3 }, { 3, 3, 3, 2, 2, 1, 2 }, { 1, 3, 3, 2, 1, 1, 1 },
			{ 2, 2, 2, 2, 3, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 3, 2, 2, 3, 3 }, { 2, 1, 1, 1, 2, 1, 1 },
			{ 2, 1, 1, 1, 2, 1, 1 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 1, 1 }, { 3, 1, 2, 2, 2, 2, 1 },
			{ 3, 2, 1, 2, 2, 2, 2 }, { 1, 1, 1, 2, 2, 2, 1 }, { 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 2, 1, 2, 2, 1 },
			{ 3, 2, 2, 3, 2, 2, 2 }, { 2, 2, 1, 2, 3, 2, 3 }, { 2, 3, 2, 2, 1, 2, 3 }, { 2, 1, 1, 1, 2, 3, 2 },
			{ 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 3, 2, 3, 2, 1 }, { 2, 2, 2, 2, 2, 2, 1 }, { 3, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 1, 2, 3, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 1, 2, 2, 2, 2, 1, 2 }, { 2, 2, 2, 1, 2, 1, 1 },
			{ 1, 2, 2, 1, 2, 2, 2 }, { 1, 1, 2, 1, 1, 1, 1 }, { 3, 1, 2, 3, 2, 2, 2 }, { 2, 3, 2, 2, 2, 1, 1 },
			{ 1, 2, 3, 3, 3, 3, 1 }, { 2, 1, 2, 1, 1, 1, 1 }, { 2, 2, 1, 3, 2, 3, 3 }, { 2, 3, 2, 2, 2, 2, 2 },
			{ 2, 3, 1, 1, 3, 3, 3 }, { 2, 2, 2, 1, 2, 1, 1 }, { 2, 3, 2, 2, 3, 2, 3 }, { 2, 2, 1, 2, 3, 1, 1 },
			{ 2, 2, 2, 3, 3, 1, 1 }, { 3, 2, 1, 1, 2, 1, 2 }, { 3, 2, 2, 2, 2, 2, 2 }, { 1, 2, 1, 1, 1, 1, 1 },
			{ 3, 2, 2, 2, 2, 1, 1 }, { 1, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 3, 2, 2, 1 }, { 1, 1, 1, 3, 3, 2, 1 },
			{ 1, 1, 1, 1, 2, 1, 1 }, { 2, 3, 3, 2, 3, 2, 3 }, { 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 3, 2, 3, 1, 1, 1, 2 }, { 2, 2, 2, 2, 2, 2, 1 },
			{ 3, 2, 2, 2, 3, 1, 1 }, { 2, 3, 3, 3, 2, 3, 3 }, { 2, 2, 2, 2, 1, 2, 1 }, { 2, 3, 2, 2, 2, 1, 3 },
			{ 3, 2, 2, 1, 1, 1, 1 }, { 2, 2, 2, 2, 1, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 2, 2, 2, 2, 1, 1, 1 },
			{ 1, 1, 3, 2, 2, 1, 1 }, { 2, 3, 3, 2, 2, 2, 2 }, { 2, 2, 2, 1, 2, 2, 1 }, { 2, 2, 2, 2, 1, 1, 1 },
			{ 2, 2, 2, 1, 2, 1, 1 }, { 2, 2, 2, 3, 2, 2, 1 }, { 2, 3, 2, 2, 2, 1, 1 }, { 2, 1, 1, 1, 2, 2, 2 },
			{ 2, 3, 2, 2, 2, 2, 3 }, { 2, 2, 2, 1, 1, 1, 1 }, { 2, 3, 2, 2, 2, 3, 3 }, { 2, 2, 2, 1, 1, 1, 1 },
			{ 3, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 3, 3, 3, 3, 3 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 3, 2, 3, 3, 2, 3, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 2, 1, 3, 2 }, { 2, 2, 1, 1, 2, 3, 3 },
			{ 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 3, 3, 2, 1 }, { 2, 2, 2, 2, 2, 3, 1 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 1, 1, 1, 1 }, { 2, 2, 1, 2, 2, 1, 1 }, { 1, 2, 3, 2, 2, 2, 1 }, { 1, 2, 1, 2, 1, 1, 3 },
			{ 3, 1, 2, 2, 1, 1, 1 }, { 1, 2, 3, 2, 1, 3, 3 }, { 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 3, 3, 2, 1, 1 }, { 2, 3, 3, 2, 2, 3, 3 }, { 2, 2, 2, 2, 2, 2, 1 }, { 2, 1, 2, 2, 1, 1, 1 },
			{ 2, 3, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 1, 1 }, { 2, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 3, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 3, 2, 1, 1 }, { 1, 1, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 2 },
			{ 2, 2, 2, 3, 3, 3, 1 }, { 2, 3, 3, 2, 2, 2, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 1, 2, 2, 2 },
			{ 2, 3, 2, 2, 2, 1, 1 }, { 1, 2, 2, 1, 2, 1, 2 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 2 }, { 2, 1, 2, 1, 2, 1, 2 }, { 2, 2, 3, 2, 2, 2, 3 }, { 2, 2, 2, 2, 1, 1, 1 },
			{ 3, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 1, 3, 2, 1, 1, 1 }, { 3, 1, 3, 3, 2, 1, 1 },
			{ 1, 1, 2, 1, 1, 2, 2 }, { 3, 3, 2, 2, 3, 1, 1 }, { 2, 3, 1, 2, 2, 2, 2 }, { 2, 1, 2, 3, 2, 1, 1 },
			{ 2, 2, 1, 2, 2, 2, 2 }, { 2, 3, 2, 3, 2, 3, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 2, 3, 2, 2, 1, 1 },
			{ 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 2, 1 }, { 3, 2, 1, 2, 1, 1, 1 }, { 2, 3, 3, 2, 2, 1, 2 },
			{ 1, 2, 2, 2, 2, 1, 2 }, { 3, 3, 3, 2, 2, 1, 2 }, { 3, 3, 3, 3, 2, 1, 1 }, { 2, 1, 1, 2, 2, 3, 1 },
			{ 2, 2, 3, 1, 2, 3, 2 }, { 2, 1, 1, 2, 3, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 3, 2, 3, 1, 2 },
			{ 2, 2, 2, 1, 2, 3, 1 }, { 1, 2, 1, 2, 3, 2, 3 } };
}