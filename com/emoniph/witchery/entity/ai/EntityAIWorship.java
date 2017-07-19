/*    */ package com.emoniph.witchery.entity.ai;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntityGoblin;
/*    */ 
/*    */ public class EntityAIWorship extends net.minecraft.entity.ai.EntityAIBase
/*    */ {
/*    */   private final EntityGoblin goblin;
/*    */   private final double maxDuration;
/*    */   private int currentTick;
/*    */   private boolean shouldBegin;
/*    */   private int posX;
/*    */   private int posY;
/*    */   private int posZ;
/*    */   
/*    */   public EntityAIWorship(EntityGoblin goblin, double maxDuration) {
/* 16 */     this.goblin = goblin;
/* 17 */     this.maxDuration = maxDuration;
/* 18 */     func_75248_a(15);
/*    */   }
/*    */   
/*    */   public boolean func_75250_a()
/*    */   {
/* 23 */     return (this.shouldBegin) || (this.goblin.isWorshipping());
/*    */   }
/*    */   
/*    */   public void func_75249_e()
/*    */   {
/* 28 */     this.currentTick = 0;
/* 29 */     this.shouldBegin = false;
/* 30 */     this.goblin.setWorshipping(true);
/* 31 */     this.goblin.func_70661_as().func_75492_a(this.posX, this.posY, this.posZ, 0.4D);
/*    */   }
/*    */   
/*    */   public boolean func_75253_b()
/*    */   {
/* 36 */     return (this.currentTick <= this.maxDuration) || (this.goblin.field_70170_p.field_73012_v.nextInt(3) == 0);
/*    */   }
/*    */   
/*    */   public void func_75251_c()
/*    */   {
/* 41 */     this.goblin.setWorshipping(false);
/*    */   }
/*    */   
/*    */   public void func_75246_d()
/*    */   {
/* 46 */     this.currentTick += 1;
/*    */   }
/*    */   
/*    */   public void begin(net.minecraft.tileentity.TileEntity tile) {
/* 50 */     if (this.goblin.field_70170_p.field_73012_v.nextInt(3) != 0) {
/* 51 */       this.shouldBegin = true;
/* 52 */       this.posX = tile.field_145851_c;
/* 53 */       this.posY = tile.field_145848_d;
/* 54 */       this.posZ = tile.field_145849_e;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIWorship.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */