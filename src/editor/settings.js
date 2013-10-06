$(function(){
  initConfig({
  "triggers":[
  {
    "fields":[
      {"key":"triggers","name":"Triggers","type":"trigger"}
    ],
    "type":"metaor",
    "name":"[Meta] OR"
  },
  {
    "fields":[
      {"key":"triggers","name":"Triggers","type":"trigger"}
    ],
    "type":"metaand",
    "name":"[Meta] AND"
  },

  {
    "fields":[
      {"key":"ach","name":"achievement slug","type":"text"}
    ],
    "type":"ach",
    "name":"has achievement"
  },

  {
    "fields":[{"key":"threshold",
    "name":"achievement count threshold",
    "type":"text"},
    {
      "key":"prefix",
      "name":"achievement slug prefix",
      "type":"text"
    }
    ],

      "type":"achcount",
      "name":"Achievement count"
    },
    {
      "fields":[
      {
        "key":"includeIgnored",
        "name":"Include commands that have been cancelled by preprocess event",
        "type":"bool"
      },
      {
        "key":"isRegex",
        "name":"Treat prefix as regex expression",
        "type":"bool"
      },
      {
        "key":"commandText",
        "name":"Command prefix to detect",
        "type":"text"
      }
      ],
      "type":"oncommand",
      "name":"On command"
    },
    {
      "fields":[
      {
        "key":"cuboid",
        "name":"cuboid",
        "type":"cuboid"
      }
      ],
      "type":"cuboid",
      "name":"cuboid"
    },
    {
      "fields":[
      {
        "key":"cuboid",
        "name":"cuboid",
        "type":"cuboid"
      },
      {
        "key":"time",
        "name":"Time inside hill(seconds)",
        "type":"text"
      }
      ],
      "type":"koth",
      "name":"King of the hill"
    },
    {
      "fields":[
      {
        "key":"amount",
        "name":"Lower threshold",
        "type":"text"
      }
      ],
      "type":"bankbalance",
      "name":"(Vault)Check bank balance"
    },
    {
      "fields":[
      {
        "key":"l",
        "name":"Block to interact",
        "type":"location"
      }
      ],
      "type":"interact",
      "name":"Click block at location"
    },
    {
      "fields":[

      ],
      "type":"locked",
      "name":"Lock this achievement"
    },
    {
      "fields":[
      {
        "key":"ach",
        "name":"achievement slug",
        "type":"text"
      }
      ],
      "type":"noach",
      "name":"Does not have achievement"
    },
    {
      "fields":[
      {
        "key":"perm",
        "name":"permission node",
        "type":"text"
      }
      ],
      "type":"perm",
      "name":"has permission node"
    },
    {
      "fields":[
      {
        "key":"name",
        "name":"Team name",
        "type":"text"
      }
      ],
      "type":"onteam",
      "name":"Player team"
    },
    {
      "fields":[
      {
        "key":"startCuboid",
        "name":"start cuboid",
        "type":"cuboid"
      },
      {
        "key":"endCuboid",
        "name":"end cuboid",
        "type":"cuboid"
      },
      {
        "key":"timing",
        "name":"time to beat (seconds)",
        "type":"text"
      }
      ],
      "type":"speedrun",
      "name":"Speed run"
    },
    {
      "fields":[
      {
        "key":"domain",
        "name":"Domain",
        "type":"text"
      },
      {
        "key":"world",
        "name":"World",
        "type":"text"
      },
      {
        "key":"cat",
        "name":"Category",
        "type":"text"
      },
      {
        "key":"stat",
        "name":"Statistic",
        "type":"text"
      },
      {
        "key":"threshold",
        "name":"Lower threshold",
        "type":"text"
      }
      ],
      "type":"stat",
      "name":"stat above threshold"
    },
    {
      "fields":[
      {
        "key":"domain",
        "name":"Domain",
        "type":"text"
      },
      {
        "key":"world",
        "name":"World",
        "type":"text"
      },
      {
        "key":"cat",
        "name":"Category",
        "type":"text"
      },
      {
        "key":"stat",
        "name":"statistic",
        "type":"text"
      },
      {
        "key":"lowerThreshold",
        "name":"Lower bound threshold",
        "type":"text"
      },
      {
        "key":"upperThreshold",
        "name":"Upper bound threshold",
        "type":"text"
      }
      ],
      "type":"statwithin",
      "name":"Stat within boundaries"
    },
    {
      "fields":[
      {
        "key":"region",
        "name":"Region name",
        "type":"text"
      },
      {
        "key":"world",
        "name":"World name",
        "type":"text"
      }
      ],
      "type":"wgregion",
      "name":"(WorldGuard) Inside region"
    }
    ],
    "rewards":[
    {
      "fields":[
      {
        "key":"command",
        "name":"Command",
        "type":"text"
      }
      ],
      "type":"comm",
      "name":"Execute console command"
    },
    {
      "fields":[
      {
        "key":"name",
        "name":"Counter name",
        "type":"text"
      },
      {
        "key":"count",
        "name":"Amount to increment",
        "type":"text"
      }
      ],
      "type":"counter",
      "name":"Increment counter"
    },
    {
      "fields":[
      {
        "key":"subgroup",
        "name":"Subgroup to add",
        "type":"text"
      }
      ],
      "type":"subgroup",
      "name":"(DroxPerms) add subgroup"
    },
    {
      "fields":[
      {
        "key":"track",
        "name":"Track to promote along",
        "type":"text"
      }
      ],
      "type":"promote",
      "name":"(DroxPerms) Promote along track"
    },
    {
      "fields":[
      {
        "key":"amount",
        "name":"Amount to give",
        "type":"text"
      }
      ],
      "type":"money",
      "name":"(vault) Give money"
    },
    {
      "fields":[
      {
        "key":"itemStr",
        "name":"Item String (CraftBook syntax)",
        "type":"text"
      },
      {
        "key":"tryEnderChest",
        "name":"try placed in enderchest",
        "type":"bool"
      },
      {
        "key":"tryDrop",
        "name":"drop item in world",
        "type":"bool"
      }
      ],
      "type":"item",
      "name":"Item reward"
    },
    {
      "fields":[
      {
        "key":"comm",
        "name":"Command",
        "type":"text"
      }
      ],
      "type":"playercommand",
      "name":"Execute command as player"
    },
    {
      "fields":[
      {
        "key":"potionType",
        "name":"Potion Type",
        "type":"text"
      },
      {
        "key":"amplifier",
        "name":"Amplifier",
        "type":"text"
      },
      {
        "key":"duration",
        "name":"Duration",
        "type":"text"
      }
      ],
      "type":"potion",
      "name":"Apply potion effect"
    },
    {
      "fields":[
      {
        "key":"group",
        "name":"Group to add",
        "type":"text"
      }
      ],
      "type":"vaultaddgroup",
      "name":"(Vault) add/set group"
    },
    {
      "fields":[
      {
        "key":"to",
        "name":"Teleport to",
        "type":"location"
      }
      ],
      "type":"teleport",
      "name":"Teleport to location"
    }
    ]
});});