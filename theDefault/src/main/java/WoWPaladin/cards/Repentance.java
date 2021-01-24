package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import WoWPaladin.powers.LoseHolyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;


import static WoWPaladin.WoWPaladin.makeCardPath;

public class Repentance extends CustomCard {

    /*
    Repentance - 0
    Common Skill
    Evoke your seal. Draw a card. (Don't) Exhaust.
     */

    //Card text variable declarations
    public static final String ID = WoWPaladin.makeID("Repentance");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Repentance.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;
    private static final int COST = 0;
    private static final int MAGIC = 2;

    public Repentance() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new EvokeOrbAction(MAGIC));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, MAGIC));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Repentance();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.exhaust = false;
            initializeDescription();
        }
    }
}
