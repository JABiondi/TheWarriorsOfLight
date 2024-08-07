package theperformersmod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theperformersmod.cards.BaseCard;
import theperformersmod.character.ThePerformers;
import theperformersmod.powers.FourfoldFeathersPower;
import theperformersmod.util.CardStats;

public class FanDance extends BaseCard {
    public static final String ID = makeID(FanDance.class.getSimpleName()); // unique label for card. Turns CLASS name into card ID
    private static final CardStats info = new CardStats(    // organized way to store card stats in CardStats object
            ThePerformers.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            0 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;
    // private static final int MAGIC_NUMBER = 1;

    public FanDance() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        // setMagic(MAGIC_NUMBER);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot method adds given action to bottom of action queue
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ReducePowerAction(p, p, FourfoldFeathersPower.POWER_ID, 1));
        // addToBot(new DrawCardAction(p, MAGIC_NUMBER));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        // Checks if player has a feather or not
        canUse = p.hasPower(FourfoldFeathersPower.POWER_ID);
        if (!canUse)
            // TODO: CHANGE THIS TO PULL MESSAGE FROM 'UIStrings.json', REMOVE PURE STRING
            this.cantUseMessage = "I need at least 1 #rFourfold #rFeather!";
        return canUse;
    }



    @Override
    public void triggerOnGlowCheck() {
        super.triggerOnGlowCheck();
        boolean glow = AbstractDungeon.player.hasPower(FourfoldFeathersPower.POWER_ID);

        if (glow) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }



    @Override
    public AbstractCard makeCopy() {
        return new FanDance();
    }

}
