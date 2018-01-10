package epochx_weka;

import org.epochx.gp.model.GPModel;
import org.epochx.life.GenerationAdapter;
import org.epochx.life.Life;
import org.epochx.stats.StatField;
import org.epochx.stats.Stats;
import org.epochx.gp.model.EvenParity;

public class Main {

	public static void main(String[] args) {
		GPModel model = new EvenParity(4);	// cria modelo
		//model.setCrossover(new UniformPointCrossover(model));
		Life.get().addGenerationListener(new GenerationAdapter() {	//define estat�sticas
			public void onGenerationEnd() { 
				Stats.get().print(StatField.GEN_NUMBER, 
								  StatField.GEN_FITNESS_MIN,
								  StatField.GEN_FITTEST_PROGRAM);
			}	//
		});
		model.run();
		
		/*Colunas:*/
		/*|| Numero Gera��o | Melhor Fitness | Melhor Programa ||*/

	}

}
