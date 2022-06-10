package com.freezeblader22.celestialutilities.container;

import com.freezeblader22.celestialutilities.CelestialUtilities;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, CelestialUtilities.MOD_ID);


public static final RegistryObject<ContainerType<ResearchTableContainer>> RESEARCH_TABLE_CONTAINER = CONTAINERS.register("research_table_container", () -> IForgeContainerType.create(((windowId, inv, data) -> {
    BlockPos pos = data.readBlockPos();
    World world = inv.player.getEntityWorld();
    return new ResearchTableContainer(windowId, world, pos, inv, inv.player);
})));


public static void register(IEventBus eventBus) {
    CONTAINERS.register(eventBus);
}
}
