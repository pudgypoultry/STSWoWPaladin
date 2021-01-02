package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import WoWPaladin.characters.ThePaladin;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class OathOfPiety extends CustomCard {

    /*
     * Oath of Piety - 1
     * Common Skill
     * Gain 5(7) block.
     * When this is Retained, gain 1(2) strength.
     */


    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("OathOfPiety");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("DefendPaladin.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int BLOCK = 5;
    private static final int MAGIC_NUMBER = 1;
    private static final int UPGRADE_BLOCK = 3;
    private static final int UPGRADE_MAGIC_NUMBER = 1;

    // /STAT DECLARATION/


    public OathOfPiety() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = MAGIC_NUMBER;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //give player plated armor
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    public void onRetained() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, magicNumber), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BLOCK);
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}
