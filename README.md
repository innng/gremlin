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

## Anota��es

* **ITENS PARA COLOCAR NO GGP:**
	* gram�tica por regress�o simb�lica
	* esp�cies
	* fitness sharing
	* co-evolu��o (cooperativa ou competitiva)

* **INTEGRA��O WEKA-EPOCHX:**
	* descobrir extens�o x integra��o
	* weka biblioteca?
	
* **Racioc�nio do funcionamento:**
	* weka roda um classificador/metaclassi e seus param�tros
	* representa��o deve ser um conjunto composto por um algoritmo e seus param�tros
	* definir quais dos atributos s�o relevantes e precisam ser alterados
	* gram�tica deve garantir que o conjunto � v�lido
		* definir quando um algoritmo pode ser usado
		* definir se os param�tros est�o nos intervalos v�lidos para o algoritmo


## Weka

* usar a LibSVM tamb�m?

## Auto-Weka

* 

## Epochx

* 

## D�vidas

* a integra��o entre epochx e weka vai servir para rodar o weka ao final da escolha do pipeline ou para o c�lculo da fitness?
* se a divis�o por esp�cies for colocada, vale a pena fazer uma co-evolu��o tamb�m?
* representa��o: meta-classifier e hyperparameters?
* o que � o (Nom)Class do weka que eu tenho que selecionar pra rodar um classificador?
* a gram�tica na verdade vai precisar definir como um pipeline � feito e garantir que no crossover o pipeline ainda seja v�lido?

## GP baseado em gram�tica pra regress�o simb�lica

* qual a diferen�a do Regression pra regress�o simb�lica?
	* 
* como ser� a representa��o dos indiv�duos?
	* provavelmente eles ser�o um pipeline que pode ser rodado no weka
	* um classificador ou metaclassificador seguido dos seus param�tros
* qual o conjunto de metaclassificadores, classificadores e seus hiperparam�tros?
	* 39 metaclassificadores e classificadores, descobrir quantos par�metros poss�veis
* qual ser� a fun��o fitness?
	* rodar o pipeline no weka e usar algo relacionado � precis�o?
