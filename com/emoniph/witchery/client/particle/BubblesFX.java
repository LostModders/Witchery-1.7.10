/*     */ package com.emoniph.witchery.client.particle;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ public class BubblesFX
/*     */   extends EntityFX
/*     */ {
/*  25 */   public static final ResourceLocation particles = new ResourceLocation("witchery:textures/particle/power.png");
/*     */   
/*  27 */   private boolean canMove = false;
/*     */   
/*     */   public BubblesFX(World world, double x, double y, double z)
/*     */   {
/*  31 */     super(world, x, y, z);
/*  32 */     this.field_70145_X = true;
/*     */   }
/*     */   
/*     */   public void func_70539_a(Tessellator tess, float partialTicks, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  37 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(particles);
/*  38 */     GL11.glDepthMask(false);
/*  39 */     GL11.glEnable(3042);
/*  40 */     GL11.glBlendFunc(770, 771);
/*  41 */     GL11.glAlphaFunc(516, 0.003921569F);
/*  42 */     tess.func_78382_b();
/*  43 */     tess.func_78380_c(func_70070_b(partialTicks));
/*     */     
/*  45 */     int typeIndex = 16;
/*  46 */     int par1 = typeIndex + this.field_70546_d * 8 / 20 % 16;
/*     */     
/*     */ 
/*  49 */     int particleTextureIndexX = par1 % 16;
/*  50 */     int particleTextureIndexY = par1 / 16;
/*     */     
/*  52 */     float f6 = particleTextureIndexX / 16.0F;
/*  53 */     float f7 = f6 + 0.0624375F;
/*  54 */     float f8 = particleTextureIndexY / 16.0F;
/*  55 */     float f9 = f8 + 0.0624375F;
/*     */     
/*  57 */     float scale = 0.1F * this.field_70544_f;
/*  58 */     float x = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * partialTicks - field_70556_an);
/*  59 */     float y = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * partialTicks - field_70554_ao);
/*  60 */     float z = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * partialTicks - field_70555_ap);
/*  61 */     tess.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 1.0F);
/*  62 */     tess.func_78374_a(x - par3 * scale - par6 * scale, y - par4 * scale, z - par5 * scale - par7 * scale, f7, f9);
/*  63 */     tess.func_78374_a(x - par3 * scale + par6 * scale, y + par4 * scale, z - par5 * scale + par7 * scale, f7, f8);
/*  64 */     tess.func_78374_a(x + par3 * scale + par6 * scale, y + par4 * scale, z + par5 * scale + par7 * scale, f6, f8);
/*  65 */     tess.func_78374_a(x + par3 * scale - par6 * scale, y - par4 * scale, z + par5 * scale - par7 * scale, f6, f9);
/*  66 */     tess.func_78381_a();
/*  67 */     GL11.glDisable(3042);
/*  68 */     GL11.glDepthMask(true);
/*  69 */     GL11.glAlphaFunc(516, 0.1F);
/*     */   }
/*     */   
/*     */   public void func_70071_h_()
/*     */   {
/*  74 */     if (!this.field_70170_p.field_72995_K) {
/*  75 */       func_70106_y();
/*     */     }
/*  77 */     this.field_70169_q = this.field_70165_t;
/*  78 */     this.field_70167_r = this.field_70163_u;
/*  79 */     this.field_70166_s = this.field_70161_v;
/*     */     
/*  81 */     if ((this.field_70546_d++ >= Math.min(this.field_70547_e, 600)) || (this.field_70546_d < 0)) {
/*  82 */       func_70106_y();
/*  83 */     } else if (this.field_70546_d > this.field_70547_e * 0.9D) {
/*  84 */       this.field_70145_X = false;
/*     */     }
/*     */     
/*  87 */     if ((!this.field_70128_L) && (this.canMove)) {
/*  88 */       this.field_70181_x -= 0.04D * this.field_70545_g;
/*  89 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       
/*  91 */       if (this.field_70122_E)
/*     */       {
/*  93 */         this.field_70159_w *= 0.699999988079071D;
/*  94 */         this.field_70179_y *= 0.699999988079071D;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int func_70537_b()
/*     */   {
/* 101 */     return 3;
/*     */   }
/*     */   
/*     */   public BubblesFX setMaxAge(int maxAge) {
/* 105 */     this.field_70547_e = maxAge;
/* 106 */     return this;
/*     */   }
/*     */   
/*     */   public BubblesFX setGravity(float gravity) {
/* 110 */     this.field_70545_g = gravity;
/* 111 */     return this;
/*     */   }
/*     */   
/*     */   public BubblesFX setCanMove(boolean canMove) {
/* 115 */     this.canMove = canMove;
/* 116 */     return this;
/*     */   }
/*     */   
/*     */   public BubblesFX setScale(float scale) {
/* 120 */     this.field_70544_f = scale;
/* 121 */     return this;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/particle/BubblesFX.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */