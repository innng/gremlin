package src;

import weka.classifiers.MultipleClassifiersCombiner;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import java.util.Enumeration;
import weka.core.Option;

public class TheClassifier extends MultipleClassifiersCombiner {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4128335545805988597L;
	
	public TheClassifier() {
		
	}

	@Override
	public void buildClassifier(Instances arg0) throws Exception {
		// TODO Auto-generated method stub
		
//		GRModel model = new TheGR();
//		
//		Life.get().addGenerationListener(new GenerationAdapter() {
//			public void onGenerationEnd() {
//				Stats.get().print(StatField.GEN_NUMBER,
//								  StatField.GEN_FITNESS_MIN,
//								  StatField.GEN_FITTEST_PROGRAM);
//			}
//		});
//		
//		model.run();

	}
	
	/**
	 * returns a short description that is displayed in the GUI, like the 
	 * Explorer or Experimenter.  How long this description will be is really 
	 * up to you, but it should be sufficient to understand the classifier’s 
	 * underlying algorithm. If the classifier implements the 
	 * weka.core.TechnicalInformationHandler interface then you could refer to 
	 * the publication(s) by extending the returned string by 
	 * getTechnicalInformation().toString().
	 * @return
	 */
	public String globalInfo() {
		return null;
	}
	
	/**
	 * returns a java.util.Enumeration of weka.core.Option objects. This 
	 * enumeration is used to display the help on the command-line, hence it 
	 * needs to return the Option objects of the superclass as well.
	 */
	public Enumeration<Option> listOptions() {
		return null;
		
	}
	
	/**
	 * parses the options that the classifier would receive from a commandline 
	 * invocation. A parameter and argument are always two elements in the 
	 * string array. A common mistake is to use a single cell in the string 
	 * array for both of them, e.g., "-S 1" instead of "-S","1". You can use the 
	 * methods getOption and getFlag of the weka.core.Utils class to retrieve 
	 * the values of an option or to ascertain whether a flag is present. But 
	 * note that these calls remove the option and, if applicable, the argument 
	 * from the string array (“destructive”). The last call in the setOptions
	 * methods should always be the super.setOptions(String[]) one, in order to 
	 * pass on any other arguments still present in the array to the superclass.
	 */
	public void setOptions(String[] options) {
		
	}
	
	/**
	 * returns a string array of command-line options that resemble the current 
	 * classifier setup. Supplying this array to the setOptions(String[]) method 
	 * must result in the same configuration. This method will get called in the 
	 * GUI when copying a classifier setup to the clipboard. Since handling of 
	 * arrays is a bit cumbersome in Java (due to fixed length), using an 
	 * instance of java.util.Vector is a lot easier for creating the array that 
	 * needs to be returned.
	 */
	public String[] getOptions() {
		return null;
		
	}
	
	/**
	 * returns meta-information on what type of data the classifier can handle, 
	 * in regards to attributes and class attributes.
	 */
	public Capabilities getCapabilities() {
		return null;
		
	}
	
	/**
	 * is used for outputting the built model. This is not required, but it is 
	 * useful for the user to see properties of the model. Decision trees 
	 * normally ouput the tree, support vector machines the support vectors and 
	 * rule-based classifiers the generated rules.
	 */
	public String toString() {
		return null;
		
	}
	
	/**
	 * returns the classification or regression for the given 
	 * weka.core.Instance object. In case of a nominal class attribute, this 
	 * method returns the index of the class label that got predicted. You do 
	 * not need to override this method in this case as the 
	 * weka.classifiers.Classifier superclass already determines the class label
	 * index based on the probabilities array that the 
	 * distributionForInstance(Instance) method returns (it returns the index 
	 * in the array with the highest probability; in case of ties the first one).
	 * For numeric class attributes, you need to override this method, as it has
	 * to return the regression value predicted by the model.
	 */
	public double classifyInstance(Instance data) {
		return 0;
		
	}	
}
