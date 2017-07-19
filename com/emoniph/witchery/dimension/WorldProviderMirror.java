/*     */ package com.emoniph.witchery.dimension;
/*     */ 
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldProviderMirror
/*     */   extends WorldProvider
/*     */ {
/*     */   public WorldProviderMirror()
/*     */   {
/*  34 */     this.field_76576_e = true;
/*     */   }
/*     */   
/*     */   public int getActualHeight() {
/*  38 */     return Config.instance().shrinkMirrorWorld ? getHeight() : super.getActualHeight();
/*     */   }
/*     */   
/*     */   public String func_80007_l()
/*     */   {
/*  43 */     return "Mirror";
/*     */   }
/*     */   
/*     */   public IChunkProvider func_76555_c()
/*     */   {
/*  48 */     return new WorldChunkManagerMirror(this.field_76579_a);
/*     */   }
/*     */   
/*     */   public boolean func_76567_e()
/*     */   {
/*  53 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_76569_d()
/*     */   {
/*  58 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canDoLightning(Chunk chunk)
/*     */   {
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isBlockHighHumidity(int x, int y, int z)
/*     */   {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isDaytime()
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   public ChunkCoordinates getSpawnPoint()
/*     */   {
/*  78 */     return new ChunkCoordinates(4, 9, 4);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_76561_g()
/*     */   {
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_76556_a() {
/*  88 */     float f = 0.1F;
/*     */     
/*  90 */     for (int i = 0; i <= 15; i++) {
/*  91 */       float f1 = 1.0F - i / 15.0F;
/*  92 */       this.field_76573_f[i] = ((1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f);
/*     */     }
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_) {
/*  98 */     return Vec3.func_72443_a(0.0D, 0.03D, 0.1D);
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_76568_b(int p_76568_1_, int p_76568_2_)
/*     */   {
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   public float func_76563_a(long par1, float par3)
/*     */   {
/* 109 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
/*     */   {
/* 114 */     return super.getSkyColor(cameraEntity, partialTicks);
/*     */   }
/*     */   
/*     */   private static boolean isSafeBlock(World world, int posX, int posY, int posZ) {
/* 118 */     boolean base = BlockUtil.isSolid(world, posX, posY, posZ);
/* 119 */     boolean air1 = !BlockUtil.isSolid(world, posX, posY + 1, posZ);
/* 120 */     boolean air2 = !BlockUtil.isSolid(world, posX, posY + 2, posZ);
/* 121 */     boolean isSafe = (base) && (air1) && (air2);
/* 122 */     return isSafe;
/*     */   }
/*     */   
/*     */   public float getSunBrightnessFactor(float par1) {
/* 126 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public boolean canDoRainSnowIce(Chunk chunk)
/*     */   {
/* 131 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/dimension/WorldProviderMirror.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */