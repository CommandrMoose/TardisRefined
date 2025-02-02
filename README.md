![image](https://i.imgur.com/eIfAaYJ.png)



<p align="center">
    <a href="https://crowdin.com/project/tardis-refined">
        <img src="https://badges.crowdin.net/tardis-refined/localized.svg" alt="Crowdin">
    </a>
    <a href="https://www.curseforge.com/minecraft/mc-mods/tardis-refined">
        <img src="https://cf.way2muchnoise.eu/782697.svg?badge_style=flat" alt="CurseForge">
    </a>
    <a href="https://modrinth.com/mod/tardis-refined">
        <img src="https://modrinth-utils.vercel.app/api/badge/downloads?id=nqVt6aES&logo=true" alt="ModRinth">
    </a>
    <a href="https://discord.gg/your-discord-invite">
        <img src="https://discordapp.com/api/guilds/1054629302152478730/widget.png?style=shield" alt="Discord Shield">
    </a>
</p>


# TARDIS Refined

TARDIS Refined is a Minecraft mod for Forge and Fabric that brings the TARDIS from *Doctor Who* to Minecraft.

🔗 **[View the Wiki](https://wiki.tardisrefined.net)**

---

## Workspace

TARDIS Refined implements the [Architectury Gradle plugin](https://github.com/architectury/architectury-plugin), enabling seamless multi-modloader development through a common module. No additional plugins or files are required. It is recommended to familiarise yourself with Architectury before submitting pull requests.

This repository is configured to utilise Forge's data generation for common data-driven content. However, generated files are not included in the repository. Running the Forge Data task after cloning the repository—and periodically thereafter—is highly recommended to keep your data up to date.

---

## Contributions

We welcome contributions to TARDIS Refined! If you have an idea for a new feature or have found a bug, please open an issue. If you’d like to contribute code, submit a pull request. Please note that the development team follows a set roadmap, and substantial additions or changes will be reviewed to ensure they align with the project’s vision.

### 🏆 Contributors

| **Credit** | **Contribution** |
|------------|-----------------|
| [CommandrMoose](https://twitter.com/CommandrMoose) | Project Lead & Developer |
| [Jeryn](https://github.com/Jeryn99) | Developer |
| [50ap5ud5](https://twitter.com/50ap5ud5) | Developer |
| [duzo](https://github.com/Duzos) | Developer |
| [MagicMan](https://twitter.com/MagicMrmann) | Art Lead |
| [ILikePandas](https://twitter.com/ILikePandas18) | Console Builds |
| [Jacob.K](https://twitter.com/JacobKauffman11) | Console Builds |

Special thanks to: **Commoble, Lucraft, starray1000000, The Judge, Monsterwaill, and Sea Pickle**.

### 🏗 Additional Builders
- **"Radiance"** – *Getsuga Tenno*
- **"Watchface"** – *t___cup*
- **"Vapor"** – *Tank*
- **"Newwave"** – *Muffled*

Other builds were contributed by members of the team.

---

## Immersive Portals Support (Developer Note)

This mod includes dedicated compatibility and integration with **Immersive Portals** when it is installed.

- By default, Immersive Portals is *not* enabled in development.
- Fabric's `build.gradle` must be modified to allow support.
- Instructions on enabling this can be found within `build.gradle`.

🚀 **For users:** No additional steps are required. Simply install **Fabric TR** and **Fabric IP**, and you’re good to go!
