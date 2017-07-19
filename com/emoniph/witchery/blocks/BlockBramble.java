/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.util.MultiItemBlock;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class BlockBramble
/*     */   extends BlockBase
/*     */ {
/*  30 */   private static final String[] BRAMBLE_TYPES = { "ender", "wild" };
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] iconArray;
/*     */   
/*  34 */   public static class ClassItemBlock extends MultiItemBlock { public ClassItemBlock(Block block) { super(); }
/*     */     
/*     */ 
/*     */     protected String[] getNames()
/*     */     {
/*  39 */       return BlockBramble.BRAMBLE_TYPES;
/*     */     }
/*     */     
/*     */     @SideOnly(Side.CLIENT)
/*     */     public IIcon func_77617_a(int meta)
/*     */     {
/*  45 */       return this.field_150939_a.func_149691_a(0, meta);
/*     */     }
/*     */   }
/*     */   
/*     */   public BlockBramble() {
/*  50 */     super(Material.field_151585_k, ClassItemBlock.class);
/*     */     
/*  52 */     func_149711_c(20.0F);
/*  53 */     func_149752_b(1000.0F);
/*     */     
/*  55 */     float f = 0.45F;
/*  56 */     func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
/*     */   }
/*     */   
/*     */   public int func_149645_b()
/*     */   {
/*  61 */     return 6;
/*     */   }
/*     */   
/*     */   public boolean func_149662_c()
/*     */   {
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_149686_d()
/*     */   {
/*  71 */     return false;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4)
/*     */   {
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity)
/*     */   {
/*  81 */     int meta = world.func_72805_g(posX, posY, posZ);
/*  82 */     switch (meta) {
/*     */     case 0: 
/*  84 */       if ((entity instanceof EntityLivingBase)) {
/*  85 */         EntityLivingBase living = (EntityLivingBase)entity;
/*  86 */         teleportAway(world, posX, posY, posZ, living); }
/*  87 */       break;
/*     */     
/*     */     case 1: 
/*  90 */       entity.func_70097_a(DamageSource.field_76367_g, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   public void teleportAway(World world, int posX, int posY, int posZ, EntityLivingBase entity)
/*     */   {
/*  96 */     if (!world.field_72995_K) {
/*  97 */       int distance = 500;
/*  98 */       int doubleDistance = 1000;
/*     */       
/*     */ 
/* 101 */       posX += world.field_73012_v.nextInt(1000) - 500;
/* 102 */       posZ += world.field_73012_v.nextInt(1000) - 500;
/* 103 */       int maxY = Math.min(posY + 64, 250);
/* 104 */       while ((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) && (posY >= 0)) {
/* 105 */         posY--;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 111 */       while (((!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) || (world.func_147439_a(posX, posY, posZ) == Blocks.field_150357_h) || (!world.func_147437_c(posX, posY + 1, posZ)) || (!world.func_147437_c(posX, posY + 2, posZ)) || (!world.func_147437_c(posX, posY + 3, posZ))) && (posY < maxY)) {
/* 112 */         posY++;
/*     */       }
/* 114 */       if ((posY > 0) && (posY < maxY)) {
/* 115 */         ItemGeneral.teleportToLocation(world, 0.5D + posX, 1.0D + posY, 0.5D + posZ, world.field_73011_w.field_76574_g, entity, true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int par1, int par2)
/*     */   {
/* 139 */     if ((par2 < 0) || (par2 >= this.iconArray.length)) {
/* 140 */       par2 = 0;
/*     */     }
/*     */     
/* 143 */     return this.iconArray[par2];
/*     */   }
/*     */   
/*     */   public int func_149692_a(int par1) {
/* 147 */     return par1;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
/* 152 */     for (int j = 0; j < BRAMBLE_TYPES.length; j++) {
/* 153 */       par3List.add(new ItemStack(par1, 1, j));
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister par1IconRegister)
/*     */   {
/* 160 */     this.iconArray = new IIcon[BRAMBLE_TYPES.length];
/* 161 */     for (int i = 0; i < this.iconArray.length; i++) {
/* 162 */       this.iconArray[i] = par1IconRegister.func_94245_a(func_149641_N() + "_" + BRAMBLE_TYPES[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer player, int x, int y, int z, int meta)
/*     */   {
/* 168 */     super.func_149636_a(world, player, x, y, z, meta);
/*     */     
/* 170 */     if ((!world.field_72995_K) && (meta == 1) && (
/* 171 */       (player.func_70694_bm() == null) || (player.func_70694_bm().func_77973_b() != Items.field_151006_E))) {
/* 172 */       spreadToIfEmpty(world, x + 1, y, z, this, meta);
/* 173 */       spreadToIfEmpty(world, x, y, z + 1, this, meta);
/* 174 */       spreadToIfEmpty(world, x - 1, y, z, this, meta);
/* 175 */       spreadToIfEmpty(world, x, y, z - 1, this, meta);
/* 176 */       spreadToIfEmpty(world, x + 1, y, z + 1, this, meta);
/* 177 */       spreadToIfEmpty(world, x - 1, y, z - 1, this, meta);
/* 178 */       spreadToIfEmpty(world, x - 1, y, z + 1, this, meta);
/* 179 */       spreadToIfEmpty(world, x + 1, y, z - 1, this, meta);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void spreadToIfEmpty(World world, int x, int y0, int z, Block newBlock, int newBlockMeta)
/*     */   {
/* 185 */     if (!world.field_72995_K) {
/* 186 */       for (int y = y0 - 1; y <= y0 + 1; y++) {
/* 187 */         Block block = world.func_147439_a(x, y, z);
/* 188 */         if ((block == Blocks.field_150433_aE) || (block == Blocks.field_150329_H) || (block == Blocks.field_150350_a)) {
/* 189 */           Block belowBlock = world.func_147439_a(x, y - 1, z);
/* 190 */           if ((belowBlock != Blocks.field_150350_a) && (world.field_73012_v.nextInt(2) == 0)) {
/* 191 */             world.func_147465_d(x, y, z, newBlock, newBlockMeta, 3);
/* 192 */             if (world.field_73012_v.nextInt(3) != 0) {
/*     */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149734_b(World world, int x, int y, int z, Random rand)
/*     */   {
/* 204 */     int meta = world.func_72805_g(x, y, z);
/* 205 */     if ((meta == 0) && (rand.nextInt(2) == 0)) {
/* 206 */       double d0 = x + rand.nextFloat();
/* 207 */       double d1 = y + 0.15F + rand.nextFloat() * 0.3F + 0.5D;
/* 208 */       double d2 = z + rand.nextFloat();
/* 209 */       world.func_72869_a(ParticleEffect.PORTAL.toString(), d0, d1, d2, 0.0D, -1.2D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149742_c(World par1World, int par2, int par3, int par4)
/*     */   {
/* 215 */     return !par1World.func_147437_c(par2, par3 - 1, par4);
/*     */   }
/*     */   
/*     */   public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5)
/*     */   {
/* 220 */     checkBlockCoordValid(par1World, par2, par3, par4);
/*     */   }
/*     */   
/*     */   protected final void checkBlockCoordValid(World par1World, int par2, int par3, int par4) {
/* 224 */     if (!func_149718_j(par1World, par2, par3, par4)) {
/* 225 */       func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
/* 226 */       par1World.func_147468_f(par2, par3, par4);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean func_149718_j(World par1World, int par2, int par3, int par4)
/*     */   {
/* 232 */     return func_149742_c(par1World, par2, par3, par4);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockBramble.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */