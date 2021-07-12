package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static WoWPaladin.WoWPaladin.makeCardPath;

public class HammerOfJustice extends CustomCard {

    /*
    Hammer of Justice - 3(2)
    Rare Skill
    Stun the target. Gain 2 Devotion. Exhaust
     */

    //Card text variable declarations
    public static final String ID = WoWPaladin.makeID("HammerOfJustice");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("HammerOfJustice.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    private static final int MAGIC = 2;

    public HammerOfJustice() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(m, m, 1));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HolyPower (p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HammerOfJustice();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
