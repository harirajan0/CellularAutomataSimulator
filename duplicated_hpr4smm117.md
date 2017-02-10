*PEER CODE REVIEW**
========================
Code Smell Refactoring Decision 1: Making State Interface which was implemented by Enums for each simulation
--------------------------------------------------------------------------------------
There was a lot of duplicate code within the Cell.java subclasses, such as the paint() method. For the paint method, initially, the cell checked what state it was, and depending on its state, it would paint itself a certain color. We fixed this by refactoring our code by creating an enum for each simulation's state. The enum implemented a State.java interface. This made it easy to get the current cell's color by simply calling getCurrentState().getColor() rather than using a switch case depending on the current state. 

Code Smell Refactoring Decision 2: Moving updateModel() method from Model subclasses to the Model class
--------------------------------------------------------------------------------------------
The updateModel() method in each of the Model sublclasses was the same except for in SegregationModel.java, there were two lines that were different. To fix this problem, we decided to simply move the updateModel() method to the superclass and have SegregationModel.java simply obverride the superclass method. 

CheckList Refactoring Decision 1: Removing use of concrete ArrayList<>
-----------------------------------------------------------------------
Our ViewClass.java had a method which took in an ArrayList<> as an argument. After looking at the project, we realized this whole method was actually unnecessary so we decided to delete the method as a whole which also eliminates the issue of using a concrete list. If the method was indeed necessary, we would have fixed this by passing in a generic List<> rather than an ArrayList<>.


CheckList Refactoring Decision 2: Shortening paint() method from 13+ lines to 1 line
------------------------------------------------------------------------------------
As a result of our Code Smell refactoring Decision 1, we were able to make the paint() method one line and have it only exist in the Cell.java superclass rather than having 4 10+ line methods live in each of the subclasses. We were able to do this by creating an Enum for each simulation's possible states. 
