package com.freezeblader22.celestialutilities.item;

import com.freezeblader22.celestialutilities.CelestialUtilities;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CelestialUtilities.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



}
