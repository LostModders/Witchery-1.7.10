/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFlyToWaypoint.CarryRequirement;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerFollowOwner;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
/*     */ import com.emoniph.witchery.entity.ai.EntityAIFlyingTempt;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.ChunkProviderFlat;
/*     */ import net.minecraft.world.gen.ChunkProviderGenerate;
/*     */ import net.minecraft.world.gen.ChunkProviderHell;
/*     */ import net.minecraft.world.gen.ChunkProviderServer;
/*     */ import net.minecraft.world.gen.structure.MapGenStructure;
/*     */ import net.minecraft.world.gen.structure.MapGenVillage;
/*     */ 
/*     */ public class EntitySpirit extends EntityFlyingTameable
/*     */ {
/*     */   public EntityAIFlyingTempt aiTempt;
/*  48 */   private int timeToLive = -1;
/*  49 */   private int spiritType = 0;
/*     */   
/*  51 */   private static final ItemStack[] TEMPTATIONS = { Witchery.Items.GENERIC.itemFocusedWill.createStack() };
/*     */   private static Field fieldStructureGenerators;
/*     */   private static Field fieldVillageGenerator;
/*     */   
/*  55 */   public EntitySpirit(World world) { super(world);
/*  56 */     func_70105_a(0.25F, 0.25F);
/*  57 */     func_70661_as().func_75495_e(true);
/*  58 */     this.field_70714_bg.func_75776_a(1, new com.emoniph.witchery.entity.ai.EntityAISitAndStay(this));
/*     */     
/*     */ 
/*     */ 
/*  62 */     this.field_70714_bg.func_75776_a(3, this.aiTempt = new EntityAIFlyingTempt(this, 0.6D, TEMPTATIONS, true));
/*  63 */     this.field_70714_bg.func_75776_a(5, new EntityAIFlyerFollowOwner(this, 1.0D, 14.0F, 5.0F));
/*     */     
/*  65 */     this.field_70714_bg.func_75776_a(8, new EntityAIFlyerFlyToWaypoint(this, EntityAIFlyerFlyToWaypoint.CarryRequirement.NONE));
/*  66 */     this.field_70714_bg.func_75776_a(9, new EntityAIFlyerLand(this, 0.8D, true));
/*  67 */     this.field_70714_bg.func_75776_a(10, new com.emoniph.witchery.entity.ai.EntityAIFlyerWander(this, 0.8D, 10.0D));
/*  68 */     this.field_70714_bg.func_75776_a(11, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 10.0F, 0.2F));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean func_70692_ba()
/*     */   {
/*  76 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setTarget(String target, int type)
/*     */   {
/*  83 */     this.timeToLive = com.emoniph.witchery.util.TimeUtil.secsToTicks(10);
/*  84 */     this.spiritType = type;
/*     */     try {
/*  86 */       if (target.equals("Village")) {
/*  87 */         IChunkProvider cp = this.field_70170_p.func_72863_F();
/*  88 */         while ((cp != null) && ((cp instanceof ChunkProviderServer))) {
/*  89 */           cp = ((ChunkProviderServer)cp).field_73246_d;
/*     */         }
/*     */         
/*  92 */         if (cp != null) {
/*  93 */           if ((cp instanceof ChunkProviderFlat)) {
/*  94 */             if (fieldStructureGenerators == null) {
/*  95 */               fieldStructureGenerators = ReflectionHelper.findField(ChunkProviderFlat.class, new String[] { "structureGenerators", "field_82696_f", "f" });
/*     */             }
/*     */             
/*  98 */             Iterator iterator = ((List)fieldStructureGenerators.get((ChunkProviderFlat)cp)).iterator();
/*  99 */             while (iterator.hasNext()) {
/* 100 */               if (setWaypointTo(iterator.next(), MapGenVillage.class)) {
/* 101 */                 return;
/*     */               }
/*     */             }
/* 104 */           } else if ((cp instanceof ChunkProviderGenerate)) {
/* 105 */             if (fieldVillageGenerator == null) {
/* 106 */               fieldVillageGenerator = ReflectionHelper.findField(ChunkProviderGenerate.class, new String[] { "villageGenerator", "field_73224_v", "v" });
/*     */             }
/*     */             
/* 109 */             if (fieldVillageGenerator != null) {
/* 110 */               setWaypointTo(fieldVillageGenerator.get((ChunkProviderGenerate)cp), MapGenVillage.class);
/*     */             }
/* 112 */           } else if ((cp instanceof ChunkProviderHell)) {
/* 113 */             setWaypointTo(((ChunkProviderHell)cp).field_73172_c);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (IllegalAccessException ex) {}
/*     */   }
/*     */   
/*     */   private boolean setWaypointTo(Object objStructure, Class<? extends MapGenStructure> clazz)
/*     */   {
/* 123 */     if ((objStructure != null) && (clazz.isAssignableFrom(objStructure.getClass()))) {
/* 124 */       setWaypointTo((MapGenStructure)objStructure);
/* 125 */       return true;
/*     */     }
/* 127 */     return false;
/*     */   }
/*     */   
/* 130 */   private void setWaypointTo(MapGenStructure mapStructure) { if (mapStructure != null) {
/* 131 */       ChunkPosition pos = mapStructure.func_151545_a(this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
/*     */       
/* 133 */       if (pos != null) {
/* 134 */         this.homeX = pos.field_151329_a;
/* 135 */         this.homeY = pos.field_151327_b;
/* 136 */         this.homeZ = pos.field_151328_c;
/* 137 */         this.waypoint = Witchery.Items.GENERIC.itemWaystone.createStack();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/* 144 */     super.func_70014_b(nbtRoot);
/* 145 */     nbtRoot.func_74768_a("SuicideIn", this.timeToLive);
/* 146 */     nbtRoot.func_74768_a("SpiritType", this.spiritType);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 151 */     super.func_70037_a(nbtRoot);
/* 152 */     if (nbtRoot.func_74764_b("SuicideIn")) {
/* 153 */       this.timeToLive = nbtRoot.func_74762_e("SuicideIn");
/*     */     } else {
/* 155 */       this.timeToLive = -1;
/*     */     }
/*     */     
/* 158 */     if (nbtRoot.func_74764_b("SpiritType")) {
/* 159 */       this.spiritType = nbtRoot.func_74762_e("SpiritType");
/*     */     } else {
/* 161 */       this.spiritType = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70629_bd()
/*     */   {
/* 167 */     func_70661_as().func_75499_g();
/* 168 */     super.func_70629_bd();
/* 169 */     if ((this.field_70170_p != null) && (!this.field_70128_L) && (!this.field_70170_p.field_72995_K) && (this.timeToLive != -1) && (--this.timeToLive == 0)) {
/* 170 */       ParticleEffect.EXPLODE.send(com.emoniph.witchery.util.SoundEffect.NONE, this, 1.0D, 1.0D, 16);
/* 171 */       func_70106_y();
/* 172 */       if (!this.field_70170_p.field_72995_K) {
/* 173 */         func_70628_a(false, 0);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/* 180 */     super.func_70088_a();
/* 181 */     this.field_70180_af.func_75682_a(21, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 186 */     return par1;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/* 191 */     super.func_110147_ax();
/* 192 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0D);
/* 193 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4D);
/* 194 */     func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
/* 195 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/* 200 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 206 */     if (this.spiritType == 2) return;
/*     */     ItemStack stack;
/* 208 */     ItemStack stack; if (this.spiritType == 1) {
/* 209 */       stack = Witchery.Items.GENERIC.itemSubduedSpiritVillage.createStack();
/*     */     } else {
/* 211 */       stack = Witchery.Items.GENERIC.itemSubduedSpirit.createStack();
/*     */     }
/*     */     
/* 214 */     func_70099_a(stack, 0.0F);
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 219 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 225 */     super.func_70071_h_();
/* 226 */     if (this.field_70170_p.field_72995_K) {
/* 227 */       int color = getFeatherColor();
/* 228 */       float red = 1.0F;
/* 229 */       float green = 0.8F;
/* 230 */       float blue = 0.0F;
/* 231 */       if (color > 0) {
/* 232 */         red = (color >> 16 & 0xFF) / 255.0F;
/* 233 */         green = (color >> 8 & 0xFF) / 255.0F;
/* 234 */         blue = (color & 0xFF) / 255.0F;
/*     */       }
/* 236 */       Witchery.proxy.generateParticle(this.field_70170_p, this.field_70165_t - this.field_70130_N * 0.5D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N, 0.1D + this.field_70163_u + this.field_70170_p.field_73012_v.nextDouble() * 0.2D, this.field_70161_v - this.field_70130_N * 0.5D + this.field_70170_p.field_73012_v.nextDouble() * this.field_70130_N, red, green, blue, 10, -0.1F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int func_70627_aG()
/*     */   {
/* 244 */     return super.func_70627_aG() * 2;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 249 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 254 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 259 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/* 264 */     return false;
/*     */   }
/*     */   
/*     */   public EntitySpirit spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/* 268 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_70877_b(ItemStack itemstack)
/*     */   {
/* 273 */     return (itemstack != null) && (itemstack.func_77973_b() == net.minecraft.init.Items.field_151103_aS);
/*     */   }
/*     */   
/*     */   public boolean func_70878_b(EntityAnimal par1EntityAnimal)
/*     */   {
/* 278 */     return false;
/*     */   }
/*     */   
/*     */   public int getFeatherColor() {
/* 282 */     return this.field_70180_af.func_75679_c(21);
/*     */   }
/*     */   
/*     */   public void setFeatherColor(int par1) {
/* 286 */     this.field_70180_af.func_75692_b(21, Integer.valueOf(par1));
/*     */   }
/*     */   
/*     */   public boolean func_70601_bi()
/*     */   {
/* 291 */     if (this.field_70170_p.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) {
/* 292 */       boolean superGetCanSpawnHere = (this.field_70170_p.func_72855_b(this.field_70121_D)) && (this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D));
/*     */       
/*     */ 
/*     */ 
/* 296 */       int i = MathHelper.func_76128_c(this.field_70165_t);
/* 297 */       int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 298 */       int k = MathHelper.func_76128_c(this.field_70161_v);
/* 299 */       superGetCanSpawnHere = (superGetCanSpawnHere) && (func_70783_a(i, j, k) >= 0.0F) && (j >= 60);
/*     */       
/* 301 */       net.minecraft.block.Block blockID = this.field_70170_p.func_147439_a(i, j - 1, k);
/*     */       
/* 303 */       return (superGetCanSpawnHere) && (this.field_70170_p.field_73012_v.nextInt(10) == 0) && ((blockID == Blocks.field_150349_c) || (blockID == Blocks.field_150354_m)) && (this.field_70170_p.func_72883_k(i, j, k) > 8);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 308 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/* 314 */     if (func_94056_bM()) {
/* 315 */       return func_94057_bL();
/*     */     }
/* 317 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.spirit.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 323 */     par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
/*     */     
/* 325 */     return par1EntityLivingData;
/*     */   }
/*     */   
/*     */   public EntityAgeable func_90011_a(EntityAgeable par1EntityAgeable)
/*     */   {
/* 330 */     return null;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntitySpirit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */