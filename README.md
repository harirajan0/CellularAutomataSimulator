# CellSociety Team 24
===================

CompSci 308 Cell Society Project

Team members: Hari Rajan, Bihan Zhuang, Vishnu Gottiparthy, Gabriel Chen

Started: 01/29/2017

Finished: 02/12/2017

Hours: 50 per individual

### Role of each member
##### Hari
For this project, I was in charge of the following:

1) Predator-Prey Model backend

2) State interface and State Enums for each simulation

3) Loader package (Loader.java, XMLCreator.java, XMLException.javaj, XMLParse.java), all configuration features, exception handling and alerts

5) Refactoring code into the Model.java superclass and its subclasses

6) Removing magic values from code and moving them to Resources.java 

As the project progressed, we all ended up helping each other out on practically every facet of the project so I also had a role working on other parts of the project including the Segregation model, the main package (specifically Controller.java), and implementing other features as well. Also, we all spent a lot of time refactoring and debugging each other's code.

##### Bihan
1) Designed the class hierarchy following the MVC model with group. Refactored so that the Controller, Views and Models integrate well together. Also refactored to better
separate frontend and backend. Worked on some GUI, View, Controller and Model methods. 

2) Designed and wrote classes in the cellshapeview and neighborfinder packages to allow different shapes of cells. 

3) Wrote backend for Segregation Cells.

4) Debugging with team.


##### Vishnu
1) Spreading Fire model backend

2) Population graph visualization front and back end

3) Refactored model double for-loop methods to iterator methods, and 
	cut down on duplicate code/hide methods from subclasses

5) Displaying of cells in the model view

6) Debugging with the team

7) Javadoc comments
##### Gabriel
1) Worked with group on class hierarchy following the MVC model. Worked on Controller, Views, and Model integration. Worked on step logic, and main application logic.
Worked on refactoring in various areas in the project as well. Worked mainly on GUI, View, Controller, and Model. Also implemented interactions like zooming, scrolling, and click
to change state.

2) Designed and wrote the classes for Simulation View, Simulation GUI, Control Panel, working closely with oother components as well. 

3) Wrote backend for Conway Cells.

4) Worked with everyone on debugging basically everything as things came up.

### Resources
Many official Java Documentation on different built-in classes and StackOverflow Q&A.

Compsci 308 Piazza page.

### To Run
Run ApplicationStartup in main package

### To Test
Use any XML file in the resources package. 

### Required data or resource files
1) The English properties file in the resources package.
 
2) All the XML files in the resources package for running the simulations.

### Information about using the program
Nothing special

### Known bugs, crashes, or problems
None identified

### Extra features
1) Different arrangements of neighbors

2) Triangle, square, and hexagon cell shapes

3) Error checking

4) Initial configurations set by specific locations and states, randomly based on total number of locations, and randomly based on probability distribution

5) Graph of populations of each type of cell

6) Click to change cell state

7) Zoom in/out functionality

### Impressions and Improvements
Assignment was a good challenge that pushed us to make good design decisions. 

