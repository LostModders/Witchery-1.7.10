/*     */ package com.emoniph.witchery.brewing.potions;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.util.EntitySizeInfo;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent.Pre;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class PotionResizing extends PotionBase implements IHandlePreRenderLiving, IHandleRenderLiving, IHandleLivingUpdate, IHandleLivingHurt, IHandleLivingJump, IHandleLivingAttack
/*     */ {
/*     */   private static Method methodEntitySetSize;
/*     */   private static Method methodZombieSetSize;
/*     */   private static Method methodZombieSetSize2;
/*     */   private static Method methodAgeableSetSize;
/*     */   private static Method methodAgeableSetSize2;
/*     */   
/*     */   public PotionResizing(int id, int color)
/*     */   {
/*  35 */     super(id, color);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_111187_a(EntityLivingBase entity, BaseAttributeMap attributes, int amplifier)
/*     */   {
/*  44 */     EntitySizeInfo sizeInfo = new EntitySizeInfo(entity);
/*  45 */     setEntitySize(entity, sizeInfo.defaultWidth, sizeInfo.defaultHeight);
/*  46 */     if ((entity instanceof EntityPlayer)) {
/*  47 */       EntityPlayer player = (EntityPlayer)entity;
/*  48 */       player.eyeHeight = sizeInfo.eyeHeight;
/*     */     }
/*  50 */     entity.field_70138_W = sizeInfo.stepSize;
/*  51 */     Witchery.packetPipeline.sendToAll(new com.emoniph.witchery.network.PacketSyncEntitySize(entity));
/*  52 */     super.func_111187_a(entity, attributes, amplifier);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setEntitySize(Entity entity, float width, float height)
/*     */   {
/*     */     try
/*     */     {
/*  64 */       if ((entity instanceof EntityZombie)) {
/*  65 */         if (methodZombieSetSize == null) {
/*  66 */           methodZombieSetSize = ReflectionHelper.findMethod(EntityZombie.class, (EntityZombie)entity, new String[] { "setSize", "func_70105_a", "a" }, new Class[] { Float.TYPE, Float.TYPE });
/*     */         }
/*     */         
/*  69 */         if (methodZombieSetSize2 == null) {
/*  70 */           methodZombieSetSize2 = ReflectionHelper.findMethod(EntityZombie.class, (EntityZombie)entity, new String[] { "func_146069_a", "a" }, new Class[] { Float.TYPE });
/*     */         }
/*     */         
/*  73 */         methodZombieSetSize.invoke(entity, new Object[] { Float.valueOf(width), Float.valueOf(height) });
/*  74 */         methodZombieSetSize2.invoke(entity, new Object[] { Float.valueOf(1.0F) });
/*  75 */       } else if ((entity instanceof EntityAgeable)) {
/*  76 */         if (methodAgeableSetSize == null) {
/*  77 */           methodAgeableSetSize = ReflectionHelper.findMethod(EntityAgeable.class, (EntityAgeable)entity, new String[] { "setSize", "func_70105_a", "a" }, new Class[] { Float.TYPE, Float.TYPE });
/*     */         }
/*     */         
/*     */ 
/*  81 */         if (methodAgeableSetSize2 == null) {
/*  82 */           methodAgeableSetSize2 = ReflectionHelper.findMethod(EntityAgeable.class, (EntityAgeable)entity, new String[] { "setScale", "func_98055_j", "a" }, new Class[] { Float.TYPE });
/*     */         }
/*     */         
/*  85 */         methodAgeableSetSize.invoke(entity, new Object[] { Float.valueOf(width), Float.valueOf(height) });
/*  86 */         methodAgeableSetSize2.invoke(entity, new Object[] { Float.valueOf(1.0F) });
/*     */       } else {
/*  88 */         if (methodEntitySetSize == null) {
/*  89 */           methodEntitySetSize = ReflectionHelper.findMethod(Entity.class, entity, new String[] { "setSize", "func_70105_a", "a" }, new Class[] { Float.TYPE, Float.TYPE });
/*     */         }
/*     */         
/*  92 */         methodEntitySetSize.invoke(entity, new Object[] { Float.valueOf(width), Float.valueOf(height) });
/*     */       }
/*     */     }
/*     */     catch (IllegalAccessException ex) {}catch (IllegalArgumentException ex) {}catch (InvocationTargetException ex) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Pre event, int amplifier)
/*     */   {
/* 102 */     GL11.glPushMatrix();
/* 103 */     GL11.glTranslated(event.x, event.y, event.z);
/*     */     
/* 105 */     float scale = getModifiedScaleFactor(entity, amplifier);
/*     */     
/* 107 */     GL11.glScalef(scale, scale, scale);
/* 108 */     GL11.glTranslated(-event.x, -event.y, -event.z);
/*     */   }
/*     */   
/*     */   public static float getModifiedScaleFactor(EntityLivingBase entity, int amplifier)
/*     */   {
/* 113 */     float currentHeight = entity.field_70131_O;
/* 114 */     EntitySizeInfo sizeInfo = new EntitySizeInfo(entity);
/*     */     
/* 116 */     float ratio = currentHeight / sizeInfo.defaultHeight;
/* 117 */     float factor = getScaleFactor(amplifier);
/* 118 */     float scale = factor < 1.0F ? Math.max(ratio, factor) : Math.min(ratio, factor);
/* 119 */     return scale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onLivingRender(World world, EntityLivingBase entity, net.minecraftforge.client.event.RenderLivingEvent.Post event, int amplifier) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration)
/*     */   {
/* 130 */     float reductionFactor = 0.03F * (event.entity.field_70170_p.field_72995_K ? 1 : 20);
/* 131 */     if ((world.field_72995_K) || (entity.field_70173_aa % 20 == 0))
/*     */     {
/* 133 */       EntitySizeInfo sizeInfo = new EntitySizeInfo(entity);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 141 */       float scale = getScaleFactor(amplifier);
/* 142 */       float requiredHeight = sizeInfo.defaultHeight * scale;
/* 143 */       float requiredWidth = sizeInfo.defaultWidth * scale;
/* 144 */       float currentHeight = event.entityLiving.field_70131_O;
/*     */       
/* 146 */       if (requiredHeight != currentHeight) {
/* 147 */         if ((entity instanceof EntityPlayer)) {
/* 148 */           EntityPlayer player = (EntityPlayer)entity;
/* 149 */           if (!world.field_72995_K)
/* 150 */             player.eyeHeight = (currentHeight * 0.92F);
/*     */         }
/* 152 */         entity.field_70138_W = (scale < 1.0F ? 0.0F : scale - 1.0F);
/*     */         
/* 154 */         if (scale < 1.0F) {
/* 155 */           setEntitySize(entity, Math.max(entity.field_70130_N - reductionFactor, requiredWidth), Math.max(currentHeight - reductionFactor, requiredHeight));
/*     */         }
/*     */         else {
/* 158 */           setEntitySize(entity, Math.min(entity.field_70130_N + reductionFactor, requiredWidth), Math.min(currentHeight + reductionFactor, requiredHeight));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean handleAllHurtEvents()
/*     */   {
/* 166 */     return true;
/*     */   }
/*     */   
/*     */   public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier)
/*     */   {
/* 171 */     if (!world.field_72995_K) {
/* 172 */       PotionEffect effectDefender = entity.func_70660_b(this);
/* 173 */       boolean isDefenderShrunken = effectDefender != null;
/* 174 */       DamageSource source = event.source;
/* 175 */       if ((source.func_76355_l() == "mob") || (source.func_76355_l() == "player")) {
/* 176 */         if ((source.func_76346_g() != null) && ((source.func_76346_g() instanceof EntityLivingBase))) {
/* 177 */           EntityLivingBase attacker = (EntityLivingBase)source.func_76346_g();
/* 178 */           PotionEffect effectAttacker = attacker.func_70660_b(this);
/* 179 */           if ((isDefenderShrunken) || (effectAttacker != null)) {
/* 180 */             float scale = getDamageMultiplier(effectAttacker, effectDefender);
/* 181 */             event.ammount *= Math.max(Math.min(scale, 3.0F), 0.5F);
/*     */           }
/*     */         }
/* 184 */       } else if ((source == DamageSource.field_76379_h) && (isDefenderShrunken) && 
/* 185 */         (getScaleFactor(effectDefender.func_76458_c()) > event.ammount)) {
/* 186 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onLivingAttack(World world, EntityLivingBase entity, LivingAttackEvent event, int amplifier)
/*     */   {
/* 194 */     if ((Witchery.modHooks.isAM2Present) && (!world.field_72995_K) && (event.source == DamageSource.field_76368_d) && (amplifier <= 1) && ((entity instanceof EntityPlayer)))
/*     */     {
/* 196 */       if (!event.entity.field_70170_p.func_147439_a(MathHelper.func_76128_c(event.entity.field_70165_t), MathHelper.func_76128_c(event.entity.field_70163_u), MathHelper.func_76128_c(event.entity.field_70161_v)).func_149721_r())
/*     */       {
/*     */ 
/* 199 */         event.setCanceled(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static float getScaleFactor(int amplifier) {
/* 205 */     switch (amplifier) {
/*     */     case 0: 
/*     */     default: 
/* 208 */       return 0.25F;
/*     */     case 1: 
/* 210 */       return 0.4F;
/*     */     case 2: 
/* 212 */       return 2.0F;
/*     */     }
/* 214 */     return 3.0F;
/*     */   }
/*     */   
/*     */   private static int getSize(PotionEffect amplifier)
/*     */   {
/* 219 */     if (amplifier == null) {
/* 220 */       return 3;
/*     */     }
/* 222 */     switch (amplifier.func_76458_c()) {
/*     */     default: 
/* 224 */       return 3;
/*     */     case 0: 
/* 226 */       return 1;
/*     */     case 1: 
/* 228 */       return 2;
/*     */     case 2: 
/* 230 */       return 4;
/*     */     }
/* 232 */     return 5;
/*     */   }
/*     */   
/*     */   public static float getDamageMultiplier(PotionEffect amplifierA, PotionEffect amplifierB)
/*     */   {
/* 237 */     int sizeA = getSize(amplifierA);
/* 238 */     int sizeB = getSize(amplifierB);
/* 239 */     float sizeDiff = sizeA / sizeB;
/* 240 */     return sizeDiff;
/*     */   }
/*     */   
/*     */   public void onLivingJump(World world, EntityLivingBase entity, LivingEvent.LivingJumpEvent event, int amplifier)
/*     */   {
/* 245 */     float scale = getScaleFactor(amplifier);
/* 246 */     if (scale > 1.0F) {
/* 247 */       event.entityLiving.field_70181_x *= (scale * 0.5D + 0.5D);
/*     */     } else {
/* 249 */       event.entityLiving.field_70181_x *= Math.max(scale, 0.5D) * 1.5D;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/potions/PotionResizing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */