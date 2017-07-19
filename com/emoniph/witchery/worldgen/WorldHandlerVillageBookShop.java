/*    */ package com.emoniph.witchery.worldgen;
/*    */ 
/*    */ import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
/*    */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldHandlerVillageBookShop
/*    */   implements VillagerRegistry.IVillageCreationHandler
/*    */ {
/*    */   public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int size)
/*    */   {
/* 23 */     return new StructureVillagePieces.PieceWeight(ComponentVillageBookShop.class, 15, 1 + (size > 2 ? random.nextInt(2) : 0));
/*    */   }
/*    */   
/*    */   public Class getComponentClass()
/*    */   {
/* 28 */     return ComponentVillageBookShop.class;
/*    */   }
/*    */   
/*    */ 
/*    */   public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
/*    */   {
/* 34 */     return ComponentVillageBookShop.construct(startPiece, pieces, random, p1, p2, p3, p4, p5);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/WorldHandlerVillageBookShop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */