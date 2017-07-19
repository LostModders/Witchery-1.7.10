/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMandrake extends EntityMob
/*     */ {
/*     */   public EntityMandrake(World world)
/*     */   {
/*  27 */     super(world);
/*  28 */     func_70661_as().func_75491_a(true);
/*  29 */     func_70661_as().func_75495_e(true);
/*  30 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  31 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, 1.0D, false));
/*  32 */     this.field_70714_bg.func_75776_a(3, new EntityAIWander(this, 0.8D));
/*  33 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  34 */     this.field_70714_bg.func_75776_a(5, new EntityAILookIdle(this));
/*  35 */     this.field_70715_bh.func_75776_a(1, new net.minecraft.entity.ai.EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  36 */     this.field_70715_bh.func_75776_a(2, new net.minecraft.entity.ai.EntityAIHurtByTarget(this, false));
/*  37 */     this.field_70728_aV = 0;
/*  38 */     func_70105_a(0.6F, 0.9F);
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  43 */     if (func_94056_bM()) {
/*  44 */       return func_94057_bL();
/*     */     }
/*  46 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.mandrake.name");
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  52 */     super.func_110147_ax();
/*  53 */     func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111263_d).func_111128_a(0.65D);
/*     */   }
/*     */   
/*     */   public boolean func_70650_aV()
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   public int func_82143_as()
/*     */   {
/*  63 */     return func_70638_az() == null ? 3 : 3 + (int)(func_110143_aJ() - 1.0F);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  68 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  73 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  78 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/*  83 */     return "mob.ghast.scream";
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/*  88 */     return "mob.ghast.scream";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/*  93 */     return "mob.ghast.death";
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity entity)
/*     */   {
/*  98 */     if ((!this.field_70170_p.field_72995_K) && 
/*  99 */       ((entity instanceof EntityPlayer))) {
/* 100 */       EntityPlayer player = (EntityPlayer)entity;
/* 101 */       if ((!player.func_70644_a(Potion.field_76431_k)) && (!com.emoniph.witchery.item.ItemEarmuffs.isHelmWorn(player))) {
/* 102 */         player.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 300, 1));
/*     */       }
/*     */     }
/* 105 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 110 */     func_70099_a(Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), 0.0F);
/* 111 */     func_70099_a(new ItemStack(Witchery.Items.SEEDS_MANDRAKE, this.field_70170_p.field_73012_v.nextDouble() <= 0.25D ? 2 : 1), 0.0F);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityMandrake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */