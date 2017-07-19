/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.model.ModelWitch;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*     */ public class RenderCovenWitch extends RenderLiving
/*     */ {
/*  26 */   private static final ResourceLocation BASIC_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch1.png");
/*  27 */   private static final ResourceLocation A_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch2.png");
/*  28 */   private static final ResourceLocation B_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch3.png");
/*  29 */   private static final ResourceLocation C_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch4.png");
/*  30 */   private static final ResourceLocation D_TEXTURE = new ResourceLocation("witchery", "textures/entities/covenwitch5.png");
/*     */   
/*     */   private final ModelWitch model;
/*     */   
/*     */ 
/*     */   public RenderCovenWitch()
/*     */   {
/*  37 */     super(new ModelWitch(0.0F), 0.5F);
/*  38 */     this.model = ((ModelWitch)this.field_77045_g);
/*     */   }
/*     */   
/*     */   public void func_82412_a(EntityCovenWitch par1EntityCovenWitch, double par2, double par4, double par6, float par8, float par9) {
/*  42 */     ItemStack itemstack = par1EntityCovenWitch.func_70694_bm();
/*  43 */     this.model.field_82900_g = (itemstack != null);
/*  44 */     super.func_76986_a(par1EntityCovenWitch, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation getCovenWitchTextures(EntityCovenWitch par1EntityCovenWitch) {
/*  48 */     switch (par1EntityCovenWitch.getTameSkin()) {
/*     */     case 0: 
/*     */     default: 
/*  51 */       return BASIC_TEXTURE;
/*     */     case 1: 
/*  53 */       return A_TEXTURE;
/*     */     case 2: 
/*  55 */       return B_TEXTURE;
/*     */     case 3: 
/*  57 */       return C_TEXTURE;
/*     */     }
/*  59 */     return D_TEXTURE;
/*     */   }
/*     */   
/*     */   protected void func_82411_a(EntityCovenWitch par1EntityCovenWitch, float par2)
/*     */   {
/*  64 */     float f1 = 1.0F;
/*  65 */     GL11.glColor3f(f1, f1, f1);
/*  66 */     super.func_77029_c(par1EntityCovenWitch, par2);
/*  67 */     ItemStack itemstack = par1EntityCovenWitch.func_70694_bm();
/*     */     
/*  69 */     if (itemstack != null) {
/*  70 */       GL11.glPushMatrix();
/*     */       
/*     */ 
/*  73 */       if (this.field_77045_g.field_78091_s) {
/*  74 */         float f2 = 0.5F;
/*  75 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/*  76 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/*  77 */         GL11.glScalef(f2, f2, f2);
/*     */       }
/*     */       
/*  80 */       this.model.field_82898_f.func_78794_c(0.0625F);
/*  81 */       GL11.glTranslatef(-0.0625F, 0.53125F, 0.21875F);
/*     */       
/*  83 */       if (((itemstack.func_77973_b() instanceof ItemBlock)) && (RenderBlocks.func_147739_a(Block.func_149634_a(itemstack.func_77973_b()).func_149645_b()))) {
/*  84 */         float f2 = 0.5F;
/*  85 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/*  86 */         f2 *= 0.75F;
/*  87 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/*  88 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  89 */         GL11.glScalef(f2, -f2, f2);
/*  90 */       } else if (itemstack.func_77973_b() == Items.field_151031_f) {
/*  91 */         float f2 = 0.625F;
/*  92 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/*  93 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/*  94 */         GL11.glScalef(f2, -f2, f2);
/*  95 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  96 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  97 */       } else if (itemstack.func_77973_b().func_77662_d()) {
/*  98 */         float f2 = 0.625F;
/*     */         
/* 100 */         if (itemstack.func_77973_b().func_77629_n_()) {
/* 101 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 102 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/* 105 */         func_82410_b();
/* 106 */         GL11.glScalef(f2, -f2, f2);
/* 107 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/* 108 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/* 110 */         float f2 = 0.375F;
/* 111 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/* 112 */         GL11.glScalef(f2, f2, f2);
/* 113 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/* 114 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/* 115 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/* 118 */       GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
/* 119 */       GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
/* 120 */       this.field_76990_c.field_78721_f.func_78443_a(par1EntityCovenWitch, itemstack, 0);
/*     */       
/* 122 */       if (itemstack.func_77973_b().func_77623_v()) {
/* 123 */         this.field_76990_c.field_78721_f.func_78443_a(par1EntityCovenWitch, itemstack, 1);
/*     */       }
/*     */       
/* 126 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82410_b() {
/* 131 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_82409_b(EntityCovenWitch par1EntityCovenWitch, float par2) {
/* 135 */     float f1 = 0.9375F;
/* 136 */     GL11.glScalef(f1, f1, f1);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 141 */     func_82412_a((EntityCovenWitch)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 146 */     func_82409_b((EntityCovenWitch)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 151 */     func_82411_a((EntityCovenWitch)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 156 */     func_82412_a((EntityCovenWitch)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 161 */     return getCovenWitchTextures((EntityCovenWitch)par1Entity);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 166 */     func_82412_a((EntityCovenWitch)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderCovenWitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */