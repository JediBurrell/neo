#More On Physics Part 1

To make a more player like player, we need to add falling, colliding, and jumping.

Let's start by adding a falling and jumping boolean in the Player, as well as a MAX_VELOCITY float.

Inside of the onBottomCollision function you created, set falling and jumping equal to false.

Above the collision check ( which should currently be at the top of tick ), set falling equal to true, velX equal to zero, and set the Y position to object.getY() minus Player height. Underneath the collision check say if falling is equal to true, set toVelY equal to MAX_VELOCITY. Play around with the value.

If you run the program, your Player will fall.

Now create a GameObject named GroundObject with the ObjectID object. You should know how to do this.

Add a new instance of it to the Handler in the MainScene, with width equal to neo.width(), height equal to ~50, x equal to 0, and y equal to neo.height() minus 50.

Run the code and you'll fall and land on the GroundObject.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/on_the_ground.PNG)

Create a function that sets the jumping variable ( return if jumping is false ), sets toVelY equal to -MAX_VELOCITY, and sets Y position to Y position minus 3 in the Player. We'll call it setJumping(boolean).

Now in the MainScene, replace onKeyPress of "W" with setJumping(true). On release, do nothing.

Where you checked if the Player is falling, add &&!jumping. Test your code, this is fun.

Now where you checked if toVelY doesn't equal to velY, add an else statement. Inside, set toVelY equal to MAX_VELOCITY.

Now inside the setJumping function ( first line ), return if trying to set jumping to true, and the player is already jumping.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/jumping.gif)

[More On Physics Part 2](https://github.com/JediBurrell/neo/blob/master/documentation/more_on_physics_2.md)