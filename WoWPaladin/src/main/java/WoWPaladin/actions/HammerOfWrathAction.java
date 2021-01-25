package WoWPaladin.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HammerOfWrathAction extends com.megacrit.cardcrawl.actions.AbstractGameAction
{
    private DamageInfo info;
    private AbstractCreature target;
    private int holyAmount;

    public HammerOfWrathAction(DamageInfo info, AbstractCreature target, int damageAmount, int magicAmount)
    {
        this.info = info;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
        this.target = target;

        if(this.target != null){
            if ((AbstractDungeon.player.hasPower("HolyPower"))) {
                holyAmount = damageAmount * AbstractDungeon.player.getPower("HolyPower").amount;
            }
            else{
                holyAmount = damageAmount * magicAmount;
            }
        }
    }

    public void update()
    {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(this.target, new DamageInfo(AbstractDungeon.player, holyAmount, DamageInfo.DamageType.NORMAL),
                        AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        this.isDone = true;
    }
}