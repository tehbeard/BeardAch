#Triggers

Triggers are conditions that must be satisfied for an achievement to activate.

Neumerous triggers exist for BeardAch, and you can write your own!


##Achievement/No achievement trigger

Satisfied when a player has an achievement

    ach|Achievement_slug_name_here
    ach|test_ach

substitute noach for ach to use no achievement (does not have the achievement)
  
##Achievement count trigger

Satisfied when a player has a certain number of achievements with a defined prefix

    achcount|slug_prefix:threshold
    achcount|test_:5
    
##Stat within boundaries trigger

Satisfied when a statistic in BeardStat is between certain values

    statwithin|category:stat:lowerBound:upperBound
    statwithin|stats:lastlogin:3000000:3003600
    
##Stat trigger

Satisfied when a statistic in BeardStat is past a threshold

    stat|category:stat:boundary
    stat|stats:playedfor:3600
    
##Permission trigger

Satisfied if a player has a certain permission node

    perm|permission.node.here
    
##Locked trigger

Disables an achievement

    locked|
    
    
##Economy trigger

satisfied if a player has a certain amount of money
    
    bankbalance|amount
    bankbalance|1000000
    
##Cuboid trigger

Satisfied when a player enters an area

    cuboid|worldname:x1:y1:z1:x2:y2:z2
    cuboid|world:0:0:0:16:128:16

##Speedrun

Satisfied when a player travels between two defined cuboids within a defined time period

    speedrun|cuboid1/cuboid2/time (seconds)
    cuboids follow the same format as the cuboid trigger
    worldname:x1:y1:z1:x2:y2:z2
    
    


#Rewards

Rewards are given when an achievement is completed


##counter reward

Arbitrary counter, stored in BeardStat database under the achCount category

    counter|counter_name:valueToAdd
    counter|chain1:1
    
##command reward

Executes a console command, replacing <PLAYER> with the player's name  
Also note the lack of /, this is not needed

    comm|give <PLAYER> 46 1

## DroxPerms subgroup reward

Adds a subgroup to the user

    subgroup|group_name
    
## DroxPerms track reward

Promotes a user along a track in DroxPerms

    promote|track_name

##Economy reward

rewards the player with money into there account

    money|amount
