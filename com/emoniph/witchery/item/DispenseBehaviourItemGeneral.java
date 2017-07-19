/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*    */ import net.minecraft.dispenser.BehaviorProjectileDispense;
/*    */ import net.minecraft.dispenser.IPosition;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class DispenseBehaviourItemGeneral
/*    */   implements net.minecraft.dispenser.IBehaviorDispenseItem
/*    */ {
/*    */   private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior;
/*    */   
/* 16 */   public DispenseBehaviourItemGeneral() { this.defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem(); }
/*    */   
/*    */   public ItemStack func_82482_a(net.minecraft.dispenser.IBlockSource block, ItemStack stack) {
/* 19 */     if (Witchery.Items.GENERIC.isBrew(stack.func_77960_j())) {
/* 20 */       return new DispenserBehaviorBrew(this, stack).func_82482_a(block, stack);
/*    */     }
/* 22 */     return this.defaultDispenserItemBehavior.func_82482_a(block, stack);
/*    */   }
/*    */   
/*    */   static class DispenserBehaviorBrew extends BehaviorProjectileDispense
/*    */   {
/*    */     final ItemStack potionItemStack;
/*    */     final DispenseBehaviourItemGeneral dispenserPotionBehavior;
/*    */     
/*    */     DispenserBehaviorBrew(DispenseBehaviourItemGeneral par1DispenserBehaviorPotion, ItemStack par2ItemStack)
/*    */     {
/* 32 */       this.dispenserPotionBehavior = par1DispenserBehaviorPotion;
/* 33 */       this.potionItemStack = par2ItemStack;
/*    */     }
/*    */     
/*    */     protected net.minecraft.entity.IProjectile func_82499_a(World par1World, IPosition par2IPosition) {
/* 37 */       return new com.emoniph.witchery.entity.EntityWitchProjectile(par1World, par2IPosition.func_82615_a(), par2IPosition.func_82617_b(), par2IPosition.func_82616_c(), (ItemGeneral.SubItem)Witchery.Items.GENERIC.subItems.get(this.potionItemStack.func_77960_j()));
/*    */     }
/*    */     
/*    */     protected float func_82498_a()
/*    */     {
/* 42 */       return super.func_82498_a() * 0.5F;
/*    */     }
/*    */     
/*    */     protected float func_82500_b() {
/* 46 */       return super.func_82500_b() * 1.25F;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/DispenseBehaviourItemGeneral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */