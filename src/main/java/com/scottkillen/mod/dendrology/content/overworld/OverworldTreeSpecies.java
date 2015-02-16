package com.scottkillen.mod.dendrology.content.overworld;


import com.scottkillen.mod.dendrology.content.ProvidesPotionEffect;
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
import com.scottkillen.mod.koresample.common.block.SlabBlock;
import com.scottkillen.mod.koresample.common.block.StairsBlock;
import com.scottkillen.mod.koresample.tree.DefinesLeaves;
import com.scottkillen.mod.koresample.tree.DefinesLog;
import com.scottkillen.mod.koresample.tree.DefinesSapling;
import com.scottkillen.mod.koresample.tree.DefinesSlab;
import com.scottkillen.mod.koresample.tree.DefinesStairs;
import com.scottkillen.mod.koresample.tree.DefinesTree;
import com.scottkillen.mod.koresample.tree.DefinesWood;
import com.scottkillen.mod.koresample.tree.block.LeavesBlock;
import com.scottkillen.mod.koresample.tree.block.LogBlock;
import com.scottkillen.mod.koresample.tree.block.SaplingBlock;
import com.scottkillen.mod.koresample.tree.block.WoodBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.gen.feature.WorldGenerator;

import static com.google.common.base.Preconditions.*;
import static com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies.Colorizer.ACEMUS_COLOR;
import static com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies.Colorizer.BASIC_COLOR;
import static com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies.Colorizer.CERASU_COLOR;
import static com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies.Colorizer.KULIST_COLOR;
import static com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies.Colorizer.NO_COLOR;

@SuppressWarnings({ "NonSerializableFieldInSerializableClass", "ClassHasNoToStringMethod" })
public enum OverworldTreeSpecies
        implements DefinesLeaves, DefinesLog, DefinesSapling, DefinesSlab, DefinesStairs, DefinesTree, DefinesWood,
        ProvidesPotionEffect
{
    // REORDERING WILL CAUSE DAMAGE TO SAVES
    ACEMUS(ACEMUS_COLOR, new AcemusTree(), new AcemusTree(false)),
    CEDRUM(NO_COLOR, new CedrumTree(), new CedrumTree(false)),
    CERASU(CERASU_COLOR, new CerasuTree(), new CerasuTree(false)),
    DELNAS(NO_COLOR, new DelnasTree(), new DelnasTree(false)),
    EWCALY(NO_COLOR, new EwcalyTree(), new EwcalyTree(false), PotionHelper.sugarEffect),
    HEKUR(BASIC_COLOR, new HekurTree(), new HekurTree(false)),
    KIPARIS(NO_COLOR, new KiparisTree(), new KiparisTree(false), PotionHelper.spiderEyeEffect),
    KULIST(KULIST_COLOR, new KulistTree(), new KulistTree(false)),
    LATA(BASIC_COLOR, new LataTree(), new LataTree(false)),
    NUCIS(BASIC_COLOR, new NucisTree(), new NucisTree(false)),
    PORFFOR(NO_COLOR, new PorfforTree(), new PorfforTree(false)),
    SALYX(NO_COLOR, new SalyxTree(), new SalyxTree(false)),
    TUOPA(BASIC_COLOR, new TuopaTree(), new TuopaTree(false));

    private final AbstractTree saplingTreeGen;
    private final AbstractTree worldTreeGen;
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
    private WoodBlock woodBlock = null;
    private SaplingBlock saplingBlock = null;
    private SlabBlock singleSlabBlock = null;
    private StairsBlock stairsBlock = null;

    static
    {
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            tree.saplingTreeGen.setTree(tree);
            tree.worldTreeGen.setTree(tree);
        }
    }

    OverworldTreeSpecies(Colorizer colorizer, AbstractTree saplingTreeGen, AbstractTree worldTreeGen,
                         String potionEffect)
    {
        this.colorizer = colorizer;
        this.saplingTreeGen = saplingTreeGen;
        this.worldTreeGen = worldTreeGen;
        this.potionEffect = potionEffect;
    }

    OverworldTreeSpecies(Colorizer colorizer, AbstractTree saplingTreeGen, AbstractTree worldTreeGen)
    {
        this(colorizer, saplingTreeGen, worldTreeGen, null);
    }

    @Override
    public String potionEffect() { return potionEffect; }

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
    public void assignLeavesBlock(LeavesBlock leavesBlock)
    {
        checkState(this.leavesBlock == null);
        this.leavesBlock = leavesBlock;
    }

    @Override
    public void assignLeavesSubBlockIndex(int leavesMeta) { this.leavesMeta = leavesMeta; }

    @Override
    public LeavesBlock leavesBlock()
    {
        checkState(leavesBlock != null);
        return leavesBlock;
    }

    @Override
    public int leavesSubBlockIndex() { return leavesMeta; }

    @SuppressWarnings("ReturnOfThis")
    @Override
    public DefinesSapling saplingDefinition() { return this; }

    @Override
    public String speciesName() { return name().toLowerCase(); }

    @Override
    public void assignLogBlock(LogBlock logBlock)
    {
        checkState(this.logBlock == null);
        this.logBlock = logBlock;
    }

    @Override
    public void assignLogSubBlockIndex(int logMeta) { this.logMeta = logMeta; }

    @Override
    public LogBlock logBlock()
    {
        checkState(logBlock != null);
        return logBlock;
    }

    @Override
    public int logSubBlockIndex() { return logMeta; }

    @Override
    public WoodBlock woodBlock()
    {
        checkState(woodBlock != null);
        return woodBlock;
    }

    @Override
    public int woodSubBlockIndex() { return planksMeta; }

    @Override
    public void assignWoodBlock(WoodBlock woodBlock)
    {
        checkState(this.woodBlock == null);
        this.woodBlock = woodBlock;
    }

    @Override
    public void assignWoodSubBlockIndex(int planksMeta) { this.planksMeta = planksMeta; }

    @Override
    public void assignStairsBlock(StairsBlock stairsBlock)
    {
        checkState(this.stairsBlock == null);
        this.stairsBlock = stairsBlock;
    }

    @Override
    public StairsBlock stairsBlock()
    {
        checkState(stairsBlock != null);
        return stairsBlock;
    }

    @Override
    public Block stairsModelBlock() { return woodBlock(); }

    @Override
    public int stairsModelSubBlockIndex() { return woodSubBlockIndex(); }

    @Override
    public String stairsName() { return speciesName(); }

    @Override
    public void assignSaplingBlock(SaplingBlock saplingBlock)
    {
        checkState(this.saplingBlock == null);
        this.saplingBlock = saplingBlock;
    }

    @Override
    public void assignSaplingSubBlockIndex(int saplingMeta) { this.saplingMeta = saplingMeta; }

    @Override
    public SaplingBlock saplingBlock()
    {
        checkState(saplingBlock != null);
        return saplingBlock;
    }

    @Override
    public int saplingSubBlockIndex() { return saplingMeta; }

    @Override
    @Deprecated
    public WorldGenerator treeGenerator() { return saplingTreeGen; }

    @Override
    public WorldGenerator saplingTreeGenerator() { return saplingTreeGen; }

    @Override
    public WorldGenerator worldTreeGenerator() { return worldTreeGen; }

    @Override
    public void assignDoubleSlabBlock(SlabBlock block)
    {
        checkState(doubleSlabBlock == null);
        doubleSlabBlock = block;
    }

    @Override
    public void assignSingleSlabBlock(SlabBlock block)
    {
        checkState(singleSlabBlock == null);
        singleSlabBlock = block;
    }

    @Override
    public void assignSlabSubBlockIndex(int slabMetadata) { this.slabMetadata = slabMetadata; }

    @Override
    public SlabBlock doubleSlabBlock()
    {
        checkState(doubleSlabBlock != null);
        return doubleSlabBlock;
    }

    @Override
    public SlabBlock singleSlabBlock()
    {
        checkState(singleSlabBlock != null);
        return singleSlabBlock;
    }

    @Override
    public int slabSubBlockIndex() { return slabMetadata; }

    @Override
    public Block slabModelBlock() { return woodBlock(); }

    @Override
    public int slabModelSubBlockIndex() { return woodSubBlockIndex(); }

    @Override
    public String slabName() { return speciesName(); }

    public enum Colorizer
    {
        ACEMUS_COLOR,
        BASIC_COLOR,
        CERASU_COLOR,
        KULIST_COLOR,
        NO_COLOR
    }
}
