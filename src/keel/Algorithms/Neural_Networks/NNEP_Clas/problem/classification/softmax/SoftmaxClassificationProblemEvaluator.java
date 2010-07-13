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

package keel.Algorithms.Neural_Networks.NNEP_Clas.problem.classification.softmax;

import java.util.Comparator;

import keel.Algorithms.Neural_Networks.NNEP_Common.data.DoubleTransposedDataSet;
import keel.Algorithms.Neural_Networks.NNEP_Common.problem.errorfunctions.IErrorFunction;
import net.sf.jclec.IConfigure;
import net.sf.jclec.IFitness;
import net.sf.jclec.base.AbstractIndividual;
import net.sf.jclec.fitness.SimpleValueFitness;
import net.sf.jclec.fitness.ValueFitnessComparator;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationRuntimeException;

/**
 * <p>
 * @author Written by Pedro Antonio Gutierrez Penia (University of Cordoba) 16/7/2007
 * @author Modified by Aaron Ruiz Mora (University of Cordoba) 16/7/2007
 * @version 0.1
 * @since JDK1.5
 */

public class SoftmaxClassificationProblemEvaluator extends ClassificationProblemEvaluator implements IConfigure {
	
	/** 
	 * <p>
	 * Regression problem evaluator
	 * </p>
	 */
	
	/////////////////////////////////////////////////////////////////
	// --------------------------------------- Serialization constant
	/////////////////////////////////////////////////////////////////
	
	/** Generated by Eclipse */
	
	private static final long serialVersionUID = 5628503640645851126L;
	
	/////////////////////////////////////////////////////////////////
	// ----------------------------------------------- Error Function
	/////////////////////////////////////////////////////////////////
	
	/** Error function to evaluate classificator */
	
	IErrorFunction<double[][]>  defaultErrorFunction;
	
	/////////////////////////////////////////////////////////////////
	// ------------------------------------------- Fitness comparator
	/////////////////////////////////////////////////////////////////
	
	/** Fitnesses comparator */
	
	protected Comparator<IFitness> comparator = 
		new ValueFitnessComparator(false);
	
	/////////////////////////////////////////////////////////////////
	// -------------------------------------------------- Constructor
	/////////////////////////////////////////////////////////////////
    
    /**
     * <p>
     * Empty constructor
     * </p>
     */
    
    public SoftmaxClassificationProblemEvaluator() {
        super();
    }

	/////////////////////////////////////////////////////////////////
	// ------------------------------- Getting and setting properties
	/////////////////////////////////////////////////////////////////
	
	/**
	 * <p>
	 * Returns error function
	 * 
	 * @return IErrorFunction<double[][]> Error function
	 * </p>
	 */
	
	public IErrorFunction<double[][]> getErrorFunction() 
	{
		return defaultErrorFunction;
	}

	/**
	 * <p>
	 * Sets error function
	 * 
	 * @param errorFunction error function
	 * </p>
	 */
	
	public void setErrorFunction(IErrorFunction<double[][]> errorFunction) 
	{
		this.defaultErrorFunction = errorFunction;
	}
	
	/////////////////////////////////////////////////////////////////
	// ------------------------ Overwriting AbstractEvaluator methods
	/////////////////////////////////////////////////////////////////
	
	/**
	 * <p>
	 * Returns a ValueFitnessComparator
	 * </p> 
	 */
	
	public Comparator<IFitness> getComparator() {
		return comparator;
	}

	/**
	 * <p>
	 *  Evaluates a individual
	 *  @param ind Individual to evaluate
	 * </p>
	 */
	public void evaluate(AbstractIndividual<? extends ISoftmaxClassifier> ind) {
		// Dataset to be used
		DoubleTransposedDataSet dataset;
		if(dataNormalized)
			dataset = scaledTrainData;
		else
			dataset = unscaledTrainData;
				
		// Obtained outputs with this dataSet
		double obtained[][] = ind.getGenotype().rawOutputs(dataset.getAllInputs());			
		
		// Obtain error
		double error = defaultErrorFunction.calculateError(obtained,dataset.getAllOutputs());
		
        //Set the fitness of the individual as (1/1+Error)
        ind.setFitness(new SimpleValueFitness(1/(1+error)));        
	}
	
	/////////////////////////////////////////////////////////////////
	// ---------------------------- Implementing IConfigure interface
	/////////////////////////////////////////////////////////////////
	
	/**
	 * <p>
	 * Configuration parameters for NeuralNetEvaluator are:
	 * Problem evaluator configuration
	 *       org.ayrna.jclec.problem.ProblemEvaluator
	 * 
	 * error-function: complex 
	 *       Error function used for evaluating individuals
	 * </p>
	 */

	@SuppressWarnings("unchecked")
    public void configure(Configuration settings) {
    	
    	// ProblemEvaluator configuration
    	super.configure(settings);
    	
    	// Individual error function
		try {
			// Error function classname
			String errorFunctionClassname = 
				settings.getString("error-function");
			// Error function class
			Class<IErrorFunction<double[][]>> errorFunctionClass = 
				(Class<IErrorFunction<double[][]>>) Class.forName(errorFunctionClassname);
			// Error function instance
			IErrorFunction<double[][]> errorFunction = errorFunctionClass.newInstance();
			// Set error function
			setErrorFunction(errorFunction);
		} 
		catch (ClassNotFoundException e) {
			throw new ConfigurationRuntimeException("Illegal error function classname");
		} 
		catch (InstantiationException e) {
			throw new ConfigurationRuntimeException("Problems creating an instance of error function", e);
		} 
		catch (IllegalAccessException e) {
			throw new ConfigurationRuntimeException("Problems creating an instance of error function", e);
		}
    	
    }

}

