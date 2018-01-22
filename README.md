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
				* missingSeparate (-M)
				* numThreads  (-E)
				* poolSize (-P)
				* preComputeCorrelationMatrix (-Z)
		* Search
			* BestFirst
				* direction (-D)
				* lookupCacheSize (-?)
				* searchTerminatior (-N)
				* startSet (-P)
			* GreedyStepwise
				* conservativeForwardSelection (-C)
				* debuggingOutput (-D)
				* generateRanking (-R)
				* numToSelect (-N)
				* searchBackwards (-B)
				* startSet (-P)
				* threshold (-T) 
	* Base
		* BayesNet
			* estimator (-E)
			* searchAlgorithm (-Q)
			* useADTree (-D)
		* NaiveBayes
			* displayModelInOldFormat (-O)
			* useKernelEstimator (-K)
			* useSupervisedDiscretization (-D)
		* NaiveBayesMultinomial
		* Logistic
			* maxIts (-M)
			* ridge (-R)
			* useConjugateGradientDescent (-C)
		* MultilayerPerceptron
			* GUI (-G)
			* autoBuild (-?)
			* decay (-D)
			* hiddenLayers (-H)
			* learningRate (-L)
			* momentum (-M)
			* nominalToBinaryFilter (-B)
			* normalizeAttributes (-I)
			* normalizeNumericClass (-C)
			* reset (-R)
			* seed (-S)
			* trainingTime (-N)
			* validationSetSize (-V)
			* validationThreshold (-E)
		* SGD
			* dontNormalize (-N)
			* dontReplaceMissing (-M)
			* epochs (-E)
			* epsilon (-C)
			* lambda (-R)
			* learningRate (-L)
			* lossFunction (-F)
			* seed (-S)
		* SimpleLogistic
			* errorOnProbabilities (-P)
			* heuristicStop (-H)
			* maxBoostingIterations (-M)
			* numBoostingIterations (-I)
			* useAIC (-A)
			* useCrossValidation (-S)
			* weightTrimBeta (-W)
		* SMO
			* buildCalibrationModels (-M)
			* c (-C)
			* calibrator (-calibrator)
			* checksTurnedOff (-no-checks)
			* epsilon (-P)
			* filterType (-N)
			* kernel (-K)
			* numFolds (-V)
			* randomSeed (-W)
			* toleranceParameter (-L)
		* VotedPerceptron
			* exponent (-E)
			* maxK (-M)
			* numIterations (-I)
			* seed (-S)
		* IBk
			* KNN (-K)
			* crossValidate (-X)
			* distanceWeighting (-I/-F)
			* meanSquare (-E)
			* nearestNeightbourSearchAlgorithm (-A)
			* windowSize (-W)
		* KStar
			* entropicAutoBlend (-E)
			* globalBlend (-B)
			* missingMode (-M)
		* DecisionTable
			* crossVal (-X)
			* evaluationMeasure (-E)
			* search (-S)
			* useIBk (-I)
		* JRip
			* checkErrorRate (-E)
			* folds (-F)
			* minNo (-N)
			* optimizations (-O)
			* seed (-S)
			* usePruning (-P)
		* OneR
			* minBucketSize (-B)
		* PART
			* binarySplits (-B)
			* confidenceFactor (-C)
			* minNumObj (-M)
			* numFolds (-N)
			* reducedErrorPruning (-R)
			* seed (-Q)
			* unpruned (-U)
			* useMDLcorrection (-J)
		* ZeroR
		* DecisionStump
		* J48
			* binarySplits (-B)
			* collapseTree (-O)
			* confidenceFactor (-C)
			* minNumObj (-M)
			* numFolds (-N)
			* reducedErrorPruning (-R)
			* saveInstanceData (-L)
			* seed (-?)
			* subtreeRaising (-S)
			* unpruned (-U)
			* useLaplace (-A)
			* useMDLcorrection (-J)
		* LMT
			* convertNominal (-B)
			* errorOnProbabilities (-P)
			* fastRegression (-C)
			* minNumInstances (-M)
			* numBoostingIterations (-I)
			* splitOnResiduals (-R)
			* useAIC (-A)
			* weightTrimBeta (-W)
		* RandomForest
			* bagSizePercent (-P)
			* breakTiesRandomly (-B)
			* calcOutOfBag (-O)
			* maxDepth (-depth)
			* numFeatures (-K)
			* numIterations (-I)
			* seed (-S)
		* RandomTree
			* KValue (-K)
			* allowUnclassifiedInstances (-U)
			* breakTiesRandomly (-B)
			* maxDepth (-depth)
			* minNum (-M)
			* minVarianceDrop (-V)
			* numFolds (-N)
			* seed (-S)
		* REPTree
			* initialCount (-I)
			* maxDepth (-L)
			* minNum (-M)
			* minVarianceDrop (-V)
			* noPruning (-P)
			* numFolds (-N)
			* seed (-S)
			* spreadInitialCount (-R)
	* Ensemble
		* Stacking
			* classifiers (-B)
			* metaClassifier (-M)
			* numFolds (-X)
			* seed (-S)
		* Vote
			* classifiers (-B)
			* cõmbinationRule (-R)
			* preBuiltClassifiers (-P)
			* seed (-S)
	* Meta
		* LWL
			* KNN (-K)
			* classifier (-W)
			* nearestNeighbourSearchAlgorithm (-A)
			* weightingKernel (-U)
		* AdaBoostM1
			* classifier (-W)
			* numIterations (-I)
			* seed (-S)
			* useResampling (-Q)
			* weightThreshold (-P)
		* AttributeSelectedClassifier
			* classifier (-W)
			* evaluator (-E)
			* search (-S)
		* Bagging
			* bagSizePercent (-P)
			* calcOutOfBag (-O)
			* classifier (-W)
			* numIterations (-I)
			* seed (-1)
		* RandomComittee
			* classifier (-W)
			* numIterations (-I)
			* seed (-S)
		* RandomSubSpace
			* classifier (-W)
			* numIterations (-I)
			* seed (-S)
			* subSpaceSize (-P)
