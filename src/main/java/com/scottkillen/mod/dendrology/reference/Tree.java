package com.scottkillen.mod.dendrology.reference;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer;
import com.scottkillen.mod.dendrology.block.ModLogBlock;
import com.scottkillen.mod.dendrology.block.ModPlanksBlock;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import com.scottkillen.mod.dendrology.world.gen.feature.AcemusTree;
import com.scottkillen.mod.dendrology.world.gen.feature.CedrumTree;
import com.scottkillen.mod.dendrology.world.gen.feature.CerasuTree;
import com.scottkillen.mod.dendrology.world.gen.feature.DelnasTree;
import com.scottkillen.mod.dendrology.world.gen.feature.EwcalyTree;
import com.scottkillen.mod.dendrology.world.gen.feature.HekurTree;
import com.scottkillen.mod.dendrology.world.gen.feature.KiparisTree;
import com.scottkillen.mod.dendrology.world.gen.feature.KulistTree;
import com.scottkillen.mod.dendrology.world.gen.feature.LataTree;
import com.scottkillen.mod.dendrology.world.gen.feature.NucisTree;
import com.scottkillen.mod.dendrology.world.gen.feature.PorfforTree;
import com.scottkillen.mod.dendrology.world.gen.feature.SalyxTree;
import com.scottkillen.mod.dendrology.world.gen.feature.TuopaTree;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.ACEMUS_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.BASIC_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.CERASU_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.KULIST_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.NONE;

@SuppressWarnings("NonSerializableFieldInSerializableClass")
public enum Tree
{
    ACEMUS(1, 3, ACEMUS_COLOR, 0, 7, 0, 7, new AcemusTree()),
    CEDRUM(0, 1, NONE, 0, 1, 0, 1, new CedrumTree()),
    CERASU(0, 2, CERASU_COLOR, 0, 2, 0, 2, new CerasuTree()),
    DELNAS(2, 0, NONE, 1, 0, 0, 8, new DelnasTree()),
    EWCALY(1, 0, NONE, 0, 4, 0, 4, new EwcalyTree()),
    HEKUR(1, 2, BASIC_COLOR, 0, 6, 0, 6, new HekurTree()),
    KIPARIS(0, 3, NONE, 0, 3, 0, 3, new KiparisTree()),
    KULIST(0, 1, KULIST_COLOR, 0, 5, 0, 5, new KulistTree()),
    LATA(0, 0, BASIC_COLOR, 0, 0, 0, 0, new LataTree()),
    NUCIS(2, 2, BASIC_COLOR, 1, 2, 0, 10, new NucisTree()),
    PORFFOR(3, 0, NONE, 1, 4, 0, 12, new PorfforTree()),
    SALYX(2, 3, NONE, 1, 3, 0, 11, new SalyxTree()),
    TUOPA(2, 1, BASIC_COLOR, 1, 1, 0, 9, new TuopaTree());

    static
    {
        for (final Tree tree : Tree.values())
            tree.treeGen.setTree(tree);
    }

    private static final ImmutableList<ImmutableList<Tree>> LOGS_N_LEAVES_GROUPS = ImmutableList
            .of(ImmutableList.of(LATA, CEDRUM, CERASU, KIPARIS), ImmutableList.of(EWCALY, KULIST, HEKUR, ACEMUS),
                    ImmutableList.of(DELNAS, TUOPA, NUCIS, SALYX), ImmutableList.of(PORFFOR));

    private static final ImmutableList<ImmutableList<Tree>> SAPLING_GROUPS = ImmutableList
            .of(ImmutableList.of(LATA, CEDRUM, CERASU, KIPARIS, EWCALY, KULIST, HEKUR, ACEMUS),
                    ImmutableList.of(DELNAS, TUOPA, NUCIS, SALYX, PORFFOR));

    private static final ImmutableList<ImmutableList<Tree>> PLANKS_GROUPS = ImmutableList.of(ImmutableList
            .of(LATA, CEDRUM, CERASU, KIPARIS, EWCALY, KULIST, HEKUR, ACEMUS, DELNAS, TUOPA, NUCIS, SALYX, PORFFOR));

    private int logBlock;
    private int logMeta;
    private final int leavesBlock;
    private final int leavesMeta;
    private final int saplingBlock;
    private final int saplingMeta;
    private final int planksBlock;
    private final int planksMeta;
    private final Colorizer colorizer;
    private final AbstractTree treeGen;

    @SuppressWarnings("ConstructorWithTooManyParameters")
    Tree(int leavesBlock, int leavesMeta, Colorizer colorizer, int saplingBlock,
         int saplingMeta, int planksBlock, int planksMeta, AbstractTree treeGen)
    {
        this.leavesBlock = leavesBlock;
        this.leavesMeta = leavesMeta;
        this.colorizer = colorizer;
        this.saplingBlock = saplingBlock;
        this.saplingMeta = saplingMeta;
        this.planksBlock = planksBlock;
        this.planksMeta = planksMeta;
        this.treeGen = treeGen;
    }

    public static ImmutableList<String> getLogNames(int group)
    {
        checkElementIndex(group, LOGS_N_LEAVES_GROUPS.size());

        return buildNamesList(LOGS_N_LEAVES_GROUPS.get(group));
    }

    public static ImmutableList<String> getLeavesNames(int group)
    {
        return getLogNames(group);
    }

    public static List<String> getPlankNames(int group)
    {
        checkElementIndex(group, PLANKS_GROUPS.size());

        return buildNamesList(PLANKS_GROUPS.get(group));
    }

    public static ImmutableList<String> getSaplingNames(int group)
    {
        checkElementIndex(group, SAPLING_GROUPS.size());

        return buildNamesList(SAPLING_GROUPS.get(group));
    }

    private static ImmutableList<String> buildNamesList(ImmutableList<Tree> trees)
    {
        final List<String> names = Lists.newArrayList();
        for (final Tree tree : trees) names.add(tree.toString());

        return ImmutableList.copyOf(names);
    }

    public static List<? extends WorldGenerator> getSaplingTreeGens(int group)
    {
        checkElementIndex(group, SAPLING_GROUPS.size());

        final List<WorldGenerator> treeGens = Lists.newArrayList();
        for (final Tree tree : SAPLING_GROUPS.get(group)) treeGens.add(tree.treeGen);

        return ImmutableList.copyOf(treeGens);
    }

    public static List<Colorizer> getColorizers(int group)
    {
        checkElementIndex(group, LOGS_N_LEAVES_GROUPS.size());

        final List<Colorizer> colorizers = Lists.newArrayList();
        for (final Tree tree : LOGS_N_LEAVES_GROUPS.get(group)) colorizers.add(tree.colorizer);

        return ImmutableList.copyOf(colorizers);
    }

    public static ImmutableList<ImmutablePair<Item, Integer>> getSaplings(int group)
    {
        checkElementIndex(group, LOGS_N_LEAVES_GROUPS.size());

        final List<ImmutablePair<Item, Integer>> saplings = Lists.newArrayList();
        for (final Tree tree : LOGS_N_LEAVES_GROUPS.get(group))
            saplings.add(ImmutablePair.of(Item.getItemFromBlock(tree.getSaplingBlock()), tree.saplingMeta));

        return ImmutableList.copyOf(saplings);
    }

    public ModLogBlock getLogBlock()
    {
        return ModBlocks.logs.get(logBlock);
    }

    public int getLogMeta() { return logMeta; }

    public ModLeavesBlock getLeavesBlock()
    {
        return ModBlocks.leaves.get(leavesBlock);
    }

    public int getLeavesMeta() { return leavesMeta; }

    public ModPlanksBlock getPlanksBlock()
    {
        return ModBlocks.planks.get(planksBlock);
    }

    public int getPlanksMeta() { return planksMeta; }

    public ModSaplingBlock getSaplingBlock()
    {
        return ModBlocks.saplings.get(saplingBlock);
    }

    @Override
    public String toString()
    {
        return name().toLowerCase();
    }

    public static ImmutableList<ModLogBlock> getLogBlocks()
    {
        final List<ModLogBlock> blocks = Lists.newArrayList();
        final List<String> names = Lists.newArrayList();
        for (final Tree tree : values())
        {
            tree.logBlock = blocks.size();
            tree.logMeta = names.size();

            names.add(tree.toString());
            if (names.size() == ModLogBlock.CAPACITY)
            {
                blocks.add(new ModLogBlock(names));
                names.clear();
            }
        }
        if (names.size() > 0)
            blocks.add(new ModLogBlock(names));

        return ImmutableList.copyOf(blocks);
    }
}
