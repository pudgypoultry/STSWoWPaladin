package WoWPaladin.cards;

import WoWPaladin.OptionCards.SmiteThem;
import WoWPaladin.WoWPaladin;
import WoWPaladin.actions.HammerOfWrathAction;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
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
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class Smite extends CustomCard {

    /*
    Smite - 1
    Common Attack
    Deal damage equal to 3(4) times your Holy.
    */

    public static final String ID = WoWPaladin.makeID("Smite");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("Judgement.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    //Stat value variable declarations
    private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.SPECIAL;
    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
    public static final AbstractCard.CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;
    private static final int COST = 1;
    private static final int DAMAGE = 3;
    private static final int UPGRADE_DAMAGE = 1;


    public Smite() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = damage = DAMAGE;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {


        if(AbstractDungeon.player.hasPower(HolyPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, damage * AbstractDungeon.player.getPower(HolyPower.POWER_ID).amount, damageTypeForTurn),
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_DAMAGE);
            initializeDescription();
        }
    }
}