package com.scottkillen.mod.dendrology.content;


import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer;
import com.scottkillen.mod.dendrology.block.ModLogBlock;
import com.scottkillen.mod.dendrology.block.ModPlanksBlock;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import com.scottkillen.mod.dendrology.block.ModStairsBlock;
import com.scottkillen.mod.dendrology.block.ModWoodSlabBlock;
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

import static com.google.common.base.Preconditions.*;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.ACEMUS_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.BASIC_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.CERASU_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.KULIST_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.NO_COLOR;

@SuppressWarnings("NonSerializableFieldInSerializableClass")
public enum OverworldSpecies implements IContent
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

    private int leavesMeta;
    private int logMeta;
    private int planksMeta;
    private int saplingMeta;
    private int slabMetadata;

    private ModWoodSlabBlock doubleSlabBlock = null;
    private ModLeavesBlock leavesBlock = null;
    private ModLogBlock logBlock = null;
    private ModPlanksBlock planksBlock = null;
    private ModSaplingBlock saplingBlock = null;
    private ModWoodSlabBlock singleSlabBlock = null;
    private ModStairsBlock stairsBlock = null;

    static
    {
        for (final OverworldSpecies tree : OverworldSpecies.values())
            tree.treeGen.setTree(tree);
    }

    OverworldSpecies(Colorizer colorizer, AbstractTree treeGen)
    {
        this.colorizer = colorizer;
        this.treeGen = treeGen;
    }

    @Override
    public Colorizer getColorizer() { return colorizer; }

    @Override
    public ModLeavesBlock getLeavesBlock()
    {
        checkState(leavesBlock != null);
        return leavesBlock;
    }

    @Override
    public void setLeavesBlock(ModLeavesBlock leavesBlock)
    {
        checkState(this.leavesBlock == null);
        this.leavesBlock = leavesBlock;
    }

    @Override
    public int getLeavesMeta() { return leavesMeta; }

    @Override
    public void setLeavesMeta(int leavesMeta) { this.leavesMeta = leavesMeta; }

    @Override
    public ModLogBlock getLogBlock()
    {
        checkState(logBlock != null);
        return logBlock;
    }

    @Override
    public void setLogBlock(ModLogBlock logBlock)
    {
        checkState(this.logBlock == null);
        this.logBlock = logBlock;
    }

    @Override
    public int getLogMeta() { return logMeta; }

    @Override
    public void setLogMeta(int logMeta) { this.logMeta = logMeta; }

    @Override
    public String getName() { return name().toLowerCase(); }

    @Override
    public ModPlanksBlock getPlanksBlock()
    {
        checkState(planksBlock != null);
        return planksBlock;
    }

    @Override
    public void setPlanksBlock(ModPlanksBlock planksBlock)
    {
        checkState(this.planksBlock == null);
        this.planksBlock = planksBlock;
    }

    @Override
    public int getPlanksMeta() { return planksMeta; }

    @Override
    public void setPlanksMeta(int planksMeta) { this.planksMeta = planksMeta; }

    @Override
    public ModSaplingBlock getSaplingBlock()
    {
        checkState(saplingBlock != null);
        return saplingBlock;
    }

    @Override
    public void setSaplingBlock(ModSaplingBlock saplingBlock)
    {
        checkState(this.saplingBlock == null);
        this.saplingBlock = saplingBlock;
    }

    @Override
    public int getSaplingMeta() { return saplingMeta; }

    @Override
    public void setSaplingMeta(int saplingMeta) { this.saplingMeta = saplingMeta; }

    @Override
    public ModWoodSlabBlock getSingleSlabBlock()
    {
        checkState(singleSlabBlock != null);
        return singleSlabBlock;
    }

    @Override
    public void setSlabBlock(ModWoodSlabBlock block, boolean isDouble)
    {
        checkState(isDouble ? doubleSlabBlock == null : singleSlabBlock == null);
        if (isDouble) doubleSlabBlock = block;
        else singleSlabBlock = block;
    }

    @Override
    public int getSlabMeta() { return slabMetadata; }

    @Override
    public void setSlabMeta(int slabMetadata) { this.slabMetadata = slabMetadata; }

    @Override
    public ModStairsBlock getStairsBlock()
    {
        checkState(stairsBlock != null);
        return stairsBlock;
    }

    @Override
    public void setStairsBlock(ModStairsBlock stairsBlock)
    {
        checkState(this.stairsBlock == null);
        this.stairsBlock = stairsBlock;
    }

    @Override
    public AbstractTree getTreeGen() { return treeGen; }
}
