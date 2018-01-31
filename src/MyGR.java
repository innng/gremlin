import org.epochx.gr.model.GRModel;
import org.epochx.representation.CandidateProgram;
import org.epochx.tools.grammar.Grammar;

import java.io.File;
import java.io.IOException;

public class MyGR extends GRModel{

    public MyGR(File file) throws IOException {
        setGrammar(new Grammar(file));

    }

    @Override
    public double getFitness(CandidateProgram candidateProgram) {
        return 0;
    }
}
