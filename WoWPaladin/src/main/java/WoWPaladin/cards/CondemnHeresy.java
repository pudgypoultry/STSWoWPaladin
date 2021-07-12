package WoWPaladin.cards;

import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.unique.SkewerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class CondemnHeresy extends CustomCard {

    /*
     * Condemn Heresy - X
     * Common Attack
     * Deal 5(7) Damage X times. Valiance - 2X times.
     *
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("CondemnHeresy");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("CondemnHeresy.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;

    private static final int COST = -1;
    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE = 2;
    boolean isEliteOrBoss;

    // /STAT DECLARATION/

    public CondemnHeresy() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        baseDamage = DAMAGE;
        this.isMultiDamage = true;
    }

    public void atTurnStart() {
        isEliteOrBoss = false;
        Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

        while (var2.hasNext()) {
            AbstractMonster m = (AbstractMonster) var2.next();
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
            }
        }
    }

    public void triggerWhenCopied(){
        isEliteOrBoss = false;
        Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

        while (var2.hasNext()) {
            AbstractMonster m = (AbstractMonster) var2.next();
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isEliteOrBoss = true;
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (p.hasRelic("Chemical X")) {
            //effect += 2;
            //p.getRelic("Chemical X").flash();
        }
        if (AbstractDungeon.getCurrRoom().eliteTrigger || isEliteOrBoss) {
            AbstractDungeon.actionManager.addToBottom(new SkewerAction(p, m, damage * 2, this.damageTypeForTurn, this.freeToPlayOnce, effect));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new SkewerAction(p, m, damage, this.damageTypeForTurn, this.freeToPlayOnce, effect));
        }

    }

    public void triggerOnGlowCheck() {
        if (AbstractDungeon.getCurrRoom().eliteTrigger || isEliteOrBoss) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}
