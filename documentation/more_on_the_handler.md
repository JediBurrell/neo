#More On The Handler

If you've followed the previous documentation, you see two usage of the Handler.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/handler_usage.PNG)

So what is it, and why is it important?

The Handler class is the GameObject manager and renderer.

It has two main functions:

    tick();
	
	// and
	
	render(Graphics);

When you overrided the render function in the MainScene, you called handler.render(Graphics) at the bottom.

This is vitally important, because without this, nothing will render, and if you put it at the top, you'd still see no result.

**Leave handler.render(Graphics) at the bottom of render(Graphics)**

_Also, if you override tick, call handler.tick(). Placement doesn't matter._

Some other functions you'll use are:

    addObject(GameObject);

addObject(GameObject) is self explanatory, and you've used it before.

    addObject(GameObject, Integer);

This one is useful for various reasons. This allows you to prioritize certain GameObject's and put things things in the background.

If you put something at the 0 position ( in the array ), it will get processed first, but it will also render first, so anything after that _may_ render in front of it, if the positions contridict each other.

    removeObject(GameObject);

There's reasons to pay attention to documentation, and this is it.

If you try to remove an object, ( or add one for that matter ) while rendering or ticking, your game **will** crash.

You _need_ to set up two Arrays if you plan on removing or adding an object in real time.

This is pretty easy. Create two LinkedList variables of GameObject, name one toAdd, and the other toRemove, _not that names are important_.

Now at the bottom or the top of the tick function ( Override it, and don't forget to call super ), reference handler.object, and remove all of the toRemove objects, and add all of the toAdd objects, and then of course, clear the lists.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/remove_and_add.PNG)

Now whenever you need to add or remove objects, simply add it to the queue.

[More On Physics](https://github.com/JediBurrell/neo/blob/master/documentation/more_on_physics.md)