package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import WoWPaladin.powers.LoseHolyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static WoWPaladin.WoWPaladin.makeCardPath;

public class Prayer extends CustomCard {

    /*
    Prayer - 0
    Common Skill
    Gain +2(4) dexterity until end of turn.
     */

    //Card text variable declarations
    public static final String ID = WoWPaladin.makeID("Prayer");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Prayer.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;
    private static final int COST = 0;
    private static final int UPGRADE_COST = 1;
    private static final int MAGIC = 5;

    public Prayer() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HolyPower (p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseHolyPower(p, this.magicNumber), this.magicNumber));

        if(this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new BetterDiscardPileToHandAction(1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Prayer();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;

            initializeDescription();
        }
    }
}
