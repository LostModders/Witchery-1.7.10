/*    */ package com.emoniph.witchery.worldgen;
/*    */ 
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import java.lang.reflect.Constructor;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldHandlerClonedStructure
/*    */   implements IWorldGenHandler
/*    */ {
/*    */   private final double chance;
/*    */   private final int range;
/*    */   private final int width;
/*    */   private final int height;
/*    */   private final int depth;
/*    */   Class<? extends ComponentClonedStructure> clazz;
/*    */   
/*    */   public WorldHandlerClonedStructure(Class<? extends ComponentClonedStructure> clazz, double chance, int range, int width, int height, int depth)
/*    */   {
/* 21 */     this.clazz = clazz;
/* 22 */     this.chance = chance;
/* 23 */     this.range = range;
/* 24 */     this.width = width;
/* 25 */     this.height = height;
/* 26 */     this.depth = depth;
/*    */   }
/*    */   
/*    */   public int getExtentX()
/*    */   {
/* 31 */     return this.width;
/*    */   }
/*    */   
/*    */   public int getExtentZ()
/*    */   {
/* 36 */     return this.depth;
/*    */   }
/*    */   
/*    */   public int getRange()
/*    */   {
/* 41 */     return this.range;
/*    */   }
/*    */   
/*    */   public boolean generate(World world, Random random, int x, int z)
/*    */   {
/* 46 */     if ((Config.instance().generateGoblinHuts) && (random.nextDouble() < this.chance)) {
/* 47 */       int direction = random.nextInt(4);
/*    */       try {
/* 49 */         Constructor ctor = this.clazz.getConstructor(new Class[] { Integer.TYPE, Random.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
/* 50 */         ComponentClonedStructure component = (ComponentClonedStructure)ctor.newInstance(new Object[] { Integer.valueOf(direction), random, Integer.valueOf(x), Integer.valueOf(z), Integer.valueOf(this.width), Integer.valueOf(this.height), Integer.valueOf(this.depth) });
/* 51 */         component.addComponentParts(world, random);
/*    */       }
/*    */       catch (NoSuchMethodException ex) {}catch (InvocationTargetException ex) {}catch (InstantiationException ex) {}catch (IllegalAccessException ex) {}
/*    */       
/*    */ 
/*    */ 
/* 57 */       return true;
/*    */     }
/* 59 */     return false;
/*    */   }
/*    */   
/*    */   public void initiate() {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WorldHandlerClonedStructure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */