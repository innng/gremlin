package src;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

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
		
		System.out.println("oi");
	}

	
}
