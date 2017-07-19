/*    */ package com.emoniph.witchery.worldgen;
/*    */ 
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.Log;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class WorldHandlerWickerMan
/*    */   implements IWorldGenHandler
/*    */ {
/*    */   private final double chance;
/*    */   private final int range;
/*    */   
/*    */   public WorldHandlerWickerMan(double chance, int range)
/*    */   {
/* 17 */     this.chance = chance;
/* 18 */     this.range = range;
/*    */   }
/*    */   
/*    */   public int getExtentX()
/*    */   {
/* 23 */     return 6;
/*    */   }
/*    */   
/*    */   public int getExtentZ()
/*    */   {
/* 28 */     return 5;
/*    */   }
/*    */   
/*    */   public int getRange()
/*    */   {
/* 33 */     return this.range;
/*    */   }
/*    */   
/*    */   public boolean generate(World world, Random random, int x, int z)
/*    */   {
/* 38 */     int direction = random.nextInt(4);
/* 39 */     if ((Config.instance().generateWickerMen) && (random.nextDouble() < this.chance)) {
/* 40 */       new ComponentWickerMan(direction, random, x, z).addComponentParts(world, random);
/* 41 */       Log.instance().debug("wickerman " + x + " " + z + " dir=" + direction);
/* 42 */       return true;
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */   
/*    */   public void initiate() {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WorldHandlerWickerMan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */