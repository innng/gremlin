package src;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import org.epochx.gr.model.GRModel;
import org.epochx.life.GenerationAdapter;
import org.epochx.life.Life;
import org.epochx.stats.StatField;
import org.epochx.stats.Stats;

import src.*;

public class General {
	

	public static void main(String[] args) {
		DataSource source = null;
		Instances data = null;
		try {
			source = new DataSource("E:\\UbuntuBashFiles\\git\\epochx-weka\\weka-3-8-2\\data\\iris.arff");
			data = source.getDataSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(data.classIndex() == -1)
			data.setClassIndex(data.numAttributes() - 1);
		
		GRModel model = new TheGR();
		
		Life.get().addGenerationListener(new GenerationAdapter() {
			public void onGenerationEnd() {
				Stats.get().print(StatField.GEN_NUMBER,
								  StatField.GEN_FITNESS_MIN,
								  StatField.GEN_FITTEST_PROGRAM);
			}
		});
		
		model.run();

	}

	
}
