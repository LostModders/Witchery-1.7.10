/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderSplatter extends RenderSnowball
/*    */ {
/*    */   public RenderSplatter(Item item)
/*    */   {
/* 21 */     this(item, 0);
/*    */   }
/*    */   
/*    */   public RenderSplatter(Item item, int damageValue) {
/* 25 */     super(item, damageValue);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 30 */     EntitySplatter brew = (EntitySplatter)entity;
/* 31 */     IIcon icon = Witchery.Items.GENERIC.func_77617_a(Witchery.Items.GENERIC.itemQuartzSphere.damageValue);
/*    */     
/*    */ 
/* 34 */     if (icon != null) {
/* 35 */       GL11.glPushMatrix();
/* 36 */       GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 37 */       GL11.glEnable(32826);
/* 38 */       GL11.glScalef(0.5F, 0.5F, 0.5F);
/*    */       
/* 40 */       func_110777_b(entity);
/*    */       
/* 42 */       Tessellator tessellator = Tessellator.field_78398_a;
/* 43 */       int color = brew.getColor();
/* 44 */       if (color != -1) {
/* 45 */         float red = (color >> 16 & 0xFF) / 255.0F;
/* 46 */         float green = (color >> 8 & 0xFF) / 255.0F;
/* 47 */         float blue = (color & 0xFF) / 255.0F;
/* 48 */         GL11.glColor3f(red, green, blue);
/*    */       }
/*    */       
/* 51 */       drawIcon(tessellator, icon);
/*    */       
/* 53 */       GL11.glDisable(32826);
/* 54 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */   private void drawIcon(Tessellator tessalator, IIcon icon) {
/* 59 */     float f = icon.func_94209_e();
/* 60 */     float f1 = icon.func_94212_f();
/* 61 */     float f2 = icon.func_94206_g();
/* 62 */     float f3 = icon.func_94210_h();
/* 63 */     float f4 = 1.0F;
/* 64 */     float f5 = 0.5F;
/* 65 */     float f6 = 0.25F;
/* 66 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 67 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 68 */     tessalator.func_78382_b();
/* 69 */     tessalator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 70 */     tessalator.func_78374_a(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
/* 71 */     tessalator.func_78374_a(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
/* 72 */     tessalator.func_78374_a(f4 - f5, f4 - f6, 0.0D, f1, f2);
/* 73 */     tessalator.func_78374_a(0.0F - f5, f4 - f6, 0.0D, f, f2);
/* 74 */     tessalator.func_78381_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/RenderSplatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */