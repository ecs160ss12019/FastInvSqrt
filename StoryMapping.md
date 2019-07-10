# Story Mapping for Breakout

## BHAG
You are a paddle, and you are attemping to destory as many bricks as possible by using a ball that you bounce, while ensuring that it stays in bound.

## User Goals:
- Paddle: As a paddle, I am attempting to reflect the ball when it hits me.
- Ball: As a ball, I bounce around the screen destroying the blocks.
- Brick: As a brick, I disappear and change the ball's angle by some degree on contact and drop items if I am an item brick.
- Stage Level: As a stage level, I generate the wall placement according to a difficulty.
- Effect-Object: As an effect object I want to generate special effects when the ball comes into contact with me.
- UI/UX interface: As the UI/UX interface I want to display relevant information and game control buttons in an aesthetically pleasing way.
 
| Epic Story | As a paddle, I reflect the ball when it hits me. | As a ball, I bounce around the screen destroying the blocks. | As a brick, I disappear and change the ball's angle on contact. | As a stage level, I generate the wall placement. | As an effect object I want to generate special effects when the ball comes into contact with me. | As the game screen I want to display relevant information and game control buttons in an aesthetically pleasing way. |
|------------|--------------------------------------------------|--------------------------------------------------------------|----------------------------------------------------------------|--------------------------------------------------|--------------------------------------------------| --------------------------------------------------|
| Sprint 1   | Able to be move paddle and bounce the ball back	| Moves around the screen and bounces off bricks and screen edges.	| Able to be placed on screen and change the angle of the ball on contact.	| Generate a hardcoded level. |	Create a wormhole object that teleports the ball to a different wormhole.| Add buttons that control the horizontal movement of the paddle. |
| Sprint 2   | Able to change paddle size based on items and achieve finer controls using physics	| Able to adjust speed of ball based on items and duration of the game	|  Power-Up and Power-Down bricks are able to drop items that modify the paddle and ball. Bricks can dissappear when destroyed by ball.	| Able to programatically change level and select levels from the title screen.	|	Create a spike object that destroys the ball and decrements the number of lives left. | Add display that keeps track of number of lives left, current stage level, and score. For now use a simple scoring system. Also add a pause button to pause the game. |
| Sprint 3   | Add sound effect when ball makes contact with paddle. Add animation when paddle changes size.	|  Animation and effects for when the ball makes contact with special objects(spikes and wormholes)	|  Add animation and sound effects for destruction of bricks. Add explosive bricks that destroy surrounding bricks on contact.	| Design original levels.	| Add animations and sound effects for both spikes and wormholes. | Modify scoring system to a combo-based scoring system. Add animation and sound effects for when lives decrement, score increments, and when reaching combos. |
