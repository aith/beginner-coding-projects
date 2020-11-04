#  simple Makefile for the Roots program
Queens: Queens.class
	echo Main-class: Queens > Manifest
	jar cvfm Queens Manifest Queens.class
	rm Manifest
	chmod u+x Queens
Queens.class: Queens.java
	javac -Xlint Queens.java
clean:
	rm –f Queens.class Queens
