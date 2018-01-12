# Epochx-Weka

## Links
* http://www.uky.edu/~nyu222/tutorials/Weka.htm
	* Tutorial para rodar o weka pela command-line
* http://citeseer.ist.psu.edu/viewdoc/download;jsessionid=95A6782EFD289D8852FE5B6253EA47F2?doi=10.1.1.26.2091&rep=rep1&type=pdf
	* Paper: Grammatically-based Genetic Programming 
* http://dces.essex.ac.uk/staff/rpoli/gp-field-guide/A_Field_Guide_to_Genetic_Programming.pdf
	* A Field Guide to Genetic Programming 
* https://machinelearningmastery.com/use-regression-machine-learning-algorithms-weka/
	* Como usar algoritmos de regressão pra machine learning
* https://weka.wikispaces.com/Use+Weka+in+your+Java+code
	* Colocar Weka no código Java
* https://www.cs.waikato.ac.nz/ml/weka/documentation.html
	* Toda a documentação disponível do Weka
* https://www.researchgate.net/publication/216300961_Symbolic_regression_using_abstract_expression_grammars
* https://www.lri.fr/~hansen/proceedings/2014/GECCO/companion/posters/p139.pdf
	* Grammatical Evolution and Attribute Grammar 
* https://www.cs.waikato.ac.nz/ml/weka/mooc/dataminingwithweka/
* https://www.cs.waikato.ac.nz/ml/weka/mooc/moredataminingwithweka/
* https://pdfs.semanticscholar.org/4caf/361402d29beae227f1b00da95d471662872d.pdf
	* Pdf sobre gp baseado em gramática
* http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.649.6050&rep=rep1&type=pdf
	* Regressão simbólica com gp baseado em gramática
* https://www.researchgate.net/publication/266658252_A_genetic_programming_problem_definition_language_code_generator_for_the_EpochX_framework

## Dúvidas
* Qual a relação entre o projeto e uma gramática para regressão simbólica?
* Utilizar todos os paramêtros disponíveis (command-line + GUI) ou só da GUI?
* Por que o Auto-Weka não é consistente com escolha do algoritmo (algoritmo muda a cada execução)? Seed?
* Integração servirá pra rodar o Weka ao final da execução ou pra escolha da fitness?
* Vale a pena colocar co-evolução?
* Gramática vai definir a construção do pipeline?
* Qual a importância da seleção de atributos para a execução do código? Pre-processamento?

## To-do List
* Preparar uma gramática para o projeto
* Implementar as funções para usar o weka dentro do código
* Montar um algoritmo para o gp baseado em gramática
* Selecionar melhorias para implementar no gp
* Procurar todos os paramêtros possíveis e o seu intervalo de funcionamento

## Anotações
* **Melhorias que podem ser adicionadas:**
	* Espécies
	* Fitness Sharing
	* Co-evolução (cooperativa ou competitiva?)
	* Opção de selecionar dataset pra treino e teste (?)
* **Funcionamento do programa:**
	* Representação - pipeline em forma de árvore:
		* Pre-processamento: adição filtro ou não e seleção de atributos
			* Seleção de atributos não é realmente necessária porque já tem metaclassificador e filtro que a faz
		* Processamento: classificador/meta e paramêtros
		* Pos-processamento: tipo de avaliação (?)
	* Gramática garante que o conjunto é válido:
		* Garantir no crossover, na mutação e na inicialização que o pipeline roda no Weka
		* Garantir que na busca só estarão os algoritmos que são compatíveis com o dado dataset
* **Opções pra rodar um classificador:**
	* Aplicar filtro nos dados
		* Escolher paramêtros
	* Escolher classificador
		* Escolher paramêtros