#Rendering To The Screen

If you ran your project, you may have noticed you got a NullPointerException.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/nullpointerexception.PNG)

The reason you received this error is because when you started Main, it looked for a Scene to begin working with, but could not find one.

So let's make a Scene. Start by making a new class ( we recommend doing it in a separate package ( ...scenes ) ) and extend Scene. For reference, we'll call it MainScene, but you're free to name it whatever you want.

    public class MainScene extends Scene

_We assume you're auto-importing packages now._

Once you've done that, create your constructor; this should take a Neo parameter ( Remember, Main extends Neo ), and send it through super.

Implement the required methods.

    @Override
	public void render(Graphics arg0){
	
	}

And override the onLoad method. Because you're not using Resources yet, it's not necessary, but it's still recommended to do your Scene setup in the onLoad function.

You can leave it empty since there are no objects yet.

Add a global Neo variable, and in your constructor make your neo variable equal to the neo parameter.

Now inside of render, let's create a background. _We'll show you how to do this, but it is highly recommended you read over the [Java documentation for Graphics.](https://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html)_
You should rename the Graphics variable to "g", though we'll refer to it as arg0.

    ...
	
	arg0.setColor(new Color(32, 32, 32));
	arg0.fillRect(0, 0, neo.width(), neo.height());
	
	...

Let's go back to the Main class, in the constructor make the protected variable "scene" equal to MainScene.

MainScene's code should look like this:

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/rendering_code.PNG)

And when you run the code, you'll no longer get the NullPointerException!

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/rendering_result.PNG)

[Making Objects](https://github.com/JediBurrell/neo/blob/master/documentation/making_objects.md)