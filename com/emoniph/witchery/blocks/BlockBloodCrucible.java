/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer.VampireUltimate;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class BlockBloodCrucible extends BlockBaseContainer
/*     */ {
/*     */   public BlockBloodCrucible()
/*     */   {
/*  31 */     super(Material.field_151576_e, TileEntityBloodCrucible.class);
/*  32 */     func_149752_b(1000.0F);
/*  33 */     func_149711_c(2.5F);
/*  34 */     func_149672_a(field_149769_e);
/*  35 */     func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.31F, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  42 */     if (!world.field_72995_K) {
/*  43 */       TileEntityBloodCrucible crucible = (TileEntityBloodCrucible)com.emoniph.witchery.util.BlockUtil.getTileEntity(world, x, y, z, TileEntityBloodCrucible.class);
/*     */       
/*  45 */       if ((crucible != null) && 
/*  46 */         ((world instanceof WorldServer))) {
/*  47 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  48 */         ItemStack stack = player.func_70694_bm();
/*  49 */         if ((playerEx.getVampireLevel() >= 10) && ((crucible.isFull()) || (player.field_71075_bZ.field_75098_d)) && (stack != null)) {
/*  50 */           boolean success = false;
/*  51 */           if (Witchery.Items.GENERIC.itemArtichoke.isMatch(stack)) {
/*  52 */             playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.STORM);
/*  53 */             success = true;
/*  54 */           } else if (Witchery.Items.GENERIC.itemBatWool.isMatch(stack)) {
/*  55 */             playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.SWARM);
/*  56 */             success = true;
/*  57 */           } else if (stack.func_77973_b() == Items.field_151103_aS) {
/*  58 */             playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.FARM);
/*  59 */             success = true;
/*     */           }
/*     */           
/*  62 */           if (success) {
/*  63 */             crucible.drainAll();
/*  64 */             stack.field_77994_a -= 1;
/*  65 */             ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, world, 0.5D + x, y, 0.5D + z, 0.5D, 0.5D, 16);
/*     */           } else {
/*  67 */             ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, y, 0.5D + z, 0.5D, 0.5D, 16);
/*     */           }
/*     */         } else {
/*  70 */           ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + x, y, 0.5D + z, 0.5D, 0.5D, 16);
/*     */         }
/*     */       }
/*     */       
/*  74 */       return true;
/*     */     }
/*  76 */     return true;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/*  81 */     return 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side)
/*     */   {
/*  87 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  92 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  97 */     return false;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */   public static class TileEntityBloodCrucible
/*     */     extends TileEntity
/*     */   {
/*     */     private static final int MAX_BLOOD_LEVEL = 20;
/*     */     private int bloodLevel;
/*     */     
/*     */     public boolean canUpdate()
/*     */     {
/* 111 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isFull() {
/* 115 */       return this.bloodLevel == 20;
/*     */     }
/*     */     
/*     */     public void drainAll() {
/* 119 */       this.bloodLevel = 0;
/* 120 */       markBlockForUpdate(false);
/*     */     }
/*     */     
/*     */     public int getBloodLevel() {
/* 124 */       return this.bloodLevel;
/*     */     }
/*     */     
/*     */     public void increaseBloodLevel() {
/* 128 */       if (this.bloodLevel < 20) {
/* 129 */         this.bloodLevel = Math.min(5 + this.bloodLevel, 20);
/* 130 */         markBlockForUpdate(false);
/*     */       }
/*     */     }
/*     */     
/*     */     public float getPercentFilled() {
/* 135 */       return this.bloodLevel / 20.0F;
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtRoot)
/*     */     {
/* 140 */       super.func_145841_b(nbtRoot);
/* 141 */       nbtRoot.func_74768_a("BloodLevel", this.bloodLevel);
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtRoot)
/*     */     {
/* 146 */       super.func_145839_a(nbtRoot);
/* 147 */       this.bloodLevel = nbtRoot.func_74762_e("BloodLevel");
/*     */     }
/*     */     
/*     */     public void markBlockForUpdate(boolean notifyNeighbours) {
/* 151 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 152 */       if ((notifyNeighbours) && (this.field_145850_b != null)) {
/* 153 */         this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/*     */       }
/*     */     }
/*     */     
/*     */     public net.minecraft.network.Packet func_145844_m()
/*     */     {
/* 159 */       NBTTagCompound nbtTag = new NBTTagCompound();
/* 160 */       func_145841_b(nbtTag);
/* 161 */       return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */     }
/*     */     
/*     */     public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */     {
/* 166 */       super.onDataPacket(net, packet);
/* 167 */       func_145839_a(packet.func_148857_g());
/* 168 */       this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBloodCrucible.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */