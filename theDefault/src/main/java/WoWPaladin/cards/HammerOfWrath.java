package WoWPaladin.cards;

import WoWPaladin.OptionCards.SmiteThem;
import WoWPaladin.WoWPaladin;
import WoWPaladin.actions.HammerOfWrathAction;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import WoWPaladin.OptionCards.SealEvoke;

import java.util.ArrayList;
import java.util.Iterator;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class HammerOfWrath extends CustomCard {

    /*
    Hammer of Wrath - 1
    Uncommon Attack
    Gain 2 Holy. Deal damage equal to 3 times your Holy. Evoke your Seal.
    */

    public static final String ID = WoWPaladin.makeID("HammerOfWrath");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Judgement.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.NONE;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;
    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;


    public HammerOfWrath() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList();
        stanceChoices.add(new SealEvoke());
        stanceChoices.add(new SmiteThem());

        if (this.upgraded) {
            Iterator var4 = stanceChoices.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                c.upgrade();
            }
        }

        AbstractDungeon.actionManager.addToBottom(
                new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(
                        AbstractDungeon.player, AbstractDungeon.player, new HolyPower(p, this.magicNumber), this.magicNumber));

        AbstractDungeon.actionManager.addToBottom(new ChooseOneAction(stanceChoices));

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}
