package WoWPaladin.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import WoWPaladin.cards.JusticeDemandsRetribution;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class JusticeDemandsRetributionAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private int magicNumber;
    private AbstractPlayer p;
    private int energyOnUse;
    private boolean upgraded;
    private DamageInfo info;
    private static final float DURATION = 0.1F;
    private int platedAmount;

    public JusticeDemandsRetributionAction(AbstractCreature target, DamageInfo info, int plated) {
        this.freeToPlayOnce = false;
        this.setValues(target, this.info = info);
        this.duration = 0.1F;
        actionType = ActionType.DAMAGE;
        this.platedAmount = plated;
    }

    @Override
    public void update() {
        if (this.duration == DURATION && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
            this.target.damage(this.info);
            if ((((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {
                AbstractDungeon.actionManager.addToBottom( // 2.Gain plated armor
                        new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                new com.megacrit.cardcrawl.powers.PlatedArmorPower(AbstractDungeon.player, platedAmount), platedAmount));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            else{
                this.target.damage(this.info);
            }
        }

        this.tickDuration();
    }
}
