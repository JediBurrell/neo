#Making Objects

So far we've created a Window that can display a color in the background. But let's create something worthwhile.

To do this, we're going to create objects. You can do this by extending GameObject. We'll call it Player.

**Do not try to render objects in the render function of the scene, make a GameObject, always!**

Create this in a new package ( ...objects ).

Then create the constructor. This takes an X, Y, and an ObjectID parameter. You can skip the ObjectID however, and choose one statically from the ObjectID class when calling super.

A width and height parameter is suggested, but only for objects that aren't final. Otherwise, set the width and height in the constructor.

_You do have to set the width and height parameter manually, super will not. Though the parameters do exist and are protected._

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/player.PNG)

Add unimplemented methods.

Let's set our boundaries for collision purposes. Your getBounds() should look similar to this. _You must always return a Rectangle or the game may crash._

_If it doesn't require collision, make an empty Rectangle, but make sure it is a Rectangle and not null._

	@Override
	public Rectangle getBounds(){
	
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

Obviously you can change the boundaries to your needs, but that's the usual necessities.

Because we're creating a character, we're going to need some more advanced boundaries.

Create four boundary methods - topBounds(), rightBounds(), bottomBounds(), and leftBounds().

They should be properly spaced apart so that if right collides, the top doesn't as well. Use diagram to base your boundaries on.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/boundary_diagram.PNG)

After you've done that, override the tick function, but do not call super unless you're using the onCollision event listener.

Inside create a loop at the beginning of the tick function. Make sure the GameObject is not the one you're creating

    if(object!=this) ...

And check if object.getBounds().intersects() with any of the bounds.

To save you a headache, here's the code snippet.

	for(GameObject o : objects){
		if(o!=this){
			if(o.getBounds().intersects(getTopBounds()))
				onTopCollision(o, o.getId());
			else if(o.getBounds().intersects(getBottomBounds()))
				onBottomCollision(o, o.getId());
			else if(o.getBounds().intersects(getRightBounds()))
				onRightCollision(o, o.getId());
			else if(o.getBounds().intersects(getLeftBounds()))
				onLeftCollision(o, o.getId());
		}
	}

Then to not discount velocity, let's add velX and velY to the x and y variable in the tick function. This only needs to be done when not calling super.

	x+=velX;
	y+=velY;

An example of what you can do with onCollision is create an enemy, and inside of onCollision check if the ObjectID is equal to ObjectID.player ( or check instanceof GameObject ) and call a die or attack function from the PlayerObject.

Now that we're done implementing the basic physics ( more or less ) let's render the player.

If you have an image for the player, you can put it in a source folder ( not the src folder ), and create a new BufferedImageLoader, and loadImage().

But we'll make it a blue square.

Inside of render(Graphics), like we did in the scene, we'll use the Graphics class.

	public void render(Graphics arg0){
	
		arg0.setColor(new Color(50, 50, 200));
		arg0.fillRect((int) x, (int) y, (int) width, (int) height);
	
	}

You're done with creating the Player for now, obviously you'll need to tweak it to your needs.

Go to the MainScene, create a global Player variable, and in the render function at the bottom, call:

	...
	
	handler.render(arg0);

Now in the onLoad function, call:

	player = new Player(100, 100, 50, 50);
	handler.addObject(player);
	
	...
	
If you run it, you'll see the Player rendering. Now let's make it move.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/our_player.PNG)

Still in the MainScene, override onKeyPressed(), and onKeyReleased().

In these functions you'll call player.setVelX / player.setVelY, follow this diagram.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/keystroke_diagram.PNG)

We now have a moving player!

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/moving_player.gif)

[Animating Objects](https://github.com/JediBurrell/neo/blob/master/documentation/animating_objects.md)