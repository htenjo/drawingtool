# DrawingTool 1.0
This project provides a tool to draw given a list of commands

1. 	How it was thinked?
	- 	This program was developed in Java 1.7
	-	Use maven 3 to build and generate reports
	-	At the beginning three options were analyzed:
		-	Brute force: 
			There are a lot of examples of this exercise on internet in a single method, but this kind of solution
			is not the best option because any change require to modify lot of existing code.
		-	Figures (Lines, Rectangles, etc) as principal entities
			A second way to handle this requirement is organizing the geometric figures as the main entities in the 
			program, but this aproach has a logical problem, not all commands handle with figures (e.g. 1. Create a Canvas 
			is not a figure,  2. Fill is not a regular operation) 
		-	Commands as principal entities:
			I think the best approach is using a like Pipes and Filtes architectural pattern where the message sent in 
			the pipes are the canvas and the filters are the given commands. This solution is highlt extensible and 
			can be configured in different ways
	-	Constraints analyzed:
		-	A java matrix was used to represent the canvas, so only positive integer numbers are valid as numeric parameters
		-	The width and hight of the canvas should be higher that 0
		-	CommandLine constraints:
			-	It's required minimum 4 parameters (x1 y1 x2 y2)
			-	x1, x2, y1, y2 € Integer
			-	Only horizontal and vertical lines are allowed at this moment, so x1==x2 || y1==y2
			-	x1, x2, y1, y2 > 0
			-	x1, x2, y1, y2 < Canvas.width - 1
			-	(x1 != x2) || (y1 != y2)
			-	The canvas should exist
		-	CommandRectangle constraints:
			-	It's required minimum 4 parameters (x1 y1 x2 y2)
			-	x1, x2, y1, y2 € Integer
			-	x1, x2, y1, y2 > 0
			-	x1, x2, y1, y2 < Canvas.width - 1
			-	The canvas should exist
		-	CommandFill constraint:
			-	It's required minimum 4 parameters (x1 y1 x2 y2)
			-	x, y € Integer
			-	x, y > 0
			-	color != null && color != ""
			-	x, y should be inside canvas
			-	The canvas should exist
		-	To understand better the output file, empty pixels were painted as dots and each command has its own character

2. 	What does folder names mean in the package?
  -	docs: useful documents to support the quality of the given solution 
  -	install: contains the executable file (jar file) and examples of required files
  -	projecSite: generated information by maven about the program

3. 	How it works?  
  In a command line execute this command:  
	$java -jar DrawingTool-1.0.jar <inputfile> <outputfile>  
	Errors should be handled with a description and error code in console and output files  

4. 	The souce code could be downloaded from git:  
  In a command line execute this command:  
	$git clone https://github.com/zerovirus23/DrawingTool  
