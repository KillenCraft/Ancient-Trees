package com.scottkillen.mod.dendrology.compat.mfr;

import com.google.common.base.Objects;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Interface(iface = "powercrystals.minefactoryreloaded.api.IFactoryHarvestable", modid = MineFactoryReloadedMod.MOD_ID)
public final class MFRWood implements IFactoryHarvestable
{
    private final Block woodBlock;

    public MFRWood(BlockLog woodBlock) { this.woodBlock = woodBlock; }

    @Override
    public Block getPlant() { return woodBlock; }

    @Override
    @Method(modid = MineFactoryReloadedMod.MOD_ID)
    public HarvestType getHarvestType() { return HarvestType.Tree; }

    @Override
    public boolean breakBlock() { return true; }

    @Override
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z)
    {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(World world, Random unused, Map<String, Boolean> unused1, int x, int y, int z)
    {
        return world.getBlock(x, y, z).getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
    }

    @Override
    public void preHarvest(World world, int x, int y, int z) { }

    @SuppressWarnings("MethodWithMultipleLoops")
    @Override
    public void postHarvest(World world, int x, int y, int z)
    {
        MineFactoryReloadedMod.notifyNeighbors(world, x, y, z, woodBlock);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("woodBlock", woodBlock).toString();
    }
}
