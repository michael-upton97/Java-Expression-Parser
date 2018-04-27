# Java-Expression-Parser
A program to read in mathematical expressions, Lex them into Tokens, Parse them into a tree and evaluate them. Project specifically for Object Oriented Design Class and is written with heavy use of recursion based off a grammar analysis. 

Description:
In order to complete this program, I had to evaluate what the grammar of a mathematical expression was, use that grammar to define tokens and nodes in a tree, then write a parser file that restated the non-terminal types as functions. These functions create the nodes that rest in the tree and build the tree in such a fashion as to preserve the correct order of operations. This implementation of the lexer/parser is fully object oriented and includes many customer Exceptions and handles for them. 

If observing this code as part of my resume please note the used of object-oriented design, the thorough understanding of java ands its data structures as well as the elegant error handling and extensive documentation. I am most proud of how quickly I produced this work (about 12 hours). If I revisited the Project I would implement a proper iterator rather than the “pseudo” one I put together, I would try clean up my Express file a little, perhaps moving some instruction blocks into methods and would add additional functionality, such as a currency token that only evaluates to 2 decimal places. 

Tools: 
This Program was written in the Eclipse Java Oxygen 2 compiler environment.
Microsoft OneDrive assisted in easy (mostly) transfer of files between my laptop and desktop. 

Other Notes:
If planning on entering a custom text file for the program to read, it needs to be saved in the immediate directory, next to default.txt.

Program will only save defined Identifiers for the period in which the program is running. Once mode Q is selected or the program is otherwise ended, the data assigned to each ID will be lost. 

References:
Thanks to Professor Tom Fuller
Other assistance from the StackOverflow forum (review of previously submitted questions)
Oracle JavaDocs (version 8)
