package com.scottkillen.mod.dendrology.compat.mfr;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import cpw.mods.fml.common.Optional.Interface;
import cpw.mods.fml.common.Optional.InterfaceList;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FertilizerType;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;
import java.util.Random;

@InterfaceList({
        @Interface(iface = "powercrystals.minefactoryreloaded.api.IFactoryFertilizable", modid = MineFactoryReloadedMod.MOD_ID),
        @Interface(iface = "powercrystals.minefactoryreloaded.api.IFactoryPlantable", modid = MineFactoryReloadedMod.MOD_ID)
})
public final class MFRSapling implements IFactoryPlantable, IFactoryFertilizable
{
    private final Item saplingItem;
    private final BlockSapling sapling;
    private final Object plantedSapling;

    public MFRSapling(BlockSapling saplingBlock)
    {
        saplingItem = Item.getItemFromBlock(saplingBlock);
        sapling = saplingBlock;
        plantedSapling = new ReplacementBlock(saplingBlock).setMeta(true);
    }

    @Override
    public Item getSeed() { return saplingItem; }

    @Override
    public boolean canBePlanted(ItemStack stack, boolean forFermenting) { return true; }

    @Override
    @Method(modid = MineFactoryReloadedMod.MOD_ID)
    public ReplacementBlock getPlantedBlock(World world, int x, int y, int z, ItemStack stack)
    {
        return (ReplacementBlock) plantedSapling;
    }

    @Override
    public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack)
    {
        return sapling.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public void prePlant(World world, int x, int y, int z, ItemStack stack) { }

    @Override
    public void postPlant(World world, int x, int y, int z, ItemStack stack) { }

    @Override
    public Block getPlant() { return sapling; }

    @Override
    @Method(modid = MineFactoryReloadedMod.MOD_ID)
    public boolean canFertilize(World world, int x, int y, int z, FertilizerType fertilizerType) { return true; }

    @Override
    @Method(modid = MineFactoryReloadedMod.MOD_ID)
    public boolean fertilize(World world, Random rand, int x, int y, int z, FertilizerType fertilizerType)
    {
        final Block block = world.getBlock(x, y, z);
        if (block instanceof ModSaplingBlock)
        {
            final ModSaplingBlock saplingBlock = (ModSaplingBlock) block;
            saplingBlock.func_149878_d(world, x, y, z, world.rand);
            return !world.getBlock(x, y, z).equals(block);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("saplingItem", saplingItem).add("sapling", sapling)
                .add("plantedSapling", plantedSapling).toString();
    }
}
