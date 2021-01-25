package WoWPaladin.cards;

import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;
import WoWPaladin.powers.HolyPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;


import static WoWPaladin.WoWPaladin.makeCardPath;

/*
Blessing of Kings - 2
Uncommon Skill
Gain 2(4) dexterity and Strength
*/
public class DivineStorm extends CustomCard {

    public static final String ID = WoWPaladin.makeID("DivineStorm");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("DivineStorm.png");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    //Stat value variable declarations
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;
    private static final int COST = 3;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;


    public DivineStorm() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;

    }

    public DivineStorm(boolean exhaust, boolean upgraded) {
        super(ID, NAME, IMG, 0, DESCRIPTION.substring(0,95) + " Exhaust.", TYPE, COLOR, RARITY, TARGET);
        this.exhaust = exhaust;
        this.baseDamage = DAMAGE;
        if (upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),
                // a list of existing actions can be found at com.megacrit.cardcrawl.actions but
                // Chances are you'd instead look at "hey my card is similar to this basegame card"
                // Let's find out what action *it* uses.
                // I.e. i want energy gain or card draw, lemme check out Adrenaline
                // P.s. if you want to damage ALL enemies OUTSIDE of a card, check out the custom orb.
                AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if(p.hasPower(HolyPower.POWER_ID)) {
            int devo = p.getPower(HolyPower.POWER_ID).amount;

            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, HolyPower.POWER_ID));

            for (int i = 0; i < devo; i++) {
                p.hand.addToHand(new DivineStorm(true, this.upgraded));
            }
        }
    }

    public void triggerOnGlowCheck() {
        if ((AbstractDungeon.player.hasPower(HolyPower.POWER_ID))) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
        }
    }
}
