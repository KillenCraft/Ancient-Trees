package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.item.LeavesItem;
import com.scottkillen.mod.dendrology.item.LogItem;
import com.scottkillen.mod.dendrology.item.SaplingItem;
import com.scottkillen.mod.dendrology.world.gen.feature.AcemusTree;
import com.scottkillen.mod.dendrology.world.gen.feature.CedrumTree;
import com.scottkillen.mod.dendrology.world.gen.feature.CerasuTree;
import com.scottkillen.mod.dendrology.world.gen.feature.DelnasTree;
import com.scottkillen.mod.dendrology.world.gen.feature.EwcalyTree;
import com.scottkillen.mod.dendrology.world.gen.feature.HekurTree;
import com.scottkillen.mod.dendrology.world.gen.feature.KiparisTree;
import com.scottkillen.mod.dendrology.world.gen.feature.KulistTree;
import com.scottkillen.mod.dendrology.world.gen.feature.LataTree;
import com.scottkillen.mod.dendrology.world.gen.feature.PorfforTree;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenerator;

import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.ACEMUS;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.BASIC;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.CERASU;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.KULIST;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.NONE;

@SuppressWarnings({ "StaticNonFinalField", "UtilityClass", "PublicField", "WeakerAccess" })
public final class ModBlocks
{
    private static final int DEFAULT_LEAVES_FIRE_ENCOURAGEMENT = 30;
    private static final int DEFAULT_LOG_FIRE_ENCOURAGEMENT = 5;
    private static final int DEFAULT_LEAVES_FLAMMABILITY = 60;
    private static final int DEFAULT_LOG_FLAMMABILITY = 5;

    private static final ImmutableList<String> LOG0_NAMES = ImmutableList.of("lata", "cedrum", "cerasu", "kiparis");
    private static final ImmutableList<String> LOG1_NAMES = ImmutableList.of("ewcaly", "kulist", "hekur", "acemus");
    private static final ImmutableList<String> LOG2_NAMES = ImmutableList.of("delnas", "poplar", "walnut", "willow");
    private static final ImmutableList<String> LOG3_NAMES = ImmutableList.of("porffor");

    private static final ImmutableList<String> LEAVES0_NAMES = ImmutableList.of("lata", "cedrum", "kiparis", "ewcaly");
    private static final ImmutableList<String> LEAVES1_NAMES = ImmutableList.of("hekur", "kulist", "delnas", "poplar");
    private static final ImmutableList<String> LEAVES2_NAMES = ImmutableList.of("cerasu", "acemus");
    private static final ImmutableList<String> LEAVES3_NAMES = ImmutableList.of("walnut", "willow", "porffor");

    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES0_COLORS = ImmutableList.of(BASIC, NONE, NONE, NONE);
    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES1_COLORS = ImmutableList.of(BASIC, KULIST, NONE, BASIC);
    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES2_COLORS = ImmutableList.of(CERASU, ACEMUS);
    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES3_COLORS = ImmutableList.of(BASIC, NONE, NONE);

    private static final ImmutableList<String> SAPLING0_NAMES =
            ImmutableList.of("lata", "cedrum", "cerasu", "kiparis", "ewcaly", "kulist", "hekur", "acemus");
    private static final ImmutableList<String> SAPLING1_NAMES = ImmutableList.of("delnas", "poplar", "walnut", "willow", "porffor");

    private static final ImmutableList<? extends WorldGenerator> SAPLING0_GENS = ImmutableList
            .of(new LataTree(), new CedrumTree(), new CerasuTree(), new KiparisTree(),
                    new EwcalyTree(), new KulistTree(), new HekurTree(), new AcemusTree());
    private static final ImmutableList<? extends WorldGenerator> SAPLING1_GENS =
            ImmutableList.of(new DelnasTree(), new WorldGenTaiga1(), new WorldGenTaiga1(), new WorldGenTaiga1(), new PorfforTree());

    public static Block logs0 = new ModLogBlock(LOG0_NAMES);
    public static Block logs1 = new ModLogBlock(LOG1_NAMES);
    public static Block logs2 = new ModLogBlock(LOG2_NAMES);
    public static Block logs3 = new ModLogBlock(LOG3_NAMES);
    public static ModLeavesBlock leaves0 = new ModLeavesBlock(LEAVES0_NAMES, LEAVES0_COLORS);
    public static ModLeavesBlock leaves1 = new ModLeavesBlock(LEAVES1_NAMES, LEAVES1_COLORS);
    public static ModLeavesBlock leaves2 = new ModLeavesBlock(LEAVES2_NAMES, LEAVES2_COLORS);
    public static ModLeavesBlock leaves3 = new ModLeavesBlock(LEAVES3_NAMES, LEAVES3_COLORS);
    public static BlockSapling sapling0 = new ModSaplingBlock(SAPLING0_NAMES, SAPLING0_GENS);
    public static BlockSapling sapling1 = new ModSaplingBlock(SAPLING1_NAMES, SAPLING1_GENS);

    private ModBlocks()
    {
        throw new AssertionError();
    }

    public static void init()
    {
        registerLogBlock(logs0, "logs0", LOG0_NAMES);
        registerLogBlock(logs1, "logs1", LOG1_NAMES);
        registerLogBlock(logs2, "logs2", LOG2_NAMES);
        registerLogBlock(logs3, "logs3", LOG3_NAMES);
        registerLeavesBlock(leaves0, "leaves0");
        registerLeavesBlock(leaves1, "leaves1");
        registerLeavesBlock(leaves2, "leaves2");
        registerLeavesBlock(leaves3, "leaves3");
        registerSaplingBlock(sapling0, "sapling0", SAPLING0_NAMES);
        registerSaplingBlock(sapling1, "sapling1", SAPLING1_NAMES);

        registerSaplings();
    }

    private static void registerSaplings()
    {   // lata
        ModLeavesBlock.addSapling(leaves0, 0, sapling0, 0);

        // cedrum
        ModLeavesBlock.addSapling(leaves0, 1, sapling0, 1);

        // kiparis
        ModLeavesBlock.addSapling(leaves0, 2, sapling0, 3);

        // ewcaly
        ModLeavesBlock.addSapling(leaves0, 3, sapling0, 4);

        // hekur
        ModLeavesBlock.addSapling(leaves1, 0, sapling0, 6);

        // porffor
        ModLeavesBlock.addSapling(leaves3, 2, sapling1, 4);

        // kulist
        ModLeavesBlock.addSapling(leaves1, 1, sapling0, 5);

        // delnas
        ModLeavesBlock.addSapling(leaves1, 2, sapling1, 0);

        // poplar
        ModLeavesBlock.addSapling(leaves1, 3, sapling1, 1);

        // cerasu
        ModLeavesBlock.addSapling(leaves2, 0, sapling0, 2);
        ModLeavesBlock.addSapling(leaves2, 1, sapling0, 2);

        // acemus
        ModLeavesBlock.addSapling(leaves2, 2, sapling0, 7);

        // walnut
        ModLeavesBlock.addSapling(leaves3, 0, sapling1, 2);

        // willow
        ModLeavesBlock.addSapling(leaves3, 1, sapling1, 3);
    }

    private static void registerLeavesBlock(Block block, String name)
    {
        GameRegistry.registerBlock(block, LeavesItem.class, name);
        Blocks.fire.setFireInfo(block, DEFAULT_LEAVES_FIRE_ENCOURAGEMENT, DEFAULT_LEAVES_FLAMMABILITY);
    }

    private static void registerLogBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, LogItem.class, name, block, subblockNames.toArray(new String[0]));
        Blocks.fire.setFireInfo(block, DEFAULT_LOG_FIRE_ENCOURAGEMENT, DEFAULT_LOG_FLAMMABILITY);
    }

    private static void registerSaplingBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, SaplingItem.class, name, block, subblockNames.toArray(new String[0]));
    }
}
