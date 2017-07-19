/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.EnumPlantType;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockBloodRose
/*     */   extends BlockBaseContainer
/*     */   implements IPlantable
/*     */ {
/*  37 */   private final float RADIUS = 0.2F;
/*     */   
/*     */   public BlockBloodRose() {
/*  40 */     super(Material.field_151585_k, TileEntityBloodRose.class);
/*     */     
/*  42 */     func_149711_c(0.0F);
/*  43 */     func_149672_a(field_149779_h);
/*     */     
/*  45 */     func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  55 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  60 */     return 1;
/*     */   }
/*     */   
/*     */   public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  65 */     return EnumPlantType.Plains;
/*     */   }
/*     */   
/*     */   public Block getPlant(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  70 */     return this;
/*     */   }
/*     */   
/*     */   public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  75 */     return world.func_72805_g(x, y, z);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4)
/*     */   {
/*  86 */     float f = 0.0625F;
/*  87 */     return AxisAlignedBB.func_72330_a(par2 + 0.5F - 0.2F + 0.0625F, par3, par4 + 0.5F - 0.2F + 0.0625F, par2 + 0.5F + 0.2F - 0.0625F, par3 + 0.6F - 0.0625F, par4 + 0.5F + 0.2F - 0.0625F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*     */   {
/*  93 */     if ((!world.field_72995_K) && ((entity instanceof EntityPlayer))) {
/*  94 */       TileEntity tileentity = world.func_147438_o(posX, posY, posZ);
/*  95 */       if ((tileentity != null) && ((tileentity instanceof TileEntityBloodRose))) {
/*  96 */         TileEntityBloodRose chest = (TileEntityBloodRose)tileentity;
/*  97 */         chest.storePlayer((EntityPlayer)entity);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int func_149692_a(int par1)
/*     */   {
/* 105 */     return 0;
/*     */   }
/*     */   
/*     */   public Item func_149650_a(int par1, Random rand, int fortune)
/*     */   {
/* 110 */     return null;
/*     */   }
/*     */   
/*     */   public TileEntity func_149915_a(World world, int metadata)
/*     */   {
/* 115 */     TileEntityBloodRose tileentitychest = new TileEntityBloodRose();
/* 116 */     return tileentitychest;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/* 122 */     this.field_149761_L = par1IconRegister.func_94245_a(func_149641_N());
/* 123 */     this.fullIcon = par1IconRegister.func_94245_a(func_149641_N() + "_full");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/* 129 */     if (meta == 0) {
/* 130 */       return super.func_149691_a(side, meta);
/*     */     }
/* 132 */     return this.fullIcon;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon fullIcon;
/*     */   public static class TileEntityBloodRose
/*     */     extends TileEntity
/*     */   {
/*     */     private String customName;
/* 142 */     public ArrayList<String> players = new ArrayList();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean canUpdate()
/*     */     {
/* 149 */       return false;
/*     */     }
/*     */     
/*     */     public void sync() {
/* 153 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public void storePlayer(EntityPlayer player) {
/* 157 */       if ((!this.field_145850_b.field_72995_K) && 
/* 158 */         (player != null)) {
/* 159 */         if (this.players.size() == 0) {
/* 160 */           this.players.add(player.func_70005_c_());
/*     */         } else {
/* 162 */           this.players.set(0, player.func_70005_c_());
/*     */         }
/* 164 */         if (func_145832_p() != 1) {
/* 165 */           this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
/* 166 */           sync();
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public String popUserExcept(EntityPlayer usingPlayer, boolean excludeUser)
/*     */     {
/* 173 */       String missingPlayers = "";
/* 174 */       for (int i = this.players.size() - 1; i >= 0; i--) {
/* 175 */         String foundPlayerName = (String)this.players.get(i);
/* 176 */         if ((!excludeUser) || (!foundPlayerName.equals(usingPlayer.func_70005_c_()))) {
/* 177 */           if (this.field_145850_b.func_72924_a(foundPlayerName) != null) {
/* 178 */             this.players.remove(i);
/* 179 */             sync();
/* 180 */             if (this.players.size() == 0) {
/* 181 */               this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
/*     */             }
/* 183 */             return foundPlayerName;
/*     */           }
/* 185 */           missingPlayers = missingPlayers + foundPlayerName + " ";
/*     */         }
/* 187 */         else if (this.players.size() == 1) {
/* 188 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, usingPlayer, "tile.witcheryLeechChest.onlyowntaglock", new Object[0]);
/* 189 */           return null;
/*     */         }
/*     */       }
/*     */       
/* 193 */       if (!missingPlayers.isEmpty()) {
/* 194 */         ChatUtil.sendTranslated(EnumChatFormatting.RED, usingPlayer, "tile.witcheryLeechChest.playernotloggedin", new Object[] { missingPlayers });
/*     */       }
/*     */       
/* 197 */       return null;
/*     */     }
/*     */     
/*     */     public Packet func_145844_m()
/*     */     {
/* 202 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 203 */       func_145841_b(nbtTag);
/* 204 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 209 */       super.onDataPacket(net, packet);
/* 210 */       func_145839_a(packet.func_148857_g());
/* 211 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound par1NBTTagCompound)
/*     */     {
/* 216 */       super.func_145839_a(par1NBTTagCompound);
/*     */       
/* 218 */       this.players.clear();
/* 219 */       NBTTagList nbtPlayersList = par1NBTTagCompound.func_150295_c("WITCPlayers", 10);
/* 220 */       for (int i = 0; i < nbtPlayersList.func_74745_c(); i++) {
/* 221 */         NBTTagCompound nbtPlayer = nbtPlayersList.func_150305_b(i);
/* 222 */         String s = nbtPlayer.func_74779_i("Player");
/* 223 */         if ((s != null) && (!s.isEmpty())) {
/* 224 */           this.players.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 231 */       super.func_145841_b(nbtTag);
/* 232 */       NBTTagList nbtItemsList = new NBTTagList();
/*     */       
/*     */ 
/* 235 */       NBTTagList nbtPlayers = new NBTTagList();
/*     */       
/* 237 */       for (int i = 0; i < this.players.size(); i++) {
/* 238 */         NBTTagCompound nbtPlayer = new NBTTagCompound();
/* 239 */         nbtPlayer.func_74778_a("Player", (String)this.players.get(i));
/* 240 */         nbtPlayers.func_74742_a(nbtPlayer);
/*     */       }
/*     */       
/* 243 */       nbtTag.func_74782_a("WITCPlayers", nbtPlayers);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBloodRose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */