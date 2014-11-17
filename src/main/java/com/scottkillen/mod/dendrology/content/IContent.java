package com.scottkillen.mod.dendrology.content;

import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer;
import com.scottkillen.mod.dendrology.block.ModLogBlock;
import com.scottkillen.mod.dendrology.block.ModPlanksBlock;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import com.scottkillen.mod.dendrology.block.ModStairsBlock;
import com.scottkillen.mod.dendrology.block.ModWoodSlabBlock;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;

public interface IContent
{
    Colorizer getColorizer();

    ModLeavesBlock getLeavesBlock();

    void setLeavesBlock(ModLeavesBlock block);

    int getLeavesMeta();

    void setLeavesMeta(int meta);

    ModLogBlock getLogBlock();

    void setLogBlock(ModLogBlock logBlock);

    int getLogMeta();

    void setLogMeta(int meta);

    String getName();

    ModPlanksBlock getPlanksBlock();

    void setPlanksBlock(ModPlanksBlock block);

    int getPlanksMeta();

    void setPlanksMeta(int meta);

    ModSaplingBlock getSaplingBlock();

    void setSaplingBlock(ModSaplingBlock block);

    int getSaplingMeta();

    void setSaplingMeta(int meta);

    ModWoodSlabBlock getSingleSlabBlock();

    void setSlabBlock(ModWoodSlabBlock block, boolean isDouble);

    int getSlabMeta();

    void setSlabMeta(int meta);

    ModStairsBlock getStairsBlock();

    void setStairsBlock(ModStairsBlock block);

    AbstractTree getTreeGen();
}
