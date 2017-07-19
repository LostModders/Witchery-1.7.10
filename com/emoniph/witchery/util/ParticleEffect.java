/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import com.emoniph.witchery.network.PacketParticles;
/*    */ import com.emoniph.witchery.network.PacketPipeline;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public enum ParticleEffect
/*    */ {
/* 11 */   HUGE_EXPLOSION("hugeexplosion"),  LARGE_EXPLODE("largeexplode"),  WATER_BUBBLE("bubble"),  SUSPENDED("suspended"),  DEPTH_SUSPEND("depthsuspend"), 
/* 12 */   TOWN_AURA("townaura"),  CRIT("crit"),  MAGIC_CRIT("magicCrit"),  SMOKE("smoke"),  MOB_SPELL("mobSpell"), 
/* 13 */   SPELL("spell"),  INSTANT_SPELL("instantSpell"),  NOTE("note"),  PORTAL("portal"),  ENCHANTMENT_TABLE("enchantmenttable"), 
/* 14 */   EXPLODE("explode"),  FLAME("flame"),  LAVA("lava"),  FOOTSTEP("footstep"),  SPLASH("splash"), 
/* 15 */   LARGE_SMOKE("largesmoke"),  CLOUD("cloud"),  REDDUST("reddust"),  SNOWBALL_POOF("snowballpoof"),  DRIP_WATER("dripWater"), 
/* 16 */   DRIP_LAVA("dripLava"),  SNOW_SHOVEL("snowshovel"),  SLIME("slime"),  HEART("heart"),  ICON_CRACK("iconcrack_"), 
/* 17 */   TILE_CRACK("tilecrack_"),  SPELL_COLORED("spell");
/*    */   
/*    */   final String particleID;
/*    */   
/*    */   private ParticleEffect(String particleID) {
/* 22 */     this.particleID = particleID;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 27 */     return this.particleID;
/*    */   }
/*    */   
/*    */   public void send(SoundEffect sound, World world, double x, double y, double z, double width, double height, int range)
/*    */   {
/* 32 */     send(sound, world, x, y, z, width, height, range, 16777215);
/*    */   }
/*    */   
/*    */   public void send(SoundEffect sound, World world, double x, double y, double z, double width, double height, int range, int color)
/*    */   {
/* 37 */     if (!world.field_72995_K) {
/* 38 */       com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, x, y, z, width, height, color), TargetPointUtil.from(world, x, y, z, range));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public void send(SoundEffect sound, Entity entity, double width, double height, int range)
/*    */   {
/* 45 */     if (!entity.field_70170_p.field_72995_K) {
/* 46 */       com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, entity, width, height), TargetPointUtil.from(entity, range));
/*    */     }
/*    */   }
/*    */   
/*    */   public void send(SoundEffect sound, Entity entity, double width, double height, int range, int color)
/*    */   {
/* 52 */     if (!entity.field_70170_p.field_72995_K) {
/* 53 */       com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, entity, width, height, color), TargetPointUtil.from(entity, range));
/*    */     }
/*    */   }
/*    */   
/*    */   public void send(SoundEffect sound, TileEntity tile, double width, double height, int range, int color)
/*    */   {
/* 59 */     if (!tile.func_145831_w().field_72995_K) {
/* 60 */       com.emoniph.witchery.Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, 0.5D + tile.field_145851_c, 0.5D + tile.field_145848_d, 0.5D + tile.field_145849_e, width, height, color), TargetPointUtil.from(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, range));
/*    */     }
/*    */   }
/*    */   
/*    */   public void send(SoundEffect sound, World world, Coord center, double width, double height, int range)
/*    */   {
/* 66 */     send(sound, world, center.x + 0.5D, center.y, center.z + 0.5D, width, height, range);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/ParticleEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */