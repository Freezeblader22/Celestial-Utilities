package com.freezeblader22.celestialutilities.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ModItemGroup {

    public static final ItemGroup CELESTIAL_UTILITIES = new ItemGroup("celestialUtilitiesTab")
    {
    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(Items.BARRIER);
    }

    };
}
