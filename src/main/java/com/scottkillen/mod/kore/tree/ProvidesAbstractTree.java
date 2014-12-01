package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import com.scottkillen.mod.kore.common.Named;

public interface ProvidesAbstractTree extends Named
{
    AbstractTree getTreeGen();
}
