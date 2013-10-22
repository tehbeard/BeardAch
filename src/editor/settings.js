$(function(){initConfig({
  "triggers": [
    {
      "fields": [
        {
          "key": "ach",
          "name": "achievement slug",
          "type": "text"
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
          "type": "text"
        },
        {
          "key": "prefix",
          "name": "achievement slug prefix",
          "type": "text"
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
          "type": "text"
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
          "type": "bool"
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
          "type": "text"
        },
        {
          "key": "before",
          "name": "Before this time(ticks)",
          "type": "text"
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
      "fields": [],
      "type": "metaand",
      "name": "Meta AND trigger"
    },
    {
      "fields": [],
      "type": "metaor",
      "name": "Meta OR trigger"
    },
    {
      "fields": [
        {
          "key": "biome",
          "name": "Biome",
          "type": "text"
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
          "type": "bool"
        },
        {
          "key": "isRegex",
          "name": "Treat prefix as regex expression",
          "type": "bool"
        },
        {
          "key": "commandText",
          "name": "Command prefix to detect",
          "type": "text"
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
          "type": "text"
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
          "type": "text"
        },
        {
          "key": "ignoreDurability",
          "name": "Ignore durability values",
          "type": "bool"
        },
        {
          "key": "ignoreMetadata",
          "name": "Ignore metadata (Enchantments, Lore)",
          "type": "bool"
        }
      ],
      "type": "wearitem",
      "name": "wearingitem"
    },
    {
      "fields": [
        {
          "key": "itemStr",
          "name": "Item String (CraftBook item syntax)",
          "type": "text"
        },
        {
          "key": "ignoreDurability",
          "name": "Ignore durability values",
          "type": "bool"
        },
        {
          "key": "ignoreMetadata",
          "name": "Ignore metadata (Enchantments, Lore)",
          "type": "bool"
        }
      ],
      "type": "invcheck",
      "name": "Inventory check"
    },
    {
      "fields": [
        {
          "key": "type",
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
          ]
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
          "type": "text"
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
          "type": "cuboid"
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
          "type": "cuboid"
        },
        {
          "key": "time",
          "name": "Time inside hill(seconds)",
          "type": "text"
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
          "type": "location"
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
          "type": "cuboid"
        },
        {
          "key": "endCuboid",
          "name": "end cuboid",
          "type": "cuboid"
        },
        {
          "key": "timing",
          "name": "time to beat (seconds)",
          "type": "text"
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
          "type": "text"
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
          "type": "text"
        }
      ],
      "type": "playercommand",
      "name": "Execute command as player"
    },
    {
      "fields": [
        {
          "key": "subgroup",
          "name": "Subgroup to add",
          "type": "text"
        }
      ],
      "type": "subgroup",
      "name": "(DroxPerms) add subgroup"
    },
    {
      "fields": [
        {
          "key": "track",
          "name": "Track to promote along",
          "type": "text"
        }
      ],
      "type": "promote",
      "name": "(DroxPerms) Promote along track"
    },
    {
      "fields": [
        {
          "key": "l",
          "name": "Location",
          "type": "location"
        }
      ],
      "type": "compass",
      "name": "set compass target"
    },
    {
      "fields": [
        {
          "key": "doFire",
          "name": "fire explosion",
          "type": "bool"
        },
        {
          "key": "power",
          "name": "fire explosion",
          "type": "text"
        }
      ],
      "type": "explode",
      "name": "Cause an explosion"
    },
    {
      "fields": [
        {
          "key": "itemStr",
          "name": "Firework String (CraftBook item syntax)",
          "type": "text"
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
          "type": "text"
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
          "type": "text"
        },
        {
          "key": "tryEnderChest",
          "name": "try placed in enderchest",
          "type": "bool"
        },
        {
          "key": "tryDrop",
          "name": "drop item in world",
          "type": "bool"
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
          "type": "bool"
        }
      ],
      "type": "lightning",
      "name": "Strike lightning on player"
    },
    {
      "fields": [
        {
          "key": "soundName",
          "name": "sound name",
          "type": "text"
        },
        {
          "key": "volume",
          "name": "volume (decimal, 1.0 is normal)",
          "type": "text"
        },
        {
          "key": "pitch",
          "name": "pitch (decimal, 1.0 is normal)",
          "type": "text"
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
          "type": "text"
        },
        {
          "key": "amplifier",
          "name": "Amplifier",
          "type": "text"
        },
        {
          "key": "duration",
          "name": "Duration",
          "type": "text"
        },
        {
          "key": "ambient",
          "name": "Ambient",
          "type": "text"
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
          "type": "text"
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
          "type": "location"
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
          "type": "text"
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
          "type": "text"
        },
        {
          "key": "y",
          "name": "Y",
          "type": "text"
        },
        {
          "key": "z",
          "name": "Z",
          "type": "text"
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
          "type": "text"
        }
      ],
      "type": "xp",
      "name": "Give xp"
    }
  ]
});});