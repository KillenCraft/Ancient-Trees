package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import java.util.EnumSet;

import static com.google.common.base.Preconditions.*;
import static net.minecraftforge.common.util.ForgeDirection.UP;

public abstract class AbstractTree extends WorldGenAbstractTree
{
    protected static final ImmutableSet<ForgeDirection> WEST = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.WEST));
    protected static final ImmutableSet<ForgeDirection> EAST = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.EAST));
    protected static final ImmutableSet<ForgeDirection> NORTH = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.NORTH));
    protected static final ImmutableSet<ForgeDirection> SOUTH = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.SOUTH));
    protected static final ImmutableSet<ForgeDirection> SOUTHWEST = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.SOUTH, ForgeDirection.WEST));
    protected static final ImmutableSet<ForgeDirection> NORTHWEST = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.NORTH, ForgeDirection.WEST));
    protected static final ImmutableSet<ForgeDirection> SOUTHEAST = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.SOUTH, ForgeDirection.EAST));
    protected static final ImmutableSet<ForgeDirection> NORTHEAST = ImmutableSet.copyOf(EnumSet.of(ForgeDirection.NORTH, ForgeDirection.EAST));

    protected AbstractTree(boolean isFromSapling)
    {
        super(isFromSapling);
    }

    @SuppressWarnings({ "MethodMayBeStatic", "WeakerAccess" })
    protected boolean canBeReplacedByLog(World world, int x, int y, int z)
    {
        final Block block = world.getBlock(x, y, z);

        return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z);
    }

    protected boolean goodGrowthConditions(World world, int x, int y, int z, int height, IPlantable plantable)
    {
        checkArgument(height > 0);
        if (y < 1 || y + height + 1 > world.getHeight()) return false;
        if (!hasRoomToGrow(world, x, y, z, height)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        return block.canSustainPlant(world, x, y - 1, z, UP, plantable);

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

    protected abstract Block getLeavesBlock();

    protected abstract int getLeavesMetadata();

    protected abstract Block getLogBlock();

    protected abstract int getLogMetadata();

    protected boolean placeLeaves(World world, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).canBeReplacedByLeaves(world, x, y, z))
        {
            setBlockAndNotifyAdequately(world, x, y, z, getLeavesBlock(), getLeavesMetadata());
            return true;
        }
        return false;
    }

    protected boolean placeLog(World world, int x, int y, int z)
    {
        if (canBeReplacedByLog(world, x, y, z))
        {
            setBlockAndNotifyAdequately(world, x, y, z, getLogBlock(), getLogMetadata());
            return true;
        }
        return false;
    }
}
