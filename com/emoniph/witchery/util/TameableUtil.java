/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.server.management.ServerConfigurationManager;
/*    */ 
/*    */ public class TameableUtil
/*    */ {
/*    */   public static void setOwner(EntityTameable tameable, EntityPlayer owner)
/*    */   {
/* 15 */     if ((tameable != null) && (owner != null))
/*    */     {
/*    */ 
/*    */ 
/* 19 */       tameable.func_152115_b(owner.func_110124_au().toString());
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static void setOwnerByUsername(EntityTameable tameable, String ownerUsername)
/*    */   {
/* 28 */     EntityPlayer player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(ownerUsername);
/* 29 */     setOwner(tameable, player);
/*    */   }
/*    */   
/*    */   public static boolean isOwner(EntityTameable tameable, EntityPlayer player)
/*    */   {
/* 34 */     return tameable.func_152114_e(player);
/*    */   }
/*    */   
/*    */   public static boolean hasOwner(EntityTameable tameable) {
/* 38 */     String id = tameable.func_152113_b();
/* 39 */     return (id != null) && (!id.isEmpty());
/*    */   }
/*    */   
/*    */   public static net.minecraft.entity.EntityLivingBase getOwnerAccrossDimensions(EntityTameable tameable) {
/* 43 */     String id = tameable.func_152113_b();
/* 44 */     UUID uuid = UUID.fromString(id);
/* 45 */     return getPlayerByID(uuid);
/*    */   }
/*    */   
/*    */   public static EntityPlayerMP getPlayerByID(UUID uuid) {
/* 49 */     Iterator iterator = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b.iterator();
/*    */     EntityPlayerMP entityplayermp;
/*    */     do
/*    */     {
/* 53 */       if (!iterator.hasNext()) {
/* 54 */         return null;
/*    */       }
/*    */       
/* 57 */       entityplayermp = (EntityPlayerMP)iterator.next();
/* 58 */     } while (!entityplayermp.func_146103_bH().getId().equals(uuid));
/*    */     
/* 60 */     return entityplayermp;
/*    */   }
/*    */   
/*    */   public static void cloneOwner(EntityTameable tameable, EntityTameable tameableToCopyFrom) {
/* 64 */     tameable.func_152115_b(tameableToCopyFrom.func_152113_b());
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/TameableUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */