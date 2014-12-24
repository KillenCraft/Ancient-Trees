package com.scottkillen.mod.dendrology.content;


import com.scottkillen.mod.dendrology.world.AcemusColorizer;
import com.scottkillen.mod.dendrology.world.CerasuColorizer;
import com.scottkillen.mod.dendrology.world.KulistColorizer;
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
import com.scottkillen.mod.kore.common.ProvidesPotionEffect;
import com.scottkillen.mod.kore.tree.DefinesTree;
import com.scottkillen.mod.kore.tree.block.LeavesBlock;
import com.scottkillen.mod.kore.tree.block.LogBlock;
import com.scottkillen.mod.kore.tree.block.WoodBlock;
import com.scottkillen.mod.kore.tree.block.SaplingBlock;
import com.scottkillen.mod.kore.tree.block.StairsBlock;
import com.scottkillen.mod.kore.tree.block.SlabBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionHelper;
import net.minecraft.world.IBlockAccess;

import static com.google.common.base.Preconditions.*;
import static com.scottkillen.mod.dendrology.content.OverworldTreeSpecies.Colorizer.ACEMUS_COLOR;
import static com.scottkillen.mod.dendrology.content.OverworldTreeSpecies.Colorizer.BASIC_COLOR;
import static com.scottkillen.mod.dendrology.content.OverworldTreeSpecies.Colorizer.CERASU_COLOR;
import static com.scottkillen.mod.dendrology.content.OverworldTreeSpecies.Colorizer.KULIST_COLOR;
import static com.scottkillen.mod.dendrology.content.OverworldTreeSpecies.Colorizer.NO_COLOR;

@SuppressWarnings({ "NonSerializableFieldInSerializableClass", "ClassHasNoToStringMethod" })
public enum OverworldTreeSpecies implements DefinesTree, ProvidesPotionEffect
{
    // REORDERING WILL CAUSE DAMAGE TO SAVES
    ACEMUS(ACEMUS_COLOR, new AcemusTree()),
    CEDRUM(NO_COLOR, new CedrumTree()),
    CERASU(CERASU_COLOR, new CerasuTree()),
    DELNAS(NO_COLOR, new DelnasTree()),
    EWCALY(NO_COLOR, new EwcalyTree(), PotionHelper.sugarEffect),
    HEKUR(BASIC_COLOR, new HekurTree()),
    KIPARIS(NO_COLOR, new KiparisTree(), PotionHelper.spiderEyeEffect),
    KULIST(KULIST_COLOR, new KulistTree()),
    LATA(BASIC_COLOR, new LataTree()),
    NUCIS(BASIC_COLOR, new NucisTree()),
    PORFFOR(NO_COLOR, new PorfforTree()),
    SALYX(NO_COLOR, new SalyxTree()),
    TUOPA(BASIC_COLOR, new TuopaTree());

    private final AbstractTree treeGen;
    private final Colorizer colorizer;
    private final String potionEffect;

    private int leavesMeta;
    private int logMeta;
    private int planksMeta;
    private int saplingMeta;
    private int slabMetadata;

    private SlabBlock doubleSlabBlock = null;
    private LeavesBlock leavesBlock = null;
    private LogBlock logBlock = null;
    private WoodBlock planksBlock = null;
    private SaplingBlock saplingBlock = null;
    private SlabBlock singleSlabBlock = null;
    private StairsBlock stairsBlock = null;

    static
    {
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
            tree.treeGen.setTree(tree);
    }

    OverworldTreeSpecies(Colorizer colorizer, AbstractTree treeGen, String potionEffect)
    {
        this.colorizer = colorizer;
        this.treeGen = treeGen;
        this.potionEffect = potionEffect;
    }

    OverworldTreeSpecies(Colorizer colorizer, AbstractTree treeGen)
    {
        this(colorizer, treeGen, null);
    }

    @Override
    public String getPotionEffect() { return potionEffect; }

    @Override
    @SideOnly(Side.CLIENT)
    public int getLeavesInventoryColor()
    {
        switch (colorizer)
        {
            case NO_COLOR:
                return 0xffffff;
            case ACEMUS_COLOR:
                return AcemusColorizer.getInventoryColor();
            case CERASU_COLOR:
                return CerasuColorizer.getInventoryColor();
            case KULIST_COLOR:
                return KulistColorizer.getInventoryColor();
            default:
                return Blocks.leaves.getRenderColor(0);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getLeavesColor(IBlockAccess blockAccess, int x, int y, int z)
    {
        switch (colorizer)
        {
            case NO_COLOR:
                return 0xffffff;
            case ACEMUS_COLOR:
                return AcemusColorizer.getColor(x, z);
            case CERASU_COLOR:
                return CerasuColorizer.getColor(x, y, z);
            case KULIST_COLOR:
                return KulistColorizer.getColor(x, y, z);
            default:
                return Blocks.leaves.colorMultiplier(blockAccess, x, y, z);
        }
    }

    @Override
    public LeavesBlock getLeavesBlock()
    {
        checkState(leavesBlock != null);
        return leavesBlock;
    }

    @Override
    public void setLeavesBlock(LeavesBlock leavesBlock)
    {
        checkState(this.leavesBlock == null);
        this.leavesBlock = leavesBlock;
    }

    @Override
    public int getLeavesMeta() { return leavesMeta; }

    @Override
    public void setLeavesMeta(int leavesMeta) { this.leavesMeta = leavesMeta; }

    @Override
    public LogBlock getLogBlock()
    {
        checkState(logBlock != null);
        return logBlock;
    }

    @Override
    public void setLogBlock(LogBlock logBlock)
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
    public WoodBlock getPlanksBlock()
    {
        checkState(planksBlock != null);
        return planksBlock;
    }

    @Override
    public void setPlanksBlock(WoodBlock planksBlock)
    {
        checkState(this.planksBlock == null);
        this.planksBlock = planksBlock;
    }

    @Override
    public int getPlanksMeta() { return planksMeta; }

    @Override
    public void setPlanksMeta(int planksMeta) { this.planksMeta = planksMeta; }

    @Override
    public SlabBlock getSingleSlabBlock()
    {
        checkState(singleSlabBlock != null);
        return singleSlabBlock;
    }

    @Override
    public int getSlabMeta() { return slabMetadata; }

    @Override
    public void setSlabMeta(int slabMetadata) { this.slabMetadata = slabMetadata; }

    @Override
    public StairsBlock getStairsBlock()
    {
        checkState(stairsBlock != null);
        return stairsBlock;
    }

    @Override
    public void setStairsBlock(StairsBlock stairsBlock)
    {
        checkState(this.stairsBlock == null);
        this.stairsBlock = stairsBlock;
    }

    @Override
    public SaplingBlock getSaplingBlock()
    {
        checkState(saplingBlock != null);
        return saplingBlock;
    }

    @Override
    public void setSaplingBlock(SaplingBlock saplingBlock)
    {
        checkState(this.saplingBlock == null);
        this.saplingBlock = saplingBlock;
    }

    @Override
    public int getSaplingMeta() { return saplingMeta; }

    @Override
    public void setSaplingMeta(int saplingMeta) { this.saplingMeta = saplingMeta; }

    @Override
    public void setSlabBlock(SlabBlock block, boolean isDouble)
    {
        checkState(isDouble ? doubleSlabBlock == null : singleSlabBlock == null);
        if (isDouble) doubleSlabBlock = block;
        else singleSlabBlock = block;
    }

    @Override
    public AbstractTree getTreeGen() { return treeGen; }

    public enum Colorizer
    {
        ACEMUS_COLOR,
        BASIC_COLOR,
        CERASU_COLOR,
        KULIST_COLOR,
        NO_COLOR
    }
}
