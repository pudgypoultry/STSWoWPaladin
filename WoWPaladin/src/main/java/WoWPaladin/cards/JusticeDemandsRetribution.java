package WoWPaladin.cards;

import WoWPaladin.actions.JusticeDemandsRetributionAction;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import WoWPaladin.WoWPaladin;
import WoWPaladin.characters.ThePaladin;

import static WoWPaladin.WoWPaladin.makeCardPath;

public class JusticeDemandsRetribution extends CustomCard {

    /*
     * Justice Demands Retribution! - 2
     * Uncommon Attack
     * Deal 10 damage to an enemy. If this is fatal, gain 5 plated armor.
     */

    // TEXT DECLARATION

    public static final String ID = WoWPaladin.makeID("JusticeDemandsRetribution");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = makeCardPath("JusticeDemandsRetribution.png");


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = ThePaladin.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 4;
    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC = 2;

    // /STAT DECLARATION/

    public JusticeDemandsRetribution() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.damage = baseDamage = DAMAGE;
        this.magicNumber = baseMagicNumber = MAGIC;

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom( new JusticeDemandsRetributionAction(m, new DamageInfo(p, damage, damageTypeForTurn), magicNumber));
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBlock(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}
