/*   */ package com.emoniph.witchery.util;
/*   */ 
/*   */ import net.minecraft.entity.Entity;
/*   */ 
/*   */ public class EntityDamageSourceIndirectSilver extends net.minecraft.util.EntityDamageSourceIndirect
/*   */ {
/*   */   public EntityDamageSourceIndirectSilver(Entity directSource, Entity indirectSource) {
/* 8 */     super("indirectMagic", directSource, indirectSource);
/*   */   }
/*   */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/EntityDamageSourceIndirectSilver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */