package main.java.GeneticAlgorithm.StudentGrouping;

import main.java.GeneticAlgorithm.Common.PermutationChromosome;

public class StudentGroups extends PermutationChromosome {

	public final static int MAXIMUM_STUDENTS = 512;
	//public final static int MAXIMUM_STUDENTS = 12;
	
	public StudentGroups() {
		super(StudentGroups.MAXIMUM_STUDENTS);
	}

	public StudentGroups(int[] groups) {
		super(groups);
	}
	
	/**
	 * Fitness is a measure of the overall heterogeneity of ALL groups, 
	 * invalid groups aren't counted. 
	 * 
	 * Loop must iterate through the entire 'class' of grouped students to get an
	 * accurate measure of the overall heterogeneity of all groups in that class.
	 * 
	 * @return sumGh double - the sum of all GH of only valid groups 
	 */
	@Override
	public double getFitness(){
		Boolean allValidGroups = true;
		double sumGh = 0;

		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];
			
			if (StudentScores.getMaxDistance(s1, s2, s3, s4) <= 2) {
				// only valid groups can contribute to the overall heterogenity of all groups
				// continue on with loop to measure all valid groups
				allValidGroups = false;
				continue; 
			}

			double gh = StudentScores.getGhValue(s1, s2, s3, s4);
			if (gh < 0.5) {
				// only valid groups can contribute to the overall heterogenity of all groups
				// continue on with loop to measure all valid groups
				allValidGroups = false;
				continue;
			}			

			sumGh += gh;
		}
		
		if( allValidGroups == true ){
			// @TODO - do something better than this cheap shot
			sumGh = 999999.999999;
		}
		
		return sumGh;
	}

	@Override
	public int[] getEncoding() {
		return this.getPermutationEncoding();
	}
	
	/**
	 * Required for the output, intended for use once the best/fittest class has been 
	 * selected. 
	 * 
	 * @return Array  
	 */
	@Override
	public String[] getMembers() {
		int dimension1 = StudentGroups.MAXIMUM_STUDENTS / 4;
		String[] members = new String[dimension1];

		// loop through bestGroup to get individual members
		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int i1 = this.getEncoding()[(i * 4)];
			int i2 = this.getEncoding()[(i * 4) + 1];
			int i3 = this.getEncoding()[(i * 4) + 2];
			int i4 = this.getEncoding()[(i * 4) + 3];
			
			String s1 = Integer.toString(i1);
			String s2 = Integer.toString(i2);
			String s3 = Integer.toString(i3);
			String s4 = Integer.toString(i4);

			members[i] = s1 +","+ s2 + ","+ s3 +","+ s4;

		}

		return members;

	}
	
	/**
	 * Output the highest Euclidean distance of each group.
	 * 
	 * @return double eachGroupMaxDistance
	 */
	@Override
	public double[] getEachGroupMaxDistance() {
		double[] eachGroupMaxDistance = new double[(StudentGroups.MAXIMUM_STUDENTS / 4)];
		// loop through bestGroup to get individual members
		for (int i = 0; i < StudentGroups.MAXIMUM_STUDENTS / 4; i++) {

			int s1 = this.getEncoding()[(i * 4)];
			int s2 = this.getEncoding()[(i * 4) + 1];
			int s3 = this.getEncoding()[(i * 4) + 2];
			int s4 = this.getEncoding()[(i * 4) + 3];

			// set the variable with values 
			eachGroupMaxDistance[i] = StudentScores.getMaxDistance(s1, s2, s3, s4);
		}

		return eachGroupMaxDistance;

	}
}
