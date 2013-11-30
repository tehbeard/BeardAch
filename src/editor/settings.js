$(function(){initConfig({
  "triggers": [
    {
      "fields": [
        {
          "key": "ach",
          "name": "achievement slug",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "ach",
      "name": "has achievement"
    },
    {
      "fields": [
        {
          "key": "threshold",
          "name": "achievement count threshold",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "prefix",
          "name": "achievement slug prefix",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "achcount",
      "name": "Achievement count"
    },
    {
      "fields": [
        {
          "key": "ach",
          "name": "achievement slug",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "noach",
      "name": "Does not have achievement"
    },
    {
      "fields": [
        {
          "key": "isStormy",
          "name": "Storm currently?",
          "type": "bool",
          "min": false,
          "max": false
        }
      ],
      "type": "storm",
      "name": "Is it stormy?"
    },
    {
      "fields": [
        {
          "key": "after",
          "name": "After this time(ticks)",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "before",
          "name": "Before this time(ticks)",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "time",
      "name": "Between two times"
    },
    {
      "fields": [],
      "type": "locked",
      "name": "Lock this achievement"
    },
    {
      "fields": [
        {
          "key": "triggers",
          "name": "triggers",
          "type": "trigger",
          "min": false,
          "max": false
        }
      ],
      "type": "metaand",
      "name": "Meta AND trigger"
    },
    {
      "fields": [
        {
          "key": "triggers",
          "name": "triggers",
          "type": "trigger",
          "min": false,
          "max": false
        }
      ],
      "type": "metaor",
      "name": "Meta OR trigger"
    },
    {
      "fields": [
        {
          "key": "biome",
          "name": "Biome",
          "type": "selection",
          "values": [
            "SWAMPLAND",
            "FOREST",
            "TAIGA",
            "DESERT",
            "PLAINS",
            "HELL",
            "SKY",
            "OCEAN",
            "RIVER",
            "EXTREME_HILLS",
            "FROZEN_OCEAN",
            "FROZEN_RIVER",
            "ICE_PLAINS",
            "ICE_MOUNTAINS",
            "MUSHROOM_ISLAND",
            "MUSHROOM_SHORE",
            "BEACH",
            "DESERT_HILLS",
            "FOREST_HILLS",
            "TAIGA_HILLS",
            "SMALL_MOUNTAINS",
            "JUNGLE",
            "JUNGLE_HILLS"
          ],
          "min": false,
          "max": false
        }
      ],
      "type": "biome",
      "name": "Biome check"
    },
    {
      "fields": [
        {
          "key": "includeIgnored",
          "name": "Include commands that have been cancelled by preprocess event",
          "type": "bool",
          "min": false,
          "max": false
        },
        {
          "key": "isRegex",
          "name": "Treat prefix as regex expression",
          "type": "bool",
          "min": false,
          "max": false
        },
        {
          "key": "commandText",
          "name": "Command prefix to detect",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "oncommand",
      "name": "On command"
    },
    {
      "fields": [
        {
          "key": "perm",
          "name": "permission node",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "perm",
      "name": "has permission node"
    },
    {
      "fields": [
        {
          "key": "itemStr",
          "name": "Item String (CraftBook item syntax)",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "ignoreDurability",
          "name": "Ignore durability values",
          "type": "bool",
          "min": false,
          "max": false
        },
        {
          "key": "ignoreMetadata",
          "name": "Ignore metadata (Enchantments, Lore)",
          "type": "bool",
          "min": false,
          "max": false
        }
      ],
      "type": "wearitem",
      "name": "wearingitem"
    },
    {
      "fields": [
        {
          "key": "entityType",
          "name": "Entity",
          "type": "selection",
          "values": [
            "DROPPED_ITEM",
            "EXPERIENCE_ORB",
            "LEASH_HITCH",
            "PAINTING",
            "ARROW",
            "SNOWBALL",
            "FIREBALL",
            "SMALL_FIREBALL",
            "ENDER_PEARL",
            "ENDER_SIGNAL",
            "THROWN_EXP_BOTTLE",
            "ITEM_FRAME",
            "WITHER_SKULL",
            "PRIMED_TNT",
            "FALLING_BLOCK",
            "FIREWORK",
            "BOAT",
            "MINECART",
            "MINECART_CHEST",
            "MINECART_FURNACE",
            "MINECART_TNT",
            "MINECART_HOPPER",
            "MINECART_MOB_SPAWNER",
            "CREEPER",
            "SKELETON",
            "SPIDER",
            "GIANT",
            "ZOMBIE",
            "SLIME",
            "GHAST",
            "PIG_ZOMBIE",
            "ENDERMAN",
            "CAVE_SPIDER",
            "SILVERFISH",
            "BLAZE",
            "MAGMA_CUBE",
            "ENDER_DRAGON",
            "WITHER",
            "BAT",
            "WITCH",
            "PIG",
            "SHEEP",
            "COW",
            "CHICKEN",
            "SQUID",
            "WOLF",
            "MUSHROOM_COW",
            "SNOWMAN",
            "OCELOT",
            "IRON_GOLEM",
            "HORSE",
            "VILLAGER",
            "ENDER_CRYSTAL",
            "SPLASH_POTION",
            "EGG",
            "FISHING_HOOK",
            "LIGHTNING",
            "WEATHER",
            "PLAYER",
            "COMPLEX_PART",
            "UNKNOWN"
          ],
          "min": false,
          "max": false
        }
      ],
      "type": "deathentity",
      "name": "Killed by"
    },
    {
      "fields": [
        {
          "key": "itemStr",
          "name": "Item String (CraftBook item syntax)",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "ignoreDurability",
          "name": "Ignore durability values",
          "type": "bool",
          "min": false,
          "max": false
        },
        {
          "key": "ignoreMetadata",
          "name": "Ignore metadata (Enchantments, Lore)",
          "type": "bool",
          "min": false,
          "max": false
        }
      ],
      "type": "invcheck",
      "name": "Inventory check"
    },
    {
      "fields": [
        {
          "key": "entityType",
          "name": "Entity",
          "type": "selection",
          "values": [
            "DROPPED_ITEM",
            "EXPERIENCE_ORB",
            "LEASH_HITCH",
            "PAINTING",
            "ARROW",
            "SNOWBALL",
            "FIREBALL",
            "SMALL_FIREBALL",
            "ENDER_PEARL",
            "ENDER_SIGNAL",
            "THROWN_EXP_BOTTLE",
            "ITEM_FRAME",
            "WITHER_SKULL",
            "PRIMED_TNT",
            "FALLING_BLOCK",
            "FIREWORK",
            "BOAT",
            "MINECART",
            "MINECART_CHEST",
            "MINECART_FURNACE",
            "MINECART_TNT",
            "MINECART_HOPPER",
            "MINECART_MOB_SPAWNER",
            "CREEPER",
            "SKELETON",
            "SPIDER",
            "GIANT",
            "ZOMBIE",
            "SLIME",
            "GHAST",
            "PIG_ZOMBIE",
            "ENDERMAN",
            "CAVE_SPIDER",
            "SILVERFISH",
            "BLAZE",
            "MAGMA_CUBE",
            "ENDER_DRAGON",
            "WITHER",
            "BAT",
            "WITCH",
            "PIG",
            "SHEEP",
            "COW",
            "CHICKEN",
            "SQUID",
            "WOLF",
            "MUSHROOM_COW",
            "SNOWMAN",
            "OCELOT",
            "IRON_GOLEM",
            "HORSE",
            "VILLAGER",
            "ENDER_CRYSTAL",
            "SPLASH_POTION",
            "EGG",
            "FISHING_HOOK",
            "LIGHTNING",
            "WEATHER",
            "PLAYER",
            "COMPLEX_PART",
            "UNKNOWN"
          ],
          "min": false,
          "max": false
        }
      ],
      "type": "killentity",
      "name": "Kill entity"
    },
    {
      "fields": [
        {
          "key": "entityType",
          "name": "Entity",
          "type": "selection",
          "values": [
            "DROPPED_ITEM",
            "EXPERIENCE_ORB",
            "LEASH_HITCH",
            "PAINTING",
            "ARROW",
            "SNOWBALL",
            "FIREBALL",
            "SMALL_FIREBALL",
            "ENDER_PEARL",
            "ENDER_SIGNAL",
            "THROWN_EXP_BOTTLE",
            "ITEM_FRAME",
            "WITHER_SKULL",
            "PRIMED_TNT",
            "FALLING_BLOCK",
            "FIREWORK",
            "BOAT",
            "MINECART",
            "MINECART_CHEST",
            "MINECART_FURNACE",
            "MINECART_TNT",
            "MINECART_HOPPER",
            "MINECART_MOB_SPAWNER",
            "CREEPER",
            "SKELETON",
            "SPIDER",
            "GIANT",
            "ZOMBIE",
            "SLIME",
            "GHAST",
            "PIG_ZOMBIE",
            "ENDERMAN",
            "CAVE_SPIDER",
            "SILVERFISH",
            "BLAZE",
            "MAGMA_CUBE",
            "ENDER_DRAGON",
            "WITHER",
            "BAT",
            "WITCH",
            "PIG",
            "SHEEP",
            "COW",
            "CHICKEN",
            "SQUID",
            "WOLF",
            "MUSHROOM_COW",
            "SNOWMAN",
            "OCELOT",
            "IRON_GOLEM",
            "HORSE",
            "VILLAGER",
            "ENDER_CRYSTAL",
            "SPLASH_POTION",
            "EGG",
            "FISHING_HOOK",
            "LIGHTNING",
            "WEATHER",
            "PLAYER",
            "COMPLEX_PART",
            "UNKNOWN"
          ],
          "min": false,
          "max": false
        }
      ],
      "type": "riding",
      "name": "Riding entity"
    },
    {
      "fields": [
        {
          "key": "name",
          "name": "Team name",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "onteam",
      "name": "Player team"
    },
    {
      "fields": [
        {
          "key": "cuboid",
          "name": "cuboid",
          "type": "cuboid",
          "min": false,
          "max": false
        }
      ],
      "type": "cuboid",
      "name": "cuboid"
    },
    {
      "fields": [
        {
          "key": "cuboid",
          "name": "cuboid",
          "type": "cuboid",
          "min": false,
          "max": false
        },
        {
          "key": "time",
          "name": "Time inside hill(seconds)",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "koth",
      "name": "King of the hill"
    },
    {
      "fields": [
        {
          "key": "l",
          "name": "Block to interact",
          "type": "location",
          "min": false,
          "max": false
        }
      ],
      "type": "interact",
      "name": "Click block at location"
    },
    {
      "fields": [
        {
          "key": "startCuboid",
          "name": "start cuboid",
          "type": "cuboid",
          "min": false,
          "max": false
        },
        {
          "key": "endCuboid",
          "name": "end cuboid",
          "type": "cuboid",
          "min": false,
          "max": false
        },
        {
          "key": "timing",
          "name": "time to beat (seconds)",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "speedrun",
      "name": "Speed run"
    }
  ],
  "rewards": [
    {
      "fields": [
        {
          "key": "command",
          "name": "Command",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "comm",
      "name": "Execute console command"
    },
    {
      "fields": [
        {
          "key": "comm",
          "name": "Command",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "playercommand",
      "name": "Execute command as player"
    },
    {
      "fields": [
        {
          "key": "cmd",
          "name": "Command",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "env",
          "name": "Env. variables",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "workDir",
          "name": "Working directory",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "execshell",
      "name": "Execute shell command"
    },
    {
      "fields": [
        {
          "key": "l",
          "name": "Location",
          "type": "location",
          "min": false,
          "max": false
        }
      ],
      "type": "compass",
      "name": "set compass target"
    },
    {
      "fields": [
        {
          "key": "doFire",
          "name": "explosion causes fire",
          "type": "bool",
          "min": false,
          "max": false
        },
        {
          "key": "power",
          "name": "Explosion power",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "explode",
      "name": "EXPLOSIONS?"
    },
    {
      "fields": [
        {
          "key": "itemStr",
          "name": "Firework String (CraftBook item syntax)",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "firework",
      "name": "Launch firework"
    },
    {
      "fields": [
        {
          "key": "ticks",
          "name": "Ignite for (ticks)",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "ignite",
      "name": "Ignite the player"
    },
    {
      "fields": [
        {
          "key": "itemStr",
          "name": "Item String (CraftBook item syntax)",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "tryEnderChest",
          "name": "try placed in enderchest",
          "type": "bool",
          "min": false,
          "max": false
        },
        {
          "key": "tryDrop",
          "name": "drop item in world",
          "type": "bool",
          "min": false,
          "max": false
        }
      ],
      "type": "item",
      "name": "Item reward"
    },
    {
      "fields": [
        {
          "key": "real",
          "name": "Real Lightning?",
          "type": "bool",
          "min": false,
          "max": false
        }
      ],
      "type": "lightning",
      "name": "Strike lightning on player"
    },
    {
      "fields": [
        {
          "key": "sound",
          "name": "sound name",
          "type": "selection",
          "values": [
            "AMBIENCE_CAVE",
            "AMBIENCE_RAIN",
            "AMBIENCE_THUNDER",
            "ANVIL_BREAK",
            "ANVIL_LAND",
            "ANVIL_USE",
            "ARROW_HIT",
            "BREATH",
            "BURP",
            "CHEST_CLOSE",
            "CHEST_OPEN",
            "CLICK",
            "DOOR_CLOSE",
            "DOOR_OPEN",
            "DRINK",
            "EAT",
            "EXPLODE",
            "FALL_BIG",
            "FALL_SMALL",
            "FIRE",
            "FIRE_IGNITE",
            "FIZZ",
            "FUSE",
            "GLASS",
            "HURT",
            "HURT_FLESH",
            "ITEM_BREAK",
            "ITEM_PICKUP",
            "LAVA",
            "LAVA_POP",
            "LEVEL_UP",
            "MINECART_BASE",
            "MINECART_INSIDE",
            "NOTE_BASS",
            "NOTE_PIANO",
            "NOTE_BASS_DRUM",
            "NOTE_STICKS",
            "NOTE_BASS_GUITAR",
            "NOTE_SNARE_DRUM",
            "NOTE_PLING",
            "ORB_PICKUP",
            "PISTON_EXTEND",
            "PISTON_RETRACT",
            "PORTAL",
            "PORTAL_TRAVEL",
            "PORTAL_TRIGGER",
            "SHOOT_ARROW",
            "SPLASH",
            "SPLASH2",
            "STEP_GRASS",
            "STEP_GRAVEL",
            "STEP_LADDER",
            "STEP_SAND",
            "STEP_SNOW",
            "STEP_STONE",
            "STEP_WOOD",
            "STEP_WOOL",
            "SWIM",
            "WATER",
            "WOOD_CLICK",
            "BAT_DEATH",
            "BAT_HURT",
            "BAT_IDLE",
            "BAT_LOOP",
            "BAT_TAKEOFF",
            "BLAZE_BREATH",
            "BLAZE_DEATH",
            "BLAZE_HIT",
            "CAT_HISS",
            "CAT_HIT",
            "CAT_MEOW",
            "CAT_PURR",
            "CAT_PURREOW",
            "CHICKEN_IDLE",
            "CHICKEN_HURT",
            "CHICKEN_EGG_POP",
            "CHICKEN_WALK",
            "COW_IDLE",
            "COW_HURT",
            "COW_WALK",
            "CREEPER_HISS",
            "CREEPER_DEATH",
            "ENDERDRAGON_DEATH",
            "ENDERDRAGON_GROWL",
            "ENDERDRAGON_HIT",
            "ENDERDRAGON_WINGS",
            "ENDERMAN_DEATH",
            "ENDERMAN_HIT",
            "ENDERMAN_IDLE",
            "ENDERMAN_TELEPORT",
            "ENDERMAN_SCREAM",
            "ENDERMAN_STARE",
            "GHAST_SCREAM",
            "GHAST_SCREAM2",
            "GHAST_CHARGE",
            "GHAST_DEATH",
            "GHAST_FIREBALL",
            "GHAST_MOAN",
            "IRONGOLEM_DEATH",
            "IRONGOLEM_HIT",
            "IRONGOLEM_THROW",
            "IRONGOLEM_WALK",
            "MAGMACUBE_WALK",
            "MAGMACUBE_WALK2",
            "MAGMACUBE_JUMP",
            "PIG_IDLE",
            "PIG_DEATH",
            "PIG_WALK",
            "SHEEP_IDLE",
            "SHEEP_SHEAR",
            "SHEEP_WALK",
            "SILVERFISH_HIT",
            "SILVERFISH_KILL",
            "SILVERFISH_IDLE",
            "SILVERFISH_WALK",
            "SKELETON_IDLE",
            "SKELETON_DEATH",
            "SKELETON_HURT",
            "SKELETON_WALK",
            "SLIME_ATTACK",
            "SLIME_WALK",
            "SLIME_WALK2",
            "SPIDER_IDLE",
            "SPIDER_DEATH",
            "SPIDER_WALK",
            "WITHER_DEATH",
            "WITHER_HURT",
            "WITHER_IDLE",
            "WITHER_SHOOT",
            "WITHER_SPAWN",
            "WOLF_BARK",
            "WOLF_DEATH",
            "WOLF_GROWL",
            "WOLF_HOWL",
            "WOLF_HURT",
            "WOLF_PANT",
            "WOLF_SHAKE",
            "WOLF_WALK",
            "WOLF_WHINE",
            "ZOMBIE_METAL",
            "ZOMBIE_WOOD",
            "ZOMBIE_WOODBREAK",
            "ZOMBIE_IDLE",
            "ZOMBIE_DEATH",
            "ZOMBIE_HURT",
            "ZOMBIE_INFECT",
            "ZOMBIE_UNFECT",
            "ZOMBIE_REMEDY",
            "ZOMBIE_PIG_IDLE",
            "ZOMBIE_PIG_ANGRY",
            "ZOMBIE_PIG_DEATH",
            "ZOMBIE_PIG_HURT",
            "DIG_WOOL",
            "DIG_GRASS",
            "DIG_GRAVEL",
            "DIG_SAND",
            "DIG_SNOW",
            "DIG_STONE",
            "DIG_WOOD",
            "FIREWORK_BLAST",
            "FIREWORK_BLAST2",
            "FIREWORK_LARGE_BLAST",
            "FIREWORK_LARGE_BLAST2",
            "FIREWORK_TWINKLE",
            "FIREWORK_TWINKLE2",
            "FIREWORK_LAUNCH",
            "SUCCESSFUL_HIT",
            "HORSE_ANGRY",
            "HORSE_ARMOR",
            "HORSE_BREATHE",
            "HORSE_GALLOP",
            "HORSE_HIT",
            "HORSE_IDLE",
            "HORSE_JUMP",
            "HORSE_LAND",
            "HORSE_SADDLE",
            "HORSE_SOFT",
            "HORSE_WOOD",
            "DONKEY_ANGRY",
            "DONKEY_DEATH",
            "DONKEY_HIT",
            "DONKEY_IDLE",
            "HORSE_SKELETON_DEATH",
            "HORSE_SKELETON_IDLE",
            "HORSE_ZOMBIE_DEATH",
            "HORSE_ZOMBIE_HIT",
            "HORSE_ZOMBIE_IDLE",
            "VILLAGER_DEATH",
            "VILLAGER_HAGGLE",
            "VILLAGER_HIT",
            "VILLAGER_IDLE",
            "VILLAGER_NO",
            "VILLAGER_YES"
          ],
          "min": false,
          "max": false
        },
        {
          "key": "volume",
          "name": "volume (decimal, 1.0 is normal)",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "pitch",
          "name": "pitch (decimal, 1.0 is normal)",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "playsnd",
      "name": "play sound"
    },
    {
      "fields": [
        {
          "key": "potionType",
          "name": "Potion Type",
          "type": "selection",
          "values": [
            "WATER",
            "REGEN",
            "SPEED",
            "FIRE_RESISTANCE",
            "POISON",
            "INSTANT_HEAL",
            "NIGHT_VISION",
            "WEAKNESS",
            "STRENGTH",
            "SLOWNESS",
            "INSTANT_DAMAGE",
            "INVISIBILITY"
          ],
          "min": false,
          "max": false
        },
        {
          "key": "amplifier",
          "name": "Amplifier",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "duration",
          "name": "Duration",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "ambient",
          "name": "Ambient",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "potion",
      "name": "Apply potion effect"
    },
    {
      "fields": [
        {
          "key": "health",
          "name": "Health to add",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "sethealth",
      "name": "Heal player"
    },
    {
      "fields": [
        {
          "key": "to",
          "name": "Teleport to",
          "type": "location",
          "min": false,
          "max": false
        }
      ],
      "type": "teleport",
      "name": "Teleport to location"
    },
    {
      "fields": [
        {
          "key": "text",
          "name": "message",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "text",
      "name": "Display text"
    },
    {
      "fields": [
        {
          "key": "x",
          "name": "X",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "y",
          "name": "Y",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "z",
          "name": "Z",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "applyforce",
      "name": "Apply force to player"
    },
    {
      "fields": [
        {
          "key": "xp",
          "name": "Amount to give",
          "type": "number",
          "min": 0,
          "max": false
        },
        {
          "key": "xpLevels",
          "name": "Amount of levels to add",
          "type": "number",
          "min": 0,
          "max": false
        }
      ],
      "type": "xp",
      "name": "Give xp"
    }
  ],
  "triggerHelp": {
    "oncommand": {
      "name": "On command",
      "description": "Triggers when a user attempts to execute a command",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Include commands that have been cancelled by preprocess event",
          "description": "Certain commands use the preprocess event instead of Bukkit\u0027s command api, check this to allow those commands"
        },
        {
          "name": "Treat prefix as regex expression",
          "description": "Use a regex expression instead of checking if command starts with"
        },
        {
          "name": "Command prefix to detect",
          "description": "command prefix or regex expression to check"
        }
      ]
    },
    "speedrun": {
      "name": "Speed run",
      "description": "Triggers if player gets between two points within the given time",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "start cuboid",
          "description": "Area player enters to start cuboid. NOTE: Entry into this area starts the timer, so for race areas with gates, place the cuboid immediately after the starting gate"
        },
        {
          "name": "end cuboid",
          "description": "Area player must enter to finish the race"
        },
        {
          "name": "time to beat (seconds)",
          "description": "Time player must beat in order to trigger this achievement"
        }
      ]
    },
    "koth": {
      "name": "King of the hill",
      "description": "Player stays in an area for a certain amount of time. Timer resets when a player walks out the area",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "cuboid",
          "description": "Area player must be inside of"
        },
        {
          "name": "Time inside hill(seconds)",
          "description": "Time inside area"
        }
      ]
    },
    "deathentity": {
      "name": "Killed by",
      "description": "fires when killed by a specific entity",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Entity",
          "description": "Entity type to kill player"
        }
      ]
    },
    "biome": {
      "name": "Biome check",
      "description": "Is player in a biome",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Biome",
          "description": "Biome to check for"
        }
      ]
    },
    "killentity": {
      "name": "Kill entity",
      "description": "fires when killing a specific entity",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Entity",
          "description": "Entity type to kill"
        }
      ]
    },
    "invcheck": {
      "name": "Inventory check",
      "description": "Checks player has an item in their inventory, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Item String (CraftBook item syntax)",
          "description": "Item stack to look for"
        },
        {
          "name": "Ignore durability values",
          "description": "Whether to ignore the durability (damage) of the item"
        },
        {
          "name": "Ignore metadata (Enchantments, Lore)",
          "description": "Ignore metadata such as enchantments, lore etc"
        }
      ]
    },
    "wearitem": {
      "name": "wearingitem",
      "description": "Checks player is wearing an item, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Item String (CraftBook item syntax)",
          "description": "Item stack to look for in an armor slot"
        },
        {
          "name": "Ignore durability values",
          "description": "Whether to ignore the durability (damage) of the item"
        },
        {
          "name": "Ignore metadata (Enchantments, Lore)",
          "description": "Ignore metadata such as enchantments, lore etc"
        }
      ]
    },
    "ach": {
      "name": "has achievement",
      "description": "Checks if the player has an achievement",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "achievement slug",
          "description": "The achievement id (slug) to check for"
        }
      ]
    },
    "metaand": {
      "name": "Meta AND trigger",
      "description": "Meta trigger, will fire if all triggers under it return true",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "triggers",
          "description": "list of triggers"
        }
      ]
    },
    "storm": {
      "name": "Is it stormy?",
      "description": "Triggers if world a player in is stormy",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Storm currently?",
          "description": "Set true for should have a storm, false for should be clear"
        }
      ]
    },
    "perm": {
      "name": "has permission node",
      "description": "Does the player have a permission node",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "permission node",
          "description": "Permission node to check, does not have to one declared by a plugin"
        }
      ]
    },
    "cuboid": {
      "name": "cuboid",
      "description": "Is player inside an area",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "cuboid",
          "description": "Area to check"
        }
      ]
    },
    "noach": {
      "name": "Does not have achievement",
      "description": "Triggers only if the player does not have this achievement",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "achievement slug",
          "description": "achievement slug to check for"
        }
      ]
    },
    "time": {
      "name": "Between two times",
      "description": "Checks if the worlds time is between two values",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "After this time(ticks)",
          "description": "World time must have progressed past"
        },
        {
          "name": "Before this time(ticks)",
          "description": "World time must be before"
        }
      ]
    },
    "riding": {
      "name": "Riding entity",
      "description": "Is player riding an entity",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Entity",
          "description": "Entity type to check"
        }
      ]
    },
    "onteam": {
      "name": "Player team",
      "description": "Is player on a team",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Team name",
          "description": "Team name to check for (Case Sensitive)"
        }
      ]
    },
    "interact": {
      "name": "Click block at location",
      "description": "Player clicks a block",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Block to interact",
          "description": "Block location to be clicked"
        }
      ]
    },
    "locked": {
      "name": "Lock this achievement",
      "description": "Locks an achievement ensuring it cannot be triggered",
      "dependencies": [
        "none"
      ],
      "fields": []
    },
    "achcount": {
      "name": "Achievement count",
      "description": "Counts number of achievements with a specific id prefix",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "achievement count threshold",
          "description": "Amount of achievements player must exceed, (Triggers if count \u003e this value)"
        },
        {
          "name": "achievement slug prefix",
          "description": "The prefix of the ids of achievements to count"
        }
      ]
    },
    "metaor": {
      "name": "Meta OR trigger",
      "description": "Meta trigger, will fire if any trigger under it returns true",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "triggers",
          "description": "list of triggers"
        }
      ]
    }
  },
  "rewardHelp": {
    "comm": {
      "name": "Execute console command",
      "description": "Execute a command as the console",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Command",
          "description": "Command to execute, \u003cPLAYER\u003e is replaced with the player\u0027s name"
        }
      ]
    },
    "sethealth": {
      "name": "Heal player",
      "description": "Sets the players health to certain value",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Health to add",
          "description": ""
        }
      ]
    },
    "text": {
      "name": "Display text",
      "description": "Display text to a player",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "message",
          "description": "Text to display to a user"
        }
      ]
    },
    "firework": {
      "name": "Launch firework",
      "description": "Launches a firework, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Firework String (CraftBook item syntax)",
          "description": ""
        }
      ]
    },
    "xp": {
      "name": "Give xp",
      "description": "Gives a player some xp",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Amount to give",
          "description": "Raw amount of XP to give a player (for example, a blaze drops 10 xp"
        },
        {
          "name": "Amount of levels to add",
          "description": "XP levels to add to a player (note: negative values will take away, and cap at 0)"
        }
      ]
    },
    "compass": {
      "name": "set compass target",
      "description": "Sets the player\u0027s compass target to new location",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Location",
          "description": ""
        }
      ]
    },
    "execshell": {
      "name": "Execute shell command",
      "description": "Executes a shell command, this reward IS DANGEROUS, AND MUST BE ENABLED VIA CONFIG (DEFAULTS TO OFF)",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Command",
          "description": "Command to execute, \u003cPLAYER\u003e token replaced with player name"
        },
        {
          "name": "Env. variables",
          "description": "Environment variables, using var\u003dvalue\u0026var2\u003dvalue2 format, use \\\u0026 and \\\\ for \u0026 and \\ respectively"
        },
        {
          "name": "Working directory",
          "description": "Working directory, will default to however bukkit was started."
        }
      ]
    },
    "explode": {
      "name": "EXPLOSIONS?",
      "description": "BE AS AWESOME AS MR TORGUE. EXPLOSIONS!",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "explosion causes fire",
          "description": "ADD PYRO TECHNICS TO THIS BADASS EXPLOSION"
        },
        {
          "name": "Explosion power",
          "description": "HOW BADASS IS THIS EXPLOSION? (4.0 IS TNT)"
        }
      ]
    },
    "lightning": {
      "name": "Strike lightning on player",
      "description": "Throws a lightning bolt at the player",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Real Lightning?",
          "description": "If false, uses bukkit\u0027s fake lightning that does not injure a player"
        }
      ]
    },
    "applyforce": {
      "name": "Apply force to player",
      "description": "Apply a force to a player",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "X",
          "description": "force along x  axis to apply"
        },
        {
          "name": "Y",
          "description": "force along y axis to apply"
        },
        {
          "name": "Z",
          "description": "force along z axis to apply"
        }
      ]
    },
    "ignite": {
      "name": "Ignite the player",
      "description": "Sets a player on fire",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Ignite for (ticks)",
          "description": ""
        }
      ]
    },
    "item": {
      "name": "Item reward",
      "description": "Gives a player an item, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Item String (CraftBook item syntax)",
          "description": ""
        },
        {
          "name": "try placed in enderchest",
          "description": "Attempts to place the item in the users enderchest if ticked"
        },
        {
          "name": "drop item in world",
          "description": "Attempts to drop the item in world if inventory/s are full"
        }
      ]
    },
    "potion": {
      "name": "Apply potion effect",
      "description": "Applies a potion effect to a player",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Potion Type",
          "description": ""
        },
        {
          "name": "Amplifier",
          "description": ""
        },
        {
          "name": "Duration",
          "description": ""
        },
        {
          "name": "Ambient",
          "description": ""
        }
      ]
    },
    "playsnd": {
      "name": "play sound",
      "description": "plays a sound",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "sound name",
          "description": "Name of the sound to play (same as playsound command)"
        },
        {
          "name": "volume (decimal, 1.0 is normal)",
          "description": "Volume to play at"
        },
        {
          "name": "pitch (decimal, 1.0 is normal)",
          "description": "pitch to play at"
        }
      ]
    },
    "teleport": {
      "name": "Teleport to location",
      "description": "Teleports a player to a particular location",
      "dependencies": [
        "none"
      ],
      "fields": [
        {
          "name": "Teleport to",
          "description": ""
        }
      ]
    }
  }
});});