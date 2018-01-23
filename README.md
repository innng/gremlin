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
* Algum motivo pra separação dos parâmetros de um algoritmo ser antecedido por -- no caso de um classificador e dentro de " " no restante?
* Descobrir qual o papel de iterated e randomizable

## Decisões de projeto
* Preprocessamento e posprocessamento selecionados por metaclassificador
* Próprio projeto é um meta classificador
* Fazer uma cadeia de meta classificadores do Weka a partir do meu

## Anotações
* Os parâmetros de um classificador selecionado por outro são antecedidos por --
* Os parâmetros e o nome de um algoritmo que não é classificador vem dentro de " "

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
				* locallyPredictive (-L)
					* (true, false; false)
				* missingSeparate (-M)
					* (true, false; false)
				* numThreads  (-E)
				* poolSize (-P)
				* preComputeCorrelationMatrix (-Z)
		* Search
			* BestFirst
				* direction (-D)
					* (0, 1, 2; 1)
				* lookupCacheSize (-?)
				* searchTerminatior (-N)
					* ([2, 10]; 5) inteiro
				* startSet (-P)
			* GreedyStepwise
				* conservativeForwardSelection (-C)
					* (true, false; false)
				* debuggingOutput (-D)
				* generateRanking (-R)
					* (true, false; false)
				* numToSelect (-N)
					* ([10, 1000]; 30) inteiro
				* searchBackwards (-B)
					* (true, false; false)
				* startSet (-P)
				* threshold (-T) 
				* regras:
					* N se R false
	* Base
		* BayesNet
			* estimator (-E)
			* searchAlgorithm (-Q)
				* (K2, HillClimber, LAGDHillClimber, SimulatedAnnealing, TabuSearch, TAN; K2)
			* useADTree (-D)
				* (true, false; false)
		* NaiveBayes
			* displayModelInOldFormat (-O)
			* useKernelEstimator (-K)
				* (true, false; false)
			* useSupervisedDiscretization (-D)
				* (true, false; false)
			* regras:
				* K se D false
		* NaiveBayesMultinomial
		* Logistic
			* maxIts (-M)
			* ridge (-R)
				* ([1e-12, 10]; 1e-7)
			* useConjugateGradientDescent (-C)
		* MultilayerPerceptron
			* GUI (-G)
			* autoBuild (-?)
			* decay (-D)
				* (true, false; false)
			* hiddenLayers (-H)
				* (a, i, o, t; a)
			* learningRate (-L)
				* ([0.1, 1]; 0.3)
			* momentum (-M)
				* ([0.1, 1]; 0.2)
			* nominalToBinaryFilter (-B)
				* (true, false; false)
			* normalizeAttributes (-I)
			* normalizeNumericClass (-C)
				* (true, false; false)
			* reset (-R)
				* (true, false; false)
			* seed (-S)
				* (1; 1)
			* trainingTime (-N)
			* validationSetSize (-V)
			* validationThreshold (-E)
		* SGD
			* dontNormalize (-N)
				* (true, false; false)
			* dontReplaceMissing (-M)
				* (true, false; false)
			* epochs (-E)
			* epsilon (-C)
			* lambda (-R)
				* ([1e-12, 10]; 1e-4)
			* learningRate (-L)
				* ([0.00001, 0.1]; 0.01)
			* lossFunction (-F)
				* (0, 1, 2; 0)
			* seed (-S)
		* SimpleLogistic
			* errorOnProbabilities (-P)
			* heuristicStop (-H)
			* maxBoostingIterations (-M)
			* numBoostingIterations (-I)
			* useAIC (-A)
				* (true, false; false)
			* useCrossValidation (-S)
				* (true, false; false)
			* weightTrimBeta (-W)
				* W_HIDDEN (0, 1; 0)
					* 1_W (0; 0)
					* 2_W ([0, 1]; 0)
			* regras:
				* `1_W` se `W_HIDDEN` 0
				* `2_W` se `W_HIDDEN` 1
		* SMO
			* buildCalibrationModels (-M)
			* c (-C)
				* ([0.5, 1.5]; 1)
			* calibrator (-calibrator)
			* checksTurnedOff (-no-checks)
			* epsilon (-P)
			* filterType (-N)
				* (0, 1, 2; 0)
			* kernel (-K)
				* (NormalizedPolyKernel, PolyKernel, Puk, RBFKernel; NormalizedPolyKernel)
				* NormalizedPolyKernel
					* exponent (-E)
						* ([0.2, 5]; 1)
					* useLowerOrder (-L)
						* (true, false; false)
				* PolyKernel
					* exponent (-E)
						* ([0.2, 5]; 1)
					* useLowerOrder (-L)
						* (true, false; false)
				* Puk
					* omega (-O)
						* ([0.1, 1]; 1)
					* sigma (-S)
						* ([0.1, 10]; 1)
				* RBFKernel
					* gamma (-G)
						* ([0.0001, 1]; 0.01)
			* numFolds (-V)
			* randomSeed (-W)
			* toleranceParameter (-L)
			* regras:
				* parâmetros próprios de cada algoritmo dependendo do valor de K
		* VotedPerceptron
			* exponent (-E)
				* ([0.2, 5]; 1)
			* maxK (-M)
				* ([5000, 50000]; 10000) inteiro
			* numIterations (-I)
				* ([1, 10]; 1) inteiro
			* seed (-S)
		* IBk
			* KNN (-K)
				* ([1, 64]; 1) inteiro
			* crossValidate (-X)
				* (true, false; false)
			* distanceWeighting (-I/-F)
				* (true, false; false)
			* meanSquare (-E)
				* (true, false; false)
			* nearestNeightbourSearchAlgorithm (-A)
			* windowSize (-W)
			* regras:
				* F se I false
		* KStar
			* entropicAutoBlend (-E)
				* (true, false; false)
			* globalBlend (-B)
				* ([1, 100]; 20) inteiro
			* missingMode (-M)
				* (a, d, m, n; a)
		* DecisionTable
			* crossVal (-X)
				* (1, 2, 3, 4; 1)
			* evaluationMeasure (-E)
				* (acc, rmse, mae, aux; acc)
			* search (-S)	
				* (BestFirst, GreedyStepwise; BestFirst)
			* useIBk (-I)
				* (true, false; false)
		* JRip
			* checkErrorRate (-E)
				* (true, false; false)
			* folds (-F)
			* minNo (-N)
				* ([1, 5]; 2)
			* optimizations (-O)
				* ([1, 5]; 2) inteiro
			* seed (-S)
			* usePruning (-P)
				* (true, false; false)
		* OneR
			* minBucketSize (-B)
				* ([1, 32]; 6) inteiro
		* PART
			* binarySplits (-B)
				* (true, false; false)
			* confidenceFactor (-C)
			* minNumObj (-M)
				* ([1, 64]; 2) inteiro
			* numFolds (-N)
				* ([2, 5]; 3) inteiro
			* reducedErrorPruning (-R)
				* (true, false; false)
			* seed (-Q)
			* unpruned (-U)
			* useMDLcorrection (-J)
			* regras:
				* N se R true
		* ZeroR
		* DecisionStump
		* J48
			* binarySplits (-B)
				* (true, false; false)
			* collapseTree (-O)
				* (true, false; false)
			* confidenceFactor (-C)
				* C_HIDDEN ([0, 1]; 0.25)
					* C ([0, 1]; 0.25)
			* minNumObj (-M)
				* ([1, 64]; 2) inteiro
			* numFolds (-N)
			* reducedErrorPruning (-R)
			* saveInstanceData (-L)
			* seed (-?)
			* subtreeRaising (-S)
				* (true, false; false)
			* unpruned (-U)
				* (true, false; false)
			* useLaplace (-A)
				* (true, false; false)
			* useMDLcorrection (-J)
				* (true, false; false)
			* regras:
				* U se S false
				* U se C_HIDDEN 0
				* C se C_HIDDEN 1
		* LMT
			* convertNominal (-B)
				* (true, false; false)
			* errorOnProbabilities (-P)
				* (true, false; false)
			* fastRegression (-C)
				* (true, false; false)
			* minNumInstances (-M)
				* ([1, 64]; 15) inteiro
			* numBoostingIterations (-I)
			* splitOnResiduals (-R)
				* (true, false; false)
			* useAIC (-A)
				* (true, false; false)
			* weightTrimBeta (-W)
				* W_HIDDEN (0, 1; 0)
					* `1_W` (0; 0)
					* `2_W` ([0, 1]; 0)
			* regras:
				* 1_W se `W_HIDDEN` 0
				* 2_W se `W_HIDDEN` 1
		* RandomForest
			* bagSizePercent (-P)
			* breakTiesRandomly (-B)
			* calcOutOfBag (-O)
			* maxDepth (-depth)
				* depth_HIDDEN (0, 1; 0)
					* `1_INT_depth` (0; 0)
					* `2_INT_depth` ([1, 20]; 2) inteiro
			* numFeatures (-K)
				* features_HIDDEN (0, 1; 0)
					* `1_INT_K`(0; 0)
					* `2_INT_K` ([1, 32]; 2) inteiro
			* numIterations (-I)
				* ([2, 256]; 10)
			* seed (-S)
			* regras:
				* `1_INT_K` se features_HIDDEN 0
				* `2_INT_K` se features_HIDDEN 1
				* `1_INT_depth` se depth_HIDDEN 0
				* `2_INT_depth` se depth_HIDDEN 1
		* RandomTree
			* KValue (-K)
				* features_HIDDEN (0, 1; 0)
					* `1_INT_K` (0; 0)
					* `2_INT_K` ([2, 32]; 2) inteiro
			* allowUnclassifiedInstances (-U)
				* (true, false; false)
			* breakTiesRandomly (-B)
			* maxDepth (-depth)
				* depth_HIDDEN (0, 1; 0)
					* `1_INT_depth` (0; 0)
					* `2_INT_depth` ([2, 20]; 2) inteiro
			* minNum (-M)
				* ([1, 64]; 1) inteiro
			* minVarianceDrop (-V)
			* numFolds (-N)
				* back_HIDDEN (0, 1; 0)
					* `1_INT_N` (0; 0)
					* `2_INT_N` ([2, 5]; 3) inteiro
			* seed (-S)
			* regras:
				* `1_INT_K` se features_HIDDEN 0
				* `2_INT_K` se features_HIDDEN 1
				* `1_INT_depth` se depth_HIDDEN 0
				* `2_INT_depth` se depth_HIDDEN 1
				* `1_INT_N` se back_HIDDEN 0
				* `2_INT_N` se back_HIDDEN 1 
		* REPTree
			* initialCount (-I)
			* maxDepth (-L)
				* depth_HIDDEN (0, 1; 0)
					* `1_INT_L` (-1; -1)
					* `2_INT_L` ([2, 20]; 2)
			* minNum (-M)
				* ([1, 64]; 2) inteiro
			* minVarianceDrop (-V)
				* ([1e-5, 1e-1]; 1e-3)
			* noPruning (-P)
				* (true, false; false)
			* numFolds (-N)
			* seed (-S)
			* spreadInitialCount (-R)
			* regras:
				* `1_INT_L` se depth_HIDDEN 0
				* `2_INT_L`	se depth_HIDDEN 1
	* Ensemble
		* Stacking
			* classifiers (-B)
			* metaClassifier (-M)
			* numFolds (-X)
				* (10; 10)
			* seed (-S)
				* (1; 1)
		* Vote
			* classifiers (-B)
			* combinationRule (-R)
				* (AVG, PROD, MAJ, MIN, MAX; AVG)
			* preBuiltClassifiers (-P)
			* seed (-S)
				* (1; 1)
	* Meta
		* LWL
			* KNN (-K)
				* K_HIDDEN (0, 1; 0)
					* 1_K (-1, 10, 30, 60, 90, 120; -1)
			* classifier (-W)
			* nearestNeighbourSearchAlgorithm (-A)
				* (LinearNNSearch; LinearNNSearch)
			* weightingKernel (-U)
				* U_HIDDEN (0, 1; 0)
					* 1_U (0, 1, 2, 3, 4; 0)
			* regras:
				* `1_K` se K_HIDDEN 1
				* `1_U` se K_HIDDEN 0
				* `1_U` se U_HIDDEN 1
				* `1_K` se U_HIDDEN 0
		* AdaBoostM1
			* classifier (-W)
			* numIterations (-I)
				* ([2, 128]; 10) inteiro
			* seed (-S)
				* (1; 1)
			* useResampling (-Q)
				* (true, false; false)
			* weightThreshold (-P)
				* p_HIDDEN (0, 1; 0)
					* 1_P (100; 100)
					* `2_INT_P` ([50, 100]; 100)
				* regras:
					* `1_P` se p_HIDDEN 0
					* `2_INT_P` se p_HIDDEN 1
		* AttributeSelectedClassifier
			* classifier (-W)
			* evaluator (-E)
				* (CfsSubsetEval; CfsSubsetEval)
			* search (-S)
				* (BestFirst, GreedyStepwise; BestFirst)
		* Bagging
			* bagSizePercent (-P)
				* ([10, 100]; 100) inteiro
			* calcOutOfBag (-O)
				* (true, false; false)
			* classifier (-W)
			* numIterations (-I)
				* ([2, 128]; 10) inteiro
			* seed (-1)
				* (1; 1)
			* regras:
				* O se INT_P 100
		* RandomComittee
			* classifier (-W)
			* numIterations (-I)
				* ([2, 64]; 10) inteiro
			* seed (-S)
				* (1; 1)
		* RandomSubSpace
			* classifier (-W)
			* numIterations (-I)
				* ([2, 64]; 10) inteiro
			* seed (-S)
				* (1; 1)
			* subSpaceSize (-P)
				* ([0.1, 1]; 0.5)
