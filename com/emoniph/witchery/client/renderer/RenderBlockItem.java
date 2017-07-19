/*    */ package com.emoniph.witchery.client.renderer;
/*    */ 
/*    */ import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraftforge.client.IItemRenderer.ItemRenderType;
/*    */ 
/*    */ public class RenderBlockItem implements net.minecraftforge.client.IItemRenderer
/*    */ {
/*    */   protected TileEntitySpecialRenderer render;
/*    */   protected TileEntity dummytile;
/*    */   
/*    */   public RenderBlockItem(TileEntitySpecialRenderer render, TileEntity dummy)
/*    */   {
/* 15 */     this.render = render;
/* 16 */     this.dummytile = dummy;
/*    */   }
/*    */   
/*    */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type)
/*    */   {
/* 21 */     return true;
/*    */   }
/*    */   
/*    */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, net.minecraftforge.client.IItemRenderer.ItemRendererHelper helper)
/*    */   {
/* 26 */     return true;
/*    */   }
/*    */   
/*    */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data)
/*    */   {
/* 31 */     if (type == IItemRenderer.ItemRenderType.ENTITY) {
/* 32 */       org.lwjgl.opengl.GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
/*    */     }
/* 34 */     this.render.func_147500_a(this.dummytile, 0.0D, 0.0D, 0.0D, 0.0F);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/renderer/RenderBlockItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */