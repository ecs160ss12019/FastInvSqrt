# As a paddle, I am attempting to reflect the ball when it hits me.  
When the left and right buttons are pressed the paddle should move accordingly.  
When the ball makes contact with the paddle, the ball should bounce upwards at an angle corresponding to which part of the paddle it hit.  
When the paddle attempts to go off screen, it should be kept within bounds.  


# As a ball, I bounce around the screen destroying the blocks.  
When the ball makes contact with any object (edge of the game, brick, paddle) it should bounce in a physically realistic way.  
When the ball makes contact with the brick, it should inflict a point of damage to the brick, destroying it if it reaches 0.  
When the ball goes off the bottom of the screen, it should decrease a life, and send update logic accordingly.  

# As a brick, I disappear and change the ball's angle by some degree on contact and drop items if I am an item brick.  
I should be able to be interacted with the ball.  
When the brick is hit by a ball, the brick should disappear (durable bricks would just be replaced with a new brick with one less health), and reflect the ball at a specific angle and may increase speed.  
If I am a buff/debuff brick, I should drop an item from where I disappeared.  

# Stage Level: As a stage level, I generate the wall placement according to a difficulty.  
Objects of a stage-level should be generated at proper positions when map data is loaded in.  
When selecting game modes/stage levels from the title screen, the correct stage-level should be generated.  

# Effect-Object: As an effect object I want to generate special effects when the ball comes into contact with me.  
When the ball hits the spike, a life should be loss.  
When the ball hits a wormhole, the position of the ball should be moved to the position of a connected wormhole after a short animation, while maintaining the same vector of movement.  

# UI/UX interface: As the UI/UX interface I want to display relevant information and game control buttons in an aesthetically pleasing way.  
When specific buttons are pressed, it should react accordingly (i.e. pressing the settings button will open up the settings).  
Should include a title menu, so I can select level and difficulty (stage level).  
After selecting level and difficulty, I should be able to start playing.  
After losing all of my lives, a retry button should show up, resetting my score for current level.  

# Sound Effects: As the sounds affects, I play music in the background of the game, I play specfic sound effects when things occur such as hitting power up. 
When the game starts, background music should begin playing.
When the a power up is contacted, a sound effect will play. When a brick is broken or destroyed a broken sound effect should be played. 

