/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BrewItemKey
/*    */ {
/*    */   public final Item ITEM;
/*    */   public final int DAMAGE;
/*    */   private final int ITEM_HASH;
/*    */   
/*    */   public BrewItemKey(Item item)
/*    */   {
/* 18 */     this(item, 0);
/*    */   }
/*    */   
/*    */   public BrewItemKey(Block block) {
/* 22 */     this(block, 0);
/*    */   }
/*    */   
/*    */   public BrewItemKey(Block block, int damage) {
/* 26 */     this(Item.func_150898_a(block), damage);
/*    */   }
/*    */   
/*    */   public BrewItemKey(Item item, int damage) {
/* 30 */     this.ITEM = item;
/* 31 */     this.DAMAGE = damage;
/* 32 */     if (this.ITEM != null) {
/* 33 */       if (this.ITEM.func_77658_a() != null) {
/* 34 */         this.ITEM_HASH = this.ITEM.func_77658_a().hashCode();
/*    */       } else {
/* 36 */         Log.instance().warning(String.format("unlocalizedname=null for item passed to BrewItemKey constructor (another mod cleared it?)", new Object[0]));
/* 37 */         this.ITEM_HASH = "".hashCode();
/*    */       }
/*    */     } else {
/* 40 */       Log.instance().warning("item=null passed to BrewItemKey constructor, block to item map must be busted somehow (tweaking blocks?).");
/* 41 */       this.ITEM_HASH = "".hashCode();
/*    */     }
/*    */   }
/*    */   
/*    */   public ItemStack toStack() {
/* 46 */     return new ItemStack(this.ITEM, 1, this.DAMAGE);
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 51 */     int result = 17;
/* 52 */     result = 37 * result + this.ITEM_HASH;
/*    */     
/* 54 */     return result;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 59 */     if ((obj == null) || (getClass() != obj.getClass())) {
/* 60 */       return false;
/*    */     }
/*    */     
/* 63 */     if (obj == this) {
/* 64 */       return true;
/*    */     }
/*    */     
/* 67 */     BrewItemKey other = (BrewItemKey)obj;
/* 68 */     return (this.ITEM == other.ITEM) && ((this.DAMAGE == 32767) || (other.DAMAGE == 32767) || (this.DAMAGE == other.DAMAGE));
/*    */   }
/*    */   
/*    */   public static BrewItemKey fromStack(ItemStack stack) {
/* 72 */     return new BrewItemKey(stack.func_77973_b(), stack.func_77960_j());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BrewItemKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */