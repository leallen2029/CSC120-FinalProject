One option I am considering is the Secret Garden because it was my favorite book as a child. I think it would be fun to have the player explore different parts of the garden and follow the narrative. It could use a NSEW directional format. However, I think it would not be the best for this project because it is a chapter book, and I don’t see any clear path to do the narrative justice considering its length. 

Sherlock 

______
Intro()
Intro blurb
-Where you are 
-You get a phone call
-You wake up Watson and tell him to get going
Do you want to take a cab or walk

cab() 
Can you notice something but can't act on it

walk()
Watson takes a cab… because of his leg
You notice a luggage abandoned in an alleyway

…You arrive
_______
House()
You have arrived at the place of the death.
Do you want to look outside or inside? Or what do you want to do?

LookOutside()

LookInside()
upstairs()
bedroom()
floor()
body() what do you notice?


downstairs()

________
actions()
lookaround()
Tells the environment of a particular location 
Like each option needs to describe the place
Open
Take
Pickpocket
Enter?
notice()
- asks what the reader notices? 

you can do some things again  and you can put up and place down things

____________
?narratives()

ending options
you solve the crime as sherlock and preserve his honor
you solve the crime but convict the wrong person
you die
     

ohhh im going to make some general actions and override them in the instances


	Main.java
	Scene.java
	IntroScene.java
	HouseScene.java
    AdventureScene.java
	BustScene.java
	CabRideScene.java
	FinalScene.java
	Player.java
	Item.java
