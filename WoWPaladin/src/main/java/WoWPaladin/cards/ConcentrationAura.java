package WoWPaladin.cards;

import WoWPaladin.powers.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class ConcentrationAura extends AbstractDynamicCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Weirdness Apply X (+1) keywords to yourself.
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("ConcentrationAura");
    public static final String IMG = makeCardPath("ConcentrationAura.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/

    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;

    private static final int COST = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    // /STAT DECLARATION/

    public ConcentrationAura() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        this.isInnate = false;
    }

    // Actions the card should do.
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ConcentrationAuraPower(p, this.magicNumber), this.magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            //upgradeMagicNumber(UPGRADE_MAGIC);
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
        }
    }
}