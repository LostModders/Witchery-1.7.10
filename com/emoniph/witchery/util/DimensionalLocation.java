/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ public class DimensionalLocation
/*    */ {
/*    */   public final int dimension;
/*    */   public final double posX;
/*    */   public final double posY;
/*    */   public final double posZ;
/*    */   public final boolean isValid;
/*    */   
/*    */   public DimensionalLocation(net.minecraft.entity.Entity entity) {
/* 14 */     this(entity.field_71093_bK, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, true);
/*    */   }
/*    */   
/*    */   public DimensionalLocation(NBTTagCompound nbtTag, String prefix) {
/* 18 */     this(nbtTag.func_74762_e(prefix + "D"), nbtTag.func_74769_h(prefix + "X"), nbtTag.func_74769_h(prefix + "Y"), nbtTag.func_74769_h(prefix + "Z"), (nbtTag.func_74764_b(prefix + "D")) && (nbtTag.func_74764_b(prefix + "X")) && (nbtTag.func_74764_b(prefix + "Y")) && (nbtTag.func_74764_b(prefix + "Z")));
/*    */   }
/*    */   
/*    */   public DimensionalLocation(int dimension, double posX, double posY, double posZ)
/*    */   {
/* 23 */     this(dimension, posX, posY, posZ, true);
/*    */   }
/*    */   
/*    */   protected DimensionalLocation(int dimension, double posX, double posY, double posZ, boolean isValid) {
/* 27 */     this.dimension = dimension;
/* 28 */     this.posX = posX;
/* 29 */     this.posY = posY;
/* 30 */     this.posZ = posZ;
/* 31 */     this.isValid = isValid;
/*    */   }
/*    */   
/*    */   public void saveToNBT(NBTTagCompound nbtTag, String prefix) {
/* 35 */     nbtTag.func_74768_a(prefix + "D", this.dimension);
/* 36 */     nbtTag.func_74780_a(prefix + "X", this.posX);
/* 37 */     nbtTag.func_74780_a(prefix + "Y", this.posY);
/* 38 */     nbtTag.func_74780_a(prefix + "Z", this.posZ);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/DimensionalLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */