# Epochx-Weka

## Links
* http://www.uky.edu/~nyu222/tutorials/Weka.htm
	* Tutorial para rodar o weka pela command-line
* http://citeseer.ist.psu.edu/viewdoc/download;jsessionid=95A6782EFD289D8852FE5B6253EA47F2?doi=10.1.1.26.2091&rep=rep1&type=pdf
	* Paper: Grammatically-based Genetic Programming
* http://dces.essex.ac.uk/staff/rpoli/gp-field-guide/A_Field_Guide_to_Genetic_Programming.pdf
	* A Field Guide to Genetic Programming
* https://weka.wikispaces.com/Use+Weka+in+your+Java+code
	* Colocar Weka no código Java
* https://www.cs.waikato.ac.nz/ml/weka/documentation.html
	* Toda a documentação disponível do Weka
* https://www.lri.fr/~hansen/proceedings/2014/GECCO/companion/posters/p139.pdf
	* Grammatical Evolution and Attribute Grammar
* https://www.cs.waikato.ac.nz/ml/weka/mooc/dataminingwithweka/
* https://www.cs.waikato.ac.nz/ml/weka/mooc/moredataminingwithweka/
* https://pdfs.semanticscholar.org/4caf/361402d29beae227f1b00da95d471662872d.pdf
	* Pdf sobre gp baseado em gramática
* https://www.researchgate.net/publication/266658252_A_genetic_programming_problem_definition_language_code_generator_for_the_EpochX_framework
* https://weka.wikispaces.com/file/view/Build_classifier_353.pdf/82916711/Build_classifier_353.pdf

## Dúvidas
* O que significa i, il e l que seguem os números?
* O que são os parâmetros denomidos HIDDEN e porque eles tem várias opções mas só usam uma sempre?
* Os condicionais dos parâmetros do Auto-Weka não são os condicionais do Weka e vice-versa. Por quê? Onde são os condicionais do Auto-Weka?

## To-do List
1. Terminar de verificar todos os algoritmos e parâmetros usados, bem como os intervalos
2. Rodar e estudar um GR
3. Terminar as duas playlists do Weka
4. Ler Report do Alex
5. Descobrir qual o papel de iterated e randomizable
6. Testar a gramática simples
7. Usar Weka e Auto-Weka por command-line
8. Aprender mais sobre gramáticas BNF
9. Começar a gramática BNF
10. Aprender sobre as funcionalidades do EpochX
11. Verificar indivíduos gerados na inicialização pela gramática
12. Terminar de implementar o Meta Classificador do Weka
13. Selecionar melhorias para implementar no GP

## Decisões de projeto
* Preprocessamento e posprocessamento selecionados por metaclassificador
* Meta classificador
* Fazer uma cadeia de meta classificadores do Weka a partir do meu

## Anotações
* Os parâmetros de cada algoritmo escolhido são separados por --

* **Melhorias que podem ser adicionadas:**
	* Espécies
	* Fitness Sharing
	* Co-evolução (cooperativa ou competitiva?)
	* Opção de selecionar dataset pra treino e teste

* **Funcionamento do programa:**
	* Representação - pipeline em forma de árvore:
		* Pre-processamento: seleção de atributos e adição de filtro
			* Seleção de atributos não é realmente necessária porque já tem metaclassificador e filtro que a faz
		* Processamento: classificador/meta e parâmetros
		* Pos-processamento: ensemble
	* Gramática garante que o conjunto é válido:
		* Garantir no crossover, na mutação e na inicialização que o pipeline roda no Weka
		* Garantir que na busca só estarão os algoritmos que são compatíveis com o dado dataset

* **Construção do classificador:**
	* Classificador base: metaclassificador, multiple base classifiers, randomizable (?)
		* MultipleClassifierCombiner 

* **Algoritmos e parâmetros**
* Parâmetros
	* Geral
		* batchSize (-batch-size)
		* debug (-output-debug-info)
		* doNotCheckCapabilities (-do-not-check-capabilities)
		* numDecimalPlaces (-num-decimal-places)
		* outputOutOfBagComplexityStatistics  (-output-out-of-bag-complexity-statistics)
		* numExecutionSlots (-num-slots)
		* printClassifiers (-print)
		* representCopiesUsingWeights (-represent-copies-using-weights)
		* storeOutOfBagPredictions (-store-out-of-bag-predictions)
		* doNotPrintModels (-do-not-print)
		* computeAttributeImportance (-attribute-importance)
		* doNotMakeSplitPointActualValue (-doNotMakeSplitPointActualValue)

* Algoritmos
	* Attribute Selection
		* Eval
			* CfsSubsetEval
		* Search
			* BestFirst
			* GreedyStepwise
	* Base
		* BayesNet
		* NaiveBayes
		* NaiveBayesMultinomial
		* Logistic
		* MultilayerPerceptron
		* SGD
		* SimpleLogistic
		* SMO
		* VotedPerceptron
		* IBk
		* KStar
		* DecisionTable
		* JRip
		* OneR
		* PART
		* ZeroR
		* DecisionStump
		* J48
		* LMT
		* RandomForest
		* RandomTree
		* REPTree
	* Ensemble
		* Stacking
		* Vote
	* Meta
		* LWL
		* AdaBoostM1
		* AttributeSelectedClassifier
		* Bagging
		* RandomComittee
		* RandomSubSpace
