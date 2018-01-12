package epochx_weka;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.core.Utils;
import weka.core.OptionHandler;

public final class WekaManager {
	
	private static Instances data = null;
	private Filter filter = null;
	private Classifier classifier = null;
	private Evaluation eval = null;
	
	public WekaManager() {
		
	}
	
	public void setInstance(String path) {
		DataSource source = null;
		
		try {
			source = new DataSource(path);
			data = source.getDataSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
	}

	public void setClassifier(String name, String options) {
		this.classifier = Classifier.forName(name, Utils.splitOptions(options));
	}
	
	public void setFilter(String name, String options) {
		this.filter = Filter.forName(name, Utils.splitOptions(options));
	}
}
