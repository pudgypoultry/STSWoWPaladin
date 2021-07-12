package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.orbs.SealOfRighteousness;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import WoWPaladin.characters.ThePaladin;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class RighteousDuty extends CustomCard {

    /*
     * Righteous Duty - 0
     * Uncommon Skill
     * Channel 1 Seal of Righteousness. Exhaust.
     * (Upgrade: Don't exhaust).
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("RighteousDuty");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("RighteousDuty.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;

    private static final int COST = 0;
    private static final int MAGIC = 1;


    // /STAT DECLARATION/

    public RighteousDuty() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!this.upgraded){
            //AbstractDungeon.actionManager.addToTop(new RemoveAllOrbsAction());
            // Remove all orbs without evoking
        }

        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new SealOfRighteousness()));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EquilibriumPower(p, this.magicNumber), this.magicNumber));
        // Channel a Seal of Righteousness.

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.exhaust = false;
            initializeDescription();
        }
    }
}
