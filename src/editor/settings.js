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
      "fields": [
        {
          "key": "domain",
          "name": "Domain",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "world",
          "name": "World",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "cat",
          "name": "Category",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "stat",
          "name": "Statistic",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "lowerThreshold",
          "name": "Lower bound threshold",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "upperThreshold",
          "name": "Upper bound threshold",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "statwithin",
      "name": "Stat within boundaries"
    },
    {
      "fields": [
        {
          "key": "domain",
          "name": "Domain",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "world",
          "name": "World",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "cat",
          "name": "Category",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "stat",
          "name": "Statistic",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "threshold",
          "name": "Lower threshold",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "stat",
      "name": "stat above threshold"
    },
    {
      "fields": [
        {
          "key": "isop",
          "name": "is OP",
          "type": "bool",
          "min": false,
          "max": false
        }
      ],
      "type": "isop",
      "name": "is player an OP"
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
            "JUNGLE_HILLS",
            "JUNGLE_EDGE",
            "DEEP_OCEAN",
            "STONE_BEACH",
            "COLD_BEACH",
            "BIRCH_FOREST",
            "BIRCH_FOREST_HILLS",
            "ROOFED_FOREST",
            "COLD_TAIGA",
            "COLD_TAIGA_HILLS",
            "MEGA_TAIGA",
            "MEGA_TAIGA_HILLS",
            "EXTREME_HILLS_PLUS",
            "SAVANNA",
            "SAVANNA_PLATEAU",
            "MESA",
            "MESA_PLATEAU_FOREST",
            "MESA_PLATEAU",
            "SUNFLOWER_PLAINS",
            "DESERT_MOUNTAINS",
            "FLOWER_FOREST",
            "TAIGA_MOUNTAINS",
            "SWAMPLAND_MOUNTAINS",
            "ICE_PLAINS_SPIKES",
            "JUNGLE_MOUNTAINS",
            "JUNGLE_EDGE_MOUNTAINS",
            "COLD_TAIGA_MOUNTAINS",
            "SAVANNA_MOUNTAINS",
            "SAVANNA_PLATEAU_MOUNTAINS",
            "MESA_BRYCE",
            "MESA_PLATEAU_FOREST_MOUNTAINS",
            "MESA_PLATEAU_MOUNTAINS",
            "BIRCH_FOREST_MOUNTAINS",
            "BIRCH_FOREST_HILLS_MOUNTAINS",
            "ROOFED_FOREST_MOUNTAINS",
            "MEGA_SPRUCE_TAIGA",
            "EXTREME_HILLS_MOUNTAINS",
            "EXTREME_HILLS_PLUS_MOUNTAINS",
            "MEGA_SPRUCE_TAIGA_HILLS"
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
          "key": "amount",
          "name": "Lower threshold",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "bankbalance",
      "name": "(Vault)Check bank balance"
    },
    {
      "fields": [
        {
          "key": "cause",
          "name": "Damage type",
          "type": "selection",
          "values": [
            "CONTACT",
            "ENTITY_ATTACK",
            "PROJECTILE",
            "SUFFOCATION",
            "FALL",
            "FIRE",
            "FIRE_TICK",
            "MELTING",
            "LAVA",
            "DROWNING",
            "BLOCK_EXPLOSION",
            "ENTITY_EXPLOSION",
            "VOID",
            "LIGHTNING",
            "SUICIDE",
            "STARVATION",
            "POISON",
            "MAGIC",
            "WITHER",
            "FALLING_BLOCK",
            "THORNS",
            "CUSTOM"
          ],
          "min": false,
          "max": false
        }
      ],
      "type": "deathtype",
      "name": "Killed by"
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
            "MINECART_COMMAND",
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
            "MINECART_COMMAND",
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
            "MINECART_COMMAND",
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
          "key": "itemStr",
          "name": "Fished item String (CraftBook item syntax)",
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
      "type": "fisheditem",
      "name": "Fished item"
    },
    {
      "fields": [],
      "type": "locked",
      "name": "Lock this achievement"
    },
    {
      "fields": [
        {
          "key": "region",
          "name": "Region name",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "world",
          "name": "World name",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "wgregion",
      "name": "(WorldGuard) Inside region"
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
    }
  ],
  "rewards": [
    {
      "fields": [
        {
          "key": "subgroup",
          "name": "Subgroup to add",
          "type": "text",
          "min": false,
          "max": false
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
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "promote",
      "name": "(DroxPerms) Promote along track"
    },
    {
      "fields": [
        {
          "key": "group",
          "name": "Group to add",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "vaultaddgroup",
      "name": "(Vault) add/set group"
    },
    {
      "fields": [
        {
          "key": "amount",
          "name": "Amount to give (can be a decimal",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "money",
      "name": "(vault) Give money"
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
            "ZOMBIE_WALK",
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
            "HORSE_DEATH",
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
            "HORSE_SKELETON_HIT",
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
            "WATER_BREATHING",
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
          "key": "name",
          "name": "Counter name",
          "type": "text",
          "min": false,
          "max": false
        },
        {
          "key": "count",
          "name": "Amount to increment",
          "type": "text",
          "min": false,
          "max": false
        }
      ],
      "type": "counter",
      "name": "Increment counter"
    }
  ],
  "triggerHelp": {
    "speedrun": {
      "name": "Speed run",
      "description": "Triggers if player gets between two points within the given time",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
      "categories": [],
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
    "statwithin": {
      "name": "Stat within boundaries",
      "description": "Triggers if statistic is between two values",
      "dependencies": [
        "BeardStat"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Domain",
          "description": "domain to check, for the regular stats use default as the value"
        },
        {
          "name": "World",
          "description": "World to check stats for (* matches all worlds)"
        },
        {
          "name": "Category",
          "description": "statistic category to check"
        },
        {
          "name": "Statistic",
          "description": "Statistic name to check"
        },
        {
          "name": "Lower bound threshold",
          "description": "Threshold statistic must equal or greater than"
        },
        {
          "name": "Upper bound threshold",
          "description": "Threshold statistic must equal or be less than"
        }
      ]
    },
    "wgregion": {
      "name": "(WorldGuard) Inside region",
      "description": "Player inside a worldGuard region",
      "dependencies": [
        "WorldGuard"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Region name",
          "description": "name of region"
        },
        {
          "name": "World name",
          "description": "name of world"
        }
      ]
    },
    "perm": {
      "name": "has permission node",
      "description": "Does the player have a permission node",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "permission node",
          "description": "Permission node to check, does not have to one declared by a plugin"
        }
      ]
    },
    "storm": {
      "name": "Is it stormy?",
      "description": "Triggers if world a player in is stormy",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Storm currently?",
          "description": "Set true for should have a storm, false for should be clear"
        }
      ]
    },
    "isop": {
      "name": "is player an OP",
      "description": "Is the player a vanilla OP",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "is OP",
          "description": "Is the player a vanilla Operator"
        }
      ]
    },
    "noach": {
      "name": "Does not have achievement",
      "description": "Triggers only if the player does not have this achievement",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
      "categories": [],
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
      "categories": [],
      "fields": [
        {
          "name": "Entity",
          "description": "Entity type to check"
        }
      ]
    },
    "metaor": {
      "name": "Meta OR trigger",
      "description": "Meta trigger, will fire if any trigger under it returns true",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "triggers",
          "description": "list of triggers"
        }
      ]
    },
    "deathtype": {
      "name": "Killed by",
      "description": "fires when killed by specific damage type",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Damage type",
          "description": "Damage type that killed the player"
        }
      ]
    },
    "oncommand": {
      "name": "On command",
      "description": "Triggers when a user attempts to execute a command",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
    "bankbalance": {
      "name": "(Vault)Check bank balance",
      "description": "Checks the players bank balance",
      "dependencies": [
        "Vault"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Lower threshold",
          "description": "Balance must be atleast this value to trigger, supports decimal values"
        }
      ]
    },
    "biome": {
      "name": "Biome check",
      "description": "Is player in a biome",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Biome",
          "description": "Biome to check for"
        }
      ]
    },
    "deathentity": {
      "name": "Killed by",
      "description": "fires when killed by a specific entity",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Entity",
          "description": "Entity type to kill player"
        }
      ]
    },
    "invcheck": {
      "name": "Inventory check",
      "description": "Checks player has an item in their inventory, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
    "killentity": {
      "name": "Kill entity",
      "description": "fires when killing a specific entity",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Entity",
          "description": "Entity type to kill"
        }
      ]
    },
    "wearitem": {
      "name": "wearingitem",
      "description": "Checks player is wearing an item, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
      "categories": [],
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
      "categories": [],
      "fields": [
        {
          "name": "triggers",
          "description": "list of triggers"
        }
      ]
    },
    "fisheditem": {
      "name": "Fished item",
      "description": "Triggers when a player fishes an item, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e for the fish",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Fished item String (CraftBook item syntax)",
          "description": "Item stack fished"
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
    "stat": {
      "name": "stat above threshold",
      "description": "Value of statistic is atleast provided value. This trigger supports regex values",
      "dependencies": [
        "BeardStat"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Domain",
          "description": "domain to check, for the regular stats use default as the value"
        },
        {
          "name": "World",
          "description": "World to check stats for (* matches all worlds)"
        },
        {
          "name": "Category",
          "description": "statistic category to check"
        },
        {
          "name": "Statistic",
          "description": "Statistic name to check"
        },
        {
          "name": "Lower threshold",
          "description": "Threshold statistic must equal or beat"
        }
      ]
    },
    "cuboid": {
      "name": "cuboid",
      "description": "Is player inside an area",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "cuboid",
          "description": "Area to check"
        }
      ]
    },
    "onteam": {
      "name": "Player team",
      "description": "Is player on a team",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
      "categories": [],
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
      "categories": [],
      "fields": []
    },
    "achcount": {
      "name": "Achievement count",
      "description": "Counts number of achievements with a specific id prefix",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
    }
  },
  "rewardHelp": {
    "vaultaddgroup": {
      "name": "(Vault) add/set group",
      "description": "Sets the group of a player using vault, \\n Exact behaviour will depend on the permissions plugin, consult your permissions plugin as needed. \\n Should expected behaviour not occur, consider using the execute console command reward instead.",
      "dependencies": [
        "Vault"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Group to add",
          "description": ""
        }
      ]
    },
    "comm": {
      "name": "Execute console command",
      "description": "Execute a command as the console",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
      "categories": [],
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
      "categories": [],
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
      "categories": [],
      "fields": [
        {
          "name": "Firework String (CraftBook item syntax)",
          "description": ""
        }
      ]
    },
    "counter": {
      "name": "Increment counter",
      "description": "Increment counter value. Counters are stored in BeardStat and accessible from the stats triggers",
      "dependencies": [
        "BeardStat"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Counter name",
          "description": "Name of the counter"
        },
        {
          "name": "Amount to increment",
          "description": "Amount to increment counter by"
        }
      ]
    },
    "xp": {
      "name": "Give xp",
      "description": "Gives a player some xp",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
      "categories": [],
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
      "categories": [],
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
      "categories": [],
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
      "categories": [],
      "fields": [
        {
          "name": "Real Lightning?",
          "description": "If false, uses bukkit\u0027s fake lightning that does not injure a player"
        }
      ]
    },
    "promote": {
      "name": "(DroxPerms) Promote along track",
      "description": "Track promotion player",
      "dependencies": [
        "DroxPerms"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Track to promote along",
          "description": ""
        }
      ]
    },
    "applyforce": {
      "name": "Apply force to player",
      "description": "Apply a force to a player",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
    "item": {
      "name": "Item reward",
      "description": "Gives a player an item, using \u003ca href\u003d\u0027http://wiki.sk89q.com/wiki/CraftBook/Item_Syntax\u0027\u003eCraftBook item syntax\u003c/a\u003e",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
    "ignite": {
      "name": "Ignite the player",
      "description": "Sets a player on fire",
      "dependencies": [
        "none"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Ignite for (ticks)",
          "description": ""
        }
      ]
    },
    "potion": {
      "name": "Apply potion effect",
      "description": "Applies a potion effect to a player",
      "dependencies": [
        "none"
      ],
      "categories": [],
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
    "money": {
      "name": "(vault) Give money",
      "description": "Gives a player some money",
      "dependencies": [
        "Vault"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Amount to give (can be a decimal",
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
      "categories": [],
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
      "categories": [],
      "fields": [
        {
          "name": "Teleport to",
          "description": ""
        }
      ]
    },
    "subgroup": {
      "name": "(DroxPerms) add subgroup",
      "description": "Add a subgroup to a player",
      "dependencies": [
        "DroxPerms"
      ],
      "categories": [],
      "fields": [
        {
          "name": "Subgroup to add",
          "description": ""
        }
      ]
    }
  }
});});