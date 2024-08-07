package theperformersmod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theperformersmod.cards.BaseCard;
import theperformersmod.character.ThePerformers;
import theperformersmod.powers.FourfoldFeathersPower;
import theperformersmod.util.CardStats;

public class FeatherAttack extends BaseCard {
    public static final String ID = makeID(FeatherAttack.class.getSimpleName()); // unique label for card. Turns CLASS name into card ID
    private static final CardStats info = new CardStats(    // organized way to store card stats in CardStats object
            ThePerformers.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 1;

    public FeatherAttack() {
        super(ID, info);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC);

        // Tags for use in other parts of the game.
         // Used by strike dummy / perfected strike
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot method adds given action to bottom of action queue
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(p, p, new FourfoldFeathersPower(p, magicNumber)));
                {
            //new DamageAction is an action that deals damage
            //First arg: Target to deal dmg to, will be 'm'
            //Second arg: DamageInfo. Contains required info abt the damage to deal -> source, amount, type
                    //Source: Always the player. 'p' variable
                    //Amount: The cards damage value. 'damage' variable (must be set in constructor)
                    //Type: 3 types of damage.
                    //ALL attacks deal 'NORMAL' damage.
                    //Any blockable damage not from attacks is 'THORNS'
                    //Damage that ignores block is 'HP_LOSS'
            //Third arg: AttackEffect. Visual/sound effect that will be used for the damage.
            //For fancier effects, AttackEffect.NONE is used and visuals/sounds are added separately. Ex) Bludgeon
                } // brackets around comments for cleanliness
    }

    @Override
    public AbstractCard makeCopy() {
        return new FeatherAttack();
    }

}
