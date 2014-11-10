package com.scottkillen.mod.dendrology.content;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer;
import com.scottkillen.mod.dendrology.block.ModLogBlock;
import com.scottkillen.mod.dendrology.block.ModPlanksBlock;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import com.scottkillen.mod.dendrology.block.ModStairsBlock;
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
import java.util.List;

import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.ACEMUS_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.BASIC_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.CERASU_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.KULIST_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.NO_COLOR;

@SuppressWarnings("NonSerializableFieldInSerializableClass")
public enum TreeContent
{
    // REORDERING WILL CAUSE DAMAGE TO SAVES
    ACEMUS(ACEMUS_COLOR, new AcemusTree()),
    CEDRUM(NO_COLOR, new CedrumTree()),
    CERASU(CERASU_COLOR, new CerasuTree()),
    DELNAS(NO_COLOR, new DelnasTree()),
    EWCALY(NO_COLOR, new EwcalyTree()),
    HEKUR(BASIC_COLOR, new HekurTree()),
    KIPARIS(NO_COLOR, new KiparisTree()),
    KULIST(KULIST_COLOR, new KulistTree()),
    LATA(BASIC_COLOR, new LataTree()),
    NUCIS(BASIC_COLOR, new NucisTree()),
    PORFFOR(NO_COLOR, new PorfforTree()),
    SALYX(NO_COLOR, new SalyxTree()),
    TUOPA(BASIC_COLOR, new TuopaTree());

    private final AbstractTree treeGen;
    private final Colorizer colorizer;
    private int leavesBlock;
    private int leavesMeta;
    private int logBlock;
    private int logMeta;
    private int planksBlock;
    private int planksMeta;
    private int saplingBlock;
    private int saplingMeta;
    private int stairsBlock;

    static
    {
        for (final TreeContent tree : TreeContent.values())
            tree.treeGen.setTree(tree);
    }

    @SuppressWarnings("ConstructorWithTooManyParameters")
    TreeContent(Colorizer colorizer, AbstractTree treeGen)
    {
        this.colorizer = colorizer;
        this.treeGen = treeGen;
    }

    public static ImmutableList<ModLogBlock> getLogBlocks()
    {
        final List<ModLogBlock> blocks = Lists.newArrayList();
        final List<String> names = Lists.newArrayList();
        for (final TreeContent tree : values())
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
        final List<TreeContent> trees = Lists.newArrayList();
        for (final TreeContent tree : values())
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
        final List<TreeContent> trees = Lists.newArrayList();
        for (final TreeContent tree : values())
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

    public static ImmutableList<ModPlanksBlock> getPlanksBlocks()
    {
        final List<ModPlanksBlock> blocks = Lists.newArrayList();
        final List<String> names = Lists.newArrayList();
        for (final TreeContent tree : values())
        {
            tree.planksBlock = blocks.size();
            tree.planksMeta = names.size();

            names.add(tree.toString());
            if (names.size() == ModPlanksBlock.CAPACITY)
            {
                //noinspection ObjectAllocationInLoop
                blocks.add(new ModPlanksBlock(names));
                names.clear();
            }
        }
        if (!names.isEmpty()) blocks.add(new ModPlanksBlock(names));

        return ImmutableList.copyOf(blocks);
    }

    public static ImmutableList<ModStairsBlock> getStairsBlocks()
    {
        final List<ModStairsBlock> blocks = Lists.newArrayList();
        for (final TreeContent tree : values())
        {
            tree.stairsBlock = blocks.size();
            //noinspection ObjectAllocationInLoop
            final ModStairsBlock block = new ModStairsBlock(tree.getPlanksBlock(), tree.planksMeta);
            block.setBlockName(String.format("stairs.%s", tree));
            blocks.add(block);
        }

        return ImmutableList.copyOf(blocks);
    }

    public ModLogBlock getLogBlock()
    {
        return ModBlocks.LOG_BLOCKS.get(logBlock);
    }

    public int getLogMeta() { return logMeta; }

    public ModLeavesBlock getLeavesBlock()
    {
        return ModBlocks.LEAVES_BLOCKS.get(leavesBlock);
    }

    public int getLeavesMeta() { return leavesMeta; }

    public ModPlanksBlock getPlanksBlock()
    {
        return ModBlocks.PLANKS_BLOCKS.get(planksBlock);
    }

    public int getPlanksMeta() { return planksMeta; }

    public ModSaplingBlock getSaplingBlock()
    {
        return ModBlocks.SAPLING_BLOCKS.get(saplingBlock);
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
