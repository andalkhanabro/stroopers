
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


### Stretched User Stories (To Be Implemented)

- Stroopers should fail/end when constrained time is over

### Attributions 

- I did not want to use the in-built sorting methods of Java for my _sortScoreboard_ method. So, I learnt one of the 
sorting algorithms from YouTube and implemented the bubble sort algorithm in my code as per my needs. The exact video I 
learnt it from is available [here.](https://youtu.be/g8qeaEd2jTc)
- My project heavily relies on the ability to print colored text on the terminal. I studied about this and learnt about 
internal ANSI codes and how they can be used to do so. The exact ANSI codes I used in my methods are available
[here](https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html).