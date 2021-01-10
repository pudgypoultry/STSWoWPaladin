package WoWPaladin.cards;

import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Whirlwind;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class ShieldUp extends CustomCard {

    /*
     * Templar's Verdict - 2
     * Starter Attack
     * Strike Deal 10 damage. If you have 3(2) or more Holy, spend it and deal 25 instead.
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("ShieldUp");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("TemplarsVerdict.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int BLOCK = 14;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC_NUMBER = -1;
    private static final int UPGRADE_BLOCK = 4;


    // /STAT DECLARATION/

    public ShieldUp() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block = baseBlock = BLOCK;
        this.magicNumber = baseMagicNumber = MAGIC;
    }
/*
    @Override
    public void applyPowers() {
        int originalBaseDamage = this.baseDamage;
        this.baseDamage = ACTIVATED_DAMAGE;
        super.applyPowers();
        int activeDamage = this.damage;
        this.baseDamage = originalBaseDamage;
        super.applyPowers();
        this.baseBlock = ACTIVATED_DAMAGE;
        this.block = activeDamage;
        this.isBlockModified = activeDamage != ACTIVATED_DAMAGE;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int originalBaseDamage = this.baseDamage;
        this.baseDamage = ACTIVATED_DAMAGE;
        super.calculateCardDamage(mo);
        int activeDamage = this.damage;
        this.baseDamage = originalBaseDamage;
        super.calculateCardDamage(mo);
        this.baseBlock = ACTIVATED_DAMAGE;
        this.block = activeDamage;
        this.isBlockModified = activeDamage != ACTIVATED_DAMAGE;
    }
*/

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if ((AbstractDungeon.player.hasPower(HolyPower.POWER_ID)) && AbstractDungeon.player.getPower(HolyPower.POWER_ID).amount >= this.magicNumber) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, HolyPower.POWER_ID, this.magicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, 5), 5));

        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }

    public void triggerOnGlowCheck() {
        if ((AbstractDungeon.player.hasPower(HolyPower.POWER_ID)) && AbstractDungeon.player.getPower(HolyPower.POWER_ID).amount >= this.magicNumber) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC_NUMBER);
            upgradeBlock(UPGRADE_BLOCK);
            initializeDescription();
        }
    }
}

