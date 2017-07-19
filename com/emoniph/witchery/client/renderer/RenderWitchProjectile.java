/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryItems;
/*    */ import com.emoniph.witchery.entity.EntityWitchProjectile;
/*    */ import com.emoniph.witchery.item.ItemGeneral;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderSnowball;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.IIcon;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderWitchProjectile extends RenderSnowball
/*    */ {
/*    */   public RenderWitchProjectile(Item item)
/*    */   {
/* 23 */     this(item, 0);
/*    */   }
/*    */   
/*    */   public RenderWitchProjectile(Item item, int damageValue) {
/* 27 */     super(item, damageValue);
/*    */   }
/*    */   
/*    */   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9)
/*    */   {
/* 32 */     if ((entity instanceof EntityWitchProjectile)) {
/* 33 */       EntityWitchProjectile entityProjectile = (EntityWitchProjectile)entity;
/* 34 */       IIcon icon = Witchery.Items.GENERIC.func_77617_a(entityProjectile.getDamageValue());
/*    */       
/* 36 */       if (icon != null) {
/* 37 */         GL11.glPushMatrix();
/* 38 */         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
/* 39 */         GL11.glEnable(32826);
/* 40 */         GL11.glScalef(0.5F, 0.5F, 0.5F);
/* 41 */         func_110777_b(entity);
/* 42 */         Tessellator tessellator = Tessellator.field_78398_a;
/*    */         
/* 44 */         if (entityProjectile.getDamageValue() != Witchery.Items.GENERIC.itemQuicklime.damageValue) {
/* 45 */           GL11.glPushMatrix();
/* 46 */           func_77026_22(tessellator, Items.field_151068_bn.func_77618_c(16384, 1));
/* 47 */           GL11.glPopMatrix();
/*    */         }
/*    */         
/* 50 */         func_77026_22(tessellator, icon);
/*    */         
/* 52 */         GL11.glDisable(32826);
/* 53 */         GL11.glPopMatrix();
/*    */       }
/*    */     } else {
/* 56 */       super.func_76986_a(entity, par2, par4, par6, par8, par9);
/*    */     }
/*    */   }
/*    */   
/*    */   private void func_77026_22(Tessellator par1Tessellator, IIcon par2Icon) {
/* 61 */     float f = par2Icon.func_94209_e();
/* 62 */     float f1 = par2Icon.func_94212_f();
/* 63 */     float f2 = par2Icon.func_94206_g();
/* 64 */     float f3 = par2Icon.func_94210_h();
/* 65 */     float f4 = 1.0F;
/* 66 */     float f5 = 0.5F;
/* 67 */     float f6 = 0.25F;
/* 68 */     GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 69 */     GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 70 */     par1Tessellator.func_78382_b();
/* 71 */     par1Tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 72 */     par1Tessellator.func_78374_a(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
/* 73 */     par1Tessellator.func_78374_a(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
/* 74 */     par1Tessellator.func_78374_a(f4 - f5, f4 - f6, 0.0D, f1, f2);
/* 75 */     par1Tessellator.func_78374_a(0.0F - f5, f4 - f6, 0.0D, f, f2);
/* 76 */     par1Tessellator.func_78381_a();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderWitchProjectile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */