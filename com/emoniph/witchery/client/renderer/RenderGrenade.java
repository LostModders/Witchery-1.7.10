/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.entity.EntityGrenade;
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
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderGrenade
/*    */   extends RenderSnowball
/*    */ {
/*    */   private final Item item;
/*    */   
/*    */   public RenderGrenade(Item item)
/*    */   {
/* 25 */     this(item, 0);
/*    */   }
/*    */   
/*    */   public RenderGrenade(Item item, int damageValue) {
/* 29 */     super(item, damageValue);
/* 30 */     this.item = item;
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 35 */     EntityGrenade brew = (EntityGrenade)entity;
/* 36 */     Item item = brew.getMode() == 0 ? Witchery.Items.SUN_GRENADE : Witchery.Items.DUP_GRENADE;
/* 37 */     IIcon icon = item.getIcon(null, 1);
/*    */     
/* 39 */     if (icon != null) {
/* 40 */       GL11.glPushMatrix();
/* 41 */       GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 42 */       GL11.glEnable(32826);
/* 43 */       GL11.glScalef(0.3F, 0.3F, 0.3F);
/*    */       
/* 45 */       func_110777_b(entity);
/*    */       
/* 47 */       Tessellator tessellator = Tessellator.field_78398_a;
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
/* 61 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 62 */       GL11.glPushMatrix();
/* 63 */       drawIcon(tessellator, item.getIcon(null, 0));
/* 64 */       GL11.glPopMatrix();
/*    */       
/*    */ 
/* 67 */       drawIcon(tessellator, icon);
/*    */       
/* 69 */       GL11.glDisable(32826);
/* 70 */       GL11.glPopMatrix();
/*    */     }
/*    */   }
/*    */   
/*    */   private void drawIcon(Tessellator tessalator, IIcon icon) {
/* 75 */     float f = icon.func_94209_e();
/* 76 */     float f1 = icon.func_94212_f();
/* 77 */     float f2 = icon.func_94206_g();
/* 78 */     float f3 = icon.func_94210_h();
/* 79 */     float f4 = 1.0F;
/* 80 */     float f5 = 0.5F;
/* 81 */     float f6 = 0.25F;
/* 82 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 83 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 84 */     tessalator.func_78382_b();
/* 85 */     tessalator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 86 */     tessalator.func_78374_a(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
/* 87 */     tessalator.func_78374_a(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
/* 88 */     tessalator.func_78374_a(f4 - f5, f4 - f6, 0.0D, f1, f2);
/* 89 */     tessalator.func_78374_a(0.0F - f5, f4 - f6, 0.0D, f, f2);
/* 90 */     tessalator.func_78381_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderGrenade.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */