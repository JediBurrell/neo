#More On Physics Part 2

So far you can move on the ground, fall, and jump.

But what's the point of jumping if there's nothing to jump over or onto?

Inside of onLeftCollision, tell it to set the Player's x position to the object's x plus objects y, immediately set velY equal to zero if it's less than zero.

You should also go back to onBottomCollision, as well as the rest of the collision functions, and wrap everything in an if statement making sure you're colliding with an ObjectID.object, and not anything else, because you'll want to have different actions for different kinds of objects, and right now, this is only the ground.

onRightCollision, set Player's x position to object's x - Player's width, immediately set velY equal to zero if it's greater than zero.

onTopCollision, set Player's y position to object's y position plus object's height, toVelY equal to MAX_VELOCITY, velY immediately to zero, and you're done.

To test this code, add more Ground Object's in various locations.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/collision.gif)