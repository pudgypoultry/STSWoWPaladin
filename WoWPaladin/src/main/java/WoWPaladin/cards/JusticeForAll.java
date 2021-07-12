package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.orbs.SealOfJustice;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class JusticeForAll extends CustomCard {

    /*
     * Justice For All - 1
     * Uncommon Skill
     * Remove your current seal. Channel 1 Seal of Justice. Exhaust.
     * (Upgrade: Do not remove your current seal.)
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("JusticeForAll");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("JusticeForAll.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;

    private static final int COST = 1;
    private static final int MAGIC = 7;

    // /STAT DECLARATION/

    public JusticeForAll() {
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

        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new SealOfJustice()));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FlameBarrierPower(p, this.magicNumber), this.magicNumber));

        // Channel a Seal of Justice

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
