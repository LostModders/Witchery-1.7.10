/*    */ package com.emoniph.witchery.blocks;
/*    */ 
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ public class TileEntityBase extends TileEntity {
/*  6 */   protected long ticks = 0L;
/*    */   
/*    */   public void func_145845_h()
/*    */   {
/* 10 */     super.func_145845_h();
/*    */     
/* 12 */     if (this.ticks == 0L) {
/* 13 */       initiate();
/* 14 */     } else if (this.ticks >= Long.MAX_VALUE) {
/* 15 */       this.ticks = 1L;
/*    */     }
/*    */     
/* 18 */     this.ticks += 1L;
/*    */   }
/*    */   
/*    */   protected void initiate() {}
/*    */   
/*    */   public void markBlockForUpdate(boolean notifyNeighbours)
/*    */   {
/* 25 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 26 */     if ((notifyNeighbours) && (this.field_145850_b != null)) {
/* 27 */       this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, func_145838_q());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/blocks/TileEntityBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */