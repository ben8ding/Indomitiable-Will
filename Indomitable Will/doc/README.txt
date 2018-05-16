README
Nathaniel Lao, Matthew Li, Benjamin Ding
5/2/18

Description: This program is a top-down shooter game where the player represents the character Skal, whose mission is to save his village from an evil invasion. Skal can pick up multiple weapons, powerups, and other goodies to help aid his fight against the invaders. He also can find a map with his progress. Skal has the ability to fight boss enemies, who have special abilities and are stronger than regular enemies. 

Instructions: On the start screen, choose a menu option. The How to Play menu explains the instructions of the game more in depth. If the play option is selected, the player is then sent into the game. Skal should then enter rooms and defeat enemies to try to complete the game.

Skal can only shoot in the direction he is facing. The spacebar is used to shoot, and WASD to move up, left, down, and right, respectively. Kill enemies without dying, and gain powerups and weapons by running over them to aid your journey. 

Features: 
Must-Haves:
-1 Bot type - some form of enemy with a level of “intelligence”
-3 Unique weapons
-Boss enemies that the player will have to fight
-2 Power-ups that the player can acquire and use against enemies
-5 Multiple levels - to add depth into the game
Want-to-haves:
-Different characters the player can choose from
-Co-op mode when on the same computer
-Screen-Scrolling in larger rooms
-Minimap - to allow player more context into the endeavor
Stretch Goals:
-Networking Multiplayer mode
-Advanced Ai - moves in an unpredictable way
-PvP mode - to allow PvE and PvP gameplay


Class List:
-Main - contains the main method and runs the program
-Menu - title screen
-PauseMenu - Pause screen
-InstructionPanel - how to play
-DrawingSurface - the graphics window
-Level - a standard level in the game
-BossLevel - a level that contains a boss 
-Basic- template for objects
-Player - user controlled player and the object for other players (multiplayer mode)
-Enemy - base model for an enemy
-Weapon - model for a weapon
-Shotgun - model for a shotgun
-Rifle - model for a rifle
-Capsule - checks for collisions
-Projectile - model for a projectile
-Powerup - creates different powerups users can use
-Pickup - base class for obtainable object
-Obtainable - interface for weapons and powerups
-HitBox - creates hitboxes of AI and players


Responsibility List:
Matthew
-GUI design, Menus and Levels, timing, sprite finding, playtesting executive, uml
Nathaniel
-Readme, quality of life, balancing, interface design, subclass design
Ben
-enemy design, pseudo-AI,  superclass designer, class templates

Feedback given by: Dante Rella and Arjun Sampath

UML:
Pickup, Projectile, Bot, Boss, BossLevel, Weapon, Powerups, Obtainable, are islands because they are never instantiated or referenced in any other classes
Player and enemy are aspects of a level and levels should have players and enemies instead of the drawing surface having them

ReadMe:
AI is extremely ambitious as a must-have, especially with variable levels of intelligence
Need 5 want-to haves and 5 stretch goals
A lot of classes in the class list are not included in the UML
Obtainable interface is not needed because it’s the same thing as pickup so you can make pickup abstract instead
