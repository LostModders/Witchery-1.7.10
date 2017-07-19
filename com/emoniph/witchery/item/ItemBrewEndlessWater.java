/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class ItemBrewEndlessWater extends ItemBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   protected IIcon itemIconOverlay;
/*     */   
/*     */   public ItemBrewEndlessWater()
/*     */   {
/*  23 */     func_77625_d(1);
/*  24 */     func_77656_e(99);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasEffect(ItemStack stack, int pass)
/*     */   {
/*  30 */     return pass == 0;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v()
/*     */   {
/*  36 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon getIcon(ItemStack stack, int pass)
/*     */   {
/*  42 */     if (pass == 0) {
/*  43 */       return this.itemIconOverlay;
/*     */     }
/*  45 */     return this.field_77791_bV;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/*  55 */     super.func_94581_a(iconRegister);
/*  56 */     this.itemIconOverlay = iconRegister.func_94245_a("witchery:brew_overlay");
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack stack, int pass)
/*     */   {
/*  62 */     if (pass == 0) {
/*  63 */       int color = 255;
/*  64 */       return 255;
/*     */     }
/*  66 */     return super.func_82790_a(stack, pass);
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expanded)
/*     */   {
/*  73 */     String localText = String.format(com.emoniph.witchery.Witchery.resource("item.witchery:brew.water.tip"), new Object[] { Integer.valueOf(stack.func_77958_k() - stack.func_77960_j() + 1).toString(), Integer.valueOf(stack.func_77958_k() + 1).toString() });
/*     */     
/*     */ 
/*  76 */     if (localText != null) {
/*  77 */       for (String s : localText.split("\n")) {
/*  78 */         if (!s.isEmpty()) {
/*  79 */           list.add(s);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
/*     */   {
/*  88 */     if ((!world.field_72995_K) && 
/*  89 */       (stack.func_77960_j() <= stack.func_77958_k())) {
/*  90 */       Block block = world.func_147439_a(x, y, z);
/*  91 */       if (block == Blocks.field_150383_bp) {
/*  92 */         int meta = world.func_72805_g(x, y, z);
/*  93 */         if (meta < 3) {
/*  94 */           stack.func_77972_a(1, player);
/*  95 */           Blocks.field_150383_bp.func_150024_a(world, x, y, z, 3);
/*  96 */           SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
/*     */         }
/*     */       }
/*     */       else {
/* 100 */         ForgeDirection face = ForgeDirection.getOrientation(side);
/* 101 */         x += face.offsetX;
/* 102 */         y += face.offsetY;
/* 103 */         z += face.offsetZ;
/* 104 */         if ((block != null) && (com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(world, x, y, z, player))) {
/* 105 */           stack.func_77972_a(1, player);
/* 106 */           world.func_147449_b(x, y, z, Blocks.field_150358_i);
/* 107 */           world.func_147471_g(x, y, z);
/* 108 */           SoundEffect.WATER_SPLASH.playAtPlayer(world, player);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 113 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBrewEndlessWater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */