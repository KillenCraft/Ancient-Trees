package com.scottkillen.mod.dendrology.compat.mfr;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Interface(iface = "powercrystals.minefactoryreloaded.api.IFactoryHarvestable", modid = MineFactoryReloadedMod.MOD_ID)
public final class MFRLeaves implements IFactoryHarvestable
{
    private final Block leavesBlock;

    public MFRLeaves(BlockLeaves leavesBlock) { this.leavesBlock = leavesBlock; }

    @Override
    public Block getPlant() { return leavesBlock; }

    @Override
    @Method(modid = MineFactoryReloadedMod.MOD_ID)
    public HarvestType getHarvestType() { return HarvestType.TreeLeaf; }

    @Override
    public boolean breakBlock() { return true; }

    @Override
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(World world, Random unused, Map<String, Boolean> harvesterSettings, int x, int y,
                                    int z)
    {
        final Block targetBlock = world.getBlock(x, y, z);
        if (harvesterSettings.get("silkTouch").equals(Boolean.TRUE))
        {
            if (targetBlock instanceof IShearable)
            {
                final ItemStack shears = new ItemStack(Items.shears, 1, 0);
                final IShearable shearable = (IShearable) targetBlock;
                if (shearable.isShearable(shears, world, x, y, z))
                    return shearable.onSheared(shears, world, x, y, z, 0);
            }
            if (Item.getItemFromBlock(targetBlock) != null)
            {
                final List<ItemStack> drops = Lists.newArrayList();
                drops.add(new ItemStack(targetBlock, 1, targetBlock.getDamageValue(world, x, y, z)));
                return drops;
            }
        }
        return targetBlock.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
    }

    @Override
    public void preHarvest(World world, int x, int y, int z) { }

    @SuppressWarnings("MethodWithMultipleLoops")
    @Override
    public void postHarvest(World world, int x, int y, int z)
    {
        MineFactoryReloadedMod.notifyNeighbors(world, x, y, z, leavesBlock);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("leavesBlock", leavesBlock).toString();
    }
}
