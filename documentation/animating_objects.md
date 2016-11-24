#Animating Objects

We now have a square that can move around, but everything is very static.

Let's animate the Player.

Let's start by overriding the setVelX, and setVelY function, but do not call super.

Once you've done that, let's put toVelX ( a non existing variable ) and toVelY equal to the parameter.

Now create the global variable, it should be private.

Inside of the tick function, check if velX does not equal to toVelX, and same with velY.

Inside the if statement, check if the toVelX/toVelY variable is equal to zero.

If so, subtract velX / velY( not toVelX / toVelY ) divided by 20. Otherwise, add toVelX / toVelY divided by 20. ( 20 is very exaggerated, you should use something much smaller typically. )

Run the code, it eases the characters movement very well.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/easing_player.gif)

Now let's animate the players size based on its movement. ( Unless you're using an image, that wouldn't look good. )

This should be pretty simple, just add the absolute value of the velocity in the area where you drew your character.

    ... (int) (width+Math.abs(velX)), (int) (height+Math.abs(velY))

And you get this:

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/player_intensifies.gif)

[More On The Handler](https://github.com/JediBurrell/neo/blob/master/documentation/more_on_the_handler.md)