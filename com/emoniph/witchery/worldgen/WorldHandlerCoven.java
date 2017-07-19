/*    */ package com.emoniph.witchery.worldgen;
/*    */ 
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class WorldHandlerCoven
/*    */   implements IWorldGenHandler
/*    */ {
/*    */   private final double chance;
/*    */   private final int range;
/*    */   
/*    */   public WorldHandlerCoven(double chance, int range)
/*    */   {
/* 17 */     this.chance = chance;
/* 18 */     this.range = range;
/*    */   }
/*    */   
/*    */   public int getExtentX()
/*    */   {
/* 23 */     return 11;
/*    */   }
/*    */   
/*    */   public int getExtentZ()
/*    */   {
/* 28 */     return 11;
/*    */   }
/*    */   
/*    */   public int getRange()
/*    */   {
/* 33 */     return this.range;
/*    */   }
/*    */   
/*    */   public boolean generate(World world, Random random, int x, int z)
/*    */   {
/* 38 */     if ((Config.instance().generateCovens) && (random.nextDouble() < this.chance)) {
/* 39 */       int direction = random.nextInt(4);
/* 40 */       if (!new ComponentCoven(direction, random, x, z).addComponentParts(world, random)) {
/* 41 */         return false;
/*    */       }
/*    */       
/* 44 */       Log.instance().debug("coven " + x + " " + z + " dir=" + direction);
/*    */       
/* 46 */       return true;
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public void initiate() {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WorldHandlerCoven.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */