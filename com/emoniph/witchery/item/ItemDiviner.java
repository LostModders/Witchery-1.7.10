/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
/*    */ import com.emoniph.witchery.util.BlockSide;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemDiviner
/*    */   extends ItemBase
/*    */ {
/*    */   private static final int MAX_DAMAGE = 50;
/*    */   private static final int DAMAGE_PER_USE = 1;
/*    */   private final Block blockToDetect;
/*    */   
/*    */   public ItemDiviner(Block blockToDetect)
/*    */   {
/* 31 */     this.blockToDetect = blockToDetect;
/* 32 */     func_77625_d(1);
/* 33 */     func_77656_e(50);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 39 */     return EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */   public EnumAction func_77661_b(ItemStack itemstack)
/*    */   {
/* 44 */     return EnumAction.bow;
/*    */   }
/*    */   
/*    */   public int func_77626_a(ItemStack itemstack)
/*    */   {
/* 49 */     return 400;
/*    */   }
/*    */   
/*    */   public void onUsingTick(ItemStack itemstack, EntityPlayer player, int countdown)
/*    */   {
/* 54 */     World world = player.field_70170_p;
/* 55 */     if (!world.field_72995_K) {
/* 56 */       int elapsedTicks = func_77626_a(itemstack) - countdown;
/* 57 */       MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 6.0D);
/*    */       
/* 59 */       if ((mop == null) || (mop.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) || (!BlockSide.TOP.isEqual(mop.field_72310_e))) {
/* 60 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/* 61 */         player.func_71041_bz();
/* 62 */         return;
/*    */       }
/*    */       
/* 65 */       int posX = MathHelper.func_76128_c(mop.field_72311_b);
/* 66 */       int posY = MathHelper.func_76128_c(mop.field_72312_c) - elapsedTicks;
/* 67 */       int posZ = MathHelper.func_76128_c(mop.field_72309_d);
/* 68 */       Block block = world.func_147439_a(posX, posY, posZ);
/*    */       
/* 70 */       boolean foundBlock = false;
/* 71 */       boolean foundSomething = false;
/*    */       
/* 73 */       if (block == this.blockToDetect) {
/* 74 */         foundBlock = true;
/* 75 */         foundSomething = true;
/* 76 */       } else if (block == Blocks.field_150357_h) {
/* 77 */         foundBlock = false;
/* 78 */         foundSomething = true;
/*    */       }
/*    */       
/* 81 */       if ((foundSomething) || (posY <= 1)) {
/* 82 */         if (foundBlock) {
/* 83 */           ParticleEffect.MAGIC_CRIT.send(SoundEffect.RANDOM_ORB, world, 0.5D + mop.field_72311_b, mop.field_72312_c + 1, 0.5D + mop.field_72309_d, 0.5D, 0.5D, 8);
/*    */         } else {
/* 85 */           ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5D + mop.field_72311_b, mop.field_72312_c + 1, 0.5D + mop.field_72309_d, 0.5D, 0.5D, 8);
/*    */         }
/* 87 */         player.func_71041_bz();
/* 88 */         itemstack.func_77972_a(1, player);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*    */   {
/* 95 */     player.func_71008_a(itemstack, func_77626_a(itemstack));
/* 96 */     return itemstack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemDiviner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */