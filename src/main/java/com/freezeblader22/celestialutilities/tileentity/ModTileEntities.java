package com.freezeblader22.celestialutilities.tileentity;

import com.freezeblader22.celestialutilities.block.ModBlocks;
import com.freezeblader22.celestialutilities.CelestialUtilities;
import com.freezeblader22.celestialutilities.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, CelestialUtilities.MOD_ID);

    public static RegistryObject<TileEntityType<ResearchTableTile>> RESEARCH_TABLE_TILE = TILE_ENTITIES.register("research_table_tile", () -> TileEntityType.Builder.create(
            ResearchTableTile::new, ModBlocks.RESEARCH_TABLE.get()).build(null));

    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }

}
