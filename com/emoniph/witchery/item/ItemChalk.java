/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import com.emoniph.witchery.util.BlockSide;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.entity.player.PlayerCapabilities;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemChalk extends ItemBase
/*    */ {
/*    */   private static final int MAX_DAMAGE = 64;
/*    */   private static final int DAMAGE_PER_USE = 1;
/*    */   private Block block;
/*    */   
/*    */   public ItemChalk(Block block)
/*    */   {
/* 25 */     this.block = block;
/*    */     
/* 27 */     func_77625_d(64);
/* 28 */     func_77656_e(64);
/* 29 */     setNoRepair();
/*    */   }
/*    */   
/*    */   public int getItemStackLimit(ItemStack stack)
/*    */   {
/* 34 */     return stack.func_77951_h() ? 1 : this.field_77777_bU;
/*    */   }
/*    */   
/*    */   public void setDamage(ItemStack stack, int damage)
/*    */   {
/* 39 */     super.setDamage(stack, damage);
/*    */   }
/*    */   
/*    */   public static boolean drawGlyph(World world, int posX, int posY, int posZ, int side, Block block, EntityPlayer player) {
/* 43 */     boolean chalkUsed = false;
/* 44 */     if (block != Witchery.Blocks.CIRCLE) {
/* 45 */       Block overBlock = world.func_147439_a(posX, posY, posZ);
/* 46 */       if (overBlock == block) {
/* 47 */         world.func_72921_c(posX, posY, posZ, world.field_73012_v.nextInt(12), 3);
/* 48 */         chalkUsed = true;
/* 49 */       } else if ((overBlock == Witchery.Blocks.GLYPH_RITUAL) || (overBlock == Witchery.Blocks.GLYPH_OTHERWHERE) || (overBlock == Witchery.Blocks.GLYPH_INFERNAL)) {
/* 50 */         world.func_147465_d(posX, posY, posZ, block, world.field_73012_v.nextInt(12), 3);
/* 51 */         world.func_147471_g(posX, posY, posZ);
/* 52 */         chalkUsed = true;
/* 53 */       } else if ((BlockSide.TOP.isEqual(side)) && (Witchery.Blocks.GLYPH_RITUAL.func_149718_j(world, posX, posY + 1, posZ)) && 
/* 54 */         (com.emoniph.witchery.util.BlockUtil.isReplaceableBlock(world, posX, posY + 1, posZ, player))) {
/* 55 */         world.func_147465_d(posX, posY + 1, posZ, block, world.field_73012_v.nextInt(12), 3);
/* 56 */         world.func_147471_g(posX, posY + 1, posZ);
/* 57 */         chalkUsed = true;
/*    */       }
/*    */       
/*    */     }
/* 61 */     else if ((world.func_147439_a(posX, posY, posZ) != block) && (BlockSide.TOP.isEqual(side)) && (Witchery.Blocks.CIRCLE.func_149718_j(world, posX, posY + 1, posZ))) {
/* 62 */       world.func_147449_b(posX, posY + 1, posZ, block);
/* 63 */       world.func_147471_g(posX, posY + 1, posZ);
/* 64 */       chalkUsed = true;
/*    */     }
/*    */     
/* 67 */     if (chalkUsed) {
/* 68 */       SoundEffect.WITCHERY_RANDOM_CHALK.playAt(world, posX, posY, posZ, 1.0F, 1.0F);
/*    */     }
/* 70 */     return chalkUsed;
/*    */   }
/*    */   
/*    */   public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ)
/*    */   {
/* 75 */     if (!world.field_72995_K) {
/* 76 */       boolean chalkUsed = drawGlyph(world, posX, posY, posZ, side, this.block, player);
/*    */       
/* 78 */       if ((chalkUsed) && (!player.field_71075_bZ.field_75098_d)) {
/* 79 */         stack.func_77972_a(1, player);
/* 80 */         if (stack.field_77994_a > 1) {
/* 81 */           ItemStack newStack = ItemStack.func_77944_b(stack);
/* 82 */           newStack.field_77994_a -= 1;
/* 83 */           newStack.func_77964_b(0);
/* 84 */           if (!player.field_71071_by.func_70441_a(newStack)) {
/* 85 */             world.func_72838_d(new EntityItem(world, player.field_70165_t + 0.5D, player.field_70163_u + 1.5D, player.field_70161_v + 0.5D, newStack));
/* 86 */           } else if ((player instanceof EntityPlayerMP)) {
/* 87 */             ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
/*    */           }
/*    */           
/* 90 */           stack.field_77994_a = 1;
/*    */         }
/* 92 */         if (!stack.func_77942_o()) {
/* 93 */           stack.func_77982_d(new NBTTagCompound());
/*    */         }
/* 95 */         stack.func_77978_p().func_74768_a("PseudoDamage", stack.func_77960_j());
/*    */       }
/*    */     }
/* 98 */     return false;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemChalk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */