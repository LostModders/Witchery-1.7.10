/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderOcelot;
/*    */ import net.minecraft.entity.passive.EntityOcelot;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderWitchCat
/*    */   extends RenderOcelot
/*    */ {
/* 15 */   private static final ResourceLocation blackTextures = new ResourceLocation("witchery", "textures/entities/blackcat.png");
/*    */   
/*    */   public RenderWitchCat(ModelBase par1ModelBase, float par2) {
/* 18 */     super(par1ModelBase, par2);
/*    */   }
/*    */   
/*    */ 
/*    */   protected ResourceLocation func_110775_a(EntityOcelot par1EntityOcelot)
/*    */   {
/* 24 */     return blackTextures;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderWitchCat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */