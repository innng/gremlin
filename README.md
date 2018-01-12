# Epochx-Weka

## Links
* http://www.uky.edu/~nyu222/tutorials/Weka.htm
	* Tutorial para rodar o weka pela command-line
* http://citeseer.ist.psu.edu/viewdoc/download;jsessionid=95A6782EFD289D8852FE5B6253EA47F2?doi=10.1.1.26.2091&rep=rep1&type=pdf
	* Paper: Grammatically-based Genetic Programming 
* http://dces.essex.ac.uk/staff/rpoli/gp-field-guide/A_Field_Guide_to_Genetic_Programming.pdf
	* A Field Guide to Genetic Programming 
* https://machinelearningmastery.com/use-regression-machine-learning-algorithms-weka/
	* Como usar algoritmos de regress�o pra machine learning
* https://weka.wikispaces.com/Use+Weka+in+your+Java+code
	* Colocar Weka no c�digo Java
* https://www.cs.waikato.ac.nz/ml/weka/documentation.html
	* Toda a documenta��o dispon�vel do Weka
* https://www.researchgate.net/publication/216300961_Symbolic_regression_using_abstract_expression_grammars
* https://www.lri.fr/~hansen/proceedings/2014/GECCO/companion/posters/p139.pdf
	* Grammatical Evolution and Attribute Grammar 
* https://www.cs.waikato.ac.nz/ml/weka/mooc/dataminingwithweka/
* https://www.cs.waikato.ac.nz/ml/weka/mooc/moredataminingwithweka/
* https://pdfs.semanticscholar.org/4caf/361402d29beae227f1b00da95d471662872d.pdf
	* Pdf sobre gp baseado em gram�tica
* http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.649.6050&rep=rep1&type=pdf
	* Regress�o simb�lica com gp baseado em gram�tica
* https://www.researchgate.net/publication/266658252_A_genetic_programming_problem_definition_language_code_generator_for_the_EpochX_framework

## D�vidas
* Qual a rela��o entre o projeto e uma gram�tica para regress�o simb�lica?
* Utilizar todos os param�tros dispon�veis (command-line + GUI) ou s� da GUI?
* Por que o Auto-Weka n�o � consistente com escolha do algoritmo (algoritmo muda a cada execu��o)? Seed?
* Integra��o servir� pra rodar o Weka ao final da execu��o ou pra escolha da fitness?
* Vale a pena colocar co-evolu��o?
* Gram�tica vai definir a constru��o do pipeline?
* Qual a import�ncia da sele��o de atributos para a execu��o do c�digo? Pre-processamento?

## To-do List
* Preparar uma gram�tica para o projeto
* Implementar as fun��es para usar o weka dentro do c�digo
* Montar um algoritmo para o gp baseado em gram�tica
* Selecionar melhorias para implementar no gp
* Procurar todos os param�tros poss�veis e o seu intervalo de funcionamento

## Anota��es
* **Melhorias que podem ser adicionadas:**
	* Esp�cies
	* Fitness Sharing
	* Co-evolu��o (cooperativa ou competitiva?)
	* Op��o de selecionar dataset pra treino e teste (?)
* **Funcionamento do programa:**
	* Representa��o - pipeline em forma de �rvore:
		* Pre-processamento: adi��o filtro ou n�o e sele��o de atributos
			* Sele��o de atributos n�o � realmente necess�ria porque j� tem metaclassificador e filtro que a faz
		* Processamento: classificador/meta e param�tros
		* Pos-processamento: tipo de avalia��o (?)
	* Gram�tica garante que o conjunto � v�lido:
		* Garantir no crossover, na muta��o e na inicializa��o que o pipeline roda no Weka
		* Garantir que na busca s� estar�o os algoritmos que s�o compat�veis com o dado dataset
* **Op��es pra rodar um classificador:**
	* Aplicar filtro nos dados
		* Escolher param�tros
	* Escolher classificador
		* Escolher param�tros