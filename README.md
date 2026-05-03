# CSC120-FinalProject

## Deliverables:
 - Your final codebase 
 - Your revised annotated architecture diagram
 - !! This is in Planning.md!! Design justification (including a brief discussion of at least one alternative you considered) 
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
My overall approach was to build the game step by step instead of trying to do everything at once. I started by setting up the basic structure with different scenes, then made sure I could move between them. After that, I added the main actions like looking around, inspecting things, and picking up items. Once the basics were working, I focused on adding more detail, like tracking the player’s choices and using those choices to affect what happens later in the game. I tested a lot as I went so I could catch problems early and fix them before moving on. Overall, I just tried to get something working first, then improve it and make it more interesting as I went.

 - What **new thing(s)** did you learn / figure out in completing this project?
I learned two primary things, not that were entirely new concepts but as different applications. Firstly, I learned how useful overriding can be. I used it as a primary mechanism in my code and it was incredibly helpful. At first when we learned it in class I was a bit confused about how this would be used in different cases but I feel as though I gained a greater understanding here. I also got a better sense of how to implement state-based game logic using boolean flags to control progression. I used variables like toldWatson, solvedSuitcase, and policeArrived to dynamically change what actions were available and which ending the player reached.
 - Is there anything that you wish you had **implemented differently**?

 - If you had **unlimited time**, what additional features would you implement?
Per the feeback of my younger brother, more tension would be interesting. Like, I think in BakerSt. It would be fun if there was a countdown to when you have to make decisions by. I also throughout the project have been adding more and more detail to scenes like murder room and outside. I would like to continue doing that so there are even more features you can explore. This would make the world feel larger and the player feel like they have more autonomy. 

 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
Prof. Hia gave me the most helpful advice. I was having a hard time figuring out how to deal with directions in this game. This prompted me to add the peice where you can go back to the suitcase, but only if you noticed it. I think this is a really fun addition that not only makes the game harder but more interesting. 

 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
I would tell myself to plan out state management more clearly from the beginning. A lot of my debugging came from tracking multiple boolean flags across scenes, so designing how those states interact earlier would have saved time.

 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.
 I did not work with a team.
