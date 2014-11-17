package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import com.scottkillen.mod.dendrology.block.ModLogBlock;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import com.scottkillen.mod.dendrology.content.IContent;
import com.scottkillen.mod.dendrology.content.OverworldSpecies;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import org.apache.commons.lang3.tuple.ImmutablePair;

import static com.google.common.base.Preconditions.*;
import static net.minecraftforge.common.util.ForgeDirection.UP;

public abstract class AbstractTree extends WorldGenAbstractTree
{
    protected static final ImmutableList<ImmutablePair<Integer, Integer>> BRANCH_DIRECTIONS = ImmutableList
            .of(ImmutablePair.of(-1, 0), ImmutablePair.of(1, 0), ImmutablePair.of(0, -1), ImmutablePair.of(0, 1),
                    ImmutablePair.of(-1, 1), ImmutablePair.of(-1, -1), ImmutablePair.of(1, 1), ImmutablePair.of(1, -1));
    private IContent tree = null;

    protected AbstractTree() { super(true); }

    @SuppressWarnings("WeakerAccess")
    protected boolean canBeReplacedByLog(World world, int x, int y, int z)
    {
        final Block block = world.getBlock(x, y, z);

        return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z);
    }

    public void setTree(OverworldSpecies tree)
    {
        this.tree = tree;
    }

    protected boolean isPoorGrowthConditions(World world, int x, int y, int z, int height, IPlantable plantable)
    {
        checkArgument(height > 0);
        if (y < 1 || y + height + 1 > world.getHeight()) return true;
        if (!hasRoomToGrow(world, x, y, z, height)) return true;

        final Block block = world.getBlock(x, y - 1, z);
        return !block.canSustainPlant(world, x, y - 1, z, UP, plantable);

    }

    @SuppressWarnings("WeakerAccess")
    protected boolean hasRoomToGrow(World world, int x, int y, int z, int height)
    {
        for (int y1 = y; y1 <= y + 1 + height; ++y1)
        {
            if (!isReplaceable(world, x, y1, z))
            {
                return false;
            }
        }
        return true;
    }

    ModLeavesBlock getLeavesBlock() { return tree.getLeavesBlock(); }

    int getLeavesMetadata() { return tree.getLeavesMeta(); }

    protected ModLogBlock getLogBlock() { return tree.getLogBlock(); }

    protected int getLogMetadata() { return tree.getLogMeta(); }

    protected ModSaplingBlock getSaplingBlock() { return tree.getSaplingBlock(); }

    @SuppressWarnings("UnusedReturnValue")
    protected boolean placeLeaves(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).canBeReplacedByLeaves(world, x, y, z))
        {
            setBlockAndNotifyAdequately(world, x, y, z, getLeavesBlock(), getLeavesMetadata());
            return true;
        }
        return false;
    }

    @SuppressWarnings("UnusedReturnValue")
    protected boolean placeLog(World world, int x, int y, int z)
    {
        if (canBeReplacedByLog(world, x, y, z))
        {
            setBlockAndNotifyAdequately(world, x, y, z, getLogBlock(), getLogMetadata());
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("tree", tree).toString();
    }
}
