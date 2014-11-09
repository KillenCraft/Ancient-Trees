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
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.List;

import static com.google.common.base.Preconditions.*;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.ACEMUS_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.BASIC_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.CERASU_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.KULIST_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.NO_COLOR;

@SuppressWarnings("NonSerializableFieldInSerializableClass")
public enum Tree
{
    ACEMUS(ACEMUS_COLOR, 0, 7, new AcemusTree()),
    CEDRUM(NO_COLOR, 0, 1, new CedrumTree()),
    CERASU(CERASU_COLOR, 0, 2, new CerasuTree()),
    DELNAS(NO_COLOR, 0, 8, new DelnasTree()),
    EWCALY(NO_COLOR, 0, 4, new EwcalyTree()),
    HEKUR(BASIC_COLOR, 0, 6, new HekurTree()),
    KIPARIS(NO_COLOR, 0, 3, new KiparisTree()),
    KULIST(KULIST_COLOR, 0, 5, new KulistTree()),
    LATA(BASIC_COLOR, 0, 0, new LataTree()),
    NUCIS(BASIC_COLOR, 0, 10, new NucisTree()),
    PORFFOR(NO_COLOR, 0, 12, new PorfforTree()),
    SALYX(NO_COLOR, 0, 11, new SalyxTree()),
    TUOPA(BASIC_COLOR, 0, 9, new TuopaTree());

    private static final ImmutableList<ImmutableList<Tree>> LOGS_N_LEAVES_GROUPS = ImmutableList
            .of(ImmutableList.of(LATA, CEDRUM, CERASU, KIPARIS), ImmutableList.of(EWCALY, KULIST, HEKUR, ACEMUS),
                    ImmutableList.of(DELNAS, TUOPA, NUCIS, SALYX), ImmutableList.of(PORFFOR));
    private static final ImmutableList<ImmutableList<Tree>> PLANKS_GROUPS = ImmutableList.of(ImmutableList
            .of(LATA, CEDRUM, CERASU, KIPARIS, EWCALY, KULIST, HEKUR, ACEMUS, DELNAS, TUOPA, NUCIS, SALYX, PORFFOR));
    private final int planksBlock;
    private final int planksMeta;
    private final Colorizer colorizer;
    private final AbstractTree treeGen;
    private int logBlock;
    private int logMeta;
    private int leavesBlock;
    private int leavesMeta;
    private int saplingBlock;
    private int saplingMeta;

    static
    {
        for (final Tree tree : Tree.values())
            tree.treeGen.setTree(tree);
    }

    @SuppressWarnings("ConstructorWithTooManyParameters")
    Tree(Colorizer colorizer, int planksBlock, int planksMeta, AbstractTree treeGen)
    {
        this.colorizer = colorizer;
        this.planksBlock = planksBlock;
        this.planksMeta = planksMeta;
        this.treeGen = treeGen;
    }

    public static List<String> getPlankNames(int group)
    {
        checkElementIndex(group, PLANKS_GROUPS.size());

        return buildNamesList(PLANKS_GROUPS.get(group));
    }

    private static ImmutableList<String> buildNamesList(ImmutableList<Tree> trees)
    {
        final List<String> names = Lists.newArrayList();
        for (final Tree tree : trees) names.add(tree.toString());

        return ImmutableList.copyOf(names);
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
                //noinspection ObjectAllocationInLoop
                blocks.add(new ModLogBlock(names));
                names.clear();
            }
        }
        if (!names.isEmpty()) blocks.add(new ModLogBlock(names));

        return ImmutableList.copyOf(blocks);
    }

    public static ImmutableList<ModLeavesBlock> getLeavesBlocks()
    {
        final List<ModLeavesBlock> blocks = Lists.newArrayList();
        final List<String> names = Lists.newArrayList();
        final List<Tree> trees = Lists.newArrayList();
        for (final Tree tree : values())
        {
            tree.leavesBlock = blocks.size();
            tree.leavesMeta = names.size();

            names.add(tree.toString());
            trees.add(tree);
            if (names.size() == ModLeavesBlock.CAPACITY)
            {
                //noinspection ObjectAllocationInLoop
                blocks.add(new ModLeavesBlock(names, trees));

                names.clear();
                trees.clear();
            }
        }
        if (!names.isEmpty()) blocks.add(new ModLeavesBlock(names, trees));

        return ImmutableList.copyOf(blocks);
    }

    public static ImmutableList<ModSaplingBlock> getSaplingBlocks()
    {
        final List<ModSaplingBlock> blocks = Lists.newArrayList();
        final List<String> names = Lists.newArrayList();
        final List<Tree> trees = Lists.newArrayList();
        for (final Tree tree : values())
        {
            tree.saplingBlock = blocks.size();
            tree.saplingMeta = names.size();

            names.add(tree.toString());
            trees.add(tree);
            if (names.size() == ModSaplingBlock.CAPACITY)
            {
                //noinspection ObjectAllocationInLoop
                blocks.add(new ModSaplingBlock(names, trees));

                names.clear();
                trees.clear();
            }
        }
        if (!names.isEmpty()) blocks.add(new ModSaplingBlock(names, trees));

        return ImmutableList.copyOf(blocks);
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

    public Colorizer getColorizer()
    {
        return colorizer;
    }

    public int getSaplingMeta()
    {
        return saplingMeta;
    }

    public AbstractTree getTreeGen()
    {
        return treeGen;
    }
}
