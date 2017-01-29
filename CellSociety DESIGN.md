
**Compsci 308: CellSociety DESIGN**
========================
INTRODUCTION
--------------
The primary goal of this project is to design a program that can run several pre-defined cellular automata models. The user will be able to specify the initial states and parameters of the models. The project will be designed such that it will be simple to add new types of models (rules, agents, number of neighbors, and states). The algorithms based on the rules specified for each model will be closed, but the parameters and initial states will be open for the user to set.<br/>
OVERVIEW
----------
A Main class will launch the application. A Loader class will read the XML data file and set the initial states and parameters of the simulation. Data files read by the Loader will contain the total number of rows and columns, each possible initial state, and the fraction of the total number of cells that correspond to each state. The Loader will instantiate all the Cells (loadXML()) and instantiate the Cells in a 2D array, to make linking easier. It will pass a list of these Cells to the Controller each time it is initialized.<br/>

The agents will extend a Cell abstract class. So for the four given simulations, we plan on having four extensions of the Cell class (SegregationCell, WaTorCell, FireCell, and ConwayCell). Each implementation of the Cell class will include its own method to update its state (update()). A Cell will also know its coordinates, its neighbors, its current state, next state, all possible states, and the color of each state in the GUI. Setter and getter methods will be set for each of these. There will be one Cell per simulation. We chose to do this implementation because it makes it rather simple to add a new simulation--one would simply have to write a new class which extends our Cell class and write their own update method, and update the Loader class to prepare itself for an XML file with inputs for the according new simulation. <br/>

A Controller class will control all the Cells. The Controller will take the output from the Loader class and store it as a list of Cells, so it will know the locations and stats of all of the Cells. On each iteration, the controller will call on each of the Cells to update itself. This design ensures that to create a new model, only new implementations of Cell will have to be made. In addition, the Controller will initialize a few buttons to change the simulation, change the input file, adjust the parameters, and control the simulation (play, pause, reset, step). Once the action events are triggered in the GUI, they will be caught by the Controller.<br/>

Another View class will be created to house the UI. It will contain the main window, and will read from the Controller to update the screen. View will also display options to change the simulation, change the input file, adjust the parameters, and control the simulation (play, pause, reset, step). These will be communicated to the Controller.<br/>

USER INTERFACE
----
The UI will have a central window where the simulation will be displayed. The simulation will be updated by methods in the View class. Toolbars around the central window will activate the play, pause, and reset features; loading of new simulation files; and changing the simulation type.<br/>

![](UI.jpg "Below is a sketch of the window design:")

The cells will be displayed in the large box labeled “Cells”. Text under the display will indicate what type of simulation is running, and how many generations have elapsed. Play, pause, step, and reset buttons are located in that order in the next line. At the bottom, there is a Load button to load in a new XML file. A slider at the bottom also the controls. <br/>

If the user inputs incorrect or empty data, a dialog box will pop up that says the data is formatted incorrectly. The simulation text will be blank, and the cells display will just be a black screen. The program will work as normal once the user loads a properly formatted data file. <br/>


