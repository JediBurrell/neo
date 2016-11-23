#Creating Your First Project

Start by creating your work environment, if you don't know the basics of Java, you should not be using neo.

Create a "Main" class, you may name it whatever you like, though it will be referred to as Main in these documentations.

Make your class extend the Neo class.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/extends_neo.PNG)

In eclipse you can Ctrl+Shift+M to auto-import the class. Otherwise, copy the following. You'll need to import the entire package.

    import io.qwerty.neo.*;

Create the constructor, you'll want to take in a Window parameter and call "super(window);" in the first line.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/constructor.PNG)

Go ahead and create your main function.

	public static void main(String[] args){
	
		
	
	}

Inside the main function, create a Window.

    Window window = new Window("My Project");

Set your windows size with either .setSize(Dimension) or .setSize(Int, Int)

_It's recommended to stick with the default ratio of 16:9._

_Whenever you resize the window, it will scale rather than reveal more screen._

_Sticking with default screen ratio prevents it from scaling strangely._

Set any other desired variables for your window, then create a new Main with the window variable you just created.

_Code snippets will not be added for obvious instructions._

Now set the content of the Window to your new Main variable.

    window.setContent(main);

It will ask you to catch any exceptions. You can add the following to the main function, as this won't be called.

    ... throws UnchangableException ...

Now below setting the content you'll start the window by calling window.start();

And after that, start the Main variable as well.

&nbsp;

You can now run the program and see that a Window has been created.

![Screenshot](https://raw.githubusercontent.com/JediBurrell/neo/master/documentation/images/a_window.PNG)

[Rendering To The Screen](https://github.com/JediBurrell/neo/blob/master/documentation/rendering_to_the_screen.md)