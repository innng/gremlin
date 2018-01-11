# Recipe weka

## Links

* https://machinelearningmastery.com/how-to-run-your-first-classifier-in-weka/
* https://machinelearningmastery.com/use-regression-machine-learning-algorithms-weka/
* https://weka.wikispaces.com/Use+Weka+in+your+Java+code
* https://www.cs.waikato.ac.nz/ml/weka/documentation.html
* http://www.uky.edu/~nyu222/tutorials/Weka.htm
* https://www.researchgate.net/publication/216300961_Symbolic_regression_using_abstract_expression_grammars
* http://radio.feld.cvut.cz/conf/poster2015/proceedings/Section_ICS/ICS_084_Zegklitz.pdf
* http://adsabs.harvard.edu/abs/2011gptp.book..109K
* https://www.lri.fr/~hansen/proceedings/2014/GECCO/companion/posters/p139.pdf
* https://www.cs.kent.ac.uk/pubs/2012/3212/content.pdf
* https://weka.waikato.ac.nz/explorer
* https://www.cs.waikato.ac.nz/ml/weka/mooc/dataminingwithweka/
* https://www.cs.waikato.ac.nz/ml/weka/mooc/moredataminingwithweka/
* https://dillinger.io/
* https://pdfs.semanticscholar.org/4caf/361402d29beae227f1b00da95d471662872d.pdf

## Anotações

* **ITENS PARA COLOCAR NO GGP:**
	* gramática por regressão simbólica
	* espécies
	* fitness sharing
	* co-evolução (cooperativa ou competitiva)

* **INTEGRAÇÃO WEKA-EPOCHX:**
	* descobrir extensão x integração
	* weka biblioteca?
	
* **Raciocínio do funcionamento:**
	* weka roda um classificador/metaclassi e seus paramêtros
	* representação deve ser um conjunto composto por um algoritmo e seus paramêtros
	* definir quais dos atributos são relevantes e precisam ser alterados
	* gramática deve garantir que o conjunto é válido
		* definir quando um algoritmo pode ser usado
		* definir se os paramêtros estão nos intervalos válidos para o algoritmo


## Weka

* usar a LibSVM também?

## Auto-Weka

* 

## Epochx

* 

## Dúvidas

* a integração entre epochx e weka vai servir para rodar o weka ao final da escolha do pipeline ou para o cálculo da fitness?
* se a divisão por espécies for colocada, vale a pena fazer uma co-evolução também?
* representação: meta-classifier e hyperparameters?
* o que é o (Nom)Class do weka que eu tenho que selecionar pra rodar um classificador?
* a gramática na verdade vai precisar definir como um pipeline é feito e garantir que no crossover o pipeline ainda seja válido?

## GP baseado em gramática pra regressão simbólica

* qual a diferença do Regression pra regressão simbólica?
	* 
* como será a representação dos indivíduos?
	* provavelmente eles serão um pipeline que pode ser rodado no weka
	* um classificador ou metaclassificador seguido dos seus paramêtros
* qual o conjunto de metaclassificadores, classificadores e seus hiperparamêtros?
	* 39 metaclassificadores e classificadores, descobrir quantos parâmetros possíveis
* qual será a função fitness?
	* rodar o pipeline no weka e usar algo relacionado à precisão?
