/*      */ package com.emoniph.witchery.infusion.infusions.symbols;
/*      */ 
/*      */ import com.emoniph.witchery.Witchery;
/*      */ import com.emoniph.witchery.WitcheryBlocks;
/*      */ import com.emoniph.witchery.WitcheryItems;
/*      */ import com.emoniph.witchery.blocks.BlockWickerBundle;
/*      */ import com.emoniph.witchery.blocks.BlockWitchDoor;
/*      */ import com.emoniph.witchery.brewing.BlockCauldron;
/*      */ import com.emoniph.witchery.brewing.EntityBrew;
/*      */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*      */ import com.emoniph.witchery.brewing.potions.PotionEnslaved;
/*      */ import com.emoniph.witchery.brewing.potions.PotionIllFitting;
/*      */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*      */ import com.emoniph.witchery.dimension.WorldProviderTorment;
/*      */ import com.emoniph.witchery.entity.EntityDarkMark;
/*      */ import com.emoniph.witchery.entity.EntityEnt;
/*      */ import com.emoniph.witchery.entity.EntitySpellEffect;
/*      */ import com.emoniph.witchery.infusion.Infusion;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionLight;
/*      */ import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
/*      */ import com.emoniph.witchery.integration.ModHookManager;
/*      */ import com.emoniph.witchery.item.ItemChalk;
/*      */ import com.emoniph.witchery.item.ItemLeonardsUrn.InventoryLeonardsUrn;
/*      */ import com.emoniph.witchery.network.PacketPipeline;
/*      */ import com.emoniph.witchery.util.BlockProtect;
/*      */ import com.emoniph.witchery.util.BlockUtil;
/*      */ import com.emoniph.witchery.util.Config;
/*      */ import com.emoniph.witchery.util.DemonicDamageSource;
/*      */ import com.emoniph.witchery.util.EntityUtil;
/*      */ import com.emoniph.witchery.util.InvUtil;
/*      */ import com.emoniph.witchery.util.Log;
/*      */ import com.emoniph.witchery.util.ParticleEffect;
/*      */ import com.emoniph.witchery.util.SoundEffect;
/*      */ import com.emoniph.witchery.util.TimeUtil;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Hashtable;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockDoor;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.boss.IBossDisplayData;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityWitch;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.entity.player.PlayerCapabilities;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.item.ItemDoor;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EntityDamageSourceIndirect;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import net.minecraftforge.fluids.FluidRegistry;
/*      */ import net.minecraftforge.fluids.FluidStack;
/*      */ 
/*      */ public class EffectRegistry
/*      */ {
/*   74 */   private static final EffectRegistry INSTANCE = new EffectRegistry();
/*      */   
/*   76 */   public static final EffectRegistry instance() { return INSTANCE; }
/*      */   
/*      */ 
/*   79 */   private Hashtable<ByteBuffer, SymbolEffect> effects = new Hashtable();
/*   80 */   private Hashtable<ByteBuffer, Integer> enhanced = new Hashtable();
/*   81 */   private Hashtable<Integer, SymbolEffect> effectID = new Hashtable();
/*   82 */   private ArrayList<SymbolEffect> allEffects = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */   public SymbolEffect addEffect(SymbolEffect effect, StrokeSet... strokeSets)
/*      */   {
/*   88 */     for (StrokeSet strokes : strokeSets) {
/*   89 */       strokes.addTo(this.effects, this.enhanced, effect);
/*      */     }
/*      */     
/*   92 */     this.effectID.put(Integer.valueOf(effect.getEffectID()), effect);
/*   93 */     strokeSets[0].setDefaultFor(effect);
/*   94 */     this.allEffects.add(effect);
/*   95 */     return effect;
/*      */   }
/*      */   
/*      */   public boolean contains(byte[] strokes) {
/*   99 */     return getEffect(strokes) != null;
/*      */   }
/*      */   
/*      */   public SymbolEffect getEffect(byte[] strokes) {
/*  103 */     return (SymbolEffect)this.effects.get(ByteBuffer.wrap(strokes));
/*      */   }
/*      */   
/*      */   public SymbolEffect getEffect(int effectID) {
/*  107 */     return (SymbolEffect)this.effectID.get(Integer.valueOf(effectID));
/*      */   }
/*      */   
/*      */   public int getLevel(byte[] strokes) {
/*  111 */     return ((Integer)this.enhanced.get(ByteBuffer.wrap(strokes))).intValue();
/*      */   }
/*      */   
/*      */   public ArrayList<SymbolEffect> getEffects() {
/*  115 */     return this.allEffects;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  121 */   public static final SymbolEffect Accio = instance().addEffect(new SymbolEffectProjectile(1, "witchery.pott.accio") {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*      */       double R_SQ;
/*  124 */       if ((caster != null) && (mop != null)) {
/*  125 */         double R = spell.getEffectLevel() == 2 ? 3.0D : spell.getEffectLevel() == 1 ? 0.8D : 9.0D;
/*  126 */         R_SQ = R * R;
/*  127 */         AxisAlignedBB bb = AxisAlignedBB.func_72330_a(spell.field_70165_t - R, spell.field_70163_u - R, spell.field_70161_v - R, spell.field_70165_t + R, spell.field_70163_u + R, spell.field_70161_v + R);
/*  128 */         List entities = world.func_72872_a(EntityItem.class, bb);
/*  129 */         for (Object obj : entities) {
/*  130 */           EntityItem item = (EntityItem)obj;
/*  131 */           if (item.func_70068_e(spell) <= R_SQ) {
/*  132 */             item.func_70107_b(caster.field_70165_t, caster.field_70163_u + 1.0D, caster.field_70161_v);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  121 */   }.setColor(5322534).setSize(1.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 3, 0, 2, 2, 1 }), new StrokeSet(1, new byte[] { 3, 0, 2, 2, 2, 1 }), new StrokeSet(2, new byte[] { 3, 0, 0, 2, 2, 1, 1 }), new StrokeSet(2, new byte[] { 3, 0, 0, 2, 2, 2, 1, 1 }), new StrokeSet(3, new byte[] { 3, 0, 0, 0, 2, 2, 2, 1, 1, 1 }), new StrokeSet(3, new byte[] { 3, 0, 0, 0, 2, 2, 2, 2, 1, 1, 1 }) });
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
/*  147 */   public static final SymbolEffect Aguamenti = instance().addEffect(new SymbolEffectProjectile(2, "witchery.pott.aguamenti")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  150 */       if (((spell.getEffectLevel() == 1) && (!world.field_73011_w.field_76575_d)) || ((world.field_73011_w.field_76575_d) && (spell.getEffectLevel() == 3))) {
/*  151 */         if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
/*  152 */           setBlock(caster, world, MathHelper.func_76128_c(mop.field_72308_g.field_70165_t), MathHelper.func_76128_c(mop.field_72308_g.field_70163_u), MathHelper.func_76128_c(mop.field_72308_g.field_70161_v), Blocks.field_150358_i);
/*  153 */         } else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  154 */           Block hitBlock = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  155 */           if (hitBlock == Witchery.Blocks.CAULDRON) {
/*  156 */             if (!Witchery.Blocks.CAULDRON.tryFillWith(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, new FluidStack(FluidRegistry.WATER, 3000))) {}
/*      */           }
/*  158 */           else if (hitBlock == Witchery.Blocks.KETTLE) {
/*  159 */             if (!Witchery.Blocks.KETTLE.tryFillWith(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, new FluidStack(FluidRegistry.WATER, 1000))) {}
/*      */           }
/*      */           else {
/*  162 */             int dx = mop.field_72310_e == 4 ? -1 : mop.field_72310_e == 5 ? 1 : 0;
/*  163 */             int dy = mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0;
/*  164 */             int dz = mop.field_72310_e == 2 ? -1 : mop.field_72310_e == 3 ? 1 : 0;
/*  165 */             setBlock(caster, world, mop.field_72311_b + dx, mop.field_72312_c + dy + ((!world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149688_o().func_76220_a()) && (mop.field_72310_e == 1) ? -1 : 0), mop.field_72309_d + dz, Blocks.field_150358_i);
/*      */           }
/*      */         }
/*  168 */       } else if (!world.field_73011_w.field_76575_d) {
/*  169 */         if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
/*  170 */           int x = MathHelper.func_76128_c(mop.field_72308_g.field_70165_t);
/*  171 */           int y = MathHelper.func_76128_c(mop.field_72308_g.field_70163_u);
/*  172 */           int z = MathHelper.func_76128_c(mop.field_72308_g.field_70161_v);
/*  173 */           setBlock(caster, world, x, y, z, Blocks.field_150358_i);
/*  174 */           setIfAir(caster, world, x, y + 1, z, Blocks.field_150358_i);
/*  175 */           setIfAir(caster, world, x + 1, y, z, Blocks.field_150358_i);
/*  176 */           setIfAir(caster, world, x - 1, y, z, Blocks.field_150358_i);
/*  177 */           setIfAir(caster, world, x, y, z + 1, Blocks.field_150358_i);
/*  178 */           setIfAir(caster, world, x, y, z - 1, Blocks.field_150358_i);
/*  179 */           setIfAir(caster, world, x, y - 1, z, Blocks.field_150358_i);
/*      */         } else {
/*  181 */           int dx = mop.field_72310_e == 4 ? -1 : mop.field_72310_e == 5 ? 1 : 0;
/*  182 */           int dy = mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0;
/*  183 */           int dz = mop.field_72310_e == 2 ? -1 : mop.field_72310_e == 3 ? 1 : 0;
/*  184 */           int x = mop.field_72311_b + dx;
/*  185 */           int y = mop.field_72312_c + dy + ((!world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149688_o().func_76220_a()) && (mop.field_72310_e == 1) ? -1 : 0);
/*  186 */           int z = mop.field_72309_d + dz;
/*  187 */           setBlock(caster, world, x, y, z, Blocks.field_150358_i);
/*  188 */           setIfAir(caster, world, x, y + 1, z, Blocks.field_150358_i);
/*  189 */           setIfAir(caster, world, x + 1, y, z, Blocks.field_150358_i);
/*  190 */           setIfAir(caster, world, x - 1, y, z, Blocks.field_150358_i);
/*  191 */           setIfAir(caster, world, x, y, z + 1, Blocks.field_150358_i);
/*  192 */           setIfAir(caster, world, x, y, z - 1, Blocks.field_150358_i);
/*  193 */           setIfAir(caster, world, x, y - 1, z, Blocks.field_150358_i);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */     private void setBlock(EntityLivingBase caster, World world, int x, int y, int z, Block block) {
/*  199 */       if (BlockProtect.checkModsForBreakOK(world, x, y, z, caster)) {
/*  200 */         world.func_147449_b(x, y, z, block);
/*      */       }
/*      */     }
/*      */     
/*      */     private void setIfAir(EntityLivingBase caster, World world, int x, int y, int z, Block block) {
/*  205 */       if (world.func_147437_c(x, y, z)) {
/*  206 */         setBlock(caster, world, x, y, z, block);
/*      */       }
/*      */     }
/*  147 */   }.setColor(1176575).setSize(2.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 0, 0, 2, 2, 1 }), new StrokeSet(1, new byte[] { 0, 0, 2, 2, 2, 1 }), new StrokeSet(2, new byte[] { 0, 0, 0, 2, 2, 1, 1 }), new StrokeSet(2, new byte[] { 0, 0, 0, 2, 2, 2, 1, 1 }), new StrokeSet(3, new byte[] { 0, 0, 0, 0, 2, 2, 1, 1, 1 }), new StrokeSet(3, new byte[] { 0, 0, 0, 0, 2, 2, 2, 1, 1, 1 }) });
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
/*  219 */   public static final SymbolEffect Alohomora = instance().addEffect(new SymbolEffectProjectile(3, "witchery.pott.alohomora")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  222 */       if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  223 */         Block blockID = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  224 */         if ((blockID == Witchery.Blocks.DOOR_ALDER) || (blockID == Witchery.Blocks.DOOR_ROWAN)) {
/*  225 */           ((BlockWitchDoor)blockID).onBlockActivatedNormally(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, null, 1, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  226 */         } else if ((blockID instanceof BlockDoor)) {
/*  227 */           ((BlockDoor)blockID).func_150014_a(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, !((BlockDoor)blockID).func_150015_f(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d));
/*      */         }
/*      */       }
/*      */     }
/*  219 */   }.setColor(5322534).setSize(0.5F), new StrokeSet[] { new StrokeSet(new byte[] { 2, 0, 2, 2, 1 }), new StrokeSet(new byte[] { 2, 0, 2, 2, 2, 1 }), new StrokeSet(new byte[] { 2, 0, 0, 2, 2, 1, 1 }), new StrokeSet(new byte[] { 2, 0, 0, 2, 2, 2, 1, 1 }) });
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
/*  241 */   public static final SymbolEffect AvadaKedavra = instance().addEffect(new SymbolEffectProjectile(4, "witchery.pott.avadakedavra", 101, true, false, null, 0, false)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  244 */       if ((mop != null) && (caster != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLivingBase))) {
/*  245 */         if ((mop.field_72308_g instanceof EntityPlayer)) {
/*  246 */           if ((world.field_72995_K) || (!(caster instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())) {
/*  247 */             EntityPlayer hitPlayer = (EntityPlayer)mop.field_72308_g;
/*  248 */             EntityUtil.instantDeath(hitPlayer, caster);
/*      */           }
/*  250 */         } else if ((mop.field_72308_g instanceof EntityLiving)) {
/*  251 */           EntityLiving hitCreature = (EntityLiving)mop.field_72308_g;
/*  252 */           if (((caster instanceof EntityPlayer)) && (((EntityPlayer)caster).field_71075_bZ.field_75098_d)) {
/*  253 */             EntityUtil.instantDeath(hitCreature, caster);
/*      */           }
/*  255 */           else if (((PotionEnslaved.canCreatureBeEnslaved(hitCreature)) || ((hitCreature instanceof EntityWitch)) || ((hitCreature instanceof EntityEnt)) || ((hitCreature instanceof net.minecraft.entity.monster.EntityGolem))) && (hitCreature.func_110138_aP() <= 200.0F)) {
/*  256 */             hitCreature.func_70097_a(DamageSource.func_76354_b(effectEntity, caster), 200.0F);
/*      */           } else {
/*  258 */             hitCreature.func_70097_a(DamageSource.func_76354_b(effectEntity, caster), 25.0F);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  241 */   }.setColor(65280).setSize(2.0F), new StrokeSet[] { new StrokeSet(new byte[] { 1, 1, 2, 2, 0, 0, 3, 3, 3, 3, 1, 1, 2 }) });
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
/*  269 */   public static final SymbolEffect CaveInimicum = instance().addEffect(new SymbolEffectProjectile(5, "witchery.pott.caveinimicum")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  272 */       if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  273 */         EffectRegistry.applyBlockEffect(world, caster, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, effectEntity.getEffectLevel(), new EffectRegistry.IBlockEffect() {
/*      */           public void doAction(World world, EntityLivingBase actor, int x, int y, int z, Block block, int meta) {
/*  275 */             Block newBlockID = Blocks.field_150350_a;
/*  276 */             if (block == Blocks.field_150346_d) {
/*  277 */               newBlockID = Blocks.field_150348_b;
/*  278 */             } else if (block == Blocks.field_150349_c) {
/*  279 */               newBlockID = Blocks.field_150348_b;
/*  280 */             } else if (block == Blocks.field_150391_bh) {
/*  281 */               newBlockID = Blocks.field_150348_b;
/*  282 */             } else if (block == Blocks.field_150347_e) {
/*  283 */               newBlockID = Blocks.field_150348_b;
/*  284 */             } else if (block == Blocks.field_150344_f) {
/*  285 */               newBlockID = Blocks.field_150348_b;
/*  286 */             } else if (block == Witchery.Blocks.PLANKS) {
/*  287 */               newBlockID = Blocks.field_150348_b;
/*  288 */             } else if (block == Blocks.field_150417_aV) {
/*  289 */               newBlockID = Blocks.field_150336_V;
/*  290 */             } else if (block == Blocks.field_150354_m) {
/*  291 */               newBlockID = Blocks.field_150322_A;
/*  292 */             } else if (block == Blocks.field_150435_aG) {
/*  293 */               newBlockID = Blocks.field_150405_ch;
/*  294 */             } else if (block == Blocks.field_150466_ao) {
/*  295 */               int i1 = ((BlockDoor)block).func_150012_g(world, x, y, z);
/*  296 */               if ((i1 & 0x8) != 0) {
/*  297 */                 y--;
/*      */               }
/*  299 */               world.func_147468_f(x, y, z);
/*  300 */               world.func_147468_f(x, y + 1, z);
/*  301 */               int pp1 = MathHelper.func_76128_c((actor.field_70177_z + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;
/*  302 */               ItemDoor.func_150924_a(world, x, y, z, pp1, Blocks.field_150454_av);
/*      */             }
/*      */             
/*  305 */             if (newBlockID != Blocks.field_150350_a) {
/*  306 */               world.func_147449_b(x, y, z, newBlockID);
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*      */     }
/*  269 */   }.setColor(3158064).setSize(3.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 0, 3, 0, 0, 2 }), new StrokeSet(1, new byte[] { 0, 3, 0, 0, 0, 2 }), new StrokeSet(1, new byte[] { 0, 3, 3, 0, 0, 2, 2 }), new StrokeSet(2, new byte[] { 0, 3, 3, 0, 0, 0, 2, 2 }), new StrokeSet(3, new byte[] { 0, 3, 3, 3, 0, 0, 0, 0, 2, 2, 2 }) });
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
/*  321 */   public static final SymbolEffect Colloportus = instance().addEffect(new SymbolEffectProjectile(6, "witchery.pott.colloportus")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  324 */       if ((mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) && (caster != null)) {
/*  325 */         int y = mop.field_72312_c;
/*  326 */         Block blockID = world.func_147439_a(mop.field_72311_b, y, mop.field_72309_d);
/*  327 */         if ((blockID instanceof BlockDoor)) {
/*  328 */           int i1 = ((BlockDoor)blockID).func_150012_g(world, mop.field_72311_b, y, mop.field_72309_d);
/*  329 */           if ((i1 & 0x8) != 0) {
/*  330 */             y--;
/*      */           }
/*  332 */           world.func_147468_f(mop.field_72311_b, y, mop.field_72309_d);
/*  333 */           world.func_147468_f(mop.field_72311_b, y + 1, mop.field_72309_d);
/*  334 */           int pp1 = MathHelper.func_76128_c((caster.field_70177_z + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;
/*  335 */           ItemDoor.func_150924_a(world, mop.field_72311_b, y, mop.field_72309_d, pp1, Witchery.Blocks.DOOR_ROWAN);
/*      */         }
/*      */       }
/*      */     }
/*  321 */   }.setColor(5322534).setSize(1.0F), new StrokeSet[] { new StrokeSet(new byte[] { 3, 3, 1, 1, 2 }), new StrokeSet(new byte[] { 3, 3, 1, 1, 1, 2 }), new StrokeSet(new byte[] { 3, 3, 3, 1, 1, 2, 2 }), new StrokeSet(new byte[] { 3, 3, 3, 1, 1, 2, 1, 2 }) });
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
/*      */   private static <T extends Entity> void applyEntityEffect(World world, EntityLivingBase actor, MovingObjectPosition mop, double xMid, double yMid, double zMid, double radius, Class<T> clazz, IEntityEffect<T> effect)
/*      */   {
/*      */     double R_SQ;
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
/*  355 */     if (radius == 0.0D) {
/*  356 */       if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && (mop.field_72308_g != null) && (clazz.isAssignableFrom(mop.field_72308_g.getClass()))) {
/*  357 */         effect.doAction(world, actor, xMid, yMid, zMid, mop.field_72308_g);
/*      */       }
/*      */     } else {
/*  360 */       double R = radius;
/*  361 */       R_SQ = R * R;
/*      */       
/*  363 */       AxisAlignedBB bb = AxisAlignedBB.func_72330_a(xMid - R, yMid - R, zMid - R, xMid + R, yMid + R, zMid + R);
/*  364 */       List entities = world.func_72872_a(clazz, bb);
/*  365 */       for (Object obj : entities) {
/*  366 */         T entity = (Entity)obj;
/*  367 */         if (entity.func_70092_e(xMid, yMid, zMid) <= R_SQ) {
/*  368 */           effect.doAction(world, actor, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*  375 */   public static final SymbolEffect Confundus = instance().addEffect(new SymbolEffectProjectile(8, "witchery.pott.confundus")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  378 */       double radius = spell.getEffectLevel() == 2 ? 2.0D : spell.getEffectLevel() == 1 ? 0.0D : 4.0D;
/*  379 */       EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new EffectRegistry.IEntityEffect()
/*      */       {
/*      */         public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
/*  382 */           if (((target instanceof EntityLivingBase)) && (!target.func_70644_a(Potion.field_76431_k))) {
/*  383 */             target.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 600));
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  375 */   }.setColor(16771328).setSize(1.5F), new StrokeSet[] { new StrokeSet(1, new byte[] { 3, 3, 0, 0, 2 }), new StrokeSet(1, new byte[] { 3, 3, 3, 0, 0, 2, 2 }), new StrokeSet(2, new byte[] { 3, 3, 3, 0, 0, 0, 2, 2 }), new StrokeSet(3, new byte[] { 3, 3, 3, 3, 0, 0, 0, 0, 2, 2, 2 }) });
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
/*  396 */   public static final SymbolEffect Crucio = instance().addEffect(new SymbolEffectProjectile(9, "witchery.pott.crucio", 5, true, false, null, 0)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  399 */       if ((mop != null) && (caster != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLivingBase))) {
/*  400 */         if ((mop.field_72308_g instanceof EntityPlayer)) {
/*  401 */           if ((world.field_72995_K) || (!(caster instanceof EntityPlayer)) || (MinecraftServer.func_71276_C().func_71219_W())) {
/*  402 */             EntityPlayer hitPlayer = (EntityPlayer)mop.field_72308_g;
/*  403 */             hitPlayer.func_70097_a(DamageSource.func_76354_b(spell, caster), 4 + 4 * (spell.getEffectLevel() - 1));
/*      */           }
/*  405 */         } else if ((mop.field_72308_g instanceof EntityLiving)) {
/*  406 */           EntityLiving hitCreature = (EntityLiving)mop.field_72308_g;
/*  407 */           hitCreature.func_70097_a(DamageSource.func_76354_b(spell, caster), 4.0F);
/*      */         }
/*      */       }
/*      */     }
/*  396 */   }.setColor(6684927).setSize(2.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 1, 3, 1, 1, 2 }), new StrokeSet(1, new byte[] { 1, 3, 3, 1, 1, 2, 2 }), new StrokeSet(2, new byte[] { 1, 3, 1, 1, 1, 2 }), new StrokeSet(2, new byte[] { 1, 3, 3, 1, 1, 1, 2, 2 }), new StrokeSet(3, new byte[] { 1, 3, 3, 3, 1, 1, 1, 1, 2, 2, 2 }) });
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
/*      */   private static void applyBlockEffect(World world, EntityLivingBase actor, int midX, int midY, int midZ, int side, int radius, IBlockEffect effect)
/*      */   {
/*  424 */     if (radius == 1) {
/*  425 */       Block block = world.func_147439_a(midX, midY, midZ);
/*  426 */       int meta = world.func_72805_g(midX, midY, midZ);
/*  427 */       if ((block != Blocks.field_150350_a) && (BlockProtect.canBreak(block, world)) && (BlockProtect.checkModsForBreakOK(world, midX, midY, midZ, block, meta, actor))) {
/*  428 */         effect.doAction(world, actor, midX, midY, midZ, block, meta);
/*      */       }
/*      */     } else {
/*  431 */       int r = Math.min(radius - 1, 3);
/*  432 */       int x = midX;int y = midY;int z = midZ;
/*  433 */       for (int k = -r; k <= r; k++) {
/*  434 */         for (int j = -r; j <= r; j++) {
/*  435 */           switch (side) {
/*      */           case 0: 
/*      */           case 1: 
/*  438 */             x = midX + k;
/*  439 */             z = midZ + j;
/*  440 */             break;
/*      */           case 2: 
/*      */           case 3: 
/*  443 */             x = midX + k;
/*  444 */             y = midY + j;
/*  445 */             break;
/*      */           case 4: 
/*      */           case 5: 
/*  448 */             y = midY + k;
/*  449 */             z = midZ + j;
/*      */           }
/*      */           
/*      */           
/*  453 */           Block block = world.func_147439_a(x, y, z);
/*  454 */           int meta = world.func_72805_g(x, y, z);
/*  455 */           if ((block != Blocks.field_150350_a) && (BlockProtect.canBreak(block, world)) && (BlockProtect.checkModsForBreakOK(world, x, y, z, block, meta, actor))) {
/*  456 */             effect.doAction(world, actor, x, y, z, block, meta);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*  464 */   public static final SymbolEffect Defodio = instance().addEffect(new SymbolEffectProjectile(10, "witchery.pott.defodio", 3, false, false, null, 0)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  467 */       if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  468 */         EffectRegistry.applyBlockEffect(world, caster, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, effectEntity.getEffectLevel(), new EffectRegistry.IBlockEffect() {
/*      */           public void doAction(World world, EntityLivingBase actor, int x, int y, int z, Block block, int meta) {
/*  470 */             Material material = block.func_149688_o();
/*  471 */             if ((material == Material.field_151571_B) || (material == Material.field_151596_z) || (material == Material.field_151578_c) || (material == Material.field_151577_b) || (material == Material.field_151588_w) || (material == Material.field_151576_e) || (material == Material.field_151595_p)) {
/*  472 */               world.func_147468_f(x, y, z);
/*  473 */               net.minecraft.item.Item itemBlock = null;
/*  474 */               int itemDamageValue = -1;
/*      */               try {
/*  476 */                 itemBlock = block.func_149650_a(meta, world.field_73012_v, 0);
/*  477 */                 itemDamageValue = block.func_149692_a(meta);
/*  478 */                 int quantity = block.quantityDropped(meta, 0, world.field_73012_v);
/*  479 */                 if ((itemBlock != null) && (itemDamageValue >= 0) && (quantity > 0)) {
/*  480 */                   world.func_72838_d(new EntityItem(world, 0.5D + x, 0.5D + y, 0.5D + z, new ItemStack(itemBlock, quantity, itemDamageValue)));
/*      */                 }
/*      */               } catch (Throwable ex) {
/*  483 */                 Log.instance().warning(ex, "Exception occured while spawning block as part of Defodio effect: new (" + itemBlock + ", " + itemDamageValue + ") old (" + block + ", " + meta + ")");
/*      */               }
/*      */             }
/*      */           }
/*      */         });
/*      */       }
/*      */     }
/*  464 */   }.setColor(4008220).setSize(2.5F), new StrokeSet[] { new StrokeSet(1, new byte[] { 0, 0, 3, 1 }), new StrokeSet(1, new byte[] { 0, 0, 0, 3, 1, 1 }), new StrokeSet(1, new byte[] { 0, 0, 3, 3, 1, 2 }), new StrokeSet(2, new byte[] { 0, 0, 0, 3, 3, 1, 1, 2 }), new StrokeSet(2, new byte[] { 0, 0, 0, 0, 3, 3, 1, 1, 1, 2 }), new StrokeSet(2, new byte[] { 0, 0, 0, 3, 3, 3, 1, 1, 2, 2 }), new StrokeSet(3, new byte[] { 0, 0, 0, 0, 3, 3, 3, 1, 1, 1, 2, 2 }) });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  503 */   public static final SymbolEffect Ennervate = instance().addEffect(new SymbolEffectProjectile(12, "witchery.pott.ennervate", 1, false, true, null, 0)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  506 */       double radius = spell.getEffectLevel() == 2 ? 2.0D : spell.getEffectLevel() == 1 ? 0.0D : 4.0D;
/*  507 */       EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new EffectRegistry.IEntityEffect()
/*      */       {
/*      */         public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
/*  510 */           if (target.func_70644_a(Potion.field_76421_d)) {
/*  511 */             target.func_82170_o(Potion.field_76421_d.field_76415_H);
/*      */           }
/*      */           
/*  514 */           if (target.func_70644_a(Potion.field_76419_f)) {
/*  515 */             target.func_82170_o(Potion.field_76419_f.field_76415_H);
/*      */           }
/*      */           
/*  518 */           if (target.func_70644_a(Potion.field_76431_k)) {
/*  519 */             target.func_82170_o(Potion.field_76431_k.field_76415_H);
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  503 */   }.setColor(16713595).setSize(1.5F), new StrokeSet[] { new StrokeSet(1, new byte[] { 0, 3, 0, 2, 3, 0, 2 }), new StrokeSet(2, new byte[] { 0, 3, 3, 0, 2, 2, 3, 3, 0, 2, 2 }), new StrokeSet(3, new byte[] { 0, 3, 3, 3, 0, 2, 2, 2, 3, 3, 3, 0, 2, 2, 2 }) });
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
/*  531 */   public static final SymbolEffect Episkey = instance().addEffect(new SymbolEffect(13, "witchery.pott.episkey", 1, false, false, null, 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int effectLevel) {
/*  534 */       double radius = effectLevel == 2 ? 2.0D : effectLevel == 1 ? 0.0D : 4.0D;
/*  535 */       MovingObjectPosition mop = new MovingObjectPosition(player);
/*  536 */       EffectRegistry.applyEntityEffect(world, player, mop, player.field_70165_t, player.field_70163_u, player.field_70161_v, radius, EntityLivingBase.class, new EffectRegistry.IEntityEffect()
/*      */       {
/*      */         public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
/*  539 */           boolean hasFood = target instanceof EntityPlayer;
/*  540 */           int currentFood = hasFood ? ((EntityPlayer)target).func_71024_bL().func_75116_a() : 5;
/*  541 */           if ((currentFood > 1) && (target.func_110143_aJ() < target.func_110138_aP())) {
/*  542 */             target.func_70691_i(Math.min(5, currentFood));
/*  543 */             if (hasFood) {
/*  544 */               ((EntityPlayer)target).func_71024_bL().func_75122_a(-Math.min(5, currentFood), 0.0F);
/*      */             }
/*  546 */             if (!target.func_70644_a(Potion.field_76431_k)) {
/*  547 */               target.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, TimeUtil.secsToTicks(4)));
/*      */             }
/*  549 */             ParticleEffect.SPLASH.send(SoundEffect.MOB_SLIME_SMALL, target, 1.0D, 1.0D, 16);
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  531 */   }, new StrokeSet[] { new StrokeSet(1, new byte[] { 2, 0, 3, 1, 1, 2 }), new StrokeSet(2, new byte[] { 2, 0, 0, 3, 1, 1, 1, 1, 2 }), new StrokeSet(2, new byte[] { 2, 2, 0, 3, 3, 1, 1, 2, 2 }), new StrokeSet(3, new byte[] { 2, 2, 0, 0, 3, 3, 1, 1, 1, 1, 2, 2 }) });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  567 */   public static final SymbolEffect Expelliarmus = instance().addEffect(new SymbolEffectProjectile(15, "witchery.pott.expelliarmus")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  570 */       double radius = spell.getEffectLevel() == 2 ? 3.0D : spell.getEffectLevel() == 1 ? 0.0D : 5.0D;
/*  571 */       EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new EffectRegistry.IEntityEffect()
/*      */       {
/*      */         public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
/*  574 */           if (actor != target) {
/*  575 */             EffectRegistry.5.this.disarm(target);
/*      */           }
/*      */         }
/*      */       });
/*      */     }
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
/*      */     private void disarm(EntityLivingBase target)
/*      */     {
/*  595 */       if ((target instanceof EntityPlayer)) {
/*  596 */         EntityPlayer playerVictim = (EntityPlayer)target;
/*  597 */         if ((playerVictim.field_71070_bA == null) || (playerVictim.field_71070_bA.field_75152_c == 0)) {
/*  598 */           int heldItemIndex = playerVictim.field_71071_by.field_70461_c;
/*  599 */           if (playerVictim.field_71071_by.field_70462_a[heldItemIndex] != null) {
/*  600 */             playerVictim.func_71019_a(playerVictim.field_71071_by.field_70462_a[heldItemIndex], true);
/*  601 */             playerVictim.field_71071_by.field_70462_a[heldItemIndex] = null;
/*      */           }
/*      */         }
/*  604 */       } else if (!PotionIllFitting.isTargetBanned(target)) {
/*  605 */         ItemStack heldItem = target.func_70694_bm();
/*  606 */         if (heldItem != null) {
/*  607 */           if ((target instanceof EntityPlayer)) {
/*  608 */             Infusion.dropEntityItemWithRandomChoice(target, heldItem, true);
/*      */           } else {
/*  610 */             target.func_70099_a(heldItem, 0.5F);
/*      */           }
/*  612 */           target.func_70062_b(0, null);
/*      */         }
/*      */       }
/*      */     }
/*  567 */   }.setColor(16747778).setSize(3.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 0, 0, 1 }), new StrokeSet(1, new byte[] { 0, 0, 0, 1, 1 }), new StrokeSet(2, new byte[] { 0, 0, 0, 0, 1, 1, 1 }), new StrokeSet(3, new byte[] { 0, 0, 0, 0, 0, 1, 1, 1, 1 }) });
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
/*  624 */   public static final SymbolEffect Flagrate = instance().addEffect(new SymbolEffect(16, "witchery.pott.flagrate", 1, false, false, null, 0, false)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int effectLevel) {
/*  627 */       MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0D);
/*  628 */       if (mop != null) {
/*  629 */         if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  630 */           ItemChalk.drawGlyph(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, Witchery.Blocks.GLYPH_INFERNAL, player);
/*      */         } else {
/*  632 */           SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */         }
/*      */       } else {
/*  635 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */       }
/*      */     }
/*  624 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 2, 0, 2, 3, 0, 2 }) });
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
/*  643 */   public static final SymbolEffect Flipendo = instance().addEffect(new SymbolEffectProjectile(17, "witchery.pott.flipendo")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  646 */       final double radius = spell.getEffectLevel() == 2 ? 3.0D : spell.getEffectLevel() == 1 ? 0.0D : 6.0D;
/*  647 */       double spellX = spell.field_70159_w;
/*  648 */       final double spellZ = spell.field_70179_y;
/*  649 */       EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new EffectRegistry.IEntityEffect()
/*      */       {
/*      */         public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
/*  652 */           if ((radius == 3.0D) || (target != actor)) {
/*  653 */             double ACCELERATION = 2.0D;
/*  654 */             if (target.func_70644_a(Potion.field_76421_d)) {
/*  655 */               ACCELERATION += 0.5D;
/*      */             }
/*      */             
/*  658 */             double motionX = spellZ * ACCELERATION;
/*  659 */             double motionY = 0.3D;
/*  660 */             double motionZ = this.val$spellZ * ACCELERATION;
/*  661 */             if ((target instanceof EntityPlayer)) {
/*  662 */               EntityPlayer targetPlayer = (EntityPlayer)target;
/*  663 */               Witchery.packetPipeline.sendTo(new com.emoniph.witchery.network.PacketPushTarget(motionX, 0.3D, motionZ), targetPlayer);
/*      */             } else {
/*  665 */               target.field_70159_w = motionX;
/*  666 */               target.field_70181_x = 0.3D;
/*  667 */               target.field_70179_y = motionZ;
/*      */             }
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  643 */   }.setColor(16775577).setSize(3.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 2, 2, 3 }), new StrokeSet(1, new byte[] { 2, 2, 2, 3, 3 }), new StrokeSet(2, new byte[] { 2, 2, 2, 2, 3, 3, 3 }), new StrokeSet(3, new byte[] { 2, 2, 2, 2, 2, 3, 3, 3, 3 }) });
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
/*  685 */   public static final SymbolEffect Impedimenta = instance().addEffect(new SymbolEffectProjectile(19, "witchery.pott.impedimenta")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  688 */       double radius = spell.getEffectLevel() == 2 ? 3.0D : spell.getEffectLevel() == 1 ? 0.0D : 6.0D;
/*  689 */       double spellX = spell.field_70159_w;
/*  690 */       double spellZ = spell.field_70179_y;
/*  691 */       EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new EffectRegistry.IEntityEffect()
/*      */       {
/*      */         public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
/*  694 */           if ((target != actor) && 
/*  695 */             (!target.func_70644_a(Potion.field_76421_d))) {
/*  696 */             target.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 600, 1));
/*      */           }
/*      */         }
/*      */       });
/*      */     }
/*  685 */   }.setColor(6191615).setSize(1.5F), new StrokeSet[] { new StrokeSet(1, new byte[] { 3, 3, 2 }), new StrokeSet(1, new byte[] { 3, 3, 3, 2, 2 }), new StrokeSet(2, new byte[] { 3, 3, 3, 3, 2, 2, 2 }), new StrokeSet(3, new byte[] { 3, 3, 3, 3, 3, 2, 2, 2, 2 }) });
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
/*  710 */   public static final SymbolEffect Imperio = instance().addEffect(new SymbolEffectProjectile(20, "witchery.pott.imperio", 10, true, false, null, 0)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  713 */       if ((mop != null) && (caster != null) && ((caster instanceof EntityPlayer)) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLivingBase)))
/*      */       {
/*  715 */         EntityLivingBase entityLiving = (EntityLivingBase)mop.field_72308_g;
/*  716 */         if (PotionEnslaved.canCreatureBeEnslaved(entityLiving)) {
/*  717 */           EntityPlayer player = (EntityPlayer)caster;
/*  718 */           EntityLiving creature = (EntityLiving)entityLiving;
/*  719 */           net.minecraft.nbt.NBTTagCompound nbt = entityLiving.getEntityData();
/*  720 */           if (PotionEnslaved.setEnslaverForMob(creature, player)) {
/*  721 */             ParticleEffect.SPELL.send(SoundEffect.MOB_ZOMBIE_INFECT, creature, 1.0D, 2.0D, 8);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  710 */   }.setColor(10686463).setSize(1.5F), new StrokeSet[] { new StrokeSet(new byte[] { 2, 1, 1, 1, 1 }) });
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
/*  729 */   public static final SymbolEffect Incendio = instance().addEffect(new SymbolEffectProjectile(21, "witchery.pott.incendio")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*  732 */       double radius = spell.getEffectLevel() == 2 ? 3.0D : spell.getEffectLevel() == 1 ? 0.0D : 6.0D;
/*  733 */       final int level = spell.getEffectLevel();
/*  734 */       if (radius == 0.0D) {
/*  735 */         if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
/*  736 */           mop.field_72308_g.func_70015_d(1);
/*  737 */           mop.field_72308_g.func_70097_a(new EntityDamageSourceIndirect("onFire", spell, caster).func_76361_j(), 0.1F);
/*  738 */         } else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  739 */           Block hitBlock = BlockUtil.getBlock(world, mop);
/*  740 */           if ((hitBlock == Witchery.Blocks.WICKER_BUNDLE) && (BlockWickerBundle.limitToValidMetadata(world.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) == 1)) {
/*  741 */             if (!BlockWickerBundle.tryIgniteMan(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, caster != null ? caster.field_70177_z : 0.0F)) {}
/*      */ 
/*      */           }
/*  744 */           else if (hitBlock == Witchery.Blocks.BRAZIER) {
/*  745 */             com.emoniph.witchery.blocks.BlockBrazier.tryIgnite(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
/*  746 */             return;
/*      */           }
/*      */           
/*  749 */           int dx = mop.field_72310_e == 4 ? -1 : mop.field_72310_e == 5 ? 1 : 0;
/*  750 */           int dy = mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0;
/*  751 */           int dz = mop.field_72310_e == 2 ? -1 : mop.field_72310_e == 3 ? 1 : 0;
/*  752 */           world.func_147449_b(mop.field_72311_b + dx, mop.field_72312_c + dy + ((!world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149688_o().func_76220_a()) && (mop.field_72310_e == 1) ? -1 : 0), mop.field_72309_d + dz, Blocks.field_150480_ab);
/*      */         }
/*      */       } else {
/*  755 */         EffectRegistry.applyEntityEffect(world, caster, mop, spell.field_70165_t, spell.field_70163_u, spell.field_70161_v, radius, EntityLivingBase.class, new EffectRegistry.IEntityEffect()
/*      */         {
/*      */           public void doAction(World world, EntityLivingBase actor, double x, double y, double z, EntityLivingBase target) {
/*  758 */             if (target != actor) {
/*  759 */               target.func_70015_d(level);
/*      */             }
/*      */           }
/*      */         });
/*      */         
/*  764 */         if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)) {
/*  765 */           final int side = mop.field_72310_e;
/*  766 */           EffectRegistry.applyBlockEffect(world, caster, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, level, new EffectRegistry.IBlockEffect() {
/*      */             public void doAction(World world, EntityLivingBase actor, int x, int y, int z, Block block, int meta) {
/*  768 */               if (side == 1) {
/*  769 */                 int dx = side == 4 ? -1 : side == 5 ? 1 : 0;
/*  770 */                 int dy = side == 1 ? 1 : side == 0 ? -1 : 0;
/*  771 */                 int dz = side == 2 ? -1 : side == 3 ? 1 : 0;
/*  772 */                 int nX = x + dx;
/*  773 */                 int nY = y + dy;
/*  774 */                 int nZ = z + dz;
/*  775 */                 if (world.func_147437_c(nX, nY, nZ)) {
/*  776 */                   world.func_147449_b(nX, nY, nZ, Blocks.field_150480_ab);
/*      */                 }
/*      */               }
/*      */             }
/*      */           });
/*      */         }
/*      */       }
/*      */     }
/*  729 */   }.setColor(16724023).setSize(2.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 3, 0, 0, 1, 1 }), new StrokeSet(2, new byte[] { 3, 0, 0, 0, 1, 1, 1 }), new StrokeSet(3, new byte[] { 3, 0, 0, 0, 0, 1, 1, 1, 1 }) });
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
/*      */ 
/*  791 */   public static final SymbolEffect Lumos = instance().addEffect(new SymbolEffectProjectile(22, "witchery.pott.lumos")
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  794 */       if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  795 */         int dx = mop.field_72310_e == 4 ? -1 : mop.field_72310_e == 5 ? 1 : 0;
/*  796 */         int dy = mop.field_72310_e == 1 ? 1 : mop.field_72310_e == 0 ? -1 : 0;
/*  797 */         int dz = mop.field_72310_e == 2 ? -1 : mop.field_72310_e == 3 ? 1 : 0;
/*      */         
/*  799 */         int x = mop.field_72311_b + 1 * dx;
/*  800 */         int y = mop.field_72312_c + 1 * dy;
/*  801 */         int z = mop.field_72309_d + 1 * dz;
/*      */         
/*  803 */         Material material = world.func_147439_a(x, y, z).func_149688_o();
/*  804 */         if ((material == Material.field_151579_a) || (material == Material.field_151597_y)) {
/*  805 */           world.func_147449_b(x, y, z, Witchery.Blocks.GLOW_GLOBE);
/*      */         }
/*      */       }
/*      */     }
/*  791 */   }.setColor(16777018).setSize(0.5F), new StrokeSet[] { new StrokeSet(new byte[] { 1, 1, 1, 2 }) });
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
/*  812 */   public static final SymbolEffect MeteolojinxRecanto = instance().addEffect(new SymbolEffect(23, "witchery.pott.meteolojinxrecanto", 100, false, false, null, 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int effectLevel) {
/*  815 */       MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0D);
/*  816 */       if (world.func_72896_J()) {
/*  817 */         WorldServer worldserver = MinecraftServer.func_71276_C().field_71305_c[0];
/*  818 */         if (worldserver != null) {
/*  819 */           WorldInfo worldinfo = worldserver.func_72912_H();
/*  820 */           worldinfo.func_76084_b(false);
/*  821 */           worldinfo.func_76069_a(false);
/*      */         }
/*      */       } else {
/*  824 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */       }
/*      */     }
/*  812 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 0, 0, 0, 2, 2, 1, 0, 2, 2, 1, 1 }) });
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
/*  837 */   public static final SymbolEffect Nox = instance().addEffect(new SymbolEffect(26, "witchery.pott.nox", 50, false, false, null, 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int effectLevel)
/*      */     {
/*  841 */       int x0 = MathHelper.func_76128_c(player.field_70165_t);
/*  842 */       int y0 = MathHelper.func_76128_c(player.field_70163_u);
/*  843 */       int z0 = MathHelper.func_76128_c(player.field_70161_v);
/*  844 */       int radius = 10;
/*  845 */       for (int y = y0 - radius; y <= y0 + radius; y++) {
/*  846 */         for (int x = x0 - radius; x <= x0 + radius; x++) {
/*  847 */           for (int z = z0 - radius; z <= z0 + radius; z++) {
/*  848 */             Block blockID = world.func_147439_a(x, y, z);
/*      */             
/*  850 */             if (blockID.getLightValue(world, x, y, z) > 0.8D)
/*      */             {
/*  852 */               if (BlockProtect.canBreak(blockID, world)) {
/*  853 */                 int blockMeta = world.func_72805_g(x, y, z);
/*  854 */                 if (BlockProtect.checkModsForBreakOK(world, x, y, z, blockID, blockMeta, player)) {
/*  855 */                   world.func_147468_f(x, y, z);
/*  856 */                   if (blockID.func_149745_a(world.field_73012_v) > 0) {
/*  857 */                     blockID.func_149697_b(world, x, y, z, blockMeta, 0);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  837 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 0, 0, 2, 1, 2, 0 }) });
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
/*  881 */   public static final SymbolEffect Protego = instance().addEffect(new SymbolEffect(31, "witchery.pott.protego")new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int effectLevel) {
/*  884 */       MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0D);
/*  885 */       if (mop != null) {
/*  886 */         if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*  887 */           InfusionLight.placeBarrierShield(world, player, mop);
/*      */         } else {
/*  889 */           SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */         }
/*      */       } else {
/*  892 */         SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */       }
/*      */     }
/*  881 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 1, 1, 0 }), new StrokeSet(new byte[] { 1, 1, 1, 0, 0 }), new StrokeSet(new byte[] { 1, 1, 1, 1, 0, 0, 0 }) });
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
/*  910 */   public static final SymbolEffect Stupefy = instance().addEffect(new SymbolEffectProjectile(36, "witchery.pott.stupefy", 5, false, true, null, 0)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  913 */       if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) && ((mop.field_72308_g instanceof EntityLivingBase))) {
/*  914 */         EntityLivingBase entityLiving = (EntityLivingBase)mop.field_72308_g;
/*  915 */         if (!entityLiving.func_70644_a(Potion.field_76421_d)) {
/*  916 */           entityLiving.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 6000, 9));
/*      */         }
/*      */       }
/*      */     }
/*  910 */   }.setColor(1279).setSize(1.5F), new StrokeSet[] { new StrokeSet(1, new byte[] { 2, 2, 0, 3, 0, 2 }), new StrokeSet(1, new byte[] { 2, 2, 2, 0, 3, 3, 0, 2, 2 }), new StrokeSet(2, new byte[] { 2, 2, 0, 0, 3, 0, 0, 2 }), new StrokeSet(2, new byte[] { 2, 2, 2, 0, 0, 3, 3, 0, 0, 2, 2 }) });
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
/*  932 */   public static final SymbolEffect Ignianima = instance().addEffect(new SymbolEffectProjectile(39, "witchery.pott.ignianima", 2, true, false, "ignianima", 0)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect e) {
/*  935 */       double R = 1.5D;
/*  936 */       double R_SQ = 2.25D;
/*  937 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(e.field_70165_t - 1.5D, e.field_70163_u - 1.5D, e.field_70161_v - 1.5D, e.field_70165_t + 1.5D, e.field_70163_u + 1.5D, e.field_70161_v + 1.5D);
/*  938 */       List entities = world.func_72872_a(EntityLivingBase.class, bounds);
/*  939 */       for (Object hit : entities) {
/*  940 */         EntityLivingBase hitEntity = (EntityLivingBase)hit;
/*  941 */         if (e.func_70068_e(hitEntity) <= 2.25D) {
/*  942 */           float damage = 4.0F;
/*  943 */           float scale = (hitEntity instanceof EntityPlayer) ? hitEntity.func_110138_aP() / 20.0F : 1.0F;
/*  944 */           if (caster != null) {
/*  945 */             float health = 20.0F * (caster.func_110143_aJ() / caster.func_110138_aP());
/*  946 */             if (health > 19.0F) {
/*  947 */               damage = 2.0F;
/*  948 */             } else if (health > 15.0F) {
/*  949 */               damage = 3.0F;
/*  950 */             } else if (health > 10.0F) {
/*  951 */               damage = 5.0F;
/*      */             } else {
/*  953 */               damage = 6.0F + (12.0F - health) / 2.0F;
/*      */             }
/*      */           }
/*  956 */           float scaledDamage = damage * scale;
/*  957 */           hitEntity.func_70097_a(new DemonicDamageSource(caster), scaledDamage);
/*  958 */           ParticleEffect.FLAME.send(SoundEffect.FIRE_IGNITE, hitEntity, 1.0D, 2.0D, 16);
/*      */         }
/*      */       }
/*      */     }
/*  932 */   }.setColor(16770912).setSize(3.0F), new StrokeSet[] { new StrokeSet(new byte[] { 3, 3, 0, 1, 1 }), new StrokeSet(new byte[] { 3, 3, 0, 0, 1, 1, 1, 1 }), new StrokeSet(new byte[] { 3, 3, 3, 0, 1, 1 }), new StrokeSet(new byte[] { 3, 3, 3, 0, 0, 1, 1, 1, 1 }), new StrokeSet(new byte[] { 3, 3, 3, 3, 0, 1, 1 }), new StrokeSet(new byte[] { 3, 3, 3, 3, 0, 0, 1, 1, 1, 1 }) });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  972 */   public static final SymbolEffect CarnosaDiem = instance().addEffect(new SymbolEffect(40, "witchery.pott.carnosadiem", 1, true, false, "carnosadiem", 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int effectLevel) {
/*  975 */       float damage = player.func_110138_aP() * 0.1F;
/*  976 */       player.func_70097_a(new DemonicDamageSource(player), damage);
/*  977 */       ParticleEffect.REDDUST.send(SoundEffect.MOB_ENDERDRAGON_GROWL, player, 1.0D, 2.0D, 16);
/*  978 */       int currentPower = Infusion.getCurrentEnergy(player);
/*  979 */       int maxPower = Infusion.getMaxEnergy(player);
/*  980 */       Infusion.setCurrentEnergy(player, Math.min(currentPower + 10, maxPower));
/*  981 */       Witchery.modHooks.boostBloodPowers(player, damage);
/*      */     }
/*  972 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 2, 2, 0, 1, 1 }), new StrokeSet(new byte[] { 2, 2, 0, 0, 1, 1, 1, 1 }), new StrokeSet(new byte[] { 2, 2, 2, 0, 1, 1 }), new StrokeSet(new byte[] { 2, 2, 2, 0, 0, 1, 1, 1, 1 }), new StrokeSet(new byte[] { 2, 2, 2, 2, 0, 1, 1 }), new StrokeSet(new byte[] { 2, 2, 2, 2, 0, 0, 1, 1, 1, 1 }) });
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
/*  994 */   public static final SymbolEffect MORSMORDRE = instance().addEffect(new SymbolEffectProjectile(41, "witchery.pott.morsmordre", 20, true, false, "morsmordre", 0)
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect effectEntity) {
/*  997 */       if (!world.field_72995_K) {
/*  998 */         EntityDarkMark entity = new EntityDarkMark(world);
/*  999 */         entity.func_70012_b(effectEntity.field_70165_t, effectEntity.field_70163_u, effectEntity.field_70161_v, 0.0F, 0.0F);
/* 1000 */         entity.func_110163_bv();
/* 1001 */         world.func_72838_d(entity);
/*      */       }
/*      */     }
/*  994 */   }.setColor(0).setSize(3.0F).setTimeToLive(8), new StrokeSet[] { new StrokeSet(new byte[] { 0, 0, 3, 2, 2 }), new StrokeSet(new byte[] { 0, 0, 3, 3, 2, 2, 2, 2 }), new StrokeSet(new byte[] { 0, 0, 0, 3, 2, 2 }), new StrokeSet(new byte[] { 0, 0, 0, 3, 3, 2, 2, 2, 2 }), new StrokeSet(new byte[] { 0, 0, 0, 0, 3, 2, 2 }), new StrokeSet(new byte[] { 0, 0, 0, 0, 3, 3, 2, 2, 2, 2 }) });
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
/* 1016 */   public static final SymbolEffect Tormentum = instance().addEffect(new SymbolEffectProjectile(42, "witchery.pott.tormentum", 25, true, true, "tormentum", TimeUtil.minsToTicks(30))
/*      */   {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect e)
/*      */     {
/* 1020 */       if ((!world.field_72995_K) && (e.field_71093_bK != Config.instance().dimensionTormentID)) {
/* 1021 */         double R = 2.0D;
/* 1022 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(e.field_70165_t - 2.0D, e.field_70163_u - 2.0D, e.field_70161_v - 2.0D, e.field_70165_t + 2.0D, e.field_70163_u + 2.0D, e.field_70161_v + 2.0D);
/* 1023 */         List entities = world.func_72872_a(EntityLivingBase.class, bounds);
/* 1024 */         boolean setCooldown = false;
/* 1025 */         for (Object hitEntity : entities) {
/* 1026 */           if ((hitEntity instanceof EntityPlayer)) {
/* 1027 */             EntityPlayer player = (EntityPlayer)hitEntity;
/* 1028 */             WorldProviderTorment.setPlayerMustTorment(player, 1, -1);
/* 1029 */             setCooldown = true;
/* 1030 */           } else if (((hitEntity instanceof EntityLiving)) && (!(hitEntity instanceof IBossDisplayData))) {
/* 1031 */             EntityLiving hitLiving = (EntityLiving)hitEntity;
/* 1032 */             hitLiving.func_70106_y();
/* 1033 */             setCooldown = true;
/*      */           }
/*      */         }
/* 1036 */         if ((setCooldown) && (caster != null) && ((caster instanceof EntityPlayer))) {
/* 1037 */           setOnCooldown((EntityPlayer)caster);
/*      */         }
/*      */       }
/*      */     }
/* 1016 */   }.setColor(2236962).setSize(4.0F), new StrokeSet[] { new StrokeSet(new byte[] { 1, 1, 3, 2, 2 }), new StrokeSet(new byte[] { 1, 1, 3, 3, 2, 2, 2, 2 }), new StrokeSet(new byte[] { 1, 1, 1, 3, 2, 2 }), new StrokeSet(new byte[] { 1, 1, 1, 3, 3, 2, 2, 2, 2 }), new StrokeSet(new byte[] { 1, 1, 1, 1, 3, 2, 2 }), new StrokeSet(new byte[] { 1, 1, 1, 1, 3, 3, 2, 2, 2, 2 }) });
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
/*      */ 
/*      */ 
/*      */ 
/* 1050 */   public static final SymbolEffect LEONARD_1 = instance().addEffect(new SymbolEffect(43, "witchery.pott.leonard1", 5, false, false, null, 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int level)
/*      */     {
/* 1054 */       EffectRegistry.castLeonardSpell(world, player, 0);
/*      */     }
/*      */     
/*      */     public int getChargeCost(World world, EntityPlayer player, int level) {
/* 1058 */       return EffectRegistry.costOfLeonardSpell(world, player, 0);
/*      */     }
/* 1050 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 2, 0, 3, 3, 1 }) });
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
/* 1064 */   public static final SymbolEffect LEONARD_2 = instance().addEffect(new SymbolEffect(44, "witchery.pott.leonard2", 5, false, false, null, 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int level)
/*      */     {
/* 1068 */       EffectRegistry.castLeonardSpell(world, player, 1);
/*      */     }
/*      */     
/*      */     public int getChargeCost(World world, EntityPlayer player, int level) {
/* 1072 */       return EffectRegistry.costOfLeonardSpell(world, player, 1);
/*      */     }
/* 1064 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 3, 1, 2, 2, 0 }) });
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
/* 1078 */   public static final SymbolEffect LEONARD_3 = instance().addEffect(new SymbolEffect(45, "witchery.pott.leonard3", 5, false, false, null, 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int level)
/*      */     {
/* 1082 */       EffectRegistry.castLeonardSpell(world, player, 2);
/*      */     }
/*      */     
/*      */     public int getChargeCost(World world, EntityPlayer player, int level) {
/* 1086 */       return EffectRegistry.costOfLeonardSpell(world, player, 2);
/*      */     }
/* 1078 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 1, 2, 0, 0, 3 }) });
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
/* 1092 */   public static final SymbolEffect LEONARD_4 = instance().addEffect(new SymbolEffect(46, "witchery.pott.leonard4", 5, false, false, null, 0)new StrokeSet
/*      */   {
/*      */     public void perform(World world, EntityPlayer player, int level)
/*      */     {
/* 1096 */       EffectRegistry.castLeonardSpell(world, player, 3);
/*      */     }
/*      */     
/*      */     public int getChargeCost(World world, EntityPlayer player, int level) {
/* 1100 */       return EffectRegistry.costOfLeonardSpell(world, player, 3);
/*      */     }
/* 1092 */   }, new StrokeSet[] { new StrokeSet(new byte[] { 0, 3, 1, 1, 2 }) });
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
/*      */   private static int costOfLeonardSpell(World world, EntityPlayer player, int spellSlot)
/*      */   {
/* 1107 */     int slot = InvUtil.getSlotContainingItem(player.field_71071_by, Witchery.Items.LEONARDS_URN);
/* 1108 */     if ((slot >= 0) && (slot < player.field_71071_by.func_70302_i_())) {
/* 1109 */       ItemStack urnStack = player.field_71071_by.func_70301_a(slot);
/* 1110 */       if (urnStack != null) {
/* 1111 */         ItemLeonardsUrn.InventoryLeonardsUrn inv = new ItemLeonardsUrn.InventoryLeonardsUrn(player, urnStack);
/* 1112 */         if (urnStack.func_77960_j() >= spellSlot) {
/* 1113 */           ItemStack potion = inv.func_70301_a(spellSlot);
/* 1114 */           if (potion != null) {
/* 1115 */             int baseLevel = WitcheryBrewRegistry.INSTANCE.getUsedCapacity(potion.func_77978_p());
/* 1116 */             if (player.func_70644_a(Witchery.Potions.WORSHIP)) {
/* 1117 */               PotionEffect effect = player.func_70660_b(Witchery.Potions.WORSHIP);
/* 1118 */               if (effect.func_76458_c() < 1)
/*      */               {
/*      */ 
/* 1121 */                 baseLevel += (int)Math.ceil(baseLevel * 0.5D);
/*      */               }
/*      */             } else {
/* 1124 */               baseLevel *= 2;
/*      */             }
/*      */             
/* 1127 */             return Math.max(baseLevel, 4);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1132 */     return 5;
/*      */   }
/*      */   
/*      */   private static void castLeonardSpell(World world, EntityPlayer player, int spellSlot) {
/* 1136 */     int slot = InvUtil.getSlotContainingItem(player.field_71071_by, Witchery.Items.LEONARDS_URN);
/* 1137 */     if ((slot >= 0) && (slot < player.field_71071_by.func_70302_i_())) {
/* 1138 */       ItemStack urnStack = player.field_71071_by.func_70301_a(slot);
/* 1139 */       if (urnStack != null) {
/* 1140 */         ItemLeonardsUrn.InventoryLeonardsUrn inv = new ItemLeonardsUrn.InventoryLeonardsUrn(player, urnStack);
/* 1141 */         if (urnStack.func_77960_j() >= spellSlot) {
/* 1142 */           ItemStack potion = inv.func_70301_a(spellSlot);
/* 1143 */           if (potion != null) {
/* 1144 */             world.func_72889_a((EntityPlayer)null, 1008, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 0);
/* 1145 */             EntityBrew entity = new EntityBrew(world, player, potion, true);
/* 1146 */             world.func_72838_d(entity);
/* 1147 */             return;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1154 */     SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*      */   }
/*      */   
/* 1157 */   public static final SymbolEffect Attraho = instance().addEffect(new SymbolEffectProjectile(47, "witchery.pott.attraho") {
/*      */     public void onCollision(World world, EntityLivingBase caster, MovingObjectPosition mop, EntitySpellEffect spell) {
/*      */       double R_SQ;
/* 1160 */       if ((caster != null) && (mop != null)) {
/* 1161 */         double R = spell.getEffectLevel() == 2 ? 3.0D : spell.getEffectLevel() == 1 ? 2.0D : 9.0D;
/* 1162 */         R_SQ = R * R;
/* 1163 */         AxisAlignedBB bb = AxisAlignedBB.func_72330_a(spell.field_70165_t - R, spell.field_70163_u - R, spell.field_70161_v - R, spell.field_70165_t + R, spell.field_70163_u + R, spell.field_70161_v + R);
/* 1164 */         List<EntityLivingBase> entities = world.func_72872_a(EntityLivingBase.class, bb);
/* 1165 */         for (EntityLivingBase entity : entities) {
/* 1166 */           if (entity.func_70068_e(spell) <= R_SQ) {
/* 1167 */             EntityUtil.pullTowards(world, entity, new com.emoniph.witchery.util.EntityPosition(caster), 0.04D, 0.1D);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1157 */   }.setColor(5322534).setSize(1.0F), new StrokeSet[] { new StrokeSet(1, new byte[] { 0, 0, 0, 2, 2, 1, 3 }) });
/*      */   
/*      */   private static abstract interface IBlockEffect
/*      */   {
/*      */     public abstract void doAction(World paramWorld, EntityLivingBase paramEntityLivingBase, int paramInt1, int paramInt2, int paramInt3, Block paramBlock, int paramInt4);
/*      */   }
/*      */   
/*      */   private static abstract interface IEntityEffect<T extends Entity>
/*      */   {
/*      */     public abstract void doAction(World paramWorld, EntityLivingBase paramEntityLivingBase, double paramDouble1, double paramDouble2, double paramDouble3, T paramT);
/*      */   }
/*      */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/symbols/EffectRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */