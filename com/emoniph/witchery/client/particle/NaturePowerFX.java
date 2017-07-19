/*     */ package com.emoniph.witchery.client.particle;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class NaturePowerFX
/*     */   extends EntityFX
/*     */ {
/*  30 */   public static final ResourceLocation particles = new ResourceLocation("witchery:textures/particle/power.png");
/*     */   
/*  32 */   private boolean canMove = false;
/*  33 */   private boolean circling = false;
/*     */   
/*     */   public NaturePowerFX(World world, double x, double y, double z)
/*     */   {
/*  37 */     super(world, x, y, z);
/*  38 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*     */   public void func_70539_a(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  43 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(particles);
/*  44 */     GL11.glDepthMask(false);
/*  45 */     GL11.glEnable(3042);
/*  46 */     GL11.glBlendFunc(770, 771);
/*  47 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  48 */     tess.func_78382_b();
/*  49 */     tess.func_78380_c(func_70070_b(partialTicks));
/*     */     
/*  51 */     int typeIndex = 0;
/*  52 */     int par1 = typeIndex + this.field_70546_d * 8 / 20 % 16;
/*  53 */     par1 = par1 > 7 ? 15 - par1 : par1;
/*  54 */     int particleTextureIndexX = par1 % 16;
/*  55 */     int particleTextureIndexY = par1 / 16;
/*     */     
/*  57 */     float f6 = particleTextureIndexX / 16.0F;
/*  58 */     float f7 = f6 + 0.0624375F;
/*  59 */     float f8 = particleTextureIndexY / 16.0F;
/*  60 */     float f9 = f8 + 0.0624375F;
/*     */     
/*  62 */     float scale = 0.1F * this.field_70544_f;
/*  63 */     float x = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * partialTicks - field_70556_an);
/*  64 */     float y = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * partialTicks - field_70554_ao);
/*  65 */     float z = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * partialTicks - field_70555_ap);
/*  66 */     tess.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F);
/*  67 */     tess.func_78374_a(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, f7, f9);
/*  68 */     tess.func_78374_a(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, f7, f8);
/*  69 */     tess.func_78374_a(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, f6, f8);
/*  70 */     tess.func_78374_a(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, f6, f9);
/*  71 */     tess.func_78381_a();
/*  72 */     GL11.glDisable(3042);
/*  73 */     GL11.glDepthMask(true);
/*  74 */     GL11.glAlphaFunc(516, 0.1F);
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  79 */     if (!this.field_70170_p.field_72995_K) {
/*  80 */       func_70106_y();
/*     */     }
/*  82 */     this.field_70169_q = this.field_70165_t;
/*  83 */     this.field_70167_r = this.field_70163_u;
/*  84 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  86 */     if ((this.field_70546_d++ >= Math.min(this.field_70547_e, 600)) || (this.field_70546_d < 0)) {
/*  87 */       func_70106_y();
/*  88 */     } else if (this.field_70546_d > this.field_70547_e * 0.9D) {
/*  89 */       this.field_70145_X = false;
/*     */     }
/*     */     
/*  92 */     if ((!this.field_70128_L) && (this.canMove))
/*     */     {
/*  94 */       if (this.circling) {
/*  95 */         Vec3 motion = Vec3.func_72443_a(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*  96 */         motion.func_72442_b(0.5F);
/*     */         
/*     */ 
/*  99 */         this.field_70159_w = (motion.field_72450_a *= 1.08D);
/* 100 */         this.field_70181_x = (motion.field_72448_b *= 0.85D);
/* 101 */         this.field_70179_y = (motion.field_72449_c *= 1.08D);
/*     */       } else {
/* 103 */         this.field_70181_x -= 0.04D * this.field_70545_g;
/*     */       }
/* 105 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       
/* 107 */       if (this.field_70122_E)
/*     */       {
/* 109 */         this.field_70159_w *= 0.699999988079071D;
/* 110 */         this.field_70179_y *= 0.699999988079071D;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70537_b()
/*     */   {
/* 117 */     return 3;
/*     */   }
/*     */   
/*     */   public NaturePowerFX setMaxAge(int maxAge) {
/* 121 */     this.field_70547_e = maxAge;
/* 122 */     return this;
/*     */   }
/*     */   
/*     */   public NaturePowerFX setGravity(float gravity) {
/* 126 */     this.field_70545_g = gravity;
/* 127 */     return this;
/*     */   }
/*     */   
/*     */   public NaturePowerFX setCanMove(boolean canMove) {
/* 131 */     this.canMove = canMove;
/* 132 */     return this;
/*     */   }
/*     */   
/*     */   public NaturePowerFX setScale(float scale) {
/* 136 */     this.field_70544_f = scale;
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   public NaturePowerFX setCircling(boolean circling) {
/* 141 */     this.circling = circling;
/* 142 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/particle/NaturePowerFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */