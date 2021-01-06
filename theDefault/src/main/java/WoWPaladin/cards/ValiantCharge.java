package WoWPaladin.cards;

import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.watcher.WallopAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class ValiantCharge extends CustomCard {

    /*
     * Valiant Charge - 1
     * Common Attack
     * Deal 8 damage. Valiance - Gain block equal to unblocked damage dealt.
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("ValiantCharge");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("SentinelStance.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int UPGRADE_BLOCK = 3;
    private static final int ACTIVATED_DAMAGE = 15;
    private boolean isEliteOrBoss;

    // /STAT DECLARATION/

    public ValiantCharge() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        baseDamage = DAMAGE;

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
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));


        if (AbstractDungeon.getCurrRoom().eliteTrigger || isEliteOrBoss) {
            AbstractDungeon.actionManager.addToBottom(new WallopAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
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
            upgradeBlock(UPGRADE_BLOCK);
            initializeDescription();
        }
    }
}
