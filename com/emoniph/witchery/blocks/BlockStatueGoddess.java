/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockStatueGoddess
/*     */   extends BlockBaseContainer
/*     */ {
/*     */   public BlockStatueGoddess()
/*     */   {
/*  32 */     super(Material.field_151576_e, TileEntityStatueGoddess.class);
/*  33 */     func_149722_s();
/*  34 */     func_149752_b(1000.0F);
/*  35 */     func_149711_c(2.5F);
/*  36 */     func_149672_a(field_149769_e);
/*  37 */     func_149676_a(0.0F, 0.0F, 0.1F, 1.0F, 2.0F, 0.9F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
/*     */   {
/*  43 */     int l = MathHelper.func_76128_c(par5EntityLivingBase.field_70177_z * 4.0F / 360.0F + 0.5D) & 0x3;
/*     */     
/*  45 */     if (l == 0) {
/*  46 */       par1World.func_72921_c(par2, par3, par4, 2, 2);
/*     */     }
/*     */     
/*  49 */     if (l == 1) {
/*  50 */       par1World.func_72921_c(par2, par3, par4, 5, 2);
/*     */     }
/*     */     
/*  53 */     if (l == 2) {
/*  54 */       par1World.func_72921_c(par2, par3, par4, 3, 2);
/*     */     }
/*     */     
/*  57 */     if (l == 3) {
/*  58 */       par1World.func_72921_c(par2, par3, par4, 4, 2);
/*     */     }
/*     */     
/*  61 */     if ((!par1World.field_72995_K) && ((par5EntityLivingBase instanceof EntityPlayer))) {
/*  62 */       EntityPlayer player = (EntityPlayer)par5EntityLivingBase;
/*  63 */       TileEntityStatueGoddess tile = (TileEntityStatueGoddess)BlockUtil.getTileEntity(par1World, par2, par3, par4, TileEntityStatueGoddess.class);
/*  64 */       if (tile != null) {
/*  65 */         tile.setOwner(player.func_70005_c_());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_149699_a(World world, int x, int y, int z, EntityPlayer player)
/*     */   {
/*  77 */     if (!world.field_72995_K) {
/*  78 */       TileEntityStatueGoddess tile = (TileEntityStatueGoddess)BlockUtil.getTileEntity(world, x, y, z, TileEntityStatueGoddess.class);
/*  79 */       if ((tile != null) && (
/*  80 */         (player.field_71075_bZ.field_75098_d) || ((player.func_70005_c_().equals(tile.getOwner())) && (player.func_70093_af())))) {
/*  81 */         int dy = y;
/*  82 */         while (world.func_147439_a(x, dy, z) == this) {
/*  83 */           world.func_147468_f(x, dy, z);
/*  84 */           world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + dy, 0.5D + z, new ItemStack(this)));
/*  85 */           dy++;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
/*     */   {
/*  94 */     if (!world.field_72995_K)
/*     */     {
/*     */ 
/*  97 */       NBTTagCompound nbtTag = Infusion.getNBT(player);
/*  98 */       if ((nbtTag != null) && ((nbtTag.func_74764_b("witcheryCursed")) || (nbtTag.func_74764_b("witcheryInsanity")) || (nbtTag.func_74764_b("witcherySinking")) || (nbtTag.func_74764_b("witcheryOverheating")) || (nbtTag.func_74764_b("witcheryWakingNightmare"))))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 103 */         if (nbtTag.func_74764_b("witcheryCursed")) {
/* 104 */           nbtTag.func_82580_o("witcheryCursed");
/* 105 */           ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.curemisfortune", new Object[0]);
/*     */         }
/*     */         
/* 108 */         if (nbtTag.func_74764_b("witcheryInsanity")) {
/* 109 */           nbtTag.func_82580_o("witcheryInsanity");
/* 110 */           ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.cureinsanity", new Object[0]);
/*     */         }
/*     */         
/* 113 */         if (nbtTag.func_74764_b("witcherySinking")) {
/* 114 */           nbtTag.func_82580_o("witcherySinking");
/* 115 */           ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.curesinking", new Object[0]);
/* 116 */           Infusion.syncPlayer(world, player);
/*     */         }
/*     */         
/* 119 */         if (nbtTag.func_74764_b("witcheryOverheating")) {
/* 120 */           nbtTag.func_82580_o("witcheryOverheating");
/* 121 */           ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.cureoverheating", new Object[0]);
/*     */         }
/*     */         
/* 124 */         if (nbtTag.func_74764_b("witcheryWakingNightmare")) {
/* 125 */           nbtTag.func_82580_o("witcheryWakingNightmare");
/* 126 */           ChatUtil.sendTranslated(EnumChatFormatting.BLUE, player, "tile.witcheryStatusGoddess.curenightmare", new Object[0]);
/*     */         }
/*     */         
/* 129 */         if (player.func_70644_a(Potion.field_76436_u)) {
/* 130 */           player.func_82170_o(Potion.field_76436_u.field_76415_H);
/*     */         }
/* 132 */         if (player.func_70644_a(Potion.field_76437_t)) {
/* 133 */           player.func_82170_o(Potion.field_76437_t.field_76415_H);
/*     */         }
/* 135 */         if (player.func_70644_a(Potion.field_76440_q)) {
/* 136 */           player.func_82170_o(Potion.field_76440_q.field_76415_H);
/*     */         }
/* 138 */         if (player.func_70644_a(Potion.field_76419_f)) {
/* 139 */           player.func_82170_o(Potion.field_76419_f.field_76415_H);
/*     */         }
/* 141 */         if (player.func_70644_a(Potion.field_76421_d)) {
/* 142 */           player.func_82170_o(Potion.field_76421_d.field_76415_H);
/*     */         }
/*     */         
/* 145 */         ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, player, 1.0D, 2.0D, 8);
/*     */       } else {
/* 147 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */       }
/*     */       
/* 150 */       return true;
/*     */     }
/* 152 */     return super.func_149727_a(world, x, y, z, player, par6, par7, par8, par9);
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/* 157 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   public int func_149745_a(Random rand)
/*     */   {
/* 167 */     return 1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
/*     */   {
/* 173 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand) {}
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/* 184 */     this.field_149761_L = par1IconRegister.func_94245_a("stone");
/*     */   }
/*     */   
/*     */   public static class TileEntityStatueGoddess extends TileEntity
/*     */   {
/*     */     private static final String OWNER_KEY = "WITCPlacer";
/*     */     private String owner;
/*     */     
/*     */     public boolean canUpdate()
/*     */     {
/* 194 */       return false;
/*     */     }
/*     */     
/*     */     public void setOwner(String username) {
/* 198 */       this.owner = username;
/*     */     }
/*     */     
/*     */     public String getOwner() {
/* 202 */       return this.owner != null ? this.owner : "";
/*     */     }
/*     */     
/*     */     public void func_145841_b(NBTTagCompound nbtTag)
/*     */     {
/* 207 */       super.func_145841_b(nbtTag);
/* 208 */       nbtTag.func_74778_a("WITCPlacer", getOwner());
/*     */     }
/*     */     
/*     */     public void func_145839_a(NBTTagCompound nbtTag)
/*     */     {
/* 213 */       super.func_145839_a(nbtTag);
/* 214 */       if (nbtTag.func_74764_b("WITCPlacer")) {
/* 215 */         this.owner = nbtTag.func_74779_i("WITCPlacer");
/*     */       } else {
/* 217 */         this.owner = "";
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockStatueGoddess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */