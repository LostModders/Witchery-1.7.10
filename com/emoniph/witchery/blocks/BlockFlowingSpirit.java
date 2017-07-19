/*     */ package com.emoniph.witchery.blocks;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.CreatureUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fluids.BlockFluidClassic;
/*     */ import net.minecraftforge.fluids.Fluid;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ public class BlockFlowingSpirit extends BlockFluidClassic
/*     */ {
/*     */   protected final boolean nightmareBane;
/*     */   protected final boolean igniteSpiritPortals;
/*     */   protected final PotionEffect goodyEffect;
/*     */   protected final PotionEffect baddyEffect;
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon[] icons;
/*     */   
/*     */   public BlockFlowingSpirit(Fluid fluid, PotionEffect goodyEffect, PotionEffect baddyEffect, boolean nightmareBane, boolean igniteSpiritPortals)
/*     */   {
/*  35 */     super(fluid, Material.field_151586_h);
/*  36 */     this.quantaPerBlock = 5;
/*  37 */     func_149711_c(100.0F);
/*  38 */     func_149713_g(3);
/*  39 */     this.goodyEffect = goodyEffect;
/*  40 */     this.baddyEffect = baddyEffect;
/*  41 */     this.nightmareBane = nightmareBane;
/*  42 */     this.igniteSpiritPortals = igniteSpiritPortals;
/*     */   }
/*     */   
/*     */   public Block func_149663_c(String blockName)
/*     */   {
/*  47 */     com.emoniph.witchery.util.BlockUtil.registerBlock(this, blockName);
/*  48 */     return super.func_149663_c(blockName);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149691_a(int side, int meta)
/*     */   {
/*  57 */     return (side != 0) && (side != 1) ? this.icons[1] : this.icons[0];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister iconRegister)
/*     */   {
/*  63 */     this.icons = new IIcon[] { iconRegister.func_94245_a(func_149641_N() + "_still"), iconRegister.func_94245_a(func_149641_N() + "_flow") };
/*     */     
/*  65 */     if ((this.stack != null) && (this.stack.getFluid() != null)) {
/*  66 */       this.stack.getFluid().setIcons(this.icons[0], this.icons[1]);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z)
/*     */   {
/*  72 */     if ((!this.igniteSpiritPortals) || (world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID) || (world.func_147439_a(x, y - 1, z) != net.minecraft.init.Blocks.field_150433_aE) || (world.func_72805_g(x, y, z) != 0) || (!Witchery.Blocks.SPIRIT_PORTAL.tryToCreatePortal(world, x, y, z)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*  77 */       super.func_149726_b(world, x, y, z);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_149670_a(World world, int x, int y, int z, Entity entity)
/*     */   {
/*  83 */     if ((!world.field_72995_K) && (entity != null)) {
/*  84 */       if ((entity instanceof EntityLivingBase)) {
/*  85 */         EntityLivingBase livingEntity = (EntityLivingBase)entity;
/*  86 */         if ((CreatureUtil.isUndead(livingEntity)) || (CreatureUtil.isDemonic(livingEntity)) || ((this.nightmareBane) && ((livingEntity instanceof com.emoniph.witchery.entity.EntityNightmare))))
/*     */         {
/*  88 */           if (!livingEntity.func_82165_m(this.baddyEffect.func_76456_a())) {
/*  89 */             livingEntity.func_70690_d(new PotionEffect(this.baddyEffect));
/*     */           }
/*     */         }
/*  92 */         else if (!livingEntity.func_82165_m(this.goodyEffect.func_76456_a())) {
/*  93 */           livingEntity.func_70690_d(new PotionEffect(this.goodyEffect));
/*     */         }
/*     */       }
/*  96 */       else if ((this.nightmareBane) && ((entity instanceof EntityItem))) {
/*  97 */         EntityItem item = (EntityItem)entity;
/*  98 */         ItemStack stack = item.func_92059_d();
/*  99 */         if (Witchery.Items.GENERIC.itemDisturbedCotton.isMatch(stack)) {
/* 100 */           ItemStack newStack = new ItemStack(Witchery.Blocks.WISPY_COTTON, stack.field_77994_a);
/* 101 */           item.func_92058_a(newStack);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean canDisplace(IBlockAccess world, int x, int y, int z)
/*     */   {
/* 109 */     if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
/* 110 */       return false;
/*     */     }
/* 112 */     return super.canDisplace(world, x, y, z);
/*     */   }
/*     */   
/*     */   public boolean displaceIfPossible(World world, int x, int y, int z)
/*     */   {
/* 117 */     if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
/* 118 */       return false;
/*     */     }
/* 120 */     return super.displaceIfPossible(world, x, y, z);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/BlockFlowingSpirit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */