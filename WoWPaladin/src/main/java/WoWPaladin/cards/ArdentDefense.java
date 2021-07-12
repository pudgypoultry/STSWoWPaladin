package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class ArdentDefense extends CustomCard {

    /*
     * Sentinel Stance - 1
     * Common Skill
     * Gain 7 block. Valiance - Instead, gain 12(15) block.
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("ArdentDefense");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("ArdentDefense.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;

    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC_NUMBER = 3;
    boolean isEliteOrBoss;


    // /STAT DECLARATION/

    public ArdentDefense() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = baseMagicNumber = MAGIC;
        this.block = baseBlock = BLOCK;
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
/*
    @Override
    public void applyPowers() {
        if (AbstractDungeon.getCurrRoom().eliteTrigger) {
            this.magicNumber = 0;
        }
    }
*/
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block)); //Block
        if(AbstractDungeon.getCurrRoom().eliteTrigger || isEliteOrBoss){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, magicNumber)); //Block + Extra Block
        }
    }

    public void triggerOnGlowCheck() {
        if (AbstractDungeon.getCurrRoom().eliteTrigger || isEliteOrBoss) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}