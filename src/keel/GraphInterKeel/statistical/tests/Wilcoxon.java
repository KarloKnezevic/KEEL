/**
 * File: Wilcoxon.java.
 *
 * Wilcoxon signed ranks test
 *
 * @author Written by Joaquin Derrac (University of Granada) 1/12/2010
 * @version 1.0
 * @since JDK1.5
 */
package keel.GraphInterKeel.statistical.tests;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import keel.GraphInterKeel.statistical.Configuration;
import org.core.*;

public class Wilcoxon {

    private static DecimalFormat nf;

	private static double wilcoxonRanks [][];
	private static double exactPValues[][];
	private static double asymptoticPValues[][];
	private static String confidenceIntervals95[][];
	private static String confidenceIntervals90[][];
	private static double exactConfidence90[][];
	private static double exactConfidence95[][];

    private static double data[][];
    private static int columns;
    private static int rows;
    private static String algorithms [];

	/**
	* Builder
	*/
	public Wilcoxon(){

	}//end-method

    /**
     * In this method, all possible pairwise Wilcoxon comparisons are performed
     *
     * @param newData Array with the results of the method
     * @param newAlgorithms A vector of String with the names of the algorithms
     */
	public static void doWilcoxon(double newData[][], String newAlgorithms[]) {

		String outputFileName = Configuration.getPath();

        String outputString = new String("");
	    outputString = header();

        data=new double [newData[0].length][newData.length];
        algorithms=new String [newAlgorithms.length];
        columns=data[0].length;
        rows=data.length;

        //reverse data matrix
        for(int i=0;i<data.length; i++){
            for(int j=0;j<data[0].length; j++){
                data[i][j]=newData[j][i];
            }
        }
        System.arraycopy(newAlgorithms, 0, algorithms, 0, newAlgorithms.length);

        wilcoxonRanks=new double[columns][columns];
		exactPValues=new double[columns][columns];
		asymptoticPValues=new double[columns][columns];
		confidenceIntervals95=new String [columns][columns];
		confidenceIntervals90=new String [columns][columns];
		exactConfidence90=new double[columns][columns];
		exactConfidence95=new double[columns][columns];

        nf = (DecimalFormat) DecimalFormat.getInstance();
		nf.setMaximumFractionDigits(6);
		nf.setMinimumFractionDigits(0);

		DecimalFormatSymbols dfs = nf.getDecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		nf.setDecimalFormatSymbols(dfs);

	    outputString += computeBody();

        outputString += footer();

	    Files.writeFile(outputFileName, outputString);

	}//end-method


    /**
     * Computes body of the report file (i.e. the test itself)
     *
     * @return Contents of report
     */
    public static String computeBody() {

		double value;
		String text;

		for(int first=0;first<columns;first++){
			for(int second=0;second<columns;second++){
                if(first!=second){
                    computeRanks(first,second);
                }
			}
		}

		text="\n";
		
		//print the rank matrix

        text+="\\begin{table}[!htp]\n\\centering\\scriptsize\n" + "\\begin{tabular}{\n";
        text+="|c";
        for (int i=0; i<columns; i++) {
        	text +="|r";
        }
        text+="|}\n\\hline\n";

        for (int i=0; i<columns; i++) {
        	text +="&" + algorithms[i];
        }
        text +="\\\\\n\\hline\n";
        for (int i=0; i<columns; i++) {
        	text +=algorithms[i];
        	for (int j=0; j<columns; j++) {
                if(i!=j){
                    text +="& "+wilcoxonRanks[i][j];
                }
                else{
                    text +="& -";
                }
        	}
        	text +="\\\\\n\\hline\n";
        }

        text +="\n" + "\\end{tabular}\n" + "\\caption{Ranks computed by the Wilcoxon test}\n\\end{table}\n";
        text +="\n\\end{landscape}\n";
        text +="\n \\clearpage \n\n";

        //print individual comparisons

        for(int first=0;first<columns;first++){
             text +="\n\\section{Detailed results for "+algorithms[first]+"}\n\n";
             text +="\n\\subsection{Results}\n\n";
             text+="\\begin{table}[!htp]\n\\centering\\small\n" + "\\begin{tabular}{\n";
             text+="|c|c|c|c|c|";
             text+="}\n\\hline\n";
             text+=" VS & $R^{+}$ & $R^{-}$ & Exact P-value & Asymptotic P-value \\\\ \\hline \n";
             for(int second=0;second<columns;second++){
                if(first!=second){
                    text+=algorithms[second]+" & "+wilcoxonRanks[first][second]+" & "+wilcoxonRanks[second][first]+" & ";

                    if(rows<51){
                        value=exactPValues[first][second];
                        if(value!=1.0){
                            text +=value+" & ";
                        }
                        else{
                            text +="$\\geq$ 0.2 & ";
                        }
                    }
                    else{
                        text+="- & ";
                    }

                    value=asymptoticPValues[first][second];
                    text+=nf.format(value);
                    text+="\\\\ \\hline \n";
                }
             }

            text +="\n" + "\\end{tabular}\n" + "\\caption{Results obtained by the Wilcoxon test for algorithm "+algorithms[first]+"}\n\\end{table}\n";

            text +="\n\\subsection{Confidence intervals for Median of diferences}\n\n";

            text+="\\begin{table}[!htp]\n\\centering\\small\n" + "\\begin{tabular}{\n";
            text+="|c|c|c|";
            text+="}\n\\hline\n";
            text+=" $\\alpha$=0.90 & Confidence interval & Exact confidence \\\\ \\hline \n";
            for(int second=0;second<columns;second++){
                if(first!=second){
                    text+=algorithms[second]+" & "+confidenceIntervals90[first][second]+" & "+nf.format(exactConfidence90[first][second])+"\\\\ \\hline \n";
                }
            }
            text +="\n" + "\\end{tabular}\n" + "\\caption{Confidence intervals for algorithm "+algorithms[first]+" ($\\alpha$=0.90)}\n\\end{table}\n";

            text+="\\begin{table}[!htp]\n\\centering\\small\n" + "\\begin{tabular}{\n";
            text+="|c|c|c|";
            text+="}\n\\hline\n";
            text+=" $\\alpha$=0.95 & Confidence interval & Exact confidence \\\\ \\hline \n";
            for(int second=0;second<columns;second++){
                if(first!=second){
                    text+=algorithms[second]+" & "+confidenceIntervals95[first][second]+" & "+nf.format(exactConfidence95[first][second])+"\\\\ \\hline \n";
                }
            }
            text +="\n" + "\\end{tabular}\n" + "\\caption{Confidence intervals for algorithm "+algorithms[first]+" ($\\alpha$=0.95)}\n\\end{table}\n";
            text +="\n \\clearpage \n\n";
        }
       
		return text;

	}//end-method

    /**
     * Compute ranks and associated p-values for a giver pair of samples
     *
     * @param first First sample
     * @param second Second sample
     */
	public static void computeRanks(int first,int second){

		double AOld [],A [];
		double BOld [],B [];
		double diffOld [], diff [];
		int ties,N,pointer;
		boolean sign[];
		double ranks[];
		double RA,RB;
		ArrayList<Double> walsh;
		int criticalN;
		String interval;

		AOld=new double[rows];
		BOld=new double[rows];
		diffOld=new double[rows];

		ties=0;

		for(int i=0;i<rows;i++){

            if(Configuration.getObjective()==1){
                AOld[i]=data[i][first];
                BOld[i]=data[i][second];
            }else{
                AOld[i]=data[i][second];
                BOld[i]=data[i][first];
            }

			diffOld[i]=Math.abs(AOld[i]-BOld[i]);

			if(diffOld[i]==0.0){
				ties++;
			}
		}

		N=rows-ties;

		A=new double[N];
		B=new double[N];
		diff=new double[N];
		sign=new boolean[N];
		ranks=new double[N];

		pointer=0;

		for(int i=0;i<rows;i++){

			if(diffOld[i]!=0.0){
				A[pointer]=AOld[i];
				B[pointer]=BOld[i];
				diff[pointer]=Math.abs(A[pointer]-B[pointer]);
				if((A[pointer]-B[pointer])>0.0){
					sign[pointer]=true;
				}
				else{
					sign[pointer]=false;
				}
				pointer++;
			}

		}

		//compute ranks
		double min;
		double points;
		int tied;
		String tiedString="";

		Arrays.fill(ranks, -1.0);

		for(int rank=1;rank<=N;){
			min=Double.MAX_VALUE;
			tied=1;

			for(int i=0;i<N;i++){
				if((ranks[i]==-1.0)&&diff[i]==min){
					tied++;
				}
				if((ranks[i]==-1.0)&&diff[i]<min){
					min=diff[i];
					tied=1;
				}

			}

			//min has the lower unassigned value
			if(tied==1){
				points=rank;
			}
			else{
				tiedString+=(tied+"-");
				points=0.0;
				for(int k=0;k<tied;k++){
					points+=(rank+k);
				}
				points/=tied;
			}

			for(int i=0;i<N;i++){
				if(diff[i]==min){
					ranks[i]=points;
				}
			}

			rank+=tied;
		}

		//compute sumOfRanks

		RA=0.0;
		RB=0.0;

		for(int i=0;i<ranks.length;i++){
			if(sign[i]){
				RA+=ranks[i];
			}
			else{
				RB+=ranks[i];
			}
		}

		//Treatment of 0's
		double increment;
		double sum0;
		if(ties>1){
			if(ties%2==1){
				increment=ties-1.0;
			}
			else{
				increment=ties;
			}

			//Adition of 0 ranked differences
			sum0=(((double)ties+1.0)*(double)ties)/2.0;
			sum0/=2.0;

			RA+=sum0;
			RB+=sum0;

			//Reescaling of the rest of ranks
			for(int i=0;i<ranks.length;i++){
				if(sign[i]){
					RA+=increment;
				}
				else{
					RB+=increment;
				}
			}
		}

		//save the ranks
		wilcoxonRanks[first][second]=RA;
		wilcoxonRanks[second][first]=RB;

		//compute exact pValue
		exactPValues[first][second]=WilcoxonDistribution.computeExactProbability(N, RB);
		exactPValues[second][first]=WilcoxonDistribution.computeExactProbability(N, RA);

		//compute asymptotic P Value

		int tiesDistribution [];

		tiesDistribution=decode(tiedString);

		asymptoticPValues[first][second]=WilcoxonDistribution.computeAsymptoticProbability(N, RB, tiesDistribution);
		asymptoticPValues[second][first]=WilcoxonDistribution.computeAsymptoticProbability(N, RA, tiesDistribution);

		//compute confidence intervals
		walsh=new ArrayList<Double>();

		double aux, aux2;
		for(int i=0;i<diffOld.length-1;i++){
			aux=AOld[i]-BOld[i];
			walsh.add(aux);
			for(int j=i+1;j<diffOld.length;j++){
				aux2=AOld[j]-BOld[j];
				walsh.add((aux+aux2)/2.0);
			}
		}

		Collections.sort(walsh);

		//Find critical levels

		criticalN=findCriticalValue(diffOld.length,0.05);
		criticalN=Math.max(criticalN,0);

		//Build interval
		interval="[";
        interval+=nf.format(walsh.get(criticalN));
        interval+=" , ";
        interval+=nf.format(walsh.get(walsh.size()-(criticalN+1)));
        interval+="]";

		confidenceIntervals95[first][second]=interval;
		exactConfidence95[first][second]=1.0-(WilcoxonDistribution.computeExactProbability(diffOld.length,criticalN));
		

		criticalN=findCriticalValue(diffOld.length,0.1);
        criticalN=Math.max(criticalN,0);

		//Build interval
		interval="[";
        interval+=nf.format(walsh.get(criticalN));
        interval+=" , ";
        interval+=nf.format(walsh.get(walsh.size()-(criticalN+1)));
        interval+="]";
        
		confidenceIntervals90[first][second]=interval;
		exactConfidence90[first][second]=1.0-(WilcoxonDistribution.computeExactProbability(diffOld.length,criticalN));
	}//end-method

    /**
     * Find the first critical value lower than alpha
     *
     * @param N N parameter
     * @param alpha Limit p-value
     *
     * @return Critical value
     */
	private static int findCriticalValue(int N, double alpha){

		double limit=alpha;
		int critical=-1;

		do{
			critical++;
		}while(WilcoxonDistribution.computeExactProbability(N, critical)<limit);

		critical--;

		return critical;
	}//end-method

    /**
     * Decodes an string of ties
     * @param cad String
     *
     * @return Integer array representation
     */
	private static int [] decode(String cad){

		int result [];
		String array [];

		if(cad.equals("")){
			result=new int [0];
		}
		else{
			array=cad.split("-");
			result=new int [array.length];
			for(int i=0;i<array.length;i++){
				result[i]=Integer.parseInt(array[i]);
			}
		}

		return result;

	}//end-method

    /**
     * Footer of the report
     *
     * @return Contents of the footer
     */
    private static String footer() {
         String output = new String("");

         output += "\n\\end{document}";

         return output;
    }//end-method

	/**
	* 
	* This method composes the header of the LaTeX file where the results are saved
    *
	* @return A string with the header of the LaTeX file
	*/   
	private static String header() {
        String output = new String("");
        output += "\\documentclass[a4paper,10pt]{article}\n";
        output += "\\usepackage{lscape}\n";
	    output += "\\title{Wilcoxon Signed Ranks test.}\n";
	    output += "\\date{\\today}\n\\author{KEEL non-parametric statistical module}\n\\begin{document}\n\n\\begin{landscape}\n\\pagestyle{empty}\n\\maketitle\n\\thispagestyle{empty}\n\\section{Global ranks}\n\n";
	        		
	    return output;

	}//end-method

}//end-class