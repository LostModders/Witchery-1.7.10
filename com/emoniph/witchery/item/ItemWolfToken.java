/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.common.ExtendedPlayer;
/*    */ import com.emoniph.witchery.util.ChatUtil;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumAction;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWolfToken
/*    */   extends ItemBase
/*    */ {
/*    */   public ItemWolfToken()
/*    */   {
/* 25 */     this.autoGenerateTooltip = true;
/* 26 */     func_77625_d(1);
/* 27 */     func_77656_e(0);
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 33 */     return EnumRarity.epic;
/*    */   }
/*    */   
/*    */   public EnumAction func_77661_b(ItemStack itemstack)
/*    */   {
/* 38 */     return EnumAction.bow;
/*    */   }
/*    */   
/*    */   public int func_77626_a(ItemStack itemstack)
/*    */   {
/* 43 */     return TimeUtil.secsToTicks(1);
/*    */   }
/*    */   
/*    */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*    */   {
/* 48 */     if ((!player.field_70170_p.field_72995_K) && (countdown == 1)) {
/* 49 */       if (player.func_70093_af()) {
/* 50 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 51 */         int vampLevel = playerEx.getVampireLevel() + 1;
/* 52 */         if (vampLevel > 10) {
/* 53 */           vampLevel = 0;
/*    */         }
/* 55 */         playerEx.setVampireLevel(vampLevel);
/* 56 */         ChatUtil.sendTranslated(EnumChatFormatting.GREEN, player, "witchery.vampire.setlevel", new Object[] { Integer.valueOf(vampLevel).toString() });
/*    */         
/* 58 */         ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, player, 1.5D, 1.5D, 16);
/*    */       } else {
/* 60 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 61 */         int wolfLevel = playerEx.getWerewolfLevel() + 1;
/* 62 */         if (wolfLevel > 10) {
/* 63 */           wolfLevel = 0;
/*    */         }
/* 65 */         playerEx.setWerewolfLevel(wolfLevel);
/* 66 */         ChatUtil.sendTranslated(EnumChatFormatting.GREEN, player, "witchery.werewolf.setlevel", new Object[] { Integer.valueOf(wolfLevel).toString() });
/*    */         
/* 68 */         ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, player, 1.5D, 1.5D, 16);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*    */   {
/* 75 */     player.func_71008_a(stack, func_77626_a(stack));
/* 76 */     return stack;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemWolfToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */