/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelVillagerBed;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.opengl.GL11;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderVillagerBed
/*    */ {
/* 29 */   private static final ResourceLocation TEXTURE = new ResourceLocation("witchery", "textures/entities/villagerbed.png");
/*    */   
/*    */   private final ModelVillagerBed model;
/*    */   
/*    */   public RenderVillagerBed()
/*    */   {
/* 35 */     this.model = new ModelVillagerBed();
/*    */   }
/*    */   
/*    */   public void render(float x, float y, float z, int metadata) {
/* 39 */     GL11.glPushMatrix();
/* 40 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */     
/* 42 */     Minecraft.func_71410_x().field_71446_o.func_110577_a(TEXTURE);
/*    */     
/*    */ 
/* 45 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 52 */     GL11.glTranslatef(0.5F, 0.2F, -0.6F);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 77 */     this.model.func_78088_a((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/*    */     
/* 79 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderVillagerBed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */