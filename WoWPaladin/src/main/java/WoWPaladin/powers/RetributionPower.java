package WoWPaladin.powers;

import WoWPaladin.WoWPaladin;
import WoWPaladin.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import basemod.*;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;

import static WoWPaladin.WoWPaladin.makePowerPath;

public class RetributionPower extends AbstractPower implements OnReceivePowerPower{
    public static final String POWER_ID = WoWPaladin.makeID("RetributionPower");
    private static final PowerStrings powerStrings = com.megacrit.cardcrawl.core.CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("RetributionAuraPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("RetributionAuraPower32.png"));

    public RetributionPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.priority = 0;
        this.canGoNegative = false;
        this.type = PowerType.BUFF;
        updateDescription();

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

    }

    @Override
    public void stackPower(int stackAmount) {
        {
            this.fontScale = 8.0F;
            this.amount += stackAmount;
        }
    }

    @Override
    public boolean onReceivePower(AbstractPower p, AbstractCreature c1, AbstractCreature c2) {
        if(p.ID == HolyPower.POWER_ID){
            if((AbstractDungeon.player.hasPower(HolyPower.POWER_ID)) && AbstractDungeon.player.getPower(HolyPower.POWER_ID).amount < 5){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(c1, c2, new StrengthPower(AbstractDungeon.player, this.amount), this.amount));
            }
            else if((AbstractDungeon.player.hasPower(HolyPower.POWER_ID)) && AbstractDungeon.player.getPower(HolyPower.POWER_ID).amount == 5){
                //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(c1, c2, new StrengthPower(AbstractDungeon.player, this.amount), this.amount));

            }
            else{
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(c1, c2, new StrengthPower(AbstractDungeon.player, this.amount), this.amount));
            }
            //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(c1, c2, new StrengthPower(AbstractDungeon.player, this.amount), this.amount));
        }
        return true;
    }

    @Override
    public void updateDescription(){
        {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        }
    }
}