package com.cricketcraft.chisel.block;

import com.cricketcraft.chisel.api.ICarvable;
import com.cricketcraft.chisel.carving.CarvableHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCarvable extends Block implements ICarvable
{
    public CarvableHelper carverHelper;

    public BlockCarvable(Material material)
    {
        super(material);
    }
}
