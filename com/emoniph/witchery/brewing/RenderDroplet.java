/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderSnowball;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.IIcon;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderDroplet
/*    */   extends RenderSnowball
/*    */ {
/*    */   public RenderDroplet(Item item)
/*    */   {
/* 25 */     this(item, 0);
/*    */   }
/*    */   
/*    */   public RenderDroplet(Item item, int damageValue) {
/* 29 */     super(item, damageValue);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 34 */     EntityDroplet brew = (EntityDroplet)entity;
/* 35 */     IIcon icon = Witchery.Items.GENERIC.func_77617_a(Witchery.Items.GENERIC.itemQuartzSphere.damageValue);
/*    */     
/* 37 */     if (icon != null) {
/* 38 */       GL11.glPushMatrix();
/* 39 */       GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 40 */       GL11.glEnable(32826);
/* 41 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*    */       
/* 43 */       func_110777_b(entity);
/*    */       
/* 45 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 46 */       int color = brew.getColor();
/* 47 */       if (color != -1) {
/* 48 */         float red = (color >> 16 & 0xFF) / 255.0F;
/* 49 */         float green = (color >> 8 & 0xFF) / 255.0F;
/* 50 */         float blue = (color & 0xFF) / 255.0F;
/* 51 */         GL11.glColor3f(red, green, blue);
/*    */       }
/*    */       
/* 54 */       drawIcon(tessellator, icon);
/*    */       
/* 56 */       GL11.glDisable(32826);
/* 57 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */   private void drawIcon(Tessellator tessalator, IIcon icon) {
/* 62 */     float f = icon.func_94209_e();
/* 63 */     float f1 = icon.func_94212_f();
/* 64 */     float f2 = icon.func_94206_g();
/* 65 */     float f3 = icon.func_94210_h();
/* 66 */     float f4 = 1.0F;
/* 67 */     float f5 = 0.5F;
/* 68 */     float f6 = 0.25F;
/* 69 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/*    */     
/* 71 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 72 */     tessalator.func_78382_b();
/* 73 */     tessalator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 74 */     tessalator.func_78374_a(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
/* 75 */     tessalator.func_78374_a(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
/* 76 */     tessalator.func_78374_a(f4 - f5, f4 - f6, 0.0D, f1, f2);
/* 77 */     tessalator.func_78374_a(0.0F - f5, f4 - f6, 0.0D, f, f2);
/* 78 */     tessalator.func_78381_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/RenderDroplet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */