/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*    */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class PotionWrappedInVine extends PotionBase implements IHandleLivingHurt, IHandleRenderLiving
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   private static ResourceLocation TEXTURE;
/*    */   
/*    */   public PotionWrappedInVine(int id, int color)
/*    */   {
/* 20 */     super(id, true, color);
/*    */   }
/*    */   
/*    */ 
/*    */   public void postContructInitialize()
/*    */   {
/* 26 */     setIncurable();
/*    */   }
/*    */   
/*    */   public boolean handleAllHurtEvents() {
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*    */   {
/* 35 */     if ((!event.entity.field_70170_p.field_72995_K) && 
/* 36 */       (event.source.func_76347_k())) {
/* 37 */       event.ammount *= Math.min(amplifier + 1, 4);
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
/*    */   {
/* 48 */     if (TEXTURE == null) {
/* 49 */       TEXTURE = new ResourceLocation("witchery", "textures/entities/vine_overlay.png");
/*    */     }
/* 51 */     GL11.glPushMatrix();
/* 52 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
/* 53 */     ModelOverlayRenderer.render(entity, event.x, event.y, event.z, event.renderer);
/* 54 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionWrappedInVine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */