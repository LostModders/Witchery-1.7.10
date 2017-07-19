/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidClassic;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ public class BlockDisease extends BlockFluidClassic
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon[] icons;
/*     */   
/*     */   public BlockDisease(Fluid fluid)
/*     */   {
/*  29 */     super(fluid, net.minecraft.block.material.Material.field_151597_y);
/*  30 */     this.quantaPerBlock = 1;
/*  31 */     func_149711_c(100.0F);
/*  32 */     func_149713_g(1);
/*     */   }
/*     */   
/*     */   public int getMaxRenderHeightMeta()
/*     */   {
/*  37 */     return 16;
/*     */   }
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand)
/*     */   {
/*  42 */     super.func_149674_a(world, x, y, z, rand);
/*  43 */     int chance = Config.instance().diseaseRemovalChance;
/*  44 */     if (chance > 0) {
/*  45 */       if (world.field_73012_v.nextInt(chance) == 0) {
/*  46 */         world.func_147468_f(x, y, z);
/*     */       }
/*  48 */       world.func_147464_a(x, y, z, this, this.tickRate);
/*     */     }
/*     */   }
/*     */   
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  54 */     com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
/*  55 */     return super.func_149663_c(blockName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  64 */     return (side != 0) && (side != 1) ? this.icons[1] : this.icons[0];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/*  70 */     this.icons = new IIcon[] { iconRegister.func_94245_a(func_149641_N() + "_still"), iconRegister.func_94245_a(func_149641_N() + "_flow") };
/*     */     
/*  72 */     if ((this.stack != null) && (this.stack.getFluid() != null)) {
/*  73 */       this.stack.getFluid().setIcons(this.icons[0], this.icons[1]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  79 */     if ((!world.field_72995_K) && (entity != null) && 
/*  80 */       ((entity instanceof EntityLivingBase))) {
/*  81 */       EntityLivingBase livingEntity = (EntityLivingBase)entity;
/*  82 */       if ((!com.emoniph.witchery.util.CreatureUtil.isImmuneToDisease(livingEntity)) && 
/*  83 */         (!livingEntity.func_70644_a(Witchery.Potions.DISEASED)) && (world.field_73012_v.nextInt(3) == 0)) {
/*  84 */         livingEntity.func_70690_d(new PotionEffect(Witchery.Potions.DISEASED.field_76415_H, TimeUtil.minsToTicks(1 + world.field_73012_v.nextInt(4))));
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
/*     */   public boolean canDisplace(IBlockAccess world, int x, int y, int z)
/*     */   {
/*  98 */     return super.canDisplace(world, x, y, z);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean displaceIfPossible(World world, int x, int y, int z)
/*     */   {
/* 107 */     return super.displaceIfPossible(world, x, y, z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockDisease.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */