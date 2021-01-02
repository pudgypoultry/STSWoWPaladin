package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;

import static WoWPaladin.WoWPaladin.makeCardPath;

/*
Blessing of Kings - 2
Uncommon Skill
Gain 2(4) dexterity and Strength
*/
public class ContemplativeMeditation extends CustomCard {

    public static final String ID = WoWPaladin.makeID("ContemplativeMedicine");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("StrikePaladin.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    //Stat value variable declarations
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;
    private static final int COST = 1;






    public ContemplativeMeditation() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;

    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.hasPower(HolyPower.POWER_ID)) {
            int devo = p.getPower(HolyPower.POWER_ID).amount;

            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, HolyPower.POWER_ID));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FocusPower(p, 1), devo));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
        }
    }
}
