package src;
import org.epochx.gr.model.GRModel;
import org.epochx.representation.CandidateProgram;
import org.epochx.tools.grammar.Grammar;

public class TheGR extends GRModel {
	
	public static final String GRAMMAR_FRAGMENT = "<start> ::= Multilayerperceptron |"
												+ " LogisticRegression |"
												+ " DecisionTree |"
												+ " RandomForest";
	
	public TheGR() {
		setGrammar(new Grammar(GRAMMAR_FRAGMENT));
	}

	@Override
	public double getFitness(CandidateProgram arg0) {
		return 0;
	}

}
