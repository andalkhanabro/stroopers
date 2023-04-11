
# Stroopers!

### **Weirdness. That’s a trait that the human brain and the universe have in common.**

By now, man has realised that the brain is exponentially more than just a bunch of cells. The three pound organ governs 
everything, be it thought, action, movement, vision, perception or just the very act of typing this sentence. 
Till date, humans continue to use external stimuli to probe into the brain organisation, trying to decode the underlying 
algorithms we subconsciously use and how our brains vary from one person to another. It is, inevitably, one of mankind’s
big intellectual quests.

In this application, I will be designing a simple game based on the *Stroop Effect.* 
Out of the many eccentric behaviors exhibited by the human brain, this is the delay in response caused while 
differentiating between congruent and incongruent stimuli. One subset of this effect is caused by color incongruence, 
where people tend to confuse the displayed color of a word with the color it spells.

Intrigued by the neuroscience which backs this phenomenon, 
my aim is to make *Stroopers:* a potentially indefinitely running game in which words will randomly appear on the user’s screen.
Each word will have a true color, with which it will be displayed in, and a color that the word will spell. 
The objective of the game will be to enter the first letter of the true color, without being confused by how it is 
spelled in a constrained time. Each correct answer will earn the user one point, while any incorrect answer or failure 
to give an answer will cause the game to be over. After a certain point threshold, the pacing of the game will also 
increase to make subsequent progress difficult. Finally, the user will be able to enter the earned score to a scoreboard
and see some performance statistics relative to everyone else who has played.

Playable by anyone who wants to put their brain to a test, this game is a simple variation of the testing used in 
research around the world. The Stroop Effect is a very real phenomenon and is accounted for by UX designers and artists
everywhere. With this game, I just want to simply demonstrate that our perceptions and actions are affected by a lot of 
things. Things we know and things we don't know.

### User Stories

- As a user, I want to be able to play *Stroopers* and earn a score 
- As a user, I want to be able to add my score to a scoreboard
- As a user, I want to be able to view and sort the scoreboard
- As a user, I want to be able to see my rank relative to other scores in the scoreboard 
- As a user, I want to be able to remove my score from the scoreboard
- As a user, I want to be able to save my scoreboard when I stop playing the game 
- As a user, I want to be able to load a previously saved scoreboard when I start playing a game 

### Instructions For Grader 

The GUI will usually have self-explanatory buttons as you go but there may be some steps that you need to follow. I have briefly explained them below. 

- The game will start with a visual interface, and you can load the previous data by clicking the _load game_ button if you wish to do so. This will show you a pop-up message that the data has been loaded successfully. 
- After this, you may press the button which says _PLAY_ to start the game. A screen with game instructions will immediately pop up and stay there for 10 seconds. Do not press any key yet else the action listeners will cause the game to be over. 
- After the rules screen, a countdown will be displayed automatically and eventually the first word will be displayed on the screen. You can play the game as instructed by the rules until the game is over. This entire sequence of changing screens and overlaying images on the press of a button is my _visual component_ for the program.
- After the game is over, your score count will be displayed on the screen. Now you may enter your name in the text field at the top of the screen. 
- To perform the _first_ action associated the X to Y requirement, you can now press the _ADD USER AND SAVE_ button. This adds your current profile to the board and saves the board in one step, at your discretion. 
- After this, a screen with 4 buttons will be displayed. To see a panel of Xs which have been already added to Y, you can click on the _DISPLAY BOARD_ button. A scrollable table should appear on the screen. 
- To perform the _second_ action associated with the X to Y requirement, you can now press the _RANK_ button to determine your rank as a pop-up message relative to other players on the scoreboard. 
- If you want to play again, you may press the play again button and conversely, if you want to exit the game, pressing the exit button will prompt the program to terminate.

### Attributions 

- I did not want to use the in-built sorting methods of Java for my _sortScoreboard_ method. So, I learnt one of the 
sorting algorithms from YouTube and implemented the bubble sort algorithm in my code as per my needs. The exact video I 
learnt it from is available [here.](https://youtu.be/g8qeaEd2jTc)
- My project heavily relies on the ability to print colored text on the terminal. I studied about this and learnt about 
internal ANSI codes and how they can be used to do so. The exact ANSI codes I used in my methods are available
[here](https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html).
- I understood and modified a lot of the code given by the CPSC 210 team in the WorkRoomApp to implement and test 
data persistence in my application.

### Phase 3 Attributions 

- Based on IntelliJ's recommendations, I looked into lambda expressions and used them in my code to write neat code in action listeners. The resource I used is available [here.](https://youtu.be/tj5sLSFjVj4)
- I learnt how to set a background image of a component in Java Swing from [this](https://www.youtube.com/watch?v=yGcYoz0s94E) resource. 
- I obtained a list of supported font styles in Java Swing from [here.](http://www.java2s.com/Tutorials/Java/Java_Swing/1520__Java_Swing_Font.htm)
- I defined custom colors and used hexadecimal and RGB representations of Java Color objects by using the methods explained [here.](https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html)
- To generate a pre-determined set of answers as strings for hexadecimal representations of colors, I used a map data structure as a static field as explained [here.]([https://www.baeldung.com/java-initialize-hashmap)
- I learnt about JLabels, JButtons, JPanels, JTextFields, JTextAreas, and JScrollPane from the original java documentation from each and used associated methods. 
- I learnt about using custom border layouts for a component using the BorderFactory class from [here.](https://youtu.be/Eb2QydjQvV4)
- I utilised the JTable class to make my scoreboard and learnt how to make a JTable from [here](https://youtu.be/TwMXA1S38qg) and how to modify it using a Table Model from [here.](https://docs.oracle.com/javase/tutorial/uiswing/components/table.html)
- Because I wanted absolute control over the positioning over my components in the Jframe, I used a null layout and learnt how to do so from the original Java documentation for it which is available [here.](https://docs.oracle.com/javase/tutorial/uiswing/layout/none.html)
- I used the Java Timer and TimerTask classes to do certain actions after certain time intervals as learnt from the resource available [here.](https://stackoverflow.com/questions/4044726/how-to-set-a-timer-in-java)



### Image Attributions 

- For the background of my starting interface, I used an abstract image which is available [here.](https://stock.adobe.com/ca/images/Abstrakt-farbiges-Hintergrundmuster/25209792?)
- For my countdown screen, I used an image to encircle my numbers for aesthetic purposes which is available [here.](https://unsplash.com/photos/UgNjyPkphtU?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText)
- I used an icon which says "rules" for my game instructions which is available [here.](https://www.bbc.co.uk/bitesize/topics/zvypmfr/articles/z7bfhbk)
- I added a play icon to my play button which can be licensed from [here.](https://www.vecteezy.com/free-vector/video-play-icon)


## **Phase 4** 

### **UML Diagram** 

- The UML Diagram is uploaded to the root directory




### **Sample EventLog** 

The image below shows a simple execution of my program whereby a user starts the program, loads data from file, plays the game
and earns points and saves his profile to the scoreboard. Initially, the first few events show the parsing events, where old users
are loaded and added back to the board. Then, a new score profile is made and the game starts. The events that follow show the user
playing the game, and each event shows how giving a correct answer increments the point count. Finally, on the first incorrect answer, 
the profile of the new user is made and added to the board at the user's discretion. The final events then, are, sorting of the scoreboard 
and determining of the rank of the user who just played. In this case, Aandal, Jibran and Shahbaz were 3 old users whose profiles were loaded from file 
and Mike was the new user who played the game, earned points, added himself to the board and got an assigned rank. 

**Note**: The parsing events show up in the event log because parsing from the _JSON_ format and adding scores back to the board
requires the use of the _addScore(Score score)_ method  in the model package. Since both instances, adding a new user and parsing old users, make use
of the same method, they show up in the event log the first time the game is loaded. 

Tue Apr 11 16:44:17 PDT 2023
Profile [Username: Jibran, Points: 11] has been added to the board!


Tue Apr 11 16:44:17 PDT 2023
Profile [Username: Shahbaz, Points: 7] has been added to the board!


Tue Apr 11 16:44:17 PDT 2023
Profile [Username: Aandal, Points: 7] has been added to the board!


Tue Apr 11 16:44:19 PDT 2023
A new score has been profile made! Game starting with 0 points..


Tue Apr 11 16:44:44 PDT 2023
Earned 1 point! Player has 1 points now.


Tue Apr 11 16:44:45 PDT 2023
Earned 1 point! Player has 2 points now.


Tue Apr 11 16:44:49 PDT 2023
Earned 1 point! Player has 3 points now.


Tue Apr 11 16:44:50 PDT 2023
Earned 1 point! Player has 4 points now.


Tue Apr 11 16:44:52 PDT 2023
Earned 1 point! Player has 5 points now.


Tue Apr 11 16:45:01 PDT 2023
Earned 1 point! Player has 6 points now.


Tue Apr 11 16:45:02 PDT 2023
Earned 1 point! Player has 7 points now.


Tue Apr 11 16:45:04 PDT 2023
Earned 1 point! Player has 8 points now.


Tue Apr 11 16:45:06 PDT 2023
Earned 1 point! Player has 9 points now.


Tue Apr 11 16:45:12 PDT 2023
Profile [Username: Mike, Points: 9] has been added to the board!


Tue Apr 11 16:45:12 PDT 2023
Scoreboard has been sorted according to points using a sorting method!


Tue Apr 11 16:45:12 PDT 2023
Rank of Mike is 2/4 on the board!



Process finished with exit code 0


### **Refactoring and General Analysis Of Design** 

While making my UML Diagram, I realised that there are multiple instances where the design of my code can be improved, 
especially in my big UI classes like GamePanel & GUI where cohesion is poor. These classes, albeit fully functional, do
more than one thing and are generally unfocused, managing all the technicalities of making AND setting up the components
of the GUI. I believe that I can factor out a class just to make buttons, or even a collective
helper method to set the common functionalities of buttons and labels instead of making a separate method for each one 
of them (i.e makePlayButton, makeAddUserButton, exitButton etc) and thereby make the code easier to read and modify by 
enabling a single point of control, which is a vital design principle as seen in CPSC 110. Additionally, I see that while
I have separate classes for ScoreboardGUI and ScoreUI, my main classes do not have associations with them, meaning that 
I have instantiated new objects wherever required, instead of making them fields of the class with a global access and reducing memory usage. 
Hence, another possible refactoring is to single out such object instances and make them the fields of the class (e.g 
making ScoreBoardGUI a static field of GUI which can be referred to anywhere in the rest of the code.)

Another possible refactoring is in MyCustomColors class. Here, while I define my own colors using RGB values, I make use 
of built-in Java colors as well, and the same class is making colors, managing internal representations (hexadecimal, name, RGB)
and storing them as a map data structure. This means that the class has low cohesion and can be broken down into simpler classes for 
improved readability. One way of doing so is to make an abstract class Color to represent a general color, and then make two classes
MyColors and JavaColors extend it, abstracting out the methods that are common to both to reduce code duplication. Alternatively, 
another way around this could be to make an enumeration of colors and use that in other classes instead of making them fields 
and managing them in the same class. 

Overall, there are also multiple instances in my TUI code where I code abstract out a field of type Scanner and reinitialise it 
wherever I require user input and hence a Scanner object. While this might not affect performance significantly right now 
because of the scale of the codebase, I believe it would be good practice to make use of the same object instances wherever 
possible.