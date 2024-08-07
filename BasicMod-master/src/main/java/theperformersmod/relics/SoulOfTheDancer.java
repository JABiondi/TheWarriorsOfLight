package theperformersmod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import theperformersmod.character.ThePerformers;
import theperformersmod.powers.FourfoldFeathersPower;

import static theperformersmod.ThePerformersMod.makeID;

public class SoulOfTheDancer extends BaseRelic {
    private static final String NAME = "SoulOfTheDancer"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:SoulOfTheDancer
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.

    private static final double CHANCE = .33;
    private static final int NUM_FEATHERS = 1;
    private static final Random RANDOM = new Random();

    public SoulOfTheDancer() {
        super(ID, NAME, ThePerformers.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        super.onUseCard(targetCard, useCardAction);
        // if chance is 60%, (.6) then number has to be greater than .4
        if (targetCard.type == AbstractCard.CardType.ATTACK) {
            if (RANDOM.random((float)0.0, (float)1.0) > (1. - CHANCE)) {
                flash();
                addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FourfoldFeathersPower(AbstractDungeon.player, NUM_FEATHERS), NUM_FEATHERS));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + NUM_FEATHERS + DESCRIPTIONS[1];
    }
}
