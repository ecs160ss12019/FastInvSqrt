# Story Mapping for Breakout

## BHAG
You are a paddle, and you are attemping to destory as many blocks as possible by using a ball that you bounce, while ensuring that it stays in bound.

## User Goals:
- Paddle: As a paddle, I am attempting to reflect the ball when it hits me.
- Ball: As a ball, I bounce around the screen destroying the blocks.
- Wall: As a wall, I disappear and change the ball's angle by some degree on contact.
- Stage Level: As a stage level, I generate the wall placement according to a difficulty.

| Epic Story | As a paddle, I reflect the ball when it hits me. | As a ball, I bounce around the screen destroying the blocks. | As a wall, I disappear and change the ball's angle on contact. | As a stage level, I generate the wall placement. |
|------------|--------------------------------------------------|--------------------------------------------------------------|----------------------------------------------------------------|--------------------------------------------------|
| Sprint 1   | Able to be moved.                                | Moves around the screen.                                     | Able to be placed on screen.                                   | Generate a hardcoded level.                      |
| Sprint 2   | Able to bounce the ball back.                    | Bounces off walls and screen edges.                          | Able to change angle of ball.                                  | Able to programatically change level.            |
| Sprint 3   | Finer control using physics.                     | Makes walls disappear.                                       | Disappears when hit by ball.                                   | Able to generate level based on difficulty.      |
