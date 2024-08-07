package theperformersmod.powers;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theperformersmod.ThePerformersMod.makeID;

public class FourfoldFeathersPower extends BasePower{
    public static final String POWER_ID = makeID("FourfoldFeathersPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing TURN_BASED controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if their amount is positive or negative.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public FourfoldFeathersPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if (this.amount > 4) {
            this.amount = 4;
            addToBot(new TalkAction(true, this.DESCRIPTIONS[3], 1.0F, 2.0F));
        }

        this.canGoNegative = false;
    }


    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0){
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (this.amount > 4){
            this.amount = 4;
        }
    }

    @Override
    public void updateDescription() {
        super.updateDescription();
        if (this.amount == 1)
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        else
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }
}
