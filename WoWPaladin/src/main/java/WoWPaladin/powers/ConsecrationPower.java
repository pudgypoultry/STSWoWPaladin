package WoWPaladin.powers;

import WoWPaladin.WoWPaladin;
import WoWPaladin.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;

import static WoWPaladin.WoWPaladin.makePowerPath;

public class ConsecrationPower extends AbstractPower {
    public static final String POWER_ID = WoWPaladin.makeID("ConsecrationPower");
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("ConsecrationPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("ConsecrationPower32.png"));

    public ConsecrationPower(AbstractCreature owner, int newAmount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = newAmount;
        this.type = PowerType.BUFF;
        this.updateDescription();
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atEndOfTurn(boolean isPlayer) {
        AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(amount * AbstractDungeon.player.hand.size(), true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));

        //AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, amount * AbstractDungeon.player.hand.size(), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }


}
