/*    */ package com.emoniph.witchery.brewing.potions;
/*    */ 
/*    */ import com.emoniph.witchery.client.model.ModelGrotesque;
/*    */ import com.emoniph.witchery.entity.EntityDemon;
/*    */ import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.IBossDisplayData;
/*    */ import net.minecraft.entity.monster.EntityGolem;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class PotionGrotesque extends PotionBase implements IHandleLivingUpdate, IHandleRenderLiving
/*    */ {
/*    */   @SideOnly(Side.CLIENT)
/*    */   private static ModelGrotesque DEMON_HEAD_MODEL;
/*    */   @SideOnly(Side.CLIENT)
/*    */   private static ResourceLocation TEXTURE;
/*    */   
/*    */   public PotionGrotesque(int id, int color)
/*    */   {
/* 29 */     super(id, color);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
/*    */   {
/* 40 */     if (DEMON_HEAD_MODEL == null) {
/* 41 */       DEMON_HEAD_MODEL = new ModelGrotesque();
/*    */     }
/*    */     
/* 44 */     if (TEXTURE == null) {
/* 45 */       TEXTURE = new ResourceLocation("witchery", "textures/entities/Demon.png");
/*    */     }
/* 47 */     GL11.glPushMatrix();
/* 48 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
/* 49 */     ModelOverlayRenderer.renderModel(event.entity, event.x, event.y, event.z, event.renderer, DEMON_HEAD_MODEL);
/*    */     
/* 51 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*    */   {
/* 56 */     if ((!world.field_72995_K) && (world.func_82737_E() % 5L == 3L)) {
/* 57 */       float radius = 4.0F;
/* 58 */       float radiusSq = 16.0F;
/* 59 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(entity.field_70165_t - 4.0D, entity.field_70163_u - 4.0D, entity.field_70161_v - 4.0D, entity.field_70165_t + 4.0D, entity.field_70163_u + 4.0D, entity.field_70161_v + 4.0D);
/*    */       
/* 61 */       java.util.List<EntityLiving> list = world.func_72872_a(EntityLiving.class, bounds);
/* 62 */       for (EntityLiving victim : list) {
/* 63 */         boolean canApply = (entity != victim) && (!(victim instanceof EntityDemon)) && (!(victim instanceof IBossDisplayData)) && (!(victim instanceof EntityGolem));
/*    */         
/* 65 */         if ((canApply) && (victim.func_70068_e(entity) < 16.0D)) {
/* 66 */           RiteProtectionCircleRepulsive.push(entity.field_70170_p, victim, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionGrotesque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */