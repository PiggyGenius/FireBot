# Ensimag 2A POO - TP 2016/17
# ============================
#
# Ce Makefile permet de compiler le test de l'ihm en ligne de commande.
# Alternative (recommandee?): utiliser un IDE (eclipse, netbeans, ...)
# Le but est ici d'illustrer les notions de "classpath", a vous de l'adapter
# a votre projet.
#
# Organisation:
#  1) Les sources (*.java) se trouvent dans le repertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) se trouvent dans le repertoire bin
#     La hierarchie des sources (par package) est conservee.
#     L'archive bin/gui.jar contient les classes de l'interface graphique
#
# Compilation:
#  Options de javac:
#   -d : repertoire dans lequel sont places les .class compiles
#   -classpath : repertoire dans lequel sont cherches les .class deja compiles
#   -sourcepath : repertoire dans lequel sont cherches les .java (dependances)

all: testInvader testCarteGUI

testInvader:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestInvader.java

testCarteGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestCarteGUI.java

# Ajouter une regle
# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exeInvader: testInvader
	java -classpath bin:bin/gui.jar TestInvader

exeCarteGUI: testCarteGUI
ifeq ("$(MAP)", "")
	java -classpath bin:bin/gui.jar TestCarteGUI cartes/spiralOfMadness-50x50.map
else
	java -classpath bin:bin/gui.jar TestCarteGUI $(MAP)
endif
	


doc:
	javadoc -docencoding utf8 -encoding utf8 -charset utf8 -private -d doc/ -sourcepath src/ -classpath gui.jar -subpackages chemin simulation io robot enumerations

LATEX_TRASH=rapport.toc rapport.log rapport.aux

rapport:
	pdflatex -output-directory rapport/ rapport/rapport.tex && pdflatex -output-directory rapport/ rapport/rapport.tex

clean:
	rm -rf bin/*.class doc/* $(addprefix rapport/, $(LATEX_TRASH))

.PHONY: doc clean rapport
