/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class PotionSpiked extends PotionBase implements IHandleLivingUpdate, IHandleRenderLiving
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   private static ResourceLocation TEXTURE;
/*    */   
/*    */   public PotionSpiked(int id, int color)
/*    */   {
/* 23 */     super(id, color);
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 28 */     if ((!world.field_72995_K) && (world.func_82737_E() % 5L == 3L)) {
/* 29 */       List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, entity.field_70121_D.func_72314_b(0.2D + 0.1D * amplifier, 0.0D, 0.2D + 0.1D * amplifier));
/*    */       
/* 31 */       for (EntityLivingBase otherEntity : entities) {
/* 32 */         if (otherEntity != entity) {
/* 33 */           otherEntity.func_70097_a(DamageSource.field_76367_g, 1 + amplifier);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
/*    */   {
/* 45 */     if (TEXTURE == null) {
/* 46 */       TEXTURE = new ResourceLocation("witchery", "textures/entities/cactus_overlay.png");
/*    */     }
/*    */     
/* 49 */     GL11.glPushMatrix();
/* 50 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
/* 51 */     ModelOverlayRenderer.render(entity, event.x, event.y, event.z, event.renderer);
/* 52 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionSpiked.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */