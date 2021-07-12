package WoWPaladin.cards;


import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EquilibriumPower;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class LayOnHands extends CustomCard {

    /*
    Lay On Hands - 4(1)
    Rare Skill
    Draw your deck. Heal 10. Exhaust.
    */

    public static final String ID = WoWPaladin.makeID("LayOnHands");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("LayOnHands.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;


    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_PALADINYELLOW;
    private static final int COST = 3;
    private static final int HEAL_AMT = 10;
    private static final int UPGRADE_MAGIC = 5;
    private static final int UPGRADE_COST = 2;




    public LayOnHands() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = HEAL_AMT;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ExpertiseAction(p, 10));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EquilibriumPower(p, 1), 1));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            //upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }

}
