import org.epochx.life.GenerationAdapter;
import org.epochx.life.Life;
import org.epochx.stats.Stats;
import weka.classifiers.AbstractClassifier;
import weka.core.Instances;

import java.io.File;
import java.io.IOException;

import static org.epochx.stats.StatField.*;

public class MyClassifier extends AbstractClassifier {

    protected static MyGR gr = null;

    protected static File file = null;

    public MyClassifier() {

    }

    public static void main(String[] args) {
        System.out.println("oi");

        String path = "E:\\UbuntuBashFiles\\git\\epochx-weka\\grammar\\grammar.bnf";
        file = new File(path);
        try {
            gr = new MyGR(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Life.get().addGenerationListener(new GenerationAdapter(){
            @Override
            public void onGenerationEnd() {
                Stats.get().print(GEN_NUMBER, GEN_FITNESS_MIN, GEN_FITTEST_PROGRAM);
            }
        });

        gr.run();
    }

    @Override
    public void buildClassifier(Instances instances) throws Exception {

    }
}
