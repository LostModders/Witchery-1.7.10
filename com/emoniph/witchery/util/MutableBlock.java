/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ public class MutableBlock
/*    */ {
/*    */   private final Block block;
/*    */   private final int metadata;
/*    */   private final int newMetadata;
/*    */   
/*    */   public MutableBlock(Block block) {
/* 12 */     this(block, -1, 0);
/*    */   }
/*    */   
/*    */   public MutableBlock(Block block, int metadata) {
/* 16 */     this(block, metadata, 0);
/*    */   }
/*    */   
/*    */   public MutableBlock(Block block, int metadata, int newMetadata) {
/* 20 */     this.block = block;
/* 21 */     this.metadata = metadata;
/* 22 */     this.newMetadata = newMetadata;
/*    */   }
/*    */   
/*    */   public MutableBlock(String extra) {
/* 26 */     String name = extra;
/* 27 */     int meta = 0;
/* 28 */     int comma = extra.lastIndexOf(',');
/* 29 */     if (comma >= 0) {
/* 30 */       name = extra.substring(0, comma);
/*    */       
/* 32 */       String metaString = extra.substring(comma + 1);
/* 33 */       meta = Integer.parseInt(metaString);
/*    */     }
/*    */     
/*    */ 
/* 37 */     this.block = Block.func_149684_b(name);
/* 38 */     this.metadata = meta;
/* 39 */     this.newMetadata = 0;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj)
/*    */   {
/* 44 */     if (obj == this) {
/* 45 */       return true;
/*    */     }
/* 47 */     if ((obj == null) || (obj.getClass() != getClass())) {
/* 48 */       return false;
/*    */     }
/* 50 */     MutableBlock other = (MutableBlock)obj;
/* 51 */     return (this.block == other.block) && ((this.metadata == -1) || (other.metadata == -1) || (this.metadata == other.metadata));
/*    */   }
/*    */   
/*    */   public void mutate(net.minecraft.world.World world, int posX, int posY, int posZ) {
/* 55 */     mutate(world, posX, posY, posZ, true);
/*    */   }
/*    */   
/*    */   public void mutate(net.minecraft.world.World world, int posX, int posY, int posZ, boolean allowAnyPlacement) {
/*    */     try {
/* 60 */       if (this.metadata != -1) {
/* 61 */         if ((allowAnyPlacement) || (this.block.func_149742_c(world, posX, posY, posZ))) {
/* 62 */           world.func_147465_d(posX, posY, posZ, this.block, this.metadata, 3);
/*    */         }
/* 64 */       } else if (this.newMetadata > 0) {
/* 65 */         if ((allowAnyPlacement) || (this.block.func_149742_c(world, posX, posY, posZ))) {
/* 66 */           world.func_147465_d(posX, posY, posZ, this.block, this.newMetadata, 3);
/*    */         }
/*    */       }
/* 69 */       else if ((allowAnyPlacement) || (this.block.func_149742_c(world, posX, posY, posZ))) {
/* 70 */         world.func_147449_b(posX, posY, posZ, this.block);
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 74 */       Log.instance().debug(String.format("Exception occured mutating a plant %s", new Object[] { e.toString() }));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/MutableBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */