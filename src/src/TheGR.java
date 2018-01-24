package src;
import org.epochx.gr.model.GRModel;
import org.epochx.representation.CandidateProgram;
import org.epochx.tools.grammar.Grammar;

public class TheGR extends GRModel {
	
	public static final String GRAMMAR_FRAGMENT_1 = "<start> ::= Multilayerperceptron |"
												+ " LogisticRegression |"
												+ " DecisionTree |"
												+ " RandomForest";

	public static final String GRAMMAR_FRAGMENT_2 = "<start> ::= <MLP> | <Trees>\n"
												  + "<MLP> ::= MultilayerPerceptron\n"
												  + "<Trees> ::= DecisionTree | RandomForest";
	
	public TheGR() {
		setGrammar(new Grammar(GRAMMAR_FRAGMENT_2));
	}

	@Override
	public double getFitness(CandidateProgram arg0) {
		return 0;
	}

}
