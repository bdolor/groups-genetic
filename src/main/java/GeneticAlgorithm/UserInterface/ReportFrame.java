package main.java.GeneticAlgorithm.UserInterface;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import main.java.GeneticAlgorithm.Interfaces.IReport;

import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ReportFrame extends ApplicationFrame implements IReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private int Evolution = 0;
	private double maxValidFitness = 0;
	private XYSeries averageFitness;
	private XYSeries maxFitness;
	private XYSeries minFitness;
	private XYSeries averageValidGroups;
	private int validSolutions = 0;
	private JLabel ValidSolutionsLabel;

	/**
	 * Create the frame.
	 */
	public ReportFrame() {

		super("COMP-658 Computational Intelligence");
		setBounds(400, 400, 450, 300);

		final XYSeriesCollection fitnessDataset = new XYSeriesCollection();
		final XYSeriesCollection groupDataset = new XYSeriesCollection();

		this.averageFitness = new XYSeries("Average Fitness");
		this.averageValidGroups = new XYSeries("Valid Groups");
		this.maxFitness = new XYSeries("Best Fitness");
		this.minFitness = new XYSeries("Worst Fitness");

		fitnessDataset.addSeries(this.averageFitness);
		fitnessDataset.addSeries(this.maxFitness);
		fitnessDataset.addSeries(this.minFitness);
		groupDataset.addSeries(this.averageValidGroups);

		ChartPanel fitnessChartPanel = new ChartPanel(this.getFitnessChart(fitnessDataset));
		ChartPanel groupChartPanel = new ChartPanel(this.getGroupChart(groupDataset));

		fitnessChartPanel.setPreferredSize(new java.awt.Dimension(400, 270));
		groupChartPanel.setPreferredSize(new java.awt.Dimension(400, 270));

		this.ValidSolutionsLabel = new JLabel("Found 0 valid solutions.");

		// final JPanel content = new JPanel(new BorderLayout());

		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(boxLayout);

		this.add(fitnessChartPanel);
		this.add(groupChartPanel);
		this.add(this.ValidSolutionsLabel);

		// content.add(fitnessChartPanel,BorderLayout.CENTER);
		// content.add(groupChartPanel, BorderLayout.LINE_START);
		// content.add(this.ValidSolutionsLabel, BorderLayout.SOUTH);

		// this.setContentPane(content);

	}

	@Override
	public void updateReport(double averageFitness, double averageValidGroups, int validSolutions, double maxFitness,
			double minFitness, double maxValidFitness) {

		this.Evolution++;
		this.averageFitness.add(this.Evolution, averageFitness);
		this.averageValidGroups.add(this.Evolution, averageValidGroups);
		this.maxFitness.add(this.Evolution, maxFitness);
		this.minFitness.add(this.Evolution, minFitness);

		this.validSolutions += validSolutions;
		this.maxValidFitness = maxValidFitness > this.maxValidFitness ? maxValidFitness : this.maxValidFitness;

		this.ValidSolutionsLabel.setText(String.format("Found %d valid solutions.  Fittest solution is %f",
				this.validSolutions, this.maxValidFitness));

	}

	protected JFreeChart getFitnessChart(XYSeriesCollection dataset) {
		JFreeChart chart = ChartFactory.createXYLineChart("Fitness Measures", // chart
																				// title
				"Evolution", // x axis label
				"Fitness", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
		);

		final XYPlot plot = chart.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
		axis = plot.getRangeAxis();

		return chart;
	}

	protected JFreeChart getGroupChart(XYSeriesCollection dataset) {
		JFreeChart chart = ChartFactory.createXYLineChart("Group Information", // chart
																				// title
				"Evolution", // x axis label
				"Group Count", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
		);

		final XYPlot plot = chart.getXYPlot();
		ValueAxis axis = plot.getDomainAxis();
		axis.setAutoRange(true);
		// axis.setFixedAutoRange(60000.0); // 60 seconds
		axis = plot.getRangeAxis();
		// axis.setRange(0.0, 200.0);

		return chart;
	}

}
