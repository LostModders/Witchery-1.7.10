/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockFire;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent.Post;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class PotionHellishAura extends PotionBase implements IHandleRenderLiving
/*     */ {
/*     */   public PotionHellishAura(int id, int color)
/*     */   {
/*  28 */     super(id, color);
/*     */   }
/*     */   
/*     */   public boolean func_76397_a(int duration, int amplifier)
/*     */   {
/*  33 */     int frequencyFactor = 25;
/*  34 */     int k = frequencyFactor >> amplifier;
/*  35 */     return duration % k == 0;
/*     */   }
/*     */   
/*     */   public void func_76394_a(EntityLivingBase entity, int amplifier)
/*     */   {
/*  40 */     World world = entity.field_70170_p;
/*  41 */     if (!world.field_72995_K) {
/*  42 */       List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, entity.field_70121_D.func_72314_b(1.5D, 0.0D, 1.5D));
/*     */       
/*  44 */       for (EntityLivingBase otherEntity : entities) {
/*  45 */         if (entity != otherEntity) {
/*  46 */           otherEntity.func_70097_a(new EntityDamageSource(DamageSource.field_76370_b.field_76373_n, entity).func_76361_j().func_76348_h(), 1.0F);
/*     */           
/*  48 */           ParticleEffect.FLAME.send(SoundEffect.MOB_GHAST_FIREBALL, otherEntity, otherEntity.field_70130_N, otherEntity.field_70131_O, 16);
/*     */           
/*  50 */           if (amplifier > 0) {
/*  51 */             otherEntity.func_70015_d(amplifier);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier)
/*     */   {
/*  62 */     Entity p_76977_1_ = entity;
/*  63 */     double p_76977_2_ = entity.field_70165_t;
/*  64 */     double p_76977_4_ = entity.field_70163_u;
/*  65 */     double p_76977_6_ = entity.field_70161_v;
/*     */     
/*     */ 
/*  68 */     GL11.glDisable(2896);
/*  69 */     IIcon iicon = Blocks.field_150480_ab.func_149840_c(0);
/*  70 */     IIcon iicon1 = Blocks.field_150480_ab.func_149840_c(1);
/*  71 */     GL11.glPushMatrix();
/*     */     
/*     */ 
/*     */ 
/*  75 */     float f1 = p_76977_1_.field_70130_N * 1.4F;
/*  76 */     GL11.glScalef(f1, f1, f1);
/*     */     
/*  78 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  79 */     float f2 = 0.5F;
/*  80 */     float f3 = 0.0F;
/*  81 */     float f4 = p_76977_1_.field_70131_O / f1;
/*  82 */     float f5 = (float)(p_76977_1_.field_70163_u - p_76977_1_.field_70121_D.field_72338_b);
/*  83 */     GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
/*  84 */     GL11.glTranslatef(0.0F, 0.0F, -0.3F + (int)f4 * 0.02F);
/*     */     
/*  86 */     GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F);
/*  87 */     float f6 = 0.0F;
/*  88 */     int i = 0;
/*  89 */     tessellator.func_78382_b();
/*     */     
/*  91 */     while (f4 > 0.0F) {
/*  92 */       IIcon iicon2 = i % 2 == 0 ? iicon : iicon1;
/*  93 */       RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110575_b);
/*  94 */       float f7 = iicon2.func_94209_e();
/*  95 */       float f8 = iicon2.func_94206_g();
/*  96 */       float f9 = iicon2.func_94212_f();
/*  97 */       float f10 = iicon2.func_94210_h();
/*     */       
/*  99 */       if (i / 2 % 2 == 0) {
/* 100 */         float f11 = f9;
/* 101 */         f9 = f7;
/* 102 */         f7 = f11;
/*     */       }
/*     */       
/* 105 */       tessellator.func_78374_a(f2 - f3, 0.0F - f5, f6, f9, f10);
/*     */       
/* 107 */       tessellator.func_78374_a(-f2 - f3, 0.0F - f5, f6, f7, f10);
/*     */       
/* 109 */       tessellator.func_78374_a(-f2 - f3, 1.4F - f5, f6, f7, f8);
/*     */       
/* 111 */       tessellator.func_78374_a(f2 - f3, 1.4F - f5, f6, f9, f8);
/*     */       
/* 113 */       f4 -= 0.45F;
/* 114 */       f5 -= 0.45F;
/* 115 */       f2 *= 0.9F;
/* 116 */       f6 += 0.03F;
/* 117 */       i++;
/*     */     }
/*     */     
/* 120 */     tessellator.func_78381_a();
/* 121 */     GL11.glPopMatrix();
/*     */     
/* 123 */     GL11.glEnable(2896);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionHellishAura.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */