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

all: testInvader testLecture testCarte testRobots testCarteGUI testEvenements

testInvader:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestInvader.java

testLecture:
	javac -d bin -sourcepath src src/TestLecteurDonnees.java

testCarte:
	javac -d bin -sourcepath src src/TestCarte.java

testRobots:
	javac -d bin -sourcepath src src/TestRobots.java

testCarteGUI:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestCarteGUI.java

testEvenements:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/TestEvenements.java

# Execution:
# on peut taper directement la ligne de commande :
#   > java -classpath bin:bin/gui.jar TestInvader
# ou bien lancer l'execution en passant par ce Makefile:
#   > make exeInvader
exeInvader: testInvader
	java -classpath bin:bin/gui.jar TestInvader

exeLecture: testLecture
	java -classpath bin TestLecteurDonnees cartes/carteSujet.map

exeCarte: testCarte
	java -classpath bin TestCarte

exeRobots: testRobots
	java -classpath bin TestRobots

exeCarteGUI: testCarteGUI
	java -classpath bin:bin/gui.jar TestCarteGUI cartes/spiralOfMadness-50x50.map

exeEvenements: testEvenements
	java -classpath bin:bin/gui.jar TestEvenements cartes/carteSujet.map -enableassertions

doc:
	javadoc -private -d doc/ -sourcepath src/ -classpath bin/ -subpackages chemin simulation io robot enumerations evenement

clean:
	rm -rf bin/*.class doc/*

.PHONY: doc clean
