/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.infusion.Infusion;
/*    */ import com.emoniph.witchery.infusion.Infusion.Registry;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.monster.EntityWitch;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*    */ 
/*    */ public class ItemWitchHand extends ItemBase
/*    */ {
/*    */   public ItemWitchHand()
/*    */   {
/* 24 */     func_77625_d(1);
/* 25 */     func_77664_n();
/*    */   }
/*    */   
/*    */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*    */   public EnumRarity func_77613_e(ItemStack itemstack)
/*    */   {
/* 31 */     return EnumRarity.uncommon;
/*    */   }
/*    */   
/*    */   public void func_77663_a(ItemStack itemstack, World world, Entity entity, int par4, boolean par5)
/*    */   {
/* 36 */     if ((entity instanceof EntityPlayer)) {
/* 37 */       Infusion.Registry.instance().get((EntityPlayer)entity).onUpdate(itemstack, world, (EntityPlayer)entity, par4, par5);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity)
/*    */   {
/* 43 */     Infusion.Registry.instance().get(player).onLeftClickEntity(itemstack, player.field_70170_p, player, entity);
/* 44 */     return true;
/*    */   }
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player)
/*    */   {
/* 49 */     player.func_71008_a(itemstack, func_77626_a(itemstack));
/* 50 */     return itemstack;
/*    */   }
/*    */   
/*    */   public int func_77626_a(ItemStack itemstack)
/*    */   {
/* 55 */     return 400;
/*    */   }
/*    */   
/*    */   public void onUsingTick(ItemStack itemstack, EntityPlayer player, int countdown)
/*    */   {
/* 60 */     Infusion.Registry.instance().get(player).onUsingItemTick(itemstack, player.field_70170_p, player, countdown);
/*    */   }
/*    */   
/*    */   public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int countdown)
/*    */   {
/* 65 */     if ((world.field_72995_K) || (!Infusion.isOnCooldown(world, itemstack))) {
/* 66 */       Infusion.Registry.instance().get(player).onPlayerStoppedUsing(itemstack, world, player, countdown);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class EventHooks {
/*    */     @SubscribeEvent
/*    */     public void onLivingDeath(LivingDeathEvent event) {
/* 73 */       if ((!event.entityLiving.field_70170_p.field_72995_K) && (
/* 74 */         ((event.entityLiving instanceof EntityWitch)) || ((event.entityLiving instanceof com.emoniph.witchery.entity.EntityCovenWitch)))) {
/* 75 */         Entity entitySource = event.source.func_76364_f();
/* 76 */         if ((entitySource != null) && ((entitySource instanceof EntityPlayer))) {
/* 77 */           EntityPlayer player = (EntityPlayer)entitySource;
/* 78 */           boolean hasArthana = (player.func_70694_bm() != null) && (player.func_70694_bm().func_77973_b() == Witchery.Items.ARTHANA);
/* 79 */           if (player.field_70170_p.field_73012_v.nextDouble() < (hasArthana ? 0.5D : 0.33D)) {
/* 80 */             ItemStack itemstack = new ItemStack(Witchery.Items.WITCH_HAND);
/* 81 */             EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, itemstack);
/*    */             
/* 83 */             event.entityLiving.field_70170_p.func_72838_d(entityItem);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemWitchHand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */