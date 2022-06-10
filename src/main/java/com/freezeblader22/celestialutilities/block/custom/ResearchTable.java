package com.freezeblader22.celestialutilities.block.custom;

import com.freezeblader22.celestialutilities.container.ResearchTableContainer;
import com.freezeblader22.celestialutilities.tileentity.ModTileEntities;
import com.freezeblader22.celestialutilities.tileentity.ResearchTableTile;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class ResearchTable extends Block {

private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

private static final VoxelShape SHAPE_N = Stream.of(
        Block.makeCuboidShape(0, 1, 0, 16, 10, 16),
        Block.makeCuboidShape(10, 10, 10, 13, 15, 13),
        Block.makeCuboidShape(13, 15, 10, 14, 17, 11),
        Block.makeCuboidShape(13, 17, 10.5, 14, 19, 10.5),
        Block.makeCuboidShape(12, 17, 10.5, 13, 18, 10.5),
        Block.makeCuboidShape(11, 18, 10.5, 13, 20, 10.5),
        Block.makeCuboidShape(9, 19, 10.5, 11, 20, 10.5),
        Block.makeCuboidShape(9, 20, 10.5, 12, 21, 10.5),
        Block.makeCuboidShape(0, 10, 8, 6, 11, 17),
        Block.makeCuboidShape(16, 10, 1, 17, 11, 8),
        Block.makeCuboidShape(0, 10, 15, 16, 13, 16),
        Block.makeCuboidShape(9, 10, 9, 14, 11, 14),
        Block.makeCuboidShape(-1, 0, -1, 17, 1, 17),
        Block.makeCuboidShape(1, 13, 15, 15, 14, 16)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

private static final VoxelShape SHAPE_E = Stream.of(
        Block.makeCuboidShape(0, 1, 0, 16, 10, 16),
        Block.makeCuboidShape(3, 10, 10, 6, 15, 13),
        Block.makeCuboidShape(5, 15, 13, 6, 17, 14),
        Block.makeCuboidShape(5.5, 17, 13, 5.5, 19, 14),
        Block.makeCuboidShape(5.5, 17, 12, 5.5, 18, 13),
        Block.makeCuboidShape(5.5, 18, 11, 5.5, 20, 13),
        Block.makeCuboidShape(5.5, 19, 9, 5.5, 20, 11),
        Block.makeCuboidShape(5.5, 20, 9, 5.5, 21, 12),
        Block.makeCuboidShape(-1, 10, 0, 8, 11, 6),
        Block.makeCuboidShape(8, 10, 16, 15, 11, 17),
        Block.makeCuboidShape(0, 10, 0, 1, 13, 16),
        Block.makeCuboidShape(-1, 0, -1, 17, 1, 17),
        Block.makeCuboidShape(2, 10, 9, 7, 11, 14),
        Block.makeCuboidShape(0, 13, 1, 1, 14, 15)
                ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

private static final VoxelShape SHAPE_S = Stream.of(
        Block.makeCuboidShape(0, 1, 0, 16, 10, 16),
        Block.makeCuboidShape(3, 10, 3, 6, 15, 6),
        Block.makeCuboidShape(2, 15, 5, 3, 17, 6),
        Block.makeCuboidShape(2, 17, 5.5, 3, 19, 5.5),
        Block.makeCuboidShape(3, 17, 5.5, 4, 18, 5.5),
        Block.makeCuboidShape(3, 18, 5.5, 5, 20, 5.5),
        Block.makeCuboidShape(5, 19, 5.5, 7, 20, 5.5),
        Block.makeCuboidShape(4, 20, 5.5, 7, 21, 5.5),
        Block.makeCuboidShape(10, 10, -1, 16, 11, 8),
        Block.makeCuboidShape(-1, 10, 8, 0, 11, 15),
        Block.makeCuboidShape(0, 10, 0, 16, 13, 1),
        Block.makeCuboidShape(-1, 0, -1, 17, 1, 17),
        Block.makeCuboidShape(2, 10, 2, 7, 11, 7),
        Block.makeCuboidShape(1, 13, 0, 15, 14, 1)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

private static final VoxelShape SHAPE_W = Stream.of(
        Block.makeCuboidShape(0, 1, 0, 16, 10, 16),
        Block.makeCuboidShape(-1, 0, -1, 17, 1, 17),
        Block.makeCuboidShape(10, 10, 3, 13, 15, 6),
        Block.makeCuboidShape(10, 15, 2, 11, 17, 3),
        Block.makeCuboidShape(10.5, 17, 2, 10.5, 19, 3),
        Block.makeCuboidShape(10.5, 17, 3, 10.5, 18, 4),
        Block.makeCuboidShape(10.5, 18, 3, 10.5, 20, 5),
        Block.makeCuboidShape(10.5, 19, 5, 10.5, 20, 7),
        Block.makeCuboidShape(10.5, 20, 4, 10.5, 21, 7),
        Block.makeCuboidShape(8, 10, 10, 17, 11, 16),
        Block.makeCuboidShape(1, 10, -1, 8, 11, 0),
        Block.makeCuboidShape(15, 10, 0, 16, 13, 16),
        Block.makeCuboidShape(9, 10, 2, 14, 11, 7),
        Block.makeCuboidShape(15, 13, 1, 16, 14, 15)
).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public ResearchTable() {
        super(AbstractBlock.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.5f, 4.0f)
                .sound(SoundType.WOOD)
                .harvestLevel(0)
                .harvestTool(ToolType.AXE));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                    return SHAPE_N;
            case EAST:
                    return SHAPE_E;
            case SOUTH:
                    return SHAPE_S;
            case WEST:
                    return SHAPE_W;
            default:
                    return SHAPE_N;
        }
    }




    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

   @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if(!player.isCrouching()) {
                if(tileEntity instanceof ResearchTableTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);
                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Our container provider is missing!");
                }
            } else {
                if(tileEntity instanceof ResearchTableTile) {
                    if(worldIn.isThundering()) {
                        EntityType.LIGHTNING_BOLT.spawn(((ServerWorld) worldIn), null, player, pos, SpawnReason.TRIGGERED, true, true);

                        ((ResearchTableTile)tileEntity).lightningHasStruck();
                    }
                }
            }

        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.celestialutilities.research_table");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new ResearchTableContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.RESEARCH_TABLE_TILE.get().create();
    }



    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}

