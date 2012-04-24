#Addons

Starting with Dumpling, BeardAch offers a power addon system to allow you to add more awesome sauce to it,
in the form of addons. Addons are custom triggers and rewards written by the community. While they have
some limitations. To use them, you must understand the way addons are loaded.

##Loading addons
BeardAch loads triggers and rewards in 3 distinct stages.

1. Internal triggers/rewards - These are hard coded to load at boot and cannot be switched off, 
they can however be replaced by the next stages.
2. Bundled triggers/rewards - These are triggers and rewards that are part of the main BeardAch package, 
but not coded to load. Instead they must be added to the bundle.txt file inside BeardAch.jar to load them.
This provides a way to easily package custom triggers and rewards to customers (if you are a minecraft 
hosting provider) or for easier deployment. These can be replaced by external addons.
3. External addons - These will be the most familiar setup, placing an addon jar in the BeardAch/addons 
folder will load it after the previous two stages have loaded.

##Constraints
There is no guarantee that a trigger/reward will load after a plugin it requires has loaded, or even that 
said plugin will be loaded!

As such defensive programming is advised, for example, if you need to use the blarg plugin, you would have 
the following code during configuration.

    blargPlugin = (Blarg) Bukkit.getServer().getPluginManager().getPlugin("blarg");
    if(blargPlugin == null){
      BeardAch.printCon("BlargTrigger Could not load blarg! Will Attempt to load when triggered!");
    }
    
    
Please note, `BeardAch.printCon(String)` is the advised method for displaying errors to console. For debug, please  
use `BeardAch.printDebugCon(String)` which obeys the general.debug flag in config. 
for the trigger method, you should try and get the plugin again if it failed during config. Remember! It 
might have loaded after BeardAch!

    //inside trigger check function
    
    //try and load if not loaded
    if(blargPlugin == null){
      blargPlugin = (Blarg) Bukkit.getServer().getPluginManager().getPlugin("blarg");
    }
    
    //run check only if loaded
    if(blargPlugin != null){
      //run check and return status
    }else{
      BeardAch.printCon("ERROR: blarg plugin not found for BlargTrigger!, Please install blarg or uninstall this addon!");
    }

#Coding an addon, prerequisites
You will need:
* an IDE 
* Source code or jars for:
    * Bukkit
    * BeardAch
* this document
* an understand of how to code and follow instructions

#Coding an addon, making a trigger

All triggers must implement the `ITrigger` class, this contains two methods that MUST be implemented.

    public void configure(String config);
    public boolean checkAchievement(Player player);

the first method `configure(String)`, controls how a trigger attached to an achievement will behave.
The string will be the right hand part of the trigger definition in the achievements definition.
    ...
    triggers:
      - ach|This side of the "|" is the string that is passed to configure(String)

it is in configure you will perform the initial setup for an instance (parsing the config string, 
initialising variables and trying to get plugins).

`configure()` is called on each instance of a trigger that is created.

`checkAchievement(Player)` is called every time BeardAch runs a check of all online players. 
it will only be called if the achievement this trigger is attached to is one a player does not have.
It must return true if the trigger is triggered (i.e the condition is met), false otherwise.

Now you have a class that can be configured and can be asked if it's triggered, great! But you are 
not done yet. You must tell BeardAch what the trigger's tag is. This is a very easy one line piece 
of code as shown below (example taken from the AchievementTrigger):

    @Configurable(tag="ach")
    public class AchCheckTrigger implements ITrigger {
    
`@Configurable()` is an annotation that tells BeardAch's addon loader that any requests for a trigger 
called "ach" should use instances of this class. Thus as another example, the trigger definition for 
the achievement below

    .....
    triggers:
      - ach|test_ach
    .....
    
Would make the following happen during achievement creation

    create new instance of trigger identified by tag "ach"
    Configure new instance of trigger, passing "test_ach" into configure(String)
    .....

#Coding an addon, making a reward

Rewards are pretty much the exact same as triggers in terms of both having a `configure(String)` method 
and a `@Configurable()` annotation above the class definition.

The only difference is they must implement IReward and provide `public void giveReward(Player)` instead 
of `checkAchievement()`
 