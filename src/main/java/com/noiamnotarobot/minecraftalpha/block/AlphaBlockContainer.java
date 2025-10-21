package com.noiamnotarobot.minecraftalpha.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class AlphaBlockContainer extends AlphaBlock implements ITileEntityProvider {

    protected AlphaBlockContainer(Material materialIn, String name) {
        this(materialIn, name, true);
    }

    protected AlphaBlockContainer(Material materialIn, String name, boolean addToCreativeMenu) {
        super(materialIn, name, addToCreativeMenu);
        this.isBlockContainer = true;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        world.setTileEntity(x, y, z, createNewTileEntity(world, world.getBlockMetadata(x, y, z)));
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(world, x, y, z, meta);
        world.removeTileEntity(x, y, z);
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventData) {
        super.onBlockEventReceived(world, x, y, z, eventId, eventData);
        TileEntity entity = world.getTileEntity(x, y, z);
        return entity != null && entity.receiveClientEvent(eventId, eventData);
    }
}
