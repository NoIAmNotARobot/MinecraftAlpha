package com.noiamnotarobot.minecraftalpha.block.logic;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import com.noiamnotarobot.minecraftalpha.block.AlphaBlockMinecartTrack;

public class AlphaMinecartTrackLogic {

    private final World worldObj;
    private final int trackX;
    private final int trackY;
    private final int trackZ;
    private int trackMetadata;
    private final List<ChunkPosition> connectedTracks;
    final AlphaBlockMinecartTrack minecartTrack;

    public AlphaMinecartTrackLogic(AlphaBlockMinecartTrack var1, World var2, int var3, int var4, int var5) {
        this.minecartTrack = var1;
        this.connectedTracks = new ArrayList<>();
        this.worldObj = var2;
        this.trackX = var3;
        this.trackY = var4;
        this.trackZ = var5;
        this.trackMetadata = var2.getBlockMetadata(var3, var4, var5);
        this.calculateConnectedTracks();
    }

    private void calculateConnectedTracks() {
        this.connectedTracks.clear();
        if (this.trackMetadata == 0) {
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ - 1));
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ + 1));
        } else if (this.trackMetadata == 1) {
            this.connectedTracks.add(new ChunkPosition(this.trackX - 1, this.trackY, this.trackZ));
            this.connectedTracks.add(new ChunkPosition(this.trackX + 1, this.trackY, this.trackZ));
        } else if (this.trackMetadata == 2) {
            this.connectedTracks.add(new ChunkPosition(this.trackX - 1, this.trackY, this.trackZ));
            this.connectedTracks.add(new ChunkPosition(this.trackX + 1, this.trackY + 1, this.trackZ));
        } else if (this.trackMetadata == 3) {
            this.connectedTracks.add(new ChunkPosition(this.trackX - 1, this.trackY + 1, this.trackZ));
            this.connectedTracks.add(new ChunkPosition(this.trackX + 1, this.trackY, this.trackZ));
        } else if (this.trackMetadata == 4) {
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY + 1, this.trackZ - 1));
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ + 1));
        } else if (this.trackMetadata == 5) {
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ - 1));
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY + 1, this.trackZ + 1));
        } else if (this.trackMetadata == 6) {
            this.connectedTracks.add(new ChunkPosition(this.trackX + 1, this.trackY, this.trackZ));
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ + 1));
        } else if (this.trackMetadata == 7) {
            this.connectedTracks.add(new ChunkPosition(this.trackX - 1, this.trackY, this.trackZ));
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ + 1));
        } else if (this.trackMetadata == 8) {
            this.connectedTracks.add(new ChunkPosition(this.trackX - 1, this.trackY, this.trackZ));
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ - 1));
        } else if (this.trackMetadata == 9) {
            this.connectedTracks.add(new ChunkPosition(this.trackX + 1, this.trackY, this.trackZ));
            this.connectedTracks.add(new ChunkPosition(this.trackX, this.trackY, this.trackZ - 1));
        }

    }

    private void refreshConnectedTracks() {
        for (int var1 = 0; var1 < this.connectedTracks.size(); ++var1) {
            AlphaMinecartTrackLogic var2 = this.getMinecartTrackLogic((ChunkPosition) this.connectedTracks.get(var1));
            if (var2 != null && var2.isConnectedTo(this)) {
                this.connectedTracks.set(var1, new ChunkPosition(var2.trackX, var2.trackY, var2.trackZ));
            } else {
                this.connectedTracks.remove(var1--);
            }
        }

    }

    private boolean isMinecartTrack(int var1, int var2, int var3) {
        return this.worldObj.getBlock(var1, var2, var3) == this.minecartTrack
            || (this.worldObj.getBlock(var1, var2 + 1, var3) == this.minecartTrack
                || this.worldObj.getBlock(var1, var2 - 1, var3) == this.minecartTrack);
    }

    private AlphaMinecartTrackLogic getMinecartTrackLogic(ChunkPosition var1) {
        return this.worldObj.getBlock(var1.chunkPosX, var1.chunkPosY, var1.chunkPosZ) == this.minecartTrack
            ? new AlphaMinecartTrackLogic(
                this.minecartTrack,
                this.worldObj,
                var1.chunkPosX,
                var1.chunkPosY,
                var1.chunkPosZ)
            : (this.worldObj.getBlock(var1.chunkPosX, var1.chunkPosY + 1, var1.chunkPosZ) == this.minecartTrack
                ? new AlphaMinecartTrackLogic(
                    this.minecartTrack,
                    this.worldObj,
                    var1.chunkPosX,
                    var1.chunkPosY + 1,
                    var1.chunkPosZ)
                : (this.worldObj.getBlock(var1.chunkPosX, var1.chunkPosY - 1, var1.chunkPosZ) == this.minecartTrack
                    ? new AlphaMinecartTrackLogic(
                        this.minecartTrack,
                        this.worldObj,
                        var1.chunkPosX,
                        var1.chunkPosY - 1,
                        var1.chunkPosZ)
                    : null));
    }

    private boolean isConnectedTo(AlphaMinecartTrackLogic var1) {
        for (ChunkPosition connectedTrack : this.connectedTracks) {
            if (connectedTrack.chunkPosX == var1.trackX && connectedTrack.chunkPosZ == var1.trackZ) {
                return true;
            }
        }

        return false;
    }

    private boolean isInTrack(int var1, int var2, int var3) {
        for (ChunkPosition connectedTrack : this.connectedTracks) {
            if (connectedTrack.chunkPosX == var1 && connectedTrack.chunkPosZ == var3) {
                return true;
            }
        }

        return false;
    }

    private int getAdjacentTracks() {
        int var1 = 0;
        if (this.isMinecartTrack(this.trackX, this.trackY, this.trackZ - 1)) {
            ++var1;
        }

        if (this.isMinecartTrack(this.trackX, this.trackY, this.trackZ + 1)) {
            ++var1;
        }

        if (this.isMinecartTrack(this.trackX - 1, this.trackY, this.trackZ)) {
            ++var1;
        }

        if (this.isMinecartTrack(this.trackX + 1, this.trackY, this.trackZ)) {
            ++var1;
        }

        return var1;
    }

    private boolean canConnectTo(AlphaMinecartTrackLogic var1) {
        if (this.isConnectedTo(var1)) {
            return true;
        } else if (this.connectedTracks.size() == 2) {
            return false;
        } else if (this.connectedTracks.isEmpty()) {
            return true;
        } else {
            return true;
        }
    }

    private void connectToNeighbor(AlphaMinecartTrackLogic var1) {
        this.connectedTracks.add(new ChunkPosition(var1.trackX, var1.trackY, var1.trackZ));
        boolean var2 = this.isInTrack(this.trackX, this.trackY, this.trackZ - 1);
        boolean var3 = this.isInTrack(this.trackX, this.trackY, this.trackZ + 1);
        boolean var4 = this.isInTrack(this.trackX - 1, this.trackY, this.trackZ);
        boolean var5 = this.isInTrack(this.trackX + 1, this.trackY, this.trackZ);
        byte var6 = -1;
        if (var2 || var3) {
            var6 = 0;
        }

        if (var4 || var5) {
            var6 = 1;
        }

        if (var3 && var5 && !var2 && !var4) {
            var6 = 6;
        }

        if (var3 && var4 && !var2 && !var5) {
            var6 = 7;
        }

        if (var2 && var4 && !var3 && !var5) {
            var6 = 8;
        }

        if (var2 && var5 && !var3 && !var4) {
            var6 = 9;
        }

        if (var6 == 0) {
            if (this.worldObj.getBlock(this.trackX, this.trackY + 1, this.trackZ - 1) == this.minecartTrack) {
                var6 = 4;
            }

            if (this.worldObj.getBlock(this.trackX, this.trackY + 1, this.trackZ + 1) == this.minecartTrack) {
                var6 = 5;
            }
        }

        if (var6 == 1) {
            if (this.worldObj.getBlock(this.trackX + 1, this.trackY + 1, this.trackZ) == this.minecartTrack) {
                var6 = 2;
            }

            if (this.worldObj.getBlock(this.trackX - 1, this.trackY + 1, this.trackZ) == this.minecartTrack) {
                var6 = 3;
            }
        }

        if (var6 < 0) {
            var6 = 0;
        }

        this.worldObj.setBlockMetadataWithNotify(this.trackX, this.trackY, this.trackZ, var6, 3);
    }

    private boolean canConnectFrom(int var1, int var2, int var3) {
        AlphaMinecartTrackLogic var4 = this.getMinecartTrackLogic(new ChunkPosition(var1, var2, var3));
        if (var4 == null) {
            return false;
        } else {
            var4.refreshConnectedTracks();
            return var4.canConnectTo(this);
        }
    }

    public void place(boolean var1) {
        boolean var2 = this.canConnectFrom(this.trackX, this.trackY, this.trackZ - 1);
        boolean var3 = this.canConnectFrom(this.trackX, this.trackY, this.trackZ + 1);
        boolean var4 = this.canConnectFrom(this.trackX - 1, this.trackY, this.trackZ);
        boolean var5 = this.canConnectFrom(this.trackX + 1, this.trackY, this.trackZ);
        byte var6 = -1;
        if ((var2 || var3) && !var4 && !var5) {
            var6 = 0;
        }

        if ((var4 || var5) && !var2 && !var3) {
            var6 = 1;
        }

        if (var3 && var5 && !var2 && !var4) {
            var6 = 6;
        }

        if (var3 && var4 && !var2 && !var5) {
            var6 = 7;
        }

        if (var2 && var4 && !var3 && !var5) {
            var6 = 8;
        }

        if (var2 && var5 && !var3 && !var4) {
            var6 = 9;
        }

        if (var6 == -1) {
            if (var2 || var3) {
                var6 = 0;
            }

            if (var4 || var5) {
                var6 = 1;
            }

            if (var1) {
                if (var3 && var5) {
                    var6 = 6;
                }

                if (var4 && var3) {
                    var6 = 7;
                }

                if (var5 && var2) {
                    var6 = 9;
                }

                if (var2 && var4) {
                    var6 = 8;
                }
            } else {
                if (var2 && var4) {
                    var6 = 8;
                }

                if (var5 && var2) {
                    var6 = 9;
                }

                if (var4 && var3) {
                    var6 = 7;
                }

                if (var3 && var5) {
                    var6 = 6;
                }
            }
        }

        if (var6 == 0) {
            if (this.worldObj.getBlock(this.trackX, this.trackY + 1, this.trackZ - 1) == this.minecartTrack) {
                var6 = 4;
            }

            if (this.worldObj.getBlock(this.trackX, this.trackY + 1, this.trackZ + 1) == this.minecartTrack) {
                var6 = 5;
            }
        }

        if (var6 == 1) {
            if (this.worldObj.getBlock(this.trackX + 1, this.trackY + 1, this.trackZ) == this.minecartTrack) {
                var6 = 2;
            }

            if (this.worldObj.getBlock(this.trackX - 1, this.trackY + 1, this.trackZ) == this.minecartTrack) {
                var6 = 3;
            }
        }

        if (var6 < 0) {
            var6 = 0;
        }

        this.trackMetadata = var6;
        this.calculateConnectedTracks();
        this.worldObj.setBlockMetadataWithNotify(this.trackX, this.trackY, this.trackZ, var6, 3);

        for (ChunkPosition connectedTrack : this.connectedTracks) {
            AlphaMinecartTrackLogic var8 = this.getMinecartTrackLogic(connectedTrack);
            if (var8 != null) {
                var8.refreshConnectedTracks();
                if (var8.canConnectTo(this)) {
                    var8.connectToNeighbor(this);
                }
            }
        }

    }

    public static int getNAdjacentTracks(AlphaMinecartTrackLogic var0) {
        return var0.getAdjacentTracks();
    }
}
