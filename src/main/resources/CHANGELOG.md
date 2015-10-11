# Ancient Trees Changelog

## 1.6.4
- Reduce number of saplings dropped.
- Give player control over sapling drop frequency.

## 1.6.3
- Fix some localization errors.
- Fix for exceptions unhandled by Immersive Engineering ([BluSunrize/ImmersiveEngineering#419](/BluSunrize/ImmersiveEngineering/issues/419))
- Fix bug that causes MineFactoryReloaded to often plant the wrong sapling.

## 1.6.2
- Fix NEI complaining of (non-crash) exception
- Fix incorrectly registering leaves as "fertilizable" with Minefactory Reloaded
- Update to latest version (1.3.2) of KoreSample

## 1.6.1
- Make a separate creative tab for blocks added for Chisel, rather than hijack Chisel's tab
- Fix server crash (while looking for Chisel's creative tab)

## 1.6.0
- Update Chisel integration to use new Chisel API

## 1.5.1
- Fixed crash when Storage Drawers integration is enabled and Refined Relocation is present.

## 1.5.0
- Storage Drawers Integration: Make storage drawers from ancient trees!
- Switches in configuration file to toggle integration with other mods.

## 1.4.0
- Saplings can now be planted, fertilized and harvested in MineFactory reloaded machines. (Though you should
  be careful with larger trees!)
- Saplings are now found wrapped in ancient packages inside chests. (This is to allow us more rarity than the
  vanilla chest loot system.)

## 1.3.1
- All saplings are now added to chests.  ([#29](/MinecraftModArchive/Dendrology/issues/29))

## 1.3.0
- Improved Forestry integration.
- Removed compile time dependency on The Chisel jar.
- Fixed crashbug that occurs with the Aether mod.
- All logs can now be smelted into charcoal.  ([#27](/MinecraftModArchive/Dendrology/issues/27))

## 1.2.4
- Fixed missing localizations for chisel blocks.

## 1.2.3
- Updated build environment to compile against Minecraft Forge 10.13.2.1291
- Added [Chisel 2][chisel_url] integration.
- Fix localization errors.
- Fixed planks making the wrong sounds.
[chisel_url]: http://www.curse.com/mc-mods/minecraft/225236-chisel-2

## 1.2.2
- Add support for [Version Checker][vc_url].
[vc_url]: http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2091981-version-checker-auto-update-mods-and-clean

## 1.2.1
- Added version specific dependency for Kore Sample

## 1.2.0
- Replaced logo with custom art from [Septolum][Septolum_tweet]
- Trees will now generate during world gen, though they will be *very* scarce.
- Reduce virtual sawdust to virtually nil
[Septolum_tweet]: https://twitter.com/Septolum

## 1.1.0
- Added CHANGELOG.md after [tweet by tedyhere][tedy_tweet]
- Added [GardenStuff][gardenstuff_link] integration. ([#19](/MinecraftModArchive/Dendrology/issues/19))
- Added German locale. ([MCManuelLP][mcmanuelLP_link])
- Broke out common code into a separate mod ([Kore Sample][koresample]) ([#15](/MinecraftModArchive/Dendrology/issues/15))
[gardenstuff_link]: http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/2163513
[mcmanuelLP_link]: https://github.com/mcmanuellp
[tedy_tweet]: https://twitter.com/tedyhere/status/546699297782521856
[koresample]: http://scottk.us/KoreSample

## 1.0.0
- Added Great Britain locale. ([Seanthebaker][sean_link])
- Added Pirate Speak locale.
- Added Esperanto locale.
- Added [Minechem][minechem_link] integration.
- Added [Forestry][forestry_link] integration.
[minechem_link]: http://www.minechemmod.com/
[forestry_link]: http://forestry.sengir.net
[sean_link]: https://github.com/seanthebaker

## 0.3.2
- Fix the fix for [#11](/MinecraftModArchive/Dendrology/issues/11). ([#12](/MinecraftModArchive/Dendrology/issues/12))

## 0.3.1
- Added code to prevent exceptions caused by ExtraUtilities. ([#11](/MinecraftModArchive/Dendrology/issues/11))

## 0.3.0
- Replaced low quality sapling textures with saplings from [The Painterly Pack][painterlypack_link] by [Rhodox][rhodox_tweet] and friends.
- Allowed some saplings to be used as potion ingredients.
- Allowed some saplings to be crafted into dyes.
- Added saplings as random loot for chests.
- Added config file settings for the rarity of saplings as chest loot.
[painterlypack_link]: http://painterlypack.net/
[rhodox_tweet]: https://twitter.com/PainterlyPack

## 0.2.2
- Fixed planks being insta-break with any tool. (#9)
- Replaced placeholder sapling textures with original low quality works. :smiling_imp:

## 0.2.1
- Fixed missing textures.

## 0.2.0
- Fixed crash bug when running on server.
- Standardized build scripts.
- Began preparations to separate kore mod.

## 0.1.0
- Thirteen new wood species.
- Each species is block complete.

