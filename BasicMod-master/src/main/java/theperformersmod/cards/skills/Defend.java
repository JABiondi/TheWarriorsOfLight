package theperformersmod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theperformersmod.cards.BaseCard;
import theperformersmod.character.ThePerformers;
import theperformersmod.util.CardStats;

public class Defend extends BaseCard {
    public static final String ID = makeID(Defend.class.getSimpleName()); // unique label for card. Turns CLASS name into card ID
    private static final CardStats info = new CardStats(    // organized way to store card stats in CardStats object
            ThePerformers.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Defend() {
        super(ID, info);
        setBlock(BLOCK, UPG_BLOCK);

        // Tags for use in other parts of the game.
        tags.add(CardTags.STARTER_DEFEND); // Used by pandoras box
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot method adds given action to bottom of action queue
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Defend();
    }

}
