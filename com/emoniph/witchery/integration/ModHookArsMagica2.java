/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import am2.api.ArsMagicaApi;
/*    */ import am2.api.IExtendedProperties;
/*    */ import am2.api.enchantment.IAMEnchantmentHelper;
/*    */ import am2.api.events.ReconstructorRepairEvent;
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.item.ItemChalk;
/*    */ import cpw.mods.fml.common.eventhandler.EventBus;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ 
/*    */ public class ModHookArsMagica2
/*    */   extends ModHook
/*    */ {
/*    */   public String getModID()
/*    */   {
/* 23 */     return "arsmagica2";
/*    */   }
/*    */   
/*    */ 
/*    */   protected void doInit() {}
/*    */   
/*    */ 
/*    */   protected void doPostInit()
/*    */   {
/* 32 */     Witchery.modHooks.isAM2Present = true;
/* 33 */     MinecraftForge.EVENT_BUS.register(new EventHooks());
/*    */   }
/*    */   
/*    */   protected void doReduceMagicPower(EntityLivingBase entity, float factor)
/*    */   {
/* 38 */     IntegrateAM2.doReduceMagicPower(entity, factor);
/*    */   }
/*    */   
/*    */   protected void makeItemModProof(ItemStack stack)
/*    */   {
/* 43 */     IntegrateAM2.makeItemModProof(stack);
/*    */   }
/*    */   
/*    */   private static class IntegrateAM2 {
/*    */     public static void doReduceMagicPower(EntityLivingBase entity, float factor) {
/* 48 */       IExtendedProperties props = ArsMagicaApi.instance.getExtendedProperties(entity);
/* 49 */       if (props != null) {
/* 50 */         float maxMana = props.getMaxMana();
/* 51 */         float mana = props.getCurrentMana();
/* 52 */         if ((maxMana > 0.0F) && (mana > 0.0F)) {
/* 53 */           float reduction = Math.max(maxMana * factor, 1.0F);
/* 54 */           float newMana = Math.max(mana - reduction, 0.0F);
/* 55 */           props.setCurrentMana(newMana);
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */     public static void makeItemModProof(ItemStack stack) {
/* 61 */       if ((stack.func_77956_u()) && 
/* 62 */         (ArsMagicaApi.instance != null)) {
/* 63 */         IAMEnchantmentHelper helper = ArsMagicaApi.instance.getEnchantHelper();
/* 64 */         if (helper != null) {
/* 65 */           int id = helper.getSoulboundID();
/* 66 */           if ((id >= 0) && (id < Enchantment.field_77331_b.length)) {
/* 67 */             stack.func_77966_a(Enchantment.field_77331_b[id], 1);
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static class EventHooks
/*    */   {
/*    */     @SubscribeEvent
/*    */     public void onReconstructorRepair(ReconstructorRepairEvent event) {
/* 78 */       if ((event.item != null) && (!event.isCanceled())) {
/* 79 */         Item item = event.item.func_77973_b();
/* 80 */         if ((item == Witchery.Items.POPPET) || ((item instanceof ItemChalk))) {
/* 81 */           event.setCanceled(true);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookArsMagica2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */