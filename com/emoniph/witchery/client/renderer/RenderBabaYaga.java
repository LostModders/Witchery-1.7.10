/*     */ package com.emoniph.witchery.client.renderer;
/*     */ 
/*     */ import com.emoniph.witchery.client.model.ModelBabaYaga;
/*     */ import com.emoniph.witchery.entity.EntityBabaYaga;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
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
/*     */ public class RenderBabaYaga extends RenderLiving
/*     */ {
/*  26 */   private static final ResourceLocation TEXTURES = new ResourceLocation("witchery", "textures/entities/babayaga.png");
/*     */   private final ModelBabaYaga model;
/*     */   
/*     */   public RenderBabaYaga() {
/*  30 */     super(new ModelBabaYaga(0.0F), 0.5F);
/*  31 */     this.model = ((ModelBabaYaga)this.field_77045_g);
/*     */   }
/*     */   
/*     */   public void func_82412_a(EntityBabaYaga par1EntityBabaYaga, double par2, double par4, double par6, float par8, float par9) {
/*  35 */     ItemStack itemstack = par1EntityBabaYaga.func_70694_bm();
/*  36 */     this.model.field_82900_g = (itemstack != null);
/*  37 */     net.minecraft.entity.boss.BossStatus.func_82824_a(par1EntityBabaYaga, true);
/*  38 */     super.func_76986_a(par1EntityBabaYaga, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation getBabaYagaTextures(EntityBabaYaga par1EntityBabaYaga) {
/*  42 */     return TEXTURES;
/*     */   }
/*     */   
/*     */   protected void func_82411_a(EntityBabaYaga par1EntityBabaYaga, float par2) {
/*  46 */     float f1 = 1.0F;
/*  47 */     GL11.glColor3f(f1, f1, f1);
/*  48 */     super.func_77029_c(par1EntityBabaYaga, par2);
/*  49 */     ItemStack itemstack = par1EntityBabaYaga.func_70694_bm();
/*     */     
/*  51 */     if (itemstack != null) {
/*  52 */       GL11.glPushMatrix();
/*     */       
/*     */ 
/*  55 */       if (this.field_77045_g.field_78091_s) {
/*  56 */         float f2 = 0.5F;
/*  57 */         GL11.glTranslatef(0.0F, 0.625F, 0.0F);
/*  58 */         GL11.glRotatef(-20.0F, -1.0F, 0.0F, 0.0F);
/*  59 */         GL11.glScalef(f2, f2, f2);
/*     */       }
/*     */       
/*  62 */       this.model.field_82898_f.func_78794_c(0.0625F);
/*  63 */       GL11.glTranslatef(-0.0625F, 0.53125F, 0.21875F);
/*     */       
/*  65 */       if (((itemstack.func_77973_b() instanceof ItemBlock)) && (RenderBlocks.func_147739_a(Block.func_149634_a(itemstack.func_77973_b()).func_149645_b()))) {
/*  66 */         float f2 = 0.5F;
/*  67 */         GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
/*  68 */         f2 *= 0.75F;
/*  69 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/*  70 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  71 */         GL11.glScalef(f2, -f2, f2);
/*  72 */       } else if (itemstack.func_77973_b() == Items.field_151031_f) {
/*  73 */         float f2 = 0.625F;
/*  74 */         GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
/*  75 */         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
/*  76 */         GL11.glScalef(f2, -f2, f2);
/*  77 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  78 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*  79 */       } else if (itemstack.func_77973_b().func_77662_d()) {
/*  80 */         float f2 = 0.625F;
/*     */         
/*  82 */         if (itemstack.func_77973_b().func_77629_n_()) {
/*  83 */           GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*  84 */           GL11.glTranslatef(0.0F, -0.125F, 0.0F);
/*     */         }
/*     */         
/*  87 */         func_82410_b();
/*  88 */         GL11.glScalef(f2, -f2, f2);
/*  89 */         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
/*  90 */         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/*     */       } else {
/*  92 */         float f2 = 0.375F;
/*  93 */         GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
/*  94 */         GL11.glScalef(f2, f2, f2);
/*  95 */         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
/*  96 */         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
/*  97 */         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*     */       }
/*     */       
/* 100 */       GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
/* 101 */       GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
/* 102 */       this.field_76990_c.field_78721_f.func_78443_a(par1EntityBabaYaga, itemstack, 0);
/*     */       
/* 104 */       if (itemstack.func_77973_b().func_77623_v()) {
/* 105 */         this.field_76990_c.field_78721_f.func_78443_a(par1EntityBabaYaga, itemstack, 1);
/*     */       }
/*     */       
/* 108 */       GL11.glPopMatrix();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_82410_b() {
/* 113 */     GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */   
/*     */   protected void func_82409_b(EntityBabaYaga par1EntityBabaYaga, float par2) {
/* 117 */     float f1 = 0.9375F;
/* 118 */     GL11.glScalef(f1, f1, f1);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 123 */     func_82412_a((EntityBabaYaga)par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 128 */     func_82409_b((EntityBabaYaga)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2)
/*     */   {
/* 133 */     func_82411_a((EntityBabaYaga)par1EntityLivingBase, par2);
/*     */   }
/*     */   
/*     */   public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 138 */     func_82412_a((EntityBabaYaga)par1EntityLivingBase, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */   protected ResourceLocation func_110775_a(Entity par1Entity)
/*     */   {
/* 143 */     return getBabaYagaTextures((EntityBabaYaga)par1Entity);
/*     */   }
/*     */   
/*     */   public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 148 */     func_82412_a((EntityBabaYaga)par1Entity, par2, par4, par6, par8, par9);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBabaYaga.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */