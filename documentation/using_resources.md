#Using Resources

Sometimes you need to save settings, maybe skins or maps.

Using Resources, you can save the necessary files and encrypt them to make sure they aren't easily changed.

To do this, you will need the Apache library linked at the root of this repository.

Go ahead and add the library, then open your Main class.

Inside of your main class, Auto Import the Resources file, and create two functions, loadResources, and createResources.

Inside of onLoad at the bottom of the function, call loadResources.

Now create a global static string called PROJECT_ROOT.

Make this equal to a static call to FileManager's function getAppdata ( this only works for Windows, please create your own function for cross-compatibility ), plus "\\." your project name.

Or something of the sorts.

Now inside of loadResources, call the Resources static function loadResources, with PROJECT_ROOT, and a random but static String. This is your encryption key.

It's going to throw an error, so wrap it with a try and catch statement. Don't call printStackTrace in catch, instead call createResources.

When the error was thrown, your resources base was already created. So inside of createResources just set your default resources.

For this example, we're creating an integer of Player's x, and Player's y. Set it to the default.

This will throw an error, just wrap it with a try and catch statement, and ignore the catch statement, nothing will happen.

Now when creating the player, instead of using a static x and y position, call Resources.getInt() of your x and y variable.

This will throw also throw an error, if you did everything right, there should be no errors.

You can create a basic counter that will set the settings every few ticks, or just create a WindowListener. It's up to you.

You can use this system to do way more complex stuff as well, like for example, creating a sandbox that can be edited. Having a skin system. And obviously for settings.

It's extremely easy to use if you know how to use it.

Test your code, close and start it up again, and you should see your Player resumes in the position he was in when you exited.