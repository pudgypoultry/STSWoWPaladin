package WoWPaladin.cards;

import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.FocusPotion;
import com.megacrit.cardcrawl.powers.FocusPower;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class BlessingOfWisdom extends CustomCard {

    /*
    Blessing of Wisdom - 2
    Uncommon Skill
    Gain 3 focus. Exhaust (Remove Exhaust)
    */

    //Card text variable declarations
    public static final String ID = WoWPaladin.makeID("BlessingOfWisdom");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("BlessingOfWisdom.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;
    private static final int COST = 2;


    public BlessingOfWisdom() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom( new ApplyPowerAction(p,p, new FocusPower(p, 3), 3));
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

