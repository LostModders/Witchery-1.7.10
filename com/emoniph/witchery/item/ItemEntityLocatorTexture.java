/*    */ package com.emoniph.witchery.item;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class ItemEntityLocatorTexture extends net.minecraft.client.renderer.texture.TextureCompass
/*    */ {
/*    */   public ItemEntityLocatorTexture()
/*    */   {
/* 10 */     super("witchery:entitylocator");
/*    */   }
/*    */   
/*    */   public void func_94241_a(World world, double playerX, double playerY, double playerZ, boolean p_94241_8_, boolean p_94241_9_)
/*    */   {
/* 15 */     if (!this.field_110976_a.isEmpty()) {
/* 16 */       double d3 = 0.0D;
/*    */       
/* 18 */       if ((world != null) && (!p_94241_8_)) {
/* 19 */         net.minecraft.util.ChunkCoordinates chunkcoordinates = world.func_72861_E();
/* 20 */         double d4 = chunkcoordinates.field_71574_a - playerX;
/* 21 */         double d5 = chunkcoordinates.field_71573_c - playerY;
/* 22 */         playerZ %= 360.0D;
/* 23 */         d3 = -((playerZ - 90.0D) * 3.141592653589793D / 180.0D - Math.atan2(d5, d4));
/*    */         
/* 25 */         if (!world.field_73011_w.func_76569_d()) {
/* 26 */           d3 = Math.random() * 3.141592653589793D * 2.0D;
/*    */         }
/*    */       }
/*    */       
/* 30 */       if (p_94241_9_) {
/* 31 */         this.field_94244_i = d3;
/*    */       }
/*    */       else
/*    */       {
/* 35 */         for (double d6 = d3 - this.field_94244_i; d6 < -3.141592653589793D; d6 += 6.283185307179586D) {}
/*    */         
/*    */ 
/*    */ 
/* 39 */         while (d6 >= 3.141592653589793D) {
/* 40 */           d6 -= 6.283185307179586D;
/*    */         }
/*    */         
/* 43 */         if (d6 < -1.0D) {
/* 44 */           d6 = -1.0D;
/*    */         }
/*    */         
/* 47 */         if (d6 > 1.0D) {
/* 48 */           d6 = 1.0D;
/*    */         }
/*    */         
/* 51 */         this.field_94242_j += d6 * 0.1D;
/* 52 */         this.field_94242_j *= 0.8D;
/* 53 */         this.field_94244_i += this.field_94242_j;
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 58 */       int i = (int)((this.field_94244_i / 6.283185307179586D + 1.0D) * this.field_110976_a.size()) % this.field_110976_a.size();
/* 59 */       while (i < 0) { i = (i + this.field_110976_a.size()) % this.field_110976_a.size();
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 64 */       if (i != this.field_110973_g) {
/* 65 */         this.field_110973_g = i;
/* 66 */         net.minecraft.client.renderer.texture.TextureUtil.func_147955_a((int[][])this.field_110976_a.get(this.field_110973_g), this.field_130223_c, this.field_130224_d, this.field_110975_c, this.field_110974_d, false, false);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemEntityLocatorTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */