package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.powers.HolyPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import WoWPaladin.characters.ThePaladin;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class DivineBulwark extends CustomCard {

    /*
     * Wiki-page: https://github.com/daviscook477/BaseMod/wiki/Custom-Cards
     *
     * Defend Gain 5 (8) block.
     */


    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("DivineBulwark");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("DivineBulwark.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    // /STAT DECLARATION/


    public DivineBulwark() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = baseMagicNumber = MAGIC;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(AbstractDungeon.player.hasPower(HolyPower.POWER_ID)){
            int holyAmount = AbstractDungeon.player.getPower(HolyPower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(
                    new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new com.megacrit.cardcrawl.powers.PlatedArmorPower(AbstractDungeon.player, holyAmount), holyAmount));
        }

        AbstractDungeon.actionManager.addToBottom(
                new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(
                        AbstractDungeon.player, AbstractDungeon.player, new HolyPower(p, this.magicNumber), this.magicNumber));

    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            upgradeBaseCost(1);
            initializeDescription();
        }
    }
}
