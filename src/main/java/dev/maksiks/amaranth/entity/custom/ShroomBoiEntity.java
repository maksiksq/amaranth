package dev.maksiks.amaranth.entity.custom;

import dev.maksiks.amaranth.entity.ModEntities;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

public class ShroomBoiEntity extends Animal {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private Player eatingPlayer = null;
    private int eatingProgress = 0;
    private int timeSinceLastInteraction = 0;
    private static final int EATING_DURATION = 60;
    private static final int MAX_INTERACTION_GAP = 5;
    private static final int HUNGER_RESTORE = 6;
    private static final float HEALTH_RESTORE = 4.0F;

    public ShroomBoiEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(Items.ROTTEN_FLESH), false));

        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(Items.ROTTEN_FLESH);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return ModEntities.SHROOM_BOI.get().create(level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (this.isBaby() && hand == InteractionHand.MAIN_HAND && itemStack.isEmpty()) {
            if (!this.level().isClientSide()) {
                if (this.eatingPlayer == null) {
                    this.eatingPlayer = player;
                    this.eatingProgress = 0;
                    this.timeSinceLastInteraction = 0;

                    this.playSound(SoundEvents.GENERIC_EAT, 0.5F + 0.5F * (float) this.random.nextInt(2),
                            (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                    this.gameEvent(GameEvent.EAT);
                } else if (this.eatingPlayer == player) {
                    this.timeSinceLastInteraction = 0;
                }
            }
            return InteractionResult.CONSUME;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        } else {
            this.handleEatingProcess();
        }
    }

    private void handleEatingProcess() {
        if (this.eatingPlayer != null && this.isBaby()) {
            if (this.eatingPlayer.distanceToSqr(this) > 4.0D ||
                    !this.eatingPlayer.isAlive() ||
                    this.eatingPlayer.isSpectator() ||
                    !this.eatingPlayer.getMainHandItem().isEmpty()) {
                this.cancelEating();
                return;
            }

            this.timeSinceLastInteraction++;

            if (this.timeSinceLastInteraction > MAX_INTERACTION_GAP) {
                this.cancelEating();
                return;
            }

            this.eatingProgress++;

            if (this.eatingProgress % 5 == 0) {
                this.spawnEatingParticles();

                if (this.eatingProgress % 10 == 0) {
                    this.playSound(SoundEvents.GENERIC_EAT, 0.3F,
                            (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                }
            }

            if (this.eatingProgress >= EATING_DURATION) {
                this.completeEating();
            }
        }
    }

    private void spawnEatingParticles() {
        if (this.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < 3; i++) {
                double x = this.getX() + (this.random.nextDouble() - 0.5) * this.getBbWidth();
                double y = this.getY() + this.random.nextDouble() * this.getBbHeight();
                double z = this.getZ() + (this.random.nextDouble() - 0.5) * this.getBbWidth();

                serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MUSHROOM_STEM.defaultBlockState()),
                        x, y, z, 1,
                        (this.random.nextDouble() - 0.5) * 0.1,
                        this.random.nextDouble() * 0.1,
                        (this.random.nextDouble() - 0.5) * 0.1,
                        0.05);
            }
        }
    }

    private void completeEating() {
        if (this.eatingPlayer != null && !this.level().isClientSide()) {
            FoodData foodData = this.eatingPlayer.getFoodData();
            foodData.eat(HUNGER_RESTORE, 0.6F);

            this.eatingPlayer.heal(HEALTH_RESTORE);

            this.playSound(SoundEvents.PLAYER_BURP, 1.0F, 1.0F);

            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(        new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MUSHROOM_STEM.defaultBlockState()),
                        this.getX(), this.getY() + this.getBbHeight() * 0.5, this.getZ(),
                        15, 0.3, 0.3, 0.3, 0.1);
            }

            this.discard();
        }

        this.eatingPlayer = null;
        this.eatingProgress = 0;
    }

    private void cancelEating() {
        this.eatingPlayer = null;
        this.eatingProgress = 0;
        this.timeSinceLastInteraction = 0;
    }

    @Override
    public void die(net.minecraft.world.damagesource.DamageSource damageSource) {
        this.cancelEating();
        super.die(damageSource);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 10;
            idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    // unused
    public int getEatingProgress() {
        return this.eatingProgress;
    }

    public boolean isBeingEaten() {
        return this.eatingPlayer != null;
    }
}