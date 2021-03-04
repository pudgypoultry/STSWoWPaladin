package WoWPaladin.cards;

import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class EyeForAnEye extends CustomCard {

    /*
    Eye For An Eye - 1
    Common Skill
    When you are attacked this turn, deal 15 (20) damage back. Gain 2 Holy.
    */

    public static final String ID = WoWPaladin.makeID("EyeForAnEye");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("EyeForAnEye.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;
    private static final int COST = 1;
    private static final int MAGIC_NUMBER = 10;
    private static final int UPGRADE_MAGIC = 5;


    public EyeForAnEye() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC_NUMBER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new FlameBarrierPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom( new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HolyPower(p, 2), 2));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}

