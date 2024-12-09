![image](https://i.imgur.com/eIfAaYJ.png)

<img src="https://discordapp.com/api/guilds/1054629302152478730/widget.png?style=shield" alt="Discord Shield"/>[![Crowdin](https://badges.crowdin.net/tardis-refined/localized.svg)](https://crowdin.com/project/tardis-refined)
[![CurseForge](https://cf.way2muchnoise.eu/782697.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/tardis-refined)
[![ModRinth](https://modrinth-utils.vercel.app/api/badge/downloads?id=nqVt6aES&logo=true)](https://modrinth.com/mod/tardis-refined)




 TARDIS Refined is a Minecraft mod for Forge and Fabric which brings the TARDIS from Doctor Who to Minecraft.
 
 
 <a href="https://wiki.tardisrefined.net">View the wiki.</a>

## Workspace
TARDIS Refined implements the <a href="https://github.com/architectury/architectury-plugin">Architectuary Gradle plugin</a>, allowing easy multi-modloader development via a common module. This functionality does not require any additional installation of plugins or files. It is recommended you familiarise yourself with Achitectury first before proceeding with PR submissions. 

This repository has been set up to utilise Forge's data generation for most common data gen items. The repository does not include these generated files, so it is recommended to run the Forge Data task when you clone the repository. It is also recommended to run this task regularly to stay concurrent with your branch's data.


## Contributions

We welcome contributions to TARDIS Refined. If you have an idea for a new feature or have found a bug, please open an issue. If you would like to contribute code, please submit a pull request. Please understand that the associated development team have set tasks and goals for the project, and PRs that include large additions/changes will be closely examined to determine if they meet the project's design.

<table>
   <thead>
      <tr>
         <th>Credit</th>
         <th>Contribution</th>
      </tr>
   </thead>
   <tbody>
<tr><td><a href="https://twitter.com/CommandrMoose">CommandrMoose</a></td>
         <td>Project lead and Developer</td> </tr>
      <tr>
         <td><a href="https://github.com/Jeryn99">Jeryn</a></td>
         <td>Developer</td>
      </tr>
            <tr>
         <td><a href="https://twitter.com/50ap5ud5">50ap5ud5</a></td>
         <td>Developer</td>
      </tr>
    <tr>
         <td><a href="https://github.com/Duzos">duzo</a></td>
         <td>Developer</td>
      </tr>
<tr><td><a href="https://twitter.com/MagicMrmann">MagicMan</a></td>
         <td>Art Lead</td> </tr>
<tr><td><a href="https://twitter.com/ILikePandas18">ILikePandas</a></td>
         <td>Console Builds</td> </tr>
         <tr><td><a href="https://twitter.com/JacobKauffman11">Jacob.K</a></td>
         <td>Console Builds</td> </tr>
   </tbody>
</table>

With special thanks to Commoble, Lucraft, starray1000000, The Judge, Monsterwaill and Sea Pickle<br/><hr/>

Additionally, thanks to the additional builders who contributed desktops:
"Radiance" by Getsuga Tenno
"Watchface" by t___cup
"Vapor" by Tank
"Newwave" by Muffled

Other builds made by members of the team. 

![image](https://user-images.githubusercontent.com/34910888/209611682-abeaedc6-cf9a-465f-a693-9ba85f38cca6.png)

## Immersive Portals Support (Developer Note)
This mod has dedicated support for being compatible and integrating with Immersive Portals when Immersive Portals is present.
By default, Immersive Portals is not enabled in dev and fabrics build.gradle file must be edited to allow for this.
Instructions on how to do this can be found in the relevant build.gradle
If you are a user, the above is not relevant to you. All you should need to do is install Fabric TR and Fabric IP and you will be all ready to go.
