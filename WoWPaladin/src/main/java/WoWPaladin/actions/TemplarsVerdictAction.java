
package WoWPaladin.actions;

import WoWPaladin.powers.HolyPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TemplarsVerdictAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
{
    public TemplarsVerdictAction(AbstractCreature target)
    {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
        this.target = target;
    }

    public void update()
    {
        if ((this.target != null) && (this.target.hasPower("HolyPower"))) {
            AbstractDungeon.actionManager.addToTop(new com.megacrit.cardcrawl.actions.common.DrawCardAction(AbstractDungeon.player, 1));
            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, HolyPower.POWER_ID, 3));
        }
        this.isDone = true;
    }
}