/***********************************************************************

	This file is part of KEEL-software, the Data Mining tool for regression, 
	classification, clustering, pattern mining and so on.

	Copyright (C) 2004-2010
	
	F. Herrera (herrera@decsai.ugr.es)
    L. S�nchez (luciano@uniovi.es)
    J. Alcal�-Fdez (jalcala@decsai.ugr.es)
    S. Garc�a (sglopez@ujaen.es)
    A. Fern�ndez (alberto.fernandez@ujaen.es)
    J. Luengo (julianlm@decsai.ugr.es)

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see http://www.gnu.org/licenses/
  
**********************************************************************/

package keel.Algorithms.Neural_Networks.IRPropPlus_Regr;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import keel.Algorithms.Neural_Networks.NNEP_Common.data.DoubleTransposedDataSet;
import keel.Algorithms.Neural_Networks.NNEP_Common.data.IAttribute;
import keel.Algorithms.Neural_Networks.NNEP_Common.neuralnet.INeuralNet;
import keel.Algorithms.Neural_Networks.NNEP_Common.problem.ProblemEvaluator;
import keel.Algorithms.Neural_Networks.NNEP_Regr.problem.errorfunctions.MSEErrorFunction;
import keel.Algorithms.Neural_Networks.NNEP_Regr.problem.errorfunctions.SEPErrorFunction;
import keel.Algorithms.Neural_Networks.NNEP_Regr.problem.regression.IRegressor;
import keel.Algorithms.Neural_Networks.NNEP_Regr.problem.regression.RegressionProblemEvaluator;
import net.sf.jclec.IEvaluator;
import net.sf.jclec.base.AbstractIndividual;


/**
 * <p>
 * @author Written by Pedro Antonio Gutierrez Penia (University of Cordoba) 23/11/2007
 * @version 0.1
 * @since JDK1.5
 * </p>
 */

public class IRPropPlusReporterRegr {
	
	/**
	 * <p>
	 * Reporter for iRProp+ algorithm for regression
	 * </p>
	 */

	/////////////////////////////////////////////////////////////////
	// --------------------------------------- Serialization constant
	/////////////////////////////////////////////////////////////////

	/**
	 * Generated by Eclipse
	 */

	private static final long serialVersionUID = 873929825900558241L;

	/////////////////////////////////////////////////////////////////
	// --------------------------------------------------- Properties
	/////////////////////////////////////////////////////////////////
	
	/** MSE Error function */

	private MSEErrorFunction mseErrorFunction = new MSEErrorFunction();

	/** SEP Error function */

	private SEPErrorFunction sepErrorFunction = new SEPErrorFunction();

	/** KEEL headers of output files */

	private String header;

	/** Train result file */

	private String trainResultFile;

	/** Test result file */

	private String testResultFile;

	/** Best model result file */

	private String bestModelResultFile;

	/** Metadata information of output attribute for generating output files */

	private IAttribute outputAttribute;

	/////////////////////////////////////////////////////////////////
	// -------------------------------------------------- Constructor
	/////////////////////////////////////////////////////////////////

	/**
	 * <p>
	 * Empty constructor
	 * </p>
	 */

	public IRPropPlusReporterRegr() {
		super();
	}

	/////////////////////////////////////////////////////////////////
	// ------------------------------- Getting and setting properties
	/////////////////////////////////////////////////////////////////

	/**
	 * <p>
	 * Returns file name where the best model obtained will be written
	 *
	 * @return String File name
	 * </p>
	 */

	public String getBestModelResultFile() {
		return bestModelResultFile;
	}

	/**
	 * <p>
	 * Sets file name where the best model obtained will be written
	 *
	 * @param bestModelResultFile File name
	 * </p>
	 */

	public void setBestModelResultFile(String bestModelResultFile) {
		this.bestModelResultFile = bestModelResultFile;
	}

	/**
	 * <p>
	 * Returns file name where the testing results of best model 
	 * obtained will be written
	 *
	 * @return String File name
	 * </p>
	 */

	public String getTestResultFile() {
		return testResultFile;
	}

	/**
	 * <p>
	 * Sets file name where the testing results of best model 
	 * obtained will be written
	 *
	 * @param testResultFile File name
	 * </p>
	 */

	public void setTestResultFile(String testResultFile) {
		this.testResultFile = testResultFile;
	}

	/**
	 * <p>
	 * Returns file name where the training results of best model 
	 * obtained will be written
	 *
	 * @return String File name
	 * </p>
	 */

	public String getTrainResultFile() {
		return trainResultFile;
	}

	/**
	 * <p>
	 * Sets file name where the testing results of best model 
	 * obtained will be written
	 *
	 * @param trainResultFile File name
	 * </p>
	 */

	public void setTrainResultFile(String trainResultFile) {
		this.trainResultFile = trainResultFile;
	}

	/**
	 * <p>
	 * Returns KEEL file header
	 *
	 * @return String KEEL file header
	 * </p>
	 */

	public String getHeader() {
		return header;
	}

	/**
	 * <p>
	 * Sets KEEL file header
	 *
	 * @param header KEEL file header
	 * </p>
	 */

	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * <p>
	 * Returns output attribute metadata
	 *
	 * @return IAttribute Output attribute metadata
	 * </p>
	 */

	public IAttribute getOutputAttribute() {
		return outputAttribute;
	}

	/**
	 * <p>
	 * Sets output attribute metadata
	 *
	 * @param outputAttribute New output attribute metadata
	 * </p>
	 */

	public void setOutputAttribute(IAttribute outputAttribute) {
		this.outputAttribute = outputAttribute;
	}

	/////////////////////////////////////////////////////////////////
	// ----------------------------------------------- Public Methods
	/////////////////////////////////////////////////////////////////


	/**
	 * <p>
	 * This method is called when the algorithm has finished its execution.
	 * 
	 * @param resultIndividual Final resulting individual of iRProp+ algorithm.
	 * @param evaluator Evaluator used to obtain errors.
	 * </p>
	 */ 

	@SuppressWarnings("unchecked")
	public void algorithmFinished(AbstractIndividual<INeuralNet> resultIndividual, ProblemEvaluator evaluator) {

		try 
		{
			PrintWriter print = new PrintWriter( new FileWriter ( trainResultFile ) );
			print.write(header);

			DoubleTransposedDataSet dataset = evaluator.getTrainData();
			double[] observedOutput = evaluator.getUnscaledTrainData().getAllOutputs()[0];

			IRegressor bestRegressor = (IRegressor) resultIndividual.getGenotype();
			double[] predictedOutput = bestRegressor.operate(dataset.getAllInputs());

			// Unscale the outputs
			evaluator.getNormalizer().scale(predictedOutput, evaluator.getOutputInterval().getRight(), evaluator.getOutputInterval().getLeft(),
					evaluator.getUnscaledMax()[dataset.getNofinputs()], evaluator.getUnscaledMin()[dataset.getNofinputs()]);

			// Print train results		
			for(int i=0; i<dataset.getNofobservations(); i++){
				print.write(outputAttribute.show(observedOutput[i]) + " ");
				print.write(outputAttribute.show(predictedOutput[i]) + "\n");
			}

			print.close();

			// Print test results		
			print = new PrintWriter( new FileWriter ( testResultFile ) );
			print.write(header);

			dataset = evaluator.getTestData();

			observedOutput = evaluator.getUnscaledTestData().getAllOutputs()[0];
			predictedOutput = bestRegressor.operate(dataset.getAllInputs());

			// Unscale the outputs
			evaluator.getNormalizer().scale(predictedOutput, evaluator.getOutputInterval().getRight(), evaluator.getOutputInterval().getLeft(),
					evaluator.getUnscaledMax()[dataset.getNofinputs()], evaluator.getUnscaledMin()[dataset.getNofinputs()]);

			for(int i=0; i<dataset.getNofobservations(); i++){
				print.write(outputAttribute.show(observedOutput[i]) + " ");
				print.write(outputAttribute.show(predictedOutput[i]) + "\n");
			}

			print.close();

			// Print test results		
			print = new PrintWriter( new FileWriter ( bestModelResultFile ) ); 

			print.write(renderNeuralNetIndividual(resultIndividual, evaluator));

			print.close();
		}
		catch ( IOException e )
		{
			System.err.println( "Can not open the training output file: " + e.getMessage() );
		}
	}

	/**
	 * <p>
	 * Renders a NeuralNetIndividual to a String
	 *
	 * @param nnind IGenotype<INeuralNet> to render
	 * @param evaluator NeuralNetEvaluator to use in individual evaluation
	 * 
	 * @return String String with the render of a NeuralNetIndividual
	 * </p>
	 */

	@SuppressWarnings("unchecked")
	public String renderNeuralNetIndividual(AbstractIndividual<INeuralNet> nnind, IEvaluator evaluator){
		
		String result = nnind.toString();
		result+="\n";

		result+=("Number of hidden neurons: " + nnind.getGenotype().getNofhneurons());
		result+=(" Number of effective links: "+ nnind.getGenotype().getNoflinks() + "\n");

		IRegressor regressor = (IRegressor) nnind.getGenotype();
		result+=("Train MSE: " + ((RegressionProblemEvaluator)evaluator).getTrainRegressionError( regressor, mseErrorFunction) + "\n");
		result+=("Test  MSE: " + ((RegressionProblemEvaluator)evaluator).getTestRegressionError( regressor, mseErrorFunction) + "\n");
		result+=("Train SEP: " + ((RegressionProblemEvaluator)evaluator).getTrainRegressionError( regressor, sepErrorFunction) + "\n");
		result+=("Test  SEP: " + ((RegressionProblemEvaluator)evaluator).getTestRegressionError( regressor, sepErrorFunction) + "\n");

		return result;
	}
}

