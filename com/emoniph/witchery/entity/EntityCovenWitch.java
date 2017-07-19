/*      */ package com.emoniph.witchery.entity;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*      */ import com.emoniph.witchery.entity.ai.EntityAIMoveToRestrictionAndSit;
/*      */ import com.emoniph.witchery.familiar.Familiar;
/*      */ import com.emoniph.witchery.infusion.Infusion;
/*      */ import com.emoniph.witchery.item.ItemGeneral;
/*      */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.network.PacketSound;
/*      */ import com.emoniph.witchery.ritual.RitualStep;
/*      */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*      */ import com.emoniph.witchery.util.ChatUtil;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.Coord;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.TameableUtil;
/*      */ import com.emoniph.witchery.util.TargetPointUtil;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.entity.DataWatcher;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.IRangedAttackMob;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*      */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*      */ import net.minecraft.entity.ai.EntityAIMoveIndoors;
/*      */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*      */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*      */ import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
/*      */ import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
/*      */ import net.minecraft.entity.ai.EntityAISit;
/*      */ import net.minecraft.entity.ai.EntityAITasks;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.monster.EntitySpider;
/*      */ import net.minecraft.entity.monster.EntityZombie;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.projectile.EntityPotion;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemPotion;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.common.util.ForgeDirection;
/*      */ 
/*      */ public class EntityCovenWitch extends EntityTameable implements IRangedAttackMob
/*      */ {
/*   72 */   private static final UUID field_110184_bp = UUID.fromString("DA2E2747-8768-4F9A-9135-258E93B077A4");
/*   73 */   private static final AttributeModifier field_110185_bq = new AttributeModifier(field_110184_bp, "Drinking speed penalty", -0.25D, 0).func_111168_a(false);
/*      */   
/*   75 */   private static final Item[] witchDrops = { Items.field_151114_aO, Items.field_151102_aT, Items.field_151137_ax, Items.field_151070_bp, Items.field_151069_bo, Items.field_151016_H, Items.field_151055_y, Items.field_151055_y };
/*      */   
/*      */ 
/*      */   private int witchAttackTimer;
/*      */   
/*   80 */   private String questOfferedTo = "";
/*   81 */   private boolean questAccepted = false;
/*   82 */   private int questType = 0;
/*   83 */   private int questItemsNeeded = 0;
/*   84 */   private int timeToLive = -1;
/*      */   public static final String COVEN_KEY = "WITCCoven";
/*      */   
/*   87 */   public EntityCovenWitch(World world) { super(world);
/*   88 */     func_70105_a(0.6F, 1.95F);
/*   89 */     func_70661_as().func_75491_a(true);
/*   90 */     func_70661_as().func_75495_e(true);
/*   91 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*   92 */     this.field_70714_bg.func_75776_a(1, this.field_70911_d);
/*   93 */     this.field_70714_bg.func_75776_a(2, new EntityAIMoveToRestrictionAndSit(this, 0.6D));
/*   94 */     this.field_70714_bg.func_75776_a(2, new EntityAIArrowAttack(this, 1.0D, 60, 10.0F));
/*      */     
/*      */ 
/*   97 */     this.field_70714_bg.func_75776_a(4, new EntityAIMoveIndoors(this));
/*   98 */     this.field_70714_bg.func_75776_a(5, new EntityAIRestrictOpenDoor(this));
/*   99 */     this.field_70714_bg.func_75776_a(6, new EntityAIOpenDoor(this, true));
/*  100 */     this.field_70714_bg.func_75776_a(7, new EntityAIMoveTowardsRestriction(this, 0.6D));
/*  101 */     this.field_70714_bg.func_75776_a(8, new net.minecraft.entity.ai.EntityAIWander(this, 1.0D));
/*  102 */     this.field_70714_bg.func_75776_a(9, new net.minecraft.entity.ai.EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  103 */     this.field_70714_bg.func_75776_a(10, new net.minecraft.entity.ai.EntityAILookIdle(this));
/*  104 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false));
/*  105 */     this.field_70715_bh.func_75776_a(2, new EntityAIOwnerHurtTarget(this));
/*  106 */     this.field_70715_bh.func_75776_a(3, new EntityAIHurtByTarget(this, true));
/*      */     
/*  108 */     setTameSkin(this.field_70146_Z.nextInt(5));
/*  109 */     func_70903_f(false);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean func_70601_bi()
/*      */   {
/*  115 */     boolean living = (this.field_70170_p.func_72855_b(this.field_70121_D)) && (this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()) && (!this.field_70170_p.func_72953_d(this.field_70121_D));
/*      */     
/*      */ 
/*  118 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/*  119 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  120 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*  121 */     boolean creature = (living) && (func_70783_a(i, j, k) >= 0.0F) && ((Config.instance().covenWitchSpawnWeight == 1) || (this.field_70170_p.field_73012_v.nextInt(Config.instance().covenWitchSpawnWeight) == 0));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  129 */     return creature;
/*      */   }
/*      */   
/*      */ 
/*      */   public void func_94058_c(String par1Str) {}
/*      */   
/*      */ 
/*      */   private void setInnerCustomNameTag(String s)
/*      */   {
/*  138 */     this.field_70180_af.func_75692_b(10, s);
/*      */   }
/*      */   
/*      */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*      */   {
/*  143 */     setTameSkin(this.field_70170_p.field_73012_v.nextInt(5));
/*  144 */     return super.func_110161_a(par1EntityLivingData);
/*      */   }
/*      */   
/*      */   protected void func_70088_a()
/*      */   {
/*  149 */     super.func_70088_a();
/*  150 */     func_70096_w().func_75682_a(18, Byte.valueOf((byte)0));
/*  151 */     func_70096_w().func_75682_a(21, Byte.valueOf((byte)0));
/*      */   }
/*      */   
/*      */   protected void func_110147_ax()
/*      */   {
/*  156 */     super.func_110147_ax();
/*  157 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0D);
/*  158 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*      */   }
/*      */   
/*      */   public void func_70014_b(NBTTagCompound nbtRoot) {
/*  162 */     super.func_70014_b(nbtRoot);
/*  163 */     nbtRoot.func_74768_a("SkinType", getTameSkin());
/*  164 */     nbtRoot.func_74778_a("QuestOffered", this.questOfferedTo);
/*  165 */     nbtRoot.func_74757_a("QuestAccepted", this.questAccepted);
/*  166 */     nbtRoot.func_74768_a("QuestType", this.questType);
/*  167 */     nbtRoot.func_74768_a("QuestItemsNeeded", this.questItemsNeeded);
/*  168 */     nbtRoot.func_74768_a("SuicideIn", this.timeToLive);
/*      */   }
/*      */   
/*      */   public void func_70037_a(NBTTagCompound nbtRoot) {
/*  172 */     super.func_70037_a(nbtRoot);
/*  173 */     setTameSkin(nbtRoot.func_74762_e("SkinType"));
/*  174 */     this.questOfferedTo = nbtRoot.func_74779_i("QuestOffered");
/*  175 */     this.questAccepted = nbtRoot.func_74767_n("QuestAccepted");
/*  176 */     this.questType = nbtRoot.func_74762_e("QuestType");
/*  177 */     this.questItemsNeeded = nbtRoot.func_74762_e("QuestItemsNeeded");
/*  178 */     if (nbtRoot.func_74764_b("SuicideIn")) {
/*  179 */       this.timeToLive = nbtRoot.func_74762_e("SuicideIn");
/*      */     } else {
/*  181 */       this.timeToLive = -1;
/*      */     }
/*  183 */     if ((nbtRoot.func_74764_b("CustomName")) && (nbtRoot.func_74779_i("CustomName").length() > 0)) {
/*  184 */       setInnerCustomNameTag(nbtRoot.func_74779_i("CustomName"));
/*      */     }
/*      */   }
/*      */   
/*      */   public String func_70005_c_()
/*      */   {
/*  190 */     if (func_94056_bM()) {
/*  191 */       return func_94057_bL();
/*      */     }
/*  193 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.covenwitch.name");
/*      */   }
/*      */   
/*      */   public void setTimeToLive(int i)
/*      */   {
/*  198 */     this.timeToLive = i;
/*      */   }
/*      */   
/*      */ 
/*      */   public void func_70629_bd()
/*      */   {
/*  204 */     super.func_70629_bd();
/*  205 */     if ((this.field_70170_p != null) && (!this.field_70128_L) && (!this.field_70170_p.field_72995_K) && (this.timeToLive != -1)) {
/*  206 */       if (this.timeToLive > 0) {
/*  207 */         this.timeToLive -= 1;
/*      */       }
/*  209 */       if ((func_70638_az() == null) && (this.timeToLive == 0)) {
/*  210 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 1.0D, 16);
/*  211 */         func_70106_y();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static final int MAX_COVEN_SIZE = 6;
/*      */   
/*      */ 
/*      */   public void func_70645_a(DamageSource damageSource)
/*      */   {
/*  223 */     if ((!this.field_70170_p.field_72995_K) && (func_70909_n())) {
/*  224 */       Entity owner = func_70902_q();
/*  225 */       if ((owner != null) && ((owner instanceof EntityPlayer))) {
/*  226 */         EntityPlayer player = (EntityPlayer)owner;
/*  227 */         NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  228 */         if (nbtPlayer.func_74764_b("WITCCoven")) {
/*  229 */           NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*  230 */           for (int i = 0; i < nbtCovenList.func_74745_c(); i++) {
/*  231 */             NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(i);
/*  232 */             if (nbtWitch.func_74779_i("WitchName").equalsIgnoreCase(func_94057_bL())) {
/*  233 */               nbtCovenList.func_74744_a(i);
/*  234 */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  240 */     super.func_70645_a(damageSource);
/*      */   }
/*      */   
/*      */   public int getTameSkin() {
/*  244 */     return this.field_70180_af.func_75683_a(18);
/*      */   }
/*      */   
/*      */   public void setTameSkin(int par1) {
/*  248 */     this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)par1));
/*      */   }
/*      */   
/*      */   protected String func_70639_aQ()
/*      */   {
/*  253 */     return null;
/*      */   }
/*      */   
/*      */   protected String func_70621_aR()
/*      */   {
/*  258 */     return "mob.witch.hurt";
/*      */   }
/*      */   
/*      */   protected String func_70673_aS()
/*      */   {
/*  263 */     return "mob.witch.death";
/*      */   }
/*      */   
/*      */   public void setAggressive(boolean aggressive) {
/*  267 */     byte b0 = this.field_70180_af.func_75683_a(21);
/*      */     
/*  269 */     if (aggressive) {
/*  270 */       b0 = (byte)(b0 | 0x1);
/*      */     } else {
/*  272 */       b0 = (byte)(b0 & 0xFFFFFFFE);
/*      */     }
/*      */     
/*  275 */     this.field_70180_af.func_75692_b(21, Byte.valueOf(b0));
/*      */   }
/*      */   
/*      */   public boolean getAggressive() {
/*  279 */     return (this.field_70180_af.func_75683_a(21) & 0x1) != 0;
/*      */   }
/*      */   
/*      */   public void setQuestOffered(boolean aggressive) {
/*  283 */     byte b0 = this.field_70180_af.func_75683_a(21);
/*      */     
/*  285 */     if (aggressive) {
/*  286 */       b0 = (byte)(b0 | 0x4);
/*      */     } else {
/*  288 */       b0 = (byte)(b0 & 0xFFFFFFFB);
/*      */     }
/*      */     
/*  291 */     this.field_70180_af.func_75692_b(21, Byte.valueOf(b0));
/*      */   }
/*      */   
/*      */   public boolean isQuestOffered() {
/*  295 */     return (this.field_70180_af.func_75683_a(21) & 0x2) != 0;
/*      */   }
/*      */   
/*      */   public boolean func_70650_aV()
/*      */   {
/*  300 */     return true;
/*      */   }
/*      */   
/*      */   public void func_70636_d()
/*      */   {
/*  305 */     if (!this.field_70170_p.field_72995_K) {
/*  306 */       if (getAggressive()) {
/*  307 */         if (this.witchAttackTimer-- <= 0) {
/*  308 */           setAggressive(false);
/*  309 */           ItemStack itemstack = func_70694_bm();
/*  310 */           func_70062_b(0, (ItemStack)null);
/*      */           
/*  312 */           if ((itemstack != null) && (itemstack.func_77973_b() == Items.field_151068_bn)) {
/*  313 */             List list = Items.field_151068_bn.func_77832_l(itemstack);
/*      */             
/*  315 */             if (list != null) {
/*  316 */               Iterator iterator = list.iterator();
/*      */               
/*  318 */               while (iterator.hasNext()) {
/*  319 */                 PotionEffect potioneffect = (PotionEffect)iterator.next();
/*  320 */                 func_70690_d(new PotionEffect(potioneffect));
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*  325 */           func_110148_a(SharedMonsterAttributes.field_111263_d).func_111124_b(field_110185_bq);
/*      */         }
/*      */       } else {
/*  328 */         short short1 = -1;
/*      */         
/*  330 */         if ((this.field_70146_Z.nextFloat() < 0.15F) && (func_70027_ad()) && (!func_70644_a(Potion.field_76426_n))) {
/*  331 */           short1 = 16307;
/*  332 */         } else if ((this.field_70146_Z.nextFloat() < 0.05F) && (func_110143_aJ() < func_110138_aP())) {
/*  333 */           short1 = 16341;
/*  334 */         } else if ((this.field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
/*      */         {
/*  336 */           short1 = 16274;
/*  337 */         } else if ((this.field_70146_Z.nextFloat() < 0.25F) && (func_70638_az() != null) && (!func_70644_a(Potion.field_76424_c)) && (func_70638_az().func_70068_e(this) > 121.0D))
/*      */         {
/*  339 */           short1 = 16274;
/*      */         }
/*      */         
/*  342 */         if (short1 > -1) {
/*  343 */           func_70062_b(0, new ItemStack(Items.field_151068_bn, 1, short1));
/*  344 */           this.witchAttackTimer = func_70694_bm().func_77988_m();
/*  345 */           setAggressive(true);
/*  346 */           IAttributeInstance attributeinstance = func_110148_a(SharedMonsterAttributes.field_111263_d);
/*  347 */           attributeinstance.func_111124_b(field_110185_bq);
/*  348 */           attributeinstance.func_111121_a(field_110185_bq);
/*      */         }
/*      */       }
/*      */       
/*  352 */       if (this.field_70146_Z.nextFloat() < 7.5E-4F) {
/*  353 */         this.field_70170_p.func_72960_a(this, (byte)15);
/*      */       }
/*      */     }
/*      */     
/*  357 */     super.func_70636_d();
/*      */   }
/*      */   
/*      */   protected float func_70672_c(DamageSource par1DamageSource, float par2)
/*      */   {
/*  362 */     par2 = super.func_70672_c(par1DamageSource, par2);
/*      */     
/*  364 */     if (par1DamageSource.func_76346_g() == this) {
/*  365 */       par2 = 0.0F;
/*      */     }
/*      */     
/*  368 */     if (par1DamageSource.func_82725_o()) {
/*  369 */       par2 = (float)(par2 * 0.15D);
/*      */     }
/*      */     
/*  372 */     return par2;
/*      */   }
/*      */   
/*      */   @SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
/*      */   public void func_70103_a(byte par1)
/*      */   {
/*  378 */     if (par1 == 15) {
/*  379 */       for (int i = 0; i < this.field_70146_Z.nextInt(35) + 10; i++) {
/*  380 */         this.field_70170_p.func_72869_a("witchMagic", this.field_70165_t + this.field_70146_Z.nextGaussian() * 0.12999999523162842D, this.field_70121_D.field_72337_e + 0.5D + this.field_70146_Z.nextGaussian() * 0.12999999523162842D, this.field_70161_v + this.field_70146_Z.nextGaussian() * 0.12999999523162842D, 0.0D, 0.0D, 0.0D);
/*      */       }
/*      */       
/*      */     }
/*      */     else {
/*  385 */       super.func_70103_a(par1);
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean func_70085_c(EntityPlayer player)
/*      */   {
/*  391 */     if ((!this.field_70170_p.field_72995_K) && (player != null)) {
/*  392 */       if ((!func_70909_n()) && (!getAggressive()) && (player.field_71093_bK != Config.instance().dimensionDreamID)) {
/*  393 */         if (this.questAccepted) {
/*  394 */           if (this.questOfferedTo.equalsIgnoreCase(player.func_70005_c_())) {
/*  395 */             if (isCovenFull(player)) {
/*  396 */               this.questAccepted = false;
/*  397 */               this.questType = 0;
/*  398 */               this.questOfferedTo = "";
/*  399 */               playWitchTalk(this.field_70170_p, this, 0.4F);
/*      */               
/*  401 */               ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.covenfull") }));
/*      */             }
/*  403 */             else if (isQuestItemForEntity(player.func_70694_bm(), this)) {
/*  404 */               player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c].field_77994_a -= 1;
/*  405 */               if (player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c].field_77994_a == 0) {
/*  406 */                 player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
/*      */               }
/*  408 */               if (--this.questItemsNeeded == 0) {
/*  409 */                 if (addToPlayerCoven(player)) {
/*  410 */                   ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.joinedcoven") }));
/*      */                 } else {
/*  412 */                   ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.tricked") }));
/*  413 */                   func_70604_c(player);
/*  414 */                   func_70624_b(player);
/*  415 */                   func_70784_b(player);
/*  416 */                   setAggressive(true);
/*  417 */                   this.questAccepted = false;
/*  418 */                   this.questType = 0;
/*  419 */                   this.questOfferedTo = "";
/*      */                 }
/*      */               } else {
/*  422 */                 ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), String.format(Witchery.resource("witchery.witch.say.questitemsremaining"), new Object[] { Integer.valueOf(this.questItemsNeeded).toString() }) }));
/*      */               }
/*  424 */               playWitchTalk(this.field_70170_p, this, 0.8F);
/*      */             } else {
/*  426 */               ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.questnotfinished") }));
/*  427 */               playWitchTalk(this.field_70170_p, this, 0.4F);
/*      */             }
/*      */           } else {
/*  430 */             ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.begone") }));
/*  431 */             playWitchTalk(this.field_70170_p, this, 0.4F);
/*      */           }
/*      */         }
/*  434 */         else if (!this.questOfferedTo.equalsIgnoreCase(player.func_70005_c_())) {
/*  435 */           func_110163_bv();
/*  436 */           if (!func_94056_bM()) {
/*  437 */             setInnerCustomNameTag(generateWitchName());
/*      */           }
/*  439 */           if (isCovenFull(player)) {
/*  440 */             playWitchTalk(this.field_70170_p, this, 0.4F);
/*  441 */             ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.covenfull") }));
/*  442 */           } else if (!Familiar.hasActiveFamiliar(player))
/*      */           {
/*      */             String s;
/*  445 */             switch (this.field_70170_p.field_73012_v.nextInt(3)) {
/*      */             case 0: 
/*      */             default: 
/*  448 */               s = Witchery.resource("witchery.witch.say.notinterested1");
/*  449 */               break;
/*      */             case 1: 
/*  451 */               s = Witchery.resource("witchery.witch.say.notinterested2");
/*  452 */               break;
/*      */             case 2: 
/*  454 */               s = Witchery.resource("witchery.witch.say.notinterested3");
/*      */             }
/*      */             
/*  457 */             playWitchTalk(this.field_70170_p, this, 0.4F);
/*  458 */             ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), s }));
/*      */           } else {
/*  460 */             this.questOfferedTo = player.func_70005_c_();
/*  461 */             this.questType = this.field_70170_p.field_73012_v.nextInt(QUESTS.length);
/*  462 */             this.questItemsNeeded = QUESTS[this.questType].getItemsNeeded();
/*  463 */             ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), QUESTS[this.questType].getDescriptionText() }));
/*  464 */             playWitchTalk(this.field_70170_p, this, 0.4F);
/*      */           }
/*  466 */         } else if (isCovenFull(player)) {
/*  467 */           this.questOfferedTo = "";
/*  468 */           playWitchTalk(this.field_70170_p, this, 0.4F);
/*  469 */           ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), Witchery.resource("witchery.witch.say.covenfull") }));
/*      */         } else {
/*  471 */           this.questAccepted = true;
/*  472 */           QUESTS[this.questType].activate(this.field_70170_p, this, player);
/*  473 */           if (!QUESTS[this.questType].getStartText().isEmpty()) {
/*  474 */             ChatUtil.sendPlain(EnumChatFormatting.BLUE, player, String.format("<%s> %s", new Object[] { func_94057_bL(), QUESTS[this.questType].getStartText() }));
/*      */           }
/*  476 */           playWitchTalk(this.field_70170_p, this, 1.0F);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  481 */       return true;
/*      */     }
/*  483 */     return false;
/*      */   }
/*      */   
/*      */   private boolean isCovenFull(EntityPlayer player)
/*      */   {
/*  488 */     return getCovenSize(player) >= 6;
/*      */   }
/*      */   
/*      */   public static int getCovenSize(EntityPlayer player) {
/*  492 */     if (player == null) {
/*  493 */       return 0;
/*      */     }
/*      */     
/*  496 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  497 */     if (!nbtPlayer.func_74764_b("WITCCoven")) {
/*  498 */       return 0;
/*      */     }
/*      */     
/*  501 */     NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*  502 */     return nbtCovenList.func_74745_c();
/*      */   }
/*      */   
/*      */   protected boolean func_70692_ba()
/*      */   {
/*  507 */     if (func_70909_n()) {
/*  508 */       Entity player = func_70902_q();
/*  509 */       if (player == null)
/*  510 */         return true;
/*  511 */       if (player.func_70068_e(this) > 4096.0D) {
/*  512 */         return true;
/*      */       }
/*  514 */       return super.func_70692_ba();
/*      */     }
/*      */     
/*  517 */     return super.func_70692_ba();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean addToPlayerCoven(EntityPlayer player)
/*      */   {
/*  527 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  528 */     if (!nbtPlayer.func_74764_b("WITCCoven")) {
/*  529 */       nbtPlayer.func_74782_a("WITCCoven", new NBTTagList());
/*      */     }
/*      */     
/*  532 */     NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*  533 */     if (isWitchInList(nbtCovenList)) {
/*  534 */       return false;
/*      */     }
/*      */     
/*  537 */     func_70903_f(true);
/*  538 */     TameableUtil.setOwner(this, player);
/*  539 */     NBTTagCompound nbtWitch = new NBTTagCompound();
/*  540 */     writeToPlayerNBT(nbtWitch);
/*  541 */     nbtCovenList.func_74742_a(nbtWitch);
/*  542 */     ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, this, 1.0D, 2.0D, 16);
/*  543 */     func_70106_y();
/*  544 */     return true;
/*      */   }
/*      */   
/*      */   private boolean isWitchInList(NBTTagList nbtCovenList) {
/*  548 */     for (int i = 0; i < nbtCovenList.func_74745_c(); i++) {
/*  549 */       NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(i);
/*  550 */       if (nbtWitch != null) {
/*  551 */         String name = nbtWitch.func_74779_i("WitchName");
/*  552 */         if ((name != null) && (name.equalsIgnoreCase(func_94057_bL()))) {
/*  553 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  557 */     return false;
/*      */   }
/*      */   
/*      */   private void writeToPlayerNBT(NBTTagCompound nbtWitch) {
/*  561 */     nbtWitch.func_74778_a("WitchName", func_94057_bL());
/*  562 */     nbtWitch.func_74768_a("Skin", getTameSkin());
/*  563 */     nbtWitch.func_74768_a("Quest", this.questType);
/*      */   }
/*      */   
/*      */   private void readFromPlayerNBT(NBTTagCompound nbtWitch) {
/*  567 */     setTameSkin(nbtWitch.func_74762_e("Skin"));
/*  568 */     this.questType = nbtWitch.func_74762_e("Quest");
/*  569 */     setInnerCustomNameTag(nbtWitch.func_74779_i("WitchName"));
/*      */   }
/*      */   
/*      */   public static void summonCoven(ArrayList<RitualStep> ritualSteps, World world, EntityPlayer player, int[][] pos) {
/*  573 */     double RADIUS_XZ = 64.0D;
/*  574 */     double RADIUS_Y = 16.0D;
/*  575 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 64.0D, player.field_70163_u - 16.0D, player.field_70161_v - 64.0D, player.field_70165_t + 64.0D, player.field_70163_u + 16.0D, player.field_70161_v + 64.0D);
/*      */     
/*  577 */     List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
/*  578 */     for (int index = 0; index < entities.size(); index++) {
/*  579 */       EntityCovenWitch witch = (EntityCovenWitch)entities.get(index);
/*  580 */       if ((witch.func_70909_n()) && (witch.func_70902_q() == player)) {
/*  581 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
/*  582 */         witch.func_70106_y();
/*      */       }
/*      */     }
/*      */     
/*  586 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  587 */     if (nbtPlayer.func_74764_b("WITCCoven")) {
/*  588 */       NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*  589 */       for (int index = 0; (index < nbtCovenList.func_74745_c()) && (index < pos.length); index++) {
/*  590 */         ritualSteps.add(new StepSummonCovenMemeber(player, index, pos[index]));
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static class StepSummonCovenMemeber extends RitualStep {
/*      */     private final int index;
/*      */     private final int[] position;
/*      */     
/*      */     public StepSummonCovenMemeber(EntityPlayer player, int index, int[] position) {
/*  600 */       super();
/*  601 */       this.index = index;
/*  602 */       this.position = position;
/*      */     }
/*      */     
/*      */     public RitualStep.Result process(World world, int xCoord, int yCoord, int zCoord, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*      */     {
/*  607 */       if (ticks % 20L != 0L) {
/*  608 */         return RitualStep.Result.STARTING;
/*      */       }
/*      */       
/*  611 */       EntityPlayer player = ritual.getInitiatingPlayer(world);
/*  612 */       if (player != null) {
/*  613 */         NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  614 */         if (nbtPlayer.func_74764_b("WITCCoven")) {
/*  615 */           double RADIUS_XZ = 64.0D;
/*  616 */           double RADIUS_Y = 16.0D;
/*  617 */           AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 64.0D, player.field_70163_u - 16.0D, player.field_70161_v - 64.0D, player.field_70165_t + 64.0D, player.field_70163_u + 16.0D, player.field_70161_v + 64.0D);
/*      */           
/*  619 */           List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
/*      */           
/*  621 */           NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*  622 */           NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(this.index);
/*  623 */           EntityCovenWitch witch = null;
/*  624 */           for (int index = 0; index < entities.size(); index++) {
/*  625 */             EntityCovenWitch witchAround = (EntityCovenWitch)entities.get(index);
/*  626 */             if ((witchAround.func_70909_n()) && (witchAround.func_70902_q() == player) && (witchAround.func_94057_bL().equalsIgnoreCase(nbtWitch.func_74779_i("WitchName")))) {
/*  627 */               witch = witchAround;
/*  628 */               break;
/*      */             }
/*      */           }
/*      */           
/*  632 */           boolean spawn = false;
/*  633 */           if (witch == null) {
/*  634 */             witch = new EntityCovenWitch(world);
/*  635 */             witch.readFromPlayerNBT(nbtWitch);
/*  636 */             witch.func_70903_f(true);
/*  637 */             TameableUtil.setOwner(witch, player);
/*  638 */             spawn = true;
/*      */           }
/*      */           
/*  641 */           float[] FACING = { -45.0F, 45.0F, -135.0F, 135.0F, 180.0F, 0.0F };
/*      */           
/*  643 */           witch.func_70080_a(0.5D + this.position[0], 0.01D + this.position[1], 0.5D + this.position[2], FACING[this.index], 0.0F);
/*      */           
/*  645 */           witch.field_70759_as = witch.field_70177_z;
/*  646 */           witch.field_70761_aq = witch.field_70177_z;
/*  647 */           witch.field_70126_B = witch.field_70177_z;
/*      */           
/*  649 */           witch.field_70911_d.func_75270_a(true);
/*  650 */           witch.setTimeToLive(300);
/*      */           
/*  652 */           if (spawn) {
/*  653 */             world.func_72838_d(witch);
/*      */           }
/*      */           
/*  656 */           ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
/*      */         }
/*      */       }
/*      */       
/*  660 */       return RitualStep.Result.COMPLETED;
/*      */     }
/*      */   }
/*      */   
/*      */   public static void summonCoven(World world, EntityPlayer player, Coord target, int ticks) {
/*  665 */     if ((ticks % 20 == 0) && (ticks / 20 > 0)) {
/*  666 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  667 */       if (nbtPlayer.func_74764_b("WITCCoven")) {
/*  668 */         double RADIUS_XZ = 64.0D;
/*  669 */         double RADIUS_Y = 16.0D;
/*  670 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 64.0D, player.field_70163_u - 16.0D, player.field_70161_v - 64.0D, player.field_70165_t + 64.0D, player.field_70163_u + 16.0D, player.field_70161_v + 64.0D);
/*      */         
/*  672 */         List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
/*      */         
/*  674 */         NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*  675 */         int witchIndex = ticks / 20 - 1;
/*  676 */         if (witchIndex > nbtCovenList.func_74745_c()) {
/*  677 */           return;
/*      */         }
/*  679 */         NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(witchIndex);
/*  680 */         EntityCovenWitch witch = null;
/*  681 */         for (int index = 0; index < entities.size(); index++) {
/*  682 */           EntityCovenWitch witchAround = (EntityCovenWitch)entities.get(index);
/*  683 */           if ((witchAround.func_70909_n()) && (witchAround.func_70902_q() == player) && (witchAround.func_94057_bL().equalsIgnoreCase(nbtWitch.func_74779_i("WitchName")))) {
/*  684 */             witch = witchAround;
/*  685 */             break;
/*      */           }
/*      */         }
/*      */         
/*  689 */         boolean spawn = false;
/*  690 */         if (witch == null) {
/*  691 */           witch = new EntityCovenWitch(world);
/*  692 */           witch.readFromPlayerNBT(nbtWitch);
/*  693 */           witch.func_70903_f(true);
/*  694 */           TameableUtil.setOwner(witch, player);
/*  695 */           spawn = true;
/*      */         }
/*      */         
/*  698 */         TileEntity closest = null;
/*  699 */         double bestDistSq = 0.0D;
/*  700 */         for (Object obj : world.field_147482_g) {
/*  701 */           TileEntity tile = (TileEntity)obj;
/*  702 */           if ((tile instanceof com.emoniph.witchery.brewing.TileEntityCauldron))
/*      */           {
/*  704 */             double dist = player.func_70092_e(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*  705 */             if ((closest == null) || (dist < bestDistSq)) {
/*  706 */               closest = tile;
/*  707 */               bestDistSq = dist;
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*  712 */         double cauldronRange = 9.0D;
/*  713 */         double cauldronRangeSq = 81.0D;
/*      */         
/*  715 */         if ((closest != null) && (bestDistSq <= 81.0D)) {
/*  716 */           witch.func_110171_b(closest.field_145851_c, closest.field_145848_d, closest.field_145849_e, 3);
/*  717 */           int maxRange = 3;
/*  718 */           int minRange = 1;
/*  719 */           int activeRadius = maxRange - minRange;
/*  720 */           int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  721 */           if (ax > activeRadius) {
/*  722 */             ax += minRange * 2;
/*      */           }
/*      */           
/*  725 */           int nx = closest.field_145851_c - maxRange + ax;
/*      */           
/*  727 */           int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
/*  728 */           if (az > activeRadius) {
/*  729 */             az += minRange * 2;
/*      */           }
/*      */           
/*  732 */           int nz = closest.field_145849_e - maxRange + az;
/*  733 */           witch.func_70080_a(nx, 0.01D + closest.field_145848_d, nz, 0.0F, 0.0F);
/*      */         } else {
/*  735 */           witch.func_70080_a(0.5D + target.x + world.field_73012_v.nextInt(3) - 1.5D, 0.01D + target.y, 0.5D + target.z + world.field_73012_v.nextInt(3) - 1.5D, 0.0F, 0.0F);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  742 */         witch.field_70911_d.func_75251_c();
/*  743 */         witch.setTimeToLive(1200);
/*      */         
/*  745 */         if (spawn) {
/*  746 */           world.func_72838_d(witch);
/*      */         }
/*      */         
/*  749 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static void summonCovenMember(World world, EntityPlayer player, int ttlSecs) {
/*  755 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  756 */     if (nbtPlayer.func_74764_b("WITCCoven")) {
/*  757 */       NBTTagList nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10);
/*  758 */       if (nbtCovenList.func_74745_c() > 0)
/*      */       {
/*  760 */         double RADIUS_XZ = 64.0D;
/*  761 */         double RADIUS_Y = 16.0D;
/*  762 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(player.field_70165_t - 64.0D, player.field_70163_u - 16.0D, player.field_70161_v - 64.0D, player.field_70165_t + 64.0D, player.field_70163_u + 16.0D, player.field_70161_v + 64.0D);
/*      */         
/*  764 */         List entities = world.func_72872_a(EntityCovenWitch.class, bounds);
/*  765 */         Collections.shuffle(entities);
/*  766 */         for (int index = 0; index < entities.size(); index++) {
/*  767 */           EntityCovenWitch witch = (EntityCovenWitch)entities.get(index);
/*  768 */           if ((witch.func_70909_n()) && (witch.func_70902_q() == player)) {
/*  769 */             int i = MathHelper.func_76128_c(player.field_70165_t) - 2;
/*  770 */             int j = MathHelper.func_76128_c(player.field_70161_v) - 2;
/*  771 */             int k = MathHelper.func_76128_c(player.field_70121_D.field_72338_b);
/*  772 */             for (int l = 0; l <= 4; l++) {
/*  773 */               for (int i1 = 0; i1 <= 4; i1++) {
/*  774 */                 if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (world.func_147439_a(i + l, k - 1, j + i1).isSideSolid(world, i + l, k - 1, j + i1, ForgeDirection.UP)) && (!world.func_147439_a(i + l, k, j + i1).func_149721_r()) && (!world.func_147439_a(i + l, k + 1, j + i1).func_149721_r()))
/*      */                 {
/*  776 */                   ItemGeneral.teleportToLocation(world, i + l + 0.5F, k, j + i1 + 0.5F, player.field_71093_bK, witch, true);
/*      */                   
/*  778 */                   witch.func_70661_as().func_75499_g();
/*  779 */                   return;
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  787 */         NBTTagCompound nbtWitch = nbtCovenList.func_150305_b(world.field_73012_v.nextInt(nbtCovenList.func_74745_c()));
/*  788 */         EntityCovenWitch witch = new EntityCovenWitch(world);
/*  789 */         int i = MathHelper.func_76128_c(player.field_70165_t) - 2;
/*  790 */         int j = MathHelper.func_76128_c(player.field_70161_v) - 2;
/*  791 */         int k = MathHelper.func_76128_c(player.field_70121_D.field_72338_b);
/*  792 */         for (int l = 0; l <= 4; l++) {
/*  793 */           for (int i1 = 0; i1 <= 4; i1++) {
/*  794 */             if (((l < 1) || (i1 < 1) || (l > 3) || (i1 > 3)) && (world.func_147439_a(i + l, k - 1, j + i1).isSideSolid(world, i + l, k - 1, j + i1, ForgeDirection.UP)) && (!world.func_147439_a(i + l, k, j + i1).func_149721_r()) && (!world.func_147439_a(i + l, k + 1, j + i1).func_149721_r()))
/*      */             {
/*  796 */               witch.func_70012_b(i + l + 0.5F, k, j + i1 + 0.5F, 0.0F, 0.0F);
/*      */               
/*      */ 
/*  799 */               break;
/*      */             }
/*      */           }
/*      */         }
/*  803 */         witch.func_70903_f(true);
/*  804 */         TameableUtil.setOwner(witch, player);
/*  805 */         witch.readFromPlayerNBT(nbtWitch);
/*  806 */         witch.setTimeToLive(ttlSecs * 20);
/*  807 */         world.func_72838_d(witch);
/*  808 */         ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, witch, 1.0D, 2.0D, 16);
/*      */       } else {
/*  810 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */       }
/*      */     } else {
/*  813 */       SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */     }
/*      */   }
/*      */   
/*      */   private static void playWitchTalk(World world, Entity where, float volume) {
/*  818 */     Witchery.packetPipeline.sendToAllAround(new PacketSound(world.field_73012_v.nextBoolean() ? SoundEffect.WITCHERY_MOB_BABA_DEATH : SoundEffect.WITCHERY_MOB_BABA_LIVING, where, 1.0F, 1.0F), TargetPointUtil.from(where, 8.0D));
/*      */   }
/*      */   
/*      */ 
/*  822 */   private static final Quest[] QUESTS = { new FightPetQuest(Witchery.resource("witchery.witch.quest.fightspider"), ""), new FightZombiePetQuest(Witchery.resource("witchery.witch.quest.fightzombie"), ""), new FetchQuest(Witchery.resource("witchery.witch.quest.getdemonheart"), Witchery.resource("witchery.witch.quest.go"), Witchery.Items.GENERIC.itemDemonHeart.createStack()), new FetchQuest(Witchery.resource("witchery.witch.quest.makecrystalball"), Witchery.resource("witchery.witch.quest.go"), new ItemStack(Witchery.Blocks.CRYSTAL_BALL)), new FetchQuest(Witchery.resource("witchery.witch.quest.getbones"), Witchery.resource("witchery.witch.quest.go"), new ItemStack(Items.field_151103_aS, 30)), new FetchQuest(Witchery.resource("witchery.witch.quest.makegrotesquebrew"), Witchery.resource("witchery.witch.quest.go"), Witchery.Items.GENERIC.itemBrewGrotesque.createStack(5)), new FetchQuest(Witchery.resource("witchery.witch.quest.makenecrostone"), Witchery.resource("witchery.witch.quest.go"), Witchery.Items.GENERIC.itemNecroStone.createStack()) };
/*      */   
/*      */ 
/*      */ 
/*      */   private static abstract class Quest
/*      */   {
/*      */     private final String questDescriptionText;
/*      */     
/*      */ 
/*      */     private final String questStartText;
/*      */     
/*      */     private final int itemsNeeded;
/*      */     
/*      */ 
/*      */     public Quest(String descriptionText, String startText, int itemsNeeded)
/*      */     {
/*  838 */       this.questStartText = startText;
/*  839 */       this.questDescriptionText = descriptionText;
/*  840 */       this.itemsNeeded = itemsNeeded;
/*      */     }
/*      */     
/*      */     public int getItemsNeeded() {
/*  844 */       return this.itemsNeeded;
/*      */     }
/*      */     
/*      */     public String getDescriptionText() {
/*  848 */       return this.questDescriptionText;
/*      */     }
/*      */     
/*      */     public String getStartText() {
/*  852 */       return this.questStartText;
/*      */     }
/*      */     
/*      */     public abstract void activate(World paramWorld, EntityCovenWitch paramEntityCovenWitch, EntityPlayer paramEntityPlayer);
/*      */     
/*      */     public boolean isQuestItem(ItemStack stack) {
/*  858 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class FetchQuest extends EntityCovenWitch.Quest {
/*      */     final ItemStack stack;
/*      */     
/*      */     public FetchQuest(String descriptionText, String startText, ItemStack stack) {
/*  866 */       super(startText, stack.field_77994_a);
/*  867 */       this.stack = stack;
/*      */     }
/*      */     
/*      */ 
/*      */     public void activate(World world, EntityCovenWitch witch, EntityPlayer player) {}
/*      */     
/*      */ 
/*      */     public boolean isQuestItem(ItemStack stack)
/*      */     {
/*  876 */       return this.stack.func_77969_a(stack);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class FightPetQuest extends EntityCovenWitch.Quest
/*      */   {
/*      */     public FightPetQuest(String descriptionText, String startText) {
/*  883 */       super(startText, 1);
/*      */     }
/*      */     
/*      */ 
/*      */     public void activate(World world, EntityCovenWitch witch, EntityPlayer player)
/*      */     {
/*  889 */       EntitySpider spider = new EntitySpider(world);
/*  890 */       spider.func_70012_b(witch.field_70165_t, witch.field_70163_u, witch.field_70161_v, witch.field_70125_A, witch.field_70177_z);
/*  891 */       world.func_72838_d(spider);
/*  892 */       spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/*  893 */       spider.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*  894 */       spider.func_70606_j((float)spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e());
/*  895 */       spider.func_70624_b(player);
/*  896 */       spider.func_70784_b(player);
/*  897 */       spider.func_70604_c(player);
/*  898 */       spider.func_94058_c(String.format(Witchery.resource("witchery.witch.pet"), new Object[] { witch.func_94057_bL() }));
/*      */       
/*  900 */       ItemStack stack = new ItemStack(Items.field_151070_bp);
/*  901 */       stack.func_151001_c(String.format(Witchery.resource("witchery.witch.peteye"), new Object[] { witch.func_94057_bL() }));
/*  902 */       if (!stack.func_77942_o()) {
/*  903 */         stack.func_77982_d(new NBTTagCompound());
/*      */       }
/*  905 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/*  906 */       nbtRoot.func_74772_a("WITCQuestOwnerIDLeast", witch.func_110124_au().getLeastSignificantBits());
/*  907 */       nbtRoot.func_74772_a("WITCQuestOwnerIDMost", witch.func_110124_au().getMostSignificantBits());
/*  908 */       NBTTagCompound nbtExtraDrop = new NBTTagCompound();
/*  909 */       stack.func_77955_b(nbtExtraDrop);
/*      */       
/*  911 */       NBTTagCompound nbtSpider = spider.getEntityData();
/*  912 */       if (!nbtSpider.func_74764_b("WITCExtraDrops")) {
/*  913 */         nbtSpider.func_74782_a("WITCExtraDrops", new NBTTagList());
/*      */       }
/*  915 */       NBTTagList nbtExtraDrops = nbtSpider.func_150295_c("WITCExtraDrops", 10);
/*  916 */       nbtExtraDrops.func_74742_a(nbtExtraDrop);
/*      */       
/*  918 */       ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, spider, 2.0D, 2.0D, 16);
/*      */     }
/*      */   }
/*      */   
/*      */   private static class FightZombiePetQuest extends EntityCovenWitch.Quest
/*      */   {
/*      */     public FightZombiePetQuest(String descriptionText, String startText) {
/*  925 */       super(startText, 1);
/*      */     }
/*      */     
/*      */     public void activate(World world, EntityCovenWitch witch, EntityPlayer player)
/*      */     {
/*  930 */       EntityZombie spider = new EntityZombie(world);
/*  931 */       spider.func_70012_b(witch.field_70165_t, witch.field_70163_u, witch.field_70161_v, witch.field_70125_A, witch.field_70177_z);
/*  932 */       world.func_72838_d(spider);
/*  933 */       spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0D);
/*  934 */       spider.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(5.0D);
/*  935 */       spider.func_70062_b(4, new ItemStack(Items.field_151144_bL));
/*  936 */       spider.func_70606_j((float)spider.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e());
/*  937 */       spider.func_70624_b(player);
/*  938 */       spider.func_70784_b(player);
/*  939 */       spider.func_70604_c(player);
/*  940 */       spider.func_94058_c(String.format(Witchery.resource("witchery.witch.pet"), new Object[] { witch.func_94057_bL() }));
/*      */       
/*  942 */       ItemStack stack = new ItemStack(Items.field_151078_bh);
/*  943 */       stack.func_151001_c(String.format(Witchery.resource("witchery.witch.petflesh"), new Object[] { witch.func_94057_bL() }));
/*  944 */       if (!stack.func_77942_o()) {
/*  945 */         stack.func_77982_d(new NBTTagCompound());
/*      */       }
/*  947 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/*  948 */       nbtRoot.func_74772_a("WITCQuestOwnerIDLeast", witch.func_110124_au().getLeastSignificantBits());
/*  949 */       nbtRoot.func_74772_a("WITCQuestOwnerIDMost", witch.func_110124_au().getMostSignificantBits());
/*  950 */       NBTTagCompound nbtExtraDrop = new NBTTagCompound();
/*  951 */       stack.func_77955_b(nbtExtraDrop);
/*      */       
/*  953 */       NBTTagCompound nbtSpider = spider.getEntityData();
/*  954 */       if (!nbtSpider.func_74764_b("WITCExtraDrops")) {
/*  955 */         nbtSpider.func_74782_a("WITCExtraDrops", new NBTTagList());
/*      */       }
/*  957 */       NBTTagList nbtExtraDrops = nbtSpider.func_150295_c("WITCExtraDrops", 10);
/*  958 */       nbtExtraDrops.func_74742_a(nbtExtraDrop);
/*      */       
/*  960 */       ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, spider, 2.0D, 2.0D, 16);
/*      */     }
/*      */   }
/*      */   
/*      */   private static boolean isQuestItemForEntity(ItemStack stack, EntityCovenWitch questGiver) {
/*  965 */     if ((questGiver != null) && (stack != null)) {
/*  966 */       if (QUESTS[questGiver.questType].isQuestItem(stack))
/*  967 */         return true;
/*  968 */       if (stack.func_77942_o()) {
/*  969 */         NBTTagCompound nbtRoot = stack.func_77978_p();
/*  970 */         if ((nbtRoot.func_74764_b("WITCQuestOwnerIDLeast")) && (nbtRoot.func_74764_b("WITCQuestOwnerIDMost"))) {
/*  971 */           UUID questGiverID = new UUID(nbtRoot.func_74763_f("WITCQuestOwnerIDMost"), nbtRoot.func_74763_f("WITCQuestOwnerIDLeast"));
/*      */           
/*  973 */           return questGiverID.equals(questGiver.getPersistentID());
/*      */         }
/*      */       }
/*      */     }
/*  977 */     return false;
/*      */   }
/*      */   
/*      */   protected void func_70628_a(boolean par1, int par2)
/*      */   {
/*  982 */     int j = this.field_70146_Z.nextInt(3) + 1;
/*      */     
/*  984 */     for (int k = 0; k < j; k++) {
/*  985 */       int l = this.field_70146_Z.nextInt(3);
/*  986 */       Item i1 = witchDrops[this.field_70146_Z.nextInt(witchDrops.length)];
/*      */       
/*  988 */       if (par2 > 0) {
/*  989 */         l += this.field_70146_Z.nextInt(par2 + 1);
/*      */       }
/*      */       
/*  992 */       for (int j1 = 0; j1 < l; j1++) {
/*  993 */         func_145779_a(i1, 1);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void func_82196_d(EntityLivingBase par1EntityLivingBase, float par2)
/*      */   {
/* 1000 */     if (!getAggressive()) {
/* 1001 */       EntityPotion entitypotion = new EntityPotion(this.field_70170_p, this, 32732);
/* 1002 */       entitypotion.field_70125_A -= -20.0F;
/* 1003 */       double d0 = par1EntityLivingBase.field_70165_t + par1EntityLivingBase.field_70159_w - this.field_70165_t;
/* 1004 */       double d1 = par1EntityLivingBase.field_70163_u + par1EntityLivingBase.func_70047_e() - 1.100000023841858D - this.field_70163_u;
/* 1005 */       double d2 = par1EntityLivingBase.field_70161_v + par1EntityLivingBase.field_70179_y - this.field_70161_v;
/* 1006 */       float f1 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
/*      */       
/* 1008 */       if ((f1 >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76421_d))) {
/* 1009 */         entitypotion.func_82340_a(32698);
/* 1010 */       } else if ((par1EntityLivingBase.func_110143_aJ() >= 8.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76436_u))) {
/* 1011 */         entitypotion.func_82340_a(32660);
/* 1012 */       } else if ((f1 <= 3.0F) && (!par1EntityLivingBase.func_70644_a(Potion.field_76437_t)) && (this.field_70146_Z.nextFloat() < 0.25F)) {
/* 1013 */         entitypotion.func_82340_a(32696);
/*      */       }
/*      */       
/* 1016 */       entitypotion.func_70186_c(d0, d1 + f1 * 0.2F, d2, 0.75F, 8.0F);
/* 1017 */       this.field_70170_p.func_72838_d(entitypotion);
/*      */     }
/*      */   }
/*      */   
/*      */   public EntityAgeable func_90011_a(EntityAgeable entityageable)
/*      */   {
/* 1023 */     return null;
/*      */   }
/*      */   
/*      */   public static String generateWitchName() {
/* 1027 */     Random ra = new Random();
/* 1028 */     return String.format("%s %s", new Object[] { FIRST_NAMES[ra.nextInt(FIRST_NAMES.length)], SURNAMES[ra.nextInt(SURNAMES.length)] });
/*      */   }
/*      */   
/* 1031 */   private static final String[] FIRST_NAMES = { "Abigail", "Agatha", "Agony", "Alcina", "Alcyone", "Alexandra", "Alexandria", "Alvira", "Amanita", "Amaranth", "Amarantha", "Ambrosia", "Amelia", "Amethyst", "Anastasia", "Andromeda", "Angel", "Angela", "Angelica", "Angelique", "Anna", "Arachne", "Aradia", "Aria", "Arianna", "Ariadne", "Ariel", "Artemis", "Artemisia", "Astrea", "Astrid", "Astoria", "Autumn", "Aurora", "Beatrix", "Bella", "Belladonna", "Belle", "Bernadette", "Beryl", "Bianca", "Blanche", "Bliss", "Calliope", "Callypso", "Calpurnia", "Camilla", "Carlotta", "Carmilla", "Caroline", "Carrie", "Cassandra", "Cassiopeia", "Catherine", "Cathy", "Cecelia", "Celeste", "Celia", "Charlotte", "Christine", "Circe", "Clara", "Claudia", "Cleopatra", "Columbia", "Coraline", "Cordelia", "Cornelia", "Crystal", "Daphne", "Daria", "Darla", "Delia", "Delilah", "Della", "Demetria", "Demonica", "Desdemona", "Desire", "Dolores", "Dora", "Dove", "Drusilla", "Dusk", "Ebony", "Echo", "Eden", "Elanore", "Electra", "Eldora", "Elena", "Eliza", "Eloise", "Elphaba", "Elspeth", "Elsinore", "Elvira", "Ember", "Emilie", "Ephemera", "Eranthe", "Eris", "Esmerelda", "Estrella", "Esther", "Eterna", "Eternity", "Eudora", "Euphemia", "Eva", "Evalina", "Evangeline", "Eve", "Granny", "Gabriella", "Gabrielle", "Garnet", "Genevieve", "Godiva", "Hathor", "Hecate", "Hecuba", "Helena", "Hepzibah", "Hesperia", "Hippolita", "Ianthe", "Icie", "Icy", "Inez", "Infinity", "Ione", "Iris", "Isabeau", "Isabella", "Isabelle", "Isadora", "Isis", "Isolde", "Istar", "Ivy", "Izora", "Jane", "Jeanette", "Jinx", "Jocasta", "Juliet", "Katrina", "Lavinia", "Layla", "Leona", "Lenora", "Lenore", "Leona", "Libitina", "Ligeia", "Lilah", "Lilith", "Lillian", "Lily", "Lolita", "Lorraine", "Lucinda", "Lucretia", "Luna", "Lydia", "Lyra", "Madeline", "Magdalena", "Magenta", "Mara", "Marcella", "Margaret", "Marguerita", "Maria", "Marie", "Marissa", "Martha", "Matilda", "Medea", "Medusa", "Melanie", "Melantha", "Melanthe", "Melinda", "Mercedes", "Merula", "Mildred", "Mina", "Minerva", "Miranda", "Miriam", "Moira", "Mordea", "Morgan", "Morgana", "Morticia", "Nadia", "Nadine", "Nerezza", "Nora", "Nyx", "Obsidia", "Octavia", "Odessa", "Olivia", "Opal", "Ophelia", "Pandora", "Patience", "Pearl", "Penelope", "Perenelle", "Permelia", "Persephone", "Pixie", "Phoenix", "Poppy", "Priscilla", "Prudence", "Rachel", "Rain", "Raven", "Regina", "Rita", "Rosa", "Rose", "Rosemary", "Rowena", "Ruby", "Sabrina", "Salem", "Samantha", "Sangria", "Scarlet", "Selena", "Selene", "Sephora", "Seraphina", "Serena", "Serenity", "Shannon", "Silver", "Simone", "Sophia", "Sybella", "Sybil", "Sylvia", "Tabitha", "Tempest", "Theda", "Theresa", "Thora", "Threnody", "Trinity", "Twilight", "Umbra", "Vaitiare", "Valerie", "Vanessa", "Verna", "Verona", "Veronica", "Vesta", "Victoria", "Violet", "Whisper", "Willow", "Winter", "Xenobia", "Zillah", "Zinnia" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1054 */   private static final String[] SURNAMES = { "Adams", "Addams", "Argent", "Ashwood", "Balfour", "Barker", "Batby", "Bathory", "Batsford", "Batson", "Batstone", "Batt", "Baudelaire", "Black", "Blackbird", "Blackburn", "Blackcat", "Blacklock", "Blackmoore", "Blackstone", "Blackthorn", "Blackwell", "Blackwing", "Blackwolf", "Blackwood", "Blair", "Blood", "Bloodgood", "Bloodhart", "Bloodmoore", "Bloodsaw", "Bloodstone", "Bloodsworth", "Bloodwine", "Bloodworth", "Boggart", "Boggarty", "Bonebrake", "Bonehart", "Bonehill", "Bonella", "Boneman", "Bones", "Bonesmith", "Bonewits", "Borden", "Broom", "Broomwood", "Burton", "Byron", "Cackler", "Cain", "Calamity", "Castle", "Castleton", "Cemetary", "Chill", "Chillingwood", "Cobweb", "Coffin", "Coffinberry", "Coffins", "Cold", "Coldbridge", "Coldeman", "Coldstone", "Coldwell", "Cole", "Collins", "Constantine", "Corbett", "Corbin", "Corpse", "Corpseley", "Creak", "Creakey", "Creep", "Creeper", "Creeps", "Crepsley", "Crimson", "Cross", "Crossway", "Crosswicks", "Crow", "Crowden", "Crowe", "Crowley", "Darcy", "Dark", "Darke", "Darken", "Darkenwald", "Darkes", "Darkmoore", "Darkwell", "Darkwood", "Deadman", "Deadmond", "Deadmore", "Deadrick", "Deadwood", "DeAngelus", "Dearborn", "Death", "Deathridge", "Deathrow", "December", "Delambre", "DeLioncourt", "Demond", "Demonde", "Demonte", "DeMort", "DeRavin", "Devall", "Devane", "DeVille", "DeWinter", "Dracul", "Drago", "Drake", "Dread", "Drear", "Dreary", "Drelincourt", "DuLac", "Dumaine", "Dunsany", "Eldritch", "Fang", "Fanger", "Fate", "Faust", "February", "Fear", "Fearfield", "Fears", "Frankenstein", "Frost", "Fury", "Gautier", "Ghoul", "Ghoulson", "Ghost", "Ghosten", "Ghostley", "Giger", "Goblin", "Goth", "Gotham", "Gothard", "Gothberg", "Gravedigger", "Gravemaker", "Graves", "Gravesen", "Gravesgard", "Grey", "Greyson", "Greystone", "Grimmauld", "Grimm", "Grimmer", "Grimmes", "Grimmins", "Grimsbro", "Grimsby", "Grimsman", "Grimwood", "Harker", "Hart", "Haunt", "Haunter", "Haunton", "Haunty", "Hawk", "Hawke", "Havelock", "Heart", "Heartstrom", "Hemlock", "Hex", "Hexem", "Hexter", "Hexwood", "Hollow", "Holmes", "Holmwood", "Hugo", "Hunter", "Hyde", "January", "Jekyll", "Kenrick", "Kilgore", "Killar", "Killewich", "Killings", "LaCroix", "Lapidus", "LaRue", "LeFay", "LeStrange", "LeStrange", "Locke", "London", "Loveless", "Lovelock", "Lovett", "Lycan", "MacBeth", "Mandrake", "Marrow", "Masters", "Mist", "Misteri", "Moan", "Moon", "Moones", "Moonie", "Moonly", "Monet", "Monster", "Monstery", "Montague", "Montresor", "Morgan", "Morgue", "Moriarty", "Murdoc", "Murray", "Morrow", "Mort", "Mortella", "Munster", "Mysterios", "Night", "Nightchase", "Nightengale", "Nightingdale", "Nightman", "Nightwalker", "Nightwine", "Nocton", "Nox", "October", "Odd", "Odder", "Oddman", "Oddson", "Owl", "Owley", "Owlford", "Owlsey", "Pale", "Pale", "Paine", "Pains", "Payne", "Plague", "Poe", "Poe", "Poe", "Pyre", "Pyre", "Pyre", "Radcliffe", "Rain", "Raven", "Ravencraft", "Ravendale", "Ravenhorst", "Ravensloft", "Ravenway", "Rayne", "Reaper", "Redbone", "Redcross", "Redd", "Redfern", "Redgrave", "Redmond", "Redwine", "Redwolf", "Renfield", "Riven", "Rookwood", "Roth", "Ripley", "Ripper", "Salvatore", "Scar", "Scare", "Scarebrook", "Scares", "Scarey", "Scarlati", "Setzer", "Seward", "Shade", "Shademoore", "Shadow", "Shadows", "Shadowton", "Shelley", "Skeleton", "Skelinen", "Skellington", "Skelton", "Skull", "Skullman", "Specter", "Spectre", "Spellman", "Spider", "Spinner", "Spirite", "Spook", "Spook", "Spook", "Song", "Snow", "St. Claire", "St. Germaine", "Steele", "Sterling", "Stoker", "Storm", "Storme", "Stormfelt", "Stormwind", "Stormyr", "Stone", "Stonewall", "Strange", "Strangeman", "Strangeway", "Striker", "Swan", "Swann", "Teeth", "Tombs", "Tombstone", "Towers", "Trick", "Valancourt", "Valdemar", "Valentine", "Valentino", "Vamper", "Vamplers", "Vampouille", "Vamprine", "Vampyr", "Van Allen", "Van Gogh", "Van Halen", "Van Helgen", "Van Helsing", "Voorhees", "Webb", "Weird", "Weird", "West", "Westenra", "White", "Whitebone", "Whitemoon", "Whitewing", "Widdowes", "Wild", "Wildblood", "Wilde", "Winchester", "Windgate", "Windholm", "Windward", "Wing", "Wingblade", "Wingfield", "Winter", "Winterford", "Winterrose", "Winterwood", "Winters", "Witche", "Witcher", "Witchery", "Witchey", "Witchman", "Wither", "Wolf", "Wolfen", "Wolfmann", "Wolfram", "Wolfstone", "Wolftooth" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void standStill()
/*      */   {
/* 1087 */     this.field_70911_d.func_75270_a(true);
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityCovenWitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */